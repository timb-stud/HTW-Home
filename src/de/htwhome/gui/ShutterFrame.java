package de.htwhome.gui;

import de.htwhome.devices.Shutter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketException;
import javax.imageio.ImageIO;

/**
 *
 * @author Volkan Gökkaya
 */
public class ShutterFrame extends javax.swing.JFrame implements StatusChangeListener {

    private Shutter shutter;
    private BufferedImage image;

    public ShutterFrame(int id, int status, String location, String description, int[] gidTab) throws SocketException, IOException {
        initComponents();
        shutter = new Shutter(id, status, location, description, gidTab);
        jProgressBar1.setValue(shutter.getStatus());
        jLabel1.setText(shutter.getStatus() + "%");
        shutter.addStatusChangeListener(this);
        image = ImageIO.read(getClass().getResource("/de/htwhome/gui/logo.png"));
        this.setIconImage(image);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jProgressBar1.setForeground(new java.awt.Color(51, 255, 0));

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(179, 179, 179))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void changeEventReceived(StatusChangeEvent evt) {
        jProgressBar1.setValue((Integer) evt.getStatus());
        jLabel1.setText(evt.getStatus() + "%");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
