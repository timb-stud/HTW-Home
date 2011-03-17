package de.htwhome.devices;

import com.google.gson.Gson;
import de.htwhome.transmission.MessageReceiver;
import de.htwhome.utils.Config;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import javax.xml.bind.JAXB;

/**
 *
 * @author Christian Rech, Tim Bartsch
 */
public abstract class AbstractDevice<T> {
    protected int id;
    protected  T status;
    private String location;
    private String type; //koennte auch als Enum realisiert werden
    private String description;
    protected static Gson gson = new Gson();
    private MessageReceiver msgReceiver;

    public AbstractDevice(int id, T status, String location, String type, String description) throws SocketException {
        this.id = id;
        this.status = status;
        this.location = location;
        this.type = type;
        this.description = description;
        msgReceiver = new MessageReceiver(this);
    }

    public Config getConfig(){  //TODO Config file + config als attribut
        Config config = JAXB.unmarshal(new File("config" + id + ".xml"), Config.class);
        return config;
    }

    public void setConfig(Config config) {
        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(("config" + id + ".xml"));
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
