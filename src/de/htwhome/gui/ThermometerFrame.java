package de.htwhome.gui;

import de.htwhome.devices.Thermometer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketException;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;

/**
 *
 * @author Volkan Gökkaya
 */
public class ThermometerFrame extends javax.swing.JFrame implements StatusChangeListener {

    public Thermometer t;
    DecimalFormat df;
    private BufferedImage image;

    public ThermometerFrame(int id, Double status, String location, String description, int gid) throws SocketException, IOException {
        initComponents();
        df = new DecimalFormat("#.##");
        t = new Thermometer(id, status, location, description, gid);
        t.addStatusChangeListener(this);
        t.startNotifier(5000);
        jLabel1.setText(df.format(t.getStatus()) + "");
        image = ImageIO.read(getClass().getResource("/de/htwhome/gui/logo.png"));
        this.setIconImage(image);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void changeEventReceived(StatusChangeEvent evt) {
        jLabel1.setText(df.format(t.getStatus()) + "");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
