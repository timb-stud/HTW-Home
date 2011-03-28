package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.timer.TimeScheduler;
import de.htwhome.timer.TimerOptions;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private void startNotifier(int intervall){
        TimeScheduler<Boolean> ts = new TimeScheduler<Boolean>(this, TimerOptions.SMOKEDETECTOR);
        ts.startIntervallRandom(intervall);
    }

    public static void main(String[] args) {
        try {
            SmokeDetector sd = new SmokeDetector(11301, false, "Wohnzimmer", "Rauchmelder");
            sd.startNotifier(2000);
        } catch (SocketException ex) {
            Logger.getLogger(SmokeDetector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
