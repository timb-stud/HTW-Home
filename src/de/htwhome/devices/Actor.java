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
 * @author Christian Rech, Tim Bartsch
 */
public abstract class Actor<T> extends AbstractDevice<T>{

    private int[] gidTab;

    /**
     * Standardkonstruktor
     */
    public Actor() {}

    /**
     * Konstruktor
     * @param id
     * @param status
     * @param location
     * @param description
     * @param gidTab
     * @throws SocketException
     */
    public Actor(int id, T status, String location, String description, int[] gidTab) throws SocketException {
        super(id, status,location, description);
        this.gidTab = gidTab;
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
	try {
	    Message msg = gson.fromJson(jsonMsg, Message.class);
	    switch (msg.getMsgType()) {
		case statusChange:
		    if (isReceiver(msg.getReceiverId())) {
			setStatus(msg.getContent());
		    }
		    break;
		case statusRequest:
		    if (isReceiver(msg.getReceiverId())) {
			setStatus(this.status);
		    }
		    break;
		case configChange:  //TODO testen
		    if (isReceiver(msg.getReceiverId())) {
			Config cfg = gson.fromJson(msg.getContent(), cfgType);
			loadAttributesFrom(cfg);
			saveConfig(devType);
		    }
		    break;
		case configRequest: //TODO testen
		    Message reply = new Message();
		    reply.setMsgType(MessageType.configResponse);
		    reply.setSenderId(this.id);
		    reply.setReceiverId(ALLDEVICES);
		    reply.setSenderDevice(devType);
		    Config cfg = new Config();
		    writeAttributesTo(cfg);
		    String content = gson.toJson(cfg, cfgType);
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
	} catch (JsonSyntaxException e) {
	    System.out.println("Received a non json: " + jsonMsg);
	}catch(JsonIOException e){
	    System.out.println("Received a non json: " + jsonMsg);
	}
    }

    public void handleFireAlarm() {}

    public void handleWeatherAlarm() {}
    
}
