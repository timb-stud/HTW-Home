package de.htwhome.devices;

import de.htwhome.gui.panel.ConfigChangeEvent;
import de.htwhome.gui.panel.ConfigChangeListener;
import de.htwhome.utils.Config;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Tim Bartsch
 */
public class ConfigList extends CopyOnWriteArrayList<Config> {

    protected final CopyOnWriteArrayList<ConfigChangeListener> listeners = new CopyOnWriteArrayList<ConfigChangeListener>();

    public void addConfigChangeListener(ConfigChangeListener l) {
        this.listeners.add(l);
    }

    public void removeConfigChangeListener(ConfigChangeListener l) {
        this.listeners.remove(l);
    }

    protected void fireChangeEvent(Config cfg) {
	ConfigChangeEvent evt = new ConfigChangeEvent(this, cfg);
	for (ConfigChangeListener l : listeners) {
	    l.changeEventReceived(evt);
	}
    }

    public void setConfigStatus(int id, Object status){
	for(Config c: this){
	    if(c.getId() == id){
		c.setStatus(status);
		fireChangeEvent(c);
	    }
	}
    }

    public void updateConfig(Config c) {
	boolean found = false;
	for (int i = 0; i < this.size() && !found; i++) {
	    if (c.equals(this.get(i))) {
		this.set(i, c);
		found = true;
	    }
	}
	if (!found) {
	    this.add(c);
	}
	fireChangeEvent(c);
    }
}
