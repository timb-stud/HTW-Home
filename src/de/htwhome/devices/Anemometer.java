package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.SocketException;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.SensorConfig;

/**
 *
 * @author tobiaslana
 * Anemometer (en) = Windmesser (de)
 */
public class Anemometer extends Sensor<Double>{

    public static final DeviceType deviceType = DeviceType.Switch;
    public static final Type cfgType = new TypeToken<SensorConfig<Double>>(){}.getType();
    private static final Double MAXLEVELWARNING = 9.0; //TODO Wert muss aus Konfig gelesen werden
    private static final Double MINLEVELWARNING = 1.0; //TODO Wert muss aus Konfig gelesen werden


    public Anemometer () {
        super.load();
    }
    
    public Anemometer (int id, Double status, String location, String description, int gid) throws SocketException {
        super(id, status, location, description, gid);
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Double status) {
        this.status = status;
        if (this.status < MINLEVELWARNING || this.status > MAXLEVELWARNING) {
            System.out.println("Sende Warnung !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
	Message msg = new Message();
        msg.setMsgType(MessageType.statusResponse);
        msg.setSenderId(this.id);
        msg.setReceiverId(this.gid);
        msg.setContent(String.valueOf(this.status));
	msg.setSenderDevice(deviceType);
	this.sendMsg(msg);
        System.out.println("Neue Windgeschwindigkeit: " + this.status); //TODO sout entfernen
    }

    public static void main(String[] args) throws SocketException {
        Anemometer a = new Anemometer(125, 5.5, "Garten", "Windmesser", ALLDEVICES);
//        a.startScheduler(a.getStatus(), 0, 5);
        a.setStatus(6.0);
        a.setStatus(9.0);
        a.setStatus(10.0);
    }

    @Override
    public void setStatus(String status) {
	double d = Double.valueOf(status);
	this.setStatus(d);
    }

    @Override
    public void setActorStatus(String status, int pos) {
	double d = Double.valueOf(status);
	this.setActorStatus(d, pos);
    }


}
