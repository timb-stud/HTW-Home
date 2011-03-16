package de.htwhome.devices;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class Sensor<T> extends AbstractDevice<T>{

    protected int[] actorListId;
    protected T[] actorListStatus;
    protected int gid;

     public Sensor (int id, T status,String location, String type, String hint, int[] actorListId, T[] actorListStatus, int gID) {
        super(id, status,location, type, hint);
        this.actorListId = actorListId;
        this.actorListStatus = actorListStatus;
        this.gid = gID;
    }

     public void sendMsg(ActionMessage<T> actionMsg, Type actionMsgType){
         String json = new Gson().toJson(actionMsg, actionMsgType);
         System.out.println("JSON:" + json);
         //TODO send(json);
     }

    public void handleMsg(String msg){
        int id = 0;             //TODO json
        T status = null;
        for(int i=0; i < actorListId.length; i++){
            if(actorListId[i] == id){
                actorListStatus[i] = status;
            }
        }
    }

}
