package com.utvgo.handsome.diff;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.utvgo.huya.BuildConfig;

/**
 * @author wzb
 * @description:
 * @date : 2020/9/22 10:18
 */
public class TOPWAYEnv implements IEnv{
    @Override
    public void initEnv() {



        DiffConfig.baseHost = "http://172.23.32.15";
       // DiffConfig.baseHost = "http://211.148.220.48";
        if(BuildConfig.DEBUG){
            DiffConfig.baseHost = "http://211.148.220.48";
        }
        DiffConfig.dataHost = DiffConfig.baseHost + "/huya-tv-mvc/";
        DiffConfig.imageHost = DiffConfig.baseHost +":81/huya/uploadFile/image/";  //正式
        DiffConfig.authHost = DiffConfig.baseHost;
        DiffConfig.statisticsHost = DiffConfig.baseHost;
        DiffConfig.activityHost = DiffConfig.baseHost+"/activity-client-web";
        DiffConfig.orderHost = DiffConfig.authHost + "/huya-order-web/";
        DiffConfig.WebUrlBase = DiffConfig.baseHost + "/uusports/huya/";

        DiffConfig.UseWebIntroduction = false;
        DiffConfig.IntroduceUrl = DiffConfig.baseHost + "/app/uusports/huya/introduction.html";


    }

    @Override
    public void jumpAppStore(final Context context) {
        try{
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName("com.topway.tvappstore","com.topway.tvappstore.AppDetailActivity");
            //ComponentName compon entName = new ComponentName("com.utvgo.huya","com.utvgo.huya.activity.LaunchActivity");
            Log.d("", "jumpAppStore: "+context.getApplicationContext().getPackageName());
            intent.setComponent(componentName);
            intent.putExtra("packageName",context.getApplicationContext().getPackageName());
            intent.setComponent(componentName);
//                Stri ng category = "android.intent.category.DEFAULT";
//                intent.addCategory(category);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
