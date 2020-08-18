package com.utvgo.huya;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

import com.sh.module.payment.bean.PayInfo;
import com.utvgo.handsome.diff.GDPurchase;
import com.utvgo.handsome.diff.GZTVPurchase;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.utils.AppUtils;
import com.utvgo.handsome.utils.XLog;
import com.utvgo.huya.activity.BaseActivity;
import com.utvgo.huya.beans.BeanActivity;
import com.utvgo.handsome.diff.DiffConfig;

public class HuyaApplication extends Application {
    public static BeanActivity beanActivity;
    public static String ProductCategoryId;
    public static String TryBestCmbId;
    public Boolean isPayApk = false;

    @Override
    public void onCreate() {
        super.onCreate();
        DiffConfig.initEnv(true);
        IntentFilter intentFilter = new IntentFilter("intent.PAY_STATE");
        registerReceiver(orderBroadcastReceiver,intentFilter);
    }
    private BroadcastReceiver orderBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(DiffConfig.CurrentPurchase instanceof GZTVPurchase) {
                BaseActivity baseActivity = new BaseActivity();
                GZTVPurchase gztvPurchase = new GZTVPurchase();
                int payState = intent.getIntExtra("payState", -9);
                String info = intent.getStringExtra("info");
                String timestamp = intent.getStringExtra("timestamp");
                String orderId = intent.getStringExtra("orderId");
                if (payState == 0) {
                    baseActivity.stat("弹出订购-" + baseActivity.playingTitle + "-订购成功");
                    PayInfo payInfo = new PayInfo();
                    payInfo.setAlbumInfo(info);
                    payInfo.setOrderId(orderId);
                    gztvPurchase.callBackFunc(context, payInfo, "0");
                    XLog.d("GZTV pay onSuccess");

                } else {
                    baseActivity.stat("弹出订购-" + baseActivity.playingTitle + "-订购失败");
                    PayInfo payInfo = new PayInfo();
                    payInfo.setAlbumInfo(info);
                    payInfo.setOrderId(orderId);
                    gztvPurchase.callBackFunc(context, payInfo, "1");
                    try {
                        gztvPurchase.tryBestAfterFailed(context, new GZTVPurchase.TryBestCallback() {
                            @Override
                            public void d(String s) {
                            }

                            @Override
                            public void success(String s) {
                            }

                            @Override
                            public void fail(String s) {
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    gztvPurchase.auth(context, new IPurchase.AuthCallback() {
                        @Override
                        public void onFinished(String message) {
                            //针对提前关闭支付窗口返回失败，鉴权支付成功上报订购成功结果
                            if ("0".equals(message)) {
                                GZTVPurchase.isPayFailed = false;
                            } else {
                                GZTVPurchase.isPayFailed = true;
                            }
                        }
                    });
                }
            }else {

                BaseActivity baseActivity = new BaseActivity();
                GZTVPurchase gztvPurchase = new GZTVPurchase();
                int payState = intent.getIntExtra("payState", -9);
                String info = intent.getStringExtra("info");
                String timestamp = intent.getStringExtra("timestamp");
                String orderId = intent.getStringExtra("orderId");
                if (BuildConfig.DEBUG){
                    return;
                }
                if (payState == 0) {
                    baseActivity.stat("弹出订购-" + baseActivity.playingTitle + "-订购成功");
                    PayInfo payInfo = new PayInfo();
                    payInfo.setAlbumInfo(info);
                    payInfo.setOrderId(orderId);
                    gztvPurchase.callBackFunc(context, payInfo, "0");
                    XLog.d("GZTV pay onSuccess");

                } else {
                    baseActivity.stat("弹出订购-" + baseActivity.playingTitle + "-订购失败");
                    PayInfo payInfo = new PayInfo();
                    payInfo.setAlbumInfo(info);
                    payInfo.setOrderId(orderId);
                    gztvPurchase.foo(context, new GZTVPurchase.TryBestCallback() {
                       @Override
                       public void d(String s) {

                       }

                       @Override
                       public void success(String s) {

                       }

                       @Override
                       public void fail(String s) {

                       }
                   });
                }
            }
            Log.d("BroadcastReceiver", "onReceive: payState："+intent.getAction()+" info:");
        }
    };

    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    public static boolean hadBuy() {
        return DiffConfig.CurrentPurchase.isPurchased();
    }
}
