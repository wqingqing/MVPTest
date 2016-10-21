package com.test.mvptest.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.test.mvptest.R;
import com.test.mvptest.utils.JumpUtil;

/**
 * Activity基础页
 */
public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout content;
    private ProgressDialog mDialog;
    private AlertDialog mAlertDialog;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDialog = new ProgressDialog(this);
        mDialog.setMessage("请稍候..");

        mAlertDialog = new AlertDialog.Builder(this).setTitle("提示").setPositiveButton("确定", null).create();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base);
        initView();
        LayoutInflater.from(this).inflate(layoutResID, content, true);
        injectPresenter();
        bindView();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    protected void initView() {
        content = (FrameLayout) findViewById(R.id.content);
        mToolBar = (Toolbar) findViewById(R.id.actionBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setTitle(String title) {
        mToolBar.setTitle(title);
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void hideActionBar() {
    }

    public Toolbar getBar(){
        return this.mToolBar;
    }

    /**
     * 显示加载匡
     *
     * @param str
     */
    public void showLoading(String str) {
        if (!TextUtils.isEmpty(str)) {
            mDialog.setMessage(str);
        }
        mDialog.show();
    }

    public void showLoading() {
        mDialog.show();
    }

    /**
     * 隐藏加载匡
     */
    public void hideLoading() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * 显示提示框
     *
     * @param str
     */
    public void showAlert(String str) {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mAlertDialog.setMessage(str);
        mAlertDialog.show();
    }

    public void showBar() {
        mToolBar.setVisibility(View.VISIBLE);
    }

    public void hideBar() {
        mToolBar.setVisibility(View.GONE);
    }

    /**
     * 跳转activity
     *
     * @param clazz
     * @return
     */
    public JumpUtil jumpToActivity(Class clazz) {
        return new JumpUtil(this, clazz);
    }


    public abstract void injectPresenter();

    public abstract void bindView();

}
