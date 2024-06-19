package com.utvgo.huya.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.TOPWAYBox;
import com.utvgo.huya.BuildConfig;


public class TWSyncHelper {
    private static final String TAG = TWSyncHelper.class.getSimpleName();

    public static String DEFAULT_CP_ID = "sp_hy";

    private static String playListUri = "content://com.starcor.medialist/" + DEFAULT_CP_ID + "/play";
    private static String collectListUri = "content://com.starcor.medialist/" + DEFAULT_CP_ID + "/collection";


    private static String playListUri_mg = "content://com.starcor.medialist/" + DEFAULT_CP_ID + "/play";
    private static String collectListUri_mg = "content://com.starcor.medialist/" + DEFAULT_CP_ID + "/collection";


    public static String DKV_TYPE_FAVORITES = "@favorites";    //收藏
    public static String DKV_TYPE_HISTORY = "@history";    //历史


    public static String queryAll(Context context, String type) {
        StringBuilder values = new StringBuilder();
        Uri uri = null;
        if (DKV_TYPE_HISTORY.equals(type)) {
            uri = Uri.parse(playListUri);
        } else if (DKV_TYPE_FAVORITES.equals(type)) {
            uri = Uri.parse(collectListUri);
        } else {
            throw new IllegalArgumentException("没有找到对应的type");
        }
        ContentResolver resolver = context.getContentResolver();
        try {
            /* 列数据格式
            String[] mediaList = new String[]{"type", "id", "user_id", "create_time", "name", "cp_video_id", "last_position", "duration", "url", "cp_id", "cp_data","cp_corner_url","cp_package_name","cp_download_url"};
             */
            Cursor cursor = resolver.query(uri, null, null, null, null);
            int columnCount = cursor.getColumnCount();
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < columnCount; i++) {
                String name = cursor.getColumnName(i);
                names.append(name).append("\r\n");
            }
            Log.i(TAG, names.toString());

            int index = 0;
            while (cursor.moveToNext()) {
                values.append("----------------------第"+index+"条数据-------------------------").append("\r\n");
                for (int i = 0; i < columnCount; i++) {
                    String value = cursor.getString(i);
                    values.append(value).append("\r\n");
                }
                values.append("\r\n").append("\r\n");
                index++;
            }
            Log.i(TAG, values.toString());

        } catch (Exception e) {
            Log.e(TAG, "queryAll: uri is illegal " + uri);
        }
        return values.toString();
    }


    public static void insertData(Context context, String type, JSONObject object) {
        Log.d(TAG, "insertData:"+type);
        if(DiffConfig.CurrentTVBox instanceof TOPWAYBox){
            Uri uri = null;
            ContentValues cv = null;
            if (DKV_TYPE_HISTORY.equals(type)) {
                uri = Uri.parse(playListUri_mg);
                cv = buildInsertPlayData(object);
            } else if (DKV_TYPE_FAVORITES.equals(type)) {
                uri = Uri.parse(collectListUri_mg);
                cv = buildInsertCollectData(object);
            } else {
                throw new IllegalArgumentException("没有找到对应的type");
            }
            ContentResolver resolver = context.getContentResolver();
            try {
                resolver.insert(uri, cv);
            } catch (Exception e) {
                Log.e(TAG, "insertData: uri is illegal " + uri);
            }
       }
    }

    private static ContentValues buildInsertCollectData(JSONObject object) {
        ContentValues insertValue = new ContentValues();
        String postUrl = "http://116.77.70.236:81/nn_img/prev/KsImg/20180523160921079-new.jpg_326x482.jpg";
//        insertValue.put("last_position",breakPoint.getAttributeValue("offset"));
//        insertValue.put("duration",breakPoint.getAttributeValue("len"));
        insertValue.put("user_id", object.getString("user_id"));
        insertValue.put("create_time", System.currentTimeMillis());
        insertValue.put("name", object.getString("name"));
        insertValue.put("cp_video_id", object.getString("cp_video_id"));
        insertValue.put("cp_video_index", "0");
        insertValue.put("url", object.getString("url"));
        insertValue.put("cp_id", DEFAULT_CP_ID);
        insertValue.put("cp_corner_url", object.getString("url"));
        insertValue.put("cp_data", "utvgo");
        insertValue.put("cp_package_name", BuildConfig.APPLICATION_ID);
        return insertValue;
    }

    private static ContentValues buildInsertPlayData(JSONObject object) {
        ContentValues insertValue = new ContentValues();
        String postUrl = "http://116.77.70.236:81/nn_img/prev/KsImg/20160307135636.jpg";
        insertValue.put("last_position", object.getLong("last_position"));
        insertValue.put("duration", object.getLong("duration"));
        insertValue.put("user_id", object.getString("user_id"));
        insertValue.put("create_time", System.currentTimeMillis());
        insertValue.put("name", object.getString("name"));
        insertValue.put("cp_video_id",object.getString("cp_video_id"));
        insertValue.put("cp_video_index", "0");
        insertValue.put("url", object.getString("url"));
        insertValue.put("cp_id", DEFAULT_CP_ID);
        insertValue.put("cp_data", object.getLong("last_position")+"");
        insertValue.put("cp_corner_url", object.getString("url"));
        insertValue.put("cp_package_name", BuildConfig.APPLICATION_ID);
        return insertValue;
    }

    public static void deleteDataByType(Context context, String type, String videoId) {
        Log.d(TAG, "deleteDataByType: "+videoId);
        if(DiffConfig.CurrentTVBox instanceof TOPWAYBox) {
            Uri uri;
            if (type.equals(DKV_TYPE_HISTORY)) {
                uri = Uri.parse(playListUri_mg + "/0");
            } else {
                uri = Uri.parse(collectListUri_mg + "/0");
            }
            ContentResolver resolver = context.getContentResolver();
            try {
                resolver.delete(uri, videoId, new String[]{});
            } catch (Exception e) {
                Log.e(TAG, "deleteData: uri is illegal " + uri);
            }
        }
    }

    public static void deletePlayData(Context context, String type) {
        Uri uri;
        if (type.equals(DKV_TYPE_HISTORY)) {
            uri = Uri.parse(playListUri + "/0");
        } else {
            uri = Uri.parse(collectListUri + "/0");
        }
        ContentResolver resolver = context.getContentResolver();
        try {
            resolver.delete(uri, "allValues", new String[]{});
        } catch (Exception e) {
            Log.e(TAG, "deleteData: uri is illegal " + uri);
        }
    }

    public static void twSavePlayHistoryItem(final Context context, final long lastPosition, final int duration, final String userId, final String assetName, final String cpVideoId, final String posterUrl) {
        if (DiffConfig.CurrentTVBox instanceof TOPWAYBox) {
            final JSONObject insertValue = new JSONObject();
            insertValue.put("last_position", lastPosition);
            insertValue.put("duration", duration);
            insertValue.put("user_id", userId);
            insertValue.put("name", assetName);
            insertValue.put("cp_video_id", cpVideoId);
            insertValue.put("url", posterUrl);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TWSyncHelper.insertData(context, DKV_TYPE_HISTORY, insertValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
}
