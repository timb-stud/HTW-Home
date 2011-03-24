/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.timer;

import de.htwhome.devices.AbstractDevice;
import de.htwhome.devices.Sensor;
import de.htwhome.utils.LittleHelpers;
import java.util.TimerTask;

/**
 *
 * @author christian
 */
class TimeSchedulerTask<T> extends TimerTask{
    private T status;
    private T firstStatus;
    private T secondStatus;
    private AbstractDevice device;
    private boolean timeSchedulerChangeStatus = false;

    public TimeSchedulerTask(AbstractDevice device, T firstStatus, T secondStatus) {
        this.device = device;
        this.firstStatus = firstStatus;
        this.secondStatus = secondStatus;
    }

    public TimeSchedulerTask(AbstractDevice device){
        this.device = device;
    }


    private T newTimeSchedulerStatus(){
       timeSchedulerChangeStatus = (timeSchedulerChangeStatus) ? true : false;
       if (timeSchedulerChangeStatus)
            return secondStatus;
        else
            return firstStatus;
    }

    @Override
    public void run() {
        if (firstStatus != null){
            status = (T) newTimeSchedulerStatus();
//            device.stopScheduler(); //TODO info: wird nun noch einem durchlauf beendet
        } else
            status = (T) LittleHelpers.randomMeasurement();
        device.setStatus(status);
    }
}
