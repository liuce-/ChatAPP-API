package edu.rice.comp504.payload;

public class SendAnnouncement {
    private int roomID;
    private String announcement;

    public SendAnnouncement(int roomID, String announcement) {
        this.roomID = roomID;
        this.announcement = announcement;
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
