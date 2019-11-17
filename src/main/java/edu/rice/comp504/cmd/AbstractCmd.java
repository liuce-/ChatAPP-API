package edu.rice.comp504.cmd;

import com.google.gson.Gson;
import edu.rice.comp504.model.User;

public abstract class AbstractCmd {
    protected Gson gson = new Gson();

    public abstract void execute(User user);
}
