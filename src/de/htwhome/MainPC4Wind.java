package de.htwhome;

import de.htwhome.gui.AnemometerFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class MainPC4Wind {

    public static void main(String[] args) throws SocketException, IOException {
        new AnemometerFrame(11402, 0.0, "Garten", "Windmesser").setVisible(true);
    }

}