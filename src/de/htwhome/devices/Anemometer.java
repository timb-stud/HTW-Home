package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.SocketException;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;

/**
 *
 * @author tobiaslana
 * Anemometer (en) = Windmesser (de)
 */
public class Anemometer extends Sensor<Double>{

    public static final Type msgType = new TypeToken<Message<Double>>(){}.getType();


    public Anemometer () {
        super.load();
    }
    
    public Anemometer (int id, Double status, String location, String description, int gid) throws SocketException {
        super(id, status, location, description, gid);
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, msgType);
    }

    @Override
    public void setStatus(Double status) {
        this.status = status;
	Message<Double> msg = new Message<Double>();
        msg.setMsgType(MessageType.statusResponse);
        msg.setSenderId(this.id);
        msg.setReceiverId(this.gid);
        msg.setStatus(this.status);
	this.sendMsg(msg, msgType);
        System.out.println("Neue Windgeschwindigkeit: " + this.status); //TODO sout entfernen
    }

    public static void main(String[] args) throws SocketException {
        Anemometer a = new Anemometer(125, 5.5, "Garten", "Windmesser", ALLDEVICES);
        a.startScheduler(a.getStatus(), 0, 5);
        a.setStatus(1.0);
    }


}
