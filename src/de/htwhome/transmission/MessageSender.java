package de.htwhome.transmission;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Tobias Lana, Volkan Goekkaya
 */
public class MessageSender {


    public static void sendMsg(String msg) throws IOException{
            DatagramSocket datagramSocket = new DatagramSocket();
            byte[] buffer = msg.getBytes();
            InetAddress receiverAddress = InetAddress.getByName("192.168.123.255");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, 1234);
            datagramSocket.send(packet);
            System.out.println("Sent " + new String(packet.getData(), 0, packet.getLength()));
//            datagramSocket.receive(packet);
//            System.out.println("Received: " + new String(packet.getData(), 0, packet.getLength()));
        }

    public static void main(String[] args) throws IOException {
        for(int i=0; i < 1000; i++){
            MessageSender.sendMsg("" + i);
        }
    }
}