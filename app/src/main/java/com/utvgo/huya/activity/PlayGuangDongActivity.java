package com.utvgo.huya.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import com.utvgo.huya.interfaces.PlayerInterface;
import com.utvgo.handsome.utils.XLog;
//import com.utvgo.qqmusic.interfaces.PlayerInterface;
//import com.utvgo.qqmusic.utils.XLog;
//
//import diff.DiffConfig;
//import diff.ITVBox;

/**
 * Created by haha on 2018/1/7.
 */

public class PlayGuangDongActivity extends PlayBaseActivity implements PlayerInterface {

    //private final String TAG = "PlayGuizhouActivity";
    protected VideoView videoView;

    @Override
    public void hahaPlayUrl(String playUrl) {

        String assetId = playUrl;

        vodPayingTime = 0;
        videoView.setVisibility(View.VISIBLE);
        videoView.setVideoURI(Uri.parse(assetId));
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                hahaPlayEnd(0.1f);
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPrepared(MediaPlayer mp) {
                onDuration((long) mp.getDuration());
                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                videoView.start();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e("PlayVideoActivity", "onError: what " + what + "  extra " + extra);
                hahaPlayEnd(0.1f);
                return true;
            }
        });
    }

    @Override
    public void hahaPauseOrResumePlay() {
        if (videoView != null) {
            if (playingState == PlayingStatePlaying) {
                videoView.pause();
            } else if (playingState == PlayingStatePause) {
                videoView.start();
            }
        }
        if (playingState == PlayingStatePlaying) {
            playingState = PlayingStatePause;
            playStateChange(PlayingStatePause);
            timeHandler.removeMessages(PLAY_TIME);
        } else if (playingState == PlayingStatePause) {
            playingState = PlayingStatePlaying;
            playStateChange(PlayingStatePlaying);
            timeHandler.removeMessages(PLAY_TIME);
            timeHandler.sendEmptyMessage(PLAY_TIME);
        }
    }

    @Override
    public void hahaPlayEnd(float v) {
        if (videoView != null) {
            videoView.stopPlayback();
        }
        super.hahaPlayEnd(v);
    }

    @Override
    public void hahaPausePlay() {
        if (videoView != null) {
            videoView.pause();
        }
        playingState = PlayingStatePause;
        playStateChange(PlayingStatePause);
        timeHandler.removeMessages(PLAY_TIME);
    }

    @Override
    public void hahaQuickSeek(double timePercent) {
        Log.d("play", "timePercent  " + timePercent);
        long time = (long) (timePercent * vodDur);
        Log.d("time", "time  " + time);
        timeHandler.removeMessages(PLAY_TIME);
        timeHandler.sendEmptyMessage(PLAY_TIME);
        vodPayingTime = time / timeStep;
        if (videoView != null) {
            videoView.seekTo((int) (vodPayingTime * timeStep));
        }
    }

    @Override
    public void setHahaPlayerSize(int x, int y, int w, int h) {

    }

    @Override
    public void setHahaPlayer(Object player) {
        videoView = (VideoView) player;
    }

    @Override
    public Object getHahaPlayer() {
        return videoView;
    }

    @Override
    public void getHahaPlayerUrl(final String vodId) {
        if(vodId.indexOf("http") >= 0)
        {
            String assetId = vodId;
            XLog.d("Play", assetId);
            hahaPlayUrl(assetId);
        }
//        else
//        {
//            final Context context = PlayGuangDongActivity.this;
//            DiffConfig.CurrentTVBox.fetchUrlByVODAssetId(context, vodId, new ITVBox.FetchUrlByVODAssetIdCallBack() {
//                @Override
//                public void onReceivedUrl(String vodId, String url) {
//                    hahaPlayUrl(url);
//                }
//            });
//        }
    }

    public void getLivePlayerUrl(String vodId) {
        //贵州
    }

}
