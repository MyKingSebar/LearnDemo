package com.wpanf.recyclerview.presenter;

import android.os.Handler;
import android.os.Message;

import com.wpanf.base.BasePresenter;
import com.wpanf.recyclerview.DataBean;
import com.wpanf.recyclerview.RAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangpanfeng@nationsky.com on 18/9/27.
 */

public class MainPresenter extends BasePresenter {

    public void getDatas() {

        Observable.create(new ObservableOnSubscribe<List<DataBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<DataBean>> e) throws Exception {
                Thread.sleep(1000);

                List<DataBean> beans = new ArrayList<>();
                DataBean bean = null;
                for (int i = 0; i < 15; i++) {
                    bean = new DataBean();
                    bean.setType(RAdapter.ITEM_STRING);
                    bean.setTitle("title" + i);
                    beans.add(bean);
                }
                e.onNext(beans);

            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<DataBean> value) {
                Message message = new Message();
                message.what = 1;
                message.obj = value;
                dispatchMsg(message);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

}
