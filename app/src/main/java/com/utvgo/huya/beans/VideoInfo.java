package com.utvgo.huya.beans;

import android.text.TextUtils;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.Platform;
import com.utvgo.huya.BuildConfig;

import java.io.Serializable;

public class VideoInfo implements Serializable {
    /**
     * {
     * "aliasName": "小棉花Top5大腿杯篇：白白橘右京名刀强行续命！空血硬钢四人怒保基地",
     * "focusImgUrl": "",
     * "videoUrlHigh": "http://172.16.146.69:17553/EG/huya/169832881.mp4",
     * "imageBig": "2019/08/06/20190806105958517.jpg",
     * "freeSecond": 15,
     * "videoId": 197058,
     * "imageMid": "2019/08/06/20190806105958517.jpg",
     * "number": 0,
     * "publicTime": "20180101",
     * "isFree": "0",
     * "vodId": "",
     * "normalImgUrl": "",
     * "name": "小棉花Top5大腿杯篇：白白橘右京名刀强行续命！空血硬钢四人怒保基地",
     * "imageSmall": "2019/08/06/20190806105958517.jpg",
     * "videoUrlFluency": "http://172.16.146.69:17553/EG/huya/169832881.mp4",
     * "titleSecond": 0
     * },
     */
    private String aliasName;//小棉花Top5大腿杯篇：白白橘右京名刀强行续命！空血硬钢四人怒保基地",
    private String focusImgUrl;
    private String videoUrlHigh;//http://172.16.146.69:17553/EG/huya/169832881.mp4",
    private String imageBig;//2019/08/06/20190806105958517.jpg",
    private int freeSecond;// 15,
    private int videoId;//": 197058,
    private String imageMid;//": "2019/08/06/20190806105958517.jpg",
    private int number;//": 0,
    private String publicTime;//": "20180101",
    private int isFree;//": "0",
    private String vodId;//": "",
    private String normalImgUrl;//": "",
    private String name;//": "小棉花Top5大腿杯篇：白白橘右京名刀强行续命！空血硬钢四人怒保基地",
    private String imageSmall;//": "2019/08/06/20190806105958517.jpg",
    private String videoUrlFluency;//": "http://172.16.146.69:17553/EG/huya/169832881.mp4",
    private int titleSecond;//": 0

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getFocusImgUrl() {
        return focusImgUrl;
    }

    public void setFocusImgUrl(String focusImgUrl) {
        this.focusImgUrl = focusImgUrl;
    }

    public String getVideoUrlHigh() {
        return videoUrlHigh;
    }

    public void setVideoUrlHigh(String videoUrlHigh) {
        this.videoUrlHigh = videoUrlHigh;
    }

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
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

    public String getImageMid() {
        return imageMid;
    }

    public void setImageMid(String imageMid) {
        this.imageMid = imageMid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public String getVodId() {
        return vodId;
    }

    public void setVodId(String vodId) {
        this.vodId = vodId;
    }

    public String getNormalImgUrl() {
        return normalImgUrl;
    }

    public void setNormalImgUrl(String normalImgUrl) {
        this.normalImgUrl = normalImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getVideoUrlFluency() {
        return videoUrlFluency;
    }

    public void setVideoUrlFluency(String videoUrlFluency) {
        this.videoUrlFluency = videoUrlFluency;
    }

    public int getTitleSecond() {
        return titleSecond;
    }

    public void setTitleSecond(int titleSecond) {
        this.titleSecond = titleSecond;

    }

    public String getPoster()
    {
        String imageUrl = "";
        if(!TextUtils.isEmpty(this.imageMid))
        {
            return this.imageMid;
        }
        if(!TextUtils.isEmpty(this.imageSmall))
        {
            return this.imageSmall;
        }
        return imageUrl;
    }

    public String getMediaSourceUrl()
    {

        if(!TextUtils.isEmpty(this.videoUrlHigh))
        {
            if(this.videoUrlHigh.startsWith("http")){
                return this.videoUrlHigh;
            }else {
                this.videoUrlHigh = DiffConfig.playHost+videoUrlHigh;
                return this.videoUrlHigh;
            }
        }
        if(!TextUtils.isEmpty(this.videoUrlFluency))
        {
            if(this.videoUrlFluency.startsWith("http")){
                return this.videoUrlFluency;
            }else {
                this.videoUrlFluency = DiffConfig.playHost+videoUrlFluency;
                return this.videoUrlFluency;
            }
        }

        return "";
    }
}
