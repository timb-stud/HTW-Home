package de.htwhome.devices;

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

    private static final String CONFIGFILENAME = "IntervalSensor";

    public IntervalSensor() {
    }

    public IntervalSensor(int id, T status, String location, String description) throws SocketException {
	super(id, status, location, description);
    }

    public void save() {
	Config sc = new Config();
	save(sc);
    }

    public void load() {
	Config sc = getConfig(CONFIGFILENAME);
	load(sc);
    }

    @Override
    public void handleMsg(String jsonMsg, DeviceType devType, Type cfgType) {
	Message msg = gson.fromJson(jsonMsg, Message.class);
	Message reply;
	Config<T> sc;
	switch (msg.getMsgType()) {
	    case statusRequest:
		reply = new Message();
		reply.setMsgType(MessageType.statusResponse);
		reply.setSenderId(this.id);
		reply.setReceiverId(ALLDEVICES);
		reply.setSenderDevice(devType);
		reply.setContent(String.valueOf(this.status));
		sendMsg(reply);
		break;
	    case configChange:
		if (msg.getReceiverId() == this.id) {
		    sc = gson.fromJson(msg.getContent(), cfgType);
		    setConfig(sc, CONFIGFILENAME);
		    getConfig(CONFIGFILENAME);
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
		sc = getConfig(CONFIGFILENAME);
		String content = gson.toJson(sc, cfgType);
		reply.setContent(content);
		sendMsg(reply);
		break;
	}
    }

}
