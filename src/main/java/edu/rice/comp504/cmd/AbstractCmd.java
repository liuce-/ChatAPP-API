package edu.rice.comp504.cmd;

import com.google.gson.Gson;

public abstract class AbstractCmd {
    protected Gson gson = new Gson();

    public abstract String execute(String body);
}
