package de.htwhome.utils;

/**
 *
 * @author christian
 */
public class DeviceConfig<T> {
    private int id;
    private T status;
    private String location;

    public DeviceConfig() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public T getStatus() {
        return status;
    }

    public void setStatus(T status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private String type;
    private String description;
}
