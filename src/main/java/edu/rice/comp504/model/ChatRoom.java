package edu.rice.comp504.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ChatRoom {
    String name;
    HashSet<User> users;
    Restriction restriction;

    public ChatRoom(String name, Point ageRestriction, HashSet<String> locRestrictions, HashSet<String> schoolRestriction) {
        this.name = name;
        this.restriction = new Restriction(ageRestriction, locRestrictions, schoolRestriction);
    }

    private ChatRoom(String name, Point ageRestriction, String locRestriction, String schoolRestriction) {
        this.name = name;
        this.restriction = new Restriction(ageRestriction, null, null);
        HashSet<String> locRestrictions = new HashSet<String>();
        locRestrictions.add(locRestriction);

        HashSet<String> schoolRestrictions = new HashSet<String>();
        schoolRestrictions.add(schoolRestriction);

        restriction.setSchoolRestriction(schoolRestrictions);
        restriction.setLocRestriction(locRestrictions);
    }

    private ChatRoom(String name) {
        this.name = name;
        this.restriction = new Restriction(null, null, null);
    }


    /**
     * Add a user to this chat room (if qualified).
     *
     * @param user add this user.
     * @return true if the user is qualified and added. False otherwise.
     */
    public boolean addUser(User user) {
        if (restriction.isQualified(user)) {
            users.add(user);
            return true;
        }
        return false;

    }

    /**
     * remove a user from a chat room
     *
     * @param user remove this user.
     * @return true if the user is in the hashset and successfully remove it. False otherwise.
     */
    public boolean removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
            return true;
        }
        return false;
    }
}
