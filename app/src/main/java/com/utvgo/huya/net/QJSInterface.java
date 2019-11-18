package com.utvgo.huya.net;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.GZTVPurchase;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.activity.ActivityActivity;
import com.utvgo.huya.activity.BaseActivity;
import com.utvgo.huya.activity.QWebViewActivity;
import com.utvgo.huya.utils.ToastUtil;

public class QJSInterface {
    private Context c;

    public QJSInterface(Context c) {
        this.c = c;
    }

    @JavascriptInterface
    public String getCA() {
        final String ca = DiffConfig.getCA(c);
        //XLog.toast(c, "getCA " + ca);
        return ca;
    }
    @JavascriptInterface
    public void zjsmorder(){
        if(HuyaApplication.hadBuy()){
            ToastUtil.show(c, "你已经是虎牙TV的尊贵会员！");
        }else {
        DiffConfig.CurrentPurchase.auth(c, new IPurchase.AuthCallback() {
            @Override
            public void onFinished(String message) {

                if (!TextUtils.isEmpty(message)) {
                    ToastUtil.show(c, message);
                }
                if (!HuyaApplication.hadBuy()) {
                    if (DiffConfig.CurrentPurchase instanceof GZTVPurchase) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                GZTVPurchase purchase = (GZTVPurchase) DiffConfig.CurrentPurchase;
                               DiffConfig.CurrentPurchase.pay(c, new CommonCallback() {
                                   @Override
                                   public void onFinished(Context context) {
                                       Activity  backc=(Activity)context;
                                       backc.finish();
                                   }
                               });
                              /*  purchase.foo(c, new GZTVPurchase.TryBestCallback() {
                                    @Override
                                    public void d(String s) {
                                        final String msg = s;
                                    }

                                    @Override
                                    public void success(String s) {
                                        ToastUtil.show(c, "你已经是虎牙TV的尊贵会员！");

                                    }

                                    @Override
                                    public void fail(String s) {
                                        ToastUtil.show(c, "订购失败！请更换支付方式");
                                    }
                                });*/
                            }
                        }).start();
                    }
                }
            }
        });
    }
    }
    @JavascriptInterface
    public void zjsmback(){
        Activity activity= (Activity) c;
        activity.finish();
    }

//    @JavascriptInterface
//    public void play(final String type, final String mid) {
//        //XLog.toast(c, "play type " + type + " mid " + mid);
//        NetWork.fetchMVDetail(c, mid, new Observer<BeanMVDetail>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(BeanMVDetail beanMVDetail) {
//                if("1".equals(beanMVDetail.getCode()))
//                {
//                    BeanMVDetail.MvBean mvBean = beanMVDetail.getMv();
//                    ArrayList data = new ArrayList<BeanUserPlayList.DataBean>();
//                    BeanUserPlayList.DataBean playBean = new BeanUserPlayList.DataBean();
//                    playBean.setBigPic(mvBean.getMvPoster());
//                    playBean.setSmallPic(mvBean.getMvPoster());
//                    playBean.setContentMid(mvBean.getMvMid());
//                    playBean.setContentName(mvBean.getName());
//                    playBean.setSingerNames(mvBean.getSingerNames());
//                    playBean.setIsFree(mvBean.getIsFree());
//                    data.add(playBean);
//                    PlayVideoActivity.play(c, ConstantEnum.MediaType.video, false, null, data, 0, true);
//                }
//            }
//        });
//    }

    @JavascriptInterface
    public int getRegionCode() {
        return Integer.parseInt(DiffConfig.getRegionCode(c));
    }

//    @JavascriptInterface
//    public void purchase() {
//        //XLog.toast(c, "purchase");
//        if (c instanceof Activity) {
//            Activity activity = (Activity) c;
//            HNTVPaymentActivity.show(activity);
//        }
//    }
    @JavascriptInterface
    public boolean checkAuth(){
       return DiffConfig.CurrentPurchase.isPurchased()?true:false;

    }
    @JavascriptInterface
    public boolean goPay(){
        DiffConfig.CurrentPurchase.pay(c, new CommonCallback() {
            @Override
            public void onFinished(Context context) {

            }
        });

        return DiffConfig.CurrentPurchase.isPurchased()?true:false;

    }

    @JavascriptInterface
    public void close() {
        //XLog.toast(c, "close");
        if (c instanceof Activity) {
            Activity activity = (Activity) c;
            activity.finish();
        }
    }

    @JavascriptInterface
    public void stat(final String pageName) {
        //XLog.toast(c, "stat " + pageName);
        if (c instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) c;
            activity.stat(pageName);
        }
    }
}
