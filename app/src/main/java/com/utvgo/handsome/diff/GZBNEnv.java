package com.utvgo.handsome.diff;

public class GZBNEnv implements IEnv {
    @Override
    public void initEnv() {
        //贵州
//    public static String baseHost = "http://10.69.45.55"; //测试环境的
  //      http://10.69.51.125/utvgo-tv-mvc/
   //     http://10.69.51.125/utvgo-user/
        DiffConfig.baseHost = "http://10.69.51.125:81";
        DiffConfig.imageHost = "http://10.69.51.125:81/cms/uploadFile/image/";  //正式
        DiffConfig.authHost = "http://10.69.51.125:81";
        DiffConfig.statisticsHost = "http://10.69.51.125:81";

        DiffConfig.activityHost = DiffConfig.baseHost;
        DiffConfig.orderHost = DiffConfig.authHost + "/cq-order-web/";
        DiffConfig.IntroduceUrl = "www.baidu.com";

//
//        DiffConfig.baseHost  = "http://10.69.45.49"; //正式环境
//        DiffConfig.activityHost = DiffConfig.baseHost;
//        DiffConfig.playHost =  DiffConfig.baseHost;
//
//        DiffConfig.host =  DiffConfig.baseHost + "/utvgo-uu-web/";
//        DiffConfig.orderHost =  DiffConfig.baseHost + "/cq-order-web/";
//        DiffConfig.statisticsHost =  DiffConfig.baseHost;
//        DiffConfig.imageHost =  DiffConfig.baseHost + ":81/cms/uploadFile/image/";
//        DiffConfig.authHost=DiffConfig.baseHost;
    }

}
