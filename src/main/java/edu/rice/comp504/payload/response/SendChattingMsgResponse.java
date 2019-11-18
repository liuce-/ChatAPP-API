package edu.rice.comp504.payload.response;

public class SendChattingMsgResponse {
    private String sender;
    private String receiver;
    private String roomName;
    private int roomID;
    private String roomOwner;
    private String msg;

    public SendChattingMsgResponse(String sender, String receiver, String roomName, int roomID, String roomOwner, String msg) {
        this.sender = sender;
        this.receiver = receiver;
        this.roomName = roomName;
        this.roomID = roomID;
        this.roomOwner = roomOwner;
        this.msg = msg;
    }

    public String getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(String roomOwner) {
        this.roomOwner = roomOwner;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
