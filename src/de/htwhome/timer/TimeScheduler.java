/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.timer;

import de.htwhome.devices.AbstractDevice;
import de.htwhome.utils.LittleHelpers;
import java.util.Timer;

/**
 *
 * @author christian
 */
public class TimeScheduler<T> {
    private Timer timer;
    private AbstractDevice ad;

    public TimeScheduler(AbstractDevice ad) {
        this.ad = ad;
        timer = new Timer();
    }

    public void startIntervallRandom(int intervall){
        timer.schedule(new TimeSchedulerTask<T>(ad), intervall);
    }

    public void startFromTill( T firstStatus, T secondStatus,long startIn, long intervall){
        timer.schedule(new TimeSchedulerTask<T>(ad, firstStatus, secondStatus), startIn, intervall);
    }

    public void stopScheduler(){
        timer.cancel(); //Terminate the timer thread
    }
}
