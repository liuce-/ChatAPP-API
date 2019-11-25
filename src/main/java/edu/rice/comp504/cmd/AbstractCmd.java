package edu.rice.comp504.cmd;

import com.google.gson.Gson;
import edu.rice.comp504.model.User;
import org.eclipse.jetty.util.log.Log;

import java.util.logging.Logger;

/**
 * The abstract command class for executing some logic.
 */
public abstract class AbstractCmd {
    protected Logger logger = Logger.getLogger(AbstractCmd.class.getName());

    /**
     * The user to be executed on.
     *
     * @param user execute this cmd on this user.
     */
    public abstract void execute(User user);
}
