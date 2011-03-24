/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

import java.util.TimerTask;

/**
 *
 * @author christian
 */
class TimeSchedulerTask<T> extends TimerTask{
    private T status;
    private T firstStatus;
    private T secondStatus;
    private IntervalSensor sensor;

    public TimeSchedulerTask(IntervalSensor sensor, T firstStatus, T secondStatus) {
        this.sensor = sensor;
        this.firstStatus = firstStatus;
        this.secondStatus = secondStatus;
    }

    public TimeSchedulerTask(IntervalSensor sensor){
        this.sensor = sensor;
    }

    @Override
    public void run() {
        if (firstStatus != null){
            status = (T) sensor.newTimeSchedulerStatus(firstStatus, secondStatus);
            sensor.stopScheduler(); //TODO info: wird nun noch einem durchlauf beendet
        } else
            status = (T) sensor.newTimeSchedulerStatus();
        sensor.setStatus(status);
    }
}
