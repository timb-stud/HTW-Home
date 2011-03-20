/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

import de.htwhome.Main;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author christian
 */
public class TimeScheduler {
    private Timer timer;

    public TimeScheduler(int[] gid, long from, long till) {
        timer = new Timer();
        long start = from * 1000;  //TODO Berechnung
        long duration = till * 1000;
        timer.schedule(new TimeSchedulerTask(999), start, duration);
    }
    
    class TimeSchedulerTask extends TimerTask{
        private boolean status = false;
        private int id;

        public TimeSchedulerTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            status = (status) ? false : true;
            System.out.format("ID = " + id + " Status = " + status + "%n");
//            timer.cancel(); //Terminate the timer thread
        }
        
    }

     public static void main(String args[]) {
        int[] gid = {1,2};
        new TimeScheduler(gid, 2, 5);
        System.out.format("Task scheduled.%n");
    }
}
