/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.gui.panel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Tim Bartsch
 */
public class ConfigListSelectionListener implements ListSelectionListener{
    ConfigPanel configPanel;

    public ConfigListSelectionListener(ConfigPanel configPanel) {
	this.configPanel = configPanel;
    }

    public void valueChanged(ListSelectionEvent e) {
	int index = e.getFirstIndex();
	configPanel.showConfig(index);
    }

}
