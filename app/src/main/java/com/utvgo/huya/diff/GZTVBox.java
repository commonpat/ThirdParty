package com.utvgo.huya.diff;

import android.content.Context;
import android.text.TextUtils;

import com.utvgo.huya.utils.Appconfig;
import com.utvgo.huya.utils.Tools;
import com.utvgo.huya.utils.XLog;


public class GZTVBox implements ITVBox {
    @Override
    public String getCA(Context context) {
       String keyNo= Tools.getStringPreference(context, "KEYNO");
       XLog.d("keyNo:"+keyNo);
       if (!TextUtils.isEmpty(keyNo)){
           return keyNo;}
        return "8002003224274725";
    }

    @Override
    public String getRegionCode(Context context) {
        String regionCode = Appconfig.getRegionCode(context);
        if (!TextUtils.isEmpty(regionCode)){
            return regionCode;
        }
        return "12000";
    }

    @Override
    public void fetchUrlByVODAssetId(final Context context, String vodId, FetchUrlByVODAssetIdCallBack callback) {

    }

}
