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
    public static Boolean firealarm = false;
    public static Boolean weatheralarm = false;

    public Panel(int id) {
	this.id = id;
	super.loadConfig(deviceType);
    }

    public Panel(int id, boolean status, String location, String type, String description) throws SocketException{
        super(id, status, location, description);
        this.configList = new ConfigList();

    }

    @Override
    public void handleMsg(String jsonMsg) {
	this.handleMsg(jsonMsg, deviceType, cfgType);
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
	fireChangeEvent();
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
		    updateConfigStatus(msg.getSenderId(), jsonMsg, devType);
		    break;
		case configResponse:
		    updateConfig(msg.getContent(), msg.getSenderDevice());
		    break;
		case fireAlarm:
		    firealarm = true; //TODO Methode um boolean wieder durch Benutzereingabe auf false zu setzen
		    System.out.println("FEUER");
		    break;
		case weatherAlarm:
		    weatheralarm = true; //TODO Methode um boolean wieder durch Benutzereingabe auf false zu setzen
		    System.out.println("UNWETTER");
		    break;
	    }
	} catch (JsonSyntaxException e) {
	    System.out.println("Received a non json: " + jsonMsg);
	} catch(JsonIOException e){
	    System.out.println("Received a non json: " + jsonMsg);
	}
    }

    public void getAllConfigs() {
        Message msg = new Message();
        msg.setMsgType(MessageType.configRequest);
        msg.setSenderId(this.id);
        msg.setReceiverId(ALLDEVICES);
        sendMsg(msg);
    }

    private void updateConfigStatus(int id, String status, DeviceType devType){
	switch (devType) {
            case Anemometer:
		configList.setConfigStatus(id, Double.valueOf(status));
                break;
            case Light:
		configList.setConfigStatus(id, Boolean.valueOf(status));
                break;
            case Panel:
		configList.setConfigStatus(id, Boolean.valueOf(status));
                break;
            case PercentSwitch:
		configList.setConfigStatus(id, Integer.valueOf(status));
                break;
            case Sunblind:
		configList.setConfigStatus(id, Integer.valueOf(status));
                break;
            case Switch:
		configList.setConfigStatus(id, Boolean.valueOf(status));
                break;
        }
    }

    private void updateConfig(String jsonCfg, DeviceType devType) {
        switch (devType) {
            case Anemometer:
                updateConfig(jsonCfg, Anemometer.deviceType, Anemometer.cfgType, new Config<Double>());
                break;
            case Light:
		updateConfig(jsonCfg, Light.deviceType, Light.cfgType, new Config<Boolean>());
                break;
            case Panel:
		updateConfig(jsonCfg, Panel.deviceType, Panel.cfgType, new Config<Boolean>());
                break;
            case PercentSwitch:
		updateConfig(jsonCfg, PercentSwitch.deviceType, PercentSwitch.cfgType, new Config<Integer>());
                break;
            case Sunblind:
		updateConfig(jsonCfg, SunBlind.deviceType, SunBlind.cfgType, new Config<Integer>());
                break;
            case Switch:
		updateConfig(jsonCfg, Switch.deviceType, Switch.cfgType, new Config<Boolean>());
                break;
        }
    }

    private void updateConfig(String jsonCfg, DeviceType devType, Type cfgType, Config c){
	c = gson.fromJson(jsonCfg, cfgType);
	c.setDeviceType(deviceType);
	configList.updateConfig(c);
	System.out.println("CFG LIST:" + configList);
    }

//    public void openDoor() {
//        Message msg = new Message();
//        msg.setMsgType(MessageType.statusChange);
//        msg.setSenderId(this.id);
//        msg.setReceiverId(12501);
//        msg.setSenderDevice(deviceType);
//	msg.setContent(String.valueOf(OPEN));
//        sendMsg(msg);
//    }

    public static Boolean getFirealarm() {
	return firealarm;
    }

    public static void setFirealarm(Boolean firealarm) {
	Panel.firealarm = firealarm;
    }

    public static Boolean getWeatheralarm() {
	return weatheralarm;
    }

    public static void setWeatheralarm(Boolean weatheralarm) {
	Panel.weatheralarm = weatheralarm;
    }


    public static void main(String[] args) throws SocketException {
        Panel p = new Panel(13001, false, "Wohnzimmer", "Panel", "Megapanel");
        p.getAllConfigs();
        //p.openDoor();
    }

    @Override
    protected void fireChangeEvent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
