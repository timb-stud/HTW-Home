/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome;

import de.htwhome.devices.OnOffTimer;
import de.htwhome.gui.LightFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class MainPC4Stress {

    public static void main(String[] args) throws SocketException, IOException{
        int[] gidLight = {22000, 21100, 21102};
        for ( int i = 41; i < 60; i++){
            new LightFrame(i, false, "Kinderzimmer", "Lampen", gidLight).setVisible(true);
        }

    }

}
