package edu.rice.comp504.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ChatRoom {
    String name;
    ArrayList<User> users;
    Restriction restriction;

    public ChatRoom (String name, Point ageRestriction, HashSet<String> locRestrictions, HashSet<String> schoolRestriction) {
        this.name = name;
        this.restriction = new Restriction(ageRestriction, locRestrictions, schoolRestriction);
    }

    private ChatRoom(String name, Point ageRestriction, String locRestriction, String schoolRestriction){
        this.name = name;
        this.restriction = new Restriction(ageRestriction, null ,null);
        HashSet<String> locRestrictions = new HashSet<String>();
        locRestrictions.add(locRestriction);

        HashSet<String> schoolRestrictions = new HashSet<String>();
        schoolRestrictions.add(schoolRestriction);

        restriction.setSchoolRestriction(schoolRestrictions);
        restriction.setLocRestriction(locRestrictions);
    }

    public void addUser(User user) {
        users.add(user);
    }

}
