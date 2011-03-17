package de.htwhome.devices;

import com.google.gson.Gson;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageReceiver;
import de.htwhome.transmission.MessageSender;
import de.htwhome.utils.DeviceConfig;
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
    protected  String type; //koennte auch als Enum realisiert werden
    protected  String description;
    protected static Gson gson = new Gson();
    private MessageReceiver msgReceiver;

    public AbstractDevice() {
        
    }

    public AbstractDevice(int id, T status, String location, String type, String description) throws SocketException {
        this.id = id;
        this.status = status;
        this.location = location;
        this.type = type;
        this.description = description;
        msgReceiver = new MessageReceiver(this);
        msgReceiver.start();
    }

    protected  void load(DeviceConfig dc){
        this.id = dc.getId();
        this.status = (T) dc.getStatus();
        this.location = dc.getLocation();
        this.type = dc.getType();
        this.description = dc.getDescription();
    }

    public static DeviceConfig getConfig(){  //TODO Config file + config als attribut
        DeviceConfig config = JAXB.unmarshal(new File("config.xml"), DeviceConfig.class);
        return config;
    }

    public static void setConfig(DeviceConfig config) {
        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(("config.xml"));
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

    public abstract void handleMsg(String msg, Type msgType);

    public abstract void handleMsg(String msg);

    public void sendMsg(Message<T> msg, Type msgTyp){
        try {
            String json = new Gson().toJson(msg, msgTyp);
            System.out.println("JSON:" + json); //TODO aufraeumen
            MessageSender.sendMsg(json);
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

    public abstract void setStatus(T status);

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
