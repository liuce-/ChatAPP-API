package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

public class CreateRoomResponse  implements ResponseAdapter {
    private String roomName;
    private boolean result;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public CreateRoomResponse(String roomName, boolean result) {
        this.roomName = roomName;
        this.result = result;
    }

    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}
