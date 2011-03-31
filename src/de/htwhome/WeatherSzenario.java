package de.htwhome;

import de.htwhome.gui.AnemometerFrame;
import de.htwhome.gui.ShutterFrame;
import de.htwhome.gui.SunBlindFrame;
import de.htwhome.gui.ThermometerFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class WeatherSzenario {
    public static void main(String[] args) throws SocketException, IOException {
        //Intervall-Sensoren
        new AnemometerFrame(11301, 0.0, "Garten", "Windmesser").setVisible(true);
        new ThermometerFrame(11501, 0.0, "Garten", "Thermometer").setVisible(true);
        //ACK Sensoren

        // Rollläden
        int[] gidShutter1 = {22100, 22101};
        new ShutterFrame(12301, 30, "Wohnzimmer vorne", "Rollladen", gidShutter1).setVisible(true);
        int[] gidShutter2 = {22100, 22102};
        new ShutterFrame(12302, 30, "Wohnzimmer hinten", "Rollladen", gidShutter2).setVisible(true);
        int[] gidShutter3 = {22103};
        new ShutterFrame(12301, 30, "Schlafzimmer", "Rollladen", gidShutter3).setVisible(true);
        int[] gidShutter4 = {22104};
        new ShutterFrame(12302, 30, "Küche", "Rollladen", gidShutter4).setVisible(true);
        // Markisen
        int[] gidSunBlind = {23001};
        new SunBlindFrame(12201, 50, "Wohnzimmer aussen", "Markise", gidSunBlind).setVisible(true);
    }
}
