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
public abstract class IntervalSensor<T> extends AbstractDevice<T> {

    public IntervalSensor() {
    }

    public IntervalSensor(int id, T status, String location, String description) throws SocketException {
	super(id, status, location, description);
    }

    @Override
    public void handleMsg(String jsonMsg, DeviceType devType, Type cfgType) {
	try {
	    Message msg = gson.fromJson(jsonMsg, Message.class);
	    Message reply;
	    switch (msg.getMsgType()) {
		case statusRequest:
		    reply = new Message();
		    reply.setMsgType(MessageType.statusResponse);
		    reply.setSenderId(this.id);
		    reply.setSenderDevice(devType);
		    reply.setContent(String.valueOf(this.status));
		    sendMsg(reply);
		    break;
		case configChange:
		    if (msg.getReceiverId() == this.id) {
			System.out.println("CFG CHANGE");
			Config cfg = gson.fromJson(msg.getContent(), cfgType);
			loadAttributesFrom(cfg);
			saveConfig(devType);
		    }
		    break;
		case configRequest:
		    reply = new Message();
		    reply.setMsgType(MessageType.configResponse);
		    reply.setSenderId(this.id);
		    reply.setSenderDevice(devType);
		    Config cfg = new Config();
		    writeAttributesTo(cfg);
		    String content = gson.toJson(cfg, cfgType);
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
