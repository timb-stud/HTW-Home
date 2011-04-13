package de.htwhome;

import de.htwhome.gui.LightFrame;
import de.htwhome.gui.PercentSwitchFrame;
import de.htwhome.gui.SunBlindFrame;
import de.htwhome.gui.SwitchFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class MainPC3 {

    public static void main(String[] args) throws SocketException, IOException {

        //Licht Wohnzimmer alle
//
        int[] alLicht2 = {12102};
        Boolean[] alStatusLicht2 = new Boolean[alLicht2.length];
        new SwitchFrame(11104, false, "Wohnzimmer", "Lichtschalter hinten", alLicht2, alStatusLicht2, 21102).setVisible(true);

        int[] gidLight = {21000, 21100, 21102};
        new LightFrame(12102, false, "Wohnzimmer", "Licht hinten", gidLight).setVisible(true);
//        Schalter Markise Wohnzimmer
        int[] gidSunBlind = {23001};
        new SunBlindFrame(12201, 50, "Wohnzimmer aussen", "Markise", gidSunBlind).setVisible(true);
//        Markisenschalter Wohnzimmer
        int[] alPercentSwitch = {12201};
        Integer[] alStatusPercentSwitch = new Integer[alPercentSwitch.length];
        new PercentSwitchFrame(11201, 50, "Wohnzimmer Markise", "PercentSwitcher", alPercentSwitch, alStatusPercentSwitch, 23001).setVisible(true);
    }
}
