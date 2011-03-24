package de.htwhome.transmission;

import de.htwhome.utils.HTWhomeProperties;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Tobias Lana, Volkan Goekkaya
 */
public class MessageSender {
    public static void sendMsg(String msg) throws IOException{

            byte[] buffer = msg.getBytes();
            MulticastSocket multiCastSocket = new MulticastSocket(HTWhomeProperties.PORT);
            InetAddress group = InetAddress.getByName(HTWhomeProperties.MULTICASTGROUP);
            multiCastSocket.joinGroup(group);

            DatagramPacket datagrampacket= new DatagramPacket(msg.getBytes(), msg.length(),group, HTWhomeProperties.PORT);
            multiCastSocket.send(datagrampacket);
            System.out.println("Sent " + new String(datagrampacket.getData(), 0, datagrampacket.getLength()));
        }

    public static void main(String[] args) throws IOException {
        for(int i=0; i < 1000; i++){
            MessageSender.sendMsg("" + i);
        }
    }
}