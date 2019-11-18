package edu.rice.comp504.cmd;

import com.google.gson.Gson;
import edu.rice.comp504.model.User;

/**
 * The abstract command class for executing some logic.
 */
public abstract class AbstractCmd {
    protected Gson gson = new Gson();

    /**
     * The user to be executed on.
     * @param user execute this cmd on this user.
     */
    public abstract void execute(User user);
}
