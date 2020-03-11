package com.utvgo.handsome.diff;

public class GZTVEnv implements IEnv {

    public void initEnv()
    {
//        DiffConfig.baseHost = "http://192.168.44.73";
//        DiffConfig.imageHost = "http://192.168.44.73:81/cms/uploadFile/image/";  //正式
//        DiffConfig.authHost = "http://192.168.44.73";
//        DiffConfig.statisticsHost = DiffConfig.baseHost;
//
//        DiffConfig.activityHost = DiffConfig.baseHost;
//        DiffConfig.host = DiffConfig.baseHost + "/utvgo-uu-web/";
//        DiffConfig.orderHost = DiffConfig.authHost + "/qqmusic-order-web/";
//
//        DiffConfig.playHost = "http://192.168.44.73:17553/QQMusic/";
//
//        DiffConfig.TestVideoUrl = "http://192.168.44.73:17553/QQMusic/mv/blue_ray/z0031n8jd2x_blue_ray.mp4";
//        DiffConfig.TestAudioUrl = "http://192.168.44.73:17553/QQMusic/cn0/0000QHSk0ruY3Y/000j8Tfr0wGFXi.m4a";
//
//        DiffConfig.UseWebIntroduction = true;
//        DiffConfig.IntroduceUrl = "http://192.168.44.73/app/introduce.html";
/*
    数据接口：http://192.168.44.73/utvgo-tv-mvc/
    订购：http://192.168.44.73/huya-order-web/
    收藏：http://192.168.44.73/utvgo-user/
    采集：http://192.168.44.76/utvgo-statistics/
    图片：http://192.168.44.76:81/cms/uploadFile/image/*/


        DiffConfig.baseHost = "http://192.168.44.73";
        DiffConfig.dataHost = "http://192.168.44.73/utvgo-tv-mvc/";
        DiffConfig.imageHost = "http://192.168.44.76:81/cms/uploadFile/image/";  //正式
        DiffConfig.authHost = "http://192.168.44.73";
        DiffConfig.statisticsHost = "http://192.168.44.76";
        DiffConfig.activityHost = DiffConfig.baseHost;
        DiffConfig.orderHost = DiffConfig.authHost + "/huya-order-web/";
        DiffConfig.playHost = "http://192.168.44.73:17553/EG/huya/";

        DiffConfig.UseWebIntroduction = true;
        DiffConfig.IntroduceUrl = "http://192.168.44.73/huya/introduce.html";
        GZTVPurchase.getProductInfo(this);
    }
}
