package de.htwhome.gui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Tim Bartsch
 */
public class SwitchActionListener implements ActionListener{

    SwitchPanel switchPanel;

    public SwitchActionListener(SwitchPanel switchPanel) {
	this.switchPanel = switchPanel;
    }

    public void actionPerformed(ActionEvent e) {
	switchPanel.btnClick();
    }

}
