package de.htwhome.timer;

import de.htwhome.devices.AbstractDevice;
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
    private TimerOptions option;
    private AbstractDevice device;
    private boolean timeSchedulerChangeStatus = false;

    public TimeSchedulerTask(AbstractDevice device, TimerOptions option, T firstStatus, T secondStatus) {
        this (device, option);
        this.firstStatus = firstStatus;
        this.secondStatus = secondStatus;
    }

    public TimeSchedulerTask(AbstractDevice device, TimerOptions option){
        this.device = device;
        this.option = option;
    }


    private T newTimeSchedulerStatus(){
       timeSchedulerChangeStatus = (timeSchedulerChangeStatus) ? false : true;
       if (timeSchedulerChangeStatus)
            return secondStatus;
        else
            return firstStatus;
    }

    @Override
    public void run() {
        Double testwert = 0.0;
        switch (option){
            case ANEMOMETER:
                status = (T) LittleHelpers.randomMeasurement();
                break;
            case SMOKEDETECTOR:
                testwert = LittleHelpers.randomMeasurement();
                if (testwert > 9.0)
                    status = (T) Boolean.TRUE;
                else
                    status = (T) Boolean.FALSE;
                break;
            case ONOFFTIMER:
                status = newTimeSchedulerStatus();
                break;
            case THERMOMETER:
                testwert = LittleHelpers.randomMeasurement() * 3;
                status = (T) testwert;
                break;
//            default:
//                status = (T) newTimeSchedulerStatus();
//                break;
        }
        device.setStatus(status);
    }
}
