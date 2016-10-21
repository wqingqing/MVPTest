package com.test.mvptest.presenter;

import com.test.mvptest.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;


    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }


    @Override
    public void login(String account, String pwd) {
        //TODO 网络请求登录接口 根据回调方法调用view的不同方法

        //account：test
        //pwd：123
        String errorMsg;
        if (account.equals("test") && pwd.equals("123")) {
            //登录成功
            view.loginSuccess();
        } else if (account.equals("test") && !pwd.equals("123")) {
            //登录失败
            errorMsg = "密码错误";
            view.loginError(errorMsg);
        }else if (!account.equals("test") && pwd.equals("123")) {
            //登录失败
            errorMsg = "用户名错误";
            view.loginError(errorMsg);
        }else if (!account.equals("test") && !pwd.equals("123")) {
            //登录失败
            errorMsg = "用户名密码错误";
            view.loginError(errorMsg);
        }

    }
}
