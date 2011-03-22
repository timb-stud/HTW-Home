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
    private Sensor sensor;

    public TimeSchedulerTask(Sensor sensor, T firstStatus, T secondStatus) {
        this.sensor = sensor;
        this.firstStatus = firstStatus;
        this.secondStatus = secondStatus;
    }

    @Override
    public void run() {
        status = (T) sensor.newTimeSchedulerStatus(firstStatus, secondStatus);
        sensor.setStatus(status);
    }
}
