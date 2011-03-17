package de.htwhome.transmission;

/**
 *
 * @author Tim Bartsch
 */
public class Message<T> {
    MessageType msgType;
    int senderId;
    int receiverId;
    T status;
    String jsonConfig;

    public Message() {
    }

    public Message(MessageType msgType, int senderId, int receiverId, T status, String jsonConfig) {
	this.msgType = msgType;
	this.senderId = senderId;
	this.receiverId = receiverId;
	this.status = status;
	this.jsonConfig = jsonConfig;
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

    public String getJsonConfig() {
	return jsonConfig;
    }

    public void setJsonConfig(String jsonConfig) {
	this.jsonConfig = jsonConfig;
    }

    public T getStatus() {
	return status;
    }

    public void setStatus(T status) {
	this.status = status;
    }

    @Override
    public String toString() {
	return "Message{" + "msgType=" + msgType + "senderId=" + senderId + "receiverId=" + receiverId + "status=" + status + "jsonConfig=" + jsonConfig + '}';
    }

}
