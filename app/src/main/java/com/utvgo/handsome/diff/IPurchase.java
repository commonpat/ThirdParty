package com.utvgo.handsome.diff;

import android.content.Context;

import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.huya.BuildConfig;

public abstract class IPurchase {

    public interface AuthCallback {
        void onFinished(String message);

    }

    int orderStatus = BuildConfig.orderStatus;
    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    protected void setPurchased() {

        setOrderStatus(0);
    }

    public Boolean isPurchased() {
        return (this.orderStatus == 0 || this.orderStatus == 2);
    }

    public void resetAuthStatus() {
        setOrderStatus(-1);
    }



    public abstract void pay(final Context context, final CommonCallback callback);

    public abstract void auth(final Context context, final AuthCallback authCallback);

    /**
     * 刷新鉴权
     */
    public abstract void refreshOrderStatus(final Context context, final AuthCallback callback);
    /**
     * 自己后台数据同步授权
     * */
    public abstract void syncUserAuthorization(final Context context,final String message);
}
