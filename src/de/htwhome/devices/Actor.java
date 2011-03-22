package de.htwhome.devices;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.ActorConfig;
import de.htwhome.utils.HTWhomeConfig;
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
public abstract class Actor<T> extends AbstractDevice<T>{

    private int[] gidTab;

    public Actor() {}

    public Actor(int id, T status, String location, String description, int[] gidTab) throws SocketException {
        super(id, status,location, description);
        this.gidTab = gidTab;
        save();
    }

//   public static ActorConfig getConfig(){
//        ActorConfig config = JAXB.unmarshal(new File("ActorConfig.xml"), ActorConfig.class);
//        return config;
//    }
//
//    public static void setConfig(ActorConfig config) {
//        FileWriter filewriter = null;
//        try {
//            filewriter = new FileWriter("ActorConfig.xml");
//            JAXB.marshal(config, filewriter);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                filewriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void save() {
        HTWhomeConfig ac = new HTWhomeConfig();
        save(ac);
        ac.setGidTab(gidTab);
        setConfig(ac, "Actor");
    }

    public void load() {
        HTWhomeConfig ac = this.getConfig("Actor");
        load(ac);
        this.gidTab = ac.getGidTab();
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
    public void handleMsg(String jsonMsg, DeviceType devType, Type cfgType){
	Message msg = gson.fromJson(jsonMsg, Message.class);
        HTWhomeConfig<T> ac;
        System.out.println("Verarbeite Nachricht vom Typ: " + msg.getMsgType());
	switch (msg.getMsgType()) {
	    case statusChange:
		if(isReceiver(msg.getReceiverId())){
		    setStatus(msg.getContent());
		}
		break;
	    case statusRequest:
		if(isReceiver(msg.getReceiverId())){
		    setStatus(this.status);
		}
		break;
	    case configChange:  //TODO testen
                if(isReceiver(msg.getReceiverId())){
                    ac = gson.fromJson(msg.getContent(), cfgType);
                    setConfig(ac, "Actor");
		    getConfig("Actor"); //TODO Quittierung?
		}
		break;
	    case configRequest:
                Message reply = new Message();
		reply.setMsgType(MessageType.configChange);
		reply.setSenderId(this.id);
		reply.setReceiverId(ALLDEVICES);
		reply.setSenderDevice(devType);
                save();
                ac =  getConfig("Actor");
		String content = gson.toJson(ac, cfgType);
		reply.setContent(content);
                sendMsg(reply);
                break;
            case weatherAlarm:
                handleWeatherAlarm();
            case fireAlarm:
                handleFireAlarm();
                break;
	}
    }

    public void handleFireAlarm() {}

    public void handleWeatherAlarm() {}
    
}
