package edu.rice.comp504.payload.request;

/**
 * Payload of sending a msg to another user.
 */
public class SendChattingMsg {
    private String sender;
    private String receiver;
    private String msg;
    private int roomID;

    /**
     * constructor.
     * @param sender name of the sender.
     * @param receiver name of the receiver.
     * @param msg the msg.
     * @param roomID this chat was opened in this room.
     */
    public SendChattingMsg(String sender, String receiver, String msg, int roomID) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
        this.roomID = roomID;
    }

    /**
     * getter.
     * @return name of the receiver.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * getter.
     * @return sender.
     */
    public String getSender() {
        return sender;
    }

    /**
     * getter.
     * @return msg.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * setter.
     * @param msg send this msg.
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * getter.
     * @return roomID.
     */
    public int getRoomID() {
        return roomID;
    }

}
