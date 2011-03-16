/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

/**
 *
 * @author christian
 */
public abstract class Sensor extends AbstractDevice{

    private String[] actorList;
    private int gID;

     public Sensor (int id, Object status,String location, String type, String hint, String[] aktorList, int gID) {
        super(id, status,location, type, hint);
        this.actorList = aktorList;
        this.gID = gID;
    }

}
