package com.utvgo.huya.diff;





public class GZTVEnv implements IEnv {
    public void initEnv()
    {
        DiffConfig.baseHost = "http://172.16.146.66";
        DiffConfig.imageHost = "http://172.16.146.41:81/cms/uploadFile/image/";
        DiffConfig.authHost = "http://172.16.146.6/uusports-order-web/";
        DiffConfig.statisticsHost = "http://172.16.146.6";
        DiffConfig.playHost  = "http://172.16.146.69:17553/EG/huya/";


    }
}