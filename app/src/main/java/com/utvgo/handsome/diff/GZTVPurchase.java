package com.utvgo.handsome.diff;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
//import com.sh.module.payment.bean.PayInfo;
//import com.sh.module.payment.utils.MyPay;
import com.sh.module.payment.bean.PayInfo;
import com.sh.module.payment.utils.MyPay;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.XLog;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GZTVPurchase extends IPurchase {

    public static final int RequestCode = 6566;

    static final String ProductCategoryId = "1234";

    static final String AuthApiUrl = "http://portal.candytime.com.cn/user/authtication";

    static final String QuerySvcCodeByTokenApiUrl = "http://portal.candytime.com.cn:8080/AAA/user/querySvcCodeByToken";
    static final String SecurityKey = "cfea5f18c2202dfd9ea109b20a1ada61";

    static final String SvcCodes = "APP0QQYY";
    static final String AuthBySvcApiUrl = "http://portal.candytime.com.cn:8080/AAA/user/authtication";

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

    @Override
    public void auth(Context context, final AuthCallback authCallback) {
        final String token = GZTVBox.getToken(context);

        this.businessKey = UUID.randomUUID().toString().replace("-", "");

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
                                else
                                {
                                    errorMessage = "not purchased";
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
            Activity activity = (Activity) context;
            MyPay mPay = MyPay.instance;
            int requestCode = RequestCode;
            mPay.startPay(activity, null, ProductCategoryId, requestCode, new MyPay.IGalaPayCallback() {
                @Override
                public void onSuccess(PayInfo payInfo) {
                    XLog.d("GZTV pay onSuccess");
                    auth(context, new AuthCallback() {
                        @Override
                        public void onFinished(String message) {

                        }
                    });
                }

                @Override
                public void onFailed(PayInfo payInfo, int i, String s) {
                    XLog.d("Pay error code + " + i + " message " + s);
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
        String uuidString = UUID.randomUUID().toString().replace("-", "");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("token", token);
        paramMap.put("type", "0");
        paramMap.put("businesskey", uuidString);
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
                .params("businesskey", uuidString)
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
}