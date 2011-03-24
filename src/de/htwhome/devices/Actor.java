package de.htwhome.devices;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
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

    public void save() {
        Config ac = new Config();
        save(ac);
        ac.setGidTab(gidTab);
        setConfig(ac, "Actor");
    }

    public void load() {
        Config ac = this.getConfig("Actor");
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
        Config<T> ac;
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
                break;
            case fireAlarm:
                handleFireAlarm();
                break;
	}
    }

    public void handleFireAlarm() {}

    public void handleWeatherAlarm() {}
    
}
