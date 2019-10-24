package com.utvgo.handsome.diff;

public class GDTVEnv implements IEnv {

    public void initEnv()
    {
        DiffConfig.baseHost = "http://172.16.146.66";
        DiffConfig.imageHost = "http://172.16.146.66:81/cms/uploadFile/image/";  //正式
        DiffConfig.authHost = "http://172.16.146.6";
        DiffConfig.statisticsHost = "http://172.16.146.6";

        DiffConfig.activityHost = DiffConfig.baseHost;
        DiffConfig.host = DiffConfig.baseHost + "/utvgo-uu-web/";
        DiffConfig.orderHost = DiffConfig.authHost + "/qqmusic-order-web/";
        DiffConfig.playHost = "http://172.16.146.69:17553/QQMusic/";

        DiffConfig.TestVideoUrl = "http://172.16.146.70:17553/QQMusic/mv/blue_ray/g0022q7z0um_blue_ray.mp4";
        DiffConfig.TestAudioUrl = "http://172.16.146.70:17553/QQMusic/cn0/0000QHSk0ruY3Y/000j8Tfr0wGFXi.m4a";
    }
}
