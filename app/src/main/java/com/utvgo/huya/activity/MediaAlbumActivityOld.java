package com.utvgo.huya.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.interfaces.JsonCallback;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.utvgo.huya.constant.ConstantEnumHuya.VIDEOLIST;
import static com.utvgo.huya.constant.MVAlbumTemplate.BACK;
import static com.utvgo.huya.constant.MVAlbumTemplate.MORE;
import static com.utvgo.huya.constant.MVAlbumTemplate.VIDEOFOCUS;

public class MediaAlbumActivityOld extends BuyActivity {

    @BindView(R.id.tv_count)
    TextView tvCount;

    @BindView(R.id.video)
    VideoView video;
    @BindView(R.id.video5)
    VideoView video5;

    @BindView(R.id.bg)
    ImageView bgImageView;

    @BindView(R.id.icon_left_narrow)
    ImageView leftImageView;
    @BindView(R.id.icon_right_narrow)
    ImageView rightImageView;
    @BindView(R.id.sv_video)
    SurfaceView svVideo;
    @BindView(R.id.activity_album_default)
    FrameLayout albumDefault;
    @BindView(R.id.activity_album_five)
    FrameLayout albumFive;
    @BindView(R.id.btn_fl_video)
    Button btnFlVideo;

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
    final int arrayPlay[] = {R.id.media_play0,R.id.media_play1,R.id.media_play2,R.id.media_play3};


    public static void show(final Context context, final int albumId)
    {
        Intent intent = new Intent(context, MediaAlbumActivityOld.class);
        intent.putExtra("albumId", albumId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ablum_old);

        ButterKnife.bind(this);

        this.albumId = getIntent().getIntExtra("albumId", 61916);
        this.multisetType = getIntent().getIntExtra("multiSetType", 4);
        this.channelId = getIntent().getIntExtra("channelId", 36);
        btnFlVideo.setOnFocusChangeListener(this);
        oldView = findViewById(R.id.btn_fl_item1);
        final int array1[] = {R.id.btn_fl_item1, R.id.btn_fl_item2, R.id.btn_fl_item3, R.id.btn_fl_item4};

        final int array[] = {R.id.fl_item1, R.id.fl_item2, R.id.fl_item3, R.id.fl_item4 };

        for(int i = 0; i < array.length; i++)
        {
            this.itemViewArray.add((RelativeLayout)findViewById(array[i]));
            findViewById(array1[i]).setOnFocusChangeListener(this);

        }
       setHahaPlayer(video);

        loadData();
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        if (hasFocus){
            if(v.getId() == btnFlVideo.getId()){
                btnFlVideo.setNextFocusDownId(oldView.getId());
            }
        }else {

            oldView = v;

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
                stat("视频播放-" + videoBean.getName(),"");
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean flag = false;
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            {
                View view = getCurrentFocus();
                if(view != null)
                {
                    int focusViewId = view.getId();
                    if(focusViewId == R.id.btn_fl_item4)
                    {
                        if(((this.currentPage + 1)*this.pageSize) < this.albumData.getVideos().size())
                        {
                            flag = true;
                            this.currentPage++;
                            updateItemCount();
                            updateItemContent();
                            findViewById(R.id.btn_fl_item1).requestFocus();
                        }
                    }
                }
                break;
            }

            case KeyEvent.KEYCODE_DPAD_LEFT:
            {
                View view = getCurrentFocus();
                if(view != null)
                {
                    int focusViewId = view.getId();
                    if(focusViewId == R.id.btn_fl_item1)
                    {
                        if(this.currentPage > 0)
                        {
                            flag = true;
                            this.currentPage--;
                            updateItemCount();
                            updateItemContent();
                            findViewById(R.id.btn_fl_item1).requestFocus();
                        }
                    }
                }
                break;
            }
        }
        if(!flag)
        {
            flag = super.onKeyDown(keyCode, event);
        }
        return flag;
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

            case R.id.btn_fl_item1:
            case R.id.btn_fl_item2:
            case R.id.btn_fl_item3:
            case R.id.btn_fl_item4:
            {
                if(System.currentTimeMillis() > (timesmap + 2000)) {
                    timesmap = System.currentTimeMillis();
                    final int array[] = {R.id.btn_fl_item1, R.id.btn_fl_item2, R.id.btn_fl_item3, R.id.btn_fl_item4};
                    int index = Arrays.binarySearch(array, viewId);
                    playFocusIndexOld = playFocusIndex;
                    playFocusIndex = index;
                    if (index >= 0) {
                        int playIndex = this.currentPage * pageSize + index;
                        this.playIndex = index;
                        playMedia(playIndex);
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

        if(state == PlayingStatePlaying){

            findViewById(arrayPlay[playFocusIndexOld]).setVisibility(View.INVISIBLE);
            if(this.playIndex/4 == currentPage) {
                findViewById(arrayPlay[playFocusIndex]).setVisibility(View.VISIBLE);
            }
        }

    }

    public void playVideoFullScreen() {
        ArrayList<ProgramInfoBase> list = new ArrayList<>();
        list.add(this.albumData);
        PlayVideoActivity.play(this, list, this.playIndex, false);
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

    void updateAlbumBackground(String imageUrl)
    {
        if(this.albumId == 63483){
            imageUrl = "";
        }
        if(TextUtils.isEmpty(imageUrl))
        {
            bgImageView.setImageResource(R.mipmap.bg_album);
        }
        else
        {
            loadImage(bgImageView, DiffConfig.generateImageUrl(imageUrl));
        }
    }

    void updateItemCount()
    {
        String text = String.format("< %d/%d >", Math.min(this.currentPage + 1, this.totalPage), this.totalPage);
        tvCount.setText(text);

        leftImageView.setImageResource((this.currentPage > 0)?R.mipmap.icon_previous_disavle:R.mipmap.icon_previous);
        rightImageView.setImageResource(((this.currentPage + 1) >= (this.totalPage))?R.mipmap.icon_next:R.mipmap.icon_next_disable);
    }

    void updateItemContent()
    {
        boolean flag = false;
        for(int j = 0; j < itemViewArray.size(); j++)
        {
            RelativeLayout itemLayout = this.itemViewArray.get(j);
            int contentIndex = this.currentPage*pageSize + j;
            if(contentIndex < this.albumData.getVideos().size())
            {
                itemLayout.setVisibility(View.VISIBLE);
                VideoInfo videoBean = this.albumData.getVideos().get(contentIndex);
                for(int k = 0; k < itemLayout.getChildCount(); k++)
                {
                    flag = true;
                    View view = itemLayout.getChildAt(k);
                    if (k == 2){
                        ImageView imageView = (ImageView)view;
                        imageView.setVisibility(View.INVISIBLE);

                        continue;
                    }
                    if(view instanceof ImageView)
                    {
                        ImageView imageView = (ImageView)view;
                        String posterUrl = DiffConfig.generateImageUrl(videoBean.getPoster());
                        loadImage(imageView, posterUrl);
                    }
                    if(view instanceof Button){
                        continue;
                    }
                    if(view instanceof TextView)
                    {
                        TextView textView = (TextView)view;
                        textView.setText("" + (contentIndex + 1) + ". " + videoBean.getName());
                    }
                }
                if(this.playIndex/4 == currentPage) {
                    findViewById(arrayPlay[playFocusIndex]).setVisibility(View.VISIBLE);
                }
            }
            else
            {
                itemLayout.setVisibility(View.INVISIBLE);
            }
        }
        findViewById(flag ? R.id.btn_fl_item1 : R.id.btn_fl_video).requestFocus();
    }

    private void updateFiveItemContent() {
        FrameLayout album_five = albumFive.findViewById(R.id.album_5_list);
        ImageView albumBg = albumFive.findViewById(R.id.album_5_bg);
        ImageView vif = albumFive.findViewById(R.id.vif);
        ImageView btnBack = albumFive.findViewById(R.id.btn_back);
        ImageView btnMore = albumFive.findViewById(R.id.btn_more);
        loadImage(albumBg,this.albumData.getAlbumBackground());
        for(int i=0;i<this.albumData.getVideos().size();i++) {
            if(VIDEOFOCUS.equals(this.albumData.getVideos().get(i).getHrefType())){
                vif.setId(i);
                vif.setOnClickListener(this);
                continue;
            }
            if(BACK.equals(this.albumData.getVideos().get(i).getHrefType())&& (this.albumData.getVideos().get(i).getNormalImgUrl()!=null&& this.albumData.getVideos().get(i).getNormalImgUrl().isEmpty())){
                Glide.with(this).load(DiffConfig.generateImageUrl(this.albumData.getVideos().get(i).getNormalImgUrl())).into(btnBack);
                btnBack.setId(i);
                continue;
            }
            if(MORE.equals(this.albumData.getVideos().get(i).getHrefType())&&(this.albumData.getVideos().get(i).getNormalImgUrl()!=null&& this.albumData.getVideos().get(i).getNormalImgUrl().isEmpty())){
                Glide.with(this).load(DiffConfig.generateImageUrl(this.albumData.getVideos().get(i).getNormalImgUrl())).into(btnMore);
                btnMore.setId(i);
                continue;
            }
            View itemView= View.inflate(this, R.layout.album_5_item,null);
            itemView.setId(i);
            itemView.setOnClickListener(this);
            itemView.setOnFocusChangeListener(this);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.item_image);;
            imageView.setOnFocusChangeListener(this);
            imageView.setOnClickListener(this);
            imageView.setId(i);
            TextView number = itemView.findViewById(R.id.number);
            number.setText(String.valueOf(i+1));
            TextView textView = (TextView) itemView.findViewById(R.id.name);
            textView.setText(this.albumData.getVideos().get(i).getName());

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int)this.getResources().getDimension(R.dimen.dp400),(int) this.getResources().getDimension(R.dimen.dp100));
            int topMargin = (int)(this.getResources().getDimension(R.dimen.dp50))+i*(int)(this.getResources().getDimension(R.dimen.dp100));
            params.topMargin = topMargin;
            //allHeight =  topMargin;
            album_five.addView(itemView,params);

            if(!(this.albumData.getVideos().get(i).getNormalImgUrl()).isEmpty()||this.albumData.getVideos().get(i).getNormalImgUrl()!= null) {
                Glide.with(this).load(DiffConfig.generateImageUrl(this.albumData.getVideos().get(i).getNormalImgUrl())).into(imageView);
            }
        }
    }

    /*
    *** Network
     */
    void loadData(){
        NetworkService.defaultService().fetchProgramContent(this, this.albumId, this.multisetType + "", this.channelId, "",new JsonCallback<BaseResponse<ProgramContent>>() {
            @Override
            public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
                BaseResponse<ProgramContent> data = response.body();
                if(data.isOk())
                {
                    layout(data.getData());
                    try{
                    stat("专辑播放-" + data.getData().getName(),VIDEOLIST);
                    }catch (Exception e){
                        HiFiDialogTools.getInstance().showtips(MediaAlbumActivityOld.this, "获取数据失败，请稍后重试", null);

                    }
                }
            }
        });
    }

    void layout(final ProgramContent bean)
    {
        if(bean == null)
        {
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
        if(this.showType.equals(MVAlbumTemplate.FIVE)){
            albumDefault.setVisibility(View.GONE);
            albumFive.setVisibility(View.VISIBLE);
            setHahaPlayer(video5);

            //updateAlbumBackground(bean.getAlbumBackground());
           // updateItemCount();
            updateFiveItemContent();
        }else {
            updateAlbumBackground(bean.getAlbumBackground());
            updateItemCount();
            updateItemContent();
        }
        if(this.albumData.getVideos().size() > 0)
        {   this.isFree = this.albumData.isFree();
            this.freeTime =this.albumData.getVideos().get(0).getFreeSecond();
            playMedia(0);
        }
    }




}
