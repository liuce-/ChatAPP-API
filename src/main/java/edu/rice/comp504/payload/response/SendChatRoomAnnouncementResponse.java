package edu.rice.comp504.payload.response;

import com.google.gson.Gson;
import edu.rice.comp504.payload.SendAnnouncement;

public class SendChatRoomAnnouncementResponse implements ResponseAdapter {
    private SendAnnouncement announcement;
    public SendChatRoomAnnouncementResponse(SendAnnouncement announcement) {
        this.announcement = announcement;
    }

    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}
