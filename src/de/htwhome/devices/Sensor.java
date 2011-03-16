package de.htwhome.devices;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class Sensor<T> extends AbstractDevice<T>{

    private int[] actorListId;
    private T[] actorListStatus;
    private int gID;

     public Sensor (int id, T status,String location, String type, String hint, int[] actorListId, T[] actorListStatus, int gID) {
        super(id, status,location, type, hint);
        this.actorListId = actorListId;
        this.actorListStatus = actorListStatus;
        this.gID = gID;
    }

    @Override
    public void setStatus(T status) {
        super.setStatus(status);
        String msg = "" + this.gID + ":" + "changeStatus" + ":" + this.status;
        //send(msg);
    }

    public void handleMsg(String msg){
        int id = 0;             //TODO json
        T status = null;
        for(int i=0; i<actorListId.length; i++){
            if(actorListId[i] == id){
                actorListStatus[i] = status;
            }
        }
    }

}
