package de.htwhome.gui;

import de.htwhome.devices.Thermometer;
import java.net.SocketException;
import java.text.DecimalFormat;

/**
 *
 * @author Volkan Gökkaya
 */
public class ThermometerFrame extends javax.swing.JFrame implements StatusChangeListener {

    public Thermometer t;
    DecimalFormat df;

    public ThermometerFrame(int id, Double status, String location, String description, int gid) throws SocketException {
        initComponents();
        df = new DecimalFormat("#.##");
        t = new Thermometer(id, status, location, description, gid);
        t.addStatusChangeListener(this);
        t.startNotifier(5000);
        jLabel1.setText(df.format(t.getStatus()) + "");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(40, 40, 40))
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
