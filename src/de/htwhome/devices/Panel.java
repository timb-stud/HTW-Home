/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Volkan Gökkaya, Tobias Lana
 */
public class Panel<T> extends AbstractDevice<T>{

    public Panel(int id, T status, String location, String type, String hint, int[] gidTab) throws SocketException{
        super(id, status,location, type, hint);
    }

    @Override
    public void handleMsg(String msg, Type msgType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void handleMsg(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setStatus(T status) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
