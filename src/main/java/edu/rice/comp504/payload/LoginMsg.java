package edu.rice.comp504.payload;

/**
 * The Payload of login endpoint.
 */
public class LoginMsg {
    private String username;

    /**
     * constructor.
     * @param username username.
     */
    public LoginMsg(String username) {
        this.username = username;
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
}
