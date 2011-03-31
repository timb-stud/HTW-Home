/*
 * HomePanel.java
 *
 * Created on 30.03.2011, 15:00:37
 */

package de.htwhome.gui.panel;

import de.htwhome.devices.AlarmEvent;
import de.htwhome.devices.AlarmListener;
import de.htwhome.devices.DeviceType;
import de.htwhome.devices.Panel;
import de.htwhome.utils.Config;
import java.awt.Toolkit;
import java.util.Date;

/**
 *
 * @author tim
 */
public class HomePanel extends javax.swing.JPanel implements ConfigChangeListener, AlarmListener {
    Panel panel;
    PanelFrame panelFrame;
    ClockThread clockThread;

    /** Creates new form HomePanel */
    public HomePanel(Panel panel, PanelFrame panelFrame) {
	this.panel = panel;
	this.panelFrame = panelFrame;
	clockThread = new ClockThread(this);
        initComponents();
	clockThread.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        thermometerLabel = new javax.swing.JLabel();
        anemomenterLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        lightStatusLabel = new javax.swing.JLabel();
        resetStatusButton = new javax.swing.JButton();

        thermometerLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        thermometerLabel.setText("Temperatur:");

        anemomenterLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        anemomenterLabel.setText("Wingeschwindigkeit:");

        timeLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
        timeLabel.setText("12:45");

        statusLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 0, 0));

        lightStatusLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        lightStatusLabel.setText("Brennende Lampen: ");

        resetStatusButton.setText("Warnungen zurücksetzen");
        resetStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetStatusButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lightStatusLabel))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(245, 245, 245)
                            .addComponent(statusLabel))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(thermometerLabel)
                                .addComponent(anemomenterLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 290, Short.MAX_VALUE)
                            .addComponent(timeLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(resetStatusButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(thermometerLabel)
                        .addGap(18, 18, 18)
                        .addComponent(anemomenterLabel)
                        .addGap(105, 105, 105)
                        .addComponent(statusLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(resetStatusButton)
                        .addGap(18, 18, 18)
                        .addComponent(lightStatusLabel)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetStatusButtonActionPerformed
	statusLabel.setText("");
	panel.setFirealarm(false);
	panel.setWeatheralarm(false);
    }//GEN-LAST:event_resetStatusButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anemomenterLabel;
    private javax.swing.JLabel lightStatusLabel;
    private javax.swing.JButton resetStatusButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel thermometerLabel;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables

    public void changeEventReceived(ConfigChangeEvent evt) {
	Config cfg = evt.getConfig();
	if (cfg.getDeviceType() == DeviceType.Anemometer) {
	    anemomenterLabel.setText("Windgeschwindigkeit: " + cfg.getStatus() + "km/h");
	} else {
	    if (cfg.getDeviceType() == DeviceType.Thermometer) {
		thermometerLabel.setText("Temperatur: " + cfg.getStatus() + "°C");
	    } else {
		if (cfg.getDeviceType() == DeviceType.Light) {
		    int counter = 0;
		    for (Config c : panel.getConfigList()) {
			if (c.getDeviceType() == DeviceType.Light) {
			    if ((Boolean) c.getStatus() == true) {
				counter++;
			    }
			}
		    }
		    lightStatusLabel.setText("Brennende Lampen: " + counter);
		}
	    }
	}
    }

    /*
     * Aktualisiert die Uhrzeit auf dem Panel
     */
    public void updateClock(){
	Date d = new Date();
	int h = d.getHours();
	String hs;
	int m = d.getMinutes();
	String ms;
	if(h < 10){
	    hs = "0" + h;
	}else{
	    hs = String.valueOf(h);
	}
	if(m < 10){
	    ms = "0" + m;
	}else{
	    ms = String.valueOf(m);
	}
	timeLabel.setText(hs + ":" + ms);
    }

    public void alarmEventReceived(AlarmEvent evt) {
	Object obj = evt.getSource();
	if (obj instanceof Panel) {
	    Panel p = (Panel) obj;
	    if (p.isRingalarm()) {
		Toolkit.getDefaultToolkit().beep();
		panelFrame.changeTab(2);
	    } else {
		if (p.isWeatheralarm()) {
		    Toolkit.getDefaultToolkit().beep();
		    statusLabel.setText("WETTERALARM");
		} else {
		    if (p.isFirealarm()) {
			Toolkit.getDefaultToolkit().beep();
			statusLabel.setText("FEUERALARM");
		    }
		}
	    }
	}
    }

}
