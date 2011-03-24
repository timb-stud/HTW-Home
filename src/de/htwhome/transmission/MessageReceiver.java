package de.htwhome.transmission;

import de.htwhome.devices.AbstractDevice;
import de.htwhome.devices.Light;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias Lana, Volkan Goekkaya
 */
public class MessageReceiver extends Thread{

    static final int    PORT = 1234;
    private static final int BUFFERSIZE = 256;
    public DatagramPacket pack;
    public MulticastSocket sock;
    private AbstractDevice  device;
    
    public MessageReceiver(AbstractDevice device) throws SocketException {
        try {
            sock = new MulticastSocket(PORT);
        } catch (IOException ex) {
            Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.device = device;
    }
    
    @Override
    public synchronized void run(){
        System.out.println("MsgReceiver: " + device);
        while (true) {
            try {
                pack = new DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
                sock.receive(pack);
                ServerThread st = new ServerThread(pack, device);
                st.start();
            } catch (IOException ex) {
                Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws SocketException, IOException {
        int[] gidTab = {1,2};
        Light l = new Light(2, true, "", "", gidTab);
        MessageReceiver msgr = new MessageReceiver(l);
        msgr.start();
    }
}