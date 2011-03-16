package de.htwhome.devices;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Light extends Actor<Boolean> {

    public Light(int id, boolean status, String location, String type, String hint, int[] gID) {
        super(id, status, location, type, hint, gID);
    }
}
