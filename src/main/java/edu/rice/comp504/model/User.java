package edu.rice.comp504.model;

import java.util.ArrayList;

public class User {

    private String username;
    private int age;
    private String school;
    ArrayList<ChatRoom> chatRooms;

    // TODO: do we need to keep track of individual chats?

    public User(String username, int age, String school) {
        this.username = username;
        this.age = age;
        this.school = school;
        chatRooms = new ArrayList<ChatRoom>();
    }

    public User(String username) {
        this.username = username;
        this.age = 20;
        this.school = "Rice University";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

}
