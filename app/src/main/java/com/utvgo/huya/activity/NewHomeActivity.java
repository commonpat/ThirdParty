package com.utvgo.huya.activity;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.diff.TOPWAYBox;
import com.utvgo.handsome.diff.TOPWAYPurchase;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.TopWayBroacastUtils;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

import static android.media.MediaPlayer.MEDIA_INFO_BUFFERING_END;
import static android.media.MediaPlayer.MEDIA_INFO_BUFFERING_START;
import static com.utvgo.huya.constant.ConstantEnumHuya.INDEX;
import static com.utvgo.huya.constant.ConstantEnumHuya.ORDER;


/**
 * @author wzb
 * @description:
 * @date : 2020/7/27 16:57
 */
public class NewHomeActivity extends HomeBaseActivity{

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
    @BindView(R.id.home_bg)
    ImageView homeBg;
    @BindView(R.id.image_loading)
    ImageView videoLoad;
    Boolean canScrollTop = true;
    private String videoArr[] = null;
    private OpItem videoData = null;
    private int playIndex = 0;
    private int clickIndex = 0;
    public int fistLoadPlayer = 0;
    private int playPoint = 0;
    private List<OpItem> pageData = new ArrayList<>();
    final int flContentIdArray[] = {R.id.bits_0,R.id.bits_2, R.id.bits_2, R.id.bits_3, R.id.bits_4, R.id.bits_5,
            R.id.bits_6, R.id.bits_7, R.id.bits_8, R.id.bits_9, R.id.bits_10,R.id.bits_11,R.id.bits_12,R.id.bits_13,R.id.bits_14
            ,R.id.bits_15,R.id.bits_16,R.id.bits_17,R.id.bits_18,R.id.bits_19};
    final int flContentBtnIdArray[] = {R.id.btn_fl_0,R.id.btn_fl_video, R.id.btn_fl_2, R.id.btn_fl_3, R.id.btn_fl_4, R.id.btn_fl_5,
            R.id.btn_fl_6, R.id.btn_fl_7, R.id.btn_fl_8, R.id.btn_fl_9, R.id.btn_fl_10,R.id.btn_fl_11,R.id.btn_fl_12,R.id.btn_fl_13,R.id.btn_fl_14
            ,R.id.btn_fl_15,R.id.btn_fl_16,R.id.btn_fl_17,R.id.btn_fl_18,R.id.btn_fl_19};
    public Handler towayHomeHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 999){
                if(DiffConfig.CurrentPurchase.isPurchased()) {
                    ((Button)findViewById(R.id.btn_main_order)).setBackground(getResources().getDrawable(R.drawable.btn_had_ordered));
                    ((Button)findViewById(R.id.btn_main_order)).setFocusable(false);
                }
            }
            return false;
        }
    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
        ButterKnife.bind(this);
        checkIntentData();
        initView();
        homeScrollView.setFadingEdge(480);


        runOnUiThread(new Runnable() {
             @Override
             public void run() {
                getMainNavData();
                getMainContentData();
                getInitData();
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
                  towayHomeHandle.sendEmptyMessage(999);
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
        if(videoArr != null && videoArr.length != 0) {
            int index = new Random().nextInt(videoArr.length);
            if (playIndex > videoArr.length || videoArr.length < 0) {
                index = 0;
            }
            playIndex = index;
            getHahaPlayerUrl(videoArr[playIndex]);
        }
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
        vvSmall.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                //Log.d(TAG, "onInfo:index what"+what+"____"+extra);
                vvSmall.invalidate();
                if(what == MEDIA_INFO_BUFFERING_START){
                    hahaPauseOrResumePlay();
                    videoLoad.setVisibility(View.VISIBLE);
                    return true;

                }else if(what == MEDIA_INFO_BUFFERING_END){
                    videoLoad.setVisibility(View.INVISIBLE);
                    hahaPauseOrResumePlay();
                    return true;
                }else {
                    videoLoad.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
        findViewById(R.id.btn_fl_0).requestFocus();
    }

    @Override
    @OnFocusChange({R.id.btn_fl_video,R.id.btn_fl_0,R.id.btn_fl_2,R.id.btn_fl_3,R.id.btn_fl_4,R.id.btn_fl_5,R.id.btn_fl_6,R.id.btn_fl_7,R.id.btn_fl_8,R.id.btn_fl_9,R.id.btn_fl_10,
            R.id.btn_fl_11,R.id.btn_fl_12,R.id.btn_fl_13,R.id.btn_fl_14,R.id.btn_fl_15,R.id.btn_fl_16,R.id.btn_fl_17,R.id.btn_fl_18,R.id.btn_fl_19})
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        focusView = v;
        if(v.getId() != R.id.btn_fl_video){
            ((RelativeLayout) v.getParent()).bringToFront();
           // ViewUtil.scaleView(hasFocus, (View) v.getParent());
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

                if (DiffConfig.CurrentPurchase.isPurchased()) {
                    ToastUtil.show(context, "您已经是 " + getResources().getString(R.string.app_name) + " 尊贵会员");
                }else {
                    stat("首页进入订购页",ORDER);
                    DiffConfig.CurrentPurchase.pay(context, new CommonCallback() {
                        @Override
                        public void onFinished(Context context) {
                            towayHomeHandle.sendEmptyMessage(999);
                        }
                    });
                }
//                DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
//                    @Override
//                    public void onFinished(String message) {
//                        towayHomeHandle.sendEmptyMessage(999);
//                        if (DiffConfig.CurrentPurchase.isPurchased()) {
//                            ToastUtil.show(context, "您已经是 " + getResources().getString(R.string.app_name) + " 尊贵会员");
//                        } else {
////                            if (!TextUtils.isEmpty(message)) {
////                                ToastUtil.show(context, message);
////                            }
//                            DiffConfig.CurrentPurchase.pay(context, new CommonCallback() {
//                                @Override
//                                public void onFinished(Context context) {
//
//                                }
//                            });
//                        }
//                    }
//                });
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
//                if(BuildConfig.DEBUG){
//                    QWebViewActivity.navigateUrl(this,"http://192.168.6.28:8080/uusports/huya/introduction.html");
//                    break;
//                }
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
            String arrayName[] = this.videoData.getName().split("\\|");
            if (array == null || array.length <= 1) {
                array = this.videoData.getVideoUrl().split("\\|");
            }
            int index = playIndex;
            if(index >= 0 && index < array.length)
            {
                ArrayList<ProgramInfoBase> list = new ArrayList<>();
                for(int i = 0;i < array.length && i < arrayName.length; i++) {
                    String url = array[i];
                    Uri uri = Uri.parse(url);
                    String programId = uri.getQueryParameter("pkId");
                    String channelId = uri.getQueryParameter("channelId");
                    ProgramInfoBase programInfoBase = new ProgramInfoBase();
                    programInfoBase.setName(arrayName[i]);
                    programInfoBase.setChannelId(StringUtils.intValueOfString(channelId));
                    programInfoBase.setPkId(StringUtils.intValueOfString(programId));
                    programInfoBase.setMultiSetType("0");
                    list.add(programInfoBase);
                 }
                PlayVideoActivity.play(this, list, index, false);

//                NetworkService.defaultService().fetchProgramContent(this, 0,
//                        "0", 0,url,new JsonCallback<BaseResponse<ProgramContent>>() {
//                            @Override
//                            public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
//                                BaseResponse<ProgramContent> data = response.body();
//                                if (data.isOk()) {
//                                    ArrayList<ProgramInfoBase> list = new ArrayList<>();
//                                    list.add(data.getData());
//                                    PlayVideoActivity.play(NewHomeActivity.this, list, 0, false);
//                                }
//                            }
//                        });
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

        String statistics = getIntent().getStringExtra("statistics");
        String vodId = getIntent().getStringExtra("cp_video_id");
        String goNewActivity = getIntent().getStringExtra("goNewActivity");
        String cp_data = getIntent().getStringExtra("cp_data");
        String last_position = getIntent().getStringExtra("position");
        Log.d(TAG, "checkIntentData:"+"programId"+programId+"multisetType"+multisetType+"channelId"+channelId+"albumId"+albumId+"topicId"+topicId+"styleId"+styleId +
                "/n  cp_video_id:"+vodId + "cp_data :"+cp_data+" last_position:"+last_position);

        if(programId!=null && multisetType!=null && channelId!=null) {
            needEnterRecommend = false;
            NetworkService.defaultService().fetchProgramContent(this, StringUtils.intValueOfString(programId),
                    multisetType, StringUtils.intValueOfString(channelId),"",new JsonCallback<BaseResponse<ProgramContent>>() {
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
            stat("外流好推荐进入合集",INDEX);
            MediaAlbumActivity.show(this, StringUtils.intValueOfString(albumId));
        }
        if(topicId != null ) {
            needEnterRecommend = false;
            stat("外流好推荐进入专题",INDEX);
            TopicActivity.show(this, topicId, styleId);
        }
        if (!TextUtils.isEmpty(vodId)){

            Intent intent = new Intent(this,PlayVideoActivity.class);
            intent.putExtra("vodId",vodId);
            startActivity(intent);
//            NetworkService.defaultService().fetchProgramContent(this, 0,
//                    "0", 0,vodId,new JsonCallback<BaseResponse<ProgramContent>>() {
//                        @Override
//                        public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
//                            BaseResponse<ProgramContent> data = response.body();
//                            Log.d(TAG, "onSuccess: "+data.getData().toString());
//                            if (data.isOk()) {
//                                ArrayList<ProgramInfoBase> list = new ArrayList<>();
//                                list.add(data.getData());
//                                PlayVideoActivity.play(NewHomeActivity.this, list, 0, false);
//                            }
//                        }
//                    });
        }

    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: "+ HuyaApplication.hadBuy());
        clearCache();
        alertForQuit();
//        if (!HuyaApplication.hadBuy()) {
//
//            if(DiffConfig.CurrentPurchase instanceof GZTVPurchase) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        GZTVPurchase purchase = (GZTVPurchase) DiffConfig.CurrentPurchase;
//                        purchase.tryBest(NewHomeActivity.this, new GZTVPurchase.TryBestCallback() {
//                            @Override
//                            public void d(String s) {
//
//                            }
//
//                            @Override
//                            public void success(String s) {
//
//                            }
//
//                            @Override
//                            public void fail(String s) {
//
//                            }
//                        });
//
//                    }
//                }).start();
//            }
//            showQuitRecommend();
//
//        } else {
//            alertForQuit();
//        }
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
                        TopWayBroacastUtils.getInstance().pressReturnKey(NewHomeActivity.this);
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
                            if(!"".equals(logoImageUrl)) {
                                Glide.with(NewHomeActivity.this).load(logoImageUrl).into(ivHomeLogo);
                            }
                            if(!"".equals(bgImageUrl)){
                                Glide.with(NewHomeActivity.this).load(bgImageUrl).into(homeBg);
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
//        if (resultCode == RESULT_OK) {
//            clearCache();
//            turnHome();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!DiffConfig.CurrentPurchase.isPurchased()){
            DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
                @Override
                public void onFinished(String message) {
                    if(BuildConfig.DEBUG){
                        Toast.makeText(NewHomeActivity.this, "HomePage auth finished: " + (DiffConfig.CurrentPurchase.isPurchased() ? "is Purchased" : "not Purchased"), Toast.LENGTH_LONG).show();
                    }
                    if(DiffConfig.CurrentPurchase.isPurchased()){
                        towayHomeHandle.sendEmptyMessage(999);
                    }else {
                        if(DiffConfig.CurrentTVBox instanceof TOPWAYBox){
                            TOPWAYPurchase purchase = new TOPWAYPurchase();
                            purchase.tryBest(NewHomeActivity.this, new TOPWAYPurchase.TryBestCallback() {
                                @Override
                                public void d(String s) {

                                }
                            });
                        }
                    }

                }
            });
        }

        playingTitle = "弹出订购-订购菜单";
        stat("首页",INDEX);
        if (!DiffConfig.validateDeviceKeyNO(this)) {
            return;
        }
        if(fistLoadPlayer == 0){
            fistLoadPlayer =1;
        }else if(fistLoadPlayer == 1){
            fistLoadPlayer = 2;
        }
        towayHomeHandle.sendEmptyMessageDelayed(999,4000);
        videoLoad.setVisibility(View.VISIBLE);
        playVideo();
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction()==KeyEvent.ACTION_DOWN) {
            if (focusView != null && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
                if (focusView.getId() == R.id.btn_fl_8 || focusView.getId() == R.id.btn_fl_9 || focusView.getId() == R.id.btn_fl_10|| focusView.getId() == R.id.btn_fl_11|| focusView.getId() == R.id.btn_fl_12|| focusView.getId() == R.id.btn_fl_13) {
                    setUIPage();
                }

            }
            if (focusView != null) {
                if (focusView.getId() == R.id.btn_fl_8 || focusView.getId() == R.id.btn_fl_9 || focusView.getId() == R.id.btn_fl_10|| focusView.getId() == R.id.btn_fl_11|| focusView.getId() == R.id.btn_fl_12|| focusView.getId() == R.id.btn_fl_13) {
                    if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
                        homeScrollView.setFadingEdge(50);
                    }
                }


            }
        }
//        if (focusView.getId() == R.id.btn_fl_16 || focusView.getId() == R.id.btn_fl_17 || focusView.getId() == R.id.btn_fl_18|| focusView.getId() == R.id.btn_fl_19){
//            if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN){
//               // return false;
//            }
//        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onDuration(long l) {
        super.onDuration(l);
        videoLoad.setVisibility(View.GONE);
    }
    private void setUIPage() {
        homeScrollView.setFadingEdge(480);
        if(fistLoadPlayer == 2) {
            vvSmall.resume();
            fistLoadPlayer = 1;
        }
    }
}
