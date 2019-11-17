package edu.rice.comp504.payload;

public class JoinChatRoom {
    private String username;
    private int roomID;

    public JoinChatRoom(String username, int roomID) {
        this.username = username;
        this.roomID = roomID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}
