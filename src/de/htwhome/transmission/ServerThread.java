package de.htwhome.transmission;

import de.htwhome.devices.AbstractDevice;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 *
 * @author Tobias Lana, Volkan Goekkaya
 */
class ServerThread extends Thread {
    DatagramPacket pack;
    DatagramSocket sock;
    AbstractDevice device;

    ServerThread(DatagramSocket sock, DatagramPacket pack, AbstractDevice device) {
        this.pack = pack;
        this.sock = sock;
        this.device = device;
    }

    ServerThread(DatagramPacket pack, AbstractDevice device) {
        this.pack = pack;
        this.device = device;
    }

    @Override
    public void run() {
	String msg = new String(pack.getData());
	msg = msg.trim();
	System.out.println("Received: " + msg);
	this.device.handleMsg(msg);
    }
}