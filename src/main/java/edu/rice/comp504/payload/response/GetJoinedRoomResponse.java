package edu.rice.comp504.payload.response;

import com.google.gson.Gson;
import edu.rice.comp504.model.ChatRoom;

import java.util.HashSet;

public class GetJoinedRoomResponse implements ResponseAdapter {
    private String username;
    private HashSet<ChatRoom> joinedRooms;

    public GetJoinedRoomResponse(String username, HashSet<ChatRoom> joinedRooms) {
        this.username = username;
        this.joinedRooms = joinedRooms;
    }

    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson("Not Implemented");
    }
}
