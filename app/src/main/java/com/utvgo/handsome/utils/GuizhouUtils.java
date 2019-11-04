package com.utvgo.handsome.utils;

import android.text.TextUtils;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.huya.activity.BaseActivity;
import com.utvgo.huya.utils.StrTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;



/**
 * Created by haha on 2018/1/8.
 */

public class GuizhouUtils extends BaseActivity {

    public static final String TagGuizhouDid = "TagGuizhouDid";
    public static final String TagGuizhouAreaCode = "TagGuizhouAreaCode";
    public static final String TagGuizhouUid = "TagGuizhouUid";
    public static final String TagGuizhouWebtoken = "TagGuizhouWebtoken";
    public static final String TagGuizhouVersion = "TagGuizhouVersion";
    public static final String TagGuizhouSelectProducts = "TagGuizhouSelectProducts";

    public static final String ActionGuizhouBuyResult = "com.starcor.launcher.authorities.pay.result";

    public static final String cpid = "10102";
    public static final int price = 29;
    public static final String clientcode = "NFCM0001";
    public static final String clientpwd = "NFCM0001";
    public static final String clientcodePay = "1044";
    public static final String clientpwdPay = "6cb2d0b736f3841658074f9b854e8c54";
    public static final String payVodId = "HuYaSeriesID71119123"; //真的测试订购的

    public static final String authKey = "sp_10102_southern_media";
    //转发接口
    public static final String proxyUrl = DiffConfig.orderHost +
            "guizhou/gzUserController/proxyForwarding.utvgo?reqUrl=";
    public static final String aaaHostUrl = "http://aaa.interface.gzgd";
    public static final String authUrl = aaaHostUrl+"/gzgd/AuthIndexStandard";
    public static final String payInterfaceUrl = "http://gzgd.ao.com/nn_cms/nn_cms_view/tz_portal/n36_b.php" +
            "?nns_func=get_system_params_list&PramName=serverConfig";
    public static final String utvgoAuthUrl = DiffConfig.orderHost
            + "guizhou/gzUserController/AuthIndexStandard.utvgo";
    public static final String callbackSaveAuthorizeUrl = DiffConfig.orderHost
            + "guizhou/gzUserController/callbackSaveAuthorize.utvgo";

    public static String UrlN215a = "http://10.21.4.236/gzgd/AAAAuth";
    public static String UrlN60a = "http://aaa.interface.gzgd/gzgd/pay";
    public static String UrlN219a = "http://aaa.interface.gzgd/gzgd/ScaaaProduct";

    //boos连续扣费接口文档转发接口地址 测试的
    public static final String UrlBossExchange = "http://10.68.1.17:8180/";
    //正式

    //    public static final String UrlBossExchange = "http://10.68.1.65:8180/";
    public static final int GetGuizhouPlayUrl = 1000;
    public static final int GetGuizhouUtvgoAuth = 1001;
    public static final int GetGuizhouGetPayInterfaceUrl = 1002;
    public static final int GetGuizhouCallbackSaveAuthorize = 1003;
    public static final int GetScaaaDeviceAuthReq = 1004;
    public static final int GetFML1CreateOrder = 1005;
    public static final int GetFML1CreateQRWeixin = 1006;
    public static final int GetFML1CreateQRZfb = 1007;
    public static final int GetFML1QianDaiZF = 1008;
    public static final int GetFML1BusinessStatusQuery = 1009;
    public static final int GetFML1PayChannel = 1010;
    public static final int GetBuyProductList = 1011;
    public static final int ScaaaGetProductDiscountList = 1012;
    public static final int GetSTBindex = 1013;

    public static String getLocalWifiMacAddress() {
        String local = "/sys/class/net/usbnet0/address"; //点播网卡MAC地址
//        String local = "/sys/class/net/eth0/address";  //eth0网卡MAC地址
        File file = new File(local);
        String szMacString = "";
        BufferedReader reader = null;
        String szLine = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((szLine = reader.readLine()) != null) {
                szMacString = szMacString.concat(szLine);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return szMacString.replace(":", "-");
    }
    //组装url

    public static String createProxyUrl(String sourcePath, Map<String, String> params){
        StringBuffer sb = new StringBuffer();
        if (params.size() > 0) {
            for (String key : params.keySet()) {
                sb.append(key + "=");
                if (TextUtils.isEmpty(params.get(key))) {
                    sb.append("&");
                } else {
                    String value = params.get(key);
                    sb.append(value + "&");
                }
            }
        }
        String resultStr = sourcePath +"?"+ sb.toString();
        if (resultStr.endsWith("&")){
            resultStr = resultStr.substring(0, resultStr.length()-1);
        }
        resultStr = StrTool.getURLEncoder(resultStr);
        return resultStr;
    }


}
