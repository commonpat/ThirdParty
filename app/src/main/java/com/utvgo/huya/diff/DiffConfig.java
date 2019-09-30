package com.utvgo.huya.diff;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;


import com.utvgo.huya.beans.BeanMVDetail;
import com.utvgo.huya.beans.BeanSongDetail;
import com.utvgo.huya.utils.HiFiDialogTools;

import static com.utvgo.huya.utils.Appconfig.LocalTest;

/**
 * Created by godfather on 2016/3/16.
 */
public class DiffConfig {

    public static String baseHost;
    public static String imageHost;
    public static String authHost;
    public static String baseUrlWeb;
    public static String playHost;
    public static String activityHost;
    public static String host;
    public static String orderHost;
    public static String statisticsHost;

    public static Boolean UseWebIntroduction = false;
    public static String IntroduceUrl = "";

    public static String TestVideoUrl = "";
    public static String TestAudioUrl = TestVideoUrl;

    public static IEnv GlobalEnv = null;
    public static ITVBox CurrentTVBox = null;
    public static IPurchase CurrentPurchase = null;

    public static Proxyer CurrentProxy = new Proxyer();
    public static VoiceAssistant voiceAssistant = new VoiceAssistant();

    //修改平台即可
    public static Platform CurrentPlatform = Platform.guizhou;

    public static void initEnv(final Boolean isLocalTest)
    {
        LocalTest = isLocalTest;
        switch (CurrentPlatform)
        {
            case guizhou:
            {
                GlobalEnv = new GZTVEnv();
                CurrentTVBox = new GZTVBox();
                CurrentPurchase = new GZPurchase();
                break;
            }
        }

        if(GlobalEnv != null)
        {
            GlobalEnv.initEnv();
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
            case guizhou:
                return "1200";

            default:
                return "";
        }
    }

    public static boolean validateDeviceKeyNO(final Activity activity) {

        if (TextUtils.isEmpty(CurrentTVBox.getCA(activity))) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    HiFiDialogTools.getInstance().showtips(activity, "未能识别卡号，请检查机顶盒", null);
                }
            });
            return false;
        }
        return true;
    }

    public static String getMediaAsset(BeanMVDetail.MvBean mvDetail, BeanSongDetail.SongBean songBean)
    {
        String assetString = "";
        boolean isVOD = false;
        if(mvDetail != null)
        {
            BeanMVDetail.MvBean bean = mvDetail;

            switch (CurrentPlatform)
            {
                     case guizhou:
                        assetString = bean.getHunanVid();
                     break;

                    default:
                    {
                        assetString = bean.getMvPlayUrl();
                        if(TextUtils.isEmpty(assetString))
                        {
                            assetString = bean.getMvPlayUrlHD();
                        }
                        break;
                    }
            }

        }
        else
        {
            if(songBean != null)
            {
                switch (CurrentPlatform)
                {
                    case guizhou:
                        assetString = songBean.getHunanVid();
                        break;

                    default:
                    {
                        assetString = songBean.getSongPlayUrlHq();
                        if(TextUtils.isEmpty(assetString))
                        {
                            assetString = songBean.getSongPlayUrlHq();
                        }
                        break;
                    }
                }
            }
        }
        if(!isVOD)
        {
            if(assetString.indexOf("http") != 0)
            {
                assetString = DiffConfig.playHost + assetString;
            }
        }
        return assetString;
    }

    public static String generateImageUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        if (url.toLowerCase().startsWith("http"))
            return url;
        return imageHost + url;

    }
}
