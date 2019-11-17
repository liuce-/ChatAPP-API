package edu.rice.comp504.cmd;

import edu.rice.comp504.model.User;

/**
 * This is for leaving a chat room. There can be three reason for leaving a chat room:
 * voluntarily left, connection closed, forced to leave (use "hate" in a message)
 */
public class LeaveChatRoomCmd extends AbstractCmd {
    @Override
    public void execute(User user) {
    }
}
