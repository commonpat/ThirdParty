package com.utvgo.huya.net;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.activity.BaseActivity;
import com.utvgo.huya.activity.MediaListActivity;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static com.utvgo.huya.constant.ConstantEnumHuya.ORDER;

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
    public void zjsmorder(String s){
        if (c instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) c;
            activity.playingTitle = "活动-"+s;
            activity.stat("弹出订购-"+activity.playingTitle,"");
        }
        if(HuyaApplication.hadBuy()){
            ToastUtil.show(c, "你已经是虎牙TV的尊贵会员！");
        }else {
            if(BuildConfig.DEBUG){
                DiffConfig.CurrentPurchase.pay(c, new CommonCallback() {
                    @Override
                    public void onFinished(Context context) {
                        Activity  backc=(Activity)context;
                        backc.finish();
                    }
                });
                return;
            }
        DiffConfig.CurrentPurchase.auth(c, new IPurchase.AuthCallback() {
            @Override
            public void onFinished(String message) {

                if (!TextUtils.isEmpty(message)) {
                    ToastUtil.show(c, message);
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
        Log.d("wzb", "checkAuth: "+DiffConfig.CurrentPurchase.isPurchased());
       return DiffConfig.CurrentPurchase.isPurchased()?true:false;

    }
    @JavascriptInterface
    public boolean goPay(String s){
        if (c instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) c;
            activity.playingTitle = "活动-"+s;
            activity.stat("弹出订购-"+activity.playingTitle,ORDER);
        }
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
            activity.stat(pageName,"");
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
    @JavascriptInterface
    public int getVersionCode() {

        return BuildConfig.VERSION_CODE;
    }
    @JavascriptInterface
    public String getVersionName() {

        return BuildConfig.VERSION_NAME;
    }
    /**
     *
     * 跳转珠江应用市场
     * */
    @JavascriptInterface
    public void jumpAppStore(String pkgName){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.huan.appstore","com.huan.appstore.ui.AppDetailActivity");
        //ComponentName componentName = new ComponentName("com.utvgo.huya","com.utvgo.huya.activity.LaunchActivity");
        Log.d("", "jumpAppStore: "+c.getApplicationContext().getPackageName());
        intent.setComponent(componentName);
        intent.putExtra("packagename",pkgName);
        intent.setComponent(componentName);
        String category = "android.intent.category.DEFAULT";
        intent.addCategory(category);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        c.startActivity(intent);
    }
}
