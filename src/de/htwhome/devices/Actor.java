package de.htwhome.devices;
import de.htwhome.utils.ActionEnum;

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

    @Override
    public void setStatus(T status) {
        this.status = status;
        String msg = "" + this.id + ":"  + this.status;
        //send(msg);
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
