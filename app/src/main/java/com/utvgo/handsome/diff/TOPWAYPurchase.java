package com.utvgo.handsome.diff;

import android.content.Context;
import android.content.Intent;

import com.utvgo.handsome.interfaces.CommonCallback;

import java.util.UUID;

/**
 * @author wzb
 * @description:
 * @date : 2020/9/22 10:19
 */
public class TOPWAYPurchase extends IPurchase{
    @Override
    public void pay(Context context, CommonCallback callback) {
        entryOrder(context,callback);
    }

    @Override
    public void auth(Context context, AuthCallback authCallback) {

    }

    @Override
    public void refreshOrderStatus(Context context, AuthCallback callback) {

    }
    public static void entryOrder(Context context,CommonCallback callback) {
        final String ORDER_ACTION = "com.starcor.authorities.pay";
        Intent intent = new Intent();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        intent.setAction(ORDER_ACTION);//指定的获取支付的action
        intent.putExtra("session_id", uuid);//请求前自行生成的UUID
        intent.putExtra("cp_id", "123456");//云平台统一分配的CPID
        intent.putExtra("package_name", context.getPackageName());//第三方CP应用包名
        //intent.putExtra("cp_video_id", "cp的媒资ID");//cp的媒资ID
        intent.putExtra("products", "产品包");//产品包列表
        intent.putExtra("ex_data", "附加字段");//第三方附加参数
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.sendBroadcast(intent);
    }

}
