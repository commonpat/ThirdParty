package com.utvgo.handsome.diff;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.utils.ToastUtil;

public class DiffConfig {

    public static String baseHost; //正式
    public static String imageHost;  //正式
    public static String authHost;

    public static String playHost;
    public static String activityHost;
    public static String host;
    public static String orderHost;
    public static String statisticsHost;

    public static Boolean UseWebIntroduction = false;
    public static String IntroduceUrl = "";

    public static String TestVideoUrl = "";
    public static String TestAudioUrl = TestVideoUrl;
    public static String WebUrlBase;
    public static String dataHost;

    static IEnv GlobalEnv = null;
    public static ITVBox CurrentTVBox = null;
    public static IPurchase CurrentPurchase = null;
    public static String deviceId ;

    public static Proxyer CurrentProxy = new Proxyer();
    public static VoiceAssistant voiceAssistant = new VoiceAssistant();

    //修改平台即可
    public static Platform CurrentPlatform = BuildConfig.Platform;

    static Boolean LocalTest = false;

    public static void initEnv(final Boolean isLocalTest,Context context)
    {
        LocalTest = isLocalTest;

        switch (CurrentPlatform)
        {
            case gztv:
            {
                GlobalEnv = new GZTVEnv();
                CurrentTVBox = new GZTVBox();
                CurrentPurchase = new GZTVPurchase();
                break;
            }

            case gcable:
            {
                GlobalEnv = new GDTVEnv();
                CurrentTVBox = new GDTVBox();
                CurrentPurchase = new GDPurchase();

                //广东广电U点服务器需要设置代理以及语音助手
                CurrentProxy = new GDTVProxyer();
                voiceAssistant = new GDTVXiri();
                break;
            }

            case hncatv:
            {
                GlobalEnv = new HNTVEnv();
                CurrentTVBox = new HNTVBox();
                CurrentPurchase = new HNTVPurchase();
                break;
            }
            case topway:
            {
                GlobalEnv = new TOPWAYEnv();
                CurrentTVBox = new TOPWAYBox();
                CurrentPurchase = new TOPWAYPurchase();
            }

//            case gzbn:
//            {
//                GlobalEnv = new GZBNEnv();
//                CurrentTVBox = new GZBNBox();
//                CurrentPurchase = new GZBNPurchase();
//            }
        }

        if(GlobalEnv != null)
        {
            GlobalEnv.initEnv();
            CurrentTVBox.initDeviceInfo(context);
        }
    }
    /**
     * 广东修改视频地址前缀
     * @param playHost
     */
    public static void setPlayHost(String playHost) {
        DiffConfig.playHost = playHost;
    }

    public static String getCA(final Context context)
    {
        String ca = "";
        if(CurrentTVBox != null)
        {
            ca = CurrentTVBox.getCA(context);
        }
        return ca;
    }

    public static String getRegionCode(final Context context)
    {
        String ca = "";
        if(CurrentTVBox != null)
        {
            ca = CurrentTVBox.getRegionCode(context);
        }
        return ca;
    }

    public static String getRegion(final Context context)
    {
        switch (CurrentPlatform)
        {
            case hncatv:
                return "湖南";

            case gcable:
                return "广东";

            case gztv:
                return "广州";

            case gzbn :
                return "贵州";

            case topway:
                return "深圳天威";

            default:
                return "";
        }
    }

    public static boolean validateDeviceKeyNO(final Activity activity) {

        if (TextUtils.isEmpty(CurrentTVBox.getCA(activity))) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.show(activity, "未能识别卡号，请检查机顶盒");
                }
            });
            return false;
        }
        return true;
    }

    public static String generateImageUrl(String url) {
        String retUrl = url;
        if (TextUtils.isEmpty(url)) {
            retUrl = "";
        }
        else
        {
            if (!url.toLowerCase().startsWith("http"))
                retUrl = imageHost + url;
        }
        return retUrl;
    }
}
