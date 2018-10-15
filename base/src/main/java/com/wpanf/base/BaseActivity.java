package com.wpanf.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wangpanfeng@nationsky.com on 18/9/26.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{

    protected P presenter;

    protected abstract void initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        if(null != presenter) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != presenter) {
            presenter.detachView();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void dispatchMsg(Message msg) {

    }
}
