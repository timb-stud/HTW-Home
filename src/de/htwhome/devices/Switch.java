package de.htwhome.devices;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Switch extends Sensor<Boolean> {

    public Switch (int id, boolean status,String location, String type, String hint, int[] actorListId, Boolean[] actorListStatus, int gID) {
        super(id, status, location, type, hint, actorListId, actorListStatus, gID);
    }

    public boolean getStatus() {
        return (Boolean) this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
