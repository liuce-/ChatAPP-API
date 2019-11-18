package edu.rice.comp504.cmd;

import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.JoinChatRoom;
import edu.rice.comp504.payload.response.JoinChatRoomResponse;

import java.io.IOException;

/**
 * Users joins a chatroom.
 */
public class JoinChatRoomCmd extends AbstractCmd {
    private User newMember;
    private int roomID;

    public JoinChatRoomCmd(User newMember, int roomID) {
        this.newMember = newMember;
        this.roomID = roomID;

    }

    @Override
    public void execute(User user) {
        JoinChatRoomResponse response = new JoinChatRoomResponse(roomID, newMember.getUsername());
        try {
            user.getSession().getRemote().sendString(response.getJsonRepresentation(gson));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
