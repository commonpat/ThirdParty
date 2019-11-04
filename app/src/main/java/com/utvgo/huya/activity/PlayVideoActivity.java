package com.utvgo.huya.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.Platform;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.XLog;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanCheckCollect;
import com.utvgo.huya.beans.BeanStatistics;
import com.utvgo.huya.beans.ProgramContent;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.beans.TPageData;
import com.utvgo.huya.beans.VideoInfo;
import com.utvgo.huya.constant.ConstantEnum;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ToastUtil;
import com.utvgo.huya.utils.Tools;
import com.vod.VPlayer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayVideoActivity extends BuyActivity {

    @BindView(R.id.ll_play_quick)
    LinearLayout ll_play_quick;
    @BindView(R.id.tv_quick_info)
    TextView tv_quick_info;
    @BindView(R.id.iv_quick_icon)
    ImageView iv_quick_icon;

    @BindView(R.id.iv_player_play)
    ImageView ivPlayerPlay;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;

    @BindView(R.id.rl_control)
    RelativeLayout rlControl;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.video_player_progress)
    SeekBar videoPlayerProgress;
    @BindView(R.id.fl_playList)
    FrameLayout flPlayList;
    @BindView(R.id.fl_playlist_content)
    FrameLayout flPlayListContent;

    @BindView(R.id.tv_singer_name)
    TextView tvSingerName;
    @BindView(R.id.tv_dur_left)
    TextView tvDurLeft;
    @BindView(R.id.tv_dur_right)
    TextView tvDurRight;
    @BindView(R.id.tv_playListName)
    TextView tvPlayListName;
    @BindView(R.id.rl_progress)
    RelativeLayout rlProgress;
    @BindView(R.id.iv_player_previous)
    ImageView ivPlayerPrevious;
    @BindView(R.id.iv_player_next)
    ImageView ivPlayerNext;
    @BindView(R.id.iv_player_list)
    ImageView ivPlayerList;
    @BindView(R.id.rl_video)
    RelativeLayout rlVideo;
    @BindView(R.id.activity_RootView)
    FrameLayout activityRootView;
    @BindView(R.id.gif_video_load)
    ImageView gifVideoLoad;
    @BindView(R.id.tv_progress_tag)
    TextView tvProgressTag;

    @BindView(R.id.vv_jingling)
    VideoView vvJingling;

    private int playingIndex = 0;
    private String playlistName = "";
    private ArrayList<ProgramInfoBase> playList = new ArrayList<>();

    private boolean quickSeekNow = false;

    private ArrayList<View> playListItem = new ArrayList<>();
    private int freeTime = -1;
    private long nowTime = 0;

    private int rankPageIndex;
    private int rankPageSize;
    private int rankPageTotal;
    //是否要请网络调用订购页面，避免多次弹订购
    private boolean isToShowBuy;
    private HiFiDialogTools tools;
    private boolean isExperience;
    boolean isMultiSetType = false;

    private long exitTime = 0;
    private String ifCollectton = "no";

    ProgramContent currentProgramContent;

    private static final int TagStartStatisticsPlay = 10011;
    private String statisticsPlayId = "";
    private String statisticsVideoId = "";
    final Handler statHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == TagStartStatisticsPlay) {
                startStatisticsPlay();
                statHandler.sendEmptyMessageDelayed(TagStartStatisticsPlay, 30 * 1000);
            }
            return false;
        }
    });

    public static void play(final Context context,
                            final ArrayList<ProgramInfoBase> data, //name, pkId
                            final int defaultPlayPosition,
                            final boolean isFree) {
        final ConstantEnum.MediaType mediaType = ConstantEnum.MediaType.video;
        Intent intent = new Intent(context, PlayVideoActivity.class);
        intent.putExtra("playIndex", defaultPlayPosition);
        intent.putExtra("fileType", mediaType.ordinal());
        intent.putExtra("isExperience", isFree);
        if(data != null)
        {
            Gson gson = new Gson();
            Type typeToken = new TypeToken<ArrayList<ProgramInfoBase>>(){}.getType();
            String jsonString = gson.toJson(data, typeToken);
            intent.putExtra("playList", jsonString);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        ButterKnife.bind(this);
        clearSomeFocusBeforeListen();
        //遍历所有view,实现focus监听
        traversalView(this);
        //调用复写的创建BorderView
        createBorderView(this);

        playingIndex = getIntent().getIntExtra("playIndex", 0);
        playlistName = getIntent().getStringExtra("playlistName");
        isExperience = getIntent().getBooleanExtra("isExperience", false);

        String playListJsonString = getIntent().getStringExtra("playList");
        if(!TextUtils.isEmpty(playListJsonString))
        {
            Gson gson = new Gson();
            Type typeToken = new TypeToken<ArrayList<ProgramInfoBase>>(){}.getType();
            ArrayList<ProgramInfoBase> videoInfoArray = gson.fromJson(playListJsonString, typeToken);
            this.playList.addAll(videoInfoArray);

            if(this.playList.size() == 1)
            {
                ProgramInfoBase programInfoBase = this.playList.get(0);
                if("4".equalsIgnoreCase(programInfoBase.getMultiSetType()))
                {
                    this.isMultiSetType = true;
                    this.playlistName = programInfoBase.getName();
                }
            }
        }

        initView();

        needFinish = true;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                playVideo();
            }
        });
    }

    private void initView() {
        if (!TextUtils.isEmpty(playlistName)) {
            tvPlayListName.setText(playlistName);
        }
        //翻页重新添加
        flPlayListContent.removeAllViews();
        for (int i = 0; i < playList.size(); i++) {
            ProgramInfoBase dataBean = playList.get(i);

            FrameLayout item = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.item_song_play_list, null);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = (int) getResources().getDimension(R.dimen.dp70) * i;
            item.setLayoutParams(params);
            item.setId(i + 2000);
            flPlayListContent.addView(item);

            TextView tvSongIndex = item.findViewById(R.id.tv_song_index);
            tvSongIndex.setText(i + 1 + "");
            TextView tvSongName = item.findViewById(R.id.tv_song_name);
            tvSongName.setText(dataBean.getName());

            item.setOnClickListener(this);
            playListItem.add(item);

            item.findViewById(R.id.tv_free_tag).setVisibility(dataBean.isFree() ? View.VISIBLE : View.INVISIBLE);

            if (i == (playList.size() - 1)) {
                item.setNextFocusDownId(2000);
            }
            item.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                        flPlayList.setVisibility(View.GONE);
                        ivPlayerNext.requestFocus();
                    }
                    return false;
                }
            });

        }

        videoPlayerProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setTvTimeTagInfo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                XLog.d("onStartTrackingTouch", "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                XLog.d("onStopTrackingTouch", "onStopTrackingTouch");
            }
        });
    }

    /**
     * 监听焦点之前将设置为不可聚焦
     */
    private void clearSomeFocusBeforeListen() {
        tvProgressTag.setFocusable(false);
        vvJingling.setFocusable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isToShowBuy = false;
        // startXiri();
        if (hadCallBuyView) {
            hadCallBuyView = false;
            if (!HuyaApplication.hadBuy() && !isExperience) {
                finish();
            } else {
                playVideo();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        statusticsHandler.sendEmptyMessageDelayed(TagStartStatisticsPlay, 30 * 1000);
    }

    @Override
    protected void onStop() {
        try {
            if(vp != null){
                VPlayer vPlayer=(VPlayer) getHahaPlayer();
                if (vPlayer != null) {
                    statisticsVideoPlay(vPlayer.getCurrent()+ "",
                            vPlayer.getCurrent() + "");
                }
            }else {
            VideoView videoView = (VideoView) getHahaPlayer();
            if (videoView != null) {
                statisticsVideoPlay(videoView.getCurrentPosition() / 1000 + "",
                        videoView.getCurrentPosition() / 1000 + "");
            }}
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
//        startStatisticsPlay();
//        statusticsHandler.removeMessages(TagStartStatisticsPlay);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ContextWrapper(newBase) {
            @Override
            public Object getSystemService(String name) {
                if (Context.AUDIO_SERVICE.equals(name))
                    return getApplicationContext().getSystemService(name);
                return super.getSystemService(name);
            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            focusView = v;
        }
        if (v.getId() == R.id.video_player_progress) {
            if (hasFocus) {
                tvProgressTag.setVisibility(View.VISIBLE);
                setTvTimeTagInfo(videoPlayerProgress.getProgress());
            } else {
                tvProgressTag.setVisibility(View.GONE);
            }
            needQuickTime = hasFocus;

        } else if (v.getId() == R.id.iv_collect) {
            //todo
        } else if (hasFocus) {
            tvProgressTag.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if ((event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT)
                && videoPlayerProgress.isFocused()) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                quickSeekNow = true;
            } else {
                quickSeekNow = false;
            }
        } else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP
                && tvProgressTag.getVisibility() == View.GONE && flPlayListContent.getVisibility() == View.GONE) {
            videoPlayerProgress.requestFocus();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
            if (!needBuy()) {
                rlControl.setVisibility(View.VISIBLE);
                if (fileType == ConstantEnum.MediaType.video) {
                    videoPlayerProgress.requestFocus();
                } else {
                    ivPlayerPlay.requestFocus();
                }
                hahaPauseOrResumePlay();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (flPlayList.getVisibility() == View.VISIBLE) {
                flPlayList.setVisibility(View.GONE);
                ivPlayerList.requestFocus();
            } else {
                if (isExperience) {
                    isExperience = false;
                    showBuy("");
                    return true;
                }
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出播放", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    PlayVideoActivity.this.finish();
                    if(vp != null){
                        vp.destroy();
                    }

                }
            }
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) && videoPlayerProgress.isFocused()) {
            XLog.d("onKeyUp", "onKeyUp horizon");
            quickSeekNow = false;
            if (!needBuy()) {
                hahaQuickSeek((double) videoPlayerProgress.getProgress() / (double) videoPlayerProgress.getMax());
            }
        }
        boolean flag = super.onKeyUp(keyCode, event);
        if (rlControl.getVisibility() == View.GONE) {
            rlControl.setVisibility(View.VISIBLE);
            //ivPlayerPlay.requestFocus();
            if (fileType == ConstantEnum.MediaType.video) {
                videoPlayerProgress.requestFocus();
            } else {
                ivPlayerPlay.requestFocus();
            }
        }
        return flag;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_player_list:
                if (flPlayList.getVisibility() == View.GONE) {
                    flPlayList.setVisibility(View.VISIBLE);
                    setPlayListState();
                } else {
                    flPlayList.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_collect:
                collect();
                break;
            case R.id.iv_player_previous: {
                if (playingIndex == 0) {
                    playingIndex = this.playList.size();
                }
                playPre();
                break;
            }

            case R.id.iv_player_play:
                if (!needBuy()) {
                    rlControl.setVisibility(View.VISIBLE);
                    ivPlayerPlay.requestFocus();
                    hahaPauseOrResumePlay();
                }
                break;

            case R.id.iv_player_next: {
                if (playingIndex == (playList.size() - 1)) {
                    playingIndex = -1;
                }
                playNext();
                break;
            }
            default:
                if (playListItem.contains(view)) {
                    playingIndex = playListItem.indexOf(view);
                    playVideo();
                    setPlayListState();
                    flPlayList.setVisibility(View.GONE);
                    ivPlayerPlay.requestFocus();
                }
                break;
        }

    }

    void initPlayListWithMultisetType(final ProgramContent programContent)
    {
        playlistName = programContent.getName();
        if (!TextUtils.isEmpty(playlistName)) {
            tvPlayListName.setText(playlistName);
        }
        //翻页重新添加
        flPlayListContent.removeAllViews();
        for (int i = 0; i < playList.size(); i++) {
            VideoInfo dataBean = programContent.getVideos().get(i);

            FrameLayout item = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.item_song_play_list, null);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = (int) getResources().getDimension(R.dimen.dp70) * i;
            item.setLayoutParams(params);
            item.setId(i + 2000);
            flPlayListContent.addView(item);

            TextView indexView = item.findViewById(R.id.tv_song_index);
            indexView.setText(i + 1 + "");
            TextView nameTextView = item.findViewById(R.id.tv_song_name);
            nameTextView.setText(dataBean.getName());

            item.setOnClickListener(this);
            playListItem.add(item);

            item.findViewById(R.id.tv_free_tag).setVisibility(dataBean.getIsFree() == 1 ? View.VISIBLE : View.INVISIBLE);

            if (i == (playList.size() - 1)) {
                item.setNextFocusDownId(2000);
            }
            item.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                        flPlayList.setVisibility(View.GONE);
                        ivPlayerNext.requestFocus();
                    }
                    return false;
                }
            });
        }
    }

    private void setPlayListState() {
        for (int i = 0; i < playListItem.size(); i++) {
            View itemView = playListItem.get(i);
            if (itemView instanceof FrameLayout) { //歌曲
                TextView tvIndex = (TextView) itemView.findViewById(R.id.tv_song_index);
                TextView tvName = (TextView) itemView.findViewById(R.id.tv_song_name);
                if (playingIndex == i) {
                    tvIndex.setTextColor(getResources().getColor(R.color.yellow));
                    tvName.setTextColor(getResources().getColor(R.color.yellow));

                    itemView.requestFocus();
                } else {
                    tvIndex.setTextColor(getResources().getColor(R.color.white));
                    tvName.setTextColor(getResources().getColor(R.color.white));
                }
            } else if (itemView instanceof RelativeLayout) { //MV
                TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                TextView tvSingerName = (TextView) itemView.findViewById(R.id.tv_singer_name);
                if (playingIndex == i) {
                    tvTitle.setTextColor(getResources().getColor(R.color.yellow));
                    tvSingerName.setTextColor(getResources().getColor(R.color.yellow));

                    itemView.requestFocus();
                } else {
                    tvTitle.setTextColor(getResources().getColor(R.color.white));
                    tvSingerName.setTextColor(getResources().getColor(R.color.white));
                }
            }
        }
    }

    private void collect() {
        final Context context = this;

        if (playingIndex < playList.size()) {
            if (fileType == ConstantEnum.MediaType.video) {
                if(playingIndex >= 0 && playingIndex < this.playList.size())
                {
                    final ProgramInfoBase programInfoBase = this.playList.get(playingIndex);
                    if ("yes".equals(ifCollectton)) {
                        NetworkService.defaultService().userDeleteFavor(context, "1", String.valueOf(programInfoBase.getPkId()),
                                new JsonCallback<BaseResponse>() {
                                    @Override
                                    public void onSuccess(Response<BaseResponse> response) {
                                        BaseResponse beanBasic = response.body();
                                        if (beanBasic != null && beanBasic.isOk()) {
                                            ifCollectton="no";
                                            ivCollect.setImageResource(R.drawable.selector_player_collect_no);
                                            programInfoBase.setFavor(false);
                                        } else {
                                            HiFiDialogTools.getInstance().showtips(context, "取消收藏失败，请稍后重试", null);
                                        }
                                    }
                                });
                    } else {
                        NetworkService.defaultService().userAddFavor(context, "1",
                                programInfoBase.getPkId(), programInfoBase.getChannelId(),
                                new JsonCallback<BaseResponse>() {
                                    @Override
                                    public void onSuccess(Response<BaseResponse> response) {
                                        BaseResponse bean = response.body();
                                        if (bean != null && "success".equals(bean.getMessage())) {
                                            ifCollectton="yes";
                                            ivCollect.setImageResource(R.drawable.selector_player_collect_yes);
                                            programInfoBase.setFavor(true);
                                        } else {
                                            HiFiDialogTools.getInstance().showtips(context
                                                    , "收藏失败，请稍后重试", null);
                                        }
                                    }
                                });
                    }
                }
            }
        }
    }

    private void checkCollect() {
        NetworkService.defaultService().userCheckFavorStatus(this, this.currentProgramContent.getPkId() + "", new JsonCallback<BeanCheckCollect>() {
            @Override
            public void onSuccess(Response<BeanCheckCollect> response) {
                BeanCheckCollect bean = response.body();
                if (bean != null && "1".equals(bean.getCode())) {
                    ifCollectton = bean.getData().getIsCollect();
                    if ("yes".equals(ifCollectton)) {
                        ivCollect.setImageResource(R.drawable.selector_player_collect_yes);
                    } else {
                        ivCollect.setImageResource(R.drawable.selector_player_collect_no);
                    }
                }
            }
        });
    }

    @Override
    public void getHahaPlayerUrl(String vodID) {
        if (DiffConfig.CurrentPlatform== Platform.gzbn){
            if (platfromUtils.isFuMuLe2()){
                //donothing
            }else {
                setHahaPlayer(vvJingling);}
        }else {
            setHahaPlayer(vvJingling);
        }
        //String asset = DiffHostConfig.getMediaAsset((mvDetail != null ) ? mvDetail.getData(): null, (songDetail != null) ? songDetail.getSong() : null);
        super.getHahaPlayerUrl(vodID);
    }

    @Override
    public void setPlayTime(long nowTime, long allTime) {
        super.setPlayTime(nowTime, allTime);
        this.nowTime = nowTime;
        tvDurLeft.setText(Tools.secToTime(nowTime / 1000));
        tvDurRight.setText(Tools.secToTime(allTime / 1000));
        if (!quickSeekNow) {
            videoPlayerProgress.setProgress((int) (nowTime * videoPlayerProgress.getMax() / allTime));
        }
        gifVideoLoad.setVisibility(View.GONE);
        needBuy();
    }

    private boolean needBuy() {
        XLog.d(String.format("needBuy %s,freeTime %d, isExperience %s", HuyaApplication.hadBuy() ? "hadBuy" : "noneBuy",
                freeTime, isExperience ? "true" : "false"));

        final boolean isPurchased = DiffConfig.CurrentPurchase.isPurchased();
        if (freeTime >= 0) {
            if (nowTime / 1000 >= freeTime) {
                ProgramInfoBase programInfoBase = getCurrentProgram();
                if(programInfoBase != null)
                {
                    showBuy("" + programInfoBase.getPkId());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void showBuy(String vodID) {
        if (!isToShowBuy) {
            isToShowBuy = true;

            hahaPausePlay();
            super.showBuy(vodID);
            needFinish = true;

            hadCallBuyView = true;
        }
    }

    @Override
    public void hahaPlayEnd(float v) {
        super.hahaPlayEnd(v);
        playNext();
    }

    private void playNext() {
        playingIndex++;
        if (playingIndex >= playList.size()) {
            playingIndex = 0;
        }
        playVideo();
    }

    private void playPre() {
        playingIndex--;
        if (playingIndex < 0) {
            playingIndex = playList.size() - 1;
        }
        playVideo();
    }

    private void playVideo() {
        if(this.isMultiSetType)
        {
            ProgramInfoBase programInfoBase = this.playList.get(0);
            gifVideoLoad.setVisibility(View.VISIBLE);
            tvTitle.setText(programInfoBase.getName());
            fetchProgramContent(programInfoBase);
        }
        else
        {
            if (playingIndex >= playList.size()) {
                playingIndex = playList.size() - 1;
            }
            else if (playingIndex < 0) {
                playingIndex = 0;
            }

            ProgramInfoBase programInfoBase = getCurrentProgram();
            if(programInfoBase != null)
            {
                gifVideoLoad.setVisibility(View.VISIBLE);
                tvTitle.setText(programInfoBase.getName());
                fetchProgramContent(programInfoBase);
            }
        }

    }

    void playVideoJudge(final ProgramContent programContent)
    {
        if(programContent != null)
        {
            currentProgramContent = programContent;
            startStatisticsPlay();

            checkCollect();

            boolean shouldPay = false;
            freeTime = -1;
            if(!DiffConfig.CurrentPurchase.isPurchased())
            {
                if(!currentProgramContent.isFree())
                {
                    freeTime = currentProgramContent.getFreeSecond();
                    if(freeTime <= 0)
                    {
                        freeTime = -1;
                        shouldPay = true;
                    }

                }
            }
            showViewByHandler(videoPlayerProgress);
            if(shouldPay)
            {
                showBuy(currentProgramContent.getPkId() + "");
            }
            else
            {
                String mediaSourceUrl = currentProgramContent.getMediaSourceUrl();
                if(this.isMultiSetType)
                {
                    VideoInfo videoInfo = currentProgramContent.getVideos().get(this.playingIndex);
                    mediaSourceUrl = videoInfo.getMediaSourceUrl();

                }
                if(!TextUtils.isEmpty(mediaSourceUrl+""))
                {
                    getHahaPlayerUrl(mediaSourceUrl);
                }
                statisticsPlayId = "";
                statisticsVideoPlay("0", "0");
            }
        }
    }

    @Override
    public void playStateChange(int state) {
        super.playStateChange(state);
        ivPlayerPlay.setImageResource((state == PlayingStatePlaying) ? R.drawable.selector_player_stop : R.drawable.selector_player_play);
    }

    @Override
    public void hideInfoView() {
        super.hideInfoView();
        rlControl.setVisibility(View.GONE);
        flPlayList.setVisibility(View.GONE);
    }

    //进度条显示，快进快退，时间显示出来
    private void setTvTimeTagInfo(int progress) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp130),
                (int) getResources().getDimension(R.dimen.dp50));
        int[] location = new int[2];
        videoPlayerProgress.getLocationOnScreen(location);
        params.topMargin = location[1] - (int) getResources().getDimension(R.dimen.dp55);
        params.leftMargin = (int) (location[0] + (videoPlayerProgress.getWidth() - videoPlayerProgress.getPaddingEnd() -
                videoPlayerProgress.getPaddingStart()) * progress / 100.0f - getResources().getDimension(R.dimen.dp55));
        tvProgressTag.setLayoutParams(params);

        double timePercent = (double) videoPlayerProgress.getProgress() / (double) videoPlayerProgress.getMax();
        long time = (long) (timePercent * vodDur);
        tvProgressTag.setText(Tools.formatTime(time));
    }

    ProgramInfoBase getCurrentProgram()
    {
        if(this.isMultiSetType)
        {
            return this.playList.get(0);
        }
        else
        {
            if(playingIndex >= 0 && playingIndex < this.playList.size()) {
                return this.playList.get(playingIndex);
            }
        }
        return null;
    }

    //stat
    private void startStatisticsPlay() {
        final ProgramContent programContent = this.currentProgramContent;
        if (programContent != null) {
            try {
                String programId = "";
                String videoName = "";
                String videoId = "";
                String statName = "";
                programId = programContent.getPkId() + "";
                videoName = programContent.getName();
                videoId = String.valueOf(programContent.getVideoId());
                statName = "视频播放-" + videoName;
                stat(statName);
            } catch (Exception o) {
                o.printStackTrace();
            }
        }
    }
    private void statisticsVideoPlay(String playPoint, String playTime) {
        if(this.currentProgramContent == null)
        {
            XLog.e(getLocalClassName(), "currentProgramContent is null");
            return;
        }

        String programId = "";
        String programName = "";
        String channelId = "1";
        String channelName = AppConfig.AppName;
        String spId = "1";
        String spName = AppConfig.AppName;
        String videoName = "";
        String videoId = "";
        String multiSetType = "";
        long totalTime = 0;
        try {
            try {
                if (vp != null){
                    totalTime = (long) vp.getDuration();
                }else {
                    totalTime = (long)videoView.getDuration();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ProgramContent programContent = this.currentProgramContent;
        programId = programContent.getPkId() + "";
        videoName = programContent.getName();
        videoId = programContent.getVideoId() + "";
        programName = programContent.getName();
        channelId = programContent.getChannelId() + "";
        multiSetType = programContent.getMultiSetType();

        try{
            NetworkService.defaultService().userPlayRecord(this, playPoint, videoId, videoName, programId, programName, channelId, multiSetType, totalTime);
            NetworkService.defaultService().statisticsVideo(this, playPoint, videoId, videoName,
                    spId, spName,
                    programId, programName, channelId, channelName,
                    multiSetType, "0", DiffConfig.CurrentPurchase.isPurchased() ? "1" : "0",
                    AppConfig.VipCode, statisticsVideoId, playTime, totalTime, new JsonCallback<BaseResponse<BeanStatistics>>() {
                        @Override
                        public void onSuccess(Response<BaseResponse<BeanStatistics>> response) {
                            BaseResponse<BeanStatistics> beanStatistics = response.body();
                            if (beanStatistics != null && beanStatistics.isOk()) {
                                statisticsVideoId = beanStatistics.getData().getId();
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    *** network
     */
    void fetchProgramContent(final ProgramInfoBase programInfoBase)
    {
        NetworkService.defaultService().fetchProgramContent(this, programInfoBase.getPkId(), programInfoBase.getMultiSetType(), programInfoBase.getChannelId(),
                new JsonCallback<BaseResponse<ProgramContent>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
                        BaseResponse<ProgramContent> data = response.body();
                        if(data.isOk())
                        {
                            final ProgramContent programContent = data.getData();
                            if(programContent.getMultiSetType().equalsIgnoreCase("4"))
                            {
                                initPlayListWithMultisetType(programContent);
                            }
                            playVideoJudge(programContent);
                        }
                        else
                        {
                            playNext();
                        }
                    }
                });
    }
}
