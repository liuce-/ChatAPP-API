package edu.rice.comp504.cmd;

import edu.rice.comp504.model.Restriction;
import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.response.CreateChatRoomResponse;

import java.io.IOException;

/**
 * This cmd is for letting all qualified users to know that there is a new room that
 * they can join.
 */
public class CreateChatRoomCmd extends AbstractCmd {
    private Restriction restriction;

    /**
     * constructor.
     * @param restriction apply this restriction.
     */
    public CreateChatRoomCmd(Restriction restriction) {
        this.restriction = restriction;
    }

    /**
     * let all qualified users to know that there is a new room that
     * they can join.
     * @param user execute this cmd on this user.
     */
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
