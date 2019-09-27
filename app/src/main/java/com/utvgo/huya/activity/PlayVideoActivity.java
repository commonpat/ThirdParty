package com.utvgo.huya.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
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

import com.utvgo.huya.GlideApp;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanBasic;
import com.utvgo.huya.beans.BeanSongDetail;
import com.utvgo.huya.beans.BeanStatistics;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.beans.BeanVideoDetailZero;
import com.utvgo.huya.constant.ConstantEnum;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ImageTool;
import com.utvgo.huya.utils.NetUtils;
import com.utvgo.huya.utils.ToastUtil;
import com.utvgo.huya.utils.Tools;
import com.utvgo.huya.utils.XLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by fute on 17/1/9.
 */

public class PlayVideoActivity extends BuyActivity {

    @BindView(R.id.ll_play_quick)
    LinearLayout ll_play_quick;
    @BindView(R.id.tv_quick_info)
    TextView tv_quick_info;
    @BindView(R.id.iv_quick_icon)
    ImageView iv_quick_icon;
    @BindView(R.id.iv_video_bg)
    ImageView ivVideoBg;
    @BindView(R.id.iv_player_play)
    ImageView ivPlayerPlay;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.iv_change_mv)
    ImageView ivChangeMv;
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
    //    @BindView(R.id.lrcView)
//    LrcView lrcView;
    @BindView(R.id.iv_head)
    ImageView ivHead;
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
    @BindView(R.id.tv_buy_tip)
    TextView tvBuyTip;
    @BindView(R.id.vv_jingling)
    VideoView vvJingling;

    private int playingIndex = 0;
    private String playlistName = "";
    private ArrayList<BeanUserPlayList.DataBean> playList = new ArrayList<>();

    private BeanSongDetail songDetail;
    private BeanVideoDetailZero mvDetail;
    private boolean playMVBySong = false;
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

    private long exitTime = 0;

    public static void play(final Context context,
                            final ConstantEnum.MediaType mediaType,
                            final boolean isSingle,
                            final String buySingleName,
                            final ArrayList<BeanUserPlayList.DataBean> data,
                            final int defaultPlayPosition,
                            final boolean isFree)
    {
        Intent intent = new Intent(context, PlayVideoActivity.class);
        intent.putExtra("isSingle", isSingle);
        intent.putExtra("playIndex", defaultPlayPosition);
        intent.putExtra("fileType", mediaType.ordinal());
        intent.putExtra("isExperience", isFree);
        intent.putParcelableArrayListExtra("playList", data);
        if (isSingle) {
            intent.putExtra("buySingleName", buySingleName);
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
        //setContentType(mvBean.getMultiSetType());//单集多集
        //setContentId(mvBean.getPkId());//pkgId
        //MediaType(mvBean.getChannelId());//channelId
        playList = getIntent().getParcelableArrayListExtra("playList");
        fileType = getIntent().getIntExtra("fileType", 1);
        playingIndex = getIntent().getIntExtra("playIndex", 0);
        playlistName = getIntent().getStringExtra("playlistName");
        buySingle = getIntent().getBooleanExtra("isSingle", false);
        isExperience = getIntent().getBooleanExtra("isExperience", false);

        if (buySingle) {
            if (getIntent().hasExtra("buySingleName")) {
                buySingleName = getIntent().getStringExtra("buySingleName");
            }
        }
        playVideo();
        initView();

        ivChangeMv.setVisibility(View.INVISIBLE);
        tvBuyTip.setVisibility(View.GONE);
        if (fileType == 0) {//音频
            ivVideoBg.setImageResource(R.mipmap.bg);
            //lrcView.setVisibility(View.VISIBLE);
            ivHead.setVisibility(View.VISIBLE);
            gifVideoLoad.setVisibility(View.GONE);
            //     videoPlayerProgress.requestFocus();
//            videoPlayerProgress.setClickable(false);
//            videoPlayerProgress.setEnabled(false);
//              videoPlayerProgress.setSelected(false);
//             videoPlayerProgress.setFocusable(false);
        } else {
            // lrcView.setVisibility(View.GONE);
            ivHead.setVisibility(View.GONE);
            gifVideoLoad.setVisibility(View.VISIBLE);
            ivVideoBg.setImageResource(R.mipmap.bg);
        }

        needFinish = true;

        GlideApp.with(PlayVideoActivity.this).load(R.drawable.playvideoload).skipMemoryCache(true)
                .placeholder(R.drawable.playvideoload).into(gifVideoLoad);
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


    private void initView() {
        if (!TextUtils.isEmpty(playlistName)) {
            tvPlayListName.setText(playlistName);
        }
        //翻页重新添加
        flPlayListContent.removeAllViews();
        for (int i = 0; i < playList.size(); i++) {
            BeanUserPlayList.DataBean dataBean = playList.get(i);
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
            tvSongName.setText(dataBean.getContentName());

            item.setOnClickListener(this);
            playListItem.add(item);

            if (dataBean.getIsFree() == 1) {
                item.findViewById(R.id.tv_free_tag).setVisibility(View.VISIBLE);
            } else {
                item.findViewById(R.id.tv_free_tag).setVisibility(View.GONE);
            }

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
                if (fileType == 1) {
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
                /*
                if (tools == null)
                    tools = new HiFiDialogTools();
                tools.showLeftRightTip(PlayVideoActivity.this, "提示", "您是否确定退出播放？", "确定", "取消", new MyDialogEnterListener() {
                    @Override
                    public void onClickEnter(Dialog dialog, Object object) {
                        if (object.equals(0)) {
                            PlayVideoActivity.this.finish();
                        }
                    }
                });
                */
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出播放", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
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
            if (fileType == 1) {
                videoPlayerProgress.requestFocus();
            } else {
                ivPlayerPlay.requestFocus();
            }
        }
        return flag;
    }

    private void setPlayListState() {
        for (int i = 0; i < playListItem.size(); i++) {
            View itemView = playListItem.get(i);
            if (itemView instanceof FrameLayout) { //歌曲
                TextView tvIndex = (TextView) itemView.findViewById(R.id.tv_song_index);
                TextView tvName = (TextView) itemView.findViewById(R.id.tv_song_name);
                if (playingIndex == i) {
                    tvIndex.setTextColor(getResources().getColor(R.color.green));
                    tvName.setTextColor(getResources().getColor(R.color.green));

                    itemView.requestFocus();
                } else {
                    tvIndex.setTextColor(getResources().getColor(R.color.white));
                    tvName.setTextColor(getResources().getColor(R.color.white));
                }
            } else if (itemView instanceof RelativeLayout) { //MV
                TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                TextView tvSingerName = (TextView) itemView.findViewById(R.id.tv_singer_name);
                if (playingIndex == i) {
                    tvTitle.setTextColor(getResources().getColor(R.color.green));
                    tvSingerName.setTextColor(getResources().getColor(R.color.green));

                    itemView.requestFocus();
                } else {
                    tvTitle.setTextColor(getResources().getColor(R.color.white));
                    tvSingerName.setTextColor(getResources().getColor(R.color.white));
                }
            }
        }
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
                    ToastUtil.show(this, "当前播放是第一首");
                } else {
                    if (playMVBySong) {
                        fileType = 0;
                    }
                    playPre();
                }
                break;
            }
            case R.id.iv_change_mv:
                playMVBySong = true;
                fileType = 1;
                ivPlayerPlay.requestFocus();
                playVideo();
                break;
            case R.id.iv_player_play:
                if (!needBuy()) {
                    rlControl.setVisibility(View.VISIBLE);
                    ivPlayerPlay.requestFocus();
                    hahaPauseOrResumePlay();
                }
                break;
            case R.id.iv_player_next: {
                if (playingIndex == (playList.size() - 1)) {
                    ToastUtil.show(this, "当前播放已经是最后一首");
                    return;
                }
                if (playMVBySong) {
                    fileType = 0;
                }
                playNext();
                break;
            }
            default:
                if (playListItem.contains(view)) {
                    if (playMVBySong) {
                        fileType = 0;
                    }
                    playingIndex = playListItem.indexOf(view);
                    playVideo();
                    setPlayListState();
                    flPlayList.setVisibility(View.GONE);
                    ivPlayerPlay.requestFocus();
                }
                break;
        }

    }

    private void collect() {
        if (playingIndex < playList.size()) {
            if (fileType == 1) {
                if (mvDetail == null) {
                    return;
                }
                BeanUserPlayList.DataBean bean = playList.get(playingIndex);

                asyncHttpRequest.deleteCollection(PlayVideoActivity.this, "1", bean.getContentMid(), this, this);
//                } else {
//                    asyncHttpRequest.addCollection(PlayVideoActivity.this, "1", bean.getContentMid(), this, this);
//                }
            } else {
                if (songDetail == null) {
                    return;
                }
                BeanUserPlayList.DataBean bean = playList.get(playingIndex);
                if (songDetail.getSong().getIfCollection() == 1) {
                  //  asyncHttpRequest.addCollection(PlayVideoActivity.this, "0", bean.getContentId(),bean.getMediaType(), this, this);
                } else {
                    asyncHttpRequest.deleteCollection(PlayVideoActivity.this, "0", String.valueOf(bean.getContentId()), this, this);
                }
            }
        }
    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        super.onSucceeded(method, key, object);
        if (TextUtils.equals(method, "addCollection.utvgo")) {
            BeanBasic beanBasic = (BeanBasic) object;
            if (beanBasic != null && TextUtils.equals(beanBasic.getCode(), "1")) {
                ivCollect.setImageResource(R.drawable.selector_player_collect_yes);
                if (fileType == 1) {
                    //  mvDetail.getMv().setIfCollection(1);
                } else {
                    songDetail.getSong().setIfCollection(1);
                }
            } else {
                HiFiDialogTools.getInstance().showtips(this, "收藏失败，请稍后重试", null);
            }
        } else if (TextUtils.equals(method, "deleteCollection.utvgo")) {
            BeanBasic beanBasic = (BeanBasic) object;
            if (beanBasic != null && TextUtils.equals(beanBasic.getCode(), "1")) {
                ivCollect.setImageResource(R.drawable.selector_player_collect_no);
                if (fileType == 1) {
                    //mvDetail.getMv().setIfCollection(0);
                } else {
                    songDetail.getSong().setIfCollection(0);
                }
            } else {
                HiFiDialogTools.getInstance().showtips(this, "收藏失败，请稍后重试", null);
            }
        } else if (TextUtils.equals(method, "songDetail.utvgo")) {
            statisticsPlayId = "";
            songDetail = (BeanSongDetail) object;

            if (songDetail != null && TextUtils.equals(songDetail.getCode(), "1")) {
                //songDetail.getSong().setFreeTime(10);
                boolean shouldPlay = true;
                startStatisticsPlay();
                statisticsVideoPlay("0", "0");

                if (HuyaApplication.hadBuy() || (songDetail.getSong().getIsFree() == 1) || isExperience) {
                    freeTime = -1;
                } else {
                    freeTime = Math.max(songDetail.getSong().getFreeTime(), 0);
                }
                if (freeTime == 0) {
                    shouldPlay = false;
                }

                String assetId = songDetail.getSong().getVodIdSq();
                if (TextUtils.isEmpty(songDetail.getSong().getVodIdSq()) || TextUtils.equals("0", songDetail.getSong().getVodIdSq())) {
                    assetId = songDetail.getSong().getVodIdHq();
                }

                if (shouldPlay) {
                    // XLog.toast(this, "Will play audio asset Id " + assetId);
                    getHahaPlayerUrl(assetId);
                } else {
                    //  XLog.toast(this, "require payment");
                    isToShowBuy = false;
                    showBuy(assetId);
                }

                playMVBySong = false;
                fileType = 0;

                if (songDetail.getSong().getIfCollection() == 1) {
                    ivCollect.setImageResource(R.drawable.selector_player_collect_yes);
                } else {
                    ivCollect.setImageResource(R.drawable.selector_player_collect_no);
                }

                String lyricText = songDetail.getSong().getLyricText();
                if(TextUtils.isEmpty(lyricText))
                {
                    lyricText = "暂无歌词，努力更新中...";
                }
                //解析歌词构造器
                // ILrcBuilder builder = new DefaultLrcBuilder();
                //解析歌词返回LrcRow集合
                // List<LrcRow> rows = builder.getLrcRows(lyricText);
                //将得到的歌词集合传给mLrcView用来展示
                // lrcView.setLrc(rows);
                // lrcView.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(songDetail.getSong().getMvMid())) {
                    ivChangeMv.setVisibility(View.INVISIBLE);
                } else {
                    ivChangeMv.setVisibility(View.VISIBLE);
                    /*
                    if (platfromUtils.isGuiZhou()) {
                        ivChangeMv.setVisibility(View.INVISIBLE);
                    } else {
                        ivChangeMv.setVisibility(View.VISIBLE);
                    }
                    */
                }
                String imageUrl = songDetail.getSong().getSingerSmallPic();
                if(TextUtils.isEmpty(imageUrl))
                {
                    ivHead.setImageResource(R.drawable.place_holder_song);
                }
                else
                {
                    ImageTool.loadImageWithUrl(this, DiffHostConfig.generateImageUrl(songDetail.getSong().getSingerSmallPic()), ivHead);
                }
                ivVideoBg.setVisibility(View.VISIBLE);
                ivHead.setVisibility(View.VISIBLE);

                showViewByHandler(ivPlayerPlay);
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取信息失败，请稍后重试", null);
            }
        } else if (TextUtils.equals(method, "program_content.utvgo")) {
            statisticsPlayId = "";
            mvDetail = (BeanVideoDetailZero) object;
            //mvDetail.getMv().setFreeTime(10);

            startStatisticsPlay();
            statisticsVideoPlay("0", "0");
            if (mvDetail != null && TextUtils.equals(mvDetail.getCode(), "1")) {
//                if (mvDetail.getMv().getIfCollection() == 1) {
//                    ivCollect.setImageResource(R.drawable.selector_player_collect_yes);
//                } else {
//                    ivCollect.setImageResource(R.drawable.selector_player_collect_no);
//                }

                if (HuyaApplication.hadBuy() || (mvDetail.getData().getIfFree() == "1") || isExperience) {
                    freeTime = -1;
                    if (TextUtils.isEmpty(mvDetail.getData().getVideoUrlHigh()) || TextUtils.equals("0", mvDetail.getData().getVideoUrlHigh())) {
                        getHahaPlayerUrl(mvDetail.getData().getVodId());
                    } else {
                        getHahaPlayerUrl(mvDetail.getData().getVideoUrlHigh());
                    }
                } else if (mvDetail.getData().getFreeSecond() > 0) {
                    freeTime = mvDetail.getData().getFreeSecond();
                    if (TextUtils.isEmpty(mvDetail.getData().getVideoUrlHigh()) || TextUtils.equals("0", mvDetail.getData().getVideoUrlHigh())) {
                        getHahaPlayerUrl(mvDetail.getData().getVodId());
                    } else {
                        getHahaPlayerUrl(mvDetail.getData().getVideoUrlHigh());
                    }
                } else {
                    freeTime = -1;
                    if (TextUtils.isEmpty(mvDetail.getData().getVideoUrlHigh()) || TextUtils.equals("0", mvDetail.getData().getVideoUrlHigh())) {
                        showBuy(mvDetail.getData().getVodId());
                    } else {
                        showBuy(mvDetail.getData().getVideoUrlHigh());
                    }
                }
                ivChangeMv.setVisibility(View.INVISIBLE);
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取信息失败，请稍后重试", null);
            }
            // lrcView.setVisibility(View.GONE);
            ivHead.setVisibility(View.GONE);

            showViewByHandler(videoPlayerProgress);
        } else if (TextUtils.equals(method, "statistics.utvgo")) {
            BeanStatistics beanStatistics = (BeanStatistics) object;
            if (beanStatistics != null && beanStatistics.getData() != null) {
                statisticsPlayId = beanStatistics.getData().getId();
            }
        }
    }

    @Override
    public void getHahaPlayerUrl(String vodID) {
        setHahaPlayer(vvJingling);
        //String asset = DiffHostConfig.getMediaAsset((mvDetail != null ) ? mvDetail.getData(): null, (songDetail != null) ? songDetail.getSong() : null);
        super.getHahaPlayerUrl(vodID);
    }

    @Override
    public void setPlayTime(long nowTime, long allTime) {
        super.setPlayTime(nowTime, allTime);
        this.nowTime = nowTime;
        tvDurLeft.setText(Tools.secToTime(nowTime / 1000));
        tvDurRight.setText(Tools.secToTime(allTime / 1000));
        tvBuyTip.setVisibility(View.GONE);
        if (!quickSeekNow) {
            videoPlayerProgress.setProgress((int) (nowTime * videoPlayerProgress.getMax() / allTime));
        }

        if (songDetail != null && !TextUtils.isEmpty(songDetail.getSong().getLyricText())) {
            // lrcView.seekLrcToTime(nowTime);
        }
        gifVideoLoad.setVisibility(View.GONE);

        needBuy();
    }

    private boolean needBuy() {
        XLog.d(String.format("needBuy %s,freeTime %d, isExperience %s", HuyaApplication.hadBuy() ? "hadBuy" : "noneBuy",
                freeTime, isExperience ? "true" : "false"));

        final boolean isPurchased = HuyaApplication.hadBuy();
        if (freeTime >= 0) {
            if (nowTime / 1000 >= freeTime) {
                if (fileType == 0) {
                    if (TextUtils.isEmpty(songDetail.getSong().getVodIdSq()) || TextUtils.equals("0", songDetail.getSong().getVodIdSq())) {
                        showBuy(songDetail.getSong().getVodIdHq());
                    } else {
                        showBuy(songDetail.getSong().getVodIdSq());
                    }
                } else {
                    //tvBuyTip.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(mvDetail.getData().getVideoUrlHigh()) || TextUtils.equals("0", mvDetail.getData().getVideoUrlHigh())) {
                        showBuy(mvDetail.getData().getVodId());
                    } else {
                        showBuy(mvDetail.getData().getVideoUrlHigh());
                    }
                }
                return true;
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
        if(playingIndex >= playList.size())
        {
            playingIndex = 0;
        }
        playVideo();
    }

    private void playPre() {
        playingIndex--;
        if(playingIndex < 0)
        {
            playingIndex = playList.size() - 1;
        }
        playVideo();
    }

    private void playVideo() {
//        if (!DiffHostConfig.validateDeviceKeyNO(this)) {
//            return;
//        }todo

        if (playingIndex >= playList.size()) {
            playingIndex = playList.size() - 1;
            return;
        }
        if (playingIndex < 0) {
            playingIndex = 0;
            return;
        }

        BeanUserPlayList.DataBean dataBean = playList.get(playingIndex);

        gifVideoLoad.setVisibility(View.VISIBLE);
        String mvMid = dataBean.getContentMid();
        if (playMVBySong) {
            mvMid = songDetail.getSong().getMvMid();
        }
        //String multiSetType, int pkgId,int channelId,

        if(dataBean.getSingerMids().contains("http")){
            getHahaPlayerUrl(dataBean.getSingerMids());

        }else {
            asyncHttpRequest.getMVDetail(PlayVideoActivity.this, dataBean.getContentType(), dataBean.getContentId(), dataBean.getMediaType(), this, this);
        }

        tvTitle.setText(dataBean.getContentName());
        tvSingerName.setText(dataBean.getSingerNames());
    }

    @Override
    public void playStateChange(int state) {
        super.playStateChange(state);
        if (fileType == 1) {
            ivVideoBg.setVisibility(View.GONE);
        } else {
            ivVideoBg.setVisibility(View.VISIBLE);
        }
        if (state == PlayingStatePlaying) {
            ivPlayerPlay.setImageResource(R.drawable.selector_player_stop);
        } else {
            ivPlayerPlay.setImageResource(R.drawable.selector_player_play);
        }
    }

    @Override
    public void hideInfoView() {
        super.hideInfoView();
        if (fileType == 1) {
            rlControl.setVisibility(View.GONE);
            flPlayList.setVisibility(View.GONE);
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
            VideoView videoView = (VideoView) getHahaPlayer();
            if (videoView != null) {
                statisticsVideoPlay(videoView.getCurrentPosition() / 1000 + "",
                        videoView.getCurrentPosition() / 1000 + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
//        startStatisticsPlay();
//        statusticsHandler.removeMessages(TagStartStatisticsPlay);
    }

    private static final int TagStartStatisticsPlay = 10011;
    private String statisticsPlayId = "";
    private String statisticsVideoId = "";

//    Handler statusticsHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message message) {
//            if (message.what == TagStartStatisticsPlay) {
//                startStatisticsPlay();
//                statusticsHandler.sendEmptyMessageDelayed(TagStartStatisticsPlay, 30 * 1000);
//            }
//            return false;
//        }
//    });

    private void startStatisticsPlay() {
        if (songDetail != null || mvDetail != null) {
            try {
                String programId = "";
                String videoName = "";
                String videoId = "";
                String statName = "";
                if (fileType == 0) {
                    programId = songDetail.getSong().getSongMid() + "";
                    videoName = songDetail.getSong().getSongName();
                    videoId = songDetail.getSong().getSongMid();
                    statName = "音频播放-" + videoName;
                } else {
                    programId = mvDetail.getData().getPkId() + "";
                    videoName = mvDetail.getData().getName();
                    videoId = String.valueOf(mvDetail.getData().getVideoId());
                    statName = "视频播放-" + videoName;
                }
                stat(statName);
            } catch (Exception o) {
                o.printStackTrace();
            }
        }
    }

    private void statisticsVideoPlay(String playPoint, String playTime) {
        String programId = "";
        String programName = "";
        String channelId = "1";
        String channelName = "QQ音乐";
        String spId = "1";
        String spName = "QQ音乐";
        String videoName = "";
        String videoId = "";
        long totalTime = 0;
        try {
            totalTime = videoView.getDuration();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fileType == 0) {
            programId = songDetail.getSong().getSongMid() + "";
            videoName = songDetail.getSong().getSongName();
            videoId = songDetail.getSong().getSongMid();
            programName = videoName;

        } else {
            programId = mvDetail.getData().getPkId() + "";
            videoName = mvDetail.getData().getName();
            videoId = String.valueOf(mvDetail.getData().getVideoId());
            programName = videoName;
            channelId = "2";
        }
        asyncHttpRequest.statisticsVideo(this, playPoint, videoId, videoName,
                spId, spName,
                programId, programName, channelId, channelName,
                "1", "0",  "1",//DiffConfig.CurrentPurchase.isPurchased() ? "1" : "0"
                "vip_code_50", statisticsVideoId, playTime, totalTime, new NetUtils.NetCallBack() {
                    @Override
                    public void netBack(int requestTag, Object object) {
                        if (object != null && (object instanceof BeanStatistics)) {
                            BeanStatistics beanStatistics = (BeanStatistics) object;
                            if (beanStatistics != null && beanStatistics.getData() != null) {
                                statisticsVideoId = beanStatistics.getData().getId();
                            }
                        }
                    }
                });
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
}
