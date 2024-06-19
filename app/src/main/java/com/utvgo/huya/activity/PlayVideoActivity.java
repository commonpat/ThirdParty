package com.utvgo.huya.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
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

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.bean.BeanGetPlayPoint;
import com.utvgo.handsome.bean.BeanMvPlayPoint;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.diff.Platform;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.TopWayBroacastUtils;
import com.utvgo.handsome.utils.XLog;
import com.utvgo.huya.BuildConfig;
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
import com.utvgo.huya.utils.TWSyncHelper;
import com.utvgo.huya.utils.ToastUtil;
import com.utvgo.huya.utils.Tools;
import com.utvgo.huya.utils.ViewUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.media.MediaPlayer.MEDIA_INFO_BUFFERING_END;
import static android.media.MediaPlayer.MEDIA_INFO_BUFFERING_START;
import static com.utvgo.huya.constant.ConstantEnumHuya.Asset_Id;
import static com.utvgo.huya.constant.ConstantEnumHuya.Category_Id;
import static com.utvgo.huya.constant.ConstantEnumHuya.ORDER;
import static com.utvgo.huya.constant.ConstantEnumHuya.PLAYER;
import static com.utvgo.huya.utils.TWSyncHelper.DKV_TYPE_FAVORITES;

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
    RelativeLayout gifVideoLoad;
    @BindView(R.id.tv_progress_tag)
    TextView tvProgressTag;

    @BindView(R.id.vv_jingling)
    VideoView vvJingling;
    @BindView(R.id.image_loading)
    ImageView imageLoading;

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
    private boolean isHistory = false;
    boolean isMultiSetType = false;
    private int multiSetTypeSize = 0;
    private boolean isCacheing = false;
    private boolean isFirstPlay = true;
    private boolean isFirstPage = true;
    private int position = 0;

    private long exitTime = 0;
    private String ifCollectton = "no";
    private int collecttonId = 0;

    ProgramContent currentProgramContent;

    private static final int TagStartStatisticsPlay = 10011;
    private String statisticsPlayId = "";
    private String statisticsVideoId = "";
    private boolean isFree = false;
    private String  name= "";
    private String contentMidTopWay = "";
    private boolean isFirstPlayPoint = true;
    private String vodId = "";
    private int programId = 0;
    private int videoId = 0;
    private String videoName = "";

    private String mediaSourceUrl = "";
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
        initPlayData();

    }
    private void initPlayData(){
        vodId = getIntent().getStringExtra("vodId");

        if(!TextUtils.isEmpty(vodId)){
            NetworkService.defaultService().fetchProgramContent(this, 0,
                    "0", 0,vodId,new JsonCallback<BaseResponse<ProgramContent>>() {
                        @Override
                        public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
                            BaseResponse<ProgramContent> data = response.body();
                            Log.d(TAG, "onSuccess: "+data.getData().toString());
                            if (data.isOk()) {
                                ArrayList<ProgramInfoBase> list = new ArrayList<>();
                                list.add(data.getData());
                                playList.addAll(list);
                                if (playList.size() == 1) {
                                    try {
                                        ProgramInfoBase programInfoBase = playList.get(0);
                                        if ("4".equalsIgnoreCase(programInfoBase.getMultiSetType())) {
                                            isMultiSetType = true;
                                            playlistName = programInfoBase.getName();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
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
                        }
                    });
        }else {

            playingIndex = getIntent().getIntExtra("playIndex", 0);
            playlistName = getIntent().getStringExtra("playlistName");
            isExperience = getIntent().getBooleanExtra("isExperience", false);
            isHistory = getIntent().getBooleanExtra("isHistory", false);
            if(isHistory){
                ivPlayerList.setVisibility(View.INVISIBLE);
            }
            String playListJsonString = getIntent().getStringExtra("playList");
            Log.d(TAG, "onCreate: " + playListJsonString);
            if (!TextUtils.isEmpty(playListJsonString)) {
                Gson gson = new Gson();
                Type typeToken = new TypeToken<ArrayList<ProgramInfoBase>>() {
                }.getType();
                ArrayList<ProgramInfoBase> videoInfoArray = gson.fromJson(playListJsonString, typeToken);
                this.playList.addAll(videoInfoArray);

                if (this.playList.size() == 1) {
                    try {
                        ProgramInfoBase programInfoBase = this.playList.get(0);
                        if ("4".equalsIgnoreCase(programInfoBase.getMultiSetType())) {
                            this.isMultiSetType = true;
                            this.playlistName = programInfoBase.getName();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
    }
    @SuppressLint("ResourceType")
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
            item.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    ViewUtil.runText(view.findViewById(R.id.tv_song_name),b);
                }
            });
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
        DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
            @Override
            public void onFinished(String message) {

            }
        });
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
//        try {
//           {
//            VideoView videoView = (VideoView) getHahaPlayer();
//            if (videoView != null) {
//                statisticsVideoPlay(videoView.getCurrentPosition() / 1000 + "",
//                        videoView.getCurrentPosition() / 1000 + "");
//            }}
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        super.onStop();
    //   startStatisticsPlay();
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
        if (rlControl.getVisibility() == View.GONE) {
            rlControl.setVisibility(View.VISIBLE);
            //ivPlayerPlay.requestFocus();

              videoPlayerProgress.requestFocus();

        }
        if ((event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT)
                && videoPlayerProgress.isFocused()) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                quickSeekNow = true;
                if (videoView != null) {
                    videoView.pause();
                }
                playingState = PlayingStatePause;
                playStateChange(PlayingStatePause);
                timeHandler.removeMessages(PLAY_TIME);
            }else if(event.getAction()==KeyEvent.ACTION_UP){
                if(quickSeekNow){
                    quickSeekNow =false;
                }
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
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER||keyCode==KeyEvent.KEYCODE_MEDIA_PAUSE||keyCode== KeyEvent.KEYCODE_MEDIA_PLAY) {
            if(quickSeekNow){
                quickSeekNow = false;
                timeHandler.removeCallbacksAndMessages(null);
                hahaQuickSeek((double) videoPlayerProgress.getProgress() / (double) videoPlayerProgress.getMax());
                timeHandler.sendEmptyMessage(PLAY_TIME);
                timeHandler.removeMessages(7);
                timeHandler.sendEmptyMessageDelayed(7, 8 * 1000);
                //return true;
            }
            if (!needBuy()) {
                rlControl.setVisibility(View.VISIBLE);
                if (fileType == ConstantEnum.MediaType.video) {
                    videoPlayerProgress.requestFocus();
                } else {
                    ivPlayerPlay.requestFocus();
                }
                hahaPauseOrResumePlay();
                if(playingState == PlayingStatePlaying){
                    TopWayBroacastUtils.getInstance().playEvent(this, asset, "RESUME", assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
                }else if(playingState == PlayingStatePause){
                    TopWayBroacastUtils.getInstance().playEvent(this, asset, "PAUSE", assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
                }
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
                    TopWayBroacastUtils.getInstance().playEvent(this, asset, "STOP", assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
                    PlayVideoActivity.this.finish();

                }
            }
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) && videoPlayerProgress.isFocused()) {
            XLog.d("onKeyUp", "onKeyUp horizon");
            if (!needBuy()) {
                hahaQuickSeek((double) videoPlayerProgress.getProgress() / (double) videoPlayerProgress.getMax());
                if (videoView != null) {
                    videoView.start();
                }
                playingState = PlayingStatePlaying;
                playStateChange(PlayingStatePlaying);
                timeHandler.removeMessages(PLAY_TIME);
                timeHandler.sendEmptyMessage(PLAY_TIME);

                if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT ){
                    TopWayBroacastUtils.getInstance().playEvent(this, asset, "FAST_BACK", assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
                }else if( keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
                    TopWayBroacastUtils.getInstance().playEvent(this, asset, "FAST_FORWARD", assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
                }
            }

            quickSeekNow = false;
        }else if(keyCode == KeyEvent.KEYCODE_FORWARD){
            if(quickSeekNow){
                return true;
            }
            quickSeekNow = true;
            TopWayBroacastUtils.getInstance().playEvent(this, asset, "FAST_FORWARD", assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
            if (!needBuy()) {
                rlControl.setVisibility(View.VISIBLE);
                videoPlayerProgress.requestFocus();
                hahaPauseOrResumePlay();
            }
            timeHandler.removeMessages(PLAY_TIME);
            timeHandler.sendEmptyMessage(10);
        }else if(keyCode == KeyEvent.KEYCODE_MEDIA_REWIND){
            if(quickSeekNow){
                return true;
            }
            quickSeekNow = true;
            TopWayBroacastUtils.getInstance().playEvent(this, asset, "FAST_BACK", assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
            if (!needBuy()) {
                rlControl.setVisibility(View.VISIBLE);
                videoPlayerProgress.requestFocus();
                hahaPauseOrResumePlay();
            }
            timeHandler.removeMessages(PLAY_TIME);
            timeHandler.sendEmptyMessage(11);
        }
        boolean flag = super.onKeyUp(keyCode, event);
//        if (rlControl.getVisibility() == View.GONE) {
//            rlControl.setVisibility(View.VISIBLE);
//            //ivPlayerPlay.requestFocus();
//            if (fileType == ConstantEnum.MediaType.video) {
//                videoPlayerProgress.requestFocus();
//            } else {
//                ivPlayerPlay.requestFocus();
//            }
//        }
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
//                if (playingIndex == 0) {
//                    playingIndex = this.playList.size();
//                }
                playPre();
                break;
            }

            case R.id.iv_player_play:
                if (!needBuy()) {
                    rlControl.setVisibility(View.VISIBLE);
                    ivPlayerPlay.requestFocus();
                    hahaPauseOrResumePlay();
                    if(playingState == PlayingStatePlaying){
                        TopWayBroacastUtils.getInstance().playEvent(this, asset, "RESUME", assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
                    }else if(playingState == PlayingStatePause){
                        TopWayBroacastUtils.getInstance().playEvent(this, asset, "PAUSE", assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
                    }
                }
                break;

            case R.id.iv_player_next: {
//                if (playingIndex == (playList.size() - 1)) {
//                    playingIndex = -1;
//                }
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

    @SuppressLint("ResourceType")
    void initPlayListWithMultisetType(final ProgramContent programContent)
    {
        playlistName = programContent.getName();
        if (!TextUtils.isEmpty(playlistName)) {
            tvPlayListName.setText(playlistName);
        }
        if(programContent.getVideos()!= null&&programContent.getVideos().size()>0){
            tvTitle.setText(programContent.getVideos().get(playingIndex).getName());

        }        //翻页重新添加
        flPlayListContent.removeAllViews();
        playListItem.clear();
        for (int i = 0; i < programContent.getVideos().size(); i++) {
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
            item.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    ViewUtil.runText(view.findViewById(R.id.tv_song_name),b);
                }
            });
            playListItem.add(item);

            item.findViewById(R.id.tv_free_tag).setVisibility(dataBean.getIsFree() == 1 ? View.VISIBLE : View.INVISIBLE);

            if (i == (programContent.getVideos().size() - 1)) {
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

        if (playingIndex < playListItem.size()) {
            if (fileType == ConstantEnum.MediaType.video) {
                if(playingIndex >= 0 && playingIndex < this.playListItem.size())
                {   final ProgramInfoBase programInfoBase ;
                    VideoInfo videoInfo;
                    int videoId = 0;
                    String videoName = "";
                    String programName = "";
                    int pkId = 0;
                    int channelId = 0;
                    if(this.isMultiSetType) {
                        programInfoBase = this.playList.get(0);
                        videoInfo = this.currentProgramContent.getVideos().get(playingIndex);
                        videoId = videoInfo.getVideoId();
                        videoName = videoInfo.getName();
                        pkId = this.currentProgramContent.getPkId();
                        channelId = this.currentProgramContent.getChannelId();
                        programName = this.currentProgramContent.getName();
                        imgurl = this.currentProgramContent.getImageSmall();
                    }else {
                        programInfoBase = this.playList.get(playingIndex);
                        videoId = this.currentProgramContent.getVideoId();
                        videoName = this.currentProgramContent.getName();
                        pkId = this.currentProgramContent.getPkId();
                        channelId = this.currentProgramContent.getChannelId();
                        programName = this.currentProgramContent.getName();
                        imgurl = this.currentProgramContent.getImageSmall();
                    }

                    if ("yes".equals(ifCollectton)) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                TWSyncHelper.deleteDataByType(getBaseContext(),DKV_TYPE_FAVORITES,asset);

                            }
                        }).start();
                        TopWayBroacastUtils.getInstance().collectEvent(context,1,asset,assetName,Asset_Id,Category_Id);
                        NetworkService.defaultService().userDeleteFavor(context, "1", String.valueOf(collecttonId),
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
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                JSONObject insertValue = new JSONObject();
                                insertValue.put("user_id", Tools.getStringPreference(getBaseContext(),"user_id"));
                                insertValue.put("name", assetName);
                                insertValue.put("cp_video_id", asset);
                                insertValue.put("url", DiffConfig.generateImageUrl(imgurl));
                                TWSyncHelper.insertData(getBaseContext(),DKV_TYPE_FAVORITES,insertValue);

                            }
                        }).start();
                        TopWayBroacastUtils.getInstance().collectEvent(this,0,asset,assetName,Asset_Id,Category_Id);
                        NetworkService.defaultService().userAddFavor(context, "1",
                                pkId,videoId, channelId,videoName,programName, programInfoBase.getMultiSetType(),
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
                    collecttonId = bean.getData().getId();
                    Log.d(TAG, "onSuccess: checkCollect: "+ifCollectton);
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
        imageLoading.setVisibility(View.INVISIBLE);
        setHahaPlayer(vvJingling);
        vvJingling.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "onInfo:player what"+what+"____"+extra);
                isCacheing = true;
                if(what == MEDIA_INFO_BUFFERING_START){
                    hahaPauseOrResumePlay();
                    imageLoading.setVisibility(View.VISIBLE);
                    return true;

                }else if(what == MEDIA_INFO_BUFFERING_END){
                    imageLoading.setVisibility(View.INVISIBLE);
                    hahaPauseOrResumePlay();
                    return true;
                }else {
                    imageLoading.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });


       // asset = DiffConfig.getMediaAsset((mvDetail != null ) ? mvDetail.getMv() : null, (songDetail != null) ? songDetail.getSong() : null);
        //assetName = (mvDetail != null ) ? mvDetail.getMv().getName() : "QQ音乐";
        //String asset = DiffHostConfig.getMediaAsset((mvDetail != null ) ? mvDetail.getData(): null, (songDetail != null) ? songDetail.getSong() : null);
        super.getHahaPlayerUrl(vodID);
    }

    @Override
    public void setPlayTime(long nowTime, long allTime) {
        super.setPlayTime(nowTime, allTime);
        this.nowTime = nowTime;
        tvDurLeft.setText(Tools.secToTime(nowTime / 1000));
        tvDurRight.setText(Tools.secToTime(allTime / 1000));
        tvProgressTag.setText(Tools.secToTime(nowTime / 1000));
        videoPlayerProgress.setProgress((int) (nowTime * videoPlayerProgress.getMax() / allTime));
//        if (!quickSeekNow) {
//            Log.d(TAG, "setPlayTime: "+(int) (nowTime * videoPlayerProgress.getMax() / allTime)  +" currenttime:"+  nowTime+" " +allTime);
//            videoPlayerProgress.setProgress((int) (nowTime * videoPlayerProgress.getMax() / allTime));
//        }
       // gifVideoLoad.setVisibility(View.GONE);
        String imgurl = currentProgramContent.getImageSmall();
        if(this.isMultiSetType){
            try {
                imgurl = currentProgramContent.getVideos().get(playingIndex).getImageSmall();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        twSaveHistory(DiffConfig.generateImageUrl(imgurl));
        recordPlayTime();
        needBuy();
    }

    private boolean needBuy() {

        XLog.d(String.format("needBuy %s,freeTime %d, isExperience %s", HuyaApplication.hadBuy() ? "hadBuy" : "noneBuy",
                freeTime, isExperience ? "true" : "false"));

        final boolean isPurchased = DiffConfig.CurrentPurchase.isPurchased();

            if (freeTime >= 0) {
                if (nowTime / 1000 >= freeTime) {
                    ProgramInfoBase programInfoBase = getCurrentProgram();
                    if (programInfoBase != null) {
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
            stat("弹出订购-"+playingTitle,ORDER);
            super.showBuy(vodID);
            needFinish = true;

            hadCallBuyView = true;
        }
    }

    @Override
    public void hahaPlayEnd(float v) {
        super.hahaPlayEnd(v);
        statisticsVideoPlay("0","0");
        playNext();
    }

    private void playNext() {
        playingIndex++;
        if(this.isMultiSetType){
            if(playingIndex >= multiSetTypeSize){
                playingIndex = 0;
            }
        }else if(playingIndex >= playList.size()) {
            playingIndex = 0;
            nextAssetName = playList.get(playingIndex).getName();
        }
        try {
            if (videoView != null) {
                videoView.stopPlayback();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TopWayBroacastUtils.getInstance().playEvent(this,asset,"NEXT",assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
        playVideo();
    }

    private void playPre() {
        playingIndex--;
        if(this.isMultiSetType){
            if(playingIndex < 0){
                playingIndex = multiSetTypeSize - 1;
            }
        }else if (playingIndex < 0) {
            playingIndex = playList.size() - 1;
        }
        try {
            if (videoView != null) {
                videoView.stopPlayback();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TopWayBroacastUtils.getInstance().playEvent(this,asset,"PREVIOUS",assetName,String.valueOf(vodDur/1000),String.valueOf(vodPayingTime * timeStep/1000),Asset_Id,Category_Id);
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
            }else {
                fetchProgramContent(vodId);
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
                isFree = currentProgramContent.isFree();
                if(!currentProgramContent.isFree())
                {  try{
                    if(this.isMultiSetType) {
                        if(this.playingIndex>currentProgramContent.getVideos().size()){
                            playingIndex = 0;
                        }
                        freeTime = currentProgramContent.getVideos().get(playingIndex).getFreeSecond();
                    }else {
                        freeTime = currentProgramContent.getFreeSecond();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                    if(freeTime <= 0)
                    {
                        freeTime = -1;
                        shouldPay = true;

                    }

                }
            }
            showViewByHandler(videoPlayerProgress);
            playingTitle = "视频-"+currentProgramContent.getName();

            if(shouldPay)
            {
                showBuy(currentProgramContent.getPkId() + "");
            }
            else
            {
                mediaSourceUrl = currentProgramContent.getMediaSourceUrl();

                oldAsset = asset;
                oldAssetName = assetName;
                asset = currentProgramContent.getMediaSourceUrl();
                assetName = currentProgramContent.getName();
                programId = currentProgramContent.getPkId();
                videoId = currentProgramContent.getVideoId();
                if(this.isMultiSetType)
                {
                    if(playingIndex>currentProgramContent.getVideos().size()){
                      playingIndex = 0;
                    }
                    VideoInfo videoInfo = currentProgramContent.getVideos().get(this.playingIndex);
                    mediaSourceUrl = videoInfo.getMediaSourceUrl();
                    videoId = videoInfo.getVideoId();
                    assetName = videoInfo.getName();
                    asset = videoInfo.getMediaSourceUrl();

                }
                if(!TextUtils.isEmpty(mediaSourceUrl+""))
                {
                    continue2Play(mediaSourceUrl,programId,videoId);
                }else {
                    if(playingIndex>currentProgramContent.getVideos().size()){
                        playingIndex = 0;
                    }
                    VideoInfo videoInfo = currentProgramContent.getVideos().get(playingIndex);
                    mediaSourceUrl = videoInfo.getMediaSourceUrl();
                    videoId = videoInfo.getVideoId();
                    assetName = videoInfo.getName();
                    asset = videoInfo.getMediaSourceUrl();
                    if(!TextUtils.isEmpty(mediaSourceUrl)){
                        continue2Play(mediaSourceUrl,programId,videoId);
                    }

                }
                statisticsPlayId = "";
                statisticsVideoPlay("", "0");
            }
        }
    }

    @Override
    public void playStateChange(int state) {
        super.playStateChange(state);
        if (state == PlayingStatePlaying) {
            if((!quickSeekNow&&!isToShowBuy&&!isCacheing)||isFirstPlay) {
                isFirstPlay = false;
                // TopWayBroacastUtils.getInstance().playEvent(this, asset, "PLAY", assetName, vodDur / 1000 + "", String.valueOf(vodPayingTime * timeStep / 1000), Asset_Id, Category_Id);
            }
            ivPlayerPlay.setImageResource(R.drawable.selector_player_stop);
        } else {
            if(!quickSeekNow&&!isToShowBuy&&!isCacheing) {
                // TopWayBroacastUtils.getInstance().playEvent(this, asset, "PAUSE", assetName, vodDur / 1000 + "", String.valueOf(vodPayingTime * timeStep / 1000), Asset_Id, Category_Id);
            }
            ivPlayerPlay.setImageResource(R.drawable.selector_player_play);
        }
        isCacheing = false;
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
                videoPlayerProgress.getPaddingStart()) * progress / ProgressMax - getResources().getDimension(R.dimen.dp55));
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
                stat(statName,PLAYER);
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


        String programName = "";
        String channelId = "1";
        String channelName = AppConfig.AppName;
        String spId = "1";
        String spName = AppConfig.AppName;
        String videoName = "";
        String multiSetType = "";
        long totalTime = 0;
        try {
            try {
                totalTime = (long)videoView.getDuration();
                if(TextUtils.isEmpty(playPoint)){
                    playPoint = "0";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ProgramContent programContent = this.currentProgramContent;


        programName = programContent.getName();
        channelId = programContent.getChannelId() + "";
        multiSetType = programContent.getMultiSetType();

        try{
            NetworkService.defaultService().userPlayRecord(this, playPoint, videoId+"", assetName, programId+"", programName, channelId, multiSetType, totalTime,new JsonCallback<BaseResponse>() {
                @Override
                public void onSuccess(Response<BaseResponse> response) {
                    Log.d("userPlayRecord", "onSuccess: "+response.body());
                }

                @Override
                public void onError(Response<BaseResponse> response) {
                    Log.d("userPlayRecord", "onSuccess: "+response.toString());
                }

                @Override
                public void onFinish() {
                    Log.d("userPlayRecord", "onSuccess: hhhhhhhhhhh");
                }
            });
            NetworkService.defaultService().statisticsVideo(this, playPoint, videoId+"", assetName,
                    spId, spName,
                    programId+"", programName, channelId, channelName,
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
        NetworkService.defaultService().fetchProgramContent(this, programInfoBase.getPkId(), programInfoBase.getMultiSetType(), programInfoBase.getChannelId(),"",
                new JsonCallback<BaseResponse<ProgramContent>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
                        BaseResponse<ProgramContent> data = response.body();
                        try {
                            if (data.isOk()) {
                                final ProgramContent programContent = data.getData();
                                if (programContent.getMultiSetType().equalsIgnoreCase("4")) {
                                    isMultiSetType = true;
                                    initPlayListWithMultisetType(programContent);
                                }
                                if(programContent.getVideos()!=null&&programContent.getVideos().size()>0){
                                    isMultiSetType = true;
                                    multiSetTypeSize = programContent.getVideos().size();
                                }
                                playVideoJudge(programContent);
                            } else {
                                playNext();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
    void fetchProgramContent(String assetId)
    {
        NetworkService.defaultService().fetchProgramContent(this, 0, "", 0,assetId,
                new JsonCallback<BaseResponse<ProgramContent>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
                        BaseResponse<ProgramContent> data = response.body();
                        try {
                            if (data.isOk()) {
                                final ProgramContent programContent = data.getData();
                                if (programContent.getMultiSetType().equalsIgnoreCase("4")) {
                                    isMultiSetType = true;
                                    initPlayListWithMultisetType(programContent);
                                }
                                if(programContent.getVideos()!=null&&programContent.getVideos().size()>0){
                                    isMultiSetType = true;
                                    multiSetTypeSize = programContent.getVideos().size();
                                }
                                playVideoJudge(programContent);
                            } else {
                                playNext();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
    @Override
    protected void onDestroy() {
        try {
            if(videoView!=null) {
                videoView.stopPlayback();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onDuration(long l) {
        super.onDuration(l);
        Log.d(TAG, "onDuration: "+position);

        if(position>0){
            int p = position*1000;
            if(p<l) {
                Double percent = ((double) p / (double) vodDur);
                Log.d(TAG, "onDuration: percent" + percent + " position:" + position);
                videoPlayerProgress.setProgress((int) (percent * ProgressMax));

                timeHandler.removeMessages(PLAY_TIME);
                timeHandler.removeMessages(0);
                timeHandler.sendEmptyMessage(PLAY_TIME);
                vodPayingTime = p / timeStep;
                if (videoView != null) {
                    isSeeking = true;
                    final int seekPoint = (int) vodPayingTime * 200;
                    videoView.seekTo(seekPoint);
                    XLog.d("hahaQuickSeek videoView seek to point " + seekPoint + " total time " + videoView.getDuration());
                }


                // hahaQuickSeek((double) videoPlayerProgress.getProgress() / (double) videoPlayerProgress.getMax());
                position = 0;
            }
        }
        gifVideoLoad.setVisibility(View.GONE);
//        ProgressMax =  (l/1000)/ProgressStep*100;
//        ProgressTimes  =  ((l/1000)/ProgressStep);
//
//        videoPlayerProgress.setMax((int) ProgressMax);
        if(!"".equals(oldAsset)&&!oldAsset.equals(asset)){
            TopWayBroacastUtils.getInstance().playEvent(this, oldAsset, "STOP", oldAssetName, vodDur / 1000 + "", String.valueOf(vodPayingTime * timeStep / 1000), Asset_Id, Category_Id);
        }
        TopWayBroacastUtils.getInstance().playEvent(this, asset, "PLAY", assetName, vodDur / 1000 + "", String.valueOf(vodPayingTime * timeStep / 1000), Asset_Id, Category_Id);

        int length = (int) (l/(30 * 60 * 1000));
        if(length>0){
            SeekStep = 80;
        }else {
            SeekStep = 15;
        }
    }

    private void recordPlayTime(){
        long ticket = System.currentTimeMillis();
        if(ticket - recordTicket > 5000){
            recordTicket = ticket;
            final String point = nowTime/1000+"";
            final String time = nowTime/1000+"";
            Log.d(TAG, "huya-user recordPlayTime: "+point);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    statisticsVideoPlay(point,time);
                    updatePlayPoint(point);
                }
            }).start();
            Log.d(TAG, "recordPlayTime: time:"+time+"  recordTicket:"+recordTicket);
        }

    }
    private void updatePlayPoint(String playPoint){
        Log.d(TAG, "updatePlayPoint: asset:"+asset+"programId:"+programId +"videoId:"+videoId+"  playPoint:"+playPoint);
        NetworkService.defaultService().updateUserPlayPoint(PlayVideoActivity.this, contentMidTopWay, playPoint,programId,videoId,new JsonCallback<com.utvgo.handsome.bean.BaseResponse>() {
            @Override
            public void onSuccess(Response<com.utvgo.handsome.bean.BaseResponse> response) {

            }
        });
       // asyncHttpRequest.updateUserPlayPoint(PlayVideoActivity.this,contentMidTopWay,playPoint,this,this);
    }
    private void getUserPlayPoint(String contentMid,int programId,int videoId){
        contentMidTopWay = contentMid;
        NetworkService.defaultService().getUserPlayPoint(PlayVideoActivity.this, contentMid, programId,videoId,new JsonCallback<BeanGetPlayPoint>() {
            @Override
            public void onSuccess(Response response) {
                BeanGetPlayPoint beanGetPlayPoint = (BeanGetPlayPoint) response.body();
                if (isFirstPlayPoint) {
                    isFirstPlayPoint = false;
                    if(position == 0) {
                        if (beanGetPlayPoint != null && TextUtils.equals(beanGetPlayPoint.getCode(), "1")) {
                            try {
                                position = beanGetPlayPoint.getData().getPlayPoint();
                                getHahaPlayerUrl(mediaSourceUrl);
                            } catch (Exception e) {
                                e.printStackTrace();
                                position = 0;
                                getHahaPlayerUrl(mediaSourceUrl);
                            }
                        }else {
                            position = 0;
                            getHahaPlayerUrl(mediaSourceUrl);
                        }
                    }
                }else {
                    position = 0;
                    getHahaPlayerUrl(mediaSourceUrl);
                }
            }

            @Override
            public void onError(Response<BeanGetPlayPoint> response) {
                super.onError(response);
                position = 0;
                getHahaPlayerUrl(mediaSourceUrl);
                Log.d(TAG, "huya-usergetUserPlayPoint onError: ");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Log.d(TAG, "huya-usergetUserPlayPoint onFinish: ");
            }
        });
       // asyncHttpRequest.getUserPlayPoint(PlayVideoActivity.this,contentMid,this,this);
    }
    protected void twSaveHistory(final String posterUrl){
        long ticket = System.currentTimeMillis();
        if((ticket - tvSaveHistoryTicket)>5000)
        {
            tvSaveHistoryTicket = ticket;
            final Context context = this;
            final long lastPosition = vodPayingTime * timeStep/1000;
            final int duration = (int)(vodDur / 1000);
            final String userId = Tools.getStringPreference(getBaseContext(), "user_id");
            final String cpVideoId = asset;

            TWSyncHelper.twSavePlayHistoryItem(context, lastPosition, duration, userId, assetName, cpVideoId, posterUrl);
            Log.d(TAG, "twSaveHistory: " + assetName + " "+ lastPosition+" "+posterUrl);

        }
    }
    private void continue2Play(String vodId,int programId,int videoId){
        getUserPlayPoint(vodId,programId,videoId);

    }
}
