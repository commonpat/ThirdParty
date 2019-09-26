package com.utvgo.huya.interfaces;

import android.content.Context;

public abstract class IPurchase {

    public interface AuthCallback {
        void onFinished(String message);
    }

    int orderStatus = -1;
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
}
