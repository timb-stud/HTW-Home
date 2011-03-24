package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Tim Bartsch
 */
public class SmokeDetector extends IntervalSensor<Boolean>{

    public static final DeviceType deviceType = DeviceType.SmokeDetector;
    public static final Type cfgType = new TypeToken<Config<Boolean>>(){}.getType();

    public SmokeDetector() {
        super.load();
    }
    
    public SmokeDetector (int id, Boolean status, String location, String description) throws SocketException {
        super(id, status, location, description);
    }

    @Override
    public void handleMsg(String msg) {
	handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(String status) {
	boolean b = Boolean.valueOf(status);
	setStatus(b);
    }

    @Override
    public void setStatus(Boolean status) {
	this.status = status;
	if(this.status == true){
            System.out.println("!! ACHTUNG !!");
	    Message warning = new Message();
            warning.setMsgType(MessageType.fireAlarm);
            warning.setSenderId(this.id);
            warning.setReceiverId(ALLDEVICES);
            warning.setContent(String.valueOf(this.status));
            warning.setSenderDevice(deviceType);
            this.sendMsg(warning);
        }
	Message msg = new Message();
        msg.setMsgType(MessageType.statusResponse);
        msg.setSenderId(this.id);
        msg.setContent(String.valueOf(this.status));
	msg.setSenderDevice(deviceType);
	this.sendMsg(msg);
    }

    public static void main(String[] args) throws SocketException {
        SmokeDetector sd = new SmokeDetector(11301, false, "Wohnzimmer", "Rauchmelder");
        sd.setStatus(true);
    }

}