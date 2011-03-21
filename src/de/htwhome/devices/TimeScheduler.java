/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

import com.google.gson.Gson;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageSender;
import de.htwhome.transmission.MessageType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christian
 */
public class TimeScheduler extends Sensor{
    private Timer timer;

    public TimeScheduler(long from, long till) {
        timer = new Timer();
        long start = from * 1000;  //TODO Berechnung
        long duration = till * 1000;
        timer.schedule(new TimeSchedulerTask<Boolean>(gid, true), start, duration);
    }

    @Override
    public void handleMsg(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setStatus(Object status) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     public static void main(String args[]) {
        new TimeScheduler(2, 5);
        System.out.format("Task scheduled.%n");
    }
}


class TimeSchedulerTask<T> extends TimerTask{
    private boolean changeStatus = false;
    private int gid;
    private T status;

    public TimeSchedulerTask(int gid, T status) {
        this.gid = gid;
        this.status = status;
    }

    @Override
    public void run() {
        changeStatus = (changeStatus) ? false : true;
        System.out.format("Status = " + status + " Statusswitcher = " + changeStatus + "%n");
//            timer.cancel(); //Terminate the timer thread
        Message<Boolean> msg = new Message<Boolean>();
        msg.setMsgType(MessageType.statusChange);
        msg.setReceiverId(this.gid);
        msg.setStatus((Boolean) this.status);
        this.sendMsg(msg, );
    }

    private void sendMsg(Message<T> msg, Type msgTyp){
        try {
            String json = new Gson().toJson(msg, msgTyp);
//            System.out.println("JSON:" + json); //TODO aufraeumen
            MessageSender.sendMsg(json);
        } catch (IOException ex) {
            Logger.getLogger(Actor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
