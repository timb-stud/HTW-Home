package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.SocketException;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;

/**
 *
 * @author Tim Bartsch
 */
public class PercentSwitch extends Sensor<Integer>{

    public static final Type msgType = new TypeToken<Message<Integer>>(){}.getType();
    private int min, max; //TODO minimum und maximum festlegen

    public PercentSwitch(int id, int status, String location, String type, String description, int[] actorListId, Integer[] actorStatusTab, int gid) throws SocketException {
        super(id, status, location, type, description, actorListId, actorStatusTab, gid);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, PercentSwitch.msgType);
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
	Message<Integer> msg = new Message<Integer>();
	msg.setMsgType(MessageType.statusChange);
	msg.setReceiverId(this.gid);
	msg.setStatus(this.status);
	this.sendMsg(msg, PercentSwitch.msgType);
    }

}
