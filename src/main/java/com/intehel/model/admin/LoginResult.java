package com.intehel.model.admin;

/**
*@Description 登录结果实体类
*@Author 侯森林
*@Date
*@Time
*/
public class LoginResult {
    private boolean isLogin = false;
    private String result;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
