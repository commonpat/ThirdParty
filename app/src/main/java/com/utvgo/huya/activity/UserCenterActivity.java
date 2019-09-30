package com.utvgo.huya.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.utvgo.huya.GlideApp;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanArryPage;
import com.utvgo.huya.diff.DiffConfig;
import com.utvgo.huya.diff.IPurchase;
import com.utvgo.huya.interfaces.CommonCallback;
import com.utvgo.huya.utils.ActivityUtility;
import com.utvgo.huya.utils.Appconfig;
import com.utvgo.huya.utils.FocusView;
import com.utvgo.huya.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by fute on 17/3/24.
 */

public class UserCenterActivity extends BuyActivity {

    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.btn_playRecord)
    Button btnPlayRecord;
    @BindView(R.id.btn_order)
    Button btnOrder;
    @BindView(R.id.btn_view0)
    ImageView btnView0;
    @BindView(R.id.btn_view1)
    ImageView btnView1;
    @BindView(R.id.btn_view2)
    ImageView btnView2;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_keyNo)
    TextView tvKeyNo;
    @BindView(R.id.tv_area)
    TextView tvArea;
    //@BindView(R.id.buttons_binding)
    //LinearLayout buttonsBinding;

    //@BindView(R.id.my_song_list)
    //Button mySongList;

    private String code = "";


    FocusView focus;
    BeanArryPage beanArryPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        needBringFront = false;
        setContentView(R.layout.activity_user_center);
        ButterKnife.bind(this);
        focus = findViewById(R.id.focusView);
        focus.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
        //traversalView(this);
        //createBorderView(this);


        //btnHadBuy.setVisibility(View.GONE);
        //   asyncHttpRequest.getUserInfo(this,null,this,this);
        asyncHttpRequest.getArryPage(this, null, this, this);
        String areaName = Appconfig.getRegionCode(this);

        tvUserName.setText("户 主 ：***");

        tvArea.setText("地 区 ：" + areaName);
        tvVersion.setText("版本号：" + getLocalVersionName());

        String keyNO = Appconfig.getKeyNo(this);
        tvKeyNo.setText("卡 号 ：" + keyNO);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showViewByHandler(btnOrder);
            }
        });
    }

    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        focus.setFocusView(newFocus, R.mipmap.border_focus_style_default);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        super.onFocusChange(v, hasFocus);
    }


    /**
     * 获取本地软件版本号名称
     */
    public String getLocalVersionName() {
        String localVersion = "";
        try {
            PackageInfo packageInfo = getApplicationContext().getPackageManager()
                    .getPackageInfo(getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        stat("个人中心");
//
//        final Context context = this;
//        DiffConfig.CurrentPurchase.refreshOrderStatus(this, new IPurchase.AuthCallback() {
//            @Override
//            public void onFinished(String message) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //已订购则隐藏按钮
//                        final boolean isPurchased = DiffConfig.CurrentPurchase.isPurchased();
//                        btnOrder.setVisibility(isPurchased ? View.GONE : View.VISIBLE);
//                        tvOrderState.setText(isPurchased ? "订购状态 ：已订购" : "订购状态 ：未订购");
//                        XLog.toast(context, isPurchased ? "is Purchased" : "not Purchased");
//
//                        String expireDate = "";
//                        if(DiffConfig.CurrentPlatform == Platform.hncatv)
//                        {
//                            if(DiffConfig.CurrentPurchase instanceof HNTVPurchase)
//                            {
//                                HNTVPurchase purchase = (HNTVPurchase)DiffConfig.CurrentPurchase;
//                                if (!purchase.getUserOrderProductInfoList().isEmpty()) {
//                                    UserOrderProductInfo userOrderProductInfo = purchase.getUserOrderProductInfoList().get(0);
//                                    expireDate = userOrderProductInfo.getExpireDate();
//                                    XLog.toast(context, "Product name" + userOrderProductInfo.getProductName() + " expireDate " + expireDate);
//                                }
//                            }
//                        }
//
//                        if (!TextUtils.isEmpty(expireDate)) {
//                            expireDate = "到期时间：" + expireDate;
//                            tvOrderLimitTime.setText(expireDate);
//                            tvOrderLimitTime.setVisibility(View.VISIBLE);
//                        } else {
//                            tvOrderLimitTime.setVisibility(View.INVISIBLE);
//                            XLog.toast(context, "Warning product expireDate is empty");
//                        }
//                    }
//                });
//            }
//        });
//    }

    @OnClick({R.id.btn_playRecord, R.id.btn_order, R.id.btn_view0, R.id.btn_view1, R.id.btn_view2 /*,R.id.ll_userInfo,,R.id.my_song_list*/})
    public void onClick(View view) {
        String keyNO = Appconfig.getKeyNo(this);
        final Context context = this;

        int i = view.getId();
        if (i == R.id.btn_playRecord) {
            Intent intent = new Intent(this, PlayRecordActivity.class);
            intent.putExtra("type", 0);
            startActivity(intent);

        } else if (i == R.id.btn_order) {
            DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
                @Override
                public void onFinished(String message) {
                    if (HuyaApplication.hadBuy()) {
                        ToastUtil.show(context, "您已经是 " + getResources().getString(R.string.app_name) + " 尊贵会员");
                    } else {
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtil.show(context, message);
                        }
                        DiffConfig.CurrentPurchase.pay(context, new CommonCallback() {
                            @Override
                            public void onFinished(Context context) {

                            }
                        });
                    }
                }
            });
        } else if (i == R.id.btn_view0) {
            clickRecItem(0);
        } else if (i == R.id.btn_view1) {
            clickRecItem(1);
        } else if (i == R.id.btn_view2) {
            clickRecItem(2);
        }
    }

    public void clickRecItem(int index) {
        if (!DiffConfig.validateDeviceKeyNO(this)) {
            return;
        }
        Log.d(TAG, "clickRecItem: " + beanArryPage.getData().get(index).get(0));
        BeanArryPage.Data selectBean = beanArryPage.getData().get(index).get(0);
        if (TextUtils.equals(selectBean.getHrefType(), "0")) { //超链接
            ActivityUtility.goWebActivityActivity(this, selectBean.getHref());
        } else if (TextUtils.equals(selectBean.getHrefType(), "3")) { //mv专辑
            Intent intent = new Intent(this, MVAlbumActivity.class);
            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("pkId"));
            startActivity(intent);

//        } else if (TextUtils.equals(selectBean.getHrefType(), "8")) { //活动
//            //Intent intent = new Intent(IndexActivity.this, ActivityActivity.class);
//            //startActivity(intent);
//            //todo

        } else if (TextUtils.equals(selectBean.getHrefType(), "4")) { //专题
            Intent intent = new Intent(this, TopicActivity.class);
            intent.putExtra("topicId", Uri.parse(selectBean.getHref()).getQueryParameter("themId"));
            intent.putExtra("type", Uri.parse(selectBean.getHref()).getQueryParameter("styleID"));
            startActivity(intent);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "12")) {//专题收录
//            ActivityUtility.goActivity(this, TopicCollectionActivity.class);
//        } else {
//            //视频播放
//            ArrayList<BeanUserPlayList.DataBean> playHistoryList = new ArrayList<>();
//            getHrefList(index, selectBean, playHistoryList);
//            Intent intent = new Intent(this, PlayVideoActivity.class);
//            if (index == 1) {
//                intent.putExtra("playIndex", vodIdPlayIndex);
//            } else {
//                intent.putExtra("playIndex", 0);
//            }
//            intent.putExtra("fileType", 1);
//            intent.putParcelableArrayListExtra("playList", playHistoryList);
//            this.startActivity(intent);
//        }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_0:
                    code = code + "0";
                    break;
                case KeyEvent.KEYCODE_1:
                    code = code + "1";
                    break;
                case KeyEvent.KEYCODE_2:
                    code = code + "2";
                    break;
                case KeyEvent.KEYCODE_3:
                    code = code + "3";
                    break;
                case KeyEvent.KEYCODE_4:
                    code = code + "4";
                    break;
                case KeyEvent.KEYCODE_5:
                    code = code + "5";
                    break;
                case KeyEvent.KEYCODE_6:
                    code = code + "6";
                    break;
                case KeyEvent.KEYCODE_7:
                    code = code + "7";
                    break;
                case KeyEvent.KEYCODE_8:
                    code = code + "8";
                    break;
                case KeyEvent.KEYCODE_9:
                    code = code + "9";
                    break;
            }
            checkCode();
        }
        return super.dispatchKeyEvent(event);
    }

    private void checkCode() {
        if ("0123".equals(code)) {
            code = "";
            //Intent intent = new Intent(this, MemberSingleActivity.class);
            //startActivity(intent);
        }
    }

    private int requestCode = 888;

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        super.onSucceeded(method, key, object);
        switch (method) {
            case "getUserInfo.utvgo":
                break;
            case "arryPage.utvgo":
                if (object instanceof BeanArryPage) {
                    beanArryPage = (BeanArryPage) object;
                    ImageView[] imageViews = new ImageView[]{btnView0, btnView1, btnView2};
                    for (int i = 0; i < 3; i++) {
                        Log.d(TAG, "onSucceeded: getArryPage" + beanArryPage.getImageProfix() + beanArryPage.getData().get(i).get(0).getImgUrl() + imageViews[i]);
                        GlideApp.with(this).load(beanArryPage.getImageProfix() + beanArryPage.getData().get(i).get(0).getImgUrl()).into(imageViews[i]);
                    }
                }
                break;

        }
//            case "getWxTwoDimensionCode.utvgo":
//                if (object instanceof BeanWxTwoDimensionCode) {
//                    BeanWxTwoDimensionCode beanWxTwoDimensionCode = (BeanWxTwoDimensionCode) object;
//                    if (beanWxTwoDimensionCode.getMsg().equals("ok") && beanWxTwoDimensionCode.getSub_ret() == 0) {
//                        ActivityUtility.wxLogin(UserCenterActivity.this, beanWxTwoDimensionCode.getWx_two_dimension_code(), requestCode);
//                    }
//                }
//                break;
//            case "wxLogin.utvgo":
//                if (object instanceof BeanWxLogin) {
//                    BeanWxLogin beanWxLogin = (BeanWxLogin) object;
//                    if (beanWxLogin.getMsg().equals("ok") && beanWxLogin.getSub_ret() == 0) {
//                        showUnBindButton();
//                    }
//                }
//                break;
//            case "getUserBindingInfo.utvgo":
//                if (object instanceof BeanUserBindingInfo) {
//                    BeanUserBindingInfo beanUserBindingInfo = (BeanUserBindingInfo) object;
//                    //beanUserBindingInfo.getCode() 1已绑定，2未绑定，0系统异常
//                    if (beanUserBindingInfo.getCode().equals("1")) {
//                        showUnBindButton();
//                    } else if (beanUserBindingInfo.getCode().equals("2")) {
//                        showBindButton();
//                    }
//                }
//                break;
//            case "cancelBinding.utvgo":
//                if (object instanceof BeanResult) {
//                    if (((BeanResult) object).getCode().equals("1")) {
//                        showBindButton();
//                    }
//                }
//                break;
//        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.requestCode && data != null) {
            Log.e(TAG, "onActivityResult: " + data.getStringExtra("code"));
            // if (!TextUtils.isEmpty(data.getStringExtra("code")))
            // asyncHttpRequest.wxLogin(data.getStringExtra("code"), this, this);
        }
    }
}
