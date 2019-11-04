package com.utvgo.huya.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.utvgo.handsome.bean.BeanPayInterface;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.utils.GuizhouUtils;
import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.utils.NetUtils;
import com.utvgo.huya.utils.StrTool;
import com.utvgo.huya.utils.Tools;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GZBNPayContinuityUnifiedActivity extends BuyActivity {
    public static String  selectProducts="510327";
    public static final int ReturnCode = 5600;
    private static final int GO_BUY = 2333;
    @BindView(R.id.btn_qiandai)
    Button btnQiandai;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.rl_buy_now)
    RelativeLayout rlBuyNow;
    @BindView(R.id.iv_paywx)
    ImageView ivPaywx;
    @BindView(R.id.ll_wx_pay)
    LinearLayout llWxPay;
    @BindView(R.id.btn_home)
    Button btnHome;
    @BindView(R.id.ll_buySuccess)
    FrameLayout llBuySuccess;
    @BindView(R.id.rl_buy_result)
    RelativeLayout rlBuyResult;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GO_BUY:
                    getPayUrl();
                    break;
            }
        }
    };
    public static void show(final Activity activity){
        Intent intent=new Intent(activity,GZBNPayContinuityUnifiedActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: GZBNPayContinuityUnifiedActivity");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payfumule1);
        ButterKnife.bind(this);
        mHandler.sendEmptyMessageDelayed(GO_BUY,5000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(platfromUtils.isFML1()){
         DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback(){

            @Override
            public void onFinished(String message) {
                if(HuyaApplication.hadBuy()){
                    callBackPayBuy(GZBNPayContinuityUnifiedActivity.this,0);
                }else{
                    callBackPayBuy(GZBNPayContinuityUnifiedActivity.this,1);
                }
            }
        });
        }else {
            DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
                @Override
                public void onFinished(String message) {

                    Toast.makeText(GZBNPayContinuityUnifiedActivity.this, "你 " + (DiffConfig.CurrentPurchase.isPurchased() ? "是尊贵会员了" : "还不是会员"), Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    @Override
    public void netBack(int requestTag, Object object) {
        super.netBack(requestTag, object);
        if (requestTag == GuizhouUtils.GetGuizhouUtvgoAuth) {
            if (HuyaApplication.hadBuy()){
                callBackPayBuy();
                finish();
            }
        }
    }
    private void callBackPayBuy(Context context, int flag) {
        String user_id = Tools.getStringPreference(context, GuizhouUtils.TagGuizhouUid);
        String backState=String.valueOf(flag);
        Log.d(TAG, "callBackPayBuy: "+user_id);
        Map<String, String> params = new HashMap<>();
        params.put("uid", user_id);
        params.put("productsId", selectProducts);
        params.put("sourceType", "0");
        params.put("session_id", "");
        params.put("state", backState);
        params.put("reason", "");
        Log.d(TAG, "callBackPayBuy: recevier");
        NetUtils.getData(context, GuizhouUtils.callbackSaveAuthorizeUrl,
                GuizhouUtils.GetGuizhouCallbackSaveAuthorize, params, null, null, null);
    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_qiandai:
                if (mHandler.hasMessages(GO_BUY)) {
                    mHandler.removeMessages(GO_BUY);
                }
                mHandler.sendEmptyMessage(GO_BUY);
                break;
            case R.id.btn_back:
               GZBNPayContinuityUnifiedActivity.this.finish();

//                        Intent mIntent = new Intent("com.gzgd.webapp.pay.result");
//                        mIntent.putExtra("messageSend", "模拟支付返回码为1");
//                        sendBroadcast(mIntent);
//                        finish();


                break;
        }
    }

    private void getPayUrl() {
        String proxyPathUrl = GuizhouUtils.proxyUrl + StrTool.getURLEncoder(GuizhouUtils.payInterfaceUrl);
        //先通过接口获取支付接口
        NetUtils.getData(GZBNPayContinuityUnifiedActivity.this, proxyPathUrl,
                GuizhouUtils.GetGuizhouGetPayInterfaceUrl, null, BeanPayInterface.class, new NetUtils.NetCallBack() {
                    @Override
                    public void netBack(int requestTag, Object object) {
                        if (object != null) {
                            BeanPayInterface beanPayInterface = (BeanPayInterface) object;
                            if (beanPayInterface != null) {
                                if (TextUtils.equals("0", beanPayInterface.getRet())){
                                    if (beanPayInterface.getDatas() != null) {
                                        for (int i = 0; i < beanPayInterface.getDatas().size(); i++) {
                                            BeanPayInterface.DatasBean datasBean = beanPayInterface.getDatas().get(i);
                                            if (TextUtils.equals("PAY_ADDR", datasBean.getPramKey())) {
                                                startPay(datasBean.getPramValue());
                                                break;
                                            }
                                        }
                                    }
                                }else{
                                    hiFiDialogTools.showtips(GZBNPayContinuityUnifiedActivity.this, beanPayInterface.getRetInfo(), null);
                                }
                            }else{
                                hiFiDialogTools.showtips(GZBNPayContinuityUnifiedActivity.this, "获取支付地址出错", null);
                            }
                        }
                    }
                });
    }

    private void startPay(String payUrl) {
        String sid = getUUID(this);  //否 String session_id，此次支付请求的唯一标识
        String pid = "510327"; //否 String product_id
        String cp_id = GuizhouUtils.cpid; //否 String cp_id
        String vid = ""; // 是 String vedio_id
        String vnm = ""; // 是 String vedio_name
        String pkg = getPackageName(); // 否 String package_name用来接收支付广播的应用包名

        if (payUrl.contains("?")) {
            payUrl = payUrl + "&";
        } else {
            payUrl = payUrl + "?";
        }
        payUrl = payUrl + "sid=" + sid + "&pid=" + pid + "&pkg=" + pkg + "&cp_id=" + cp_id;
        Log.e("startPay", "startPay: payUrl-->" + payUrl);
        String action = "com.gzgd.webapp.start";

        if (platfromUtils.isFML1()) {
//            String command = "am start -e commandStr " + payUrl + " com.coship.mmcp510/.MMCP510Activity";
 //           ShellUtils.execCommand(command, false);

            ComponentName cn = new ComponentName("com.coship.mmcp510","com.coship.mmcp510.MMCP510Activity") ;
            Intent intent = new Intent();
            intent.setComponent(cn);
            intent.putExtra("commandStr", payUrl);
            startActivity(intent);

        } else {
            Intent intent = new Intent(action);
            intent.putExtra("url", payUrl);
            startActivity(intent);
        }
    }


}
