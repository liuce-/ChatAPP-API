package edu.rice.comp504.model;

import edu.rice.comp504.cmd.AbstractCmd;
import org.eclipse.jetty.websocket.api.Session;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A app user.
 */
public class User implements PropertyChangeListener {

    private String username;
    private int age;
    private String school;
    private String location;

    private Session session;
    HashSet<ChatRoom> chatRooms;

    /**
     * constructor.
     * @param username user name
     * @param age age
     * @param school school
     * @param location location.
     */
    public User(String username, int age, String school, String location) {
        this.username = username;
        this.age = age;
        this.school = school;
        this.location = location;
        chatRooms = new HashSet<>();
    }

    /**
     * constructor.
     * @param username user name.
     */
    public User(String username) {
        this.username = username;
        this.age = 24;
        this.school = "Rice University";
    }

    /**
     * join a chat room.
     * @param chatRoom join this chat room.
     */
    public void joinChatRoom(ChatRoom chatRoom) {
        chatRooms.add(chatRoom);
    }

    /**
     * getter.
     * @return session.
     */
    public Session getSession() {
        return session;
    }

    /**
     * setter.
     * @param session set this session.
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * getter.
     * @return chat rooms.
     */
    public HashSet<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    /**
     * setter.
     * @param chatRooms set this chat room.
     */
    public void setChatRooms(HashSet<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    /**
     * getter.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter.
     * @param username set this username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter.
     * @return age.
     */
    public int getAge() {
        return age;
    }

    /**
     * setter.
     * @param age set this age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * getter.
     * @return school.
     */
    public String getSchool() {
        return school;
    }

    /**
     * setter.
     * @param school set this school.
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * getter.
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter.
     * @param location set this location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AbstractCmd cmd = (AbstractCmd) evt.getNewValue();
        cmd.execute(this);
    }
}
