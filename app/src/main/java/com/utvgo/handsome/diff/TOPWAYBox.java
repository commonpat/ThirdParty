package com.utvgo.handsome.diff;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.bean.BeanVideoVist;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.NetworkUtils;
import com.utvgo.handsome.utils.TopWayBroacastUtils;
import com.utvgo.handsome.bean.BeanContent;
import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.listeners.MyDialogEnterListener;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.Md5Utils;
import com.utvgo.huya.utils.Tools;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wzb
 * @description:
 * @date : 2020/9/22 10:19
 */
public class TOPWAYBox implements ITVBox{
    public static final String cpIdString = "sp_hy";
    public static String getToken(Context context) {
        String webtoken = "";
        Uri uri = Uri.parse("content://" + "com.starcor.authorities/token");
        //Uri uri = Uri.parse("content://" + "com.starcor.launcher.launcherInfo/deviceInfo");
        try {
            Cursor cursor = context.getContentResolver().query(uri,null,null,null,null);
            if (cursor != null) {
                cursor.moveToFirst();
                int state = cursor.getInt(cursor.getColumnIndex("state"));
                String reason = cursor.getString(cursor.getColumnIndex("reason"));
                webtoken = cursor.getString(cursor.getColumnIndex("webtoken"));
                String user_id = cursor.getString(cursor.getColumnIndex("user_id"));
                String version = cursor.getString(cursor.getColumnIndex("version"));
                if (state == 0) {
                    Tools.setStringPreference(context, "reason", reason);
                    Tools.setStringPreference(context, "webtoken", webtoken);
                    Tools.setStringPreference(context, "user_id", user_id);
                    Tools.setStringPreference(context, "version", version);
                }
                final String result = "code=" + state + ";" + "\n" + "message=" + reason + ";" + "\n" + "data=" + "\n" ;
                Log.d("", "getToken: "+result);


                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return webtoken;
    }

    @Override
    public String getCA(Context context) {
        String keyNo = Tools.getStringPreference(context,"device_id");
        if(!TextUtils.isEmpty(keyNo)){
            return keyNo;
        }
        return "8002004093892878";
    }

    @Override
    public String getRegionCode(Context context) {
        String regionCode = Tools.getStringPreference(context,"areaCode");
        if(!TextUtils.isEmpty(regionCode)){
            return regionCode;
        }
        return "1320";
    }
    /**
     * state：代表设备的合法性，值不等于0，则不能进入APK
     * user_id： CP应用需要根据用户ID值是否为空，判断是否需要登陆
     * webtoken 在播放鉴权接口需要传入，验证合法性。因此每次播放鉴权时，需要从主APK获取新的TOKEN值。
     * */
    @Override
    public void initDeviceInfo(final Context context) {
        final Context c = context;
        String webtoken = getToken(context);
        final String mac = getLocalWifiMacAddress();
        Tools.setStringPreference(context, "mac", mac);
        queryDataCoreContent(context, "deviceAuth", "",new ContentCallback() {
            @Override
            public void onSuccess(String s) {
                JSONObject jsonObject = JSON.parseObject(s);
                String device_id = jsonObject.getString("device_id");
                String version = jsonObject.getString("version");
                String webtoken = jsonObject.getString("webtoken");
                String user_id = jsonObject.getString("user_id");

                Tools.setStringPreference(context, "device_id", device_id);
                Tools.setStringPreference(context, "webtoken", webtoken);
                Tools.setStringPreference(context, "user_id", user_id);
                Tools.setStringPreference(context, "version", version);
                Log.d("qqmusicwzb", "onSuccess:device_id: " + device_id);
                Log.d("qqmusicwzb", "onSuccess:webtoken: " + webtoken);
                Log.d("qqmusicwzb", "onSuccess:user_id: " + user_id);
                Log.d("qqmusicwzb", "onSuccess: version:" + version);
                Log.d("qqmusicwzb", "onSuccess: mac:" + mac);

            }

            @Override
            public void onFail(String s) {
                HiFiDialogTools.getInstance().showtips(c, "设备认证失败，请退出！", new MyDialogEnterListener() {
                    @Override
                    public void onClickEnter(Dialog dialog, Object object) {

                    }
                });
            }
        });
    }

    @Override
    public void fetchUrlByVODAssetId(Context context, final String vodId, final FetchUrlByVODAssetIdCallBack callback) {
/**
 * {
 *  "action": "MEDIA_AUTH_AND_URL",
 *  "cp_video_id": "D00000001201609291600530087399981",
 *  "video_type": "0",
 *  "source":"OTHER",
 *  "video_name":"",
 *  "cp_media_id":"d0cbdf0bf13c1c502950f80b8ef37db1"
 * }
 *{
 *  "action": "MEDIA_AUTH_AND_URL",
 *  "video_id": "d2b2890eebeeb05ea425a27f4903449d",    //媒资原始ID
 *  "video_type": "0",
 *  "source":"STARCOR",
 *  "video_name":"广西卫视",
 *  "media_id":""
 * }
// * */
       if(BuildConfig.DEBUG){
           callback.onReceivedUrl("","https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4");
              return;
          // vodId = "sphys_705984307";
       }
        final String action= "mediaAuth";
        BeanContent content = new BeanContent("MEDIA_AUTH_AND_URL","OTHER",vodId,"0",context.getPackageName(),"0");
        String contentJsonString = JSON.toJSONString(content);


        queryDataCoreContent(context, action, contentJsonString, new ContentCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject =  JSON.parseObject(data);
                String url = jsonObject.getString("url");
//                String[] surl = url.split("\\?");
//                if(vodId.endsWith("m4a")){
//                    url = url.replace("mp4","m4a");
//                }
//url ="http://116.77.76.13:5000/nn_vod/nn_x64/aWQ9NWE4YmYyNTY4MmMxMTJmM2JjYzdkY2U5MGJlNWE2NzgmdXJsX2MxPTc0NzMyZjMyMzMzMDMwMzAzMDMxMzIzMDMyMzAzMDM0MzAzOTMxMzkzNDMyMzMzMzM1MzQzMDMyMzIyZjMyMzMzMDMwMzAzMDMyMzIzMDMyMzAzMDM0MzAzOTMxMzkzNDMyMzMzMzM1MzQz/MDMyMzMyZTc0NzMwMCZubl9haz0wMTljMjljMzU4NzYzMjY1YzgwODBmMzcwZWZmMTE0ZjkwJm5waXBzPTE3Mi4yNy4xNy42Njo1MTAwJm5jbXNpZD0xMDAwMDEwNiZuZ3M9NWY5MTNiZDYwMDBhODY3ZTg4NGI0MDQwODZhNmRlNmEmbm5kPXR3bmV0Lmd1YW5nZG9u/Zy5zaGVuJm5zZD16Z2R4LnNoZW56aGVuJm5mdD10cyZubl91c2VyX2lkPTgwNzU1ODgwMTE5NjcxMjkmbmR0PXN0YiZuZHY9Mi4wLjM2LlNUQi5GVFRILlNURC5EVkJfU0tXMDEuUmVsZWFzZSZuc3Q9Jm5jYT0lMjZuYWklM2Rtb3ZpZTIzMDAwMDIyMDIwMDQwOTE5/NDIzMzU0MDIzJTI2bm5fY3AlM2R5b3VrdSZuYWw9MDFkNjNiOTE1ZjA2MDcwMTllMGM0YzVhMTUwMTcxYjY2MGMzMDNhZmNkMGZhNw,,/5a8bf25682c112f3bcc7dce90be5a678.m3u8";
              //  url = "http://116.77.76.10:5000/nn_vod/nn_x64/aWQ9YzZjMTEyZjM4YmZiMTNkNTdlMmMwYTBiZjQ1MzAxMmUmdXJsX2MxPTc0NzMyZjMyMzMzMDMwMzAzMDMxMzIzMDMyMzAzMDM2MzAzMjMxMzAzMjMwMzUzODM0MzEzMDM2MzkyZjMyMzMzMDMwMzAzMDMyMzIzMDMyMzAzMDM2MzAzMjMxMzAzMjMwMzUzODM0MzEz/MDM3MzAyZTc0NzMwMCZubl9haz0wMWMyNzU2ODdlYTRmODE5YTU4NjdmYzU1NDhlYzQ5M2ZhJm5waXBzPTE3Mi4yNy4xNy42Njo1MTAwJm5jbXNpZD0xMDAwMDEwMyZuZ3M9NWY4ZmQ1NGQwMDAwMzFhODBjZTE5ZGU3NDNjZGE4MTQmbm5kPXR3bmV0Lmd1YW5nZG9u/Zy5zaGVuJm5zZD16Z2R4LnNoZW56aGVuJm5mdD10cyZubl91c2VyX2lkPTVhYmIwNzhkMjdkYzAzODY2NjFhODYyZjA3ZjczYmIwJm5kdD1zdGImbmR2PTEuNS41LlNUQi5GVFRILlNURC5PVFRfU0tXMDEuUmVsZWFzZSZuc3Q9Jm5jYT0lMjZuYWklM2Rtb3ZpZTIz/MDAwMDIyMDIwMDYwMjEwMjA1ODQxMDcwJTI2bm5fY3AlM2R5b3VrdSZuYWw9MDE0ZGQ1OGY1ZjA2MDdlZTRjZTQ1ZjVjOWRmZjdjNzA3OGVlNDY0N2Y1MDJmYw,,/c6c112f38bfb13d57e2c0a0bf453012e.m3u8";
                callback.onReceivedUrl(vodId,url);
            }

            @Override
            public void onFail(String s) {
                Log.d("wzb", action+"onFail: onFail"+s);
            }
        });
    }

    @Nullable
    @Override
    public String getLoginToken(@Nullable Context context) {
        return null;
    }

    @Nullable
    @Override
    public String getDeviceModel(@Nullable Context context) {
        return null;
    }

    @Nullable
    @Override
    public String getCookies(@Nullable Context context) {
        return null;
    }

    @Nullable
    @Override
    public String getUserId(@Nullable Context context) {
        return null;
    }

    @Override
    public void exit(@Nullable Context context) {

    }

    public interface ContentCallback{
        void onSuccess(final String s);
        void onFail(final String s);

    }
    public static void queryDataCoreContent(Context context, final String action, final String content, ContentCallback callback){
//        if (TextUtils.isEmpty(content)){
//            ToastUtil.show(context,"请求数据为空，请重试！");
//            return;
//        }
        try {
            //final String encodedContent = URLEncoder.encode(content, "utf-8");
            final long timestamp = System.currentTimeMillis();
            final String sign = Md5Utils.calMD5(content.length() + action + timestamp + cpIdString);

             ContentResolver contentResolver = context.getContentResolver();
            final Cursor cursor = contentResolver.query(Uri.parse("content://com.starcor.authorities/api/"),
                    null, "action=? and content=? and timestamp=? and cp_id=? and sign=?",
                    new String[]{action, content, timestamp+"", cpIdString, sign}, null);
            if (cursor != null && cursor.moveToNext()) {
                int code = cursor.getInt(cursor.getColumnIndexOrThrow("code"));
                final String message = cursor.getString(cursor.getColumnIndexOrThrow("message"));
                final String data = cursor.getString(cursor.getColumnIndexOrThrow("data"));
                final String result = "code=" + code + ";" + "\n" + "message=" + message + ";" + "\n" + "data=" +data+ "\n" ;
                Log.d("", "getToken: "+result);
                if(code==0) {
                    callback.onSuccess(data);
                }else {
                    callback.onFail(data);
                }
                cursor.close();
            }
        } catch (Exception e) {
            TopWayBroacastUtils.getInstance().appErr(context,e.getMessage());
            e.printStackTrace();
        }
    }
    public  void initVideoVist(final Context context){
        final Handler vhandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
               try {
                   String video_id = msg.getData().getString("video_id");
                   String video_name = msg.getData().getString("video_name");
                   int voddur = msg.getData().getInt("voddur");
                   int playTime = msg.getData().getInt("playTime");
                   String tsid = msg.getData().getString("tsid");
                   String freq = msg.getData().getString("freq");
                   String app = msg.getData().getString("app");
                   String servicetype = msg.getData().getString("servicetype");
                   String provider_id = msg.getData().getString("provider_id");
                   String category_id = msg.getData().getString("category_id");
                   String category_name = msg.getData().getString("category_name");
                   String asset_id = msg.getData().getString("asset_id");
                   String asset_name = msg.getData().getString("asset_name");
                   String episodes = msg.getData().getString("episodes");
                   String vodepisodes = msg.getData().getString("vodepisodes");
                   String program_time = msg.getData().getString("program_time");
                   String curtime = msg.getData().getString("curtime");
                   String app_id = msg.getData().getString("app_id");
                   String play_time = msg.getData().getString("play_time");
                   String page_path = msg.getData().getString("page_path");
                   String source_path = msg.getData().getString("source_path");
                   String page_name = msg.getData().getString("page_name");
                   String ver = msg.getData().getString("ver");
                   String type = msg.getData().getString("type");

                   Log.d("videovist", "handleMessage: " + video_id + "____" + playTime);
                   TopWayBroacastUtils.getInstance().pageOpen(context, app_id, category_id, category_id, "0", asset_name);
                   TopWayBroacastUtils.getInstance().vodOpen(context, ver, app, curtime);
                   TopWayBroacastUtils.getInstance().playhuyaEvent(context, tsid, freq, app, servicetype, provider_id, category_id, category_name, asset_id, asset_name, episodes, vodepisodes, program_time);
                   TopWayBroacastUtils.getInstance().vodPlay(context, 1, "", app, play_time, (Integer.valueOf(program_time) / 1000) + "", type);
                   TopWayBroacastUtils.getInstance().vodPlay(context, 2, "", app, play_time, (Integer.valueOf(program_time) / 1000) + "", type);
                   TopWayBroacastUtils.getInstance().playhuyaStopEvent(context, tsid, freq, app, servicetype, provider_id, category_id, category_name, asset_id, asset_name, episodes, vodepisodes, program_time);
               }catch (Exception e){
                   e.printStackTrace();
               }
                return false;
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = DiffConfig.host + "/uuMusic/uuMusicDataController/getRandMVContent.utvgo";
                NetworkUtils.get(context, url, new JsonCallback<BeanVideoVist>() {
                    @Override
                    public void onSuccess(Response<BeanVideoVist> response) {

                        try {
                            final  BeanVideoVist beanVideoVist = response.body();

                            Log.d("videovist", "onSucceeded: "+JSON.toJSONString(beanVideoVist));
                            if ("1".equals(beanVideoVist.getCode()) && beanVideoVist.getData() != null) {
                                for (int i = 0; i < beanVideoVist.getData().size(); i++) {
                                    int freeTime = beanVideoVist.getData().get(i).getFreeTime();
                                    int voddur = beanVideoVist.getData().get(i).getDuration();

                                    Message message = new Message();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("video_id", beanVideoVist.getData().get(i).getVodId());
                                    bundle.putString("video_name", beanVideoVist.getData().get(i).getName());
                                    bundle.putInt("voddur", beanVideoVist.getData().get(i).getDuration());
                                    bundle.putString("playTime", beanVideoVist.getData().get(i).getPlay_time());
                                    bundle.putString("tsid", beanVideoVist.getData().get(i).getTsid());
                                    bundle.putString("freq", beanVideoVist.getData().get(i).getFreq());
                                    bundle.putString("app", beanVideoVist.getData().get(i).getApp());
                                    bundle.putString("servicetype", beanVideoVist.getData().get(i).getServicetype());
                                    bundle.putString("provider_id", beanVideoVist.getData().get(i).getProvider_id());
                                    bundle.putString("category_id", beanVideoVist.getData().get(i).getCategory_id());
                                    bundle.putString("asset_id", beanVideoVist.getData().get(i).getAsset_id());
                                    bundle.putString("asset_name", beanVideoVist.getData().get(i).getAsset_name());
                                    bundle.putString("episodes", beanVideoVist.getData().get(i).getEpisodes());
                                    bundle.putString("vodepisodes", beanVideoVist.getData().get(i).getVodepisodes());
                                    bundle.putString("program_time", beanVideoVist.getData().get(i).getProgram_time());
                                    bundle.putString("play_time", beanVideoVist.getData().get(i).getPlay_time());
                                    bundle.putString("curtime", beanVideoVist.getData().get(i).getCurtime());
                                    bundle.putString("page_path", beanVideoVist.getData().get(i).getPage_path());
                                    bundle.putString("source_path", beanVideoVist.getData().get(i).getSource_path());
                                    bundle.putString("page_name", beanVideoVist.getData().get(i).getPage_name());
                                    bundle.putString("ver", beanVideoVist.getData().get(i).getVer());
                                    bundle.putString("type", beanVideoVist.getData().get(i).getType());




                                    message.what = 1000;
                                    message.setData(bundle);
                                    vhandler.sendMessageDelayed(message, 5000);
                                }

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
            }
        });
        thread.start();


    }


    public static String getLocalWifiMacAddress() {
        //String local = "/sys/class/net/usbnet0/address"; //点播网卡MAC地址
        String local = "/sys/class/net/eth0/address";  //eth0网卡MAC地址
        File file = new File(local);
        String szMacString = "";
        BufferedReader reader = null;
        String szLine = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((szLine = reader.readLine()) != null) {
                szMacString = szMacString.concat(szLine);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return szMacString.replace(":", "-");
    }

}
