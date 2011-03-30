package de.htwhome.devices;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import de.htwhome.gui.panel.ConfigChangeEvent;
import de.htwhome.gui.panel.ConfigChangeListener;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Volkan Gökkaya, Tobias Lana
 */
public class Panel extends AbstractDevice<Boolean> {

    private ConfigList configList;
    public static final DeviceType deviceType = DeviceType.Panel;
    public static final Type cfgType = new TypeToken<Config<Boolean>>() {}.getType();
    private boolean firealarm = false;
    private boolean weatheralarm = false;

    protected final CopyOnWriteArrayList<AlarmListener> listeners = new CopyOnWriteArrayList<AlarmListener>();

    public void addAlarmListener(AlarmListener l) {
        this.listeners.add(l);
    }

    public void removeAlarmListener(AlarmListener l) {
        this.listeners.remove(l);
    }

    protected void fireAlarmChangeEvent() {
	AlarmEvent evt = new AlarmEvent(this);
	for (AlarmListener l : listeners) {
	    l.alarmEventReceived(evt);
	}
    }

    public Panel(int id) {
        this.id = id;
        configList = new ConfigList();
        super.loadConfig(deviceType);
    }

    public Panel(int id, boolean status, String location, String description) throws SocketException {
        super(id, status, location, description);
        this.configList = new ConfigList();
    }

    public void addConfigChangeListener(ConfigChangeListener l) {
        configList.addConfigChangeListener(l);
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
    public void handleMsg(String jsonMsg, DeviceType devType, Type cfgType) {
        try {
            Message msg = gson.fromJson(jsonMsg, Message.class);
            switch (msg.getMsgType()) {
                case statusResponse:
                    updateConfigStatus(msg.getSenderId(), msg.getContent(), msg.getSenderDevice());
                    break;
                case configResponse:
                    updateConfig(msg.getContent(), msg.getSenderDevice());
                    break;
                case fireAlarm:
		    setFirealarm(true);
                    break;
                case weatherAlarm:
		    setWeatheralarm(true);
                    break;
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Received a non json: " + jsonMsg);
        } catch (JsonIOException e) {
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

    private void updateConfigStatus(int id, String status, DeviceType devType) {
        switch (devType) {
            case Anemometer:
                configList.setConfigStatus(id, Double.valueOf(status));
                break;
	    case DoorOpener:
		configList.setConfigStatus(id, Boolean.valueOf(status));
		break;
            case Light:
                configList.setConfigStatus(id, Boolean.valueOf(status));
                break;
	    case OnOffTimer:
	        configList.setConfigStatus(id, Boolean.valueOf(status));
		break;
            case Panel:
                configList.setConfigStatus(id, Boolean.valueOf(status));
                break;
            case PercentSwitch:
                configList.setConfigStatus(id, Integer.valueOf(status));
                break;
	    case Shutter:
		configList.setConfigStatus(id, Integer.valueOf(status));
		break;
            case Sunblind:
                configList.setConfigStatus(id, Integer.valueOf(status));
                break;
            case Switch:
                configList.setConfigStatus(id, Boolean.valueOf(status));
                break;
	    case Thermometer:
		configList.setConfigStatus(id, Double.valueOf(status));
		break;
        }
    }

    private void updateConfig(String jsonCfg, DeviceType devType) {
        switch (devType) {
            case Anemometer:
                updateConfig(jsonCfg, Anemometer.deviceType, Anemometer.cfgType, new Config<Double>());
                break;
	    case DoorOpener:
		updateConfig(jsonCfg, DoorOpener.deviceType, DoorOpener.cfgType, new Config<Boolean>());
                break;
            case Light:
                updateConfig(jsonCfg, Light.deviceType, Light.cfgType, new Config<Boolean>());
                break;
	    case OnOffTimer:
		updateConfig(jsonCfg, OnOffTimer.deviceType, OnOffTimer.cfgType, new Config<Boolean>());
                break;
            case Panel:
                updateConfig(jsonCfg, Panel.deviceType, Panel.cfgType, new Config<Boolean>());
                break;
            case PercentSwitch:
                updateConfig(jsonCfg, PercentSwitch.deviceType, PercentSwitch.cfgType, new Config<Integer>());
                break;
	    case Shutter:
		updateConfig(jsonCfg, Shutter.deviceType, Shutter.cfgType, new Config<Integer>());
                break;
	    case SmokeDetector:
		updateConfig(jsonCfg, SmokeDetector.deviceType, SmokeDetector.cfgType, new Config<Boolean>());
                break;
            case Sunblind:
                updateConfig(jsonCfg, SunBlind.deviceType, SunBlind.cfgType, new Config<Integer>());
                break;
            case Switch:
                updateConfig(jsonCfg, Switch.deviceType, Switch.cfgType, new Config<Boolean>());
                break;
	    case Thermometer:
		updateConfig(jsonCfg, Thermometer.deviceType, Thermometer.cfgType, new Config<Double>());
                break;

        }
    }

    private void updateConfig(String jsonCfg, DeviceType devType, Type cfgType, Config c) {
        c = gson.fromJson(jsonCfg, cfgType);
        c.setDeviceType(devType);
        configList.updateConfig(c);
        System.out.println("CFG LIST:" + configList);
    }

    public void changeConfig(Config cfg) {
        configList.updateConfig(cfg);
        Message msg = new Message();
        msg.setMsgType(MessageType.configChange);
        msg.setSenderId(this.id);
        msg.setReceiverId(cfg.getId());
        String json = "";
        System.out.println("WHAA" + cfg);
        switch (cfg.getDeviceType()) {
            case Anemometer:
                json = gson.toJson(cfg, Anemometer.cfgType);
                break;
	    case DoorOpener:
		json = gson.toJson(cfg, DoorOpener.cfgType);
		break;
            case Light:
                json = gson.toJson(cfg, Light.cfgType);
                break;
	    case OnOffTimer:
		json = gson.toJson(cfg, OnOffTimer.cfgType);
		break;
            case Panel:
                json = gson.toJson(cfg, Panel.cfgType);
                break;
            case PercentSwitch:
                json = gson.toJson(cfg, PercentSwitch.cfgType);
                break;
	    case Shutter:
		json = gson.toJson(cfg, Shutter.cfgType);
            case Sunblind:
                json = gson.toJson(cfg, SunBlind.cfgType);
                break;
            case Switch:
                json = gson.toJson(cfg, Switch.cfgType);
                break;
	    case Thermometer:
		json = gson.toJson(cfg, Thermometer.cfgType);
		break;
        }
        msg.setContent(json);
        sendMsg(msg);
    }

    public void changeStatus(int gid, Object status) {
        Message msg = new Message();
        msg.setSenderId(this.id);
        msg.setMsgType(MessageType.statusChange);
        msg.setReceiverId(gid);
        msg.setContent(String.valueOf(status));
        msg.setSenderDevice(deviceType);
        this.sendMsg(msg);
    }

    public void openDoor(int id) {
        Message msg = new Message();
        msg.setMsgType(MessageType.statusChange);
        msg.setSenderId(this.id);
        msg.setReceiverId(id);
        msg.setSenderDevice(deviceType);
	msg.setContent(String.valueOf(true));
        sendMsg(msg);
    }

    public void sendFireAlarm(){
	Message msg = new Message();
	msg.setMsgType(MessageType.fireAlarm);
	msg.setSenderId(this.id);
	msg.setSenderDevice(deviceType);
	sendMsg(msg);
    }

    public void sendWeatherAlarm(){
	Message msg = new Message();
	msg.setMsgType(MessageType.weatherAlarm);
	msg.setSenderId(this.id);
	msg.setSenderDevice(deviceType);
	sendMsg(msg);
    }

    public ConfigList getConfigList() {
        return configList;
    }

    @Override
    protected void fireChangeEvent() {
    }

    public boolean isFirealarm() {
	return firealarm;
    }

    public void setFirealarm(boolean firealarm) {
	this.firealarm = firealarm;
	fireAlarmChangeEvent();
    }

    public boolean isWeatheralarm() {
	return weatheralarm;
    }

    public void setWeatheralarm(boolean weatheralarm) {
	this.weatheralarm = weatheralarm;
	fireAlarmChangeEvent();
    }

}
