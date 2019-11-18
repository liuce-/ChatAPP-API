package edu.rice.comp504.model;

import java.awt.*;
import java.util.HashSet;

/**
 * The ChatRoom class.
 */
public class ChatRoom {
    private static int nextAvailableID = 0;
    private int id;
    String name;
    User owner;
    Restriction restriction;

    /**
     * Constructor.
     * @param name room name.
     * @param ageRestriction age restriction.
     * @param locRestrictions allowed locations.
     * @param schoolRestriction allowed schools.
     * @param owner owner of this chat room.
     */
    public ChatRoom(String name, Point ageRestriction, HashSet<String> locRestrictions, HashSet<String> schoolRestriction, User owner) {
        this.name = name;
        this.owner = owner;
        this.restriction = new Restriction(ageRestriction, locRestrictions, schoolRestriction);
        this.id = nextAvailableID++;
    }

    /**
     * Constructor.
     * @param name room name.
     * @param ageRestriction age restriction.
     * @param locRestriction allowed location.
     * @param schoolRestriction allowed school.
     * @param owner owner of this chat room.
     */
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

    /**
     * Create a chat room without any restrictions.
     * @param name room name.
     * @param owner owner.
     */
    public ChatRoom(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.restriction = new Restriction(null, null, null);
        this.id = nextAvailableID++;
    }

    /**
     * getter.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * getter.
     * @return room name.
     */
    public String getName() {
        return name;
    }

    /**
     * setter.
     * @param name new room name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the owner.
     * @return owner.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * setter.
     * @param owner the new owner.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * get the restriction.
     * @return restriction.
     */
    public Restriction getRestriction() {
        return restriction;
    }

    /**
     * setter.
     * @param restriction new restriction.
     */
    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }

}
