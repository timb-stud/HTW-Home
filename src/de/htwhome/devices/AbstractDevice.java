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
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class AbstractDevice<T> {

    protected int id;
    protected T status;
    protected String location;
    protected String description;
    protected static Gson gson = new Gson();
    private MessageDeamon msgDeamon;
    protected static int ALLDEVICES = 20000;
    protected final CopyOnWriteArrayList<StatusChangeListener> listeners = new CopyOnWriteArrayList<StatusChangeListener>();

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

    protected void load(Config dc) {
        this.id = dc.getId();
        this.status = (T) dc.getStatus();
        this.location = dc.getLocation();
        this.description = dc.getDescription();
    }

    protected void save(Config dc) {
        dc.setId(id);
        dc.setStatus(status);
        dc.setLocation(location);
        dc.setDescription(description);
    }

    public static Config getConfig(String filename) {
        Config config = JAXB.unmarshal(new File(filename + ".xml"), Config.class);
        return config;
    }

    public static void setConfig(Config config, String filename) {
        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter((filename + ".xml"));
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

    public abstract void handleMsg(String msg);

    public abstract void handleMsg(String jsonMsg, DeviceType devType, Type cfgType);

    public void sendMsg(Message msg) {
        try {
            String json = new Gson().toJson(msg);
            // System.out.println("JSON:" + json); //TODO aufraeumen
            msgDeamon.sendMsg(json);
        } catch (IOException ex) {
            Logger.getLogger(Actor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addStatusChangeListener(StatusChangeListener l) {
        this.listeners.add(l);
    }

    public void removeStatusChangeListener(StatusChangeListener l) {
        this.listeners.remove(l);
    }

    protected abstract void fireChangeEvent();

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

    public void setStatus(T status) {
        this.status = status;
        fireChangeEvent();
    }

    @Override
    public String toString() {
        return "AbstractDevice{" + "id=" + id + "status=" + status + "location=" + location + "description=" + description + "msgReceiver=" + msgDeamon + '}';
    }
}
