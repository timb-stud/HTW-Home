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
 * @author Tim Bartsch
 */
public abstract class IntervalSensor<T> extends AbstractDevice<T> {

    private Timer timer;
    private boolean timeSchedulerChangeStatus = false;

    public IntervalSensor() {
    }

    public IntervalSensor(int id, T status, String location, String description) throws SocketException {
	super(id, status, location, description);
    }

    public void startScheduler(T firstStatus, T secondStatus, long from, long till) {
	timer = new Timer();
	long now = System.currentTimeMillis();
	long start = (from + now) * 1000;  //TODO Berechnung
	long intervall = till * 1000;
	timer.schedule(new TimeSchedulerTask<T>(this, firstStatus, secondStatus), start, intervall);
    }

    public void startRandomScheduler(long intervall) {
	timer = new Timer();
	timer.schedule(new TimeSchedulerTask<T>(this), 0, intervall);
    }

    protected T newTimeSchedulerStatus(T firstStatus, T secondStatus) {
	if (timeSchedulerChangeStatus) {
	    status = secondStatus;
	} else {
	    status = firstStatus;
	}
	timeSchedulerChangeStatus = (timeSchedulerChangeStatus) ? false : true;
	return status;
    }

    protected T newTimeSchedulerStatus() {
	return (T) LittleHelpers.randomMeasurement();
    }

    public void stopScheduler() {
	timer.cancel(); //Terminate the timer thread
    }

    public void save() {
	Config sc = new Config();
	save(sc);
    }

    public void load() {
	Config sc = Sensor.getConfig("Sensor");
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

    public boolean getTimeSchedulerChangeStatus() {
	return timeSchedulerChangeStatus;
    }

    public void setTimeSchedulerChangeStatus(boolean TimeSchedulerChangeStatus) {
	this.timeSchedulerChangeStatus = TimeSchedulerChangeStatus;
    }
}
