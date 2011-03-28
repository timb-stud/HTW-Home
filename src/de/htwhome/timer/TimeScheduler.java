/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.timer;

import de.htwhome.devices.AbstractDevice;
import de.htwhome.timer.TimerOptions;
import java.util.Timer;

/**
 *
 * @author christian
 */
public class TimeScheduler<T> {
    private Timer timer;
    private AbstractDevice ad;
    private TimerOptions to;

    public TimeScheduler(AbstractDevice ad, TimerOptions to) {
        this.ad = ad;
        this.to = to;
        timer = new Timer();
    }

    public void startIntervallRandom(int intervall){
        timer.schedule(new TimeSchedulerTask<T>(ad, to), 0 ,intervall);
    }

    public void startFromTill( T firstStatus, T secondStatus,long startIn, long intervall){
        timer.schedule(new TimeSchedulerTask<T>(ad, to, firstStatus, secondStatus), startIn, intervall);
    }

    public void stopScheduler(){
        timer.cancel(); //Terminate the timer thread
    }
}
