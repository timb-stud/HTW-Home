package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.gui.LightFrame;
import de.htwhome.gui.StatusChangeEvent;
import de.htwhome.gui.StatusChangeListener;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Light extends Actor<Boolean> {

    public static final DeviceType deviceType = DeviceType.Light;
    public static final Type cfgType = new TypeToken<Config<Boolean>>() {
    }.getType();

    public Light(int id, boolean status, String location, String description, int[] gidTab) throws SocketException {
        super(id, status, location, description, gidTab);
    }

    public Light(int id) {
	this.id = id;
        super.loadConfig(deviceType);
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
        fireChangeEvent();
        Message msg = new Message();
        msg.setMsgType(MessageType.statusResponse);
        msg.setSenderId(this.id);
        msg.setContent(String.valueOf(this.status));
        msg.setSenderDevice(deviceType);
        this.sendMsg(msg);
        System.out.println("Light.status:" + this.status);
    }

    @Override
    public void setStatus(String status) {
        boolean b = Boolean.valueOf(status);
        this.setStatus(b);
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void handleFireAlarm() {
        setStatus(true);
    }

    protected void fireChangeEvent() {
        StatusChangeEvent<Boolean> evt = new StatusChangeEvent<Boolean>(this, this.status);
        for (StatusChangeListener l : listeners) {
            l.changeEventReceived(evt);
        }
    }

    public static void main(String[] args) throws SocketException {
//        int[] gid  = {1};
//	Light l = new Light(12, false, "haus", "Beschreibung", gid);
        LightFrame lf = new LightFrame();
        lf.setVisible(true);
    }
}
