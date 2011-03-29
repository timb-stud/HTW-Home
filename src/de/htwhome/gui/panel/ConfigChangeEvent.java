package de.htwhome.gui.panel;

import de.htwhome.utils.Config;
import java.util.EventObject;

/**
 *
 * @author Tim Bartsch
 */
public class ConfigChangeEvent extends EventObject {

    private Config config;

    public ConfigChangeEvent(Object source, Config cfg) {
	super(source);
	this.config = cfg;
    }

    public Config getConfig() {
	return config;
    }

    public void setConfig(Config config) {
	this.config = config;
    }

    @Override
    public String toString() {
	return "ConfigChangeEvent{" + "config=" + config + '}';
    }
    
}
