package com.utvgo.handsome.utils;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.interfaces.JsonCallback;

import okhttp3.MediaType;

public class NetworkUtils {
    public static <T> void get(final Context context, String url, JsonCallback<T> callback)
    {
        String requestUrl = url;
        if(requestUrl.indexOf("?") < 0)
        {
            requestUrl += "?";
        }
        else
        {
            requestUrl += "&";
        }
        requestUrl += "keyNo=" + DiffConfig.getCA(context) + "&regionCode=" + DiffConfig.getRegionCode(context) + "&vipCode=" + AppConfig.VipCode;
        OkGo.<T>get(requestUrl).cacheMode(CacheMode.NO_CACHE).tag(context).execute(callback);
    }
    public static <T> void post(final Context context, String url,String params, JsonCallback<T> callback){
        OkGo.<T>post(url).cacheMode(CacheMode.NO_CACHE).tag(context).upString(params)
                .execute(callback);
    }


}
