/*
 * SwitchFrame.java
 *
 * Created on 19.03.2011, 22:35:32
 */
package de.htwhome.gui;

import de.htwhome.devices.Switch;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author volkan
 * Dies ist die Klasse des Switch Gerätes als Grafische Oberfläche
 */
public class SwitchFrame extends javax.swing.JFrame implements StatusChangeListener {

    private Switch s;
    private BufferedImage image;

    /*
     * Konstruktor: Dieser hat die selben Parameter wie der Switch Konstruktor
     */
    public SwitchFrame(int id, Boolean status, String location, String description, int[] actorIdTab, Boolean[] actorStatusTab, int gid) throws IOException {
        initComponents();
        try {
            s = new Switch(id, status, location, description, actorIdTab, actorStatusTab, gid);
            s.addStatusChangeListener(this);
            setLabels();
        } catch (SocketException ex) {
            Logger.getLogger(SwitchFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jButton1.setText("Click me!");
        image = ImageIO.read(getClass().getResource("/de/htwhome/gui/Pics/logo.png"));
        this.setIconImage(image);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/Pics/rot.png"))); // NOI18N

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jButton1.setText("jButton1");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(13, 13, 13)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * Methode, die beim Eintreffen eines Ereignisses (Event reagieren soll)
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        s.setStatus(!s.getStatus());
    }//GEN-LAST:event_jButton1MouseClicked
    public void changeEventReceived(StatusChangeEvent evt) {
        setLabels();
        if (s.getStatusLED()) {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/Pics/gruen.png")));
        } else {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/Pics/rot.png")));
        }
    }

    /*
     * Die Lables werden hier korrekt gesetzt
     */
    private void setLabels() {
        jLabel2.setText(s.getDescription());
        jLabel3.setText(s.getLocation());
        jLabel4.setText("ID: " + s.getId());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
