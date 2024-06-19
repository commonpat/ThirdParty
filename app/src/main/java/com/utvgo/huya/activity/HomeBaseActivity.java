package com.utvgo.huya.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.utvgo.handsome.utils.TopWayBroacastUtils;
import com.utvgo.huya.HuyaApplication;

import java.io.File;

public class HomeBaseActivity extends BuyActivity{

    public Handler baseHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 257){
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiver(mHomeKeyEventReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }
    /**
     * 监听是否点击了home键将客户端推到后台
     */
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
        String SYSTEM_REASON = "reason";
        String SYSTEM_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    HuyaApplication.beanActivity = null;
                    TopWayBroacastUtils.getInstance().pressHomeKey(context);
                    clearCacheHomeKey();
                    //表示按了home键,程序到了后台
//                    finish();
//                    android.os.Process.killProcess(android.os.Process.myPid());
//                    Process.killProcess(Process.myPid());
                }
            }
        }
    };
    /**
     * 主页键清缓存，杀进程
     *
     * */
    public void clearCacheHomeKey(){
        final Context context = this;

        Glide.get(context).clearMemory();
        Log.d(TAG, "clearCache: clearMemory");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
                Log.d(TAG, "clearCache: clearDiskCache");
                try{
                    File cacheDir = getCacheDir();
                    deleteFilesByDirectory(cacheDir);
                }catch(Exception e){
                    e.printStackTrace();

                }
                baseHandle.sendEmptyMessage(257);
            }
        }).start();
    }
}
