package com.utvgo.handsome.diff;

import android.content.Context;

public class GDTVEnv implements IEnv {

    public void initEnv()
    {
        DiffConfig.baseHost = "http://172.16.146.40";
        DiffConfig.imageHost = "http://172.16.146.40:81/huya/uploadFile/image/";  //正式
        DiffConfig.authHost = "http://172.16.146.6";
        DiffConfig.statisticsHost = "http://172.16.146.6";

        DiffConfig.activityHost = DiffConfig.baseHost+"/huya-activity-client-web";
        DiffConfig.dataHost="http://172.16.146.40/huya-tv-mvc";
        DiffConfig.host = DiffConfig.baseHost + "/utvgo-uu-web/";
        DiffConfig.orderHost = DiffConfig.authHost + "/uusports-order-web/";
        DiffConfig.playHost = "http://172.16.146.69:17553/EG/huya/";

        DiffConfig.TestVideoUrl = "http://172.16.146.70:17553/QQMusic/mv/blue_ray/g0022q7z0um_blue_ray.mp4";
        DiffConfig.TestAudioUrl = "http://172.16.146.70:17553/QQMusic/cn0/0000QHSk0ruY3Y/000j8Tfr0wGFXi.m4a";
        DiffConfig.UseWebIntroduction = false;
        DiffConfig.WebUrlBase = "http://172.16.146.56/uusports/huya";
      //  DiffConfig.IntroduceUrl = "http://172.16.146.56/huyaTV/activity.html";
        DiffConfig.IntroduceUrl = "http://172.16.146.56/huyaTV/topic.html?themId=155&styleID=5&from=&backUrl=http%3A%2F%2F172.16.146.56%2FhuyaTV%2F%3FfocusArea%3D2%26pos%3D4";
    }

    @Override
    public void jumpAppStore(Context context) {

    }
}
