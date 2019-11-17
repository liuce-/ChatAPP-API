package edu.rice.comp504.payload;

public class CreateRoom {

    private String roomName;
    private int minAge;
    private int maxAge;
    private String location;
    private String school;
    private String ownerName;

    public CreateRoom(String roomName, int minAge, int maxAge, String location, String school, String ownerName) {
        this.roomName = roomName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.location = location;
        this.school = school;
        this.ownerName = ownerName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
