package de.htwhome.devices;

import de.htwhome.utils.ActionEnum;

/**
 *
 * @author Tim Bartsch
 */
public class ActionMessage<T> {

    private int gid;
    private ActionEnum action;
    private T status;

    public ActionMessage() {
    }

    public ActionMessage(int gid, ActionEnum action, T status) {
        this.gid = gid;
        this.action = action;
        this.status = status;
    }
    
    public ActionEnum getAction() {
        return action;
    }

    public void setAction(ActionEnum action) {
        this.action = action;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public T getStatus() {
        return status;
    }

    public void setStatus(T status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ActionMessage{" + "gid=" + gid + "action=" + action + "status=" + status + '}';
    }
}
