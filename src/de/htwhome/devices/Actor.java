package de.htwhome.devices;

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
        super.setStatus(status);
        String msg = "" + this.id + ":"  + this.status;
        //send(msg);
    }

    public void handleMsg(String msg){
        int gid = 0;                    //TODO json
        String action = "";
        T status = null;
        for(int i=0; i< this.gidTab.length; i++){
            if(this.gidTab[i] == gid){
                if("changeStatus".equals(action)){
                    setStatus(status);
                }else if("getStatus".equals(action)){
                    setStatus(this.status);
                }
            }
        }
    }
}
