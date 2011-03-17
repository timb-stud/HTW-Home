package de.htwhome.transmission;

import java.net.*;
import java.io.*;
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