/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.transmission;

import java.io.IOException;

/**
 *
 * @author Volkan Gökkaya
 */
public class MessageTesterClient {

    public MessageTesterClient() throws IOException, ClassNotFoundException {
        String msg = "ich sende";
        MessageSender msgsender = new MessageSender();
        for(int i = 0; i < 5; i++){
            msgsender.sendMsg(msg + "diese Zahl: "+i);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        MessageTesterClient test = new MessageTesterClient();
    }
}
