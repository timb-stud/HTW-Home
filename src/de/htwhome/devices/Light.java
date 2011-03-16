package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Light extends Actor<Boolean> {

    private static Type ackMsgType = new TypeToken<AckMessage<Boolean>>(){}.getType();

    public Light(int id, boolean status, String location, String type, String hint, int[] gID) {
        super(id, status, location, type, hint, gID);
    }

    @Override
    public void setStatus(Boolean status) {
	this.status = status;
	AckMessage<Boolean> ackMsg = new AckMessage<Boolean>(this.id, this.status);
	this.sendMsg(ackMsg, Light.ackMsgType);
    }

    public static void main(String[] args) {
	int[] gid  = {1, 2};
	Light l = new Light(10, false, "haus", "lampe", "bam", gid);
	l.setStatus(true);
    }
}
