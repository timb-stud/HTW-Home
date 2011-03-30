/*
 * PanelFrame.java
 *
 * Created on 29.03.2011, 12:44:06
 */

package de.htwhome.gui.panel;

import de.htwhome.devices.Panel;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	    panel.getAllConfigs();
	    tabbedPane.add(cfgPanel);
	    LightPanel lightPanel= new LightPanel(panel);
	    tabbedPane.add(lightPanel);
	    panel.addConfigChangeListener(lightPanel);
	} catch (SocketException ex) {
	    Logger.getLogger(PanelFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
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
