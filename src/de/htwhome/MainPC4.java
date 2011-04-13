package de.htwhome;

import de.htwhome.gui.LightFrame;
import de.htwhome.gui.PercentSwitchFrame;
import de.htwhome.gui.ShutterFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class MainPC4 {

    public static void main(String[] args) throws SocketException, IOException {

        int[] gidLight4 = {21000, 21120};
        new LightFrame(12120, false, "Küche", "Licht", gidLight4).setVisible(true);
//        Schalter Rollladen vorne
        int[] alPercentSwitch4 = {12301};
        Integer[] alStatusPercentSwitch4 = new Integer[alPercentSwitch4.length];
        new PercentSwitchFrame(11202, 50, "Küche Rollladen", "PercentSwitcher", alPercentSwitch4, alStatusPercentSwitch4, 22104).setVisible(true);
//        Rollladen vorne
        int[] gidShutter4 = {22104};
        new ShutterFrame(12304, 50, "Küche", "Rollladen", gidShutter4).setVisible(true);
//        new SmokeDetectorFrame(11402, true, "Küche", "Rauchmelder").setVisible(true);
    }
}
