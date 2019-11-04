package com.utvgo.handsome.diff;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.utvgo.handsome.bean.BeanGuizhouAuthAndPlayUrl;
import com.utvgo.handsome.bean.BeanPayInterface;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.handsome.utils.GuizhouUtils;
import com.utvgo.handsome.utils.PlatfromUtils;
import com.utvgo.huya.activity.GZBNPayContinuityUnifiedActivity;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.NetUtils;
import com.utvgo.huya.utils.StrTool;
import com.utvgo.huya.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GZBNPurchase extends IPurchase {
    public static String  selectProducts="510327";
    public PlatfromUtils platfromUtils=new PlatfromUtils();
    private Uri authoritiesURI = Uri.parse("content://com.starcor.gzgd.app.authorities/token");
    public static String TAG="wzb";
    public HiFiDialogTools hiFiDialogTools = new HiFiDialogTools();
    @Override
    public void pay(Context context, CommonCallback callback) {
        Log.d(TAG, "pay: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>pay");
        GZBNPayContinuityUnifiedActivity.show((Activity) context);
    }

    @Override
    public void auth(final Context context, final AuthCallback authCallback) {
        Log.d(TAG, "auth: ------------------------------------------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if(platfromUtils.isFML1()){
            GZBNBox.getFML1DeviceInfo(context);
            GZBNBox.getFML1WebToken(context);
            GZBNBox.queryAreaCode(context);
        }else {
            GZBNBox.getFML2DeviceInfo(context);
//            if (platfromUtils.isFuMuLe2()){
//            GZBNBox.bindBoxService(context);
//           }
        }

        Map<String, String> params =getReqParam(GuizhouUtils.payVodId,context,"0");
        NetUtils.getData(context, GuizhouUtils.utvgoAuthUrl, GuizhouUtils.GetGuizhouUtvgoAuth, params,
                BeanGuizhouAuthAndPlayUrl.class, null, new NetUtils.NetCallBack() {
                    @Override
                    public void netBack(int requestTag, Object object) {
                        if (requestTag == GuizhouUtils.GetGuizhouUtvgoAuth && object != null) {
                            BeanGuizhouAuthAndPlayUrl beanGuizhouAuthAndPlayUrl = (BeanGuizhouAuthAndPlayUrl) object;
                            if (beanGuizhouAuthAndPlayUrl != null && beanGuizhouAuthAndPlayUrl.getResult() != null) {
                                if ("0".equals(beanGuizhouAuthAndPlayUrl.getResult().getState())) {
                                    Log.d(TAG, "netBack: 订购成功"+beanGuizhouAuthAndPlayUrl.getResult().getState());
                                   setOrderStatus(0);
//                                    if (!TextUtils.isEmpty(selectProducts)&&platfromUtils.isFML1()) {
//////                                        callBackPayBuy(context);
//////                                    }
                                }
                            }
                        }
                        if(authCallback != null){
                            authCallback.onFinished("");
                        }
                    }
                });
    }

    @Override
    public void refreshOrderStatus(Context context, AuthCallback callback) {
        auth(context,callback);
    }

    public  Map<String, String> getReqParam(String vodId,Context context,String videoType) {
        String webtoken = Tools.getStringPreference(context, GuizhouUtils.TagGuizhouWebtoken);
        String user_id = Tools.getStringPreference(context, GuizhouUtils.TagGuizhouUid);
        String version = Tools.getStringPreference(context, GuizhouUtils.TagGuizhouVersion);
        Map<String, String> params = new HashMap<>();
        params.put("nns_func", "check_auth_and_get_media_by_media");
        params.put("nns_user_id", user_id);
        params.put("nns_cp_id", GuizhouUtils.cpid);
        params.put("nns_video_id", vodId);
        params.put("nns_version", version);
        params.put("nns_webtoken", webtoken);
        if (platfromUtils.isFuMuLe2() && videoType.equals("0")){
            params.put("nns_service_type", "ipqam");
            params.put("nns_ipqam_area_code",Tools.getStringPreference(context, GuizhouUtils.TagGuizhouAreaCode));
        }
        if(platfromUtils.isFuMuLe2()||platfromUtils.isFML1()){
            params.put("nns_area_code",Tools.getStringPreference(context, GuizhouUtils.TagGuizhouAreaCode));
        }
        params.put("nns_cp_video_id", vodId); //CP注入视频ID
        params.put("nns_video_type", videoType);//nonono
        params.put("nns_output_type", "json");//返回数据格式

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String timestamp = df.format(new Date());// new Date()为获取当前系统时间
        params.put("nns_timestamp", timestamp);
        String nns_auth = GZBNBox.startAES(GuizhouUtils.authKey, GuizhouUtils.authKey + GuizhouUtils.cpid
                + user_id + vodId + timestamp);
        params.put("nns_auth", nns_auth);
        return params;
    }

    private void callBackPayBuy(Context context) {
        String user_id = Tools.getStringPreference(context, GuizhouUtils.TagGuizhouUid);
        Log.d(TAG, "callBackPayBuy: "+user_id);
        Map<String, String> params = new HashMap<>();
        params.put("uid", user_id);
        params.put("productsId", selectProducts);
        params.put("sourceType", "0");
        params.put("session_id", "");
        params.put("state", "0");
        params.put("reason", "");
        NetUtils.getData(context, GuizhouUtils.callbackSaveAuthorizeUrl,
                GuizhouUtils.GetGuizhouCallbackSaveAuthorize, params, null, null, null);
    }


}
