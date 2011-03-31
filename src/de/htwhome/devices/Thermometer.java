package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.gui.StatusChangeEvent;
import de.htwhome.gui.StatusChangeListener;
import de.htwhome.timer.TimeScheduler;
import de.htwhome.timer.TimerOptions;
import java.lang.reflect.Type;
import java.net.SocketException;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;

/**
 *
 * @author tobiaslana
 */
public class Thermometer extends IntervalSensor<Double> {

    public static final DeviceType deviceType = DeviceType.Thermometer;
    public static final Type cfgType = new TypeToken<Config<Double>>() {
    }.getType();

    public Thermometer(int id) {
        this.id = id;
        super.loadConfig(deviceType);
    }

    public Thermometer(int id, Double status, String location, String description) throws SocketException {
        super(id, status, location, description);
	saveConfig(deviceType);
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Double status) {
        this.status = status;
        fireChangeEvent();
        Message msg = new Message();
        msg.setMsgType(MessageType.statusResponse);
        msg.setSenderId(this.id);
        msg.setContent(String.valueOf(this.status));
        msg.setSenderDevice(deviceType);
        this.sendMsg(msg);
    }

    @Override
    public void setStatus(String status) {
        double d = Double.valueOf(status);
        this.setStatus(d);
    }

    public void startNotifier(int intervall) {
        TimeScheduler<Double> ts = new TimeScheduler<Double>(this, TimerOptions.THERMOMETER);
        ts.startIntervallRandom(intervall);
    }

    @Override
    protected void fireChangeEvent() {
        StatusChangeEvent<Double> evt = new StatusChangeEvent<Double>(this, this.status);
        for (StatusChangeListener l : listeners) {
            l.changeEventReceived(evt);
        }
    }
}
