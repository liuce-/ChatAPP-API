package edu.rice.comp504.payload.request;

/**
 * Paylaod for joining a chat room
 */
public class JoinChatRoom {
    private String username;
    private int roomID;

    /**
     * Constructor.
     * @param username new guy's name
     * @param roomID join this room.
     */
    public JoinChatRoom(String username, int roomID) {
        this.username = username;
        this.roomID = roomID;
    }

    /**
     * getter.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter.
     * @param username set this username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter.
     * @return room id.
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * setter.
     * @param roomID set this ID.
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}
