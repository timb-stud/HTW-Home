/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.utils;

import de.htwhome.devices.AbstractDevice;
import java.util.ArrayList;

/**
 *
 * @author christian
 */
public class PanelConfig extends DeviceConfig{

    private ArrayList<AbstractDevice> deviceList;

    public PanelConfig() {
    }

    public ArrayList<AbstractDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(ArrayList<AbstractDevice> deviceList) {
        this.deviceList = deviceList;
    }
}
