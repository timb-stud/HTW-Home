package de.htwhome.gui;

import de.htwhome.devices.DoorOpener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketException;
import javax.imageio.ImageIO;

/**
 *
 * @author Volkan Gökkaya
 * Dies ist die Klasse des DoorOpeners als Grafische Oberfläche
 */
public class DoorOpenerFrame extends javax.swing.JFrame implements StatusChangeListener {

    public DoorOpener d;
    private BufferedImage image;

    /*
     * Konstruktor: Dieser hat die selben Parameter wie der DoorOpener Konstruktor
     */
    public DoorOpenerFrame(int id, boolean status, String location, String description, int[] gidTab) throws SocketException, IOException {
        initComponents();
        d = new DoorOpener(id, status, location, description, gidTab);
        d.addStatusChangeListener(this);
        image = ImageIO.read(getClass().getResource("/de/htwhome/gui/Pics/logo.png"));
        this.setIconImage(image);
        setLabels();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/Pics/tuerzu.jpg"))); // NOI18N

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
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * Methode, die beim Eintreffen eines Ereignisses (Event reagieren soll)
     */
    public void changeEventReceived(StatusChangeEvent evt) {
        setLabels();
        if (d.getStatus()) {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/Pics/tueroffen.jpg")));
        } else {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/Pics/tuerzu.jpg")));
        }
    }

    /*
     * Die Lables werden hier korrekt gesetzt
     */
    private void setLabels() {
        jLabel2.setText(d.getDescription());
        jLabel3.setText(d.getLocation());
        jLabel4.setText("ID: " + d.getId());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
