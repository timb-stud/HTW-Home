/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

/**
 *
 * @author christian
 */
public abstract class AbstractDevice<T> {
    private int id;

    protected  T status;
    private String location;
    private String type; //koennte auch als Enum realisiert werden
    private String hint;

    public AbstractDevice(int id, T status,String location, String type, String hint) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.type = type;
        this.hint = hint;
    }

//    public abstract  Object getStatus();
//    public abstract  void setStatus(Object status);
    

}
