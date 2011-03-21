package de.htwhome.transmission;

import de.htwhome.devices.DeviceType;

/**
 *
 * @author Tim Bartsch
 */
public class Message {
    MessageType msgType;
    int senderId;
    int receiverId;
    String content;
    DeviceType senderDevice;

    public Message() {
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

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public DeviceType getSenderDevice() {
	return senderDevice;
    }

    public void setSenderDevice(DeviceType senderDevice) {
	this.senderDevice = senderDevice;
    }

    @Override
    public String toString() {
	return "Message{" + "msgType=" + msgType + "senderId=" + senderId + "receiverId=" + receiverId + "content=" + content + "senderDevice=" + senderDevice + '}';
    }
}
