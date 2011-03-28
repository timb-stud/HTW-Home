package de.htwhome.devices;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Tim Bartsch
 */
public abstract class AckSensor<T> extends AbstractDevice<T> {
    int[] actorIdTab;
    T[] actorStatusTab;
    boolean[] actorAckTab;
    int gid;
    private static final String CONFIGFILENAME = "AckSensor";

    public AckSensor() { }

    public AckSensor(int id, T status, String location, String description, int[] actorIdTab, T[] actorStatusTab, int gid) throws SocketException {
	super(id, status, location, description);
	this.actorIdTab = actorIdTab;
	this.actorStatusTab = actorStatusTab;
	this.actorAckTab = new boolean[actorIdTab.length];
	this.gid = gid;
    }

    public void startResponseThread() {
        ActorRespThread art = new ActorRespThread(this);
        art.start();
    }
    
    public void setActorStatus(T status, int pos){
	actorStatusTab[pos] = status;
    }

    public abstract void setActorStatus(String status, int pos);

    public boolean checkRespones(){
        for (int i = 0; i < actorAckTab.length; i++) {
            if(actorAckTab[i] == false)
                return false;
        }
        return true;
    }

    public void save(){
        Config sc = new Config();
        save(sc);
        sc.setActorIDTab(actorIdTab);
        sc.setActorStatusTab(actorStatusTab);
        sc.setActorAckTab(actorAckTab);
       setConfig(sc, CONFIGFILENAME);
    }

    public void load(){
        Config sc = getConfig(CONFIGFILENAME);
        load(sc);
        this.actorIdTab = sc.getActorIDTab();
        this.actorStatusTab = (T[]) sc.getActorStatusTab();
        this.actorAckTab = sc.getActorAckTab();
    }

    @Override
    public void handleMsg(String jsonMsg, DeviceType devType, Type cfgType) {
	try {
	    Message msg = gson.fromJson(jsonMsg, Message.class);
	    Message reply;
	    Config<T> sc;
	    switch (msg.getMsgType()) {
		case statusRequest: //denke ist fertig. TL
		    reply = new Message();
		    reply.setMsgType(MessageType.statusResponse);
		    reply.setSenderId(this.id);
		    reply.setReceiverId(ALLDEVICES);
		    reply.setSenderDevice(devType);
		    reply.setContent(String.valueOf(this.status));
		    sendMsg(reply);
		    break;
		case statusResponse:
                    for (int i = 0; i < actorIdTab.length; i++) {
                        if (actorIdTab[i] == msg.getSenderId()) {
                            this.setActorStatus(msg.getContent(), i);
                            actorAckTab[i] = true;
                        }
                    }
		    break;
		case configChange:
		    if (msg.getReceiverId() == this.id) {
			sc = gson.fromJson(msg.getContent(), cfgType);
			setConfig(sc, CONFIGFILENAME);
			getConfig(CONFIGFILENAME);
		    }
		    break;
		case configRequest:
		    reply = new Message();
		    reply.setMsgType(MessageType.configResponse);
		    reply.setSenderId(this.id);
		    reply.setReceiverId(ALLDEVICES);
		    reply.setSenderDevice(devType);
		    sc = getConfig(CONFIGFILENAME);
		    String content = gson.toJson(sc, cfgType);
		    reply.setContent(content);
		    sendMsg(reply);
		    break;
	    }

	} catch (JsonSyntaxException e) {
	    System.out.println("Received a non json: " + jsonMsg);
	}catch(JsonIOException e){
	    System.out.println("Received a non json: " + jsonMsg);
	}
    }
}
