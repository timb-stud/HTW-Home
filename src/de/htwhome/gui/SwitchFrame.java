package de.htwhome.gui;

import de.htwhome.devices.Switch;

/**
 *
 * @author volkan
 */
public class SwitchFrame extends javax.swing.JFrame implements StatusChangeListener{

    private Switch switcher;

    public SwitchFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OnOffButton = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OnOffButton.setText("Switch");
        OnOffButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OnOffButtonMouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/htwhome/gui/rot.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(OnOffButton, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(OnOffButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        OnOffButton.getAccessibleContext().setAccessibleName("OnOffButton");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OnOffButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OnOffButtonMouseClicked
        
    }//GEN-LAST:event_OnOffButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SwitchFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton OnOffButton;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    public void changeEventReceived(StatusChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
