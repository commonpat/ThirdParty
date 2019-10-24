package com.utvgo.handsome.diff;

import android.app.Activity;
import android.content.Context;

import com.utvgo.handsome.bean.HNTVAuthData;
import com.utvgo.handsome.bean.UserOrderProductInfo;
import com.utvgo.handsome.interfaces.CommonCallback;

import java.util.LinkedList;
import java.util.List;

import rx.Observer;

public class HNTVPurchase extends IPurchase {

    private String payUrl = "";
    private String cmboId = "";

    private final List<HNTVAuthData.ProductInfoListBean> cmboList = new LinkedList();
    private final List<UserOrderProductInfo> userOrderProductInfoList = new LinkedList();

    public String getPayUrl() {
        return payUrl;
    }

    private void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getCmboId() {
        return cmboId;
    }

    public void setCmboId(String cmboId) {
        this.cmboId = cmboId;
    }

    public List<HNTVAuthData.ProductInfoListBean> getCmboList() {
        return cmboList;
    }

    public List<UserOrderProductInfo> getUserOrderProductInfoList() {
        return userOrderProductInfoList;
    }

    @Override
    public void refreshOrderStatus(final Context context, final AuthCallback callback) {
        auth(context, callback);
    }

    @Override
    public void pay(Context context, final CommonCallback callback) {
        //HNTV.show((Activity)context);
    }

    @Override
    public void auth(Context context, final AuthCallback authCallback) {
        /*
        NetWork.hntvAuth(DiffConfig.getCA(context), new Observer<HNTVAuthData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                setOrderStatus(-1);
            }

            @Override
            public void onNext(HNTVAuthData authData) {
                if (authData != null) {
                    setOrderStatus(authData.getCode());

                    if (!isPurchased()) {
                        setPayUrl(authData.getAAAResult().getPlayurl());
                    }
                    if (authData.getProductInfoList() != null && authData.getProductInfoList().size() > 0) {
                        setCmboId(authData.getProductInfoList().get(0).getCmboId());
                        getCmboList().clear();
                        getCmboList().addAll(authData.getProductInfoList());
                    }

                    List list = getUserOrderProductInfoList();
                    list.clear();

                    List userOrderProductInfoList = authData.getUserOrderProductInfo();
                    if (userOrderProductInfoList != null) {
                        list.addAll(userOrderProductInfoList);
                    }
                }

                if (authCallback != null) {
                    authCallback.onFinished("");
                }
            }
        });
        */
    }
}
