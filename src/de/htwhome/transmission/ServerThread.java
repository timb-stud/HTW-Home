package de.htwhome.transmission;

import java.io.IOException;
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
            String adress = pack.getAddress().toString();
            String msg = new String(pack.getData(), 0, pack.getLength());
            System.out.println(adress);
            System.out.println(msg);
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