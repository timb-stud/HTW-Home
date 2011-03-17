package de.htwhome.transmission;

import de.htwhome.devices.AbstractDevice;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        try {
            System.out.println("Start ThreadID: " + this.getId());
            Thread.sleep(5000);
            System.out.println(pack.getAddress().toString());
            String msg = new String(pack.getData(), 0, pack.getLength());
            this.device.handleMsg(msg);
            System.out.println("ENDE ThreadID: " + this.getId());
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}