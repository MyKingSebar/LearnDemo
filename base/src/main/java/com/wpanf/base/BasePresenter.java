package com.wpanf.base;

/**
 * Created by wangpanfeng@nationsky.com on 18/9/27.
 */

public class BasePresenter {

    private BaseView baseView;

    /**
     * used in onCreated
     * @param view
     */
    public void attachView(BaseView view) {
        baseView = view;
    }

    /**
     * used in onDestroy
     */
    public void detachView() {
        baseView = null;
    }

    public boolean isViewLive() {
        return baseView != null;
    }

    public BaseView getView() {
        return baseView;
    }

    public void getDataFromNet() {
        //TODO the method of getData(1.from net 2.from database), getback data by Callback



    }

    public void getDataFromDatabase() {
        //TODO the method of getData(1.from net 2.from database)
    }
}
