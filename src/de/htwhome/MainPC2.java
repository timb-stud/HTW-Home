package de.htwhome;

import de.htwhome.gui.LightFrame;
import de.htwhome.gui.PercentSwitchFrame;
import de.htwhome.gui.ShutterFrame;
import de.htwhome.gui.SwitchFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class MainPC2 {

    public static void main(String[] args) throws SocketException, IOException {
        //Intervall-Sensoren
        //Licht Wohnzimmer alle
        int[] alLicht1 = {12101, 12102};
        Boolean[] alStatusLicht1 = new Boolean[alLicht1.length];
        new SwitchFrame(11102, false, "Wohnzimmer", "Licht alle", alLicht1, alStatusLicht1, 21100).setVisible(true);
//
        int[] alLicht2 = {12101};
        Boolean[] alStatusLicht2 = new Boolean[alLicht2.length];
        new SwitchFrame(11103, false, "Wohnzimmer", "Lichtschalter vorne", alLicht2, alStatusLicht2, 21101).setVisible(true);

        int[] gidLight = {21000, 21100, 21101};
        new LightFrame(12101, false, "Wohnzimmer", "Licht vorne", gidLight).setVisible(true);
//        Schalter Rollladen vorne 
        int[] alPercentSwitch2 = {12301};
        Integer[] alStatusPercentSwitch2 = new Integer[alPercentSwitch2.length];
        new PercentSwitchFrame(11202, 50, "Wohnzimmer Rollladen", "PercentSwitcher", alPercentSwitch2, alStatusPercentSwitch2, 22101).setVisible(true);
//        Rollladen vorne
        int[] gidShutter = {22101};
        new ShutterFrame(12301, 50, "Wohnzimmer vorne", "Rollladen", gidShutter).setVisible(true);
    }
}
