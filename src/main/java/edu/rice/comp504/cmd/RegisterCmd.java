package edu.rice.comp504.cmd;

import edu.rice.comp504.endpoint.UserRegister;

/**
 * This class is for users register/login (with account profile).
 */
public class RegisterCmd extends AbstractCmd {
    @Override
    public void execute(String body) {
        UserRegister userRegister = gson.fromJson(body, UserRegister.class);
        
    }
}
