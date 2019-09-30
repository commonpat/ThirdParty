package com.utvgo.huya.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.GlideApp;
import com.utvgo.huya.R;
import com.utvgo.huya.UTVGOSubscriber;
import com.utvgo.huya.activity.MVAlbumActivity;
import com.utvgo.huya.activity.PlayVideoActivity;
import com.utvgo.huya.activity.TopicActivity;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.beans.PageBean;
import com.utvgo.huya.databinding.Page1Binding;
import com.utvgo.huya.utils.ActivityUtility;
import com.utvgo.huya.utils.Appconfig;
import com.utvgo.huya.utils.ToastUtil;
import com.utvgo.huya.views.CustomVideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fragment1 extends BaseFragment {
    private String TAG = "Fragment1";
    private Page1Binding binding;
    ImageView[] imgs;
    String[] vodIdStr;
    int vodIdStrIndex;
    VideoView videoView;
    CustomVideoView customVideoView;
    PageBean.DataBean dataBean;
    private List<PageBean.DataBean> dataBeans = new ArrayList<>();
    PageBean pageBean = new PageBean();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.page_1, container, false);
        imgs = new ImageView[]{binding.bits1, binding.bits2, binding.bits3, binding.bits4, binding.bits5, binding.bits6, binding.bits7, binding.bits8, binding.bits9, binding.bits10};


        for (int i = 0; i < imgs.length; i++) {
            imgs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.bits_1:
                            clickRecItem(0);
                            break;
                        case R.id.bits_2:
                            clickRecItem(1);
                            break;
                        case R.id.bits_3:
                            clickRecItem(2);
                            break;
                        case R.id.bits_4:
                            clickRecItem(3);
                            break;
                        case R.id.bits_5:
                            clickRecItem(4);
                            break;
                        case R.id.bits_6:
                            clickRecItem(5);
                            break;
                        case R.id.bits_7:
                            clickRecItem(6);
                            break;
                        case R.id.bits_8:
                            clickRecItem(7);
                            break;
                        case R.id.bits_9:
                            clickRecItem(8);
                            break;
                        case R.id.bits_10:
                            clickRecItem(9);
                            break;

                    }
                }
            });
        }
        getBaseActivity().server.getPage(typeId, Appconfig.getKeyNo(getContext()), new UTVGOSubscriber<PageBean>() {
            @Override
            public void onNext(PageBean pageBean) {
                dataBeans = pageBean.getData();
                for (int i = 0; i < pageBean.getData().size() && i < imgs.length; i++) {
                    dataBean = pageBean.getData().get(i);
                    String isVideo = dataBean.getIsVideo();
                    if (BuildConfig.DEBUG) {
                        if (i == 3) {
                            isVideo = "1";
                        }
                    }
                    if (isVideo.equals("1")) {
                        playVideo();
                    } else {
                        if (dataBean.getIsVideo().equals("0")) {
                            GlideApp.with(getContext()).load(pageBean.getImageProfix() + dataBean.getImgUrl()).into(imgs[i]);
                        }
                    }
                }
            }
        });
        return binding.getRoot();
    }

    private void clickRecItem(int index) {
//        if (!DiffConfig.validateDeviceKeyNO(this)) {
//            return;
//        }
        PageBean.DataBean selectBean = dataBeans.get(index);
//        selectBean.setHref("http://172.16.146.41/qqmusic_zt.html?themId=139&styleID=6");
//        selectBean.setHrefType("7");
        if (TextUtils.equals(selectBean.gethrefType(), "0")) { //超链接
            ActivityUtility.goWebActivityActivity(getActivity(), selectBean.getHref());
        } else if (TextUtils.equals(selectBean.gethrefType(), "3")) { //mv专辑
            Intent intent = new Intent(getActivity(), MVAlbumActivity.class);
            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("pkId"));
            startActivity(intent);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "1")) { //音频专辑
//            Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);
//            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("zjid"));
//            startActivity(intent);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "8")) { //活动
//            //Intent intent = new Intent(IndexActivity.this, ActivityActivity.class);
//            //startActivity(intent);
//            //todo
//        } else if (selectBean.getHref().contains("collect.html")) { //收藏
//            Intent intent = new Intent(this, CollectCenterActivity.class);
//            intent.putExtra("type", 1);
//            startActivity(intent);
//        } else if (selectBean.getHref().contains("htyplay.html")) { //历史
//            Intent intent = new Intent(this, CollectCenterActivity.class);
//            intent.putExtra("type", 0);
//            startActivity(intent);
        } else if (TextUtils.equals(selectBean.gethrefType(), "4")) { //专题
            Intent intent = new Intent(getContext(), TopicActivity.class);
            intent.putExtra("topicId", Uri.parse(selectBean.getHref()).getQueryParameter("themId"));
            intent.putExtra("type", Uri.parse(selectBean.getHref()).getQueryParameter("styleID"));
            startActivity(intent);
//        } else if (selectBean.getHref().contains("recordList_pd.html")) { //榜单
//            ActivityUtility.goSongRankActivity(this, Uri.parse(selectBean.getHref()).getQueryParameter("mid"),
//                    Uri.parse(selectBean.getHref()).getQueryParameter("id"), selectBean.getName());
//        } else if (TextUtils.equals(selectBean.getHrefType(), "12")) {//专题收录
//            ActivityUtility.goActivity(this, TopicCollectionActivity.class);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "13")) {//直播
//            if (!TextUtils.isEmpty(selectBean.getHref())) {
//                String[] strs = selectBean.getHref().split("=");
//                if (strs.length > 1) {
//                    turnLiveActivity(Integer.parseInt(strs[1]));
//                }
//            }
        } else {
            //视频播放
            ArrayList<BeanUserPlayList.DataBean> playHistoryList = new ArrayList<>();
            getHrefList(index, selectBean, playHistoryList);
            Intent intent = new Intent(getContext(), PlayVideoActivity.class);
            if (index == 1) {
                intent.putExtra("playIndex", vodIdStrIndex);
            } else {
                intent.putExtra("playIndex", 0);
            }
            intent.putExtra("fileType", 1);
            intent.putParcelableArrayListExtra("playList", playHistoryList);
            this.startActivity(intent);
        }

    }
    private void getHrefList(int index,  PageBean.DataBean  selectBean, ArrayList<BeanUserPlayList.DataBean> playHistoryList) {
                for(int i=0;i<dataBeans.size();i++){
                    BeanUserPlayList.DataBean playBean = new BeanUserPlayList.DataBean();
                    playBean.setSingerMids(vodIdStr[vodIdStrIndex]);
                    if(playBean.getSingerMids()==null||"".equals(playBean.getSingerMids()))
                    {
                        ToastUtil.show(getContext(),"资源数据有误！");
                    }
                    playHistoryList.add(playBean);
                }
    }

    public void playVideo() {
        vodIdStr = dataBean.getVideoUrl().split(",");
        if (vodIdStr != null || vodIdStr.length != 0) {
            vodIdStrIndex = new Random().nextInt(vodIdStr.length);
            try {
                setPlayer(binding.vvSmall);
                playUrl(vodIdStr[vodIdStrIndex]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void playUrl(String url) {
        videoView.setVisibility(View.VISIBLE);
        videoView.setVideoPath(url);
        //videoView.setVideoURI(Uri.parse(url));
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // playEnd(0.1f);
                mp.setDisplay(null);
                mp.reset();
                mp.setDisplay(videoView.getHolder());
                playVideo();
                Log.d(TAG, "onCompletion: 播放结束");
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //onDuration((long) mp.getDuration());
                //mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                videoView.start();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e("PlayVideoActivity", "onError: what " + what + "  extra " + extra);
                playEnd(0.1f);
                return true;
            }
        });
    }

    public void setPlayer(Object player) {
        videoView = (VideoView) player;
    }

    public void playEnd(float v) {
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }
}




