package de.htwhome.gui;

import de.htwhome.devices.PercentSwitch;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketException;
import javax.imageio.ImageIO;

/**
 *
 * @author Volkan Gökkaya
 * Dies ist die Klasse des PercentSwitch Gerätes als Grafische Oberfläche
 */
public class PercentSwitchFrame extends javax.swing.JFrame implements StatusChangeListener {

    public PercentSwitch pswitch;
    private BufferedImage image;

    /*
     * Konstruktor: Dieser hat die selben Parameter wie der PercentSwitch Konstruktor
     */
    public PercentSwitchFrame(int id, int status, String location, String description, int[] actorListId, Integer[] actorStatusTab, int gid) throws SocketException, IOException {
        initComponents();
        pswitch = new PercentSwitch(id, status, location, description, actorListId, actorStatusTab, gid);
        pswitch.addStatusChangeListener(this);
        image = ImageIO.read(getClass().getResource("/de/htwhome/gui/Pics/logo.png"));
        setSlider();
        setLabels();
        this.setIconImage(image);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

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
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(134, 134, 134)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * MousClickEvent: falls Maus auf den Button klickt, so aktualisiert sich der Slider
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        pswitch.setStatus(jSlider1.getValue());
    }//GEN-LAST:event_jButton1MouseClicked
    /*
     * Methode, die beim Eintreffen eines Ereignisses (Event reagieren soll)
     */

    public void changeEventReceived(StatusChangeEvent evt) {
        setLabels();
    }
    /*
     * Die Lables werden hier korrekt gesetzt
     */

    private void setLabels() {
        jLabel1.setText((Integer) pswitch.getStatus() + "%");
        jLabel2.setText(pswitch.getDescription());
        jLabel3.setText(pswitch.getLocation());
        jLabel4.setText("ID: " + pswitch.getId());
    }
    /*
     * Der Slider wird hier konfiguriert
     */

    private void setSlider() {
        jSlider1.setPaintTicks(true);
        jSlider1.setPaintLabels(true);
        jSlider1.setMinimum(0);
        jSlider1.setMaximum(100);
        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMinorTickSpacing(5);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
