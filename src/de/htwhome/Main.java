/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while(true){
            try {
                Thread.sleep(1000);
                Toolkit.getDefaultToolkit().beep();
                System.out.println("beep");
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
