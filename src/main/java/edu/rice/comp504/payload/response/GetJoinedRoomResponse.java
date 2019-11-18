package edu.rice.comp504.payload.response;

import com.google.gson.Gson;
import edu.rice.comp504.model.ChatRoom;

import java.util.HashSet;

/**
 * Give a user all the chat rooms the user has joined.
 */
public class GetJoinedRoomResponse implements ResponseAdapter {
    private String username;
    private HashSet<ChatRoom> joinedRooms;

    /**
     * constructor.
     * @param username username.
     * @param joinedRooms a set of joined rooms.
     */
    public GetJoinedRoomResponse(String username, HashSet<ChatRoom> joinedRooms) {
        this.username = username;
        this.joinedRooms = joinedRooms;
    }
    /**
     * get a JSON string to respond.
     * @param gson an Gson instance.
     * @return a JSON representation.
     */
    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson("Not Implemented");
    }
}
