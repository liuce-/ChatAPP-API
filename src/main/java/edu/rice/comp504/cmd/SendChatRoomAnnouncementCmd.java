package edu.rice.comp504.cmd;

import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.response.SendChatRoomAnnouncementResponse;

import java.io.IOException;

/**
 * This is for owner to send announcement in the chat room.
 */
public class SendChatRoomAnnouncementCmd extends AbstractCmd {
    private String announcement;

    public SendChatRoomAnnouncementCmd(String announcement) {
        this.announcement = announcement;
    }

    @Override
    public void execute(User user) {
        try {
            SendChatRoomAnnouncementResponse response = new SendChatRoomAnnouncementResponse(announcement);
            user.getSession().getRemote().sendString(response.getJsonRepresentation(gson));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
