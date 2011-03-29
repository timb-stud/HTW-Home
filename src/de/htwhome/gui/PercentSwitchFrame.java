package de.htwhome.gui;

import de.htwhome.devices.PercentSwitch;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Volkan Gökkaya
 */
public class PercentSwitchFrame extends javax.swing.JFrame implements StatusChangeListener {

    public PercentSwitch pswitch;

    public PercentSwitchFrame(int id, int status, String location, String description, int[] actorListId, Integer[] actorStatusTab, int gid) throws SocketException {
        initComponents();
        pswitch = new PercentSwitch(id, status, location, description, actorListId, actorStatusTab, gid);
        jSlider1.setMinimum(0);
        jSlider1.setMaximum(100);
        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMinorTickSpacing(5);
        jLabel1.setText(jSlider1.getValue() + "%");
        pswitch.addStatusChangeListener(this);
        jSlider1.setPaintTicks(true);
        jSlider1.setPaintLabels(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMinorTickSpacing(5);

        jLabel1.setText(" ");

        jButton1.setText("SendButton");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(160, 160, 160))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        pswitch.setStatus(jSlider1.getValue());
    }//GEN-LAST:event_jButton1MouseClicked

    public void changeEventReceived(StatusChangeEvent evt) {
        jLabel1.setText((Integer) evt.getStatus() + "%");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                int[] actorListId = {2};
                Integer[] actorStatusTab = {0};
                int gid = 3;
                try {
                    new PercentSwitchFrame(10, 50, "Wohnzimmer", "Switcher", actorListId, actorStatusTab, gid).setVisible(true);
                } catch (SocketException ex) {
                    Logger.getLogger(PercentSwitchFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
