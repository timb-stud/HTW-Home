/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwhome;

import de.htwhome.gui.AnemometerFrame;
import de.htwhome.gui.LightFrame;
import de.htwhome.gui.PercentSwitchFrame;
import de.htwhome.gui.ShutterFrame;
import de.htwhome.gui.SmokeDetectorFrame;
import de.htwhome.gui.SunBlindFrame;
import de.htwhome.gui.SwitchFrame;
import de.htwhome.gui.ThermometerFrame;
import java.net.SocketException;

/**
 *
 * @author timo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException {
        //Intervall-Sensoren
//        new AnemometerFrame(11301, 0.0, "Garten", "Windmesser", 20000).setVisible(true);
//        new ThermometerFrame(11501, 0.0, "Garten", "Thermometer", 20000).setVisible(true);
//        new SmokeDetectorFrame(30, false, "Wohnzimmerdecke", "rauch hilfee").setVisible(true);
        //ACK Sensoren
        //Wohnzimmer Markise Schalter
//        int[] alPercentSwitch = {12201};
//        Integer[] alStatusPercentSwitch = new Integer[alPercentSwitch.length];
//        new PercentSwitchFrame(11201, 50, "Wohnzimmer Markise", "PercentSwitcher", alPercentSwitch, alStatusPercentSwitch, 23001).setVisible(true);
//        //Wohnzimmer Rollladen Schalter
//        int[] alPercentSwitch2 = {12301};
//        Integer[] alStatusPercentSwitch2 = new Integer[alPercentSwitch2.length];
//        new PercentSwitchFrame(11202, 30, "Wohnzimmer Rollladen", "PercentSwitcher", alPercentSwitch2, alStatusPercentSwitch2, 22101).setVisible(true);
//        //Klingel Schalter
//        int[] alKlingel = {21110};
//        Boolean[] alStatusKlingel = new Boolean[alKlingel.length];
//        new SwitchFrame(11101, false, "Haustür", "Klingel", alKlingel, alStatusKlingel, 29001).setVisible(true);
        //Licht Wohnzimmer alle
        int[] alLicht1 = {12101, 12102};
        Boolean[] alStatusLicht1 = new Boolean[alLicht1.length];
        new SwitchFrame(11102, false, "Wohnzimmer", "Licht alle", alLicht1, alStatusLicht1, 21100).setVisible(true);

//        int[] alLicht2 = {12101};
//        Boolean[] alStatusLicht2 = new Boolean[alLicht2.length];
//        new SwitchFrame(11102, false, "Wohnzimmer", "Licht vorne", alLicht2, alStatusLicht2, 21101).setVisible(true);

        //Aktoren
        // new DoorOpenerFrame(12501, false, "Eingangstür", "Türöffner", gid).setVisible(true);
        int[] gidLight = {21100, 21101};
        new LightFrame(12101, false, "Wohnzimmer", "Licht vorne", gidLight).setVisible(true);

        int[] gidLight2 = {21100, 21102};
        new LightFrame(12102, false, "Wohnzimmer", "Licht hinten", gidLight2).setVisible(true);
//
//        int[] gidLight3 = {29001};
//        new LightFrame(12110, false, "Eingansbereich", "Licht", gidLight3).setVisible(true);
//
//        int[] gidShutter = {22101};
//        new ShutterFrame(12301, 30, "Wohnzimmer aussen", "Rollladen", gidShutter).setVisible(true);
//        int[] gidSunBlind = {23001};
//        new SunBlindFrame(12201, 50, "Wohnzimmer aussen", "Markise", gidSunBlind).setVisible(true);
    }
}
