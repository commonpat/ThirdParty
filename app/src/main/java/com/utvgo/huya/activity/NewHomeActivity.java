package com.utvgo.huya.activity;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.model.Response;
import com.sh.lib.tportal.bean.User;
import com.sh.module.payment.manager.UserManager;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.GZTVBox;
import com.utvgo.handsome.diff.GZTVPurchase;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.AppUtils;
import com.utvgo.handsome.utils.XLog;
import com.utvgo.handsome.views.CustomVideoView;
import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanInitData;
import com.utvgo.huya.beans.BeanUpgrade;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.beans.ProgramContent;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.beans.TypesBean;
import com.utvgo.huya.listeners.MyDialogEnterListener;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.NetUtils;
import com.utvgo.huya.utils.StringUtils;
import com.utvgo.huya.utils.ToastUtil;
import com.utvgo.huya.views.SmoothVorizontalScrollView;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;


/**
 * @author wzb
 * @description:
 * @date : 2020/7/27 16:57
 */
public class NewHomeActivity extends BuyActivity{

    private boolean needEnterRecommend;
    @BindView(R.id.home_scroll_view)
    SmoothVorizontalScrollView homeScrollView;
    @BindView(R.id.main_nav)
    LinearLayout mainNav;
    @BindView(R.id.main_header)
    LinearLayout mainHeader;
    @BindView(R.id.vv_small)
    CustomVideoView vvSmall;
    @BindView(R.id.iv_home_logo)
    ImageView ivHomeLogo;
    Boolean canScrollTop = true;
    private String videoArr[] = null;
    private OpItem videoData = null;
    private int playIndex = 0;
    private int clickIndex = 0;
    private List<OpItem> pageData = new ArrayList<>();
    final int flContentIdArray[] = {R.id.bits_0,R.id.bits_2, R.id.bits_2, R.id.bits_3, R.id.bits_4, R.id.bits_5,
            R.id.bits_6, R.id.bits_7, R.id.bits_8, R.id.bits_9, R.id.bits_10,R.id.bits_11,R.id.bits_12,R.id.bits_13,R.id.bits_14
            ,R.id.bits_15,R.id.bits_16,R.id.bits_17,R.id.bits_18,R.id.bits_19};
    final int flContentBtnIdArray[] = {R.id.btn_fl_0,R.id.btn_fl_video, R.id.btn_fl_2, R.id.btn_fl_3, R.id.btn_fl_4, R.id.btn_fl_5,
            R.id.btn_fl_6, R.id.btn_fl_7, R.id.btn_fl_8, R.id.btn_fl_9, R.id.btn_fl_10,R.id.btn_fl_11,R.id.btn_fl_12,R.id.btn_fl_13,R.id.btn_fl_14
            ,R.id.btn_fl_15,R.id.btn_fl_16,R.id.btn_fl_17,R.id.btn_fl_18,R.id.btn_fl_19};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
        ButterKnife.bind(this);
        initView();

        if(DiffConfig.CurrentTVBox instanceof GZTVBox){
            DiffConfig.deviceId = GZTVBox.getDeviceId(this);
            Boolean isPayApk = AppUtils.isApkExist(this,"com.sh.project.pay.general");
            if(!isPayApk){
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.huan.appstore","com.huan.appstore.ui.AppDetailActivity");
                intent.putExtra("packagename","com.sh.project.pay.general");
                intent.setComponent(componentName);
                String category = "android.intent.category.DEFAULT";
                intent.addCategory(category);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
        DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
            @Override
            public void onFinished(String message) {
                if(BuildConfig.DEBUG){
                    Toast.makeText(NewHomeActivity.this, "HomePage auth finished: " + (DiffConfig.CurrentPurchase.isPurchased() ? "is Purchased" : "not Purchased"), Toast.LENGTH_LONG).show();
                }
                if(DiffConfig.CurrentPurchase instanceof GZTVPurchase) {
                    final UserManager userManager = UserManager.getInstance(NewHomeActivity.this);
                    userManager.getUserInfo(new UserManager.UserCallBack() {
                        @Override
                        public void onSuccess(User user) {
                            if("".equals(user.getBankNum()) || user.getBankNum() == null){
                                stat("无甜果支付"+GZTVBox.getDeviceId(NewHomeActivity.this));
                            }
                        }
                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                    try {
                        if ("0".equals(message)) {
                            NetworkService.defaultService().syncUserAuthorization(getBaseContext(), "0", GZTVPurchase.TryBestCmbId);
                        } else {
                            NetworkService.defaultService().syncUserAuthorization(getBaseContext(), "1", GZTVPurchase.TryBestCmbId);

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                getInitData();
            }
        });
        runOnUiThread(new Runnable() {
             @Override
             public void run() {
                getMainNavData();
                getMainContentData();
                loadUpgrade();
             }
        });
    }

    private void getMainContentData() {
        NetworkService.defaultService().fetchHomePageData(this, AppConfig.typeIdNew, new JsonCallback<BaseResponse<List<OpItem>>>() {
            @Override
            public void onSuccess(Response<BaseResponse<List<OpItem>>> response) {
              BaseResponse<List<OpItem>> bean = response.body();
              pageData = bean.getData();
              if(bean.isOk()&& bean != null){
                  showMainContenData(bean.getImageProfix(),bean.getData());
              }else {
                  HiFiDialogTools.getInstance().showLeftRightTip(NewHomeActivity.this, "温馨提示", "请求数据失败，请检查网络！",
                          "重试", "取消", new MyDialogEnterListener() {
                      @Override
                      public void onClickEnter(Dialog dialog, Object object) {
                          if((int)object == 0){
                              getMainContentData();
                          }
                      }
                  });
              }
            }
        });
    }

    private void showMainContenData(String imageProfix, List<OpItem> data) {
        for (int i = 0; i < data.size() && i < flContentIdArray.length; i++) {
            if("1".equals(data.get(i).getIsVideo())){
                videoData = data.get(i);
                videoArr = data.get(i).getVideoUrl().split(",");
                if (videoArr == null || videoArr.length <=1){
                    videoArr = data.get(i).getVideoUrl().split("//|");
                }
                playVideo();

            }else {
                loadImage((ImageView) findViewById(flContentIdArray[i]),DiffConfig.generateImageUrl(data.get(i).getImgUrl()));
            }
        }
    }
    private void loadUpgrade() {
        NetworkService.defaultService().getVersionInfo(this, "", new JsonCallback<BeanUpgrade>() {
            @Override
            public void onSuccess(Response<BeanUpgrade> response) {
                BeanUpgrade beanUpgrade = response.body();
                if(beanUpgrade != null && "1".equals(beanUpgrade.getCode())){
                    if(beanUpgrade.getData() != null) {
                        int currentVersionCode = beanUpgrade.getData().getCurrentVersionCode();
                        String currentVersionName = beanUpgrade.getData().getCurrentVersionName();
                        int minVersionCode = beanUpgrade.getData().getMinVersionCode();
                        String minVersionName = beanUpgrade.getData().getMinVersionName();
                        int versionCode;
                        String versionName;
                        //高于最低版本
                        if (BuildConfig.VERSION_CODE >= minVersionCode) {
                            if (BuildConfig.VERSION_CODE < currentVersionCode) {
                                HiFiDialogTools.getInstance().showUpgradeTips(NewHomeActivity.this, beanUpgrade, new MyDialogEnterListener() {
                                    @Override
                                    public void onClickEnter(Dialog dialog, Object object) {
                                        if((int)object == 0){
                                            jumpAppStore();

                                        }else if ((int)object == 1){
                                            dialog.dismiss();
                                        }
                                    }
                                });
                            }
                        }else {
                            HiFiDialogTools.getInstance().showMinUpgradeTips(NewHomeActivity.this, beanUpgrade, new MyDialogEnterListener() {
                                @Override
                                public void onClickEnter(Dialog dialog, Object object) {
                                    if((int)object == 0){
                                        jumpAppStore();
                                    }
                                }
                            });
                        }
                    }
                }

            }
        });
    }

    private void playVideo() {

        int index = new Random().nextInt(videoArr.length);
        if (playIndex > videoArr.length||videoArr.length < 0){
            index = 0;
        }
        playIndex = index;
        getHahaPlayerUrl(videoArr[playIndex]);
    }

    private void getMainNavData() {
        NetworkService.defaultService().fetchHomePageNavData(this,AppConfig.PackageIdNew, new JsonCallback<TypesBean>() {
            @Override
            public void onSuccess(Response<TypesBean> response) {
                TypesBean bean = response.body();
                if(bean.isOk()){
                    typesBean = bean;
                }
            }
        });
    }

    private void initView() {
        vvSmall.setFocusable(false);
        setHahaPlayer(vvSmall);
        findViewById(R.id.btn_fl_0).requestFocus();
        homeScrollView.setOnScrollChangeListener(new SmoothVorizontalScrollView.OnScrollChangeListener() {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            @Override
            public void onTop(int l, int t, int oldl, int oldt) {
//                canScrollTop = true;
//                Animation animation = new TranslateAnimation(0, 0, 0, 0);
//                animation.setDuration(500);
//                animation.setFillAfter(true);
//                mainNav.startAnimation(animation);
//                mainHeader.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBottom(int l, int t, int oldl, int oldt) {

            }

            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
//                if (t > oldt && canScrollTop) {
//                    canScrollTop = false;
//                    Animation animation = new TranslateAnimation(0, 0, 0, -80);
//                    animation.setDuration(500);
//                    animation.setFillAfter(true);
//                    mainNav.startAnimation(animation);
//                    mainHeader.setVisibility(View.INVISIBLE);
//                    //mainNav.setBackgroundColor(getResources().getColor(R.color.cc));
//                }
            }
        });
    }

    @Override
    @OnFocusChange({R.id.btn_fl_video,R.id.btn_fl_0,R.id.btn_fl_2,R.id.btn_fl_3,R.id.btn_fl_4,R.id.btn_fl_5,R.id.btn_fl_6,R.id.btn_fl_7,R.id.btn_fl_8,R.id.btn_fl_9,R.id.btn_fl_10,
            R.id.btn_fl_11,R.id.btn_fl_12,R.id.btn_fl_13,R.id.btn_fl_14,R.id.btn_fl_15,R.id.btn_fl_16,R.id.btn_fl_17,R.id.btn_fl_18,R.id.btn_fl_19})
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        if(hasFocus&&v.getId() != R.id.btn_fl_video){
                 ((RelativeLayout) v.getParent()).bringToFront();
                 ViewCompat.animate((RelativeLayout) v.getParent())
                         .scaleX(1.12f)
                         .scaleY(1.12f)
                         .start();
        }else if(v.getId() != R.id.btn_fl_video){
                 ViewCompat.animate((RelativeLayout)v.getParent())
                     .scaleX(1)
                     .scaleY(1)
                     .start();
        }

    }

    @Override
    @OnClick({R.id.btn_fl_0,R.id.btn_fl_2,R.id.btn_fl_3,R.id.btn_fl_4,R.id.btn_fl_5,R.id.btn_fl_6,R.id.btn_fl_7,R.id.btn_fl_8,R.id.btn_fl_9,R.id.btn_fl_10,
            R.id.btn_fl_11,R.id.btn_fl_12,R.id.btn_fl_13,R.id.btn_fl_14,R.id.btn_fl_15,R.id.btn_fl_16,R.id.btn_fl_17,R.id.btn_fl_18,R.id.btn_fl_19})
    public void onClick(View view) {
        super.onClick(view);
       final Context context = this;
        //Arrays.binarySearch(flContentIdArray,view.getId());
        switch (view.getId()){
            case R.id.btn_main_order: {
                stat("首页进入订购页");
                if (DiffConfig.CurrentPurchase.isPurchased()) {
                    ToastUtil.show(context, "您已经是 " + getResources().getString(R.string.app_name) + " 尊贵会员");
                    break;
                }
                DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
                    @Override
                    public void onFinished(String message) {
                        if (DiffConfig.CurrentPurchase.isPurchased()) {
                            ToastUtil.show(context, "您已经是 " + getResources().getString(R.string.app_name) + " 尊贵会员");
                        } else {
                            if (!TextUtils.isEmpty(message)) {
                                ToastUtil.show(context, message);
                            }
                            DiffConfig.CurrentPurchase.pay(context, new CommonCallback() {
                                @Override
                                public void onFinished(Context context) {

                                }

                                @Override
                                public void onSuccess(Context context) {
                                    ToastUtil.show(context, "您已经是 " + getResources().getString(R.string.app_name) + " 尊贵会员");
                                }

                                @Override
                                public void onFail(Context context) {
                                    ToastUtil.show(context, "订购失败！");
                                }
                            });
                        }
                    }
                });
                break;
            }
            case R.id.btn_main_user_favor: {
                startActivity(new Intent(this, UserFavoriteActivity.class));
//                Intent intent = new Intent();
//                intent.setAction("intent.PAY_STATE");
//                intent.putExtra("payState",0);
//                intent.putExtra("info","支付成功");
//                intent.putExtra("timestamp","APP0HYTV");
//                sendBroadcast(intent);
                break;
            }
            case R.id.btn_main_introduction: {
                if(BuildConfig.DEBUG){
                    QWebViewActivity.navigateUrl(this,"http://192.168.5.16:8080/app/huya/activity20200813.html");
                    break;
                }
                if (DiffConfig.UseWebIntroduction) {
                    QWebViewActivity.navigateUrl(this, DiffConfig.IntroduceUrl);
                } else {
                    startActivity(new Intent(this, IntroduceActivity.class));
                }
                break;
            }
            case R.id.btn_main_user_center: {
                startActivity(new Intent(this, UserCenterActivity.class));
                break;
            }
            case R.id.btn_tab_0:
                actionOnNavOp(typesBean.getData().getNavigationBar().get(0));
                break;
            case R.id.btn_tab_1:
                actionOnNavOp(typesBean.getData().getNavigationBar().get(1));
                break;
            case R.id.btn_tab_2:
                actionOnNavOp(typesBean.getData().getNavigationBar().get(2));
                break;
            case R.id.btn_tab_3:
                actionOnNavOp(typesBean.getData().getNavigationBar().get(3));
                break;
            case R.id.btn_tab_4:
                actionOnNavOp(typesBean.getData().getNavigationBar().get(4));
                break;
            case R.id.btn_tab_5:
                actionOnNavOp(typesBean.getData().getNavigationBar().get(5));
                break;
            case R.id.btn_tab_6:
                actionOnNavOp(typesBean.getData().getNavigationBar().get(6));
                break;
            default:
                try{
                    for(int i=0;i<flContentBtnIdArray.length;i++){
                        if(flContentBtnIdArray[i] == view.getId()){
                            clickIndex = i;
                        }
                    }
                    if(clickIndex == 1){
                        gotoMediaPlayer();
                    }else {
                        actionOnOp(pageData.get(clickIndex));
                    }
                }catch (Exception e){e.printStackTrace();}

        }
    }

    private  void gotoMediaPlayer() {
        if(this.videoData != null)
        {
            String array[] = this.videoData.getHref().split(",");
            if (array == null || array.length <= 1) {
                array = this.videoData.getVideoUrl().split("\\|");
            }
            int index = playIndex;
            if(index >= 0 && index < array.length)
            {
                String url = array[index];
                Uri uri = Uri.parse(url);
                String programId = uri.getQueryParameter("pkId");
                String channelId = uri.getQueryParameter("channelId");
                ArrayList<ProgramInfoBase> list = new ArrayList<>();
                ProgramInfoBase programInfoBase = new ProgramInfoBase();
                programInfoBase.setName("");
                programInfoBase.setChannelId(StringUtils.intValueOfString(channelId));
                programInfoBase.setPkId(StringUtils.intValueOfString(programId));
                programInfoBase.setMultiSetType("0");
                list.add(programInfoBase);
                PlayVideoActivity.play(this, list, 0, false);
            }
        }
    }

    public  void  checkIntentData(){

        String programId = getIntent().getStringExtra("pkId");
        String multisetType = getIntent().getStringExtra("multisetType");
        String channelId = getIntent().getStringExtra("channelId");

        String albumId = getIntent().getStringExtra("pkId");

        String topicId = getIntent().getStringExtra("themId");
        String styleId = getIntent().getStringExtra("styleId");
        Log.d(TAG, "checkIntentData:"+"programId"+programId+"multisetType"+multisetType+"channelId"+channelId+"albumId"+albumId+"topicId"+topicId+"styleId"+styleId);

        if(programId!=null && multisetType!=null && channelId!=null) {
            needEnterRecommend = false;
            NetworkService.defaultService().fetchProgramContent(this, StringUtils.intValueOfString(programId),
                    multisetType, StringUtils.intValueOfString(channelId), new JsonCallback<BaseResponse<ProgramContent>>() {
                        @Override
                        public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
                            BaseResponse<ProgramContent> data = response.body();
                            if (data.isOk()) {
                                ArrayList<ProgramInfoBase> list = new ArrayList<>();
                                list.add(data.getData());
                                PlayVideoActivity.play(getBaseContext(), list, 0, false);
                            }
                        }
                    });
        }
        if(albumId != null) {
            needEnterRecommend = false;
            stat("外流好推荐进入合集");
            MediaAlbumActivity.show(this, StringUtils.intValueOfString(albumId));
        }
        if(topicId != null ) {
            needEnterRecommend = false;
            stat("外流好推荐进入专题");
            TopicActivity.show(this, topicId, styleId);
        }

    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: "+ HuyaApplication.hadBuy());
        if (!HuyaApplication.hadBuy()) {

            if(DiffConfig.CurrentPurchase instanceof GZTVPurchase) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GZTVPurchase purchase = (GZTVPurchase) DiffConfig.CurrentPurchase;
                        purchase.tryBest(NewHomeActivity.this, new GZTVPurchase.TryBestCallback() {
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
                }).start();
            }
            showQuitRecommend();

        } else {
            alertForQuit();
        }
    }
    private void showQuitRecommend() {
        Intent intent = new Intent(NewHomeActivity.this, ExitActivity.class);

        startActivityForResult(intent, ExitActivity.TAGRecommendExit);
    }
    private void alertForQuit() {
        HiFiDialogTools.getInstance().showLeftRightTip(NewHomeActivity.this, "温馨提示", "确认退出" +
                getResources().getString(R.string.app_name), "确认", "取消", new MyDialogEnterListener() {
            @Override
            public void onClickEnter(Dialog dialog, Object object) {
                if (object instanceof Integer) {
                    if (((Integer) object) == 0) {
                        turnHome();
                    }
                }
            }
        });
    }

    public void turnHome() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private void getInitData() {

        String path = DiffConfig.activityHost + "/activity/androidActivityController/getPushContent.utvgo?regionCode="
                + DiffConfig.getRegionCode(this);
        try{
            NetUtils.getData(this, path, null, BeanInitData.class, new NetUtils.NetCallBack() {
                @Override
                public void netBack(int requestTag, Object object) {
                    if (object != null) {
                        XLog.d("logom"+object);
                        BeanInitData beanInitData = (BeanInitData) object;
                        if (beanInitData != null) {
                            String bgImageUrl = DiffConfig.generateImageUrl(beanInitData.getHomePageResource().getImagUrl());
                            String logoImageUrl = DiffConfig.generateImageUrl(beanInitData.getHomePageResource().getLogoUrl());
                            if(!"".equals(bgImageUrl)&&!"".equals(logoImageUrl)) {
                                Glide.with(NewHomeActivity.this).load(logoImageUrl).into(ivHomeLogo);
                                // Glide.with(HomeActivity.this).load(bgImageUrl).into(homeBg);
                            }
//                        if (HuyaApplication.hadBuy()) {
//                            //会员不弹活动
//                            return;
//                        }
                            if (beanInitData.getStartPushContent() != null && needEnterRecommend) {

                                if (!TextUtils.isEmpty(beanInitData.getStartPushContent().getHref())&&(beanInitData.getStartPushContent().getHref() != null )) {
                                    QWebViewActivity.navigateUrl(NewHomeActivity.this,beanInitData.getStartPushContent().getHref(),null,null);
                                } else {
                                    Intent intent = new Intent(NewHomeActivity.this, ActivityActivity.class);
                                    intent.putExtra("bgImageUrl",bgImageUrl);
                                    startActivity(intent);
                                }

                            }
                        }
                    }
                }
            });}catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            turnHome();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        playingTitle = "弹出订购-订购菜单";
        stat("首页","");
        if (!DiffConfig.validateDeviceKeyNO(this)) {
            return;
        }
    }
    @Override
    protected void onDestroy() {

        videoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void hahaPlayEnd(float v) {
        super.hahaPlayEnd(v);
        playVideo();
    }
}
