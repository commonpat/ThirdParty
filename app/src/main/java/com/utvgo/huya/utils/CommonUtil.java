package com.utvgo.huya.utils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CommonUtil {

    /**
     * 在IO线程进行网络请求
     *
     * @param observable
     * @param subscriber
     */
    @SuppressWarnings("unchecked")
    public static void o2s(Observable observable, Subscriber subscriber) {
        observable.subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
