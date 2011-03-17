package de.htwhome.devices;

import de.htwhome.transmission.Message;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class Sensor<T> extends AbstractDevice<T>{

    protected int[] actorIdTab;
    protected T[] actorStatusTab;
    protected boolean[] actorAckTab; //TODO init
    protected int gid;

     public Sensor (int id, T status, String location, String type, String description, int[] actorIdTab, T[] actorStatusTab, int gid) throws SocketException {
        super(id, status,location, type, description);
        this.actorIdTab = actorIdTab;
        this.actorStatusTab = actorStatusTab;
        this.gid = gid;
    }

    @Override
    public void handleMsg(String jsonMsg, Type msgType){
	Message<T> msg = gson.fromJson(jsonMsg, msgType);
	switch (msg.getMsgType()) {
	    case statusRequest:
		//TODO implement
		break;
	    case statusResponse:
		for (int i = 0; i < actorIdTab.length; i++) {
		    if (actorIdTab[i] == msg.getSenderId()) {
			actorStatusTab[i] = msg.getStatus();
		    }
		}
		break;
	    case configChange:
		//TODO implement
		break;
	    case configRequest:
		//TODO implement
		break;
	}
    }

}
