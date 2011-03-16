package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.utils.ActionEnum;
import java.lang.reflect.Type;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Switch extends Sensor<Boolean> {

    private static Type actionMsgType = new TypeToken<ActionMessage<Boolean>>(){}.getType();

    public Switch (int id, boolean status, String location, String type, String hint, int[] actorListId, Boolean[] actorListStatus, int gID) {
        super(id, status, location, type, hint, actorListId, actorListStatus, gID);
    }

    @Override
    public void setStatus(Boolean status) {
	this.status = status;
	ActionMessage<Boolean> actionMsg = new ActionMessage<Boolean>(this.gid, ActionEnum.changeStatus, this.status);
	this.sendMsg(actionMsg, Switch.actionMsgType);
    }

    public static void main(String[] args) {
        int[] actorListId = {1,2};
        Boolean[] actorListStatus = {true, false};
        Switch s = new Switch(10, true, "haus", "schalter", "hintt", actorListId, actorListStatus, 12);
        s.setStatus(false);
    }
}
