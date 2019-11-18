package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

/**
 * Response of if the user registered successfully.
 */
public class UserRegisterResponse implements ResponseAdapter {
    private String username;
    private boolean result;

    /**
     * constructor.
     * @param username register this username.
     * @param result the result.
     */
    public UserRegisterResponse(String username, boolean result) {
        this.username = username;
        this.result = result;
    }

    /**
     * getter.
     * @return username.
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
     * @return result.
     */
    public boolean isResult() {
        return result;
    }

    /**
     * setter.
     * @param result result.
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * get a JSON string to respond.
     * @param gson an Gson instance.
     * @return a JSON representation.
     */
    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}
