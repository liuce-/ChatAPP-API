package edu.rice.comp504.payload;

public class SendAnnouncement {
    private int roomID;
    private String[] announcementList;

    public SendAnnouncement(int roomID, String[] announcementList) {
        this.roomID = roomID;
        this.announcementList = announcementList;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String[] getAnnouncementList() {
        return announcementList;
    }

    public void setAnnouncementList(String[] announcementList) {
        this.announcementList = announcementList;
    }
}
