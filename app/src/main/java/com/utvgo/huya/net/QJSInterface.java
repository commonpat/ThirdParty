package com.utvgo.huya.net;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.huya.activity.BaseActivity;

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
