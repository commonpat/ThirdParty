package com.utvgo.huya;

import android.app.Application;

import com.utvgo.huya.beans.BeanActivity;
import com.utvgo.huya.diff.DiffConfig;

public class HuyaApplication extends Application {
    public static BeanActivity beanActivity;
    public static boolean showHomeActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        DiffConfig.initEnv(false);
    }

    public static boolean hadBuy() {
        return DiffConfig.CurrentPurchase.isPurchased();
    }
}
