package com.utvgo.handsome.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.TOPWAYBox;
import com.utvgo.huya.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.utvgo.huya.constant.ConstantEnumHuya.APP_END;
import static com.utvgo.huya.constant.ConstantEnumHuya.APP_START;
import static com.utvgo.huya.constant.ConstantEnumHuya.DATA_TYPE_APP_START_END;
import static com.utvgo.huya.constant.ConstantEnumHuya.DATA_TYPE_COLLECTION;
import static com.utvgo.huya.constant.ConstantEnumHuya.DATA_TYPE_PORTAL;
import static com.utvgo.huya.constant.ConstantEnumHuya.KEY_BACK;
import static com.utvgo.huya.constant.ConstantEnumHuya.KEY_HOME;
import static com.utvgo.huya.constant.ConstantEnumHuya.PRODUCT_BUY_PAGEVIEW;
import static com.utvgo.huya.constant.ConstantEnumHuya.VOD_PAGEVIEW;
import static com.utvgo.huya.constant.ConstantEnumHuya.VOD_SP_PLAY;
import static com.utvgo.huya.constant.ConstantEnumHuya.VOD_SP_SEARCH;


/**
 * @author wzb
 * @description:
 * @date : 2020/9/29 11:19
 */
public class TopWayBroacastUtils {
    private final static String TAG= "topwaytest";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static TopWayBroacastUtils topWayBroacastUtilsw = null;
    public static TopWayBroacastUtils getInstance(){
        if(topWayBroacastUtilsw == null){
            topWayBroacastUtilsw = new TopWayBroacastUtils();
        }
        return topWayBroacastUtilsw;
    }
    public void appStart(Context context){
        Log.d(TAG, "appStart: 应用启动");
        String extData = String.format("app=%s;app_name=%s;ver=%s;curtime=%s", BuildConfig.APPLICATION_ID,"虎牙TV版", BuildConfig.VERSION_NAME,simpleDateFormat.format(new Date()));
        sendBroacast(context,DATA_TYPE_APP_START_END,APP_START,extData);
    }
    public void appEnd(Context context){
        Log.d(TAG, "appEnd: 应用关闭");
        String extData = String.format("app=%s;app_name=%s;ver=%s;curtime=%s",BuildConfig.APPLICATION_ID,"虎牙TV版", BuildConfig.VERSION_NAME,simpleDateFormat.format(new Date()));
        sendBroacast(context,DATA_TYPE_APP_START_END, APP_END,extData);
    }
    public void pressHomeKey(Context context){
        appEnd(context);
        Log.d(TAG, "pressHomeKey: home键");
        String extData = String.format("app=%s;column_id=%d;recommend_id=%d;name=%s;data_name=%s;loading_time=%s;curtime=%s",BuildConfig.APPLICATION_ID,1, 1,"主页","主页","1",simpleDateFormat.format(new Date()));
        sendBroacast(context,DATA_TYPE_PORTAL,KEY_HOME,extData);
    }
    public void pressReturnKey(Context context){
        appEnd(context);
        Log.d(TAG, "pressReturnKey: 返回键");
        String extData = String.format("app=%s;column_id=%d;recommend_id=%d;name=%s;data_name=%s;loading_time=%s;curtime=%s",BuildConfig.APPLICATION_ID,null, null,null,null,null,simpleDateFormat.format(new Date()));
       sendBroacast(context,DATA_TYPE_PORTAL,KEY_BACK,extData);
    }
    public void pageEvent(Context context,String page_type,String specialName,String asset_id,String category_id){
        Log.d(TAG, String.format("pageEvent: 页面%s_%s_%s_%s",page_type,specialName,asset_id,category_id));
        String extData = String.format("app=%s;page_type=%s;asset_id=%s;category_id=%s;video_id=%s;video_name=%s;special_id=%s;special_name=%s;\n",BuildConfig.APPLICATION_ID,page_type,asset_id, category_id,null,null,null,specialName);
        sendBroacast(context,DATA_TYPE_COLLECTION,VOD_PAGEVIEW,extData);
    }
    public void playEvent(Context context,String video_id,String video_action,String video_name,String voddur,String progress,String asset_id,String category_id){
        Log.d(TAG, String.format("playEvent: 播放事件%s_%s_%s_%s_%s",video_id,video_action,video_name,asset_id,category_id));
        String extData = String.format("app=%s;page_id=%s;asset_id=%s;category_id=%s;video_id=%s;video_type=%s;\n" +
                "episode_id=%s;media_id=%s;playbill_start_time=%s;playbill_length=%s;video_action=%s;\n" +
                "video_name=%s",BuildConfig.APPLICATION_ID,"p_player",asset_id,category_id,video_id,"0",null,null,progress,voddur,video_action,video_name);
        sendBroacast(context,DATA_TYPE_COLLECTION,VOD_SP_PLAY,extData);
    }
    public void searchEvent(Context context,String search_key,String result_key){
        Log.d(TAG, "searchEvent: 搜索事件"+search_key+result_key);
        String extData = String.format("app=%s;source=%s;target=%s;page_type=%s;search_key=%s;result_key=%s",BuildConfig.APPLICATION_ID,null,null,null,search_key,result_key);
        sendBroacast(context,DATA_TYPE_COLLECTION,VOD_SP_SEARCH,extData);
    }
    public void orderEvent(Context context,int state,int price,String productId){
        String extData = String.format("app=%s;product_name=%s;product_price=%d;product_id=%s;page_level=%d;user_action=%s",BuildConfig.APPLICATION_ID,state,price,productId,1);
        sendBroacast(context,DATA_TYPE_COLLECTION,PRODUCT_BUY_PAGEVIEW,extData);
    }
    public void collectEvent(Context context ,int action,String video_id,String video_name,String asset_id,String category_id){
        Log.d(TAG, "collectEvent: 收藏事件"+action+video_id);
        String extData = String.format("app=%s;action=%d;asset_id=%s;category_id=%s;video_id=%s;video_name=%s;special_id=%s;special_name=%s",BuildConfig.APPLICATION_ID,action,asset_id,category_id,video_id,video_name,null,null);
        sendBroacast(context,DATA_TYPE_COLLECTION,5,extData);
    }

    private void sendBroacast(final Context context,final int dataType,final int userAction,final String extData){
        if(DiffConfig.CurrentTVBox instanceof TOPWAYBox) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String action1 = "com.topway.appmsg";
                    Intent intent = new Intent();
                    intent.setAction(action1);//广播收集action，固定定义
                    intent.putExtra("datatype", dataType);//参考Datetype定义
                    intent.putExtra("useraction", userAction);//参考UserAction定义
                    intent.putExtra("extdata", extData);//扩展信息mActivity.sendBroadcast(intent);
                    Log.d(action1, "run: " + userAction);
                    context.sendBroadcast(intent);
                }
            }).start();
        }
    }
    public boolean isRegister(Context context){
        boolean isRegister = false;

        return false;
    }
   public void appErr(Context context,String err){
       Log.d(TAG, "appErr: 应用报错");
       String extData = String.format("app=%s;ver=%s;error=%s",BuildConfig.APPLICATION_ID,BuildConfig.VERSION_NAME,err);
       sendBroacast(context,251,0,extData);
   }
   public void appCrash(Context context,String msg){
       String extData = String.format("app=%s;ver=%s;crashlog=%s;curtime=%s",BuildConfig.APPLICATION_ID,BuildConfig.VERSION_NAME,msg,simpleDateFormat.format(new Date()));
       sendBroacast(context,254,0,extData);
   }



    public void pageOpen(Context context,String app_id,String category_id,String parent_id,String level,String name){
        String extData = String.format("app_id=%s;category_id=%s;parent_id=%s;level=%s;name=%s",app_id,category_id,parent_id,level,name);
        sendBroacast(context,23,0,extData);
    }

    public void vodOpen(Context context,String ver,String app,String curtime){
        String extData = String.format("app=%s;ver=%s;curtime=%s",app,ver,curtime);
        sendBroacast(context,253,0,extData);
    }

    public void playhuyaEvent(Context context,String tsid,String freq,String app,String servicetype,String provider_id,String category_id,String category_name,String asset_id,String asset_name,String episodes,String vodepisodes,String program_time){
        String extData = String.format("tsid=%s;freq=%s;app=%s;servicetype=%s;provider_id=%s;category_id=%s;category_name=%s;asset_id=%s;\n" +
                "asset_name=%s;episodes=%s;vodepisodes=%s;program_time=%s;id=%s" ,tsid,freq,app,servicetype,provider_id,category_id,category_name,asset_id,asset_name,episodes,vodepisodes,program_time,"0");
        sendBroacast(context,15,0,extData);
    }


    public void playhuyaStopEvent(Context context,String tsid,String freq,String app,String servicetype,String provider_id,String category_id,String category_name,String asset_id,String asset_name,String episodes,String vodepisodes,String program_time){
        String extData = String.format("tsid=%s;freq=%s;app=%s;servicetype=%s;provider_id=%s;category_id=%s;category_name=%s;asset_id=%s;\n" +
                "asset_name=%s;episodes=%s;vodepisodes=%s;program_time=%s;id=%s" ,tsid,freq,app,servicetype,provider_id,category_id,category_name,asset_id,asset_name,episodes,vodepisodes,program_time,"0");
        sendBroacast(context,15,1,extData);
    }
    public void vodPlay(Context context,int userAction,String vod_id,String app,String playtime,String length,String type){
        String extData = String.format("app=%s;video_id=%s;play_time=%s;length=%s;type=%s",app,vod_id,playtime,length,type);
        sendBroacast(context,18,userAction,extData);
    }
}
