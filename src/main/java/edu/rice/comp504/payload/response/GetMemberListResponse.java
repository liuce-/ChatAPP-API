package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

import java.beans.PropertyChangeListener;

/**
 * Response containing all member names in a chat room.
 */
public class GetMemberListResponse implements ResponseAdapter {
    private int roomID;
    private PropertyChangeListener[] listeners;

    /**
     * Constructor.
     *
     * @param roomID    room id.
     * @param listeners all members.
     */
    public GetMemberListResponse(int roomID, PropertyChangeListener[] listeners) {
        this.roomID = roomID;
        this.listeners = listeners;
    }

    /**
     * get a JSON string to respond.
     *
     * @param gson an Gson instance.
     * @return a JSON representation.
     */
    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}
