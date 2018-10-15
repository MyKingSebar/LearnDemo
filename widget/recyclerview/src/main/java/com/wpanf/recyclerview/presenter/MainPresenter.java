package com.wpanf.recyclerview.presenter;

import android.os.Handler;
import android.os.Message;

import com.wpanf.base.BasePresenter;
import com.wpanf.recyclerview.DataBean;
import com.wpanf.recyclerview.RAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpanfeng@nationsky.com on 18/9/27.
 */

public class MainPresenter extends BasePresenter {

    public void getDatas(Handler handler) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<DataBean> beans = new ArrayList<>();
                DataBean bean = null;
                for (int i = 0; i < 15; i++) {
                    bean = new DataBean();
                    bean.setType(RAdapter.ITEM_STRING);
                    bean.setTitle("title" + i);
                    beans.add(bean);
                }
                Message message = new Message();
                message.what = 1;
                message.obj = beans;
                getView().dispatchMsg(message);
            }
        }, 2000);


    }

}
