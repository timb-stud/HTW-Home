package de.htwhome.devices;

import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import de.htwhome.utils.LittleHelpers;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.Timer;

/**
 *
* @author Christian Rech, Tim Bartsch
 */
public abstract class Sensor<T> extends AbstractDevice<T>{

    protected int[] actorIdTab;
    protected T[] actorStatusTab;
    protected boolean[] actorAckTab; //TODO init
    protected int gid;


    public Sensor() {
    }

     public Sensor (int id, T status, String location, String description, int[] actorIdTab, T[] actorStatusTab, int gid) throws SocketException {
        super(id, status,location, description);
        this.actorIdTab = actorIdTab;
        this.actorStatusTab = actorStatusTab;
        this.gid = gid;
        actorAckTab = new boolean[actorIdTab.length];
        save();
    }

    public Sensor (int id, T status, String location, String description, int gid) throws SocketException {
        super(id, status,location, description);
        this.gid = gid;
    }

    
    public void startResponseThread() {
        actorRespThread art = new actorRespThread(this);
        art.start();
    }

    public void setActorStatus(T status, int pos){
	actorStatusTab[pos] = status;
    }

    public abstract void setActorStatus(String status, int pos);

    public void save(){
        Config sc = new Config();
        save(sc);
        sc.setActorIDTab(actorIdTab);
        sc.setActorStatusTab(actorStatusTab);
        sc.setActorAckTab(actorAckTab);
       setConfig(sc, "Sensor");
    }

    public void load(){
        Config sc = Sensor.getConfig("Sensor");
        load(sc);
        this.actorIdTab = sc.getActorIDTab();
        this.actorStatusTab = (T[]) sc.getActorStatusTab();
        this.actorAckTab = sc.getActorAckTab();
    }

    @Override
    public void handleMsg(String jsonMsg, DeviceType devType, Type cfgType){
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
                if (actorIdTab != null) {
                    for (int i = 0; i < actorIdTab.length; i++) {
                        if (actorIdTab[i] == msg.getSenderId()) {
			    this.setActorStatus(msg.getContent(), i);
                            actorAckTab[i] = true;
                        }
                    }
                }
		break;
	    case configChange:
		if (msg.getReceiverId() == this.id) {
		    sc = gson.fromJson(msg.getContent(), cfgType);
		    setConfig(sc, "Sensor");
		    getConfig("Sensor");
		}
		break;
	    case configRequest: //TODO implement
                reply = new Message();
		reply.setMsgType(MessageType.configResponse);
		reply.setSenderId(this.id);
		reply.setReceiverId(ALLDEVICES);
		reply.setSenderDevice(devType);
//		SensorConfig<T> sc = new SensorConfig<T>();
////                sc.setDescription(this.description);
////                sc.setId(this.id);
////                sc.setLocation(this.location);
////                sc.setStatus(this.status);
//              save(sc);
//		sc.setActorIDTab(actorIdTab);
//		sc.setActorStatusTab(actorStatusTab);
//                save();
                sc = getConfig("Sensor");
		String content = gson.toJson(sc, cfgType);
                reply.setContent(content);
                sendMsg(reply);
                break;
	}
    }
    public boolean checkRespones(){
        for (int i = 0; i < actorAckTab.length; i++) {
            if(actorAckTab[i] == false)
                return false;
        }
        return true;
    }
}
