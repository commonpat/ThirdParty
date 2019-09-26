package com.utvgo.huya.net;

import android.app.Application;
import android.content.Context;

import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.beans.BeanWLAblumData;
import com.utvgo.huya.beans.PageBean;
import com.utvgo.huya.beans.TypesBean;
import com.utvgo.huya.utils.CommonUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

import static com.utvgo.huya.Constants.PACKAGE_ID;
import static com.utvgo.huya.Constants.PLATFORM;

public class UTVGOServer {
    private final UTVGOApi server;

    public UTVGOServer(String url){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
//                        .header("version", BuildConfig.VERSION_NAME)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        client.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                //通过Gson 解析实体
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client.build())
                .build();
        server = retrofit.create(UTVGOApi.class);
    }
    public void getTypes(String  keyNo, Subscriber<TypesBean> subscriber){
        CommonUtil.o2s(server.getTypes(PACKAGE_ID, keyNo,PLATFORM),subscriber);
    }
    public void getPage(int typeId,String  keyNo, Subscriber<PageBean> subscriber){
        CommonUtil.o2s(server.getPage(typeId, keyNo,PLATFORM),subscriber);
    }
    public void getWLAblumDataSingle(String albumMid, Subscriber<BeanWLAblumData> subscriber){
        CommonUtil.o2s(server.getWLAblumDataSingle(albumMid,PLATFORM),subscriber);
    }
    public void getWLAblumData(String albumMid, Subscriber<BeanWLAblumData> subscriber){
        CommonUtil.o2s(server.getWLAblumData(albumMid,PLATFORM),subscriber);
    }


}
