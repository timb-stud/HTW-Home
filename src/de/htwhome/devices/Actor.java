package de.htwhome.devices;
import de.htwhome.transmission.Message;
import de.htwhome.utils.ActorConfig;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class Actor<T> extends AbstractDevice<T>{

    private int[] gidTab;
  
    public Actor(int id, T status, String location, String type, String hint, int[] gidTab) throws SocketException {
        super(id, status,location, type, hint);
        this.gidTab = gidTab;
    }

    public void save(){
        ActorConfig ac = new ActorConfig();
        ac.setId(id);
        ac.setStatus(status);
        ac.setLocation(location);
        ac.setType(type);
        ac.setDescription(description); //TODO alle Attribute


        Light.setConfig(ac);
    }

    public void load(){
        ActorConfig sc = (ActorConfig) super.getConfig();
        this.id = sc.getId(); //TODO alle Attribute
    }

    private boolean isReceiver(int id) {
	for (int i = 0; i < this.gidTab.length; i++) {
	    if (this.gidTab[i] == id) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public void handleMsg(String jsonMsg, Type msgType){
	Message<T> msg = gson.fromJson(jsonMsg, msgType);

	switch (msg.getMsgType()) {
	    case statusChange:
		if(isReceiver(id)){
		    setStatus(msg.getStatus());
		}
		break;
	    case statusRequest:
		if(isReceiver(id)){
		    setStatus(this.status);
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
