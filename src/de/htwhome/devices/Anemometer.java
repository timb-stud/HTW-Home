package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.timer.TimeScheduler;
import java.lang.reflect.Type;
import java.net.SocketException;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;

/**
 *
 * @author tobiaslana
 * Anemometer (en) = Windmesser (de)
 */
public class Anemometer extends Sensor<Double>{

    public static final DeviceType deviceType = DeviceType.Anemometer;
    public static final Type cfgType = new TypeToken<Config<Double>>(){}.getType();
    private static final Double MAXLEVELWARNING = 9.0; //TODO Wert muss aus Konfig gelesen werden


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
        if (this.status > MAXLEVELWARNING) {
            System.out.println("!! ACHTUNG !!");
            Message warning = new Message();
            warning.setMsgType(MessageType.weatherAlarm);
            warning.setSenderId(this.id);
            warning.setReceiverId(ALLDEVICES);
            warning.setContent(String.valueOf(this.status));
            warning.setSenderDevice(deviceType);
            this.sendMsg(warning);
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

    private void startNotifier(int intervall){ //ToDO geht nicht
        TimeScheduler<Double> ts = new TimeScheduler<Double>(this);
        ts.startIntervallRandom(intervall);
    }

    public static void main(String[] args) throws SocketException {
        Anemometer a = new Anemometer(11301, 0.0, "Garten", "Windmesser", ALLDEVICES);
//        TimeScheduler<Double> ts = new TimeScheduler<Double>(a);
//        ts.startIntervallRandom(1000);
        a.startNotifier(1000);
        System.out.println("ende");
    }
}
