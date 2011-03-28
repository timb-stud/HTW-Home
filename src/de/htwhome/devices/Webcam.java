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
    public static final Type cfgType = new TypeToken<Config<Boolean>>(){}.getType();

    public Webcam(int id, boolean status, String location, String description, int[] gidTab) throws SocketException {
        super(id, status, location, description, gidTab);
    }

    public Webcam() {
        super.load();
    }

    @Override
    public void setStatus(Boolean status) {
	this.status = status;
	Message msg = new Message();
	msg.setMsgType(MessageType.statusResponse);
	msg.setSenderId(this.id);
	msg.setContent(String.valueOf(this.status));
	msg.setSenderDevice(deviceType);
	this.sendMsg(msg);
//        System.out.println("Neues Bild erstellt. Status: " + this.status);
        if (status) {
            this.status = false;
            msg.setContent(String.valueOf(this.status));
        }
        this.sendMsg(msg);
//        System.out.println("Webcam wieder im Standby. Status: " + this.status);
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

    public static void main(String[] args) throws SocketException {
        int[] gid  = {29001};
	Webcam w = new Webcam(12101, false, "Eingangstür", "Webcam", gid);
        w.save();
    }

    @Override
    protected void fireChangeEvent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
