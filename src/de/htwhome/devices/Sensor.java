package de.htwhome.devices;

import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.ActorConfig;
import de.htwhome.utils.SensorConfig;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import javax.xml.bind.JAXB;

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

     public Sensor (int id, T status, String location, String type, String description, int[] actorIdTab, T[] actorStatusTab, int gid) throws SocketException {
        super(id, status,location, type, description);
        this.actorIdTab = actorIdTab;
        this.actorStatusTab = actorStatusTab;
        this.gid = gid;
    }

       public static SensorConfig getConfig(){  //TODO Config file + config als attribut
        SensorConfig config = JAXB.unmarshal(new File("SensorConfig.xml"), SensorConfig.class);
        return config;
    }

    public static void setConfig(SensorConfig config) {
        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(("SensorConfig.xml"));
            JAXB.marshal(config, filewriter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filewriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     
     
    public void save(){
        SensorConfig sc = new SensorConfig();
        save(sc);
        sc.setActorIDTab(actorIdTab);
        sc.setActorStatusTab(actorStatusTab);
        sc.setActorAckTab(actorAckTab);
       setConfig(sc);
    }

    public void load(){
        SensorConfig sc = this.getConfig();
        load(sc);
        this.actorIdTab = sc.getActorIDTab();
        this.actorStatusTab = (T[]) sc.getActorStatusTab();
        this.actorAckTab = sc.getActorAckTab();
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
		Message reply = new Message(MessageType.configResponse, this.id, 999, null, null);
                sendMsg(reply, null);
		break;
	}
    }

}
