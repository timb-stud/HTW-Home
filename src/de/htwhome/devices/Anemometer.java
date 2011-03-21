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
        super(id, status, location, description, null, null, gid);
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, msgType);
    }

    @Override
    public void setStatus(Double status) {
        //TODO Scheduler zum Status posten verwenden
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) throws SocketException {
        Anemometer a = new Anemometer(125, 5.5, "Garten", "Windmesser", ALLDEVICES);
        a.startScheduler(a.getStatus(), 0, 5);
    }


}
