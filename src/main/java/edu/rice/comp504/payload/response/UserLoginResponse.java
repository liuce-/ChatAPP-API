package edu.rice.comp504.payload.response;

import com.google.gson.Gson;
import edu.rice.comp504.model.User;

public class UserLoginResponse implements ResponseAdapter {
    private User user;
    private boolean success;

    public UserLoginResponse(User user, boolean success) {
        this.user = user;
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String getJsonRepresentation(Gson gson) {
        if (success) {
            return gson.toJson(user);
        } else {
            return gson.toJson("Fail");
        }
    }
}
