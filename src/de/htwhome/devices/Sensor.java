package de.htwhome.devices;

import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.SensorConfig;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.Timer;
import javax.xml.bind.JAXB;

/**
 *
* @author Christian Rech, Tim Bartsch
 */
public abstract class Sensor<T> extends AbstractDevice<T>{

    private Timer timer;

    protected int[] actorIdTab;
    protected T[] actorStatusTab;
    protected boolean[] actorAckTab; //TODO init
    protected int gid;
    private boolean timeSchedulerChangeStatus = false;


    public Sensor() {
    }

     public Sensor (int id, T status, String location, String description, int[] actorIdTab, T[] actorStatusTab, int gid) throws SocketException {
        super(id, status,location, description);
        this.actorIdTab = actorIdTab;
        this.actorStatusTab = actorStatusTab;
        this.gid = gid;
        actorAckTab = new boolean[actorIdTab.length];
    }

    public Sensor (int id, T status, String location, String description, int gid) throws SocketException {
        super(id, status,location, description);
        this.gid = gid;
    }

    public static SensorConfig getConfig(){  //TODO Config file + config als attribut
        SensorConfig config = JAXB.unmarshal(new File("SensorConfig.xml"), SensorConfig.class);
        return config;
    }

    public void startScheduler(T firstStatus, T secondStatus,long from, long till){
        timer = new Timer();
        long start = from * 1000;  //TODO Berechnung
        long intervall = till * 1000;
        timer.schedule(new TimeSchedulerTask<T>(this, firstStatus, secondStatus), start, intervall);
    }

    protected  T newTimeSchedulerStatus(T firstStatus, T secondStatus){
       if (timeSchedulerChangeStatus)
            status = secondStatus;
        else
            status = firstStatus;
       timeSchedulerChangeStatus = (timeSchedulerChangeStatus) ? false : true;
       return status;
    }

    public void stopScheduler(){
        timer.cancel(); //Terminate the timer thread
    }

    public static void setConfig(SensorConfig config) {
        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(("SensorConfig.xml"));
            JAXB.marshal(config, filewriter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filewriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void setStatus(T status) {
        actorRespThread art = new actorRespThread(this);
        art.start();
    }

    public void setActorStatus(T status, int pos){
	actorStatusTab[pos] = status;
    }

    public abstract void setActorStatus(String status, int pos);

    public void save(){
        SensorConfig sc = new SensorConfig();
        save(sc);
        sc.setActorIDTab(actorIdTab);
        sc.setActorStatusTab(actorStatusTab);
        sc.setActorAckTab(actorAckTab);
       setConfig(sc);
    }

    public void load(){
        SensorConfig sc = Sensor.getConfig();
        load(sc);
        this.actorIdTab = sc.getActorIDTab();
        this.actorStatusTab = (T[]) sc.getActorStatusTab();
        this.actorAckTab = sc.getActorAckTab();
    }

    @Override
    public void handleMsg(String jsonMsg, DeviceType devType, Type cfgType){
	Message msg = gson.fromJson(jsonMsg, Message.class);
	switch (msg.getMsgType()) {
	    case statusRequest:
		//TODO implement
		break;
	    case statusResponse:
                if (actorIdTab != null) {
                    for (int i = 0; i < actorIdTab.length; i++) {
                        if (actorIdTab[i] == msg.getSenderId()) {
			    this.setActorStatus(msg.getContent(), i);
                            actorAckTab[i] = true;
                        }
                    }
                } else {
                    System.out.println("statusResponse interessiert dieses Device nicht");
                }
		break;
	    case configChange:
		//TODO implement
		break;
	    case configRequest:
                Message reply = new Message();
		reply.setMsgType(MessageType.configChange);
		reply.setSenderId(this.id);
		reply.setReceiverId(ALLDEVICES);
		reply.setSenderDevice(devType);
		SensorConfig<T> ac = new SensorConfig<T>();
		ac.setDescription(this.description);
		ac.setId(this.id);
		ac.setLocation(this.location);
		ac.setStatus(this.status);
		ac.setActorIDTab(actorIdTab);
		ac.setActorStatusTab(actorStatusTab);
		String content = gson.toJson(ac, cfgType);
		reply.setContent(content);
                sendMsg(reply);
                break;
	}
    }
    public boolean checkRespones(){
        for (int i = 0; i < actorAckTab.length; i++) {
            if(actorAckTab[i] == false)
                return false;
        }
        return true;
    }

    public boolean getTimeSchedulerChangeStatus() {
        return timeSchedulerChangeStatus;
    }

    public void setTimeSchedulerChangeStatus(boolean TimeSchedulerChangeStatus) {
        this.timeSchedulerChangeStatus = TimeSchedulerChangeStatus;
    }
}
