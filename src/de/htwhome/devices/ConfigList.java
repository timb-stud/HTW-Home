package de.htwhome.devices;

import de.htwhome.utils.Config;
import java.util.ArrayList;

/**
 *
 * @author Tim Bartsch
 */
public class ConfigList extends ArrayList<Config> {

    public void setConfigStatus(int id, Object status){
	for(Config c: this){
	    if(c.getId() == id){
		c.setStatus(status);
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
    }
}
