/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.transmission;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author tobiaslana
 */
public class MessageSender {


    public static void sendMsg(String msg) throws IOException{
            DatagramSocket datagramSocket = new DatagramSocket();
            byte[] buffer = msg.getBytes();
            InetAddress receiverAddress = InetAddress.getByName("192.168.123.255");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, 1234);
            datagramSocket.send(packet);
            datagramSocket.receive(packet);
            System.out.println("Sent and received: " + new String(packet.getData(), 0, packet.getLength()));
       
        }
    }
