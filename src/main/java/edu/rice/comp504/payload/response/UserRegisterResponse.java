package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

public class UserRegisterResponse implements ResponseAdapter {
    private String username;
    private boolean result;

    public UserRegisterResponse(String username, boolean result) {
        this.username = username;
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}
