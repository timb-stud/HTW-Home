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
 * @author Christian Rech, Tim Bartsch
 */
public class Switch extends AckSensor<Boolean> {

    public static final DeviceType deviceType = DeviceType.Switch;
    public static final Type cfgType = new TypeToken<Config<Boolean>>() {
    }.getType();

    public Switch(int id) {
        this.id = id;
        super.loadConfig(deviceType);
    }

    public Switch(int id, Boolean status, String location, String description, int[] actorIdTab, Boolean[] actorStatusTab, int gid) throws SocketException {
        super(id, status, location, description, actorIdTab, actorStatusTab, gid);
	saveConfig(deviceType);
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
        sendStatusResponse();
        fireChangeEvent();
        Message msg = new Message();
        if (checkRespones()) {
            this.status = status;
        }
        msg.setSenderId(this.id);
        msg.setMsgType(MessageType.statusChange);
        msg.setReceiverId(this.gid);
        msg.setContent(String.valueOf(this.status));
        msg.setSenderDevice(deviceType);
        this.sendMsg(msg);
        System.out.println("Switch.status: " + this.status);
    }

    private void sendStatusResponse() {
        Message msg = new Message();
        msg.setMsgType(MessageType.statusResponse);
        msg.setSenderId(this.id);
        msg.setContent(String.valueOf(this.statusLED));
        msg.setSenderDevice(deviceType);
        this.sendMsg(msg);
    }

    @Override
    public void setStatus(String status) {
        boolean b = Boolean.valueOf(status);
        this.setStatus(b);
    }

    @Override
    public void setActorStatus(String status, int pos) {
        boolean b = Boolean.valueOf(status);
        this.setActorStatus(b, pos);
    }

    /*
     * Hier wird die StatusLED gesetzt, also die Quittierung des Schalters
     */
    public void checkAndSetStatusLed() {
        for (int i = 0; i < actorStatusTab.length; i++) {
            if (actorStatusTab[i] == false) {
                setStatusLED(false);
                fireChangeEventLED();
                return;
            }
        }
        setStatusLED(true);
    }
    /*
     * Das Boolean Feld wird an den Stellen, an denen es den Wert null hat, auf false gesetzt
     */

    public static void setNullToFalse(Boolean[] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == null) {
                tab[i] = false;
            }
        }
    }

    /*
     * Es wird hier ein Event ausgelöst, sodass die GUI ihre Aktualiserung vornehmen kann
     */
    @Override
    protected void fireChangeEvent() {
        StatusChangeEvent<Boolean> evt = new StatusChangeEvent<Boolean>(this, this.status);
        for (StatusChangeListener l : listeners) {
            l.changeEventReceived(evt);
        }
    }

    /*
     * Es wird hier ein Event ausgelöst, sodass die GUI ihre Aktualiserung vornehmen kann (nur StatusLED vom Switch)
     */
    protected void fireChangeEventLED() {
        StatusChangeEvent<Boolean> evt = new StatusChangeEvent<Boolean>(this, this.getStatusLED());
        for (StatusChangeListener l : listeners) {
            l.changeEventReceived(evt);
        }
    }

    public boolean getStatusLED() {
        return this.statusLED;
    }

    public void setStatusLED(boolean b) {
        this.statusLED = b;
        this.status = b;
        sendStatusResponse();
        fireChangeEvent();
    }
}
