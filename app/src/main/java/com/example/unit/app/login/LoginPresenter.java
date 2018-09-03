package com.example.unit.app.login;

/**
 * @author lisen
 * @since 08-31-2018
 */

public class LoginPresenter {

    private LoginManager mLoginManager;
    private LoginManager.Callback mCallback;

    public LoginPresenter(LoginManager loginManager){
        mLoginManager = loginManager;
    }

    public void setCallback(LoginManager.Callback callback){
        mCallback = callback;
    }

    public void login(String name, String pwd){
        if(!mLoginManager.validateName(name)){
            return;
        }
        if("".equals(pwd)){
            return;
        }

        mLoginManager.performLogin(name, pwd, mCallback);
    }
}
