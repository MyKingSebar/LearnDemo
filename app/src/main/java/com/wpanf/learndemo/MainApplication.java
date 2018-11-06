package com.wpanf.learndemo;

import com.w.annotationcompiler.Router;
import com.wpanf.base.BaseApplication;

/**
 * Created by wangpanfeng@nationsky.com on 18/11/5.
 */
public class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Router.getInstance().addModule("com.wpanf.recyclerview")
                .addModule("com.w.recyclerviewuser").init();
    }
}
