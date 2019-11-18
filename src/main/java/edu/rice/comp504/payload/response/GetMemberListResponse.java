package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

import java.beans.PropertyChangeListener;

public class GetMemberListResponse implements ResponseAdapter {
    private int roomID;
    private PropertyChangeListener[] listeners;

    public GetMemberListResponse( int roomID, PropertyChangeListener[] listeners) {
        this.roomID = roomID;
        this.listeners = listeners;
    }

    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}
