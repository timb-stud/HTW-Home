package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.ActorConfig;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Tobias Lana
 * Shutter (en) = Rollladen (de)
 */
public class Shutter extends Actor<Integer>{

    public static final Type cfgType = new TypeToken<ActorConfig<Integer>>(){}.getType();
    public static final DeviceType deviceType = DeviceType.Shutter;
    
    private int OPEN    = 0;
    private int SAFETY  = 66;
    private int CLOSE   = 100;

    public Shutter(int id, int status, String location, String description, int[] gidTab) throws SocketException {
        super(id, status, location, description, gidTab);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void handleWeatherAlarm() {
        setStatus(SAFETY);
    }

    @Override
    public void handleFireAlarm() {
        setStatus(OPEN);
    }

    @Override
    public void setStatus(Integer status) {
	this.status = status;
	Message msg = new Message();
	msg.setMsgType(MessageType.statusResponse);
	msg.setSenderId(this.id);
	msg.setSenderDevice(deviceType);
	msg.setContent(String.valueOf(this.status));
	sendMsg(msg);
    }

    @Override
    public void setStatus(String status) {
	int i = Integer.valueOf(status);
	this.setStatus(i);
    }

    public static void main(String[] args) throws SocketException {
        int[] gidTab = {22000, 22100, 22101}; //TODO aus Konfig
        Shutter s = new Shutter(12301, 0, "Wohnzimmer", "Rolladen vorne", gidTab); //TODO aus Konfig
        s.setStatus(85);
    }

}
