package de.htwhome.devices;

import de.htwhome.transmission.MessageSender;
import de.htwhome.utils.SensorConfig;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class Sensor<T> extends AbstractDevice<T>{

    protected int[] actorIdTab;
    protected T[] actorStatusTab;
    protected int gid;

     public Sensor (int id, T status, String location, String type, String description, int[] actorIdTab, T[] actorStatusTab, int gid) throws SocketException {
        super(id, status,location, type, description);
        this.actorIdTab = actorIdTab;
        this.actorStatusTab = actorStatusTab;
        this.gid = gid;
    }

     public void sendMsg(ActionMessage<T> actionMsg, Type actionMsgType){
        try {
            String json = gson.toJson(actionMsg, actionMsgType);
            System.out.println("JSON:" + json); //TODO send(json) + aufraeumen;
            MessageSender.sendMsg(json);
        } catch (IOException ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public void handleMsg(String msg, Type msgType){
	AckMessage<T> ackMsg = gson.fromJson(msg, msgType);
        for(int i=0; i < actorIdTab.length; i++){
            if(actorIdTab[i] == ackMsg.getId()){
                actorStatusTab[i] = ackMsg.getStatus();
            }
        }
    }

}
