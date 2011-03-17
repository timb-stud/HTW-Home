package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.utils.ActorConfig;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class Light extends Actor<Boolean> {

    public static final Type ackMsgType = new TypeToken<AckMessage<Boolean>>(){}.getType();
    public static final Type actionMsgType = new TypeToken<ActionMessage<Boolean>>(){}.getType();

    public Light(int id, boolean status, String location, String type, String description, int[] gidTab) throws SocketException {
        super(id, status, location, type, description, gidTab);
    }

    @Override
    public void setStatus(Boolean status) {
        System.out.println("Light -> SetStatus");
	this.status = status;
	AckMessage<Boolean> ackMsg = new AckMessage<Boolean>(this.id, this.status);
	this.sendMsg(ackMsg, Light.ackMsgType);
        System.out.println("Light.status:" +  this.status);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, actionMsgType);
    }

    public static void main(String[] args) throws SocketException {
        int[] gid  = {1};
	Light l = new Light(10, false, "haus", "lampe1", "Beschreibung", gid);
	//l.handleMsg("{'gid': '1', 'status': 'false', 'action': 'changeStatus'}");


        l.save();
        ActorConfig sc2 = (ActorConfig) l.getConfig();
        System.out.println("sc2 id= " + sc2.getId()
                  + "\n" + "status= " + sc2.getStatus()
                );
    }
}
