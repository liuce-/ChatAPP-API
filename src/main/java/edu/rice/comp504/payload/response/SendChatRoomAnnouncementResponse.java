package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

public class SendChatRoomAnnouncementResponse implements ResponseAdapter {
    private String announcement;

    public SendChatRoomAnnouncementResponse(String announcement) {
        this.announcement = announcement;
    }

    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}
