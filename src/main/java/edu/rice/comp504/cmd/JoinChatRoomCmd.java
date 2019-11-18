package edu.rice.comp504.cmd;

import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.response.JoinChatRoomResponse;

import java.io.IOException;

/**
 * Let users know that someone has joined this chat room.
 */
public class JoinChatRoomCmd extends AbstractCmd {
    private User newMember;
    private int roomID;

    /**
     * Constructor.
     *
     * @param newMember this is the new guy in the chat room.
     * @param roomID    this room has a new guy.
     */
    public JoinChatRoomCmd(User newMember, int roomID) {
        this.newMember = newMember;
        this.roomID = roomID;

    }

    /**
     * Let the user know someone has joined.
     *
     * @param user execute this cmd on this user.
     */
    @Override
    public void execute(User user) {
        JoinChatRoomResponse response = new JoinChatRoomResponse(roomID, newMember.getUsername());
        try {
            logger.info("Send join chat room msg to members: " + response.getJsonRepresentation(gson));
            user.getSession().getRemote().sendString(response.getJsonRepresentation(gson));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
