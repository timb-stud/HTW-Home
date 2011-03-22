package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.utils.SensorConfig;
import java.lang.reflect.Type;

/**
 *
 * @author Tim Bartsch
 */
public class SmokeDetector extends Sensor<Boolean>{

    public static final DeviceType deviceType = DeviceType.SmokeDetector;
    public static final Type cfgType = new TypeToken<SensorConfig<Boolean>>(){}.getType();

    @Override
    public void setActorStatus(String status, int pos) {
	throw new UnsupportedOperationException("Not supported yet.");
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
	if(this.status == true){
	    
	}
    }
    
}
