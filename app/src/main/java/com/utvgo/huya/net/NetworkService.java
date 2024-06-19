package com.utvgo.huya.net;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.bean.BeanAuthManagerData;
import com.utvgo.handsome.bean.BeanGetPlayPoint;
import com.utvgo.handsome.bean.BeanMvPlayPoint;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.TOPWAYPurchase;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.NetworkUtils;
import com.utvgo.handsome.utils.URLBuilder;
import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanArryPage;
import com.utvgo.huya.beans.BeanCheckCollect;
import com.utvgo.huya.beans.BeanExitPage;
import com.utvgo.huya.beans.BeanUpgrade;
import com.utvgo.huya.beans.UserFavoriteData;
import com.utvgo.huya.beans.UserPlayHistoryData;
import com.utvgo.huya.beans.BeanStatistics;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.LabelInfo;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.beans.ProgramContent;
import com.utvgo.huya.beans.ProgramListData;
import com.utvgo.huya.beans.TypesBean;
import com.utvgo.huya.utils.AppUtils;
import com.utvgo.huya.utils.NetUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NetworkService {
    private static class SingletonInner {
        private static NetworkService singletonStaticInner = new NetworkService();
    }

    public static NetworkService defaultService() {
        return SingletonInner.singletonStaticInner;
    }

    public String path2ApiUrl(String path)
    {
        return DiffConfig.baseHost + path;
    }
    public String path2HuyaUrl(String path){
        return DiffConfig.dataHost+path;
    }

    public void fetchHomePageNavData(final Context context,String packageId,final JsonCallback<TypesBean> callback)
    {
        String url = path2HuyaUrl("/ui/vip/index/navigation.utvgo?packageId="+packageId);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchHomePageData(final Context context,int typeId, final JsonCallback<BaseResponse<List<OpItem>>> callback)
    {
        //final int typeId = 74;
        String url = path2HuyaUrl("/ui/vip/index/page.utvgo?typeId=" + typeId);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchTopicData(final Context context,  final String topicId, final JsonCallback<BaseResponse<BeanTopic>> callback)
    {
        String url = path2HuyaUrl("/ui/tv/subject/select.utvgo?id=" + topicId);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchLabels(final Context context,  final int channelId, final int packageId,
                                    final JsonCallback<BaseResponse<List<LabelInfo>>> callback)
    {
        String formatString = String.format(Locale.getDefault(), "/tv/pageCenter/vipLabelList.utvgo?channelId=%d&packageId=%d&count=100", channelId, packageId);
        String url = path2HuyaUrl(formatString);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchProgramListWithLabel(final Context context,  final int channelId, final int labelId, final int pageNo, final int pageSize,
                            final JsonCallback<BaseResponse<ProgramListData>> callback)
    {
        String formatString = String.format(Locale.getDefault(), "/tv/pageCenter/programList.utvgo?channelId=%d&labelId=%d&pageNo=%d&pageSize=%d",
                channelId, labelId, pageNo, pageSize);
        String url = path2HuyaUrl(formatString);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchProgramContent(final Context context,  final int programId, final String multisetType, final int channelID,String vodId,
                               final JsonCallback<BaseResponse<ProgramContent>> callback)
    {
        String url = path2HuyaUrl("/tv/pageCenter/program_content.utvgo?pkId=" + programId + "&channelId=" + channelID +"&vodid="+ vodId +
                "&multiSetType=" + multisetType + "&pageNo=1&pageSize=10000");
        NetworkUtils.get(context, url, callback);
    }

    /**
     * User actions
     */
    public void fetchUserFavorData(final Context context, final int pageNO, final int pageSize, final JsonCallback<BaseResponse<UserFavoriteData>> callback)
    {
        String url= path2ApiUrl("/huya-user/collect/collectPageList.utvgo?pageNo=" + pageNO + "&pageSize=" + pageSize);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchUserPlayHistoryData(final Context context, final int pageNO, final int pageSize, final JsonCallback<BaseResponse<UserPlayHistoryData>> callback)
    {
        String url= path2ApiUrl("/huya-user/video/playhistoryPageList.utvgo?pageNo=" + pageNO + "&pageSize=" + pageSize);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchUserCenterOpData(final Context context, final JsonCallback<BaseResponse<List<List<OpItem>>>> callback)
    {
        String url = path2HuyaUrl("/ui/vip/index/arryPage.utvgo?typeId=76");
        NetworkUtils.get(context, url, callback);
    }

    public void userCheckFavorStatus(final Context context, final String programId, final JsonCallback<BeanCheckCollect> callback)  {
        String url = path2ApiUrl("/huya-user/collect/checkcollect.utvgo?programId=" + programId);
        NetworkUtils.get(context, url, callback);
    }

    public void userAddFavor(final Context context,
                             String collectionType,
                             int pkId,
                             int videoId,
                             int channelId,
                             String videoName,
                             String programName,
                             String multiSetType,
                             final JsonCallback<BaseResponse> callback) {
        String url = path2ApiUrl("/huya-user/collect/savecollect.utvgo?keyNo="+DiffConfig.getCA(context))+"&programId=" + pkId + "&channelId=" + channelId +"&videoName="+ URLBuilder.encodeURIComponent(videoName)+"&videoId=" + videoId
                +"&programName=" + URLBuilder.encodeURIComponent(programName)+"&multiSetType="+multiSetType;;
        String params="keyNo="+DiffConfig.getCA(context)+"&programId=" + pkId + "&channelId=" + channelId +"&videoName="+ URLBuilder.encodeURIComponent(videoName)+"&videoId=" + videoId
                +"&programName=" + URLBuilder.encodeURIComponent(programName)+"&multiSetType="+multiSetType;
        NetworkUtils.post(context,url,params,callback);
        //NetUtils.postData(context,url,1,params,BaseResponse.class,callback);
        //NetworkUtils.get(context, url, callback);
    }

    public void userDeleteFavor(final Context context, String collectionType, String assetIdString, final JsonCallback<BaseResponse> callback) {
        String url = path2ApiUrl("/huya-user/collect/delcollect.utvgo?idStr=" + assetIdString);
        String params="keyNo="+DiffConfig.getCA(context) + "idStr=" + assetIdString;
        NetworkUtils.post(context, url,params, callback);
    }

    public void userPlayRecord(final Context context, String playPoint,
                                    String videoId, final String videoName,
                                    String programId, String programName,
                                    String channelId,
                                    String multiSetType,
                                    long totalTime,final JsonCallback<BaseResponse> callback) {
        String url = path2ApiUrl("/huya-user/video/playhistory.utvgo?playPoint=" + playPoint + "&videoId=" + videoId +
                "&videoName=" + URLBuilder.encodeURIComponent(videoName)+ "&multiSetType=" + multiSetType +
                "&channelId=" + channelId + "&programId=" + programId + "&programName=" + URLBuilder.encodeURIComponent(programName)  + "&totalTime=" + totalTime
                +"&keyNo=" + DiffConfig.getCA(context) + "&regionCode=" + DiffConfig.getRegionCode(context) + "&vipCode=" + AppConfig.VipCode);
//        NetworkUtils.get(context, url, new JsonCallback<BaseResponse>() {
//            @Override
//            public void onSuccess(Response<BaseResponse> response) {
//                Log.d("userPlayRecord", "onSuccess: "+response.toString());
//            }
//        });
        String params = "playPoint=" + playPoint + "&videoId=" + videoId +
        "&videoName=" + URLBuilder.encodeURIComponent(videoName)+ "&multiSetType=" + multiSetType +
                "&channelId=" + channelId + "&programId=" + programId + "&programName=" + URLBuilder.encodeURIComponent(programName)  + "&totalTime=" + totalTime
                +"&keyNo=" + DiffConfig.getCA(context) + "&regionCode=" + DiffConfig.getRegionCode(context) + "&vipCode=" + AppConfig.VipCode;
        NetworkUtils.post(context, url, params,callback);
    }



    //用户页面浏览数据采集接口
    public void statisticsVisit(final Context context, final String pageName, final String pageTitle, final String pageUrl) {
        String orderStatus = DiffConfig.CurrentPurchase.isPurchased() ? "0" : "1";
        String url = DiffConfig.statisticsHost + "/utvgo-statistics/visit/statistics.utvgo";
        url += "?branchNo=" + DiffConfig.getRegionCode(context) + "&keyNo=" + DiffConfig.getCA(context)
                + "&vipCode=" + AppConfig.VipCode + "&orderStatus=" + orderStatus + "&vipName=" + AppConfig.AppName + "&pageName=" + pageName +"version_"+AppUtils.getLocalVersionName(context)+ "&visitTime=1" +
                "&id=&pageUrl=" + pageUrl + "&boxInfo="+DiffConfig.deviceId+"&channelId=&channelName=&referrer=&labels=&labelIds=&pageTitle=" + pageTitle;

        NetworkUtils.get(context, url, new JsonCallback<BaseResponse>() {
            @Override
            public void onSuccess(Response<BaseResponse> response) {

            }
        });
    }

    public void statisticsVideo(final Context context, String playPoint,
                                String videoId, final String videoName,
                                String spId, String spName,
                                String programId, String programName,
                                String channelId, String channelName,
                                String multiSetType, String isBase,
                                String orderStatus, String vipCode,
                                String id,
                                String playTime,
                                long totalTime,
                                final JsonCallback<BaseResponse<BeanStatistics>> callback) {
        String url = DiffConfig.statisticsHost + "/utvgo-statistics/video/statistics.utvgo?";
        url += "keyNo=" + DiffConfig.getCA(context) + "&branchNo=" + DiffConfig.getRegionCode(context) + "&playPoint=" + playPoint + "&videoId=" + videoId +
                "&playTime=" + playTime + "&videoName=" + videoName + "&spId=" + spId + "&id=" + id + "&vipCode=" + vipCode + "&isBase=" + isBase + "&multiSetType=" + multiSetType +
                "&channelId=" + channelId + "&channelName=" + channelName + "&programId=" + programId + "&spName=" + spName + "&programName=" + programName + "&orderStatus=" + orderStatus + "&totalTime=" + totalTime;
        NetworkUtils.get(context, url, callback);
    }
    /**
     * 退出挽留页数据接口
     * */

    public  void fetchExitPage(Context context,String typeId,final JsonCallback<BeanExitPage> callback){
        String url=path2HuyaUrl("/ui/vip/index/page.utvgo?typeId=75");
        NetworkUtils.get(context,url,callback);
    }
    public  void fetchExitNewPage(Context context,String typeId,final JsonCallback<BeanExitPage> callback){
        String url= path2HuyaUrl("/vip/index/page.utvgo?typeId=78");
        NetworkUtils.get(context,url,callback);
    }
    /**
     * 第一次鉴权上报接口，给订购开关判断条件
     * @param state 0已授权 1未授权
     * */
    public void syncUserAuthorization (Context context,String state,String productId){
        String url = "";
        if(BuildConfig.DEBUG){
             url = "http://172.16.146.56:8380/cq-order-web/authorizationController/syncUserAuthorization.utvgo?keyNo="+DiffConfig.getCA(context)+"&state="+state+"&cmboId="+productId+"&contentMid=";

        }else {
            url = DiffConfig.baseHost + "/cq-order-web/authorizationController/syncUserAuthorization.utvgo?keyNo="+DiffConfig.getCA(context)+"&state="+state+"&cmboId="+productId+"&contentMid=";
        }
        NetworkUtils.get(context, url, new JsonCallback<BeanExitPage>() {
            @Override
            public void onSuccess(Response<BeanExitPage> response) {
                BeanExitPage beanExitPage = (BeanExitPage) response.body();
                beanExitPage.getCode();
            }
        });
    }
    public void getVersionInfo(Context context,String s,final JsonCallback<BeanUpgrade> callback){
        String url;
//        if (BuildConfig.DEBUG){
//            url = "http://172.16.146.56:9091/utvgo-tv-mvc/tv/pageCenter/getVersionInfo.utvgo?projectType=com.utvgo.huya";
//        }else {
            url = DiffConfig.baseHost+"/utvgo-tv-mvc/tv/pageCenter/getVersionInfo.utvgo?projectType=com.utvgo.huya";
       // }
        NetworkUtils.get(context,url,callback);
    }

    public void getKeyNoLimitType(Context context,final JsonCallback<BeanAuthManagerData> callback){
        String url = "";
//        if(BuildConfig.DEBUG){
//             url = "http://172.16.146.56:8380/cq-order-web/authorizationController/getKeyNoLimitType.utvgo?keyNo="+keyNo;
//        }else {
        url = DiffConfig.baseHost + "/cq-order-web/authorizationController/getKeyNoLimitType.utvgo?keyNo=" + DiffConfig.getCA(context);
//        }
        NetworkUtils.get(context,url,callback);
    }
    public  void  callBackFunc(Context context,int state,String msg){
        String url = "";
        url = DiffConfig.baseHost + "/cq-order-web/chongqing/cqUserController/callbackSaveAuthorize.utvgo";
        Map<String,String> paramMap=new HashMap<String, String>();
        paramMap.put("msg",msg);
        paramMap.put("code",String.valueOf(state));
        paramMap.put("keyNo",DiffConfig.getCA(context));
        paramMap.put("productId", TOPWAYPurchase.productId);
        paramMap.put("SvcCodes","");
        NetUtils.postJsonData(context, url, 2, paramMap, BaseResponse.class, "", new NetUtils.NetCallBack() {
            @Override
            public void netBack(int requestTag, Object object) {
                BaseResponse baseResponse = (BaseResponse) object;
                Log.d("TAG", "netBack: "+baseResponse.getMessage());
            }
        });

    }
    /**
     * 更新上报时长，做续播
     * */
    public void  updateUserPlayPoint(Context context,String contentMid,String playPoint,int programId,int videoId,JsonCallback<com.utvgo.handsome.bean.BaseResponse> callback){
        String url = DiffConfig.baseHost + "/huya-user/video/updateUserPlayPoint.utvgo?"+"programId="+programId+"&videoId="+videoId+"&playPoint="+playPoint;
        NetworkUtils.get(context,url,callback);
    }
    /**
     * 更新上报时长，做续播
     * */
    public void  getUserPlayPoint(Context context,String contentMid,int programId,int videoId,JsonCallback<BeanGetPlayPoint> callback){
        String url = DiffConfig.baseHost +"/huya-user/video/getUserPlayhistory.utvgo?"+"programId="+programId+"&videoId="+videoId;
        NetworkUtils.get(context,url,callback);
    }

    public void  newCallBackFunc(Context context,int code,String msg){
        String url = DiffConfig.baseHost +"/cq-order-web/chongqing/cqUserController/callbackSaveAuthorize.utvgo?code="+code+"&productId=huya&sourceType=1";
        NetworkUtils.get(context, url, new JsonCallback<com.utvgo.handsome.bean.BaseResponse>() {

            @Override
            public void onSuccess(Response<com.utvgo.handsome.bean.BaseResponse> response) {

            }
        });
    }

}
