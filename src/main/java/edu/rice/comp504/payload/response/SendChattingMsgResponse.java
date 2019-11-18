package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

/**
 * Send a msg to the receiver.
 */
public class SendChattingMsgResponse implements ResponseAdapter {
    private String sender;
    private String receiver;
    private String roomName;
    private int roomID;
    private String roomOwner;
    private String msg;

    /**
     * Constructor.
     * @param sender the sender.
     * @param receiver the recevier.
     * @param roomName the roomname where they initialize the private chat.
     * @param roomID the room id where they initialize the private chat.
     * @param roomOwner the owner of the room.
     * @param msg send this msg.
     */
    public SendChattingMsgResponse(String sender, String receiver, String roomName, int roomID, String roomOwner, String msg) {
        this.sender = sender;
        this.receiver = receiver;
        this.roomName = roomName;
        this.roomID = roomID;
        this.roomOwner = roomOwner;
        this.msg = msg;
    }


    /**
     * get a JSON string to respond.
     * @param gson an Gson instance.
     * @return a JSON representation.
     */
    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}
