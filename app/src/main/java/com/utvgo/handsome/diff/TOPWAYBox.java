package com.utvgo.handsome.diff;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.utvgo.huya.utils.Md5Utils;
import com.utvgo.huya.utils.Tools;

/**
 * @author wzb
 * @description:
 * @date : 2020/9/22 10:19
 */
public class TOPWAYBox implements ITVBox{

    public static String getToken(Context context) {
        String webtoken = "";
        Uri uri = Uri.parse("content://com.starcor.authorities/token");
        Cursor cursor = context.getContentResolver().query(uri,null,null,null,"");
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                int state = cursor.getInt(cursor.getColumnIndex("state"));
                String reason = cursor.getString(cursor.getColumnIndex("reason"));
                webtoken = cursor.getString(cursor.getColumnIndex("webtoken"));
                String user_id = cursor.getString(cursor.getColumnIndex("user_id"));
                String device_id = cursor.getString(cursor.getColumnIndex("device_id"));
                String epg_host = cursor.getString(cursor.getColumnIndex("epg_host"));
                String tag = cursor.getString(cursor.getColumnIndex("tag"));
                String version = cursor.getString(cursor.getColumnIndex("version"));
                if (state == 0) {
                    Tools.setStringPreference(context, "reason", reason);
                    Tools.setStringPreference(context, "webtoken", webtoken);
                    Tools.setStringPreference(context, "user_id", user_id);
                    Tools.setStringPreference(context, "device_id", device_id);
                    Tools.setStringPreference(context, "epg_host", epg_host);
                    Tools.setStringPreference(context, "tag", tag);
                    Tools.setStringPreference(context, "version", version);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return webtoken;
    }

    @Override
    public String getCA(Context context) {
        String keyNo = Tools.getStringPreference(context,"user_id");
        if(!TextUtils.isEmpty(keyNo)){
            return keyNo;
        }
        return "123456789";
    }

    @Override
    public String getRegionCode(Context context) {
        String regionCode = Tools.getStringPreference(context,"areaCode");
        if(!TextUtils.isEmpty(regionCode)){
            return regionCode;
        }
        return "topway";
    }
    /**
     * state：代表设备的合法性，值不等于0，则不能进入APK
     * user_id： CP应用需要根据用户ID值是否为空，判断是否需要登陆
     * webtoken 在播放鉴权接口需要传入，验证合法性。因此每次播放鉴权时，需要从主APK获取新的TOKEN值。
     * */
    @Override
    public void initDeviceInfo(Context context) {
        getToken(context);
        final Uri uri = Uri.parse("content://" + "com.starcor.launcher.launcherInfo/deviceInfo");
        Cursor cursor = context.getContentResolver().query(uri, null, "from=? and cpId=?", new
                String[]{context.getPackageName(), "cp_id"}, null);
        if (cursor != null) {
            cursor.moveToNext();
            int status = cursor.getInt(cursor.getColumnIndexOrThrow("status"));
            String reason = cursor.getString(cursor.getColumnIndexOrThrow("reason"));
            String deviceId = cursor.getString(cursor.getColumnIndexOrThrow("device_id"));
            String deviceSerialNumber = cursor.getString(cursor.getColumnIndexOrThrow("device_serial_number"));
            String mac = cursor.getString(cursor.getColumnIndexOrThrow("mac"));
            String networkStatus = cursor.getString(cursor.getColumnIndexOrThrow("network_status"));
            String apkVersion = cursor.getString(cursor.getColumnIndexOrThrow("apk_version"));
            String systemVersion = cursor.getString(cursor.getColumnIndexOrThrow("system_version"));
            String model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
            String licence = cursor.getString(cursor.getColumnIndexOrThrow("licence"));
            String platformType = cursor.getString(cursor.getColumnIndexOrThrow("platform_type"));
            String areaCode= cursor.getString(cursor.getColumnIndexOrThrow("area_code"));
            Log.d("initDeviceInfo", status + ";" + reason + ";" + deviceId + ";" + deviceSerialNumber
                    + ";" + mac + ";" + networkStatus + ";" + apkVersion + ";" + systemVersion + ";" + model +
                    ";" + licence + ";" + platformType + ";" + areaCode);
            if(status == 0){
                Tools.setStringPreference(context,"deviceSerialNumber",deviceSerialNumber);
                Tools.setStringPreference(context,"deviceId",deviceId);
                Tools.setStringPreference(context,"mac",mac);
                Tools.setStringPreference(context,"networkStatus",networkStatus);
                Tools.setStringPreference(context,"apkVersion",apkVersion);
                Tools.setStringPreference(context,"systemVersion",systemVersion);
                Tools.setStringPreference(context,"model",model);
                Tools.setStringPreference(context,"licence",licence);
                Tools.setStringPreference(context,"platformType",platformType);
                Tools.setStringPreference(context,"areaCode",areaCode);
            }
        }
    }

    @Override
    public void fetchUrlByVODAssetId(Context context, String vodId, FetchUrlByVODAssetIdCallBack callback) {
        try {
            String cpIdString = "";
            if (TextUtils.isEmpty(cpIdString)){
                cpIdString = "mgtv";//第三方集成必须使用自己的cpId
            }
            String action= "mediaAuth";
            String encodedContent = "{" +
                    " \"action\": \"MEDIA_AUTH\",\n" +
                    " \"source\": \"STARCOR\",\n" +
                    " \"video_id\": \"96c38657d523127fb8371a45a4ab852d\",\n" +
                    " \"video_name\": \"test\",\n" +
                    " \"video_type\": \"0\",\n" +
                    " \"is_batch_auth_index\": \"1\",\n" +
                    " “cp_package_name”: ”com.xxxxxx”\n" +
                    "}\n";
            final long timestamp = System.currentTimeMillis();
            final String sign = Md5Utils.calMD5(encodedContent.length() + action + timestamp + cpIdString);
            final ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
            final Cursor cursor = contentResolver.query(Uri.parse("content://com.starcor.authorities/api/"),
                    null, "action=? and content=? and timestamp=? and cp_id=? and sign=?",
                    new String[]{action, encodedContent, timestamp+"", cpIdString, sign}, null);
            if (cursor != null && cursor.moveToNext()) {
                int code = cursor.getInt(cursor.getColumnIndexOrThrow("code"));
                final String message = cursor.getString(cursor.getColumnIndexOrThrow("message"));
                final String data = cursor.getString(cursor.getColumnIndexOrThrow("data"));
                final String result = "code=" + code + ";" + "\n" + "message=" + message + ";" + "\n" + "data=" + "\n" + JSON.toJSONString(data);
                Log.i("", "code=" + code + "; message=" + message + "; data=" + data);

                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
