package com.utvgo.huya.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;

import com.utvgo.huya.constant.ConstantEnum;

/**
 * Created by haha on 2017/7/23.
 */

public class PlayBaseActivity extends BaseActivity {

    public String videoType = "0";//点播 0 直播 1  默认为点播

    public int playingState = 0;  //0为停止状态    1为正在播放   2为暂停
    public static final int PlayingStateStop = 0;  //0为停止状态    1为正在播放   2为暂停
    public static final int PlayingStatePlaying = 1;  //0为停止状态    1为正在播放   2为暂停
    public static final int PlayingStatePause = 2;  //0为停止状态    1为正在播放   2为暂停
    public ConstantEnum.MediaType fileType = ConstantEnum.MediaType.video;  //0 音频   1视频
    //    private static final String TAG = "PlayBaseActivity";
    public long vodDur = 10 * 60 * 60 * 1000, vodPayingTime = 0;
    public long vodDurQQ = 0; //自己数据库中的播放时长，如果获取到的时长比这个短   则取这个时长
    boolean buySingle = false;
    public String buySingleName = "";
    private static final int HIDE_INFO_VIEW = 7;
    public static final int QUICK_NO_PLAYTIME = 8;
    public static final int PLAY_TIME = 9;

    public boolean needQuickTime = false;
    public boolean quickNoPlayTime = false;
    public boolean needFinish;
    public final static long timeStep = 200;
    public Handler timeHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == PLAY_TIME) {
                vodPayingTime++;
                //Log.d(TAG, "handleMessage: vodPayingTime" + vodPayingTime + "   vodDur + " + vodDur);
                if (videoType.equals("0")) {
                    if ((vodPayingTime + 1) * timeStep >= vodDur) {
                        hahaPlayEnd(0.1f);
                        vodPayingTime = 0;
                        return false;
                    }
                }
                if (playingState != PlayingStatePlaying) {
                    return false;
                }
                setPlayTime(vodPayingTime * timeStep, vodDur);
                timeHandler.sendEmptyMessageDelayed(PLAY_TIME, timeStep);
            } else if (message.what == HIDE_INFO_VIEW) {
                hideInfoView();
            } else if (message.what == QUICK_NO_PLAYTIME) {
                quickNoPlayTime = !quickNoPlayTime;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeHandler.sendEmptyMessageDelayed(HIDE_INFO_VIEW, 5 * 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeHandler.removeCallbacksAndMessages(null);
        playingState = PlayingStateStop;
    }

    public void hahaPlayEnd(float v) {
        timeHandler.removeMessages(PLAY_TIME);
        playingState = PlayingStateStop;
        playStateChange(PlayingStateStop);
    }

    //获取到播放时长
    public void onDuration(long l) {
        //        if (fileType==1){
        vodDur = l;
//        }else{
//            vodPayingTime = 0;
//        }

        if (vodDur < vodDurQQ) {
            vodDur = vodDurQQ;
        }

        playingState = PlayingStatePlaying;
        playStateChange(PlayingStatePlaying);
        timeHandler.removeMessages(PLAY_TIME);
        timeHandler.sendEmptyMessage(PLAY_TIME);
    }

    //设置播放时间
    public void setPlayTime(long nowTime, long allTime) {

    }

    //播放状态改变
    public void playStateChange(int state) {

    }

    //隐藏信息页面  例如播放 暂停 快进 快退那些
    public void hideInfoView() {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //Log.d("dispatchKeyEvent ", " dispatchKeyEvent " + event.getKeyCode());
        timeHandler.removeMessages(HIDE_INFO_VIEW);
        timeHandler.sendEmptyMessageDelayed(HIDE_INFO_VIEW, 5 * 1000);
        return super.dispatchKeyEvent(event);
    }

}
