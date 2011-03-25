package de.htwhome.devices;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Volkan Gökkaya, Tobias Lana
 */
public class Panel extends AbstractDevice<Boolean>{

    private ConfigList configList;
    public static final DeviceType deviceType = DeviceType.Panel;
    public static final Type cfgType = new TypeToken<Config<Boolean>>(){}.getType();
    public static Boolean FIREALARM = false;
    public static Boolean WEATHERALARM = false;
    public static final Boolean OPEN = true;
    public static final Boolean CLOSE = false;

    public Panel() {}

    public Panel(int id, boolean status, String location, String type, String description) throws SocketException{
        super(id, status, location, description);
        this.configList = new ConfigList();

    }

    public void save() {
        Config pc = new Config();
        super.save(pc);
        pc.setConfigList(configList);
        setConfig(pc, "Panel");
    }

    public void load(){
        Config pc = this.getConfig("Panel");
        load(pc);
        this.configList = pc.getConfigList();
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
	try {
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
	} catch (JsonSyntaxException e) {
	    System.out.println("Received a non json: " + jsonMsg);
	} catch(JsonIOException e){
	    System.out.println("Received a non json: " + jsonMsg);
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
     * und schreibt die Devices in die ConfigList des Panels
     */
    private void updateDevicelist(String jsonCfg, DeviceType devType) {
        switch (devType) {
            case Anemometer:
                updateDevice(jsonCfg, Anemometer.deviceType, Anemometer.cfgType, new Config<Double>());
                break;
            case Light:
		updateDevice(jsonCfg, Light.deviceType, Light.cfgType, new Config<Boolean>());
                break;
            case Panel:
		updateDevice(jsonCfg, Panel.deviceType, Panel.cfgType, new Config<Boolean>());
                break;
            case PercentSwitch:
		updateDevice(jsonCfg, PercentSwitch.deviceType, PercentSwitch.cfgType, new Config<Integer>());
                break;
            case Sunblind:
		updateDevice(jsonCfg, SunBlind.deviceType, SunBlind.cfgType, new Config<Integer>());
                break;
            case Switch:
		updateDevice(jsonCfg, Switch.deviceType, Switch.cfgType, new Config<Boolean>());
                break;
        }
    }



    private void updateDevice(String jsonCfg, DeviceType devType, Type cfgType, Config c){
	c = gson.fromJson(jsonCfg, cfgType);
	c.setDeviceType(deviceType);
	configList.updateConfig(c);
	System.out.println("CFG LIST:" + configList);
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
        p.getAllConfigs();
        //p.openDoor();
    }

}
