/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome;

import de.htwhome.devices.OnOffTimer;
import de.htwhome.devices.SmokeDetector;
import de.htwhome.devices.Switch;
import de.htwhome.gui.LightFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author christian
 */
public class StressTest {

    public static void main(String[] args) throws SocketException, IOException{
//        OnOffTimer onoff = new OnOffTimer(3000, Boolean.TRUE, "Test", "Stresstest");
//        onoff.startNotifier(500);

        int[] gidLight = {22000, 21100, 21102};
        for ( int i = 0; i < 60; i++){
            new LightFrame(i, false, "Wohnzimmer", "Licht hinten", gidLight).setVisible(true);
        }

    }

}
