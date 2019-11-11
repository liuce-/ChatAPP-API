package edu.rice.comp504.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
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

    private String un;
    private int age;
    private String school;
    ArrayList<ChatRoom> chatRooms;

    // TODO: do we need to keep track of individual chats?

    public User(String un, int age, String school) {
        this.un = un;
        this.age = age;
        this.school = school;
        chatRooms = new ArrayList<ChatRoom>();
    }

    public User(String username){
        this.un = username;
        this.age = 20;
        this.school = "Rice University";
    }
}
