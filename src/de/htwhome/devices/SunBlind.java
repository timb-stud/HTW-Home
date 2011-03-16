package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class SunBlind extends Actor<Integer>{

    private static Type ackMsgType = new TypeToken<AckMessage<Integer>>(){}.getType();

    public SunBlind(int id, int status, String location, String type, String description, int[] gID) {
        super(id, status, location, type, description, gID);
    }

    @Override
    public void setStatus(Integer status) {
	this.status = status;
	AckMessage<Integer> ackMsg = new AckMessage<Integer>(this.id, this.status);
	sendMsg(ackMsg, SunBlind.ackMsgType);
    }
    
}
