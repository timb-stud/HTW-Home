package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.utils.ActionEnum;
import java.lang.reflect.Type;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Switch extends Sensor<Boolean> {

    public static final Type actionMsgType = new TypeToken<ActionMessage<Boolean>>(){}.getType();
    public static final Type ackMsgType = new TypeToken<AckMessage<Boolean>>(){}.getType();

    public Switch (int id, boolean status, String location, String type, String description, int[] actorIdTab, Boolean[] actorStatusTab, int gid) {
        super(id, status, location, type, description, actorIdTab, actorStatusTab, gid);
    }

    @Override
    public void setStatus(Boolean status) {
	this.status = status;
	ActionMessage<Boolean> actionMsg = new ActionMessage<Boolean>(this.gid, ActionEnum.changeStatus, this.status);
	this.sendMsg(actionMsg, Switch.actionMsgType);
        System.out.println("Switch.status: " + this.status);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, ackMsgType);
    }

    public static void main(String[] args) {
        int[] actorListId = {10};
        Boolean[] actorListStatus = {false};
        Switch s = new Switch(33, false, "haus", "schalter", "hintt", actorListId, actorListStatus, 12);
        s.setStatus(true);
    }
}
