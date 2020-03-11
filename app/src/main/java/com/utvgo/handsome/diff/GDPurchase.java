package com.utvgo.handsome.diff;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.bean.BeanGuangDongVipAuth;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.NetworkUtils;
import com.utvgo.huya.activity.QWebViewActivity;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.interfaces.CommonCallback;

public class GDPurchase extends IPurchase {

    @Override
    public void pay(final Context context, final CommonCallback callback) {
        String url = DiffConfig.orderHost +  "TVMaiShiOrderController/order.utvgo?"+
                "keyNo=" + DiffConfig.getCA(context) +"&vipCode=" + AppConfig.VipCode + "&contentMid=&backUrl=http://" + QWebViewActivity.QuitScheme;
        QWebViewActivity.navigateUrl(context, url);
    }

    @Override
    public void auth(final Context context, final AuthCallback authCallback) {

        String url = DiffConfig.orderHost + "order/TVUtvgoVipAuthorizationController/checkVipAuthorization.utvgo?" + "contentMid=" + "";
        NetworkUtils.get(context, url, new JsonCallback<BeanGuangDongVipAuth>() {
            String message = "";
            @Override
            public void onSuccess(Response<BeanGuangDongVipAuth> response) {
                try{
                    BeanGuangDongVipAuth vipAuth = response.body();
                    if(vipAuth.getStatus() == 0)
                    {
                        setPurchased();
                    }
                    else
                    {
                        String msg = vipAuth.getExtra().getMsg();
                        if(msg != null)
                        {
                            message = msg;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                if(authCallback != null){
                    authCallback.onFinished(message);
                }
            }
        });
    }

    @Override
    public void refreshOrderStatus(final Context context, final AuthCallback callback) {
        auth(context, callback);
    }
}
