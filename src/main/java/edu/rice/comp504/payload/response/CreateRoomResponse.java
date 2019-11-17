package edu.rice.comp504.payload.response;

public class CreateRoomResponse {
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
}
