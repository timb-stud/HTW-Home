/*
 * PanelFrame.java
 *
 * Created on 29.03.2011, 12:44:06
 */

package de.htwhome.gui.panel;

import de.htwhome.devices.Panel;
import java.awt.Component;
import java.awt.Image;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

/**
 *
 * @author Tim Bartsch
 */
public class PanelFrame extends javax.swing.JFrame {

    Panel panel;

    /** Creates new form PanelFrame */
    public PanelFrame() {
        initComponents();
	try {
	    panel = new Panel(1337, false, "Wohnzimmer", "Panel");
	    ConfigPanel cfgPanel = new ConfigPanel(panel);
	    panel.addConfigChangeListener(cfgPanel);
	    LightPanel lightPanel= new LightPanel(panel);
	    panel.addConfigChangeListener(lightPanel);
	    DoorPanel doorPanel = new DoorPanel(panel);
	    EmergencyPanel emergencyPanel = new EmergencyPanel(panel);
	    HomePanel homePanel = new HomePanel(panel);
	    panel.addConfigChangeListener(homePanel);
	    panel.addAlarmListener(homePanel);
	    tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
	    addTab(homePanel, "button_info.png");
	    addTab(lightPanel, "button_lampe.png");
	    addTab(doorPanel, "button_door_open.png");
	    addTab(cfgPanel, "button_einstellungen.png");
	    addTab(emergencyPanel, "button_emergency.png");
	    panel.getAllConfigs();
	} catch (SocketException ex) {
	    Logger.getLogger(PanelFrame.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void addTab(Component c, String icon){
	ImageIcon image = createImageIcon(icon);
	Image img = image.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	image = new ImageIcon(img);
	tabbedPane.addTab("", image, c);
    }

    protected static ImageIcon createImageIcon(String path) {
	java.net.URL imgURL = PanelFrame.class.getResource(path);
	if (imgURL != null) {
	    return new ImageIcon(imgURL);
	} else {
	    System.err.println("Couldn't find file: " + path);
	    return null;
	}
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

}
