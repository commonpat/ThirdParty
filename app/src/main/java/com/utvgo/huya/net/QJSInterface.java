package com.utvgo.huya.net;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.GZTVPurchase;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.activity.ActivityActivity;
import com.utvgo.huya.activity.BaseActivity;
import com.utvgo.huya.activity.MediaAlbumActivity;
import com.utvgo.huya.activity.MediaListActivity;
import com.utvgo.huya.activity.PlayVideoActivity;
import com.utvgo.huya.activity.QWebViewActivity;
import com.utvgo.huya.activity.TopicActivity;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.beans.ProgramContent;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.constant.ConstantEnum;
import com.utvgo.huya.utils.StringUtils;
import com.utvgo.huya.utils.ToastUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.sh.lib.http.util.ReflectHeaderUtil.getType;

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
        if (c instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) c;
            activity.stat("活动进入订购页");
        }
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

                                   @Override
                                   public void onSuccess(Context context) {

                                   }

                                   @Override
                                   public void onFail(Context context) {

                                   }
                               });
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
    @JavascriptInterface
    public void actionGo(final String utvgoSubjectRecordList, final int index){
                BaseActivity baseActivity= (BaseActivity) c;
                List<OpItem> subjectRecordListBeen = JSON.parseObject(utvgoSubjectRecordList,new TypeReference<List<OpItem>>() {
                } );
                OpItem opItem = subjectRecordListBeen.get(index);
                Log.d("ff", "run: "+subjectRecordListBeen.get(index).getHref());
                baseActivity.actionOnOp(opItem);
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
        if (c instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) c;
            activity.stat("活动进入订购页");
        }
        DiffConfig.CurrentPurchase.pay(c, new CommonCallback() {
            @Override
            public void onFinished(Context context) {
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onSuccess(Context context) {

            }

            @Override
            public void onFail(Context context) {

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
    @JavascriptInterface
    public void goBack(){
        if (c instanceof Activity) {
            Activity activity = (Activity) c;
            activity.finish();
        }
    }
    @JavascriptInterface
    public void goMore(String utvgoSubjectRecordList,int index){
        List<OpItem> utvgoSubjectRecordListBean=JSON.parseObject(utvgoSubjectRecordList,new TypeReference<List<OpItem>>(){}.getType());
        try{
            OpItem selectBeanMore = utvgoSubjectRecordListBean.get(index);
            if(selectBeanMore.getHref()!= null||!"".equals(selectBeanMore.getHref())) {
                Uri uri = Uri.parse(selectBeanMore.getHref());
                String  channelId = uri.getQueryParameter("channelId");
                String  packageId = uri.getQueryParameter("pkId");
                if(channelId==null||"".equals(channelId)){ channelId="35";}
                if(packageId==null||"".equals(packageId)){packageId="29";}
                MediaListActivity.show(c, Integer.valueOf(channelId), null, Integer.valueOf(packageId), -1);
                }
            }catch (Exception e){
             e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void actionUserGo(final String data, final int index,final int type){
        BaseActivity baseActivity= (BaseActivity) c;
        ArrayList<OpItem> utvgoSubjectRecordListBean=JSON.parseObject(data,new TypeReference<List<OpItem>>(){}.getType());

        actionOnUser(utvgoSubjectRecordListBean,index,type);
    }
    private void actionOnUser(ArrayList<OpItem> opItem, int index, int type){
       // PlayVideoActivity.play(c,opItem,index,false);
    }
    @JavascriptInterface
    public String getLocalVersionName() {
        String localVersion = "";
        try {
            PackageInfo packageInfo = c.getApplicationContext().getPackageManager()
                    .getPackageInfo(c.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
