package edu.rice.comp504.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String un;
    private int age;
    private String school;
    ArrayList<ChatRoom> chatRooms;

    //do we need to keep track of individual chats?

    public User(String un, int age, String school) {
        this.un = un;
        this.age = age;
        this.school = school;
        chatRooms = new ArrayList<ChatRoom>();
    }


}
