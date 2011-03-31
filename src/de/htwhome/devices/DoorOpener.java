package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.gui.StatusChangeEvent;
import de.htwhome.gui.StatusChangeListener;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tobiaslana
 */
public class DoorOpener extends Actor<Boolean> {

    public static final DeviceType deviceType = DeviceType.DoorOpener;
    public static final Type cfgType = new TypeToken<Config<Boolean>>() {}.getType();

    public DoorOpener(int id, boolean status, String location, String description, int[] gidTab) throws SocketException {
        super(id, status, location, description, gidTab);
	saveConfig(deviceType);
    }

    public DoorOpener(int id) {
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
        if (status) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DoorOpener.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.status = false;
            fireChangeEvent();
            msg.setContent(String.valueOf(this.status));
        }
        this.sendMsg(msg);
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
    protected void fireChangeEvent() {
        StatusChangeEvent<Boolean> evt = new StatusChangeEvent<Boolean>(this, this.status);
        for (StatusChangeListener l : listeners) {
            l.changeEventReceived(evt);
        }
    }
}
