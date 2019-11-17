package edu.rice.comp504.model;

import org.eclipse.jetty.websocket.api.Session;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class User implements PropertyChangeListener {

    private String username;
    private int age;
    private String school;
    private String location;

    private Session session;
    ArrayList<ChatRoom> chatRooms;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    // TODO: do we need to keep track of individual chats?

    public User(String username, int age, String school, String location) {
        this.username = username;
        this.age = age;
        this.school = school;
        this.location = location;
        chatRooms = new ArrayList<ChatRoom>();
    }

    public User(String username) {
        this.username = username;
        this.age = 20;
        this.school = "Rice University";
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
