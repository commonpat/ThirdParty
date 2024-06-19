package com.utvgo.handsome.diff;

import android.content.Context;

/**
 * @author wzb
 * @description:
 * @date : 2023/5/12 17:07
 */
class CHONGQINEnv implements IEnv {
    @Override
    public void initEnv() {

        DiffConfig.authHost = "http://192.168.18.50/cq-order-web/chongqing/cqUserController/authorization.utvgo";
        DiffConfig.WebUrlBase = "http://192.168.18.50/";

        DiffConfig.baseHost = "http://192.168.18.50/";
        DiffConfig.statisticsHost  = "http://192.168.18.50/";
        DiffConfig.host = "http://192.168.18.50/utvgo-qmkg-web/";
//        DiffConfig.orderHost = "http://192.168.18.50/qmkg-order-web/MayWideOrderController/order.utvgo"+"?platformId=1&branchNo=1&vipCode=vip_code_1002&backUrl=http%3A%2F%2FKARARAOKETVQUIT%2F";
        DiffConfig.orderHost = DiffConfig.baseHost + "/cq-order-web/orderController/order.utvgo";

        DiffConfig.imageHost = "http://192.168.18.50:81/cms/uploadFile/image/";
//        if (BuildConfig.DEBUG || LocalTest){
//            Log.i("TAG", "initEnv: "+(BuildConfig.DEBUG || LocalTest));
//            DiffConfig.WebUrlBase = "http://192.168.18.51/";
//            DiffConfig.authHost = "http://192.168.18.51/cq-order-web/chongqing/cqUserController/authorization.utvgo";
//            DiffConfig.statisticsHost  = "http://192.168.18.51/";
//            DiffConfig.host = "http://192.168.18.51/utvgo-qmkg-web/";
//            DiffConfig.orderHost = DiffConfig.baseHost + "/cq-order-web/orderController/order.utvgo";
//            DiffConfig.baseHost = "http://192.168.18.51/";
//            DiffConfig.imageHost = "http://192.168.18.51:81/cms/uploadFile/image/";
//            DiffConfig.testMorePicture = "http://192.168.18.51:81/cms/uploadFile/image/2021/11/26/20211126151117521.jpg";
//        }

    }

    @Override
    public void jumpAppStore(Context context) {

    }
}
