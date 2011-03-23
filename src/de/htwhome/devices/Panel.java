package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.ActorConfig;
import de.htwhome.utils.PanelConfig;
import de.htwhome.utils.SensorConfig;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.ArrayList;
import javax.xml.bind.JAXB;

/**
 *
 * @author Volkan Gökkaya, Tobias Lana
 */
public class Panel extends AbstractDevice<Boolean>{

    private ArrayList<AbstractDevice> deviceList;
    public static final DeviceType deviceType = DeviceType.Panel;
    public static final Type cfgType = new TypeToken<ActorConfig<Boolean>>(){}.getType();
    public static Boolean FIREALARM = false;
    public static Boolean WEATHERALARM = false;
    public static final Boolean OPEN = true;
    public static final Boolean CLOSE = false;

    

    public Panel() {}

    public Panel(int id, boolean status, String location, String type, String description) throws SocketException{
        super(id, status, location, description);
        this.deviceList = new ArrayList<AbstractDevice>();

    }

    public void save() {
        PanelConfig pc = new PanelConfig();
        super.save(pc);
        pc.setDeviceList(deviceList);
        setConfig(pc);
    }

    public void load(){
        PanelConfig pc = this.getConfig();
        load(pc);
        this.deviceList = pc.getDeviceList();
    }

   public static PanelConfig getConfig(){
        PanelConfig config = JAXB.unmarshal(new File("PanelConfig.xml"), PanelConfig.class);
        return config;
    }

    public static void setConfig(PanelConfig config) {
        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(("PanelConfig.xml"));
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
    public void handleMsg(String jsonMsg) {
	this.handleMsg(jsonMsg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public void setStatus(String status) {
	boolean b = Boolean.parseBoolean(status);
	this.setStatus(b);
    }

    @Override
    public void handleMsg(String jsonMsg, DeviceType devType, Type cfgType){
	Message msg = gson.fromJson(jsonMsg, Message.class);
	switch (msg.getMsgType()) {
	    case statusResponse:
		handleStatusResponse(msg);
                updateDevicelist(msg.getContent(), msg.getSenderDevice());
		break;
	    case configChange:
		//TODO implement
		break;
	    case configRequest:
		//TODO implement
		break;
            case configResponse:
                updateDevicelist(msg.getContent(), msg.getSenderDevice());
                break;
            case fireAlarm:
                FIREALARM = true; //TODO Methode um boolean wieder durch Benutzereingabe auf false zu setzen
                panelPopUp("FEUER");
                break;
            case weatherAlarm:
                WEATHERALARM = true; //TODO Methode um boolean wieder durch Benutzereingabe auf false zu setzen
                panelPopUp("UNWETTER");
                break;
            case statusChange:
                handleStatusChange(msg);
                break;
	}
    }

    public void getAllConfigs() {
	//TODO implement
        Message msg = new Message();
        msg.setMsgType(MessageType.configRequest);
        msg.setSenderId(this.id);
        msg.setReceiverId(ALLDEVICES);
        sendMsg(msg);
//        sendMsg(msg, msgType); //TODO msgTyp ueberpruefen
    }

    public void useDeviceFunction(int receiverId) {
	//TODO implement
//        Message msg = new Message(MessageType.statusChange, this.id, receiverId, null, null);
//        sendMsg(msg, msgType); //TODO msgTyp ueberpruefen
    }

    /*
     * updateDevicelist
     * @author TL
     * @param String jsonMsg
     * 
     * Methode wertet die Nachrichten des Typs configResponse aus
     * und schreibt die Devices in die DeviceList des Panels
     */
    private void updateDevicelist(String jsonCfg, DeviceType devType) {
        //TODO implement case functions
        switch (devType) {
            case Anemometer:
                SensorConfig<Double> sc = gson.fromJson(jsonCfg, Anemometer.cfgType);
                System.out.println("ID " + sc.getId() + " hat sich gemeldet. >> " + sc.getDescription());
                sc.setDeviceType(deviceType);
                break;
            case Light:
                System.out.println("Light meldet sich");
                break;
            case Panel:
                System.out.println("Panel meldet sich");
                break;
            case PercentSwitch:
                System.out.println("PercentSwitch meldet sich");
                break;
            case Sunblind:
                System.out.println("Sunblind meldet sich");
                break;
            case Switch:
                System.out.println("Switch meldet sich");
                break;
        }
	//TODO implement
//        Message<T> msg = gson.fromJson(jsonMsg, msgType);
//        //Message content = gson.fromJson(msg.getJsonConfig(), msgType);
//        System.out.println("ID: " + msg.getSenderId() + " hat sich gemeldet");
//        System.out.println(msg.getJsonConfig());
//        String msg2 = gson.fromJson(msg.getJsonConfig(), msgType);
//        System.out.println(msg2);
    }

    /*
     * Panel muss auf verschiedene StatusChanges unterschiedlich reagieren.
     * Dies uebernimmt diese Methode
     * @author TL
     */
    private void handleStatusChange(Message msg) {
        switch (msg.getReceiverId()) {
            case 29001: //Klingel
                if (msg.getContent().equals("true")) {
                    panelPopUp("Jemand an der Tür");
                } else {
                    panelPopUp("Verpasster Besucher");
                }
                break;
        }
    }

    /*
     * Panel muss auf verschiedene StatusResponse unterschiedlich reagieren.
     * Dies uebernimmt diese Methode
     * @author TL
     */
    private void handleStatusResponse(Message msg) {
        switch (msg.getSenderId()) {
            case 12101: //Webcam
                System.out.println("Neues Webcambild: " + msg.getContent());
                break;
        }
    }

    /*
     * Mit Hilfe dieser Methode soll ein Popup auf dem Panel erscheinen
     * @author TL
     */
    public void panelPopUp(String meldung) {
        //TODO PopUp auf Panel bringen
        //TODO implement
        System.out.println(meldung);
    }

    /*
     * @author TL
     */
    public void resetAlarms() {
        WEATHERALARM = false;
        FIREALARM = false;
    }

    public void openDoor() {
        Message msg = new Message();
        msg.setMsgType(MessageType.statusChange);
        msg.setSenderId(this.id);
        msg.setReceiverId(12501);
        msg.setSenderDevice(deviceType);
	msg.setContent(String.valueOf(OPEN));
        sendMsg(msg);
    }

    public static void main(String[] args) throws SocketException {
        Panel p = new Panel(13001, false, "Wohnzimmer", "Panel", "Megapanel");
        //p.getAllConfigs();
        p.openDoor();
    }

}
