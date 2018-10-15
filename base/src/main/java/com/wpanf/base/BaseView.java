package com.wpanf.base;

import android.os.Message;

/**
 * Created by wangpanfeng@nationsky.com on 18/9/27.
 */

public interface BaseView {
    void showLoading();
    void dismissLoading();

    void showMsg(String msg);

    void showError(String error);

    void dispatchMsg(Message msg);
}
