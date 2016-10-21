package com.test.mvptest.contract;

public interface LoginContract {
    interface View {
        void loginSuccess();
        void loginError(String error);
    }

    interface Presenter {
        void login(String account , String pwd);
    }
}
