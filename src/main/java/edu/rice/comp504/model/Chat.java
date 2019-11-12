package edu.rice.comp504.model;

import java.util.HashSet;

/**
 * This class is for private chat.
 */
public class Chat {
    HashSet<User> users;

    public Chat() {
        users = new HashSet<User>();
    }

    public void addUser(User user) {
        users.add(user);
    }

}
