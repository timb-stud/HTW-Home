package de.htwhome;

import de.htwhome.gui.AnemometerFrame;
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
public class FireSzenario {

    public static void main(String[] args) throws SocketException, IOException {
        //Rauchmelder
        new AnemometerFrame(11301, 0.0, "Garten", "Windmesser", 20000).setVisible(true);
        new ThermometerFrame(11501, 0.0, "Garten", "Thermometer", 20000).setVisible(true);
        //Licht
        int[] gidLight = {21100, 21101};
        new LightFrame(12101, false, "Wohnzimmer", "Licht vorne", gidLight).setVisible(true);
        int[] gidLight2 = {21100, 21102};
        new LightFrame(12102, false, "Wohnzimmer", "Licht hinten", gidLight2).setVisible(true);
        int[] gidLight3 = {29001};
        new LightFrame(12110, false, "Eingansbereich", "Licht", gidLight3).setVisible(true);
        // Rollläden und Markisen
        int[] gidShutter1 = {22100, 22101};
        new ShutterFrame(12301, 30, "Wohnzimmer vorne", "Rollladen", gidShutter1).setVisible(true);
        int[] gidShutter2 = {22100, 22101};
        new ShutterFrame(12302, 30, "Wohnzimmer hinten", "Rollladen", gidShutter2).setVisible(true);
        int[] gidShutter3 = {22103};
        new ShutterFrame(12301, 30, "Schlafzimmer", "Rollladen", gidShutter3).setVisible(true);
        int[] gidShutter4 = {22104};
        new ShutterFrame(12302, 30, "Küche", "Rollladen", gidShutter4).setVisible(true);
        int[] gidSunBlind = {23001};
        new SunBlindFrame(12201, 50, "Wohnzimmer aussen", "Markise", gidSunBlind).setVisible(true);
    }

}
