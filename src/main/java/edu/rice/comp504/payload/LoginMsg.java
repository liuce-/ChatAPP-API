package edu.rice.comp504.payload;

public class LoginMsg {
    private String username;

    public LoginMsg(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
