package de.htwhome.gui.panel;

import javax.swing.JList;
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
	Object obj = e.getSource();
	if(obj instanceof JList){
	    JList list = (JList)obj;
	    int index = list.getSelectedIndex();
	    if(index >= 0){
		configPanel.showConfig(index);
	    }
	}

    }

}
