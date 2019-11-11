package edu.rice.comp504.model;

import java.util.ArrayList;

public class Chat {
    ArrayList<User> users;

    public Chat() {
        users = new ArrayList<User>();
    }

    public void addUser(User user) {
        users.add(user);
    }

}
