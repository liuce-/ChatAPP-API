package edu.rice.comp504.cmd;

import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.request.SendAnnouncement;
import edu.rice.comp504.payload.response.SendChatRoomAnnouncementResponse;

import java.io.IOException;

/**
 * This is for members to receive a announcement in the chat room sent by a owner.
 */
public class SendChatRoomAnnouncementCmd extends AbstractCmd {
    private SendAnnouncement announcement;

    /**
     * constructor.
     * @param announcement send this announcement.
     */
    public SendChatRoomAnnouncementCmd(SendAnnouncement announcement) {
        this.announcement = announcement;
    }

    /**
     * Send this announcement to this user.
     * @param user Send this announcement to this user.
     */
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
