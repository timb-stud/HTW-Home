package de.htwhome.devices;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class AbstractDevice<T> {
    private int id;

    protected  T status;
    private String location;
    private String type; //koennte auch als Enum realisiert werden
    private String description;

    public AbstractDevice(int id, T status,String location, String type, String description) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.type = type;
        this.description = description;
    }

}
