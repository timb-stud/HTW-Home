package de.htwhome;

import de.htwhome.gui.SmokeDetectorFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class MainPC4Smoke {

    public static void main(String[] args) throws SocketException, IOException {
        new SmokeDetectorFrame(11402, true, "Küche", "Rauchmelder").setVisible(true);
    }

}
