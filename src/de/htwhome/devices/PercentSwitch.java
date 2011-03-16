package de.htwhome.devices;

/**
 *
 * @author Tim Bartsch
 */
public class PercentSwitch extends Sensor<Integer>{

    public PercentSwitch(int id, int status, String location, String type, String hint, int[] actorListId, Integer[] actorListStatus, int gID) {
        super(id, status, location, type, hint, actorListId, actorListStatus, gID);
    }

}
