package de.htwhome.transmission;

import de.htwhome.devices.AbstractDevice;
import de.htwhome.utils.HTWhomeProperties;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias Lana, Volkan Goekkaya
 */
public class MessageDeamon extends Thread{

    public MulticastSocket sock;
    private AbstractDevice device;
    private InetAddress group;
    
    public MessageDeamon(AbstractDevice device) throws SocketException {
        try {
            sock = new MulticastSocket(HTWhomeProperties.PORT);
	    group = InetAddress.getByName(HTWhomeProperties.MULTICASTGROUP);
	    sock.joinGroup(group);
        } catch (IOException ex) {
            Logger.getLogger(MessageDeamon.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.device = device;
    }

    public void sendMsg(String msg) throws IOException {
	DatagramPacket datagrampacket = new DatagramPacket(msg.getBytes(), msg.length(), group, HTWhomeProperties.PORT);
	sock.send(datagrampacket);
	System.out.println("Sent " + new String(datagrampacket.getData(), 0, datagrampacket.getLength()));
    }
    
    @Override
    public synchronized void run(){
        System.out.println("MsgReceiver: " + device);
	try {
	    while (true) {
		DatagramPacket pack = new DatagramPacket(new byte[HTWhomeProperties.BUFFERSIZE], HTWhomeProperties.BUFFERSIZE);
		sock.receive(pack);
		ServerThread st = new ServerThread(pack, device);
		st.start();
	    }
	} catch(IOException ex){
	    Logger.getLogger(MessageDeamon.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}