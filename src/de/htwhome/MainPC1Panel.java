package de.htwhome;

import de.htwhome.gui.panel.PanelFrame;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author tobiaslana
 */
public class MainPC1Panel {

    public static void main(String[] args) throws SocketException, IOException {
        new PanelFrame().setVisible(true);
    }

}
