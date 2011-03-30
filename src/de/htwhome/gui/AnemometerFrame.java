package de.htwhome.gui;

import de.htwhome.devices.Anemometer;
import java.net.SocketException;
import java.text.DecimalFormat;

/**
 *
 * @author Volkan Gökkaya
 */
public class AnemometerFrame extends javax.swing.JFrame implements StatusChangeListener {

    public Anemometer a;
    DecimalFormat df;

    public AnemometerFrame(int id, Double status, String location, String description, int gid) throws SocketException {
        initComponents();
        df = new DecimalFormat("#.##");
        a = new Anemometer(id, status, location, description, gid);
        a.addStatusChangeListener(this);
        a.startNotifier(5000);
        jLabel1.setText(df.format(a.getStatus()) + "");
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
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void changeEventReceived(StatusChangeEvent evt) {
        jLabel1.setText(df.format(a.getStatus()) + "");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
