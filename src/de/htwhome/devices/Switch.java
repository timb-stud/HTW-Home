/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

/**
 *
 * @author christian
 */
public class Switch extends Sensor<Boolean> {

    public Switch (int id, boolean status,String location, String type, String hint, String[] aktorList, int gID) {
        super(id, status, location, type, hint, aktorList, gID);
    }

    public boolean getStatus() {
        return (Boolean) this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
