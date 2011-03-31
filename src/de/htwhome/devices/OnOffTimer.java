package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import de.htwhome.timer.TimeScheduler;
import de.htwhome.timer.TimerOptions;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import java.net.SocketException;

/**
 *
 * @author christian
 */
public class OnOffTimer extends IntervalSensor<Boolean> {

    public static final DeviceType deviceType = DeviceType.OnOffTimer;
    public static final Type cfgType = new TypeToken<Config<Boolean>>() {}.getType();

    public OnOffTimer(int id) {
        this.id = id;
        super.loadConfig(deviceType);
    }

    public OnOffTimer(int id, Boolean status, String location, String description) throws SocketException {
        super(id, status, location, description);
	saveConfig(deviceType);
    }

    @Override
    public void handleMsg(String msg) {
        handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(String status) {
        boolean b = Boolean.valueOf(status);
        setStatus(b);
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
        fireChangeEvent();
        Message msg = new Message();
        msg.setMsgType(MessageType.statusChange);
        msg.setSenderId(this.id);
        msg.setContent(String.valueOf(this.status));
        msg.setSenderDevice(deviceType);
        msg.setReceiverId(22000);
        this.sendMsg(msg);
    }

    public void startNotifier(int intervall) {
        TimeScheduler<Boolean> ts = new TimeScheduler<Boolean>(this, TimerOptions.ONOFFTIMER);
        ts.startFromTill(Boolean.TRUE, Boolean.FALSE, 0, intervall);
    }

    @Override
    protected void fireChangeEvent() {
    }
}
