package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.gui.StatusChangeEvent;
import de.htwhome.gui.StatusChangeListener;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Tobias Lana
 * Shutter (en) = Rollladen (de)
 */
public class Shutter extends Actor<Integer> {

    public static final Type cfgType = new TypeToken<Config<Integer>>() {}.getType();
    public static final DeviceType deviceType = DeviceType.Shutter;
    private static final int OPEN_STATUS = 0;
    private static final int SAFETY_STATUS = 66;
    private static final int CLOSE_STATUS = 100;

    public Shutter(int id, int status, String location, String description, int[] gidTab) throws SocketException {
        super(id, status, location, description, gidTab);
	saveConfig(deviceType);
    }

    public Shutter(int id) {
        this.id = id;
        loadConfig(deviceType);
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void handleWeatherAlarm() {
        setStatus(SAFETY_STATUS);
    }

    @Override
    public void handleFireAlarm() {
        setStatus(OPEN_STATUS);
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
        fireChangeEvent();
        Message msg = new Message();
        msg.setMsgType(MessageType.statusResponse);
        msg.setSenderId(this.id);
        msg.setSenderDevice(deviceType);
        msg.setContent(String.valueOf(this.status));
        sendMsg(msg);
    }

    @Override
    public void setStatus(String status) {
        int i = Integer.valueOf(status);
        this.setStatus(i);
    }

    @Override
    protected void fireChangeEvent() {
        StatusChangeEvent<Integer> evt = new StatusChangeEvent<Integer>(this, this.status);
        for (StatusChangeListener l : listeners) {
            l.changeEventReceived(evt);
        }
    }
}
