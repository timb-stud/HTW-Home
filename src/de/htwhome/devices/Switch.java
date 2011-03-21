package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.ActorConfig;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Switch extends Sensor<Boolean> {

    public static final DeviceType deviceType = DeviceType.Switch;
    public static final Type cfgType = new TypeToken<ActorConfig<Boolean>>(){}.getType();

    public Switch() {
        super.load();
    }

    public Switch (int id, boolean status, String location, String description, int[] actorIdTab, Boolean[] actorStatusTab, int gid) throws SocketException {
        super(id, status, location, description, actorIdTab, actorStatusTab, gid);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Boolean status) {
	this.status = status;
	Message msg = new Message();
	msg.setMsgType(MessageType.statusChange);
	msg.setReceiverId(this.gid);
	msg.setContent(String.valueOf(this.status));
	msg.setSenderDevice(deviceType);
	this.sendMsg(msg);
        System.out.println("Switch.status: " + this.status);
    }

    public static void main(String[] args) throws SocketException {
        int[] actorListId = {10};
        Boolean[] actorListStatus = {false};
        Switch s = new Switch(33, false, "haus", "hintt", actorListId, actorListStatus, 1);
        s.save();
        s.setStatus(true);
    }
}
