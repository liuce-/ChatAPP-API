package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

/**
 * Tell others a user has joined a chat room.
 */
public class JoinChatRoomResponse implements ResponseAdapter {
    private int roomID;
    private String username;

    /**
     * constructor.
     * @param roomID room id.
     * @param username the new guy.
     */
    public JoinChatRoomResponse(int roomID, String username) {
        this.roomID = roomID;
        this.username = username;
    }

    /**
     * getter.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter.
     * @param username set this username.
     */
    public void setUsername(String username) {
        this.username = username;
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
