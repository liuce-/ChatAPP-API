package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

/**
 * Response of whether a chat room is created.
 */
public class CreateRoomResponse  implements ResponseAdapter {
    private String roomName;
    private boolean result;

    /**
     * constructor.
     * @param roomName room name.
     * @param result if creating room is created.
     */
    public CreateRoomResponse(String roomName, boolean result) {
        this.roomName = roomName;
        this.result = result;
    }

    /**
     * get a JSON string to respond.
     * @param gson an Gson instance.
     * @return a JSON representation.
     */
    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}
