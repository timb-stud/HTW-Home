package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Switch extends AckSensor<Boolean> {

    public static final DeviceType deviceType = DeviceType.Switch;
    public static final Type cfgType = new TypeToken<Config<Boolean>>(){}.getType();

    public Switch(int id) {
	this.id = id;
        super.loadConfig(deviceType);
    }

    public Switch(int id, Boolean status, String location, String description, int[] actorIdTab, Boolean[] actorStatusTab, int gid) throws SocketException{
	super(id, status, location, description, actorIdTab, actorStatusTab, gid);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
        startResponseThread();
	Message msg = new Message();
        if (checkRespones())
            this.status = status;
        msg.setSenderId(this.id);
	msg.setMsgType(MessageType.statusChange);
	msg.setReceiverId(this.gid);
	msg.setContent(String.valueOf(this.status));
	msg.setSenderDevice(deviceType);
	this.sendMsg(msg);
        System.out.println("Switch.status: " + this.status);
    }
    
    @Override
    public void setStatus(String status) {
	boolean b = Boolean.valueOf(status);
	this.setStatus(b);
    }

    @Override
    public void setActorStatus(String status, int pos) {
	boolean b = Boolean.valueOf(status);
	this.setActorStatus(b, pos);
    }

    public static void main(String[] args) throws SocketException {
        int[] actorListId = {1, 12};
        Boolean[] actorListStatus = new Boolean[actorListId.length];
        Switch s = new Switch(20, false, "Haustür", "Klingel", actorListId, actorListStatus, 29001);
	s.saveConfig(deviceType);
    }
    
}
