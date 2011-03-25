/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.utils;

import de.htwhome.devices.ConfigList;
import de.htwhome.devices.DeviceType;

/**
 *
 * @author christian
 */
public class Config<T> { //TODO wenn nicht gebraucht loeschen
    //Device
    private int id;
    private T status;
    private String location;
    private DeviceType deviceType;
    private String description;

    //Actor
    int [] gidTab;

    //Sensor
    int[] actorIDTab;
    T[] actorStatusTab;
    boolean[] actorAckTab; //TODO vllt rauswerfen?

    //Panel
    private ConfigList configList;

    public Config() {
    }

    public ConfigList getConfigList() {
        return configList;
    }

    public void setConfigList(ConfigList configList) {
        this.configList = configList;
    }

    public boolean[] getActorAckTab() {
        return actorAckTab;
    }

    public void setActorAckTab(boolean[] actorAckTab) {
        this.actorAckTab = actorAckTab;
    }

    public int[] getActorIDTab() {
        return actorIDTab;
    }

    public void setActorIDTab(int[] actorIDTab) {
        this.actorIDTab = actorIDTab;
    }

    public T[] getActorStatusTab() {
        return actorStatusTab;
    }

    public void setActorStatusTab(T[] actorStatusTab) {
        this.actorStatusTab = actorStatusTab;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public int[] getGidTab() {
        return gidTab;
    }

    public void setGidTab(int[] gidTab) {
        this.gidTab = gidTab;
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

    @Override
    public boolean equals(Object obj) {
	if(obj instanceof Config){
	    Config c = (Config)obj;
	    return c.getId() == this.getId();
	}
	return false;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 83 * hash + this.id;
	return hash;
    }

    @Override
    public String toString() {
	return "Config{" + "id=" + id + "status=" + status + "location=" + location + "deviceType=" + deviceType + "description=" + description + "gidTab=" + gidTab + "actorIDTab=" + actorIDTab + "actorStatusTab=" + actorStatusTab + "actorAckTab=" + actorAckTab + "configList=" + configList + '}';
    }
    
}
