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
 * Implementierung einer Wetterstation
 * @author Tobias Lana
 * Anemometer (en) = Windmesser (de)
 */
public class Anemometer extends IntervalSensor<Double> {

    public static final DeviceType deviceType = DeviceType.Anemometer;
    public static final Type cfgType = new TypeToken<Config<Double>>() {
    }.getType();
    private static final double MAXLEVELWARNING = 9.0; //TODO Wert muss aus Konfig gelesen werden

    public Anemometer(int id) {
        this.id = id;
        super.loadConfig(deviceType);
    }

    public Anemometer(int id, Double status, String location, String description, int gid) throws SocketException {
        super(id, status, location, description);
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Double status) {
        this.status = status;
        fireChangeEvent();
        if (this.status > MAXLEVELWARNING) {
            System.out.println("!! ACHTUNG !!");
            Message warning = new Message();
            warning.setMsgType(MessageType.weatherAlarm);
            warning.setSenderId(this.id);
            warning.setReceiverId(ALLDEVICES);
            warning.setContent(String.valueOf(this.status));
            warning.setSenderDevice(deviceType);
            this.sendMsg(warning);
        }
        Message msg = new Message();
        msg.setMsgType(MessageType.statusResponse);
        msg.setSenderId(this.id);
        msg.setContent(String.valueOf(this.status));
        msg.setSenderDevice(deviceType);
        this.sendMsg(msg);
        System.out.println("Neue Windgeschwindigkeit: " + this.status); //TODO sout entfernen
    }

    @Override
    public void setStatus(String status) {
        double d = Double.valueOf(status);
        this.setStatus(d);
    }

    public void startNotifier(int intervall) {
        TimeScheduler<Double> ts = new TimeScheduler<Double>(this, TimerOptions.ANEMOMETER);
        ts.startIntervallRandom(intervall);
    }

    protected void fireChangeEvent() {
        StatusChangeEvent<Double> evt = new StatusChangeEvent<Double>(this, this.status);
        for (StatusChangeListener l : listeners) {
            l.changeEventReceived(evt);
        }
    }
}
