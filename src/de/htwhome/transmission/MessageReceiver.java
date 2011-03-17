package de.htwhome.transmission;

import de.htwhome.devices.AbstractDevice;
import de.htwhome.devices.Light;
import java.net.*;
import java.io.*;
import java.lang.reflect.Type;
/**
 *
 * @author Tobias Lana, Volkan Goekkaya
 */
public class MessageReceiver {

    static final int    PORT = 1234;
    private static final int BUFFERSIZE = 256;
    public DatagramPacket pack;
    public DatagramSocket sock;
    private AbstractDevice  device;
    
    public MessageReceiver(AbstractDevice device) throws SocketException {
        sock = new DatagramSocket(PORT);
        this.device = device;
    }
    
    public synchronized void receiver() throws IOException {
        while (true) {
            pack = new DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
            sock.receive(pack);
//            ServerThread st = new ServerThread(sock, pack);
            ServerThread st = new ServerThread(pack, device);
            st.start();
        }
    }

    public static void main(String[] args) throws SocketException, IOException {
        int[] gidTab = {1,2};
        Light l = new Light(2, true, "", "", "", gidTab);
        MessageReceiver msgr = new MessageReceiver(l);
        msgr.receiver();
    }
}