package com.utvgo.huya;

import android.app.Application;

import com.utvgo.huya.beans.BeanActivity;
import com.utvgo.handsome.diff.DiffConfig;

public class HuyaApplication extends Application {
    public static BeanActivity beanActivity;
    public static String ProductCategoryId;
    public static String TryBestCmbId;

    @Override
    public void onCreate() {
        super.onCreate();
        DiffConfig.initEnv(true);
    }

    public static boolean hadBuy() {
        return DiffConfig.CurrentPurchase.isPurchased();
    }
}
