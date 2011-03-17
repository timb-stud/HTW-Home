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
public class MessageTesterServer {
    int i = 0;

    public MessageTesterServer() throws IOException, ClassNotFoundException {
        // Server
       // MessageReceiver msgr = new MessageReceiver();
        // Client
        String msg = "ich sende";
        MessageSender msgsender = new MessageSender();
        while(i < 5){
            msgsender.sendMsg(i +"");
            i++;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        MessageTesterServer test = new MessageTesterServer();
    }
    
}
