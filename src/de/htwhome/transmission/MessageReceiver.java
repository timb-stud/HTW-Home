package de.htwhome.transmission;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            ServerThread st = new ServerThread(sock,pack);
            st.start();
        }
    }
    
    

}

class ServerThread extends Thread {
    DatagramPacket pack;
    DatagramSocket sock;

    ServerThread(DatagramSocket sock, DatagramPacket pack) {
        this.pack = pack;
        this.sock = sock;

    }
    @Override
    public void run() {
        try {
            System.out.println(pack.getSocketAddress().toString());
            System.out.println(pack.getAddress().toString());
            System.out.println(new String(pack.getData(), 0, pack.getLength()));
            System.out.println("Received! Now sending back... ");
            sock.send(pack);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
