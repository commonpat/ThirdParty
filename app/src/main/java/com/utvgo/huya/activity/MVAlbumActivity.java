package com.utvgo.huya.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.utvgo.huya.R;
import com.utvgo.huya.UTVGOSubscriber;
import com.utvgo.huya.beans.BeanBasic;
import com.utvgo.huya.beans.BeanMVDetail;
import com.utvgo.huya.beans.BeanStatistics;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.beans.BeanWLAblumData;
import com.utvgo.huya.constant.MVAlbumTemplate;
import com.utvgo.huya.template.MVAlbumTemplate1;
import com.utvgo.huya.template.MVAlbumTemplate2;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ImageTool;
import com.utvgo.huya.utils.StrTool;
import com.utvgo.huya.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.utvgo.huya.Constants.BASE_URL_VIDEO;

/**
 * Created by haha on 2018/6/8.
 */

public class MVAlbumActivity extends BuyActivity {

    @BindView(R.id.tv_ablum_name)
    TextView tvAblumName;
    @BindView(R.id.tv_mv_name)
    TextView tvMvName;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.sv_video)
    SurfaceView svVideo;
    @BindView(R.id.video)
    VideoView video;
    @BindView(R.id.iv_video_focus)
    ImageView ivVideoFocus;
    @BindView(R.id.activity_RootView)
    public
    FrameLayout activityRootView;
    @BindView(R.id.bg)
    ImageView bg;
    private String showType = "";
    private boolean isExperience;

    public ImageView getBg() {
        return bg;
    }

    private BeanMVDetail mvDetail;
    private int freeTime = -1;
    private long nowTime = 0;
    private int playIndex = 0;
    private String albumMid;
    public BeanWLAblumData beanWLAblumData;
    public List<BeanTopic.DataBean.UtvgoSubjectRecordListBean> subjectRecordListBeen = new ArrayList<>();
    private List<ImageView> ivIconList = new ArrayList<>();
    private List<TextView> tvNameList = new ArrayList<>();
    private List<RelativeLayout> itemList = new ArrayList<>();
    private int pageIndex = 0;
    private int pageSize = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mv_ablum);
        ButterKnife.bind(this);
        buySingle = getIntent().getBooleanExtra("isSingle", false);
        if (buySingle) {
            if (getIntent().hasExtra("buySingleName")) {
                buySingleName = getIntent().getStringExtra("buySingleName");
            }
        }
        isExperience = getIntent().getBooleanExtra("isExperience", false);
        addPlayView();
        ivVideoFocus.setVisibility(View.VISIBLE);
        ivVideoFocus.setOnFocusChangeListener(this);
        ivVideoFocus.setOnClickListener(this);

        createBorderView(this);
        traversal(activityRootView);
        borderView.setBorderBitmapResId(R.drawable.singer_list_f, (int) getResources().getDimension(R.dimen.dp50),
                (int) getResources().getDimension(R.dimen.dp40));

        getData();

        itemList.add((RelativeLayout) findViewById(R.id.rl_item1));
        itemList.add((RelativeLayout) findViewById(R.id.rl_item2));
        itemList.add((RelativeLayout) findViewById(R.id.rl_item3));
        itemList.add((RelativeLayout) findViewById(R.id.rl_item4));

        ivIconList.add((ImageView) findViewById(R.id.iv_icon1));
        ivIconList.add((ImageView) findViewById(R.id.iv_icon2));
        ivIconList.add((ImageView) findViewById(R.id.iv_icon3));
        ivIconList.add((ImageView) findViewById(R.id.iv_icon4));

        tvNameList.add((TextView) findViewById(R.id.tv_name1));
        tvNameList.add((TextView) findViewById(R.id.tv_name2));
        tvNameList.add((TextView) findViewById(R.id.tv_name3));
        tvNameList.add((TextView) findViewById(R.id.tv_name4));

    }

    public void getData() {
        albumMid = getIntent().getStringExtra("albumMid");
        if (buySingle) {
//            mv专辑
//            server.getWLAblumData(albumMid,new UTVGOSubscriber<BeanWLAblumData>(){
//                @Override
//                public void onCompleted() {
//                    super.onCompleted();
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    super.onError(e);
//                }
//
//                @Override
//                public void onNext(BeanWLAblumData beanWLAblumData) {
//                    Log.d(TAG, "onNext: 返回数据"+beanWLAblumData.toString());
//                }
//            });
            asyncHttpRequest.getWLAblumDataSingle(this, albumMid, this, this);
        } else {
            asyncHttpRequest.getWLAblumData(this, albumMid, this, this);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v != null && showType.equals(MVAlbumTemplate.TWO)) {
            if (v instanceof ViewGroup) {
                ViewUtil.runText(v, hasFocus);
            }
        }
        if (hasFocus) {
            if (v == ivCollect) {
                borderView.setBorderBitmapResId(R.drawable.sheet_collect_focus, (int) getResources().getDimension(R.dimen.dp40));
            } else if (v == ivVideoFocus) {
                borderView.setBorderBitmapResId(R.drawable.mains_f_2, (int) getResources().getDimension(R.dimen.dp30));
            } else if (itemList.contains(v)) {
                borderView.setBorderBitmapResId(R.drawable.singer_list_f, (int) getResources().getDimension(R.dimen.dp50),
                        (int) getResources().getDimension(R.dimen.dp40));
            } else if (showType.equals(MVAlbumTemplate.ONE)) {
                borderView.setBorderBitmapResId(R.drawable.mains_f_2_p9, (int) getResources().getDimension(R.dimen.dp5), (int) getResources().getDimension(R.dimen.dp13));
            } else if (showType.equals(MVAlbumTemplate.TWO)) {
                borderView.setBorderBitmapResId(R.drawable.mains_f_2_p9, (int) getResources().getDimension(R.dimen.dp15), (int) getResources().getDimension(R.dimen.dp13));
            }
        }
        super.onFocusChange(v, hasFocus);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hadCallBuyView  && !isExperience) {
            hadCallBuyView = false;
            finish();
        } else {
            playMV(playIndex);
        }
    }

    private void addPlayView() {
        //vod maybe not accepted
        video.setVisibility(View.VISIBLE);
        setHahaPlayer(video);
    }

    @Override
    protected void onStart() {
        super.onStart();
        statusticsHandler.sendEmptyMessageDelayed(TagStartStatisticsPlay, 30 * 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        startStatisticsPlay();
        statusticsHandler.removeMessages(TagStartStatisticsPlay);
    }

    private static final int TagStartStatisticsPlay = 10011;
    private String statisticsPlayId = "";

    Handler statusticsHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == TagStartStatisticsPlay) {
                startStatisticsPlay();
                statusticsHandler.sendEmptyMessageDelayed(TagStartStatisticsPlay, 30 * 1000);
            }
            return false;
        }
    });

    private void startStatisticsPlay() {
        if (mvDetail != null) {
            try {
                String videoName = mvDetail.getMv().getName();
                //stat("视频播放-" + videoName);todo
            } catch (Exception o) {
                o.printStackTrace();
            }
        }
    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        super.onSucceeded(method, key, object);
        if (TextUtils.equals(method, "program_content.utvgo")) {
            beanWLAblumData = (BeanWLAblumData) object;
//            if(ApkUtil.isDebug(MVAlbumActivity.this)){
//                beanWLAblumData.getAlbum().setShowType(MVAlbumTemplate.TWO);
//            }
            if (beanWLAblumData != null && TextUtils.equals(beanWLAblumData.getCode(), "1")) {
                showType = beanWLAblumData.getData().getShowType();
                switch (showType) {
                    case MVAlbumTemplate.ONE:
                        MVAlbumTemplate1 template1 = new MVAlbumTemplate1(MVAlbumActivity.this);
                        template1.addContent(beanWLAblumData);
                        break;
                    case MVAlbumTemplate.TWO:
                        MVAlbumTemplate2 template2 = new MVAlbumTemplate2(MVAlbumActivity.this);
                        template2.addContent(beanWLAblumData);
                        break;
                    case MVAlbumTemplate.THREE:
                        break;
                    default:
                        ((ViewGroup) bg.getParent()).getChildAt(1).setVisibility(View.VISIBLE);
                        showTemplateDefault();
                        showViewByHandler(itemList.get(0));
                        break;

                }
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取数据失败，请稍后重试", null);
            }

        } else if (TextUtils.equals(method, "mvDetail.utvgo")) {
            statisticsPlayId = "";
            startStatisticsPlay();
            mvDetail = (BeanMVDetail) object;
            if (mvDetail != null && TextUtils.equals(mvDetail.getCode(), "1")) {
                tvMvName.setText(mvDetail.getMv().getName() + "   " + mvDetail.getMv().getSingerName());
                if ( mvDetail.getMv().getIsFree() == 1 || isExperience) {
                    freeTime = -1;
                    if (TextUtils.isEmpty(mvDetail.getMv().getVodIdHD()) || TextUtils.equals("0", mvDetail.getMv().getVodIdHD())) {
                        getHahaPlayerUrl(mvDetail.getMv().getVodId());
                    } else {
                        getHahaPlayerUrl(mvDetail.getMv().getVodIdHD());
                    }
                } else if (mvDetail.getMv().getFreeTime() > 0) {
                    freeTime = mvDetail.getMv().getFreeTime();
                    if (TextUtils.isEmpty(mvDetail.getMv().getVodIdHD()) || TextUtils.equals("0", mvDetail.getMv().getVodIdHD())) {
                        getHahaPlayerUrl(mvDetail.getMv().getVodId());
                    } else {
                        getHahaPlayerUrl(mvDetail.getMv().getVodIdHD());
                    }
                } else {
                    freeTime = -1;
                    if (TextUtils.isEmpty(mvDetail.getMv().getVodIdHD()) || TextUtils.equals("0", mvDetail.getMv().getVodIdHD())) {
                        showBuy(mvDetail.getMv().getVodId());
                    } else {
                        showBuy(mvDetail.getMv().getVodId());
                    }
                }
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取信息失败，请稍后重试", null);
            }
        } else if (TextUtils.equals(method, "statistics.utvgo")) {
            BeanStatistics beanStatistics = (BeanStatistics) object;
            if (beanStatistics != null && beanStatistics.getData() != null) {
                statisticsPlayId = beanStatistics.getData().getId();
            }
        } else if (TextUtils.equals(method, "addCollection.utvgo")) {
            BeanBasic beanBasic = (BeanBasic) object;
            if (beanBasic != null && TextUtils.equals(beanBasic.getCode(), "1")) {
                ivCollect.setImageResource(R.drawable.sheet_collect_1);
                // beanWLAblumData.getData().setIfCollection(1);
            } else {
                HiFiDialogTools.getInstance().showtips(this, "收藏失败，请稍后重试", null);
            }
        } else if (TextUtils.equals(method, "deleteCollection.utvgo")) {
            BeanBasic beanBasic = (BeanBasic) object;
            if (beanBasic != null && TextUtils.equals(beanBasic.getCode(), "1")) {
                ivCollect.setImageResource(R.drawable.sheet_collect_2);
                //beanWLAblumData.getData().setIfCollection(0);
            } else {
                HiFiDialogTools.getInstance().showtips(this, "收藏失败，请稍后重试", null);
            }
        }
    }

    private void showTemplateDefault() {
        List<BeanWLAblumData.DataBean.VideoBean> albumData = beanWLAblumData.getData().getVideos();

        subjectRecordListBeen = new ArrayList<>();
        for (int i = 0; i < albumData.size(); i++) {
            BeanWLAblumData.DataBean.VideoBean mvDataBean = albumData.get(i);
            BeanTopic.DataBean.UtvgoSubjectRecordListBean subjectBean = new BeanTopic.DataBean.UtvgoSubjectRecordListBean();
            //  subjectBean.setImgUrl(mvDataBean.getMvPoster());
            subjectBean.setHref(mvDataBean.getVideoUrlFluency());
            subjectBean.setImgUrl(mvDataBean.getImageSmall());
            subjectBean.setName(mvDataBean.getName());
            subjectBean.setMvMid(String.valueOf(mvDataBean.getVideoId()));
            subjectRecordListBeen.add(subjectBean);
        }
        final String albumName = beanWLAblumData.getData().getName();
        final String title = albumName;
        tvAblumName.setText(title);
        //stat(title);todo

        pageSize = subjectRecordListBeen.size() / 4;
        if (subjectRecordListBeen.size() % 4 != 0) {
            pageSize = pageSize + 1;
        }
        initView();
        playMV(0);

        //stat("外部推荐位-" + albumName);todo
    }

    private void playMV(int index) {
        if (subjectRecordListBeen != null && subjectRecordListBeen.size() > index) {
            itemList.get(index % 4).requestFocus();
            pageIndex = index / 4;
            initView();
            BeanTopic.DataBean.UtvgoSubjectRecordListBean bean = subjectRecordListBeen.get(index);
            if (!TextUtils.isEmpty(albumMid)) {
                hahaPlayUrl( beanWLAblumData.getData().getVideos().get(index).getVideoUrlHigh());
                // asyncHttpRequest.getMVDetail(this, bean.getMvMid(), this, this);
            } else {
                asyncHttpRequest.getMVDetail(this, StrTool.getValueByName(bean.getHref(), "mvMid"),0,0, this, this);
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (focusView != null && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (focusView.getId() == R.id.rl_item4 && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {//下一页

                if (pageIndex < (pageSize - 1)) {
                    pageIndex++;
                    initView();
                    itemList.get(0).requestFocus();
                }

                return true;
            } else if (focusView.getId() == R.id.rl_item1 && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {//上一页
                if (pageIndex > 0) {
                    pageIndex--;
                    initView();
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void initView() {
        for (int i = pageIndex * 4; i < subjectRecordListBeen.size() && (i < (pageIndex * 4 + 4)); i++) {
            itemList.get(i % 4).setVisibility(View.VISIBLE);
            BeanTopic.DataBean.UtvgoSubjectRecordListBean bean = subjectRecordListBeen.get(i);
            ImageTool.loadImageWithUrl(this, DiffHostConfig.generateImageUrl(bean.getImgUrl()), ivIconList.get(i % 4));
            tvNameList.get(i % 4).setText(bean.getName());
        }

        if ((subjectRecordListBeen.size() - pageIndex * 4) < 4) {
            for (int i = subjectRecordListBeen.size() - pageIndex * 4; i < 4; i++) {
                itemList.get(i).setVisibility(View.GONE);
            }
        }

        tvCount.setText((pageIndex + 1) + "/" + pageSize);
    }

    @Override
    public void getHahaPlayerUrl(String vodID) {
        if (mvDetail != null) {
            hahaPlayUrl(DiffHostConfig.getMediaAsset(mvDetail.getMv(), null));
        }
    }

    @Override
    public void hahaPlayEnd(float v) {
        super.hahaPlayEnd(v);
        playMV(++playIndex);
    }

    @Override
    public void onDuration(long l) {
        super.onDuration(l);
    }

    @Override
    public void setPlayTime(long nowTime, long allTime) {
        super.setPlayTime(nowTime, allTime);
        this.nowTime = nowTime;
        needBuy();
    }

    private boolean needBuy() {
        if (freeTime != -1 && !isExperience) {
            if (nowTime / 1000 > freeTime) {
                showBuy(mvDetail.getMv().getVodId());
                return true;
            }
        }
        return false;
    }

    @OnClick(R.id.iv_collect)
    public void onViewClicked() {
//        if (beanWLAblumData == null) {
//            return;
//        }
//        if (beanWLAblumData.getAlbum().getIfCollection() == 1) {
//          //  asyncHttpRequest.deleteCollection(this, "2", albumMid, this, this);todo
//        } else {
//           // asyncHttpRequest.addCollection(this, "2", albumMid, this, this);todo
//        }
    }

    @Override
    public void onClick(View view) {
        if (view == ivVideoFocus) {
            playVideoFullScreen(playIndex);
        } else if (itemList.contains(view)) {
            playIndex = pageIndex * 4 + itemList.indexOf(view);
            playMV(playIndex);
        }
    }

    public void playVideoFullScreen(int playIndex) {
        ArrayList<BeanUserPlayList.DataBean> playHistoryList = new ArrayList<>();
        for (int i = 0; i < subjectRecordListBeen.size(); i++) {
            BeanTopic.DataBean.UtvgoSubjectRecordListBean bean = subjectRecordListBeen.get(i);
            if ((!TextUtils.isEmpty(bean.getHref()) &&
                    !TextUtils.isEmpty(StrTool.getValueByName(bean.getHref(), "mvMid")))
                    || !TextUtils.isEmpty(bean.getMvMid())) {
                BeanUserPlayList.DataBean playBean = new BeanUserPlayList.DataBean();
                if (!bean.getHref().contains("http")&&bean.getHref().contains("mp4")){
                    playBean.setSingerMids(BASE_URL_VIDEO+bean.getHref());
                }else {
                    playBean.setSingerMids(bean.getHref());}
                playBean.setBigPic(bean.getImgUrl());
                playBean.setSmallPic(bean.getImgUrl());
                if (!TextUtils.isEmpty(bean.getMvMid())) {
                    playBean.setContentMid(bean.getMvMid());
                } else {
                    playBean.setContentMid(StrTool.getValueByName(bean.getHref(), "mvMid"));
                }
                playBean.setContentName(bean.getName());
                playHistoryList.add(playBean);
            }
        }

        Intent intent = new Intent(this, PlayVideoActivity.class);
        intent.putExtra("playIndex", playIndex);
        intent.putExtra("fileType", 1);
        intent.putParcelableArrayListExtra("playList", playHistoryList);
        this.startActivity(intent);
    }

    @Override
    public void showBuy(String vodID) {
        if (buySingle) {
            super.showBuy(albumMid);
        } else {
            super.showBuy(vodID);
            hadCallBuyView = true;
        }
    }

    @Override
    public void onBackPressed() {
        if (isExperience) {
            isExperience = false;
            showBuy("");
        } else {
            super.onBackPressed();
        }
    }
}
