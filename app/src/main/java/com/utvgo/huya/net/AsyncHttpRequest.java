package com.utvgo.huya.net;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.utvgo.huya.beans.BeanArryPage;
import com.utvgo.huya.beans.BeanBasic;
import com.utvgo.huya.beans.BeanExitPage;
import com.utvgo.huya.beans.BeanMvListByGenreId;
import com.utvgo.huya.beans.BeanMVDetail;
import com.utvgo.huya.beans.BeanSongDetail;
import com.utvgo.huya.beans.BeanStatistics;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.BeanTypeGenre2;
import com.utvgo.huya.beans.BeanUserBindingInfo;
import com.utvgo.huya.beans.BeanWLAblumData;
import com.utvgo.huya.utils.Appconfig;
import com.utvgo.huya.utils.LoadingDialogTools;
import com.utvgo.huya.utils.NetUtils;
import com.utvgo.huya.utils.XLog;

import static com.utvgo.huya.Constants.APPNAME;
import static com.utvgo.huya.Constants.BASE_URL;
import static com.utvgo.huya.Constants.BASE_URL_HOST;


/**
 * Created by godfather on 2016/3/16.
 */
public class AsyncHttpRequest {
    private final String TAG = "AsyncHttpRequest";

    public RequestQueue requestQueue;
    private LoadingDialogTools loadingDialogTools;

    public RequestQueue getRequestQueue(Context context) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);
        return requestQueue;
    }

    private <T> void getJavaBean(String url, final Class<T> className, final Context context, final IVolleyRequestSuccess volleyRequestSuccess, final IVolleyRequestfail iVolleyRequestfail, String
            loadingStr) {
        getJavaBean(url, className, context, null, volleyRequestSuccess, iVolleyRequestfail, loadingStr);
    }

    private <T> void getJavaBean(String url, final Class<T> className, final Context context, final String key, final IVolleyRequestSuccess volleyRequestSuccess, final IVolleyRequestfail
            iVolleyRequestfail) {
        getJavaBean(url, className, context, key, volleyRequestSuccess, iVolleyRequestfail, null);
    }

    //通用
    private <T> void getJavaBean(String url, final Class<T> className, final Context context, final String key, final IVolleyRequestSuccess volleyRequestSuccess, final IVolleyRequestfail
            iVolleyRequestfail, final String loadingStr) {
        XLog.d("请求连接：" + url);
        if (!TextUtils.isEmpty(loadingStr)) {
            loadingDialogTools = new LoadingDialogTools();//loading需要独立出来
        }
        VolleyRequest volleyRequest = new VolleyRequest(context, getRequestQueue(context), new IVolleyRequestSuccess() {
            @Override
            public void onSucceeded(String method, String key, Object object) {
                if (className == null) {
                    if (loadingStr != null) {
                        loadingDialogTools.close();
                    }
                    return;
                }
      //        Gson gson = new Gson();
      //       object = gson.fromJson(object.toString(), className);
               object = JSON.parseObject(object.toString(), className);
                if (volleyRequestSuccess != null) {
                    try {
                        volleyRequestSuccess.onSucceeded(method, key, object);
                        XLog.d("请求成功返回：" + object.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (loadingStr != null) {
                    loadingDialogTools.close();
                }
            }
        }, new IVolleyRequestfail() {
            @Override
            public void onFailed(String method, String key, int errorTipid) {
                if (errorTipid != 0) {
//                    Tools.showTip(context, errorTipid);
                    XLog.d("请求失败：" + context.getResources().getString(errorTipid));
                }
                if (iVolleyRequestfail != null) {
                    try {
                        iVolleyRequestfail.onFailed(method, key, errorTipid);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (loadingStr != null) {
                    loadingDialogTools.close();
                }
            }
        });
        if (loadingStr != null) {
            try {
                loadingDialogTools.showLoadingDialog(context, loadingStr, null);
            } catch (Exception e) {
                XLog.d(TAG, "e:" + e);
                e.printStackTrace();
            }
        }
        volleyRequest.executeGetRequest(key, addParam(context, url));
    }
    //添加接口请求来源类型区分
    private String addParam(Context context, String url) {
        if (url.contains("?") && !url.contains("sourceType")) {
            url = url + "&sourceType=1";
        } else if (!url.contains("sourceType")) {
            url = url + "?sourceType=1";
        }
        Log.e(TAG, "URL after addParam: " + url + "&version=" + getVersion(context));
        return url + "&version=" + getVersion(context);
    }
    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }
    /**
     * 获取外流数据接口
     *
     * @param albumMid
     * @param success
     * @param fail
     */
    public void getWLAblumData(Context context, String albumMid, IVolleyRequestSuccess success, IVolleyRequestfail fail) {
        String url =  BASE_URL+ "/utvgo-tv-mvc/tv/pageCenter/program_content.utvgo?multiSetType=4&channelId=1&pkId=" + albumMid + "&pageNo=1&pageSize=33";
        getJavaBean(url, BeanWLAblumData.class, context, null, success, fail);
    }

    public void getWLAblumDataSingle(Context context, String albumMid, IVolleyRequestSuccess success, IVolleyRequestfail fail) {
        String url = BASE_URL+ "uuMusic/uuMusicDataController/mvAlbum.utvgo?albumMid=" + albumMid + "&pageNo=1&pageSize=33&singleOrderFlg=1";
        getJavaBean(url, BeanWLAblumData.class, context, null, success, fail);
    }
    /**
     * 1.1.	根据风格类型获取MV列表信息接口：
     *
     * @param success
     * @param fail
     * /utvgo-tv-mvc/tv/pageCenter/program_content.utvgo?multiSetType=4&channelId=36&pkId=57652&pageNo=1&pageSize=10000&keyNo=8002004093892878&platform=linux
     */
    public void getMVDetail(Context context, String songMid, IVolleyRequestSuccess success, IVolleyRequestfail fail) {
        String url = BASE_URL + "/utvgo-tv-mvc/tv/pageCenter/program_content.utvgo?multiSetType=4&channelId=36&pkId=57652&pageNo=1" +
                "&pageSize=10000&platform=linuxkeyNo=" + Appconfig.getKeyNo(context) + "&mvMid=" + songMid + "&sourceType=1";
        getJavaBean(url, BeanMVDetail.class, context, null, success, fail);
    }
    /**
     * 删除收藏(歌曲\mv\专辑)接口
     *
     * @param collectionType 0音频，1MV，2专辑
     * @param success
     * @param fail
     */
    public void deleteCollection(Context context, String collectionType, String contentMid, IVolleyRequestSuccess success, IVolleyRequestfail fail) {
        String url = BASE_URL + "/uuMusic/uuMusicDataController/deleteCollection.utvgo?keyNo=" + Appconfig.getKeyNo(context)
                + "&collectionType=" + collectionType + "&contentMid=" + contentMid;
        getJavaBean(url, BeanBasic.class, context, null, success, fail);
    }
    /**
     * 添加收藏(歌曲\mv\专辑)接口
     *
     * @param collectionType 0音频，1MV，2专辑
     * @param success
     * @param fail
     */
    public void addCollection(Context context, String collectionType, String contentMid, IVolleyRequestSuccess success, IVolleyRequestfail fail) {
        String url = BASE_URL + "/uuMusic/uuMusicDataController/addCollection.utvgo?keyNo=" +Appconfig.getKeyNo(context)
                + "&collectionType=" + collectionType + "&contentMid=" + contentMid;
        getJavaBean(url, BeanBasic.class, context, null, success, fail);
    }
    /**
     * 1.1.	根据风格类型获取MV列表信息接口：
     *
     * @param success
     * @param fail
     */
    public void getSongDetail(Context context, String songMid, IVolleyRequestSuccess success, IVolleyRequestfail fail) {
        String url = BASE_URL + "/uuMusic/uuMusicDataController/songDetail.utvgo?keyNo=" + Appconfig.getKeyNo(context) + "&songMid=" + songMid + "&sourceType=1";
        getJavaBean(url, BeanSongDetail.class, context, null, success, fail);
    }
    public void statisticsVideo(Context context, String playPoint,
                                String videoId, final String videoName,
                                String spId, String spName,
                                String programId, String programName,
                                String channelId, String channelName,
                                String multiSetType, String isBase,
                                String orderStatus, String vipCode,
                                String id,
                                String playTime,
                                long totalTime,
                                NetUtils.NetCallBack callback) {
        String url = BASE_URL_HOST + "/utvgo-statistics/video/statistics.utvgo";
        String params = "keyNo=" + Appconfig.getKeyNo(context) + "&branchNo=" + Appconfig.getRegionCode(context) + "&playPoint=" + playPoint + "&videoId=" + videoId +
                "&playTime=" + playTime + "&videoName=" + videoName + "&spId=" + spId + "&id=" + id + "&vipCode=" + vipCode + "&isBase=" + isBase + "&multiSetType=" + multiSetType +
                "&channelId=" + channelId + "&channelName=" + channelName + "&programId=" + programId + "&spName=" + spName + "&programName=" + programName + "&orderStatus=" + orderStatus + "&totalTime=" + totalTime;
        //getJavaBean(url, BeanGuangDongAuth.class, context, null, success, fail);
        NetUtils.postDataNoLoading(context, url, 1, params, BeanStatistics.class, callback);
    }
    //用户页面浏览数据采集接口
    public void statisticsVisit(final Context context, final String pageName, final String pageTitle, final String pageUrl) {
        String orderStatus ="0"; //DiffConfig.CurrentPurchase.isPurchased() ? "0" : "1";
        final String url = BASE_URL_HOST + "/utvgo-statistics/visit/statistics.utvgo";
        String params = "branchNo=" + Appconfig.getRegionCode(context) + "&keyNo=" + Appconfig.getKeyNo(context)
                + "&vipCode=" + "vip_code_50" + "&orderStatus=" + orderStatus + "&vipName=" + APPNAME + "&pageName=" + pageName + "&visitTime=1" +
                "&id=&pageUrl=" + pageUrl + "&boxInfo=&channelId=&channelName=&referrer=&labels=&labelIds=&pageTitle=" + pageTitle;
        NetUtils.postDataNoLoading(context, url, 0, params, null, new NetUtils.NetCallBack() {
            @Override
            public void netBack(int requestTag, Object object) {
                XLog.d("PageStatistics", "PageStatistics: " + pageName);
            }
        });
    }
    /**
     * 获取排行榜列表接口
     *
     * @param topicId
     * @param success
     * @param fail
     */
    public void getTopic(Context context, String topicId, IVolleyRequestSuccess success, IVolleyRequestfail fail) {
        String url =BASE_URL+ "/utvgo-tv-mvc/ui/tv/subject/select.utvgo?id=" + topicId;
        getJavaBean(url, BeanTopic.class, context, null, success, fail);
    }
    /**
     * 根据channelId专题获得视频列表
     * */
    public void getGenreIdLabel(Context context, String keyNo, String channelId, String genreId, IVolleyRequestSuccess success, IVolleyRequestfail fail) {
        String url=BASE_URL+"/utvgo-tv-mvc/tv/pageCenter/vipLabelList.utvgo?channelId="+channelId+"&packageId=29&count=100&keyNo="+Appconfig.getKeyNo(context)+"&platform=linux";
        getJavaBean(url, BeanTypeGenre2.class, context, null, success, fail, null);
    }
    //String url = BASE_URL + "/utvgo-tv-mvc/tv/pageCenter/programList.utvgo?channelId=34&supplierId=39&packageId=29&labelId=790&pageNo=1&pageSize=8&isContainAlbum=0&keyNo=8002004093892878&platform=linux";
    public void getMvListByLabelId(Context context, String keyNo, String labelId, String channelId, String category, String orderType, int pageNo, int pageSize, IVolleyRequestSuccess success, IVolleyRequestfail fail) {
        String url = BASE_URL+ "/utvgo-tv-mvc/tv/pageCenter/programList.utvgo?channelId="+channelId+"&supplierId=39&packageId=29&labelId="+labelId+"&pageNo="+pageNo+"&pageSize=8&isContainAlbum=0&keyNo="+Appconfig.getKeyNo(context)+"&platform=linux";
        getJavaBean(url, BeanMvListByGenreId.class, context, null, success, fail, null);
    }
    /**
     * 用户信息接口
     * */
    public void getUserInfo(Context context,String keyNo,IVolleyRequestSuccess success,IVolleyRequestfail fail){
        String url=BASE_URL+"/huyaTV-order-web/system/QueryUserInfoController/queryUserInfo.utvgo?keyNo="+Appconfig.getKeyNo(context)+"&regionCode="+Appconfig.getRegionCode(context);
        getJavaBean(url,BeanUserBindingInfo.class,context,success,fail,null);
    }
    /**
     * 会员专享接口
     * */
    public  void getArryPage(Context context,String keyNo,IVolleyRequestSuccess success,IVolleyRequestfail fail){
        String url=BASE_URL+"/utvgo-tv-mvc/ui/vip/index/arryPage.utvgo?typeId=73&keyNo="+Appconfig.getKeyNo(context)+"&platform=linux";
        getJavaBean(url, BeanArryPage.class,context,success,fail,null);
    }
    /**
     * 退出挽留页
     * */
    public  void getExitPage(Context context,String keyNo,IVolleyRequestSuccess success,IVolleyRequestfail fail){
        String url=BASE_URL+"/utvgo-tv-mvc/ui/vip/index/page.utvgo?typeId=72&keyNo="+Appconfig.getKeyNo(context)+"&platform=linux";
        getJavaBean(url, BeanExitPage.class,context,success,fail,null);
    }
}
