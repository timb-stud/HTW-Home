package de.htwhome.gui.panel;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tim Bartsch
 */
public class ClockThread extends Thread{

    HomePanel homePanel;

    public ClockThread(HomePanel homePanel) {
	this.homePanel = homePanel;
    }


    @Override
    public void run() {
	while(true){
	    try {
		homePanel.updateClock();
		this.sleep(1000 * 10);
	    } catch (InterruptedException ex) {
		Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

}
