/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.utils;

/**
 *
 * @author christian
 */
public class SensorConfig<T> extends DeviceConfig<T> {
    int[] actorIDTab;
    T[] actorStatusTab;
    boolean[] actorAckTab;

    public SensorConfig() {
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

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }
    int gid;
}
