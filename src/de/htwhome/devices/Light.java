package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.ActorConfig;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Light extends Actor<Boolean> {

    public static final Type msgType = new TypeToken<Message<Boolean>>(){}.getType();

    public Light(int id, boolean status, String location, String type, String description, int[] gidTab) throws SocketException {
        super(id, status, location, type, description, gidTab);
    }

    public Light() {
        super.load();
    }

    @Override
    public void setStatus(Boolean status) {
	this.status = status;
	Message<Boolean> msg = new Message<Boolean>();
	msg.setMsgType(MessageType.statusResponse);
	msg.setSenderId(this.id);
	msg.setStatus(this.status);
	this.sendMsg(msg, Light.msgType);
        System.out.println("Light.status:" +  this.status);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, msgType);
    }

    public static void main(String[] args) throws SocketException {
        int[] gid  = {1};
	Light l = new Light(10, false, "haus", "lampe1", "Beschreibung", gid);
//        Light l = new Light();
//	l.handleMsg("{'gid': '1', 'status': 'false', 'action': 'changeStatus'}");


//        l.save();
//        ActorConfig sc2 = (ActorConfig) l.getConfig();
//        System.out.println("sc2 id= " + sc2.getId()
//                  + "\n" + "status= " + sc2.getStatus()
//                );
    }
}
