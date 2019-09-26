package com.utvgo.huya.net;

import com.utvgo.huya.beans.BeanWLAblumData;
import com.utvgo.huya.beans.PageBean;
import com.utvgo.huya.beans.TypesBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface UTVGOApi {
    @GET("/utvgo-tv-mvc/ui/vip/index/navigation.utvgo")
    Observable<TypesBean> getTypes(@Query("packageId") String packageId, @Query("keyNo") String keyNo, @Query("platform") String platform);
    @GET("/utvgo-tv-mvc/ui/vip/index/page.utvgo")
    Observable<PageBean> getPage(@Query("typeId") int typeId, @Query("keyNo") String keyNo, @Query("platform") String platform);
    @GET("uuMusic/uuMusicDataController/mvAlbum.utvgo?pageNo=1&pageSize=33&singleOrderFlg=1")
    Observable<BeanWLAblumData> getWLAblumDataSingle(@Query("albumMid") String albumMid, @Query("platform") String platform);
    @GET("uuMusic/uuMusicDataController/mvAlbum.utvgo?pageNo=1&pageSize=33")
    Observable<BeanWLAblumData> getWLAblumData(@Query("albumMid") String albumMid, @Query("platform") String platform);
}