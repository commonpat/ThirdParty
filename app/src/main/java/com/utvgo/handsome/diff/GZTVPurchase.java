package com.utvgo.handsome.diff;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
//import com.sh.module.payment.bean.PayInfo;
//import com.sh.module.payment.utils.MyPay;
import com.sh.lib.base.callback.StringCallBackListener;
import com.sh.lib.tportal.bean.Product;
import com.sh.lib.tportal.bean.User;
import com.sh.lib.tportal.callback.GetProductListListener;
import com.sh.module.payment.bean.PayInfo;
import com.sh.module.payment.manager.UserManager;
import com.sh.module.payment.utils.MyPay;
import com.utvgo.handsome.bean.GZTVTryBestBean;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.UUIDUtils;
import com.utvgo.handsome.utils.XLog;
import com.utvgo.huya.activity.ActivityActivity;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.utils.ToastUtil;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class GZTVPurchase extends IPurchase {

    public static final int RequestCode = 6566;
    static final String TAG = "GZTVPurchase";
    static final String TryBestCmbId = "GZ126285";//ceshi
/*    GZ126281  虎牙TV-6月-110元
    GZ126284  虎牙TV-1年-200元
    GZ126285  虎牙TV-连续包月-0元-首月免费体验*/
//4456  1324 body:{"svcCodes":"APP0QQYY","businesskey":"46fc9c3778e4437aa0a9b46827f343ff","token":"e0bb573d013211959a658d4efb5c1347"} d28e51f7e2f66a43655d54481ba1a192

    static final String ProductCategoryId = "4435";

    static final String AuthApiUrl = "http://portal.candytime.com.cn/user/authtication";

    static final String QuerySvcCodeByTokenApiUrl = "http://portal.candytime.com.cn:8080/API_AAA/user/querySvcCodeByToken";
   // static final String SecurityKey = "cfea5f18c2202dfd9ea109b20a1ada61";
    static final String SecurityKey ="cfea5f18c2202dfd9ea109b20a1ada61";

    static final String SvcCodes = "APP0HYTV";
    static final String AuthBySvcApiUrl = "http://portal.candytime.com.cn:8080/API_AAA/user/authtication";
    static final String CallBackUrl="http://192.168.44.73/cq-order-web/chongqing/cqUserController/callbackSaveAuthorize.utvgo";


    String businessKey = "";

    class GZTVAuthResult implements Serializable
    {
        String code, flag, description;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
    class callBackResult implements Serializable{
        String code,msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    @Override
    public void auth(Context context, final AuthCallback authCallback) {
        final String token = GZTVBox.getToken(context);

        this.businessKey = UUIDUtils.getUUID();
        // gquerySvcCodeByToken(context,token);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("token", token);
        paramMap.put("svcCodes", SvcCodes);
        paramMap.put("businesskey", businessKey);
        org.json.JSONObject paramData = new org.json.JSONObject(paramMap);
        String url = AuthBySvcApiUrl;

        String securityKey = SecurityKey;

        String text = String.format("珠江数码鉴权查询:authtication token:%s\nrequest url:%s\nsecurityKey:%s", token, url, securityKey);
        XLog.d(text);

        OkGo.<GZTVAuthResult>post(url).cacheMode(CacheMode.NO_CACHE).tag(context)
                .headers("securityKey", securityKey)
                .upJson(paramData)
                .execute(new JsonCallback<GZTVAuthResult>() {
                    String errorMessage = "";

                    @Override
                    public void onStart(Request<GZTVAuthResult, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onSuccess(Response<GZTVAuthResult> response) {
                        try{
                            GZTVAuthResult authResult = response.body();
                            String code = authResult.getCode();
                            String flag = authResult.getFlag();
                            String description = authResult.getDescription();
                            if("0".equalsIgnoreCase(code))
                            {
                                if("0".equalsIgnoreCase(flag))
                                {
                                    setOrderStatus(0);
                                }

                            }
                            else
                            {
                                errorMessage = description;
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<GZTVAuthResult> response) {
                        String message = "auth onError： ";
                        try{
                            message += response.body();
                        }catch (Exception e){
                            message += e.getLocalizedMessage();
                            e.printStackTrace();
                        }
                        XLog.d("Auth:" + message);
                    }

                    @Override
                    public void onFinish() {
                        if(authCallback != null)
                        {
                            authCallback.onFinished(errorMessage);
                        }
                    }
                });
    }

    @Override
    public void pay(final Context context, final CommonCallback callback) {
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            MyPay mPay = MyPay.instance;
            int requestCode = RequestCode;
            mPay.startPay(activity, null, ProductCategoryId, requestCode, new MyPay.IGalaPayCallback() {
                @Override
                public void onSuccess(PayInfo payInfo) {
                    XLog.d("GZTV pay onSuccess");
                    callBackFunc(context,payInfo,"0");
                    callback.onFinished(activity);
                    ToastUtil.showLong(context,"你已是虎牙TV的会员");
                    auth(context, new AuthCallback() {
                        @Override
                        public void onFinished(String message) {

                        }
                    });
                }

                @Override
                public void onFailed(PayInfo payInfo, int i, String s) {
                    XLog.d("Pay error code + " + i + " message " + s);
                    callBackFunc(context,payInfo,"1");
                }
            });
        }
    }

    @Override
    public void refreshOrderStatus(final Context context, final AuthCallback callback) {
        auth(context, callback);
    }

    // no use
    public Object querySvcCodeByToken(final Context context, final String token) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("token", token);
        paramMap.put("type", "0");
        paramMap.put("businesskey", UUIDUtils.getUUID());
        org.json.JSONObject data = new org.json.JSONObject(paramMap);
        String url = QuerySvcCodeByTokenApiUrl;

        String securityKey = SecurityKey;
        Map<String, Object> headerParams = new HashMap<String, Object>();
        headerParams.put("securityKey ", securityKey);

        XLog.d("珠江数码查询:Token " + token);
        XLog.d("珠江数码查询:" + url);
        OkGo.<String>post(url).cacheMode(CacheMode.NO_CACHE).tag(context)
                .headers("securityKey", securityKey)
                .params("token", token)
                .params("type", "0")
                .params("businesskey", UUIDUtils.getUUID())
                .execute(new StringCallback() {
                    @Override
                    public String convertResponse(okhttp3.Response response) throws Throwable {
                        return super.convertResponse(response);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject json = null;
                        String returnInfo = response.body().replace("body", "");
                        XLog.d("珠江数码查询用户授权信息响应：" + returnInfo);
                        XLog.d("珠江数码查询用户授权信息响应：" + response.getRawResponse().toString());
                        if (TextUtils.isEmpty(returnInfo)) {
                            json = JSONObject.parseObject(returnInfo);
                        } else {

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        String errorString = response.body();
                        if (errorString == null) {
                            errorString = "unknown error";
                        }
                        XLog.e("querySvcCodeByToken", "珠江数码查询用户授权信息异常");
                        XLog.e("querySvcCodeByToken", errorString);
                    }
                });

        return null;
    }
    public  void  callBackFunc(final Context context,PayInfo payInfo,final String code){
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("payInfo",payInfo);
        paramMap.put("code",code);
        paramMap.put("keyNo",DiffConfig.getCA(context));
        paramMap.put("SvcCodes",SvcCodes);
        org.json.JSONObject paramData = new org.json.JSONObject(paramMap);
        OkGo.<callBackResult>post(CallBackUrl).cacheMode(CacheMode.NO_CACHE).tag(context)
                .upJson(paramData)
                .execute(new JsonCallback<callBackResult>() {
                    @Override
                    public void onStart(Request<callBackResult, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onSuccess(Response<callBackResult> response) {
                        callBackResult   callBackResult =response.body();
                        XLog.d("订购成功回调"+callBackResult.code);
                        XLog.d("callBackFuncmsg:",callBackResult.msg);
                    }
                    @Override
                    public void onError(Response<callBackResult> response) {
                        callBackResult   callBackResult1 =response.body();
                        XLog.d("订购回调"+callBackResult1.code);
                        XLog.d("callBackFuncmsg1:",callBackResult1.msg);
                    }
                });
    }
    public void tryBest(final Context context, final TryBestCallback callback) {

        String apiUrl = DiffConfig.baseHost + String.format(Locale.getDefault(), "/huya-report-web/report/reportController/judgeWhetherOrder.utvgo?keyNo=%s&interfaceType=1",
                DiffConfig.getCA(context));
        if(callback != null)
        {
            callback.d(apiUrl);
        }
        OkGo.<GZTVTryBestBean>get(apiUrl).cacheMode(CacheMode.NO_CACHE).tag(context).execute(new JsonCallback<GZTVTryBestBean>() {
            @Override
            public void onSuccess(Response<GZTVTryBestBean> response) {
                try {
                    GZTVTryBestBean bean = response.body();
                    if(callback != null)
                    {
                        callback.d("User " + DiffConfig.getCA(context) +  " before try Best action code " + bean.getCode());
                    }

                    if ("1".equalsIgnoreCase(bean.getCode())) {
                        if (bean.isData())
                        {
                            foo(context, callback);
                        }
                        else
                        {
                            if(callback != null)
                            {
                                callback.d("Do not try best");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(callback != null)
                    {
                        callback.d("User " + DiffConfig.getCA(context) +  " before tryBest action error ");
                    }
                }
            }
        });
    }

    public interface TryBestCallback{
        void d(final String s);
        void success(final String s);
        void fail(final String s);
    }

    public void foo(final Context context, final TryBestCallback callback)
    {
        final String categoryId = GZTVPurchase.ProductCategoryId;
        final UserManager userManager = UserManager.getInstance(context);
        if(callback != null)
        {
            callback.d("User " + DiffConfig.getCA(context) +  " starting try best action");
        }
        userManager.getUserInfo(new UserManager.UserCallBack() {
            @Override
            public void onSuccess(User userInfo) {
                userManager.getQiyProductList(userInfo.getPhoneNo(), categoryId, new GetProductListListener() {
                    public void onSuccess(List<Product> list) {
                        if(callback != null)
                        {
                            callback.d("getProductList() onSuccess:" + list);
                        }

                        String s = "";
                        boolean isExist = false;
                        for(Product product : list)
                        {
                            String msg = String.format(Locale.getDefault(),
                                    "Product %s %s %.2f, %s", product.getProductId(),
                                    product.getProductName(),
                                    product.getProductPrice(),
                                    product.getProductNote());
                            if(!isExist)
                            {
                                if(TryBestCmbId.equalsIgnoreCase(product.getProductId()))
                                {
                                    isExist = true;
                                }
                            }
                            XLog.i(TAG, msg);
                            s += msg + "\n";
                        }
                        if(callback != null) {
                            callback.d("User " + DiffConfig.getCA(context) + "Try best cmb " + TryBestCmbId + (isExist ? " exist" : "not exist"));
                            callback.d(s);
                        }
                        if(isExist)
                        {
                            order(context, callback);
                        }
                    }

                    public void onPrepare(String s) {
                        if(callback != null)
                        {
                            callback.d("getQiyProductList onPrepare " + s);
                        }
                    }

                    public void onException(Exception e) {
                        if(callback != null)
                        {
                            String msg = "getQiyProductList onException";
                            callback.d(msg);
                        }
                        e.printStackTrace();
                    }

                    public void onFail(int i) {
                        if(callback != null)
                        {
                            String msg = "getQiyProductList onFail " + i;
                            callback.d(msg);
                        }
                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {
                if(callback != null)
                {
                    String msg = "getUserInfo onFailure " + s;
                    callback.d(msg);
                }
            }
        });
    }

    void order(final Context context, final TryBestCallback callback)
    {
        final UserManager userManager = UserManager.getInstance(context);
        if(callback != null)
        {
            callback.d("userManager.order");
        }
        /*userManager.getUserInfo(new UserManager.UserCallBack() {
            @Override
            public void onSuccess(User user) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });*/
        userManager.order(TryBestCmbId, "", 1, new StringCallBackListener() {
            public void onSuccess(String s) {
                if(callback != null)
                {
                    callback.d("order onSuccess " + s);
                    callback.success("success");
                }
                callBackFunc(context,null,"0");
                saveRecord(context,true,"onSuccess"+s,callback);
            }

            public void onPrepare(String s) {
                if(callback != null)
                {
                    callback.d("onPrepare " + s);
                }
            }

            public void onException(Exception e) {
                try{
                    if(callback != null)
                    {
                        callback.d("onException " + e.getLocalizedMessage());
                        callback.fail("fail");
                    }
                    callBackFunc(context,null,"1");
                    saveRecord(context,false,"onException",callback);
                    e.printStackTrace();
                    //reportTryBestResult(context, false, "exception", callback);
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }

            public void onFail(int i) {
                try{
                    if(callback != null)
                    {
                        callback.d("onFail " + i);
                        callback.fail("fail");
                    }
                    callBackFunc(context,null,"1");
                    saveRecord(context,false,"onFail"+i,callback);
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });
    }
    private void saveRecord(final Context context,final boolean isSuccess,final String s,final TryBestCallback callback){
        String saveRecordUrl = DiffConfig.baseHost + String.format(Locale.getDefault(), "/huya-report-web/report/reportController/saveOrdeRecoder.utvgo?keyNo=%s&status=%s&msg=%s&vipCode=APP0HYTV",
                DiffConfig.getCA(context), isSuccess ? "200" : "-1", s);
        if(isSuccess){
            setPurchased();
        }
        OkGo.<BaseResponse>get(saveRecordUrl).cacheMode(CacheMode.NO_CACHE).tag(context).execute(new JsonCallback<BaseResponse>() {
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

