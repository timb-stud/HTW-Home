/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageSender;
import de.htwhome.transmission.MessageType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

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
