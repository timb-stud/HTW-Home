package de.htwhome.transmission;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Tobias Lana, Volkan Goekkaya
 */
public class MessageSender {
    static final int    PORT = 1234;

    public static void sendMsg(String msg) throws IOException{

            byte[] buffer = msg.getBytes();
            MulticastSocket multiCastSocket = new MulticastSocket(PORT);
            InetAddress group = InetAddress.getByName("228.5.6.7");

            DatagramPacket datagrampacket= new DatagramPacket(msg.getBytes(), msg.length(),group, PORT);
             multiCastSocket.send(datagrampacket);
             System.out.println("Sent " + new String(datagrampacket.getData(), 0, datagrampacket.getLength()));
        }

    public static void main(String[] args) throws IOException {
        for(int i=0; i < 1000; i++){
            MessageSender.sendMsg("" + i);
        }
    }
}