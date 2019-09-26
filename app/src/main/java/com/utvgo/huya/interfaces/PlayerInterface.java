package com.utvgo.huya.interfaces;

public interface PlayerInterface {

    enum PlayerSize {
        PlayerSizeSmall,
        PlayerSizeFull,
    }

    //设置小窗播放的播放器
    void setHahaPlayer(Object player);

    //设置小窗播放的播放器
    Object getHahaPlayer();

    //根据vodid获取播放url
    void getHahaPlayerUrl(String vodId);

    //播放url
    void hahaPlayUrl(String playUrl);

    //开始或暂停
    void hahaPauseOrResumePlay();

    //播放结束
    void hahaPlayEnd(float v);

    //暂停播放
    void hahaPausePlay();

    //快进
    void hahaQuickSeek(double timePercent);

    //设置播放器大小  播放视频区域 x,y,w,h
    void setHahaPlayerSize(int x, int y, int w, int h); //type=0 为小窗  type=1为全屏

}
