package de.htwhome.devices;
import com.google.gson.Gson;
import de.htwhome.utils.ActionEnum;
import java.lang.reflect.Type;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class Actor<T> extends AbstractDevice<T>{

    private int[] gidTab;
  
    public Actor(int id, T status,String location, String type, String hint, int[] gID) {
        super(id, status,location, type, hint);
        this.gidTab = gID;
    }

    public void sendMsg(AckMessage<T> ackMsg, Type ackMsgTyp){
	String json = new Gson().toJson(ackMsg, ackMsgTyp);
	System.out.println("JSON:" + json);
	//TODO ssend(json)
    }

    public void handleMsg(String msg){
        int gid = 0;                    //TODO json
        ActionEnum action = null;
        T status = null;
        for(int i=0; i< this.gidTab.length; i++){
            if(this.gidTab[i] == gid){
                switch(action){
                    case changeStatus:
                        setStatus(status);
                        break;
                    case getStatus:
                        setStatus(this.status);
                        break;
                }
            }
        }
    }
}
