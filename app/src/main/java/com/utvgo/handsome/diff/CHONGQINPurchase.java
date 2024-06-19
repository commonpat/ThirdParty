package com.utvgo.handsome.diff;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.bean.BeanChongQingAuth;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.activity.QWebViewActivity;
import com.utvgo.huya.utils.Tools;


/**
 * @author wzb
 * @description:
 * @date : 2023/5/12 17:08
 */
class CHONGQINPurchase extends IPurchase {
    @Override
    public void pay(Context context, CommonCallback callback) {
        String url = DiffConfig.orderHost + "?keyNo="+DiffConfig.getCA(context)+"&cmboId=&contentMid=&contentName=&returnUrl=http%3A%2F%2FKARARAOKETVQUIT%2F";//cmboId=2478& 不传，后台配置
        Log.d("TAG", "CHONGQINPurchase pay: "+url);
        QWebViewActivity.navigateUrl(context,url, QWebViewActivity.RequestType.get,null);

    }

    @Override
    public void auth(Context context, AuthCallback authCallback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    enterAuth(context,authCallback);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();



    }

    @Override
    public void refreshOrderStatus(Context context, AuthCallback callback) {
        auth(context, callback);
    }

    @Override
    public void syncUserAuthorization(Context context, String message) {

    }

    public void  enterAuth(final Context context, final AuthCallback authCallback) throws Exception{

        String url = DiffConfig.authHost +"?keyNo="+DiffConfig.getCA(context)+"&cmboIds="+"&contentMid=&token="+DiffConfig.CurrentTVBox.getLoginToken(context)+"&orderAuthFlg=true";
        Log.d("TAG", "CHONGQINPurchase enterAuth: "+url);
        OkGo.<BeanChongQingAuth>get(url)
                .cacheMode(CacheMode.NO_CACHE)
                .tag(context)
                .execute(new JsonCallback<BeanChongQingAuth>() {
                    @Override
                    public void onSuccess(Response<BeanChongQingAuth> response) {
                        BeanChongQingAuth beanChongQingAuth = response.body();

                        Log.d("TAG", "CHONGQINPurchase enterAuth: "+beanChongQingAuth.toString());

                        if(beanChongQingAuth.getCode() == 0){
                            setPurchased();
                            authCallback.onFinished("0");
                        }else if(beanChongQingAuth.getCode() == 2){
                            setPurchased();
                            authCallback.onFinished("2");
                        }else if(beanChongQingAuth.getCode() == 3){
                            setOrderStatus(-1);
                            authCallback.onFinished("3");
                        }else if(beanChongQingAuth.getCode() == 4){
                            setPurchased();
                            authCallback.onFinished("4");
                        }else {
                            setOrderStatus(-1);
                            authCallback.onFinished("3");
                        }
                        Tools.setIntegerPreference(context.getApplicationContext(),"auth",beanChongQingAuth.getCode());
                    }

                    @Override
                    public void onError(Response<BeanChongQingAuth> response) {
                        super.onError(response);
                        Log.d("TAG", "CHONGQINPurchase enterAuth: "+response.toString());
                    }
                });

    }
}
