package edu.rice.comp504.payload.response;

import com.google.gson.Gson;
import edu.rice.comp504.model.User;

/**
 * Reponse about if a user login successfully.
 */
public class UserLoginResponse implements ResponseAdapter {
    private User user;
    private boolean status;

    /**
     * constructor.
     *
     * @param user   give this user the response.
     * @param status if the user login successfully.
     */
    public UserLoginResponse(User user, boolean status) {
        this.user = user;
        this.status = status;
    }

    /**
     * getter.
     *
     * @return user.
     */
    public User getUser() {
        return user;
    }

    /**
     * setter.
     *
     * @param user set this user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * getter.
     *
     * @return status.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * setter.
     *
     * @param status set this status.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * get a JSON string to respond.
     *
     * @param gson an Gson instance.
     * @return a JSON representation.
     */
    @Override
    public String getJsonRepresentation(Gson gson) {
        if (status) {
            return gson.toJson(user);
        } else {
            return gson.toJson("Fail");
        }
    }
}
