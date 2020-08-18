package com.utvgo.handsome.diff;

public class HNTVEnv implements IEnv {

    public void initEnv()
    {
        DiffConfig.baseHost = "http://100.125.9.4";
        DiffConfig.imageHost = "http://100.125.9.4:81/cms/uploadFile/image/";  //正式
        DiffConfig.authHost = "http://100.125.9.4";
        DiffConfig.statisticsHost = DiffConfig.baseHost;

        DiffConfig.activityHost = DiffConfig.baseHost+"/huya-activity-client-web";
        DiffConfig.host = DiffConfig.baseHost + "/utvgo-uu-web/";
        DiffConfig.orderHost = DiffConfig.authHost + "/qqmusic-order-web/";

        DiffConfig.UseWebIntroduction = true;
        DiffConfig.IntroduceUrl = "http://100.125.9.4/app/introduce.html";
    }
}
