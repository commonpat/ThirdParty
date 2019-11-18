package com.utvgo.huya.net;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.NetworkUtils;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanArryPage;
import com.utvgo.huya.beans.BeanCheckCollect;
import com.utvgo.huya.beans.BeanExitPage;
import com.utvgo.huya.beans.UserFavoriteData;
import com.utvgo.huya.beans.UserPlayHistoryData;
import com.utvgo.huya.beans.BeanStatistics;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.LabelInfo;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.beans.ProgramContent;
import com.utvgo.huya.beans.ProgramListData;
import com.utvgo.huya.beans.TypesBean;
import com.utvgo.huya.utils.NetUtils;

import java.util.List;
import java.util.Locale;

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

    public void fetchHomePageNavData(final Context context, final JsonCallback<TypesBean> callback)
    {
        String url = path2ApiUrl("/utvgo-tv-mvc/ui/vip/index/navigation.utvgo?packageId=" + AppConfig.PackageId);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchHomePageData(final Context context, final JsonCallback<BaseResponse<List<OpItem>>> callback)
    {
        final int typeId = 74;
        String url = path2ApiUrl("/utvgo-tv-mvc/ui/vip/index/page.utvgo?typeId=" + typeId);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchTopicData(final Context context,  final String topicId, final JsonCallback<BaseResponse<BeanTopic>> callback)
    {
        String url = path2ApiUrl("/utvgo-tv-mvc/ui/tv/subject/select.utvgo?id=" + topicId);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchLabels(final Context context,  final int channelId, final int packageId,
                                    final JsonCallback<BaseResponse<List<LabelInfo>>> callback)
    {
        String formatString = String.format(Locale.getDefault(), "/utvgo-tv-mvc/tv/pageCenter/vipLabelList.utvgo?channelId=%d&packageId=%d&count=100", channelId, packageId);
        String url = path2ApiUrl(formatString);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchProgramListWithLabel(final Context context,  final int channelId, final int labelId, final int pageNo, final int pageSize,
                            final JsonCallback<BaseResponse<ProgramListData>> callback)
    {
        String formatString = String.format(Locale.getDefault(), "/utvgo-tv-mvc/tv/pageCenter/programList.utvgo?channelId=%d&labelId=%d&pageNo=%d&pageSize=%d",
                channelId, labelId, pageNo, pageSize);
        String url = path2ApiUrl(formatString);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchProgramContent(final Context context,  final int programId, final String multisetType, final int channelID,
                               final JsonCallback<BaseResponse<ProgramContent>> callback)
    {
        String url = path2ApiUrl("/utvgo-tv-mvc/tv/pageCenter/program_content.utvgo?pkId=" + programId + "&channelId=" + channelID +
                "&multiSetType=" + multisetType + "&pageNo=1&pageSize=10000");
        NetworkUtils.get(context, url, callback);
    }

    /**
     * User actions
     */
    public void fetchUserFavorData(final Context context, final int pageNO, final int pageSize, final JsonCallback<BaseResponse<UserFavoriteData>> callback)
    {
        String url= path2ApiUrl("/utvgo-user/collect/collectPageList.utvgo?pageNo=" + pageNO + "&pageSize=" + pageSize);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchUserPlayHistoryData(final Context context, final int pageNO, final int pageSize, final JsonCallback<BaseResponse<UserPlayHistoryData>> callback)
    {
        String url= path2ApiUrl("/utvgo-user/video/playhistoryPageList.utvgo?pageNo=" + pageNO + "&pageSize=" + pageSize);
        NetworkUtils.get(context, url, callback);
    }

    public void fetchUserCenterOpData(final Context context, final JsonCallback<BaseResponse<List<List<OpItem>>>> callback)
    {
        String url = path2ApiUrl("/utvgo-tv-mvc/ui/vip/index/arryPage.utvgo?typeId=76");
        NetworkUtils.get(context, url, callback);
    }

    public void userCheckFavorStatus(final Context context, final String programId, final JsonCallback<BeanCheckCollect> callback)  {
        String url = path2ApiUrl("/utvgo-user/collect/checkcollect.utvgo?programId=" + programId);
        NetworkUtils.get(context, url, callback);
    }

    public void userAddFavor(final Context context, String collectionType, int assetId, int channelId, final JsonCallback<BaseResponse> callback) {
        String url = path2ApiUrl("/utvgo-user/collect/savecollect.utvgo?programId=" + assetId + "&channelId=" + channelId+"&keyNo="+DiffConfig.getCA(context));
        String params="keyNo="+DiffConfig.getCA(context)+"&programId=" + assetId + "&channelId=" + channelId;
        NetworkUtils.post(context,url,params,callback);
        //NetUtils.postData(context,url,1,params,BaseResponse.class,callback);
        //NetworkUtils.get(context, url, callback);
    }

    public void userDeleteFavor(final Context context, String collectionType, String assetIdString, final JsonCallback<BaseResponse> callback) {
        String url = path2ApiUrl("/utvgo-user/collect/delcollect.utvgo?idStr=" + assetIdString);
        NetworkUtils.post(context, url,null, callback);
    }

    public void userPlayRecord(final Context context, String playPoint,
                                    String videoId, final String videoName,
                                    String programId, String programName,
                                    String channelId,
                                    String multiSetType,
                                    long totalTime) {
        String url = path2ApiUrl("/utvgo-user/video/playhistory.utvgo?playPoint=" + playPoint + "&videoId=" + videoId +
                "&videoName=" + videoName+ "&multiSetType=" + multiSetType +
                "&channelId=" + channelId + "&programId=" + programId + "&programName=" + programName  + "&totalTime=" + totalTime
                +"&keyNo=" + DiffConfig.getCA(context) + "&regionCode=" + DiffConfig.getRegionCode(context) + "&vipCode=" + AppConfig.VipCode);
//        NetworkUtils.get(context, url, new JsonCallback<BaseResponse>() {
//            @Override
//            public void onSuccess(Response<BaseResponse> response) {
//                Log.d("userPlayRecord", "onSuccess: "+response.toString());
//            }
//        });
        String params = "playPoint=" + playPoint + "&videoId=" + videoId +
        "&videoName=" + videoName+ "&multiSetType=" + multiSetType +
                "&channelId=" + channelId + "&programId=" + programId + "&programName=" + programName  + "&totalTime=" + totalTime
                +"&keyNo=" + DiffConfig.getCA(context) + "&regionCode=" + DiffConfig.getRegionCode(context) + "&vipCode=" + AppConfig.VipCode;
        NetworkUtils.post(context, url, params, new JsonCallback<BaseResponse>() {
            @Override
            public void onSuccess(Response<BaseResponse> response) {
                Log.d("userPlayRecord", "onSuccess: "+response.toString());
            }

            @Override
            public void onError(Response<BaseResponse> response) {
                Log.d("userPlayRecord", "onSuccess: "+response.toString());
            }

            @Override
            public void onFinish() {
                Log.d("userPlayRecord", "onSuccess: hhhhhhhhhhh");
            }
        });
    }



    //用户页面浏览数据采集接口
    public void statisticsVisit(final Context context, final String pageName, final String pageTitle, final String pageUrl) {
        String orderStatus = DiffConfig.CurrentPurchase.isPurchased() ? "0" : "1";
        String url = DiffConfig.statisticsHost + "/utvgo-statistics/visit/statistics.utvgo";
        url += "?branchNo=" + DiffConfig.getRegionCode(context) + "&keyNo=" + DiffConfig.getCA(context)
                + "&vipCode=" + AppConfig.VipCode + "&orderStatus=" + orderStatus + "&vipName=" + AppConfig.AppName + "&pageName=" + pageName + "&visitTime=1" +
                "&id=&pageUrl=" + pageUrl + "&boxInfo=&channelId=&channelName=&referrer=&labels=&labelIds=&pageTitle=" + pageTitle;

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

    public  void fetchExitPage(Context context,String typeId,final JsonCallback<BeanExitPage> callback){
        String url=DiffConfig.baseHost+"/utvgo-tv-mvc/ui/vip/index/page.utvgo?typeId=72";
        NetworkUtils.get(context,url,callback);
    }
}
