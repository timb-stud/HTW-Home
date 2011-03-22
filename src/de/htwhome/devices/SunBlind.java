package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.ActorConfig;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public class SunBlind extends Actor<Integer>{

    public static final Type cfgType = new TypeToken<ActorConfig<Integer>>(){}.getType();
    public static final DeviceType deviceType = DeviceType.Sunblind;
    private int MIN = 0;    //TODO minimum und maximum festlegen
    private int MAX = 100;  //TODO minimum und maximum festlegen

    public SunBlind(int id, int status, String location, String description, int[] gidTab) throws SocketException {
        super(id, status, location, description, gidTab);
    }

    @Override
    public void handleMsg(String msg) {
	super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void handleWeatherAlarm() {
        setStatus(MIN);
    }

    @Override
    public void handleFireAlarm() {
        setStatus(MIN);
    }

    @Override
    public void setStatus(Integer status) {
	this.status = status;
	Message msg = new Message();
	msg.setMsgType(MessageType.statusResponse);
	msg.setSenderId(this.id);
	msg.setSenderDevice(deviceType);
	msg.setContent(String.valueOf(this.status));
	sendMsg(msg);
    }

    @Override
    public void setStatus(String status) {
	int i = Integer.valueOf(status);
	this.setStatus(i);
    }

    public static void main(String[] args) throws SocketException {
        int[] gidTab = {23000, 23001};
        SunBlind sb = new SunBlind(12201, 50, "Wohnzimmer aussen", "Markise", gidTab); //TODO aus Konfig
        //a.startScheduler(randomMeasurement(), randomMeasurement(), 1, 5);
    }
    
}
