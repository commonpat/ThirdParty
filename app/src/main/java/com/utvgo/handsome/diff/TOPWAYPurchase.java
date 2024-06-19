package com.utvgo.handsome.diff;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.bean.BeanAuthManagerData;
import com.utvgo.handsome.bean.BeanContent;
import com.utvgo.handsome.bean.TOPWAYLockRespone;
import com.utvgo.handsome.bean.TOPWAYOrderRespone;
import com.utvgo.handsome.bean.TOPWAYTryBestBean;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.NetworkUtils;
import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.activity.NewHomeActivity;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanUpgrade;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.Tools;


import java.util.Locale;
import java.util.UUID;


/**
 * @author wzb
 * @description:
 * @date : 2020/9/22 10:19
 */
public class TOPWAYPurchase extends IPurchase{
    public static final String productId = "huya";
    public static final String spId = "sp_hy";
    public static final String cpVideoId = "sphys_705984307";
    public static boolean isBlackUser = false;

    public static final String CallBackUrl="http://211.148.220.48:13380/cq-order-web/chongqing/cqUserController/callbackSaveAuthorize.utvgo";

    public  BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            String state = intent.getStringExtra("state");
            String reason = intent.getStringExtra("reason");
            String session_id = intent.getStringExtra("session_id");
            String ex_data = intent.getStringExtra("ex_data");
            //String product = intent.getStringExtra("product");
            Log.d("TAG", state+"onReceive: "+intent.getAction());
            if("com.starcor.authorities.pay.result".equals(action)){
                if ("0".equals(state)){
                    setOrderStatus(0);
                    refreshOrderStatus(context,null);
                    callBackFunc(context,Integer.valueOf(0),reason);

                }else {
                    tryBest2(context, new TryBestCallback() {
                        @Override
                        public void d(String s) {

                        }
                    });
                    refreshOrderStatus(context,null);
                    callBackFunc(context,Integer.valueOf(-1),reason);
                }
                context.unregisterReceiver(broadcastReceiver);
            }
        }
    };
    @Override
    public void pay(Context context, CommonCallback callback) {
        entryOrder(context,callback);
    }


    @Override
    public void auth(final Context context, final AuthCallback authCallback) {
        final String keyNo = DiffConfig.getCA(context);

        NetworkService.defaultService().getKeyNoLimitType(context, new JsonCallback<BeanAuthManagerData>() {
            @Override
            public void onSuccess(Response<BeanAuthManagerData> response) {
                BeanAuthManagerData beanAuthManagerData = response.body();
                if(beanAuthManagerData.getCode().equals("1")) {
                    Log.d("TAG", "onSucceeded:authManager: "+beanAuthManagerData.getData().getType());
                    if (beanAuthManagerData.getData().getType().equals("0")) {//正常用户
                        enterAuth(context, authCallback);
                    } else if (beanAuthManagerData.getData().getType().equals("1")) {//白名单
                        setOrderStatus(0);
                        authCallback.onFinished("0");
                    } else if (beanAuthManagerData.getData().getType().equals("2")) {//黑名单
                        isBlackUser = true;
                        authCallback.onFinished("1");
                    } else if (beanAuthManagerData.getData().getType().equals("3")) {//红名单

                    } else {
                        enterAuth(context, authCallback);
                    }
                }else {
                    enterAuth(context, authCallback);
                }
            }

            @Override
            public void onError(Response<BeanAuthManagerData> response) {
                super.onError(response);
                enterAuth(context, authCallback);
            }
        });

    }

    @Override
    public void refreshOrderStatus(Context context, AuthCallback callback) {
            if(context instanceof NewHomeActivity){
                NewHomeActivity activity = (NewHomeActivity) context;
                activity.towayHomeHandle.sendEmptyMessage(999);
            }
    }

    @Override
    public void syncUserAuthorization(Context context,String message) {

        try {
            if ("0".equals(message)) {
                NetworkService.defaultService().syncUserAuthorization(context, "0", productId);
            } else {
                NetworkService.defaultService().syncUserAuthorization(context, "1", productId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 天威威视云鉴权
     * */
    public void enterAuth(final Context context, final AuthCallback authCallback){
        /**
         * uri：content://com.starcor.authorities/api
         * cp_id：mgtv
         * action：productAuth
         * content：{
         * 	"source": "STARCOR",
         * " product_id ": "BBB58846847b9c388af7ca9d9fad1",
         * "video_id": "AAA6847b9c388af7ca9d9fad1",
         * }
         * timestamp：111809335
         * sign：B29A5923D40145C2EFE118872330B54C
         *spid:sp_qqyy
         * sp名称：QQ音乐
         * 媒资包id:qqyy
         * 子栏目id:1000001
         * 栏目名称：QQ音乐
         * 产品包：00092254,00092255
         * 产品授权码: 90129
         *
         * */
        class  Content {
            private String source,product_id,video_id;
            public String getSource() {
                return source;
            }
            public void setSource(String source) {
                this.source = source;
            }
            public String getProduct_id() {
                return product_id;
            }
            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }
            public String getVideo_id() {
                return video_id;
            }
            public void setVideo_id(String video_id) {
                this.video_id = video_id;
            }
        }
        //正式：spqqyys_qqyy1000001       spqqyys_j00347e77om_mp4
        //测试：qqmusics_qqyy1000001
        final String action= "mediaAuth";
        BeanContent content = new BeanContent("MEDIA_AUTH","OTHER",cpVideoId,"0",context.getPackageName(),"0");
        String contentJsonString = JSON.toJSONString(content);
        TOPWAYBox.queryDataCoreContent(context, action, contentJsonString, new TOPWAYBox.ContentCallback() {
            @Override
            public void onSuccess(String s) {
                JSONObject jsonObject = JSONObject.parseObject(s);
                String  productId =  jsonObject.getString("checked_product_id");
                setOrderStatus(0);
                //callBackFunc(context,Integer.valueOf(0),"order success");
                authCallback.onFinished("0");

            }
            @Override
            public void onFail(String s) {
                authCallback.onFinished("1");
             //   callBackFunc(context,Integer.valueOf(1),"order failed");
            }
        });
    }





    public  void entryOrder(Context context,CommonCallback callback) {
        if(isBlackUser){
            HiFiDialogTools.getInstance().showtips(context,"用户被限制订购，请联系客服！",null);
            return;
        }
        try {
            IntentFilter intentFilter = new IntentFilter("com.starcor.authorities.pay.result");

            context.registerReceiver(broadcastReceiver, intentFilter);
            final String ORDER_ACTION = "com.starcor.authorities.pay";
            Intent intent = new Intent();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            intent.setAction(ORDER_ACTION);//指定的获取支付的action
            intent.putExtra("session_id", uuid);//请求前自行生成的UUID
            intent.putExtra("cp_id", spId);//云平台统一分配的CPID
            intent.putExtra("package_name", context.getPackageName());//第三方CP应用包名
            intent.putExtra("cp_video_id", cpVideoId);//cp的媒资ID
            intent.putExtra("products", "00092417,00092418");//产品包列表
            intent.putExtra("ex_data", "utvgo");//第三方附加参数
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.sendBroadcast(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("TAG", "entryOrder: ");
    }

    public  void  callBackFunc(Context context,int code,String msg){
       // NetworkService.defaultService().callBackFunc(context,code,msg);
        NetworkService.defaultService().newCallBackFunc(context,code,msg);
    }
    public interface TryBestCallback{
        void d(final String s);
    }
    public void tryBest(final Context context, final TryBestCallback callback) {
        String apiUrl = DiffConfig.baseHost + String.format(Locale.getDefault(), "/qq-report-web/report/reportController/judgeWhetherOrder.utvgo?keyNo=%s&interfaceType=1",
                DiffConfig.getCA(context));
        OkGo.<TOPWAYTryBestBean>get(apiUrl).cacheMode(CacheMode.NO_CACHE).tag(context).execute(new JsonCallback<TOPWAYTryBestBean>() {
            @Override
            public void onSuccess(Response<TOPWAYTryBestBean> response) {
                TOPWAYTryBestBean bean = response.body();
                if ("1".equalsIgnoreCase(bean.getCode())) {
                    if (bean.isData()){
                        checkUserLock(context,callback);
                       // towayGoPay(context,callback);
                    }
                 }

            }
        });
    }
    public void tryBest2(final Context context, final TryBestCallback callback) {
        String apiUrl = DiffConfig.baseHost + String.format(Locale.getDefault(), "/qq-report-web/report/reportController/judgeWhetherOrder.utvgo?keyNo=%s&interfaceType=8",
                DiffConfig.getCA(context));
        OkGo.<TOPWAYTryBestBean>get(apiUrl).cacheMode(CacheMode.NO_CACHE).tag(context).execute(new JsonCallback<TOPWAYTryBestBean>() {
            @Override
            public void onSuccess(Response<TOPWAYTryBestBean> response) {
                TOPWAYTryBestBean bean = response.body();
                if ("1".equalsIgnoreCase(bean.getCode())) {
                    if (bean.isData()){
                        checkUserLock(context,callback);
                       // towayGoPay(context,callback);
                    }
                }

            }
        });
    }
    public void checkUserLock(final Context context,final TryBestCallback callback){
        String user_id = Tools.getStringPreference(context,"user_id");
        String version = Tools.getStringPreference(context,"version");
        String device_id = Tools.getStringPreference(context,"device_id");
        String webtoken = Tools.getStringPreference(context,"webtoken");
        String mac = Tools.getStringPreference(context,"mac");
        String lockUrl = "http://service.interface.wsyv.topway.cn/sztw/ScaaaUser?nns_func=scaaa_get_user_parent_lock&nns_tag=26" +
                "&nns_mac=" +mac+
                "&nns_mac_id=" +mac+
                "&nns_version=" +version+
                "&nns_user_agent=nn_player%2Fstd%2F1.0.0" +
                "&nns_user_id=" +user_id+
                "&nns_buss_id=1000062.001.001" +
                "&nns_webtoken=" +webtoken+
                "&nns_output_type=json" +
                "&nns_device_id="+device_id;
        //Log.d(TAG, "checkUserLock: "+lockUrl);
        OkGo.<TOPWAYLockRespone>get(lockUrl).cacheMode(CacheMode.NO_CACHE).tag(context).execute(new JsonCallback<TOPWAYLockRespone>() {

            @Override
            public void onSuccess(Response<TOPWAYLockRespone> response) {
                TOPWAYLockRespone bean = response.body();
                if((bean.getResult().getState() == 300000) && "0".equals(bean.getUser_child_locker_detail_info().getNns_state())){
                    towayGoPay(context,callback);
                }else {
                    reportTryBestResult(context,false,"user_locked",callback);
                    callBackFunc(context,Integer.valueOf("-1"),"user_locked");
                }
            }
        });
    }
    /**
     * 去订购
     * */
    public void towayGoPay(final Context context, final TryBestCallback callback){
        String user_id = Tools.getStringPreference(context,"user_id");
         String version = Tools.getStringPreference(context,"version");
        String device_id = Tools.getStringPreference(context,"device_id");
        String webtoken = Tools.getStringPreference(context,"webtoken");
        String mac = Tools.getStringPreference(context,"mac");
        String apiUrl = "http://service.interface.wsyv.topway.cn/sztw/pay?nns_partner_id=sztw_pay_tianweishixun&nns_channel_id=boss_pay&nns_mode_id=boss_payment&nns_name=QQ%E9%9F%B3%E4%B9%90TV%E7%89%88%28%E5%8C%85%E6%9C%88%29&nns_from_id=&nns_money=2000&nns_currency_type=CNY&nns_order_type=product_buy&nns_order_price=2000&nns_order_total_price=2000&nns_product_num=1&nns_product_price=2000&nns_product_id=00092417&nns_product_name=%E8%99%8E%E7%89%99TV(%E5%8C%85%E6%9C%88)&" +
                "nns_product_fee_id=5f8fe8532872f5941fdf5270713150cd" +
                "&nns_product_fee_name=%E5%A8%81%E8%A7%86%E4%BA%91QQ%E9%9F%B3%E4%B9%90TV%E7%89%88%28%E5%8C%85%E6%9C%88%29&nns_video_type=0&nns_price_unit=month&nns_from_type=CMS&nns_video_id=spqqyys_qqyy1000002&nns_func=create_pay_order&nns_tag=26&"+
                "nns_mac=" + mac+
                "&nns_mac_id=" + mac+
                "&nns_version=" + version+
                "&nns_user_agent=nn_player%2Fstd%2F1.0.0" +
                "&nns_user_id=" +user_id+
                "&nns_buss_id=1000062.001.001" +
                "&nns_webtoken=" + webtoken +
                "&nns_output_type=json&nns_device_id="+device_id;
        OkGo.<TOPWAYOrderRespone>get(apiUrl).cacheMode(CacheMode.NO_CACHE).tag(context).execute(new JsonCallback<TOPWAYOrderRespone>() {
            @Override
            public void onSuccess(Response<TOPWAYOrderRespone> response) {
                //Log.d("qqmusicwzb", "onSuccess: "+response.body());
                TOPWAYOrderRespone bean = response.body();
                if ("0".equalsIgnoreCase(bean.getResult().getState())) {
                    setOrderStatus(0);
                    String msg = bean.getBuy_order().getProduct_id() + bean.getBuy_order().getOrder_name()+bean.getResult().getReason();
                    syncUserAuthorization(context,"0");
                    reportTryBestResult(context,true,msg,callback);
                    callBackFunc(context,0,msg);

                }else {
                    String msg = bean.getResult().getReason();
                    String state = bean.getResult().getState();
                    reportTryBestResult(context,false,msg,callback);
                    callBackFunc(context,Integer.valueOf(state),msg);
                }

            }
        });

    }
    /**
     *
     * 上报受理记录
     */
    public void reportTryBestResult(final Context context, final boolean isSuccess, final String s, final TryBestCallback callback)
    {
        if(isSuccess)
        {
            DiffConfig.CurrentPurchase.setPurchased();
        }
        String apiUrl = DiffConfig.baseHost + String.format(Locale.getDefault(), "/qq-report-web/report/reportController/saveOrdeRecoder.utvgo?keyNo=%s&status=%s&msg=%s&vipCode=APP0hy",
                DiffConfig.getCA(context), isSuccess ? "200" : "-1", s);
        if(callback != null)
        {
            callback.d(apiUrl);
        }
        OkGo.<BaseResponse>get(apiUrl).cacheMode(CacheMode.NO_CACHE).tag(context).execute(new JsonCallback<BaseResponse>() {
            @Override
            public void onSuccess(Response<BaseResponse> response) {
                try {
                    BaseResponse bean = response.body();
                    String s = String.format(Locale.getDefault(), "saveOrderRecoder ca %s result " + bean.getCode(), DiffConfig.getCA(context));
                    if(callback != null)
                    {
                        callback.d(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
