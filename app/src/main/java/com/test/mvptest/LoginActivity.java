package com.test.mvptest;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.mvptest.base.BaseActivity;
import com.test.mvptest.contract.LoginContract;
import com.test.mvptest.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private EditText loginAccount;
    private EditText loginPwd;
    private Button login;

    private LoginPresenter presenter;
    private String account;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void injectPresenter() {
        presenter = new LoginPresenter(this);
    }


    @Override
    public void bindView() {
        setTitle("登录");
        loginAccount = (EditText) findViewById(R.id.loginAccount);
        loginPwd = (EditText) findViewById(R.id.loginPwd);
        login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = loginAccount.getText().toString();
                pwd = loginPwd.getText().toString();
                if(checkData()){
                    //获取用户名密码登录
                    presenter.login(account,pwd);
                }
            }
        });

    }

    private boolean checkData() {
        if(TextUtils.isEmpty(account)){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_LONG).show();
            return false;
        }else if (TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void loginSuccess() {
        //TODO 登录成功
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();

    }

    @Override
    public void loginError(String error) {
        //TODO 登录失败
        Toast.makeText(this,"登录失败"+error,Toast.LENGTH_LONG).show();


    }
}
