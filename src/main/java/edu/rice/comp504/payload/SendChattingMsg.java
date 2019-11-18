package edu.rice.comp504.payload;

public class SendChattingMsg {
    private String sender;
    private String receiver;
    private String msg;
    private int roomID;

    public SendChattingMsg(String sender, String receiver, String msg, int roomID) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
        this.roomID = roomID;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}
