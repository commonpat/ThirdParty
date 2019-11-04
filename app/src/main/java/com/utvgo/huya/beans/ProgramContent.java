package com.utvgo.huya.beans;

import android.text.TextUtils;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.Platform;
import com.utvgo.huya.BuildConfig;

import java.util.List;

public class ProgramContent extends ProgramInfoBase {
    private List<VideoInfo> videos;
    private String videoUrlHigh;
    private String videoUrlFluency;
    private int freeSecond;
    private int videoId;

    private String albumBackground;

    public String getAlbumBackground() {
        return albumBackground;
    }

    public void setAlbumBackground(String albumBackground) {
        this.albumBackground = albumBackground;
    }

    public List<VideoInfo> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoInfo> videos) {
        this.videos = videos;
    }

    public String getVideoUrlHigh() {
        return videoUrlHigh;
    }

    public void setVideoUrlHigh(String videoUrlHigh) {
        this.videoUrlHigh = videoUrlHigh;
    }

    public String getVideoUrlFluency() {
        return videoUrlFluency;
    }

    public void setVideoUrlFluency(String videoUrlFluency) {
        this.videoUrlFluency = videoUrlFluency;
    }

    public int getFreeSecond() {
        return freeSecond;
    }

    public void setFreeSecond(int freeSecond) {
        this.freeSecond = freeSecond;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    //
    public String getMediaSourceUrl()
    {

        if(!TextUtils.isEmpty(this.videoUrlHigh))
        {
            if( DiffConfig.CurrentPlatform== Platform.gzbn && (this.videoUrlHigh.startsWith("http"))){
                return "HuYaSeriesIDhuya0001test";
            }
            return this.videoUrlHigh;
        }
        if(!TextUtils.isEmpty(this.videoUrlFluency))
        {
            if( DiffConfig.CurrentPlatform== Platform.gzbn && (this.videoUrlFluency.startsWith("http"))){
                return "HuYaSeriesIDhuya0001test";
            }
            return this.videoUrlFluency;
        }

        return "HuYaSeriesIDhuya0001test";
    }
}
