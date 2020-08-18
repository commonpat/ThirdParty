package com.utvgo.huya.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.utvgo.handsome.utils.XLog;

import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by oo on 2017/9/20.
 */

public class NetUtils {

    private static String TAG = "NetUtils";

    public static void getImage(Context context, final ImageView imageView, String path) {
        OkGo.<Bitmap>get(path).cacheMode(CacheMode.NO_CACHE).tag(context).execute(new BitmapCallback() {
            @Override
            public void onSuccess(Response<Bitmap> response) {
                imageView.setImageBitmap(response.body());
            }
        });
    }

    public static void getData(Context context, final String path, Map<String, String> params, final Class className, final NetCallBack callBack) {
         getData(context, path, 0, params, className, "", callBack);
    }

    public static void getData(Context context, final String path, final int reqTag, Map<String, String> params, final Class className, final NetCallBack callBack) {
        getData(context, path, reqTag, params, className, "", callBack);
    }

    public static void getData(final Context context, final String path, final int reqTag, Map<String, String> params, final Class className, String loadingStr, final NetCallBack callBack) {
        getData(context, path, reqTag, params, null, className, loadingStr, callBack);
    }

    public static void getData(final Context context, final String path, final int reqTag, Map<String, String> params, String JSESSIONID,
                               final Class className, String loadingStr, final NetCallBack callBack) {
        final LoadingDialogTools loadingDialogTools = new LoadingDialogTools();//loading需要独立出来
        if (!TextUtils.isEmpty(loadingStr)) {
            try {
                if (loadingDialogTools != null && loadingDialogTools.isShowing()) {
                    loadingDialogTools.dismiss();
                }
                loadingDialogTools.showLoadingDialog(context, loadingStr, null);
            } catch (Exception e) {
                XLog.d(TAG, "e:" + e);
                e.printStackTrace();
            }
        }
        GetRequest request = OkGo.<String>get(path).cacheMode(CacheMode.NO_CACHE).tag(context).params(params);
        if (JSESSIONID != null) {
            request.headers("JSESSIONID", JSESSIONID);
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
                Log.w(TAG, "onSuccess: " + response.body());
                if (className == null) {
                    if (callBack != null) {
                        callBack.netBack(reqTag, response.body());
                    }
                } else {
                    Object object = JSON.parseObject(response.body(), className);
                    if (object == null) {
                        Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
                    } else if (callBack != null) {
                        callBack.netBack(reqTag, object);
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
                Log.d(TAG, "onError: " + response.body());
            }
        });
    }

    public static void postData(Context context, final String path, final int reqTag, String params, final Class className, final NetCallBack callBack) {
        postData(context, path, reqTag, params, className, "加载中...", callBack);
    }

    public static void postDataNoLoading(Context context, final String path, final int reqTag, String params, final Class className, final NetCallBack callBack) {
        postData(context, path, reqTag, params, className, null, callBack);
    }

    public static void postWithImgData(final Context context, final String path, final int reqTag, Map<String, String> params, File file, String fileName,
                                       final Class className, final NetCallBack callBack) {

        final LoadingDialogTools loadingDialogTools = new LoadingDialogTools();//loading需要独立出来
        try {
            if (loadingDialogTools != null && loadingDialogTools.isShowing()) {
                loadingDialogTools.dismiss();
            }
            loadingDialogTools.showLoadingDialog(context, "加载中", null);
        } catch (Exception e) {
            XLog.d(TAG, "e:" + e);
            e.printStackTrace();
        }
        OkGo.<String>post(path).cacheMode(CacheMode.NO_CACHE).tag(context).params(params).params(fileName, file).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
                Log.w(TAG, "onSuccess: " + response.body());
                if (className == null) {
                    if (callBack != null) {
                        callBack.netBack(reqTag, response.body());
                    }
                } else {
                    Object object = JSON.parseObject(response.body(), className);
                    if (object == null) {
                        Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
                    } else if (callBack != null) {
                        callBack.netBack(reqTag, object);
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
            }
        });
    }

    public static void postWithImgListData(final Context context, final String path, final int reqTag, Map<String, String> params, List<String> pathList,
                                           final Class className, final NetCallBack callBack) {

        final LoadingDialogTools loadingDialogTools = new LoadingDialogTools();//loading需要独立出来
        try {
            if (loadingDialogTools != null && loadingDialogTools.isShowing()) {
                loadingDialogTools.dismiss();
            }
            loadingDialogTools.showLoadingDialog(context, "加载中", null);
        } catch (Exception e) {
            XLog.d(TAG, "e:" + e);
            e.printStackTrace();
        }
        PostRequest<String> request = OkGo.<String>post(path).cacheMode(CacheMode.NO_CACHE).tag(context).params(params);
        for (int i = 0; i < pathList.size(); i++) {
            request.params("img" + (i + 1), new File(pathList.get(i)));
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
                Log.w(TAG, "onSuccess: " + response.body());
                if (className == null) {
                    if (callBack != null) {
                        callBack.netBack(reqTag, response.body());
                    }
                } else {
                    Object object = JSON.parseObject(response.body(), className);
                    if (object == null) {
                        Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
                    } else if (callBack != null) {
                        callBack.netBack(reqTag, object);
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
            }
        });
    }

    public static void postData(final Context context, final String path, final int reqTag, String params, final Class className, String loadingStr, final NetCallBack callBack) {
        final LoadingDialogTools loadingDialogTools = new LoadingDialogTools();//loading需要独立出来
        if (!TextUtils.isEmpty(loadingStr)) {
            try {
                if (loadingDialogTools != null && loadingDialogTools.isShowing()) {
                    loadingDialogTools.dismiss();
                }
                loadingDialogTools.showLoadingDialog(context, loadingStr, null);
            } catch (Exception e) {
                XLog.d(TAG, "e:" + e);
                e.printStackTrace();
            }
        }
        OkGo.<String>post(path).cacheMode(CacheMode.NO_CACHE).tag(context).upString(params, MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8")).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
                Log.w(TAG, "onSuccess: " + response.body());
                if (className == null) {
                    if (callBack != null) {
                        callBack.netBack(reqTag, response.body());
                    }
                } else {
                    Object object = null;
                    try{
                        object = JSON.parseObject(response.body(), className);
                    }catch (Exception e){
                        XLog.e("postData", path);
                        e.printStackTrace();
                    }
                    if (object == null) {
                        Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
                    } else if (callBack != null) {
                        callBack.netBack(reqTag, object);
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
            }
        });
    }

    public static void postJsonData(final Context context, final String path, final int reqTag, Map<String, String> params, final Class className, String loadingStr, final NetCallBack callBack) {
        final LoadingDialogTools loadingDialogTools = new LoadingDialogTools();//loading需要独立出来
        if (!TextUtils.isEmpty(loadingStr)) {
            try {
                if (loadingDialogTools != null && loadingDialogTools.isShowing()) {
                    loadingDialogTools.dismiss();
                }
                loadingDialogTools.showLoadingDialog(context, loadingStr, null);
            } catch (Exception e) {
                XLog.d(TAG, "e:" + e);
                e.printStackTrace();
            }
        }
        JSONObject object = new JSONObject(params);
        OkGo.<String>post(path).cacheMode(CacheMode.NO_CACHE).tag(context).upJson(object).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    if (loadingDialogTools != null) {
                        loadingDialogTools.close();
                    }
                    Log.w(TAG, "onSuccess: " + response.body());
                    if (className == null) {
                        if (callBack != null) {
                            callBack.netBack(reqTag, response.body());
                        }
                    } else {
                        Object object = JSON.parseObject(response.body(), className);
                        if (object == null) {
                            Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
                        } else if (callBack != null) {
                            callBack.netBack(reqTag, object);
                        }
                    }
                } catch (Exception o) {
                    o.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
            }
        });
    }

    public static void postStringData(final Context context, final String path, final int reqTag, String params, final Class className, String loadingStr, final NetCallBack callBack) {
        final LoadingDialogTools loadingDialogTools = new LoadingDialogTools();//loading需要独立出来
        if (!TextUtils.isEmpty(loadingStr)) {
            try {
                if (loadingDialogTools != null && loadingDialogTools.isShowing()) {
                    loadingDialogTools.dismiss();
                }
                loadingDialogTools.showLoadingDialog(context, loadingStr, null);
            } catch (Exception e) {
                XLog.d(TAG, "e:" + e);
                e.printStackTrace();
            }
        }
        OkGo.<String>post(path).cacheMode(CacheMode.NO_CACHE).tag(context).upString(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
                Log.w(TAG, "onSuccess: " + response.body());
                if (className == null) {
                    if (callBack != null) {
                        callBack.netBack(reqTag, response.body());
                    }
                } else {
                    Object object = JSON.parseObject(response.body(), className);
                    if (object == null) {
                        Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
                    } else if (callBack != null) {
                        callBack.netBack(reqTag, object);
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (loadingDialogTools != null) {
                    loadingDialogTools.close();
                }
            }
        });
    }

    public interface NetCallBack {
        void netBack(int requestTag, Object object);
    }

}
