package com.utvgo.huya.activity;

//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//
//import com.maywide.paysdk.PayActivity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.utvgo.handsome.utils.GuizhouUtils;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.huya.interfaces.BuyInterface;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.NetUtils;
import com.utvgo.huya.utils.ToastUtil;
import com.utvgo.huya.utils.Tools;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.utvgo.handsome.diff.GZBNPurchase.selectProducts;


public class BuyActivity extends PlayGuangDongActivity implements BuyInterface {
    public boolean needShowBuy = false;
    public boolean needShowHadBuy = false;
    private String authVodid = "";


    private void sendUtvgoAuthReq(String vodId) {
        final Context context = this;
        try{
            DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
            @Override
            public void onFinished(String message) {
                final boolean isPurchased = HuyaApplication.hadBuy();
                if(isPurchased)
                {
                    if (needShowHadBuy) {
                        HiFiDialogTools.getInstance().showtips(BuyActivity.this, "你已订购过了", null);
                    }

                    if (!TextUtils.isEmpty(authVodid)) {
                        hahaPlayUrl(authVodid);
                    } else {
                        ToastUtil.show(context, "你已是虎牙TV的会员");
                    }
                } else {
                    if(needShowBuy)
                    {
                        hahaPausePlay();

                        DiffConfig.CurrentPurchase.pay(context, new CommonCallback() {
                            @Override
                            public void onFinished(Context context) {
                                DiffConfig.CurrentPurchase.refreshOrderStatus(context, null);
                            }
                        });
                    }
                }
            }
        });
        }catch (Exception e){
            Log.d(TAG, "sendUtvgoAuthReq:订购授权报错 ");
        }
    }
//
    @Override
    public void auth(String vodID, boolean needShowBuy) {
        authVodid = vodID;
        this.needShowBuy = needShowBuy;
        needShowHadBuy = true;
        if (needShowBuy) {
            sendUtvgoAuthReq(vodID);
        } else {
            getHahaPlayerUrl(vodID);
        }
    }

    @Override
    public void authOnly() {
        this.needShowBuy = false;
        sendUtvgoAuthReq("");
    }

    @Override
    public void showBuy(String vodID) {
        authVodid = vodID;
        needShowBuy = true;
        needShowHadBuy = false;
        sendUtvgoAuthReq(vodID);
    }



    @Override
    public void authFinish(boolean hadBuy, boolean needShowBuy) {

    }
    public static String getUUID(Context context) {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
    public void callBackPayBuy() {
        String user_id = Tools.getStringPreference(BuyActivity.this, GuizhouUtils.TagGuizhouUid);
        Map<String, String> params = new HashMap<>();
        params.put("uid", user_id);
        params.put("productsId", selectProducts);
        params.put("sourceType", "0");
        params.put("session_id", "");
        params.put("state", "0");
        params.put("reason", "");
        NetUtils.getData(BuyActivity.this, GuizhouUtils.callbackSaveAuthorizeUrl,
                GuizhouUtils.GetGuizhouCallbackSaveAuthorize, params, null, null, null);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PayActivity.REQUESTCODE &&
//                resultCode == PayActivity.RESULTCODE) {
//            Bundle bundle = data.getExtras();
//            //拿到订单号
//            String orderNo = bundle.getString(PayActivity.ORDERNO);
//            //拿到支付结果  0代表支付成功,1代表支付失败
//            int payResultCode = bundle.getInt(PayActivity.PAYRESULTCODE, -1);
//            //拿到页面通知地址
//            String redirectUrl = bundle.getString(PayActivity.REDIRECTURL);
//            //拿到信息描述
//            String payInfo = bundle.getString(PayActivity.PAYINFO);
//            callBack(orderNo, payResultCode + "", payInfo);
//
//            if(payResultCode == 0)
//            {
//                DiffConfig.CurrentPurchase.refreshOrderStatus(this,null);
//                ToastUtil.showInCenter(this, "订购成功");
//            }
//            else
//            {
//                String message = "订购失败";
//                if(!TextUtils.isEmpty(payInfo))
//                {
//                    message += "原因： ";
//                }
//                if(payInfo != null && payInfo.indexOf("取消订购") >= 0)
//                {
//
//                }
//                else
//                {
//                    message += "请致电广电客服电话或到当地营业厅咨询办理";
//                }
//                ToastUtil.showInCenter(this, message);
//            }
//        }
//    }
//
//    protected void callBack(String orderNo, String state, String msg) {
//        NetWork.hntvPurchasedCallBack(orderNo, state, msg, new Observer<ResponseBody>() {
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
//            public void onNext(ResponseBody o) {
//
//            }
//        });
//    }v

}
