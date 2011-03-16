package de.htwhome.transmission;

import java.net.*;
import java.io.*;
/**
 *
 * @author tobiaslana
 */
public class MessageReceiver {

    static final int    PORT = 1234;
    private static final int BUFFERSIZE = 256;

    public MessageReceiver() throws IOException, ClassNotFoundException{
        DatagramPacket pack = new DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
	DatagramSocket sock = new DatagramSocket(PORT);
        while (true) {
            sock.receive(pack);
            System.out.println(pack.getSocketAddress().toString());
            System.out.println(pack.getAddress().toString());
            System.out.println(new String(pack.getData(), 0, pack.getLength()));
            System.out.println("Received! Now sending back... ");
            sock.send(pack);
        }
    }
    
    

}
