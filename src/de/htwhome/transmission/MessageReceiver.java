package de.htwhome.transmission;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Tobias Lana, Volkan Goekkaya
 */
public class MessageReceiver {

    static final int    PORT = 1234;
    private static final int BUFFERSIZE = 256;
    public DatagramPacket pack;
    public DatagramSocket sock;
    
    public MessageReceiver() throws SocketException {
        sock = new DatagramSocket(PORT);
        
    }
    
    public synchronized void Receiver() throws IOException {
        while (true) {
            pack = new DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
            sock.receive(pack);
            ServerThread st = new ServerThread(sock, pack);
            st.start();
        }
    }

    public static void main(String[] args) throws SocketException {
        MessageReceiver msgr = new MessageReceiver();
    }
    
    

}

class ServerThread extends Thread {
    DatagramPacket pack;
    DatagramSocket sock;

    ServerThread(DatagramSocket sock, DatagramPacket pack) {
        this.pack = pack;
        this.sock = sock;

    }

    ServerThread(DatagramPacket pack) {
        this.pack = pack;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Start ThreadID: " + this.getId());
            Thread.sleep(5000);
            System.out.println(pack.getAddress().toString());
            System.out.println(new String(pack.getData(), 0, pack.getLength()));
            try {
                sock.send(pack);
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("ENDE ThreadID: " + this.getId());
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
