package de.htwhome.gui.panel;

import de.htwhome.devices.DeviceType;
import de.htwhome.devices.Panel;
import de.htwhome.utils.Config;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Tim Bartsch
 */
public class LightPanel extends JPanel implements ConfigChangeListener{

    Panel panel;

    public LightPanel(Panel panel) {
	this.panel = panel;
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void changeEventReceived(ConfigChangeEvent evt) {
	Config cfg = evt.getConfig();
	boolean found = false;
	if(cfg.getDeviceType() == DeviceType.Switch){
	    for(Component c: this.getComponents()){
		if(c instanceof SwitchPanel){
		    SwitchPanel sp = (SwitchPanel)c;
		    if(sp.getId() == cfg.getId()){
			sp.setText(cfg.getLocation(), cfg.getDescription());
			sp.setBtnStatus((Boolean)cfg.getStatus());
			found = true;
		    }
		}
	    }
	    if(!found){
		add(new SwitchPanel(cfg.getLocation(), cfg.getDescription(), (Boolean)cfg.getStatus(), cfg.getId(), panel));
	    }
	}

    }
    
}
