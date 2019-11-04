package com.utvgo.handsome.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.utvgo.handsome.utils.GuizhouUtils;
import com.utvgo.handsome.utils.XLog;
import com.utvgo.huya.activity.GZBNPayContinuityUnifiedActivity;
import com.utvgo.huya.utils.NetUtils;
import com.utvgo.huya.utils.Tools;

import java.util.HashMap;
import java.util.Map;


public class GuizhouPayReceiver extends BroadcastReceiver{
    private static final String TAG = "ResultReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String session_id = intent.getStringExtra("session_id");
            int state = intent.getIntExtra("state", -1);
            String reason = intent.getStringExtra("reason");
            Log.d("GuizhouPayReceiver", "onReceive: ");
            if (state==0){
                Log.d("GuizhouPayReceiver", "onReceive: Sucess");
                callBackPayBuy(context,state);
            }else{
                Log.d("GuizhouPayReceiver", "onReceive: Failed");
                callBackPayBuy(context,state);
            }
        }
    }
    private void callBackPayBuy(Context context,int flag) {
        String user_id = Tools.getStringPreference(context, GuizhouUtils.TagGuizhouUid);
        String backState=String.valueOf(flag);
        Log.d(TAG, "callBackPayBuy: "+user_id);
        Map<String, String> params = new HashMap<>();
        params.put("uid", user_id);
        params.put("productsId", "510327");
        params.put("sourceType", "0");
        params.put("session_id", "");
        params.put("state", backState);
        params.put("reason", "");
        Log.d(TAG, "callBackPayBuy: recevier");
        NetUtils.getData(context, GuizhouUtils.callbackSaveAuthorizeUrl,
                GuizhouUtils.GetGuizhouCallbackSaveAuthorize, params, null, null, null);
    }
}
