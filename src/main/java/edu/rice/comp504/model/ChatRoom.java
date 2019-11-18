package edu.rice.comp504.model;

import java.awt.*;
import java.util.HashSet;

public class ChatRoom {
    private static int nextAvailableID = 0;
    private int id;
    String name;
    User owner;
    Restriction restriction;

    public ChatRoom(String name, Point ageRestriction, HashSet<String> locRestrictions, HashSet<String> schoolRestriction, User owner) {
        this.name = name;
        this.owner = owner;
        this.restriction = new Restriction(ageRestriction, locRestrictions, schoolRestriction);
        this.id = nextAvailableID++;
    }

    public ChatRoom(String name, Point ageRestriction, String locRestriction, String schoolRestriction, User owner) {
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

    public ChatRoom(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.restriction = new Restriction(null, null, null);
        this.id = nextAvailableID++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }

}
