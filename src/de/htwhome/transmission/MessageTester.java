/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.transmission;

import java.io.IOException;

/**
 *
 * @author tobiaslana & volkangoekkaya
 */
public class MessageTester {

    

    public MessageTester() throws IOException, ClassNotFoundException {
        // Server
       // MessageReceiver msgr = new MessageReceiver();
        // Client
        String msg = "ich sende";
        MessageSender msgsender = new MessageSender();
        msgsender.sendMsg(msg);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        MessageTester test = new MessageTester();
    }
    
}
