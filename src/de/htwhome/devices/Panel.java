package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Volkan Gökkaya, Tobias Lana
 */
public class Panel<T> extends AbstractDevice<T>{

    public static final Type msgType = new TypeToken<Message<Boolean>>(){}.getType();
    private ArrayList<AbstractDevice> deviceList;


    public Panel() {}

    public Panel(int id, T status, String location, String type, String description) throws SocketException{
        super(id, status,location, type, description);
        this.deviceList = new ArrayList<AbstractDevice>();
    }

    @Override
    public void handleMsg(String jsonMsg){
	Message<T> msg = gson.fromJson(jsonMsg, msgType);
        System.out.println("Verarbeite Nachricht vom Typ: " + msg.getMsgType());
	switch (msg.getMsgType()) {
	    case statusResponse:
		updateDevicelist(jsonMsg, msgType);
		break;
	    case configChange:
		//TODO implement
		break;
	    case configRequest:
		Message reply = new Message(MessageType.configResponse, this.id, ALLDEVICES, null, " ");
                sendMsg(reply, msgType);
		break;
            case configResponse:
                updateDevicelist(jsonMsg, msgType);
                break;
	}
    }

    @Override
    public void handleMsg(String jsonMsg, Type msgType){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setStatus(T status) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void getAllConfigs() {
        Message msg = new Message(MessageType.configRequest, this.id, ALLDEVICES, null, null);
        sendMsg(msg, msgType); //TODO msgTyp ueberpruefen
    }

    public void useDeviceFunction(int receiverId) {
        Message msg = new Message(MessageType.statusChange, this.id, receiverId, null, null);
        sendMsg(msg, msgType); //TODO msgTyp ueberpruefen
    }

    /*
     * updateDevicelist
     * @author TL
     * @param String jsonMsg
     * 
     * Funktion wertet die Nachrichten des Typs configResponse aus
     * und schreibt die Devices in die DeviceList des Panels
     */
    private void updateDevicelist(String jsonMsg, Type msgType) {
        Message<T> msg = gson.fromJson(jsonMsg, msgType);
        //Message content = gson.fromJson(msg.getJsonConfig(), msgType);
        System.out.println("ID: " + msg.getSenderId() + " hat sich gemeldet");
        System.out.println(msg.getJsonConfig());
        String msg2 = gson.fromJson(msg.getJsonConfig(), msgType);
        System.out.println(msg2);
    }

    public static void main(String[] args) throws SocketException {
        Panel p = new Panel(123, false, "Wohnzimmer", "Panel", "Megapanel");
        p.getAllConfigs();
    }

}
