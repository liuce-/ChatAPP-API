package edu.rice.comp504.cmd;

import edu.rice.comp504.model.User;

import java.io.IOException;

/**
 * This is for owner to send announcement in the chat room.
 */
public class SendChatRoomAnnouncementCmd extends AbstractCmd {
    private String[] announcementList;

    public SendChatRoomAnnouncementCmd(String[] announcementList) {
        this.announcementList = announcementList;
    }

    @Override
    public void execute(User user) {
        try {
            user.getSession().getRemote().sendString(gson.toJson(announcementList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
