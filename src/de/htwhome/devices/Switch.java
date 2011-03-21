package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Switch extends Sensor<Boolean> {

    public static final Type msgType = new TypeToken<Message<Boolean>>(){}.getType();
    public static final DeviceType deviceType = DeviceType.Switch;

    public Switch() {
        super.load();
    }

    public Switch (int id, boolean status, String location, String description, int[] actorIdTab, Boolean[] actorStatusTab, int gid) throws SocketException {
        super(id, status, location, description, actorIdTab, actorStatusTab, gid);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, Switch.msgType);
    }

    @Override
    public void setStatus(Boolean status) {
        if (checkRespones())
            this.status = status;
	Message<Boolean> msg = new Message<Boolean>();
	msg.setMsgType(MessageType.statusChange);
	msg.setReceiverId(this.gid);
	msg.setStatus(this.status);
	this.sendMsg(msg, Switch.msgType);
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
