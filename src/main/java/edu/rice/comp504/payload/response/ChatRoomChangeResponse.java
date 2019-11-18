package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

/**
 * Give all qualified user a response to let them know there is a new chat room they can join.
 */
public class ChatRoomChangeResponse implements ResponseAdapter {

    /**
     * get a JSON string to respond.
     * @param gson an Gson instance.
     * @return a JSON representation.
     */
    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson("Room list has changed");
    }
}
