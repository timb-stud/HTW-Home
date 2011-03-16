package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.utils.ActionEnum;
import java.lang.reflect.Type;

/**
 *
 * @author Tim Bartsch
 */
public class PercentSwitch extends Sensor<Integer>{

    private static Type actionMsgType = new TypeToken<ActionMessage<Integer>>(){}.getType();

    public PercentSwitch(int id, int status, String location, String type, String hint, int[] actorListId, Integer[] actorListStatus, int gID) {
        super(id, status, location, type, hint, actorListId, actorListStatus, gID);
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
	ActionMessage<Integer> actionMsg = new ActionMessage<Integer>(this.gid, ActionEnum.changeStatus, this.status);
	this.sendMsg(actionMsg, PercentSwitch.actionMsgType);
    }

}
