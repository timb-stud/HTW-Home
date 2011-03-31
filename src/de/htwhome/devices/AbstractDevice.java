package de.htwhome.devices;

import com.google.gson.Gson;
import de.htwhome.gui.StatusChangeListener;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageDeamon;
import de.htwhome.utils.Config;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;

/**
 * Oberste Klasse. Beihaltet Funktionen welche alle Benutzen können
 * @author Christian Rech, Tim Bartsch
 */
public abstract class AbstractDevice<T> {

    protected int id;
    protected T status;
    protected String location;
    protected String description;
    protected static Gson gson = new Gson();
    private MessageDeamon msgDeamon;
    protected final CopyOnWriteArrayList<StatusChangeListener> listeners = new CopyOnWriteArrayList<StatusChangeListener>();

    /*
     * leerer Standardkonstruktor
     */
    public AbstractDevice() {
    }


    public AbstractDevice(int id, T status, String location, String description) throws SocketException {
        this.id = id;
        this.status = status;
        this.location = location;
        this.description = description;
        msgDeamon = new MessageDeamon(this);
        msgDeamon.start();
    }

    /**
     * Ladet die Konfiguration aus dem übergeben config gile
     * @param cfg
     */
    protected void loadAttributesFrom(Config cfg){
        this.id = cfg.getId();
        this.status = (T) cfg.getStatus();
        this.location = cfg.getLocation();
        this.description = cfg.getDescription();
	fireChangeEvent();
    }

    /*
     * Schreibt die Attribute in Config-File
     */
    protected Config writeAttributesTo(Config cfg){
        cfg.setId(id);
        cfg.setStatus(status);
        cfg.setLocation(location);
        cfg.setDescription(description);
	return cfg;
    }

    /**
     * Speichert die aktuelle Konfiguration
     * @param devType
     */
     public void saveConfig(DeviceType devType){
        Config cfg = new Config();
        writeAttributesTo(cfg);
	FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(devType.toString() + this.id + ".xml");
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

     /*
      * Ladet die Konfiguration
      */
    public void loadConfig(DeviceType deviceType){
        Config cfg = JAXB.unmarshal(new File(deviceType.toString() + this.id + ".xml"), Config.class);
        loadAttributesFrom(cfg);
    }

    public abstract void handleMsg(String msg);

    public abstract void handleMsg(String jsonMsg, DeviceType devType, Type cfgType);

    /*
     * Dient zum versenden eine Nachicht
     */
    public void sendMsg(Message msg) {
        try {
            String json = new Gson().toJson(msg);
            msgDeamon.sendMsg(json);
        } catch (IOException ex) {
            Logger.getLogger(Actor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Dient dazu um Observer hinzuzufügen
     */
    public void addStatusChangeListener(StatusChangeListener l) {
        this.listeners.add(l);
    }

    /*
     * Entfernt Observer
     */
    public void removeStatusChangeListener(StatusChangeListener l) {
        this.listeners.remove(l);
    }

    protected abstract void fireChangeEvent();

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

 //-----------------( Getter & Setter )------------------------->

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

}
