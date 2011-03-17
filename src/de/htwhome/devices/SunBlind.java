package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class SunBlind extends Actor<Integer>{

    public static final Type ackMsgType = new TypeToken<AckMessage<Integer>>(){}.getType();
    public static final Type actionMsgType = new TypeToken<ActionMessage<Integer>>(){}.getType();

    public SunBlind(int id, int status, String location, String type, String description, int[] gidTab) throws SocketException {
        super(id, status, location, type, description, gidTab);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, actionMsgType);
    }

    @Override
    public void setStatus(Integer status) {
	this.status = status;
	AckMessage<Integer> ackMsg = new AckMessage<Integer>(this.id, this.status);
	sendMsg(ackMsg, SunBlind.ackMsgType);
    }
    
}
