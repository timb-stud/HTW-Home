/*
 * SwitchFrame.java
 *
 * Created on 19.03.2011, 22:35:32
 */
package de.htwhome.gui;

import de.htwhome.devices.Switch;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author volkan
 */
public class SwitchFrame extends javax.swing.JFrame implements StatusChangeListener {

    private Switch s;

    public SwitchFrame(int id, Boolean status, String location, String description, int[] actorIdTab, Boolean[] actorStatusTab, int gid) {
        initComponents();
        try {
            s = new Switch(id, status, location, description, actorIdTab, actorStatusTab, gid);
            s.addStatusChangeListener(this);
        } catch (SocketException ex) {
            Logger.getLogger(SwitchFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jLabel2.setText(s.getDescription() + " : " + s.getLocation());
        StringBuffer sb = new StringBuffer();
        sb = sb.append("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OnOffButton = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OnOffButton.setText("Switch");
        OnOffButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OnOffButtonMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/rot.png"))); // NOI18N

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(OnOffButton, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(46, 46, 46)
                .addComponent(OnOffButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        OnOffButton.getAccessibleContext().setAccessibleName("OnOffButton");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OnOffButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OnOffButtonMouseClicked
        s.setStatus(OnOffButton.isSelected());
        if (!OnOffButton.isSelected()) {
            s.setStatusLED(false);
        }
    }//GEN-LAST:event_OnOffButtonMouseClicked
    public void changeEventReceived(StatusChangeEvent evt) {
        if (s.getStatusLED()) {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/gruen.png")));
        } else {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/rot.png")));
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                int[] actorListId = {12};
                Boolean[] actorListStatus = new Boolean[actorListId.length];
                new SwitchFrame(20, false, "Haustür", "Klingel", actorListId, actorListStatus, 1).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton OnOffButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
