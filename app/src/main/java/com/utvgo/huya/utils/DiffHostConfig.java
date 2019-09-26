package com.utvgo.huya.utils;


import android.app.Activity;
import android.text.TextUtils;

import com.utvgo.huya.beans.BeanMVDetail;
import com.utvgo.huya.beans.BeanSongDetail;

/**
 * Created by godfather on 2016/3/16.
 */
public class DiffHostConfig {

    //贵州
//    public static String baseHost = "http://10.69.45.55"; //测试环境的
    public static String baseHost = "http://172.16.146.66"; //正式环境
    public static String activityHost = baseHost;


    public static String host = baseHost + "/utvgo-uu-web/";
    public static String orderHost = baseHost + "/cq-order-web/";
    public static String statisticsHost = baseHost;
    public static String imageHost = baseHost + ":81/cms/uploadFile/image/";
    public static String playHost = "http://10.69.45.49:17553/QQMusic/";

    //不是真正的订购地址，广东是这个字段保持统一,用于埋点统计
    public static String authHost=baseHost;
    public static void setImageHost(String imageProfix) {}
    public static void setPlayHost(String audioProfix) {}
    public static String generateImageUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        if (url.toLowerCase().startsWith("http"))
            return url;
        return imageHost + url;

    }


    public static String getMediaAsset(BeanMVDetail.MvBean mvDetail, BeanSongDetail.SongBean songBean)
    {
        String assetString = "";
        boolean isVOD = false;
        if(mvDetail != null)
        {
            BeanMVDetail.MvBean bean = mvDetail;
             assetString = bean.getMvPlayUrl();
             if(TextUtils.isEmpty(assetString))
             {
               assetString = bean.getMvPlayUrlHD();
              }

        }
        else
        {
            if(songBean != null)
            {

                assetString = songBean.getSongPlayUrlHq();
                if(TextUtils.isEmpty(assetString))
               {
                   assetString = songBean.getSongPlayUrlHq(); }



            }
        }
        if(!isVOD)
        {
            if(assetString.indexOf("http") != 0)
            {
                assetString = playHost + assetString;
            }
        }
        return assetString;
    }
    public static enum SubPortalType
    {
        none, concert, ktv, entertainment, dance;

        public static SubPortalType  typeFromId(final String id)
        {
            SubPortalType type = none;
            if(concert.toId().equalsIgnoreCase(id))
            {
                type = concert;
            }
            else if(ktv.toId().equalsIgnoreCase(id))
            {
                type = ktv;
            }
            else if(entertainment.toId().equalsIgnoreCase(id))
            {
                type = entertainment;
            }
            else if(dance.toId().equalsIgnoreCase(id))
            {
                type = dance;
            }
            return type;
        }

        public String toId()
        {
            switch (this)
            {
                case concert:
                    return "13";

                case ktv:
                    return "14";

                case entertainment:
                    return "15";

                case dance:
                    return "17";

                default:
                    return "";
            }
        }

        public String toTitle()
        {
            switch (this)
            {
                case concert:
                    return "演唱会";

                case ktv:
                    return "KTV";

                case entertainment:
                    return "综艺";

                case dance:
                    return "舞曲";

                default:
                    return "";
            }
        }
    }
}
