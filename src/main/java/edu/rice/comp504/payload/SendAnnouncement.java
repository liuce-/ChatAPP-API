package edu.rice.comp504.payload;

public class SendAnnouncement {
    private int roomID;
    private String announcement;
    private String username;

    public SendAnnouncement(int roomID, String username, String announcement) {
        this.roomID = roomID;
        this.announcement = announcement;
        this.username = username;
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

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}
