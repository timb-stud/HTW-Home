/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

/**
 *
 * @author christian
 */
public class Light extends Actor<Boolean> {

    

    public Light(int id, boolean status, String location, String type, String hint, int[] gID) {
        super(id, status, location, type, hint, gID);
    }

    public boolean getStatus() {
        return (Boolean) this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static void main(String[] args){
        int [] i = {1,2,3};
        Light l = new Light(1, true, "sad", "asd", "asd", i);
        System.out.println(l.getStatus());
    }


}
