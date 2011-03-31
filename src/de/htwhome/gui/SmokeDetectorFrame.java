package de.htwhome.gui;

import de.htwhome.devices.SmokeDetector;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketException;
import javax.imageio.ImageIO;

/**
 *
 * @author Volkan Gökkaya
 * Dies ist die Klasse des SmokeDetector Gerätes als Grafische Oberfläche
 */
public class SmokeDetectorFrame extends javax.swing.JFrame implements StatusChangeListener {

    public SmokeDetector sd;
    private BufferedImage image;

    /*
     * Konstruktor: Dieser hat die selben Parameter wie der Shutter Konstruktor
     */
    public SmokeDetectorFrame(int id, Boolean status, String location, String description) throws SocketException, IOException {
        initComponents();
        sd = new SmokeDetector(id, status, location, description);
        sd.addStatusChangeListener(this);
        sd.startNotifier(5000);
        this.setBackground(Color.green);
        image = ImageIO.read(getClass().getResource("/de/htwhome/gui/Pics/logo.png"));
        this.setIconImage(image);
        setLabels();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToggleButton1.setText("SmokeDetector starten");
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseClicked(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * MousClickEvent: falls Maus auf den Button klickt, so wird der SmokeDetecotor aktiv
     */
    private void jToggleButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseClicked
        if (jToggleButton1.isSelected()) {
            setLabels();
        }
    }//GEN-LAST:event_jToggleButton1MouseClicked

    /*
     * Methode, die beim Eintreffen eines Ereignisses (Event reagieren soll)
     */
    public void changeEventReceived(StatusChangeEvent evt) {
        if (sd.getStatus()) {
            this.setBackground(Color.green);
            setLabels();
        } else {
            this.setBackground(Color.red);
            setLabels();
        }
    }

    /*
     * Die Lables werden hier korrekt gesetzt
     */
    private void setLabels() {
        jLabel1.setText("Smoke Detector aktiviert....");
        jLabel2.setText(sd.getDescription());
        jLabel3.setText(sd.getLocation());
        jLabel4.setText("ID: " + sd.getId());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
