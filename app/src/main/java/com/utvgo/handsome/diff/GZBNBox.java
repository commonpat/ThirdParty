package com.utvgo.handsome.diff;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;


import com.gzgd.fml.aidl.IBoxBasicService;
import com.utvgo.handsome.bean.BeanGZScaaaDeviceAuth;
import com.utvgo.handsome.bean.BeanGuizhouAuthAndPlayUrlWithMediaBean;
import com.utvgo.handsome.utils.GuizhouUtils;
import com.utvgo.handsome.utils.PlatfromUtils;
import com.utvgo.huya.utils.Base64Encoder;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.NetUtils;
import com.utvgo.huya.utils.Tools;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class GZBNBox implements ITVBox {

    private static boolean isBindBoxService=false;
    public PlatfromUtils platfromUtils = new PlatfromUtils();
    private static Context areaContext;
    public static final String TAG = "wzb";


    public static void getFML1DeviceInfo(Context context) {
            try {
                Class systemClass = Class.forName("android.os.SystemProperties");
                Method method = systemClass.getMethod("get", String.class, String.class);
                String mmcp_smartcard_id = (String) method.invoke(systemClass, "persist.sys.mmcp.smarcardid", "");
                Tools.setStringPreference(context, GuizhouUtils.TagGuizhouUid, mmcp_smartcard_id);
                Tools.setStringPreference(context, GuizhouUtils.TagGuizhouDid, mmcp_smartcard_id);
                Tools.setStringPreference(context, GuizhouUtils.TagGuizhouVersion, "1.0.0.STD.GZGD.AUTO.OTT01.Release");
               // ParamConfig.setKeyNo(context, mmcp_smartcard_id);
                Tools.setStringPreference(context,"KEYNO",mmcp_smartcard_id);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    
    public static void getFML2DeviceInfo(Context context) {
        Uri authoritiesURI = Uri.parse("content://com.starcor.gzgd.app.authorities/token");
        Cursor cursor = context.getContentResolver().query(authoritiesURI, null, null, null,
                "");
        if (cursor != null) {
            cursor.moveToFirst();
            int state = cursor.getInt(cursor.getColumnIndex("state"));
            String reason = cursor.getString(cursor.getColumnIndex("reason"));
            String webtoken = cursor.getString(cursor.getColumnIndex("webtoken"));
            String user_id = cursor.getString(cursor.getColumnIndex("user_id"));
            String device_id = cursor.getString(cursor.getColumnIndex("device_id"));
            String epg_host = cursor.getString(cursor.getColumnIndex("epg_host"));
            String tag = cursor.getString(cursor.getColumnIndex("tag"));
            String version = cursor.getString(cursor.getColumnIndex("version"));

            Log.i(TAG, "auth: state " + state);
            if (state == 0) {
                Tools.setStringPreference(context, GuizhouUtils.TagGuizhouDid, device_id);
                Tools.setStringPreference(context, GuizhouUtils.TagGuizhouUid, user_id);
               // ParamConfig.setKeyNo(context, user_id);
                Tools.setStringPreference(context,"KEYNO",user_id);
                Log.d(TAG, "getFML2DeviceInfo: "+user_id);
                Tools.setStringPreference(context, GuizhouUtils.TagGuizhouWebtoken, webtoken);
                Tools.setStringPreference(context, GuizhouUtils.TagGuizhouVersion, version);
            }
        }
    }
    
    //父母乐1的开机认证  获取web_token
    public static  void getFML1WebToken(final Context context) {
        String user_id = Tools.getStringPreference(context, GuizhouUtils.TagGuizhouUid);
        String version = Tools.getStringPreference(context, GuizhouUtils.TagGuizhouVersion);

        String path = GuizhouUtils.UrlN215a+"?nns_func=scaaa_device_auth" +
                "&nns_device_id=" + user_id + "&nns_mac_id=" + GuizhouUtils.getLocalWifiMacAddress()
                + "&nns_version=" + version + "&nns_output_type=json";

        try {
            path = URLEncoder.encode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String proxyPathUrl = GuizhouUtils.proxyUrl + path;
        Log.e(TAG, "getFML1WebToken: " + proxyPathUrl);
        NetUtils.getData(context, proxyPathUrl, GuizhouUtils.GetScaaaDeviceAuthReq, null,
                BeanGZScaaaDeviceAuth.class, null, new NetUtils.NetCallBack() {
                    @Override
                    public void netBack(int requestTag, Object object) {
                        if(requestTag==GuizhouUtils.GetScaaaDeviceAuthReq){
                            if(object != null) {
                                BeanGZScaaaDeviceAuth beanGZScaaaDeviceAuth=(BeanGZScaaaDeviceAuth) object;
                                Tools.setStringPreference(context,GuizhouUtils.TagGuizhouWebtoken,beanGZScaaaDeviceAuth.getAuth().getWeb_token());
                            }
                        }
                    }
                });
    }

    public static void bindBoxService(Context context) {
        try {
                Intent intent = new Intent("gzgd.intent.action.service.BoxService");
                final Intent eintent = new Intent(createExplicitFromImplicitIntent(context, intent));
                areaContext=context;
                isBindBoxService = context.bindService(eintent, mConnection, Context.BIND_AUTO_CREATE);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "bindBoxService: 绑定 贵州地区码 BoxService 失败 ");
            }
    }

    private static IBoxBasicService mService = null;

    public static ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IBoxBasicService.Stub.asInterface(service);
            getGuiZhouAreaCode();
            Log.d(TAG, "IBoxBasicService Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            Log.d(TAG, "IBoxBasicService Disconnected");
        }
    };

    public static void getGuiZhouAreaCode(){
        //获取区域码
        try{
            String arecode = mService.getBoxInfo("AREAID");
            Log.e(TAG, "getGuiZhouAreaCode==30130===> "+arecode );
            if (arecode==null){arecode="30130";}
            Tools.setStringPreference(areaContext, GuizhouUtils.TagGuizhouAreaCode, arecode);
        }catch(Exception o){
            o.printStackTrace();
        }
    }

    @Override
    public String getCA(Context context) {
        String keyNo= Tools.getStringPreference(context, "KEYNO");
        Log.d(TAG, "getCA: "+keyNo);
        if(!TextUtils.isEmpty(keyNo)){
            return  keyNo;
        }
        return "";
    }

    @Override
    public String getRegionCode(Context context) {
        String areaCode=Tools.getStringPreference(context, GuizhouUtils.TagGuizhouAreaCode);
        return areaCode;
    }
    
    //N51_A_8 影片鉴权并取串接口
    @Override
    public void fetchUrlByVODAssetId(final Context context, final String vodId, final FetchUrlByVODAssetIdCallBack callback) {
        Map<String, String> params = getReqParam(vodId,context,"0");
        String proxyPathUrl = GuizhouUtils.proxyUrl + GuizhouUtils.createProxyUrl(GuizhouUtils.authUrl, params);
        NetUtils.getData(context, proxyPathUrl, GuizhouUtils.GetGuizhouPlayUrl, null,
                BeanGuizhouAuthAndPlayUrlWithMediaBean.class, null, new NetUtils.NetCallBack() {
                    @Override
                    public void netBack(int requestTag, Object object) {
                        if(requestTag==GuizhouUtils.GetGuizhouPlayUrl){
                            if (object != null) {
                                    BeanGuizhouAuthAndPlayUrlWithMediaBean beanGuizhouAuthAndPlayUrl = (BeanGuizhouAuthAndPlayUrlWithMediaBean) object;
                                    if (beanGuizhouAuthAndPlayUrl != null && beanGuizhouAuthAndPlayUrl.getResult() != null) {
                                        if ("0".equals(beanGuizhouAuthAndPlayUrl.getResult().getState())) { //已授权
                                            try {
                                                if(!TextUtils.isEmpty(beanGuizhouAuthAndPlayUrl.getVideo().getIndex().getMedia().getUrl())){
                                                    Log.d(TAG, "最终播放地址: " + beanGuizhouAuthAndPlayUrl.getVideo().getIndex().getMedia().getUrl());
                                                    callback.onReceivedUrl(vodId,beanGuizhouAuthAndPlayUrl.getVideo().getIndex().getMedia().getUrl());
                                                }
                                            } catch (Exception o) {
                                                o.printStackTrace();
                                                HiFiDialogTools.getInstance().showtips(context, "播放地址错误", null);
                                            }
                                        }
//                    else if ("1".equals(beanGuizhouAuthAndPlayUrl.getResult().getState())){ //未授权
//                        showBuy("");
//                    }
                                        else {
                                            HiFiDialogTools.getInstance().showtips(context,  beanGuizhouAuthAndPlayUrl.getResult().getReason(), null);
                                        }
                                    }
                            }
                        }
                    }
                });
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
        String nns_auth = startAES(GuizhouUtils.authKey, GuizhouUtils.authKey + GuizhouUtils.cpid
                + user_id + vodId + timestamp);
        params.put("nns_auth", nns_auth);
        return params;
    }
    
    public static String startAES(String key, String sSrc) {
        try {
            if (key == null) {
                Log.e("startAES", "AES: sztw3a Authorize API key is null");
                return "";
            }
            if (key.length() != 16) {
                Log.e("startAES", "sztw3a Authorize API key is not 16 byte");
            }
            byte[] raw = key.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            String encryptedStr = new Base64Encoder().encode(encrypted);
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(encryptedStr);
            return m.replaceAll("");
        } catch (Exception ex) {
            return "";
        }

    }
    
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can matchthe given intent
        PackageManager pm =context.getPackageManager();
        List<ResolveInfo> resolveInfo =pm.queryIntentServices(implicitIntent, 0);
        // Make sure only one match was found
        if (resolveInfo == null ||resolveInfo.size() != 1) {
            return null;
        }
        // Get component info and createComponentName
        ResolveInfo serviceInfo =resolveInfo.get(0);
        String packageName =serviceInfo.serviceInfo.packageName;
        String className =serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old onefor extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
//fml1,地区码
    public static String queryAreaCode(Context context) {
        Uri uri = Uri.parse("content://com.gzgd.launcher.USERINFO/areaCode");
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            int index = cursor.getColumnIndex("areaCode");
            String areaCode = cursor.getString(index);
            cursor.close();
            Log.d(TAG, "query areaCode is " + areaCode);
            Tools.setStringPreference(context, GuizhouUtils.TagGuizhouAreaCode, areaCode);
            return areaCode;
        }
        if (cursor != null)
            cursor.close();
        return null;
    }

}
