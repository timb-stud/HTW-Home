package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.utils.Config;
import java.lang.reflect.Type;
import de.htwhome.timer.TimeScheduler;
import de.htwhome.timer.TimerOptions;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christian
 */
public class OnOffTimer extends IntervalSensor<Boolean>{
    public static final DeviceType deviceType = DeviceType.OnOffTimer;
    public static final Type cfgType = new TypeToken<Config<Boolean>>(){}.getType();

    public OnOffTimer(int id) {
	this.id = id;
        super.loadConfig(deviceType);
    }

    public OnOffTimer (int id, Boolean status, String location, String description) throws SocketException {
        super(id, status, location, description);
    }

    @Override
    public void handleMsg(String msg) {
	handleMsg(msg, deviceType, cfgType);
    }

    @Override
    public void setStatus(String status) {
       	boolean b = Boolean.valueOf(status);
	setStatus(b);
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
	fireChangeEvent();
	Message msg = new Message();
        msg.setMsgType(MessageType.statusResponse);
        msg.setSenderId(this.id);
        msg.setContent(String.valueOf(this.status));
	msg.setSenderDevice(deviceType);
	this.sendMsg(msg);
    }
    
    private void startNotifier(int intervall){
        TimeScheduler<Boolean> ts = new TimeScheduler<Boolean>(this, TimerOptions.ONOFFTIMER);
        ts.startFromTill(Boolean.TRUE, Boolean.FALSE, 0, intervall);
    }

    public static void main(String[] args){
        try {
            OnOffTimer onoff = new OnOffTimer(3000, Boolean.FALSE, "Terrasse", "bla bla");
            onoff.startNotifier(1000);
        } catch (SocketException ex) {
            Logger.getLogger(OnOffTimer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void fireChangeEvent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
