package edu.rice.comp504.cmd;

/**
 * This is for leaving a chat room. There can be three reason for leaving a chat room:
 * voluntarily left, connection closed, forced to leave (use "hate" in a message)
 */
public class LeaveChatRoomCmd extends AbstractCmd {
    @Override
    public String execute(String body) {
        return null;
    }
}
