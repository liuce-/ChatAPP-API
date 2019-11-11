package edu.rice.comp504.model;

import java.awt.*;
import java.util.ArrayList;

public class ChatRoom {
    String name;
    ArrayList<User> users;
    Point ageRestriction;
    String locRestriction;
    String schoolRestriction;
    // TODO: restrictions

    public ChatRoom (String name, Point ageRestriction, String locRestriction, String schoolRestriction) {
        this.name = name;
        this.ageRestriction = ageRestriction;
        this.locRestriction = locRestriction;
        this.schoolRestriction = schoolRestriction;
    }

    public void addUser(User user) {
        users.add(user);
    }

}
