package com.utvgo.huya.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.ITVBox;
import com.utvgo.handsome.diff.Platform;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanStatistics;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.beans.ProgramContent;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.beans.TPageData;
import com.utvgo.huya.beans.VideoInfo;
import com.utvgo.huya.constant.MVAlbumTemplate;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ViewUtil;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.utvgo.huya.constant.MVAlbumTemplate.BACK;
import static com.utvgo.huya.constant.MVAlbumTemplate.MORE;
import static com.utvgo.huya.constant.MVAlbumTemplate.VIDEOFOCUS;

public class MediaAlbumActivity extends BuyActivity {

    @BindView(R.id.video)
    VideoView video;
    @BindView(R.id.video5)
    VideoView video5;

    @BindView(R.id.bg)
    ImageView bgImageView;

    @BindView(R.id.album_item_content)
    FrameLayout albumItemContent;

    @BindView(R.id.sv_video)
    SurfaceView svVideo;
    @BindView(R.id.activity_album_default)
    FrameLayout albumDefault;
    @BindView(R.id.activity_album_five)
    FrameLayout albumFive;
    @BindView(R.id.btn_fl_video)
    Button btnFlVideo;
    @BindView(R.id.album_title)
    TextView albumTitle;

    private View oldView;
    private String showType = "";
    private boolean isExperience;
    private boolean isToShowBuy = true;

    private int freeTime = -1;
    private long nowTime = 0;
    private int playIndex = 0;
    private String albumMid;
    public TPageData beanWLAblumData;
    public List<OpItem> subjectRecordListBeen = new ArrayList<>();
    private long timesmap =0;

    private int pageIndex = 0;
    final int pageSize = 4;

    private final List<RelativeLayout> itemViewArray = new ArrayList<>();

    private int albumId, channelId, multisetType, currentPage = 0, totalPage = 0;
    private ProgramContent albumData = null;
    private  boolean isFree = false;
    long currentMediaDuration = 0;
    private int playFocusIndex = 0;
    private int playFocusIndexOld = 0;



    public static void show(final Context context, final int albumId)
    {
        Intent intent = new Intent(context, MediaAlbumActivity.class);
        intent.putExtra("albumId", albumId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ablum);

        ButterKnife.bind(this);

        this.albumId = getIntent().getIntExtra("albumId", 61916);
        this.multisetType = getIntent().getIntExtra("multiSetType", 4);
        this.channelId = getIntent().getIntExtra("channelId", 36);
        btnFlVideo.setOnFocusChangeListener(this);

        setHahaPlayer(video);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        });

    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        if (hasFocus){
             if(v instanceof FrameLayout){
                 ViewCompat.animate(v).scaleX(1.20f).scaleY(1.20f).start();
                 ViewUtil.runText(v,hasFocus);
             }
            if(v.getId()== R.id.btn_fl_video){
                btnFlVideo.setNextFocusRightId(albumItemContent.getChildAt(playFocusIndex).getId());
            }
        }else {
            if(v instanceof FrameLayout){
                ViewCompat.animate(v).scaleX(1.00f).scaleY(1.00f).start();
                ViewUtil.runText(v,hasFocus);
            }

        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null) {
            hahaPauseOrResumePlay();
            this.freeTime = (int) (nowTime/1000+10);
        }
        /*
        if (hadCallBuyView  && !isExperience) {
            hadCallBuyView = false;
            finish();
        } else {
            playMedia(playIndex);
        }
        */
    }


    @Override
    protected void onStart() {
        super.onStart();
        statusticsHandler.sendEmptyMessageDelayed(TagStartStatisticsPlay, 30 * 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        startStatisticsPlay(this.currentMediaDuration);
        statusticsHandler.removeMessages(TagStartStatisticsPlay);
    }

    @Override
    protected void onPause() {
        hahaPausePlay();
        super.onPause();
    }

    private static final int TagStartStatisticsPlay = 10011;
    private String statisticsPlayId = "";

    Handler statusticsHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == TagStartStatisticsPlay) {
                startStatisticsPlay(currentMediaDuration);
                statusticsHandler.sendEmptyMessageDelayed(TagStartStatisticsPlay, 30 * 1000);
            }
            return false;
        }
    });

    private void startStatisticsPlay(final long duration) {
        try{
            if(beanWLAblumData != null && playIndex >= 0 && playIndex < albumData.getVideos().size())
            {
                VideoInfo videoBean = albumData.getVideos().get(playIndex);
                NetworkService.defaultService().statisticsVideo(this,
                        "" + 0,
                        "" + videoBean.getVideoId(),
                        videoBean.getName(),
                        "" + albumData.getSupplierId(),
                        albumData.getSupplierName(),
                        "" + albumData.getPkId(),
                        albumData.getName(),
                        "" + albumData.getChannelId(),
                        "",
                        albumData.getMultiSetType(),
                        "0",
                        DiffConfig.CurrentPurchase.isPurchased() ? "1" : "0",
                        AppConfig.VipCode,
                        statisticsPlayId,
                        "" + 0,
                        duration,
                        new JsonCallback<BaseResponse<BeanStatistics>>() {
                            @Override
                            public void onSuccess(Response<BaseResponse<BeanStatistics>> response) {
                                BaseResponse<BeanStatistics> beanStatistics = response.body();
                                if (beanStatistics != null && beanStatistics.getData() != null) {
                                    statisticsPlayId = beanStatistics.getData().getId();
                                }
                            }
                        }
                );
                stat("视频播放-" + videoBean.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void playMedia(int index) {
        if(index >= this.albumData.getVideos().size() || index < 0)
        {
            index = 0;
        }
        if(this.albumData != null)
        {
            statisticsPlayId = "";
            this.playIndex = index;
            VideoInfo videoBean = this.albumData.getVideos().get(this.playIndex);
            //hahaPlayUrl(videoBean.getMediaSourceUrl());
            this.freeTime = videoBean.getFreeSecond();
            getHahaPlayerUrl(videoBean.getMediaSourceUrl());

        }
    }

    @Override
    public void getHahaPlayerUrl(String vodID) {
        VideoInfo videoBean = this.albumData.getVideos().get(this.playIndex);
        super.getHahaPlayerUrl(videoBean.getMediaSourceUrl());
    }


    @Override
    public void hahaPlayEnd(float v) {
        super.hahaPlayEnd(v);
        playMedia(playIndex + 1);
    }

    @Override
    public void onDuration(long l) {
        this.currentMediaDuration = l;
        startStatisticsPlay(this.currentMediaDuration);
        super.onDuration(l);
    }

    @Override
    public void setPlayTime(long nowTime, long allTime) {
        super.setPlayTime(nowTime, allTime);
        this.nowTime = nowTime;
        needBuy();
    }

    private boolean needBuy() {
        if(!HuyaApplication.hadBuy()&&isToShowBuy){
            if(!isFree) {
                if (freeTime != -1 && !isExperience) {
                    if (nowTime / 1000 > freeTime) {

                        showBuy(albumMid);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        switch (viewId)
        {
            case R.id.btn_fl_video:
            {
                playVideoFullScreen();
                break;
            }

          default:
            {
                if(System.currentTimeMillis() > (timesmap + 2000)) {
                    timesmap = System.currentTimeMillis();
                     @SuppressLint("ResourceType") int index =  view.getId()-1000;
                    playFocusIndex = index;
                    playFocusIndexOld = playFocusIndex;
                    albumTitle.setText(this.albumData.getVideos().get(index).getName());
                    if (index >= 0) {
                        playMedia(index);
                    }
                }else {
                    playVideoFullScreen();
                }
            }
        }
    }

    @Override
    public void playStateChange(int state) {
        super.playStateChange(state);
//
//        if(state == PlayingStatePlaying){
//
//            findViewById(arrayPlay[playFocusIndexOld]).setVisibility(View.INVISIBLE);
//            if(this.playIndex/4 == currentPage) {
//                findViewById(arrayPlay[playFocusIndex]).setVisibility(View.VISIBLE);
//            }
//        }

    }

    public void playVideoFullScreen() {
        ArrayList<ProgramInfoBase> list = new ArrayList<>();
        list.add(this.albumData);
        PlayVideoActivity.play(this, list, this.playFocusIndex, false);
    }

    @Override
    public void showBuy(String vodID) {
        isToShowBuy = false;
        if (buySingle) {
            super.showBuy(albumMid);
        } else {
            hahaPausePlay();
            super.showBuy(vodID);
            hadCallBuyView = true;
        }
    }

    @Override
    public void onBackPressed() {

            finish();
    }

    private void updateAlbumBackground(String imageUrl) {
        if(this.albumId == 63483){
            imageUrl = "";
        }
        if(TextUtils.isEmpty(imageUrl)){
            bgImageView.setImageResource(R.mipmap.bg_album_new);
        }else{
            loadImage(bgImageView, DiffConfig.generateImageUrl(imageUrl));
        }
    }

//    void updateItemCount() {
//        String text = String.format("< %d/%d >", Math.min(this.currentPage + 1, this.totalPage), this.totalPage);
//        tvCount.setText(text);
//
//        leftImageView.setImageResource((this.currentPage > 0)?R.mipmap.icon_previous_disavle:R.mipmap.icon_previous);
//        rightImageView.setImageResource(((this.currentPage + 1) >= (this.totalPage))?R.mipmap.icon_next:R.mipmap.icon_next_disable);
//    }

//    void updateItemContent() {
//        boolean flag = false;
//        for(int j = 0; j < itemViewArray.size(); j++){
//            RelativeLayout itemLayout = this.itemViewArray.get(j);
//            int contentIndex = this.currentPage*pageSize + j;
//            if(contentIndex < this.albumData.getVideos().size()){
//                itemLayout.setVisibility(View.VISIBLE);
//                VideoInfo videoBean = this.albumData.getVideos().get(contentIndex);
//                for(int k = 0; k < itemLayout.getChildCount(); k++)
//                {
//                    flag = true;
//                    View view = itemLayout.getChildAt(k);
//                    if (k == 2){
//                        ImageView imageView = (ImageView)view;
//                        imageView.setVisibility(View.INVISIBLE);
//
//                        continue;
//                    }
//                    if(view instanceof ImageView)
//                    {
//                        ImageView imageView = (ImageView)view;
//                        String posterUrl = DiffConfig.generateImageUrl(videoBean.getPoster());
//                        loadImage(imageView, posterUrl);
//                    }
//                    if(view instanceof Button){
//                        continue;
//                    }
//                    if(view instanceof TextView)
//                    {
//                        TextView textView = (TextView)view;
//                        textView.setText("" + (contentIndex + 1) + ". " + videoBean.getName());
//                    }
//                }
//                if(this.playIndex/4 == currentPage) {
//                    findViewById(arrayPlay[playFocusIndex]).setVisibility(View.VISIBLE);
//                }
//            }
//            else
//            {
//                itemLayout.setVisibility(View.INVISIBLE);
//            }
//        }
//        findViewById(flag ? R.id.btn_fl_item1 : R.id.btn_fl_video).requestFocus();
//    }


    /*
    *** Network
     */
   private void loadData(){
        NetworkService.defaultService().fetchProgramContent(this, this.albumId, this.multisetType + "", this.channelId,  new JsonCallback<BaseResponse<ProgramContent>>() {
            @Override
            public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
                BaseResponse<ProgramContent> data = response.body();
                if(data.isOk()){
                    layout(data.getData());
                    try{
                        stat("专辑播放-" + data.getData().getName());
                        }catch (Exception e){
                            HiFiDialogTools.getInstance().showtips(MediaAlbumActivity.this, "获取数据失败，请稍后重试", null);
                        }
                }
            }
        });
    }

    private void layout(final ProgramContent bean) {
        if(bean == null) {
            return;
        }
        this.albumData = bean;
        this.showType = bean.getShowType();
        this.currentPage = 0;
        this.totalPage = this.albumData.getVideos().size()/pageSize;
        if(this.albumData.getVideos().size()%pageSize > 0)
        {
            this.totalPage++;
        }
         updateAlbumBackground(bean.getAlbumBackground());
         //updateItemCount();
         updateItemContent();

        if(this.albumData.getVideos().size() > 0)
        {   this.isFree = this.albumData.isFree();
            this.freeTime =this.albumData.getVideos().get(0).getFreeSecond();
            albumTitle.setText(this.albumData.getVideos().get(0).getName());
            playMedia(0);
        }
    }

    private void updateItemContent() {
        for(int i=0;i<this.albumData.getVideos().size();i++){
            VideoInfo videoInfo = this.albumData.getVideos().get(i);
            View itemView = View.inflate(this,R.layout.album_item_content,null);
            itemView.setId(1000+i);
            itemView.setFocusable(true);
            itemView.setOnFocusChangeListener(this);
            itemView.setOnClickListener(this);
            itemView.setNextFocusLeftId(R.id.btn_fl_video);
            TextView textView = itemView.findViewById(R.id.album_item_content_text);
            textView.setText(videoInfo.getName());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = (int) getResources().getDimension(R.dimen.dp10)*(i+1)+(int) getResources().getDimension(R.dimen.dp70)*i;
            layoutParams.bottomMargin =(int) getResources().getDimension(R.dimen.dp10);
            layoutParams.leftMargin = (int) getResources().getDimension(R.dimen.dp30);
            albumItemContent.addView(itemView, layoutParams);

        }
           showViewByHandler(albumItemContent.getChildAt(0));
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                traversalView(MediaAlbumActivity.this);
//            }
//        },5000);

    }


}
