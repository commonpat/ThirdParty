package com.utvgo.huya.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.utvgo.huya.R;
import com.utvgo.huya.activity.BaseActivity;
import com.utvgo.huya.activity.PlayVideoActivity;
import com.utvgo.huya.beans.BeanBasic;
import com.utvgo.huya.beans.BeanPlayHistoryPageList;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.listeners.MyDialogEnterListener;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ImageTool;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oo on 2017/8/22.
 */

public class PlayRecordActivity extends BaseActivity {

    @BindView(R.id.tv_page)
    TextView tvPage;
    @BindView(R.id.fl_videoList)
    FrameLayout flVideoList;

    private int pageNo = 1;
    private int pageSize = 12;
    private int pageTotal = 1;
    private String isConcert = "0";
    private String contentType = "0";

    private List<FrameLayout> itemVideoList = new ArrayList<>();
    private List<TextView> nameVideoList = new ArrayList<>();
    private List<TextView> indexVideoList = new ArrayList<>();


    private List<BeanPlayHistoryPageList.Data.Historys> playHistoryList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_play_record);
        ButterKnife.bind(this);

        traversalView(this);
        createBorderView(this);

        scale = 1.0f;
        initView();
        contentType = getIntent().getStringExtra("contentType");
        getPlayHistoryData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String statName = "最近播放";
        stat(statName);
    }

    private void initView() {

        for (int i = 0; i < flVideoList.getChildCount(); i++) {
            FrameLayout layout = (FrameLayout) flVideoList.getChildAt(i);
            itemVideoList.add(layout);
            nameVideoList.add((TextView) ((LinearLayout) layout.getChildAt(2)).getChildAt(0));
            indexVideoList.add((TextView) layout.getChildAt(1));
            layout.setVisibility(View.GONE);
        }

        tvPage.setText("< 0/0 >");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (itemVideoList.contains(v)) {
                if (v instanceof FrameLayout) {
                    borderView.setBorderBitmapResId(R.drawable.song_list_focus, (int) getResources().getDimension(R.dimen.dp10));
                }
                if (v instanceof FrameLayout) {
                    FrameLayout frameLayout = (FrameLayout) v;
                    ((ImageView) frameLayout.getChildAt(0)).setImageResource(R.mipmap.music_list_play);
                    ((TextView) frameLayout.getChildAt(1)).setTextColor(ContextCompat.getColor(this, R.color.transparent));
                    ((TextView) ((LinearLayout) frameLayout.getChildAt(2)).getChildAt(0)).setTextColor(ContextCompat.getColor(this, R.color.green));
                }
            }
        }else { if (v instanceof FrameLayout) {
                    FrameLayout frameLayout = (FrameLayout) v;
                    ((ImageView) frameLayout.getChildAt(0)).setImageResource(0);
                    ((TextView) frameLayout.getChildAt(1)).setTextColor(ContextCompat.getColor(this, R.color.white));
                    ((TextView) ((LinearLayout) frameLayout.getChildAt(2)).getChildAt(0)).setTextColor(ContextCompat.getColor(this, R.color.white));
                }
                borderView.setBorderBitmapResId(0);
        }
         super.onFocusChange(v, hasFocus);
    }

    private void getPlayHistoryData() {
            asyncHttpRequest.getPlayHistoryPageList(this,null,pageNo,pageSize,this,this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (focusView != null && event.getAction() == KeyEvent.ACTION_DOWN) {

                if ((focusView.getId() == R.id.fl_video2 || focusView.getId() == R.id.fl_video4 || focusView.getId() == R.id.fl_video6 || focusView.getId() == R.id.fl_video8
                        || focusView.getId() == R.id.fl_video10 || focusView.getId() == R.id.fl_video12)
                        && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {//下一页
                    showOther(flVideoList, 2000);
                    return true;
                } else if ((focusView.getId() == R.id.fl_video1 || focusView.getId() == R.id.fl_video3 || focusView.getId() == R.id.fl_video5 || focusView.getId() == R.id.fl_video7
                        || focusView.getId() == R.id.fl_video9 || focusView.getId() == R.id.fl_video11)
                        && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {//上一页
                    showOther(flVideoList, -2000);
                    return true;
                }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void showOther(ViewGroup viewGroup, int value) {
        if (value > 0) {//下一页
            pageNo++;
            if (pageNo > pageTotal) {
                pageNo = pageTotal;
                return;
            }
        } else if (value < 0) {//上一页
            pageNo--;
            if (pageNo < 1) {
                pageNo = 1;
                return;
            }
        }
        super.showOther(viewGroup, value);
            contentType = "1";
            getPlayHistoryData();
    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        super.onSucceeded(method, key, object);
        if (TextUtils.equals(method, "playhistoryPageList.utvgo")) {
            BeanPlayHistoryPageList beanPlayHistoryPageList = (BeanPlayHistoryPageList) object;
            if (beanPlayHistoryPageList != null && TextUtils.equals(beanPlayHistoryPageList.getCode(), "1")) {
                playHistoryList =  beanPlayHistoryPageList.getData().getHistorys();

                flVideoList.setVisibility(View.VISIBLE);

                    for (int i = 0; i < playHistoryList.size() && i < itemVideoList.size(); i++) {
                        itemVideoList.get(i).setVisibility(View.VISIBLE);
                        BeanPlayHistoryPageList.Data.Historys dataBean = playHistoryList.get(i);
                        nameVideoList.get(i).setText(dataBean.getProgramName());
                        indexVideoList.get(i).setText((pageNo - 1) * pageSize + i + 1 + "");

                    }
                    for (int n = playHistoryList.size(); n < itemVideoList.size(); n++) {
                        itemVideoList.get(n).setVisibility(View.GONE);
                    }
                    showViewByHandler(itemVideoList.get(0));
                    if (playHistoryList.size() == 0) {
                        showViewTip("暂无数据");
                    } else {
                        hideViewTip();
                    }


                pageTotal = beanPlayHistoryPageList.getTotalPage();
                tvPage.setText("< " + beanPlayHistoryPageList.getCurrentPage() + "/" + beanPlayHistoryPageList.getTotalPage() + " >");
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取数据失败，请稍后重试", null);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.fl_video1, R.id.fl_video2, R.id.fl_video3, R.id.fl_video4, R.id.fl_video5, R.id.fl_video6, R.id.fl_video7, R.id.fl_video8,
            R.id.fl_video9, R.id.fl_video10, R.id.fl_video11, R.id.fl_video12})
    public void onClick(View view) {
            int playIndex = 0;
            int fileType = 0;
            if (itemVideoList.contains(view)) {
                playIndex = itemVideoList.indexOf(view);
                fileType = 1;
            }
            ArrayList<BeanUserPlayList.DataBean> playList = new ArrayList<>();
            for(int i = 0; i<playHistoryList.size(); i++) {
                BeanPlayHistoryPageList.Data.Historys  videoBean= playHistoryList.get(i);
                BeanUserPlayList.DataBean playBean=new BeanUserPlayList.DataBean();
                playBean.setContentMid(String.valueOf(videoBean.getVideoId()));//videoId
                playBean.setContentType(videoBean.getMultiSetType());//单集多集
                playBean.setContentId(videoBean.getProgramId());//pkgId
                playBean.setMediaType(videoBean.getChannelId());//channelId
                playBean.setContentName(videoBean.getVideoName());
                playBean.setIsFree(-1);
                playBean.setSingerMids("0");
                playBean.setSingerNames(videoBean.getProgramName());
                playList.add(playBean);
            }
            Intent intent = new Intent(this, PlayVideoActivity.class);
            intent.putExtra("playIndex", playIndex);
            intent.putExtra("fileType", fileType);
            intent.putParcelableArrayListExtra("playList",  playList);
            this.startActivity(intent);
        }

}
