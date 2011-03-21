package de.htwhome.devices;

import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageType;
import java.lang.reflect.Type;
import java.net.SocketException;

/**
 *
 * @author Volkan Gökkaya, Tobias Lana
 */
public class Panel<T> extends AbstractDevice<T>{

    public static final Type msgType = new TypeToken<Message<Boolean>>(){}.getType();
    public static final DeviceType deviceType = DeviceType.Panel;

    public Panel() {}

    public Panel(int id, T status, String location, String hint, int[] gidTab) throws SocketException{
        super(id, status,location, hint);
    }

    @Override
    public void handleMsg(String jsonMsg){
	Message<T> msg = gson.fromJson(jsonMsg, msgType);
        System.out.println("Verarbeite Nachricht vom Typ: " + msg.getMsgType());
	switch (msg.getMsgType()) {
	    case statusRequest:
		//TODO implement
		break;
	    case statusResponse:
		//TODO implement
		break;
	    case configChange:
		//TODO implement
		break;
	    case configRequest:
		Message reply = new Message(MessageType.configResponse, this.id, ALLDEVICES, null, this.toString());
                sendMsg(reply, msgType);
		break;
            case configResponse:
                addDeviceToList(jsonMsg, msgType);
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
        sendMsg(msg, msgType);
    }

    /*
     * addDeviceToList
     * @author TL
     * @param String jsonMsg
     * 
     * Funktion wertet die Nachrichten des Typs configResponse aus
     * und schreibt die Devices in die DeviceList des Panels
     */
    private void addDeviceToList(String jsonMsg, Type msgType) {
        Message<T> msg = gson.fromJson(jsonMsg, msgType);
        System.out.println("ID: " + msg.getSenderId() + " hat sich gemeldet");
    }

    public static void main(String[] args) throws SocketException {
        int[] gid  = {1};
        Panel p = new Panel(123, false, "Wohnzimmer", "Megapanel", gid);
        p.getAllConfigs();
    }

}
