/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.devices;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.htwhome.transmission.Message;
import de.htwhome.transmission.MessageSender;
import de.htwhome.transmission.MessageType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christian
 */
public class TimeScheduler<E> extends Sensor<Boolean>{
    public static final Type msgType = new TypeToken<Message<Boolean>>(){}.getType();

    private Timer timer;

    public TimeScheduler() {
        super.load();
    }

    public TimeScheduler(int id, boolean status, String location, String description, int[] actorIdTab, Boolean[] actorStatusTab, int gid) throws SocketException {
        super(id, status, location, description, actorIdTab, actorStatusTab, gid);
    }

    public void startScheduler(E status,long from, long till){
        timer = new Timer();
        long start = from * 1000;  //TODO Berechnung
        long duration = till * 1000;
        timer.schedule(new TimeSchedulerTask<E>(gid, status), start, duration);
    }

    public void stopScheduler(){
        timer.cancel(); //Terminate the timer thread
    }

    @Override
    public void handleMsg(String msg) {
        super.handleMsg(msg, TimeScheduler.msgType);
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
	Message<Boolean> msg = new Message<Boolean>();
	msg.setMsgType(MessageType.statusChange);
	msg.setReceiverId(this.gid);
	msg.setStatus(this.status);
	this.sendMsg(msg, TimeScheduler.msgType);
        System.out.println("TimeScheduler.status: " + this.status);
    }


     
 //===============(Start interne Klasse)================>
        class TimeSchedulerTask<T> extends TimerTask{
            public final Type msgType;

            private boolean changeStatus = false;
            private int gid;
            private T status;


            public TimeSchedulerTask(int gid, T status) {
                this.gid = gid;
                this.status = status;

                if (this.status instanceof Boolean)
                    msgType = new TypeToken<Message<Boolean>>(){}.getType();
                else if (this.status instanceof Integer)
                    msgType = new TypeToken<Message<Integer>>(){}.getType();
                else if (this.status instanceof Double)
                    msgType = new TypeToken<Message<Double>>(){}.getType();
                else
                    msgType = null; //TODO msgType muss initialisiert werden. null führt zu fehler
            }

            @Override
            public void run() {
                changeStatus = (changeStatus) ? false : true;
                System.out.format("Status = " + status + " Statusswitcher = " + changeStatus + "%n");
        //            timer.cancel(); //Terminate the timer thread
                Message<T> msg = new Message<T>();
                msg.setMsgType(MessageType.statusChange);
                msg.setReceiverId(this.gid);
                msg.setStatus(this.status);
                this.sendMsg(msg, this.msgType);
            }

            //TODO handleResponse

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
//================(ende interne Klasse)========================>
}


