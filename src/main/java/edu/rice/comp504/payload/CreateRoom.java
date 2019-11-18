package edu.rice.comp504.payload;

/**
 * Payload of creating a chat room
 */
public class CreateRoom {

    private String roomName;
    private int minAge;
    private int maxAge;
    private String[] location;
    private String school;
    private String ownerName;

    /**
     * Constructor.
     * @param roomName room name.
     * @param minAge minimum age allowed for this room.
     * @param maxAge maximum age allowed for this room
     * @param location location of members allowed for this room.
     * @param school school of members allowed for this room.
     * @param ownerName owner.
     */
    public CreateRoom(String roomName, int minAge, int maxAge, String[] location, String school, String ownerName) {
        this.roomName = roomName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.location = location;
        this.school = school;
        this.ownerName = ownerName;
    }

    /**
     * getter.
     * @return room name.
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * setter.
     * @param roomName set this name.
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * getter.
     * @return minAge.
     */
    public int getMinAge() {
        return minAge;
    }

    /**
     * setter.
     * @param minAge set this minAge.
     */
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    /**
     * getter.
     * @return maxAge.
     */
    public int getMaxAge() {
        return maxAge;
    }

    /**
     * setter.
     * @param maxAge set this age.
     */
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * getter.
     * @return location.
     */
    public String[] getLocation() {
        return location;
    }

    /**
     * setter.
     * @param location new location.
     */
    public void setLocation(String[] location) {
        this.location = location;
    }

    /**
     * getter.
     * @return school.
     */
    public String getSchool() {
        return school;
    }

    /**
     * setter.
     * @param school set this school.
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * getter.
     * @return owner name.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * setter.
     * @param ownerName set this name.
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
