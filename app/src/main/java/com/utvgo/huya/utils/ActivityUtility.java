package com.utvgo.huya.utils;

import android.app.Activity;
import android.content.Intent;
import com.utvgo.huya.activity.QWebViewActivity;


/**
 * Activity帮助类 这里存放的是activity有关的操作
 *
 * @author eric
 */
public final class ActivityUtility {

//    public static void goMVActivity(Activity activity, String mvType, boolean isSingle) {
//        Intent intent = new Intent(activity, MVListActivity.class);
//        intent.putExtra("mvType", mvType);
//        intent.putExtra("isSingle", isSingle);
//        activity.startActivity(intent);
//    }
//
//    public static void goSingerActivity(Activity activity, BeanSingerType.IndexJsonBean.ListBean dataBean) {
//        Intent intent = new Intent(activity, SingerListActivity.class);
//        intent.putExtra("BeanSingerType", dataBean);
//        activity.startActivity(intent);
//    }
//
//    public static void goSongListActivity(Activity activity, BeanAreaTypeSingerList.DataBean dataBean) {
//        Intent intent = new Intent(activity, CollectPageListActivity.class);
//        intent.putExtra("BeanAreaTypeSinger", dataBean);
//        activity.startActivity(intent);
//    }
//
//    public static void goSongRankActivity(Activity activity, String typeId, String rankId, String typeName) {
//        Intent intent = new Intent(activity, RankDetailActivity.class);
//        intent.putExtra("typeId", typeId);
//        intent.putExtra("rankId", rankId);
//        intent.putExtra("typeName", typeName);
//        activity.startActivity(intent);
//    }

    public static void goActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

//    public static void wxLogin(Activity activity, String url, int requestCode) {
//        Intent intent = new Intent(activity, QQMusicWebViewActivity.class);
//        intent.putExtra(QQMusicWebViewActivity.INTENT_URL, url);
//        intent.putExtra("isWxLogin", true);
//        activity.startActivityForResult(intent, requestCode);
//    }

    public static void goWebActivityActivity(Activity activity, String url) {
        //Intent intent = new Intent(activity, SpecialActivityActivity.class);
        //intent.putExtra(SpecialActivityActivity.INTENT_URL, url);
        //activity.startActivity(intent);
        QWebViewActivity.navigateUrl(activity, url, QWebViewActivity.RequestType.get);
    }

}