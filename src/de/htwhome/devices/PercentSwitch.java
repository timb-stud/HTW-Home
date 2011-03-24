package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.SocketException;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;

/**
 *
 * @author Tim Bartsch
 */
public class PercentSwitch extends Sensor<Integer>{

    public static final Type cfgType = new TypeToken<Config<Integer>>(){}.getType();
    public static final DeviceType deviceType = DeviceType.PercentSwitch;
    private int min, max; //TODO minimum und maximum festlegen

    public PercentSwitch(int id, int status, String location, String description, int[] actorListId, Integer[] actorStatusTab, int gid) throws SocketException {
        super(id, status, location, description, actorListId, actorStatusTab, gid);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
	Message msg = new Message();
	msg.setMsgType(MessageType.statusChange);
	msg.setReceiverId(this.gid);
	msg.setSenderId(this.id);
	msg.setSenderDevice(deviceType);
	msg.setContent(String.valueOf(this.status));
	this.sendMsg(msg);
    }

    @Override
    public void setStatus(String status) {
	int i = Integer.parseInt(status);
	this.setStatus(i);
    }

    @Override
    public void setActorStatus(String status, int pos) {
	int i = Integer.parseInt(status);
	this.setActorStatus(i, pos);
    }

}
