package edu.rice.comp504.cmd;

import edu.rice.comp504.model.Restriction;
import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.response.CreateChatRoomResponse;

import java.io.IOException;

/**
 * This class is for creating a chatroom with restrictions.
 */
public class CreateChatRoomCmd extends AbstractCmd {
    private Restriction restriction;

    public CreateChatRoomCmd(Restriction restriction) {
        this.restriction = restriction;
    }

    @Override
    public void execute(User user) {
        if (restriction.isQualified(user)) {
            try {
                // TODO(calvinliu): change the response.
                CreateChatRoomResponse response = new CreateChatRoomResponse();
                user.getSession().getRemote().sendString(response.getJsonRepresentation(gson));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
