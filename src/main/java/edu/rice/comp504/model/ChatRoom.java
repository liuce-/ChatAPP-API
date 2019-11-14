package edu.rice.comp504.model;

import java.awt.*;
import java.util.HashSet;

public class ChatRoom {
    private static int nextAvailableID = 0;
    private int id;
    String name;
    User owner;
    HashSet<User> users;
    Restriction restriction;

    public ChatRoom(String name, Point ageRestriction, HashSet<String> locRestrictions, HashSet<String> schoolRestriction, User owner) {
        this.name = name;
        this.owner = owner;
        this.restriction = new Restriction(ageRestriction, locRestrictions, schoolRestriction);
        this.id = nextAvailableID++;
    }

    private ChatRoom(String name, Point ageRestriction, String locRestriction, String schoolRestriction, User owner) {
        this.name = name;
        this.owner = owner;
        this.restriction = new Restriction(ageRestriction, null, null);
        HashSet<String> locRestrictions = new HashSet<String>();
        locRestrictions.add(locRestriction);

        HashSet<String> schoolRestrictions = new HashSet<String>();
        schoolRestrictions.add(schoolRestriction);

        restriction.setSchoolRestriction(schoolRestrictions);
        restriction.setLocRestriction(locRestrictions);

        this.id = nextAvailableID++;
    }

    private ChatRoom(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.restriction = new Restriction(null, null, null);
        this.id = nextAvailableID++;
    }

    public int getId() {
        return id;
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
