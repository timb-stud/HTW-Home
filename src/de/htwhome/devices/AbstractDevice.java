package de.htwhome.devices;

import com.google.gson.Gson;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageDeamon;
import de.htwhome.utils.Config;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class AbstractDevice<T> {
    protected int id;
    protected  T status;
    protected  String location;
    protected  String description;
    protected static Gson gson = new Gson();
    private MessageDeamon msgDeamon;
    protected static int ALLDEVICES = 20000;
    
    public AbstractDevice() {}

    public AbstractDevice(int id, T status, String location, String description) throws SocketException {
        this.id = id;
        this.status = status;
        this.location = location;
        this.description = description;
        msgDeamon = new MessageDeamon(this);
        msgDeamon.start();
    }

    protected void loadAttributesFrom(Config cfg){
        this.id = cfg.getId();
        this.status = (T) cfg.getStatus();
        this.location = cfg.getLocation();
        this.description = cfg.getDescription();
    }

    protected Config writeAttributesTo(Config cfg){
        cfg.setId(id);
        cfg.setStatus(status);
        cfg.setLocation(location);
        cfg.setDescription(description);
	return cfg;
    }

     public void saveConfig(DeviceType devType){
        Config cfg = new Config();
        writeAttributesTo(cfg);
	FileWriter filewriter = null;
        try {
            filewriter = new FileWriter((devType + ":" + this.id + ".xml"));
            JAXB.marshal(cfg, filewriter);
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

    public void loadConfig(DeviceType deviceType){
        Config cfg = JAXB.unmarshal(new File(deviceType + ":" + this.id + ".xml"), Config.class);
        loadAttributesFrom(cfg);
    }

    public abstract void handleMsg(String msg);

    public abstract void handleMsg(String jsonMsg, DeviceType devType, Type cfgType);

    public void sendMsg(Message msg){
        try {
            String json = new Gson().toJson(msg);
            // System.out.println("JSON:" + json); //TODO aufraeumen
            msgDeamon.sendMsg(json);
        } catch (IOException ex) {
            Logger.getLogger(Actor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public T getStatus() {
        return status;
    }
    
    public abstract void setStatus(String status);

    public abstract void setStatus(T status);

    @Override
    public String toString() {
	return "AbstractDevice{" + "id=" + id + "status=" + status + "location=" + location + "description=" + description + "msgReceiver=" + msgDeamon + '}';
    }

    @Override
    public boolean equals(Object obj) {
	if(obj instanceof AbstractDevice){
	    AbstractDevice dev = (AbstractDevice)obj;
	    return this.id == dev.id;
	}
	return false;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 83 * hash + this.id;
	return hash;
    }
}
