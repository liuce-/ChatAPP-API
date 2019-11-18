package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

public class CreateChatRoomResponse implements ResponseAdapter {
    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson("Room list has changed");
    }
}
