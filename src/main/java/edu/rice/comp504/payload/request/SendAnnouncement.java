package edu.rice.comp504.payload.request;

/**
 * Payload of sending an announcement.
 */
public class SendAnnouncement {
    private int roomID;
    private String announcement;
    private String username;

    /**
     * constructor.
     * @param roomID in this room.
     * @param username name of the owner.
     * @param announcement msg.
     */
    public SendAnnouncement(int roomID, String username, String announcement) {
        this.roomID = roomID;
        this.announcement = announcement;
        this.username = username;
    }

    /**
     * getter.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter.
     * @param username set this name.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter.
     * @return roomID
     */
    public int getRoomID() {
        return roomID;
    }

}
