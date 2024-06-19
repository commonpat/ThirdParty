package com.utvgo.handsome.diff;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.ipanel.join.cq.vodauth.IAuthService;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.bean.BeanGetPlayUrl;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.PlatfromUtils;
import com.utvgo.huya.utils.Tools;


import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

/**
 * @author wzb
 * @description:
 * @date : 2023/5/12 17:07
 */
public class CHONGQINBox implements ITVBox {
    private static String SERVICE_ACTION = "com.ipanel.join.cq.vodauth.IAuthService";

    private PlatfromUtils platfromUtils;
    private Context context;

    @Override
    public String getCA(Context context) {
        // 获取CA卡号的信息
        String keyNo = Tools.getStringPreference(context, "card");
        if (TextUtils.isEmpty(keyNo)) {
            keyNo = "23818810000000216";
        }
        return keyNo;
    }

    @Override
    public String getRegionCode(Context context) {
        return "1";
    }

    @Override
    public String getLoginToken(Context context) {
        String token = Tools.getStringPreference(context, "token");
        return token;
    }

    @Override
    public String getDeviceModel(Context context) {
        return "";
    }

    @Override
    public String getUserId(Context context) {

        return Tools.getStringPreference(context, "epgServerUrl");
    }

    @Override
    public String getCookies(Context context) {
        String cookie = Tools.getStringPreference(context, "cookieString");
        return cookie;
    }

    /**
     * iptv：ro.product.type=HWIPTV
     * p30:ro.product.name =Hi3796MV100
     * P60:ro.product.model=SC808
     */
    @Override
    public void initDeviceInfo(Context context) {
        this.context = context;
        platfromUtils = new PlatfromUtils(context);
        if (platfromUtils.isP30()) {
            // 绑定服务
            Intent intent = new Intent();
            intent.setAction(SERVICE_ACTION);
            intent.setPackage("com.ipanel.join.cq.vodauth");
            context.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        } else if (platfromUtils.isP60()) {
            try {
                Class<?> clazz = Class.forName("android.os.SystemProperties");
                Method get = clazz.getMethod("get", String.class);
                String epg_address = (String) get.invoke(null, "epg_address");
                String sessionid = (String) get.invoke(null, "sessionid");
                String keyNo = (String) get.invoke(null, "ICID");
                String ServiceGroupID = (String) get.invoke(null, "ServiceGroupID");
                String ip = (String) get.invoke(null, "IP");
                String NTID = (String) get.invoke(null, "NTID");
                String model = (String) get.invoke(null, "ro.product.model");
                String name = (String) get.invoke(null, "ro.product.name");//
                String type = (String) get.invoke(null, "ro.product.type");//

                Tools.setStringPreference(context, "card", keyNo);
                Tools.setStringPreference(context, "cookieString", sessionid);
                Tools.setStringPreference(context, "epgServerUrl", epg_address);
                Tools.setStringPreference(context, "ip", ip);
                Tools.setStringPreference(context, "model", model);
                Tools.setStringPreference(context, "serviceGroupId", ServiceGroupID + "");
                Log.d("TAG", "initDeviceInfoP60:stb_id"
                        + "epg_address:" + epg_address + "\n"
                        + "sessionid:" + sessionid + "\n"
                        + "keyNo:" + keyNo + "\n"
                        + "ServiceGroupID:" + ServiceGroupID + "\n"
                        + "IP:" + ip + "\n"
                        + "model:" + model + "\n"
                        + "name:" + name + "\n"
                        + "type:" + type + "\n"
                        + "NTID:" + NTID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Class<?> clazz = Class.forName("android.os.SystemProperties");
                Method get = clazz.getMethod("get", String.class);
                String epg_address = (String) get.invoke(null, "epg_address");
                String sessionid = (String) get.invoke(null, "sessionid");
                String keyNo = (String) get.invoke(null, "ro.di.stb_id");
                String ServiceGroupID = (String) get.invoke(null, "ServiceGroupID");
                String ip = (String) get.invoke(null, "IP");
                String NTID = (String) get.invoke(null, "NTID");
                String model = (String) get.invoke(null, "ro.product.model");
                String name = (String) get.invoke(null, "ro.product.name");
                String type = (String) get.invoke(null, "ro.product.type");

                Tools.setStringPreference(context, "card", keyNo);
                Tools.setStringPreference(context, "cookieString", sessionid);
                Tools.setStringPreference(context, "epgServerUrl", epg_address);
                Tools.setStringPreference(context, "ip", ip);
                Tools.setStringPreference(context, "model", model);
                Tools.setStringPreference(context, "serviceGroupId", ServiceGroupID + "");
                Log.d("TAG", "initDeviceInfo_iptv:stb_id"
                        + "epg_address:" + epg_address + "\n"
                        + "sessionid:" + sessionid + "\n"
                        + "keyNo:" + keyNo + "\n"
                        + "ServiceGroupID:" + ServiceGroupID + "\n"
                        + "IP:" + ip + "\n"
                        + "model:" + model + "\n"
                        + "name:" + name + "\n"
                        + "type:" + type + "\n"
                        + "NTID:" + NTID);
            } catch (Exception e) {
                e.printStackTrace();
            }

            context.registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String cookies = intent.getExtras().getString("CookieString");
                    String vspip = intent.getExtras().getString("VSP");
                    Log.d("TAG", "initDeviceInfo_iptv: onReceive: " + cookies + "\n"
                            + vspip + "\n");
                }
            }, new IntentFilter("com.ipanel.join.cq.huawei.vsp3"));

            context.registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String authToken = intent.getStringExtra("authToken");
                    String unitUserId = intent.getStringExtra("unitUserId");
                    Tools.setStringPreference(context, "token", authToken);
                    Log.d("TAG", "initDeviceInfo_iptv onReceive: " + authToken + "\n"
                            + unitUserId + "\n");
                }
            }, new IntentFilter("com.ipanel.join.cq.vodauth.AAA"));
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                initAppConfig();
            }
        }).start();

    }

    @Override
    public void fetchUrlByVODAssetId(Context context, String vodId, FetchUrlByVODAssetIdCallBack callback) {

    }

//    @Override
//    public void fetchUrlByVODAssetId(Context context, String flag, String vodId, FetchUrlByVODAssetIdCallBack callback) {
//        if (platfromUtils.isP30()) {
//            p30(context, flag, vodId, callback);
//        } else if (platfromUtils.isP60()) {
//            p60(context, flag, vodId, callback);
//        } else if (platfromUtils.isIptv()) {
//            iptv(context, flag, vodId, callback);
//        }
//    }

    private IAuthService mNav;
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mNav = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mNav = IAuthService.Stub.asInterface(service);
            try {
                if (mNav != null) {
                    // CA卡号
                    String card = mNav.getCACardNumber();
                    int authStatus = mNav.getAuthStatus();
                    // cookie
                    String cookieString = mNav.getCookieString();
                    // epgServerUrl
                    String epgServerUrl = mNav.getEPGServerUrl();
                    DiffConfig.epg_url = epgServerUrl;
                    // ip
                    String ip = mNav.getIP();
                    // mac
                    String mac = mNav.getMAC();
                    // serviceGroupId
                    long serviceGroupId = mNav.getServiceGroupId();
                    Tools.setStringPreference(context, "card", card);
                    Tools.setIntegerPreference(context, "authStatus", authStatus);
                    Tools.setStringPreference(context, "cookieString", cookieString);
                    Tools.setStringPreference(context, "epgServerUrl", epgServerUrl);
                    Tools.setStringPreference(context, "ip", ip);
                    Tools.setStringPreference(context, "mac", mac);
                    Tools.setStringPreference(context, "serviceGroupId", serviceGroupId + "");
                    Log.d("TAGonServiceConnected", "initDeviceInfo_p30: " + "getCACardNumber:" + card + "\n"
                            + "AuthStatus:" + authStatus + "\n"
                            + "getCookieString:" + cookieString + "\n"
                            + "getEPGServerUrl:" + epgServerUrl + "\n"
                            + "ip:" + ip + "\n"
                            + "mac:" + mac + "\n"
                            + "getServiceGroupId:" + serviceGroupId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    public void exit(Context context) {
        if (platfromUtils.isP30()) {
            context.unbindService(mConnection);
        }
    }
    private void initAppConfig(){

    }
    public void initToken(){

    }

    private void p30(Context context, String flag, String vodId, FetchUrlByVODAssetIdCallBack callback) {
        String isSingle = "1";
        if (!flag.equals("0")) {
            isSingle = "11";
        }
        String url = DiffConfig.CurrentTVBox.getUserId(context) + "/defaultHD/en/go_authorization.jsp?typeId=-1&playType=" + isSingle + "&progId=" + vodId + "&contentType=0&business=1&baseFlag=0&idType=FSN";
        Log.d("TAG", "p30: " + url);
        OkGo.<BeanGetPlayUrl>get(url)
                .cacheMode(CacheMode.NO_CACHE)
                .tag(context)
                .headers("Cookie", getCookies(context))
                .headers("Content-Type", "text/html")
                .headers("Accept-Charset", "utf-8")
                .headers("contentType", "utf-8")
                .execute(new JsonCallback<BeanGetPlayUrl>() {
                    @Override
                    public void onSuccess(Response<BeanGetPlayUrl> response) {
                        try {

                            BeanGetPlayUrl beanGetPlayUrl = response.body();
                            if (beanGetPlayUrl.getRetCode() == 0) {
                                String url = beanGetPlayUrl.getPlayUrl().split("\\^")[4];
                                Log.d("fetchUrlByVODAssetId", "onSuccess: " + beanGetPlayUrl.getPlayUrl() + "\n"
                                        + beanGetPlayUrl.getPlayFlag() + "\n"
                                        + beanGetPlayUrl.getReportUrl() + "\n"
                                        + beanGetPlayUrl.getAnCiFlag() + "\n"
                                        + url);
                                callback.onReceivedUrl(vodId, url);
                            } else {
                                Toast.makeText(context, beanGetPlayUrl.getMessage(), Toast.LENGTH_SHORT).show();

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<BeanGetPlayUrl> response) {
                        Log.d("TAG", "onError: " + response.body().toString());
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });

    }

    private void p60(Context context, String flag, String vodId, FetchUrlByVODAssetIdCallBack callback) {
        String isSingle = "1";
        if (!flag.equals("0")) {
            isSingle = "11";
        }
        String url = DiffConfig.CurrentTVBox.getUserId(context) + "/EPG/jsp/defaultHD/en/go_auth.jsp?playType=" + isSingle + "&progId=" + vodId + "&contentType=0&business=1&baseFlag=0&idType=FSN";
        Log.d("TAG", "p60: " + url);
        try {

            OkGo.<BeanGetPlayUrl>get(url).cacheMode(CacheMode.NO_CACHE)
                    .tag(context)
                    .headers("Cookie", "JSESSIONID=" + getCookies(context))
                    .headers("Content-Type", "text/html")
                    .headers("Accept-Charset", "utf-8")
                    .headers("contentType", "utf-8")
                    .execute(new JsonCallback<BeanGetPlayUrl>() {
                        @Override
                        public void onSuccess(Response<BeanGetPlayUrl> response) {
                            try {
                                BeanGetPlayUrl beanGetPlayUrl = response.body();
                                if (beanGetPlayUrl.getRetCode() == 0) {
                                    Log.d("TAG", "onSuccess: " + beanGetPlayUrl.toString());
                                    String url = beanGetPlayUrl.getPlayUrl().split("\\^")[4];
                                    Log.d("fetchUrlByVODAssetId", "onSuccess: " + beanGetPlayUrl.getPlayUrl() + "\n"
                                            + beanGetPlayUrl.getPlayFlag() + "\n"
                                            + beanGetPlayUrl.getReportUrl() + "\n"
                                            + beanGetPlayUrl.getAnCiFlag() + "\n"
                                            + url);
                                    callback.onReceivedUrl(vodId, url);
                                } else {
                                    Toast.makeText(context, beanGetPlayUrl.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Response<BeanGetPlayUrl> response) {
                            super.onError(response);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void iptv(Context context, String flag, String vodId, FetchUrlByVODAssetIdCallBack callback) {
        String mediaUrl = DiffConfig.epg_url + "/VSP/V3/QueryVOD";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("VODID", vodId);
            jsonObject.put("IDType", 1);

            OkGo.<Object>post(mediaUrl)
                    .cacheMode(CacheMode.NO_CACHE)
                    .tag(context)
                    .upJson(jsonObject)
                    .execute(new JsonCallback<Object>() {
                        @Override
                        public void onSuccess(Response<Object> response) {
                            Log.d("TAGQueryVOD", "onSuccess: " + response.body().toString());
                            String url = DiffConfig.epg_url + "/VSP/V3/PlayVOD";
                            try {
                                JSONObject jsonObject1 = new JSONObject();
                                jsonObject1.put("VODID", vodId);
                                jsonObject1.put("mediaID", "mediaID");
                                jsonObject1.put("IDType", "1");
                                OkGo.<Object>post(url)
                                        .cacheMode(CacheMode.NO_CACHE)
                                        .tag(context)
                                        .upJson(jsonObject1)
                                        .execute(new JsonCallback<Object>() {
                                            @Override
                                            public void onSuccess(Response<Object> response) {
                                                Log.d("TAGPlayVOD", "onSuccess: " + response.body().toString());
                                            }

                                            @Override
                                            public void onError(Response<Object> response) {
                                                super.onError(response);
                                            }
                                        });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
