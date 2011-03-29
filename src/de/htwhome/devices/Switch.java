package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.gui.StatusChangeEvent;
import de.htwhome.gui.StatusChangeListener;
import de.htwhome.gui.SwitchFrame;
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

    public boolean statusLED;
    public static final DeviceType deviceType = DeviceType.Switch;
    public static final Type cfgType = new TypeToken<Config<Boolean>>() {
    }.getType();

    public Switch(int id) {
        this.id = id;
        super.loadConfig(deviceType);
    }

    public Switch(int id, Boolean status, String location, String description, int[] actorIdTab, Boolean[] actorStatusTab, int gid) throws SocketException {
        super(id, status, location, description, actorIdTab, actorStatusTab, gid);
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
        fireChangeEvent();
        startResponseThread();
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

    @Override
    protected void fireChangeEvent() {
        StatusChangeEvent<Boolean> evt = new StatusChangeEvent<Boolean>(this, this.status);
        for (StatusChangeListener l : listeners) {
            l.changeEventReceived(evt);
        }
    }

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
        fireChangeEvent();
    }

    public static void main(String[] args) throws SocketException {
//        int[] actorListId = {12};
//        Boolean[] actorListStatus = new Boolean[actorListId.length];
//        Switch s = new Switch(20, false, "Haustür", "Klingel", actorListId, actorListStatus, 1);
//	  s.saveConfig(deviceType);
        int[] actorListId = {12};
        Boolean[] actorListStatus = new Boolean[actorListId.length];
        SwitchFrame sf = new SwitchFrame(20, false, "Haustür", "Klingel", actorListId, actorListStatus, 1);
        sf.setVisible(true);
    }
}
