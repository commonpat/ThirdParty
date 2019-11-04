package com.utvgo.huya;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.utvgo.huya.beans.BeanActivity;
import com.utvgo.handsome.diff.DiffConfig;

public class HuyaApplication extends MultiDexApplication {
    public static BeanActivity beanActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        DiffConfig.initEnv(true);
    }

    public static boolean hadBuy() {
        return DiffConfig.CurrentPurchase.isPurchased();
    }
}
