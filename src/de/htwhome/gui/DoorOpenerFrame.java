package de.htwhome.gui;

import de.htwhome.devices.DoorOpener;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Volkan Gökkaya
 */
public class DoorOpenerFrame extends javax.swing.JFrame implements StatusChangeListener {

    public DoorOpener d;

    public DoorOpenerFrame(int id, boolean status, String location, String description, int[] gidTab) throws SocketException {
        initComponents();
        d = new DoorOpener(id, status, location, description, gidTab);
        d.addStatusChangeListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/tuerzu.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void changeEventReceived(StatusChangeEvent evt) {
        if (d.getStatus()) {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/tueroffen.jpg")));
        } else {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/tuerzu.jpg")));
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                int[] gid = {12501};
                try {
                    new DoorOpenerFrame(12501, false, "Eingangstür", "Türöffner", gid).setVisible(true);
                } catch (SocketException ex) {
                    Logger.getLogger(DoorOpenerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
