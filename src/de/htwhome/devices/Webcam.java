package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class Webcam extends Actor<Boolean> {

    public static final DeviceType deviceType = DeviceType.Webcam;
    public static final Type cfgType = new TypeToken<Config<Boolean>>() {}.getType();

    public Webcam(int id, boolean status, String location, String description, int[] gidTab) throws SocketException {
        super(id, status, location, description, gidTab);
	saveConfig(deviceType);
    }

    public Webcam(int id) {
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
            this.status = false;
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
    }
}
