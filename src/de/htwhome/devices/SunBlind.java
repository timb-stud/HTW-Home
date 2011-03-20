package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class SunBlind extends Actor<Integer>{

    public static final Type msgType = new TypeToken<Message<Integer>>(){}.getType();
    private int min, max; //TODO minimum und maximum festlegen

    public SunBlind(int id, int status, String location, String type, String description, int[] gidTab) throws SocketException {
        super(id, status, location, type, description, gidTab);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, SunBlind.msgType);
    }

    @Override
    public void setStatus(Integer status) {
	this.status = status;
	Message<Integer> msg = new Message<Integer>();
	msg.setMsgType(MessageType.statusResponse);
	msg.setSenderId(this.id);
	msg.setStatus(this.status);
	sendMsg(msg, SunBlind.msgType);
    }
    
}
