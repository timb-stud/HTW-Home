package de.htwhome;

import de.htwhome.gui.LightFrame;
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
        int[] gidLight3 = {21000, 21110};
        new LightFrame(12110, false, "Flur", "Licht", gidLight3).setVisible(true);
// Lichtschalter Flur
        int[] alLicht1 = {12110};
        Boolean[] alStatusLicht1 = new Boolean[alLicht1.length];
        new SwitchFrame(11105, false, "Flur", "Licht Flur", alLicht1, alStatusLicht1, 21110).setVisible(true);
    }
}
