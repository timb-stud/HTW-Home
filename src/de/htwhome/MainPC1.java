package de.htwhome;

import de.htwhome.gui.AnemometerFrame;
import de.htwhome.gui.LightFrame;
import de.htwhome.gui.PercentSwitchFrame;
import de.htwhome.gui.ShutterFrame;
import de.htwhome.gui.SunBlindFrame;
import de.htwhome.gui.SwitchFrame;
import de.htwhome.gui.ThermometerFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class MainPC1 {

    public static void main(String[] args) throws SocketException, IOException {

// Thermometer
        new ThermometerFrame(11501, 0.0, "Flur", "Thermometer").setVisible(true);
// Licht Flur
        int[] gidLight3 = {29001};
        new LightFrame(12110, false, "Flur", "Licht", gidLight3).setVisible(true);

    }
}
