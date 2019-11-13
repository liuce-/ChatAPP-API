package edu.rice.comp504.cmd;

import edu.rice.comp504.endpoint.UserRegisterLogin;

/**
 * This class is for users register/login (with account profile).
 */
public class LoginRegisterCmd extends AbstractCmd {
    @Override
    public void execute(String body) {
        UserRegisterLogin userRegisterLogin = gson.fromJson(body, UserRegisterLogin.class);
        
    }
}
