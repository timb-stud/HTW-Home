/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.utils;

/**
 *
 * @author christian
 */
public class ActorConfig<T> extends DeviceConfig<T>{
    int [] gidTab;

    public ActorConfig() {
    }

    public int[] getGidTab() {
        return gidTab;
    }

    public void setGidTab(int[] gidTab) {
        this.gidTab = gidTab;
    }
}
