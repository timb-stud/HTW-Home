package de.htwhome.devices;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class Actor<T> extends AbstractDevice<T>{

    private int[] gID;
  
    public Actor(int id, T status,String location, String type, String hint, int[] gID) {
        super(id, status,location, type, hint);
        this.gID = gID;
    }

}
