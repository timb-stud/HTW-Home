package de.htwhome.transmission;

/**
 *
 * @author Tim Bartsch
 */
public class Message<T> {
    MessageType msgType;
    int senderId;
    int receiverId;
    T data;

    public Message() {
    }

    public Message(MessageType msgType, int senderId, int receiverId, T data) {
        this.msgType = msgType;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "Message{" + "msgType=" + msgType + "senderId=" + senderId + "receiverId=" + receiverId + "data=" + data + '}';
    }

}
