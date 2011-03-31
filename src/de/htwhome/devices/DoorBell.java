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
public class DoorBell extends AbstractDevice<Boolean>{

    public static final DeviceType deviceType = DeviceType.DoorBell;
    public static final Type cfgType = new TypeToken<Config<Boolean>>() {}.getType();

    public DoorBell(int id) {
	super();
	super.loadConfig(deviceType);
    }

    public DoorBell(int id, Boolean status, String location, String description) throws SocketException{
	super(id, status, location, description);
	saveConfig(deviceType);
    }

    @Override
    public void handleMsg(String msg) {
    }

    @Override
    public void handleMsg(String jsonMsg, DeviceType devType, Type cfgType) {
    }

    @Override
    protected void fireChangeEvent() {
    }

    @Override
    public void setStatus(String status) {
	boolean b = Boolean.valueOf(status);
	setStatus(b);
    }

    @Override
    public void setStatus(Boolean status) {
	this.status = status;
	fireChangeEvent();
	Message msg = new Message();
        msg.setMsgType(MessageType.doorRing);
        msg.setSenderId(this.id);
        msg.setSenderDevice(deviceType);
        this.sendMsg(msg);
    }

    public static void main(String[] args) throws SocketException {
	DoorBell db = new DoorBell(10, false, "vorm Haus", "kingel");
	db.setStatus(true);
    }

}
