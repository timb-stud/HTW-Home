/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

/**
 *
 * @author christian
 */
public abstract class Actor extends AbstractDevice{

    private int[] gID;
  
    public Actor(int id, Object status,String location, String type, String hint, int[] gID) {
        super(id, status,location, type, hint);
        this.gID = gID;
    }


}
