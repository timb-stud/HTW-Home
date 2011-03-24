/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Volkan Gökkaya
 */
public class ActorRespThread extends Thread{

    AckSensor sensor;
    public ActorRespThread(AckSensor sensor){
        this.sensor = sensor;
    }

    @Override
    public void run() {
        while(sensor.checkRespones() == false){
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ActorRespThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Jetzt geht LED am Schalter an... (Sensor.switchLedON(...))");
    }

}
