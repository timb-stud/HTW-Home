package de.htwhome.transmission;

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
    public DatagramSocket sock;
    
    public MessageReceiver() throws SocketException {
        sock = new DatagramSocket(PORT);
    }
    
    @Override
    public synchronized void run(){
        while (true) {
            try {
                pack = new DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
                sock.receive(pack);
                //            ServerThread st = new ServerThread(sock, pack);
                ServerThread st = new ServerThread(pack);
                st.start();
            } catch (IOException ex) {
                Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws SocketException, IOException {
        MessageReceiver msgr = new MessageReceiver();
    }
}