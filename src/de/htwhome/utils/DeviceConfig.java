package de.htwhome.utils;

import de.htwhome.devices.DeviceType;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class DeviceConfig<T> {
    private int id;
    private T status;
    private String location;
    private DeviceType deviceType;
    private String description;

    public DeviceConfig() {
    }

    public DeviceType getDeviceType() {
	return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
	this.deviceType = deviceType;
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
}
