package de.htwhome;

import de.htwhome.devices.DoorBell;
import de.htwhome.gui.AnemometerFrame;
import de.htwhome.gui.DoorBellFrame;
import de.htwhome.gui.DoorOpenerFrame;
import de.htwhome.gui.LightFrame;
import de.htwhome.gui.ShutterFrame;
import de.htwhome.gui.SunBlindFrame;
import de.htwhome.gui.ThermometerFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class DoorBellSzenario {
    public static void main(String[] args) throws SocketException, IOException {
        //Intervall-Sensoren
//        new AnemometerFrame(11301, 0.0, "Garten", "Windmesser", 20000).setVisible(true);
//        new ThermometerFrame(11501, 0.0, "Garten", "Thermometer", 20000).setVisible(true);
        // Klingel
        new DoorBellFrame(11601, false, "Klingel", "Haustür").setVisible(true);
        //Türöffner
        int[] gidDoorOpen = {21100, 21101};
        new DoorOpenerFrame(12501, false, "Haustür", "Türöffner", gidDoorOpen).setVisible(true);
        //Licht
//        int[] gidLight = {21100, 21101};
//        new LightFrame(12101, false, "Wohnzimmer", "Licht vorne", gidLight).setVisible(true);
//        int[] gidLight2 = {21100, 21102};
//        new LightFrame(12102, false, "Wohnzimmer", "Licht hinten", gidLight2).setVisible(true);
//        int[] gidLight3 = {29001};
//        new LightFrame(12110, false, "Eingangsbereich", "Licht", gidLight3).setVisible(true);
    }
}
