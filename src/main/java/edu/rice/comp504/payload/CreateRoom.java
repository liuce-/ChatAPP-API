package edu.rice.comp504.payload;

public class CreateRoom {
    private String roomName;
    private int age;
    private String location;
    private String school;

    public CreateRoom(String roomName, int age, String location, String school) {
        this.roomName = roomName;
        this.age = age;
        this.location = location;
        this.school = school;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
