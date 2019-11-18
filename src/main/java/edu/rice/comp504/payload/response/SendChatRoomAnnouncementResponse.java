package edu.rice.comp504.payload.response;

import com.google.gson.Gson;
import edu.rice.comp504.payload.request.SendAnnouncement;

/**
 * Send a announcement to other members in a chat room.
 */
public class SendChatRoomAnnouncementResponse implements ResponseAdapter {
    private SendAnnouncement announcement;

    /**
     * constructor.
     * @param announcement original payload.
     */
    public SendChatRoomAnnouncementResponse(SendAnnouncement announcement) {
        this.announcement = announcement;
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
