package de.htwhome.devices;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class Sensor<T> extends AbstractDevice<T>{

    protected int[] actorIdTab;
    protected T[] actorStatusTab;
    protected int gid;

     public Sensor (int id, T status, String location, String type, String description, int[] actorIdTab, T[] actorStatusTab, int gid) {
        super(id, status,location, type, description);
        this.actorIdTab = actorIdTab;
        this.actorStatusTab = actorStatusTab;
        this.gid = gid;
    }

     public void sendMsg(ActionMessage<T> actionMsg, Type actionMsgType){
         String json = new Gson().toJson(actionMsg, actionMsgType);
         System.out.println("JSON:" + json);
         //TODO send(json);
     }

    public void handleMsg(String msg){
        int id = 0;             //TODO json
        T status = null;
        for(int i=0; i < actorIdTab.length; i++){
            if(actorIdTab[i] == id){
                actorStatusTab[i] = status;
            }
        }
    }

}
