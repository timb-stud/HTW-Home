package de.htwhome.gui;

import de.htwhome.devices.Anemometer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketException;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;

/**
 *
 * @author Volkan Gökkaya
 * Dies ist die Klasse des Windmesser Gerätes als Grafische Oberfläche
 */
public class AnemometerFrame extends javax.swing.JFrame implements StatusChangeListener {

    public Anemometer a;
    private DecimalFormat df;
    private BufferedImage image;

    /*
     * Konstruktor: Dieser hat die selben Parameter wie der Anomometer Konstruktor
     */
    public AnemometerFrame(int id, Double status, String location, String description, int gid) throws SocketException, IOException {
        initComponents();
        df = new DecimalFormat("#.##");
        a = new Anemometer(id, status, location, description, gid);
        a.addStatusChangeListener(this);
        a.startNotifier(5000);
        setLabels();
        image = ImageIO.read(getClass().getResource("/de/htwhome/gui/Pics/logo.png"));
        this.setIconImage(image);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        jLabel1.setText(df.format(a.getStatus()) + "");
        jLabel2.setText(a.getDescription());
        jLabel3.setText(a.getLocation());
        jLabel4.setText("ID: " + a.getId());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
