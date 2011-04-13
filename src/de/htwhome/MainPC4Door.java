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
public class MainPC4Door {

    public static void main(String[] args) throws SocketException, IOException {
        // Klingel
        new DoorBellFrame(11601, false, "Klingel", "Haustür").setVisible(true);
        //Türöffner
        int[] gidDoorOpen = {21100, 21101};
        new DoorOpenerFrame(12501, false, "Haustür", "Türöffner", gidDoorOpen).setVisible(true);    }

}
