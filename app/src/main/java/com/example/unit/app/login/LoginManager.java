package com.example.unit.app.login;

/**
 * @author lisen
 * @since 08-31-2018
 */

public class LoginManager {

    public boolean validateName(String name){
        if("".equals(name) || null == name){
            return false;
        }
        return true;
    }

    public void performLogin(String name, String pwd, Callback callback) {
        try {
            Thread.sleep(100);
            callback.onSuccess("success");
            System.out.println("登陆成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("登陆失败");
            callback.onError(e);
        }
    }

    public interface Callback{
        void onSuccess(Object data);
        void onFailed(int code, String msg);
        void onError(Exception e);
    }
}
