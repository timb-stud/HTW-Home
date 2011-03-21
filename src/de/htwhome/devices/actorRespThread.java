/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

/**
 *
 * @author Volkan Gökkaya
 */
public class actorRespThread extends Thread{

    Sensor sensor;
    public actorRespThread(Sensor sensor){
        this.sensor = sensor;
    }

    @Override
    public void run() {
        while(sensor.checkRespones() == false);
        System.out.println("Jetzt geht LED am Schalter an... (Sensor.switchLedON(...))");
    }

}
