package com.utvgo.huya.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
    @BindView(R.id.fl_songList)
    FrameLayout flSongList;
    @BindView(R.id.fl_mvList)
    FrameLayout flMvList;
    @BindView(R.id.cb_mv)
    CheckBox cbMv;
    @BindView(R.id.cb_song)
    CheckBox cbSong;

    private int pageNo = 1;
    private int pageSize = 12;
    private int pageTotal = 1;
    private String isConcert = "0";
    private String contentType = "0";

    private ArrayList<ImageView> ivMVList = new ArrayList<>();
    private ArrayList<RelativeLayout> itemMVList = new ArrayList<>();
    private ArrayList<TextView> tvMVList = new ArrayList<>();

    private List<FrameLayout> itemSongList = new ArrayList<>();
    private List<TextView> nameSongList = new ArrayList<>();
    private List<TextView> indexSongList = new ArrayList<>();
    private List<ImageView> mvTagList = new ArrayList<>();

    private ArrayList<BeanUserPlayList.DataBean> playHistoryList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_play_record);
        ButterKnife.bind(this);

        traversalView(this);
        createBorderView(this);

        scale = 1.0f;
        initView();

        isConcert = getIntent().getStringExtra("isConcert");
        contentType = getIntent().getStringExtra("contentType");
        getPlayHistoryData();
        if (TextUtils.equals("1", isConcert)) { //演唱会
            cbMv.setText("演唱会播放记录");
        }
        if (TextUtils.equals("0", contentType)) {
            cbSong.requestFocus();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String statName = "最近播放-MV";
        if (TextUtils.equals("1", isConcert)) {
            statName = "最近播放-演唱会";
        } else if (TextUtils.equals("0", contentType)) {
//            asyncHttpRequest.statisticsVisit(this,"最近播放-歌曲","");
        }
        stat(statName);
    }

    private void initView() {
        for (int i = 0; i < flMvList.getChildCount(); i++) {
            View view = flMvList.getChildAt(i);
            view.setVisibility(View.INVISIBLE);
            if (view instanceof RelativeLayout) {
                itemMVList.add((RelativeLayout) view);
                ivMVList.add((ImageView) ((RelativeLayout) view).getChildAt(0));
                tvMVList.add((TextView) ((RelativeLayout) view).getChildAt(1));
            }
        }

        for (int i = 0; i < flSongList.getChildCount(); i++) {
            FrameLayout layout = (FrameLayout) flSongList.getChildAt(i);
            itemSongList.add(layout);
            nameSongList.add((TextView) ((LinearLayout) layout.getChildAt(2)).getChildAt(0));
            indexSongList.add((TextView) layout.getChildAt(1));
            mvTagList.add((ImageView) ((LinearLayout) layout.getChildAt(2)).getChildAt(1));
            layout.setVisibility(View.GONE);
        }

        tvPage.setText("< 0/0 >");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (v == cbSong) {
                if (!cbSong.isChecked()) {
                    pageNo = 1;
                    cbSong.setChecked(true);
                    cbMv.setChecked(false);
                    contentType = "0";
                    getPlayHistoryData();
                    stat("最近播放-歌曲");
                }
                borderView.setBorderBitmapResId(0);
            } else if (v == cbMv) {
                if (!cbMv.isChecked()) {
                    pageNo = 1;
                    cbMv.setChecked(true);
                    cbSong.setChecked(false);
                    contentType = "1";
                    getPlayHistoryData();
                    stat("最近播放-MV");
                }
                borderView.setBorderBitmapResId(0);
            } else if (itemSongList.contains(v)) {
                if (v instanceof FrameLayout) {
                    borderView.setBorderBitmapResId(R.drawable.song_list_focus, (int) getResources().getDimension(R.dimen.dp10));
                }
                if (v instanceof FrameLayout) {
                    FrameLayout frameLayout = (FrameLayout) v;
                    ((ImageView) frameLayout.getChildAt(0)).setImageResource(R.mipmap.music_list_play);
                    ((TextView) frameLayout.getChildAt(1)).setTextColor(ContextCompat.getColor(this, R.color.transparent));
                    ((TextView) ((LinearLayout) frameLayout.getChildAt(2)).getChildAt(0)).setTextColor(ContextCompat.getColor(this, R.color.green));
                }
            } else if (itemMVList.contains(v)) {
                borderView.setBorderBitmapResId(R.drawable.singer_list_f, (int) getResources().getDimension(R.dimen.dp50));
            } else if (v.getId() == R.id.btn_search_mv) {
                borderView.setBorderBitmapResId(0);
            }
        } else {
            if (v instanceof FrameLayout) {
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
        //asyncHttpRequest.userPlayList(this, contentType, isConcert, pageNo, pageSize, this, this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (focusView != null && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (cbSong.isChecked()) {
                if ((focusView.getId() == R.id.fl_song2 || focusView.getId() == R.id.fl_song4 || focusView.getId() == R.id.fl_song6 || focusView.getId() == R.id.fl_song8
                        || focusView.getId() == R.id.fl_song10 || focusView.getId() == R.id.fl_song12)
                        && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {//下一页
                    showOther(flSongList, 2000);
                    return true;
                } else if ((focusView.getId() == R.id.fl_song1 || focusView.getId() == R.id.fl_song3 || focusView.getId() == R.id.fl_song5 || focusView.getId() == R.id.fl_song7
                        || focusView.getId() == R.id.fl_song9 || focusView.getId() == R.id.fl_song11)
                        && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {//上一页
                    showOther(flSongList, -2000);
                    return true;
                }
            } else {
                if ((focusView.getId() == R.id.item4 || focusView.getId() == R.id.item8 || focusView.getId() == R.id.item12)
                        && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {//下一页
                    showOther(flMvList, 2000);
                    return true;
                } else if ((focusView.getId() == R.id.item1 || focusView.getId() == R.id.item5 || focusView.getId() == R.id.item9)
                        && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {//上一页
                    showOther(flMvList, -2000);
                    return true;
                }
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
        if (cbSong.isChecked()) {
            contentType = "0";
            getPlayHistoryData();
        } else {
            contentType = "1";
            getPlayHistoryData();
        }
    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        super.onSucceeded(method, key, object);
        if (TextUtils.equals(method, "userPlayList.utvgo")) {
            BeanUserPlayList beanUserPlayList = (BeanUserPlayList) object;
            if (beanUserPlayList != null && TextUtils.equals(beanUserPlayList.getCode(), "1")) {
                playHistoryList = beanUserPlayList.getData();
                if (cbMv.isChecked()) {
                    flMvList.setVisibility(View.VISIBLE);
                    flSongList.setVisibility(View.GONE);
                    for (int i = 0; i < playHistoryList.size() && i < itemMVList.size(); i++) {
                        itemMVList.get(i).setVisibility(View.VISIBLE);
                        BeanUserPlayList.DataBean dataBean = playHistoryList.get(i);
                        tvMVList.get(i).setText(dataBean.getContentName());

                        ImageTool.loadImageWithUrl(this, DiffHostConfig.generateImageUrl(dataBean.getBigPic()), ivMVList.get(i));
                    }
                    for (int n = playHistoryList.size(); n < itemMVList.size(); n++) {
                        itemMVList.get(n).setVisibility(View.GONE);
                    }
                    showViewByHandler(itemMVList.get(0));
                    if (playHistoryList.size() == 0) {
                        showViewTip("暂无数据");
                    } else {
                        hideViewTip();
                    }

                } else {
                    flSongList.setVisibility(View.VISIBLE);
                    flMvList.setVisibility(View.GONE);
                    for (int i = 0; i < playHistoryList.size() && i < itemSongList.size(); i++) {
                        itemSongList.get(i).setVisibility(View.VISIBLE);
                        BeanUserPlayList.DataBean dataBean = playHistoryList.get(i);
                        nameSongList.get(i).setText(dataBean.getContentName());
                        indexSongList.get(i).setText((pageNo - 1) * pageSize + i + 1 + "");

                        if (TextUtils.isEmpty(dataBean.getMvMid())) {
                            mvTagList.get(i).setVisibility(View.GONE);
                        } else {
                            mvTagList.get(i).setVisibility(View.VISIBLE);
                        }
                    }
                    for (int n = playHistoryList.size(); n < itemSongList.size(); n++) {
                        itemSongList.get(n).setVisibility(View.GONE);
                    }
                    showViewByHandler(itemSongList.get(0));
                    if (playHistoryList.size() == 0) {
                        showViewTip("暂无数据");
                    } else {
                        hideViewTip();
                    }
                }

                pageTotal = beanUserPlayList.getTotalPage();
                tvPage.setText("< " + beanUserPlayList.getCurrentPage() + "/" + beanUserPlayList.getTotalPage() + " >");
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取数据失败，请稍后重试", null);
            }
        } else if (TextUtils.equals(method, "deleteUserPlayRecord.utvgo")) {
            BeanBasic beanBasic = (BeanBasic) object;
            if (beanBasic != null && TextUtils.equals(beanBasic.getCode(), "1")) {
                if (cbSong.isChecked()) {
                    contentType = "0";
                    getPlayHistoryData();
                } else {
                    contentType = "1";
                    getPlayHistoryData();
                }
            } else {
                HiFiDialogTools.getInstance().showtips(this, "清空失败，请稍后重试", null);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.fl_song1, R.id.fl_song2, R.id.fl_song3, R.id.fl_song4, R.id.fl_song5, R.id.fl_song6, R.id.fl_song7, R.id.fl_song8,
            R.id.fl_song9, R.id.fl_song10, R.id.fl_song11, R.id.fl_song12, R.id.item1, R.id.item2, R.id.item3, R.id.item4,
            R.id.item5, R.id.item6, R.id.item7, R.id.item8, R.id.item9, R.id.item10, R.id.item11, R.id.item12, R.id.btn_clear_all})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_clear_all) {
            String tipStr = "是否清空播放记录";
            if (cbSong.isChecked()) {
                tipStr = "是否清空歌曲播放记录";
            }
            HiFiDialogTools.getInstance().showLeftRightTip(this, "提示", tipStr, "取消", "立即清空", new MyDialogEnterListener() {
                @Override
                public void onClickEnter(Dialog dialog, Object object) {
                    if (Integer.parseInt(object.toString()) == 1) {
                        if (cbSong.isChecked()) {
                           // asyncHttpRequest.deleteUserPlayRecord(PlayRecordActivity.this, "0", PlayRecordActivity.this, PlayRecordActivity.this);
                        } else {
                           // asyncHttpRequest.deleteUserPlayRecord(PlayRecordActivity.this, "1", PlayRecordActivity.this, PlayRecordActivity.this);
                        }
                    }
                }
            });
        } else {
            int playIndex = 0;
            int fileType = 0;
            if (itemSongList.contains(view)) {
                playIndex = itemSongList.indexOf(view);
                fileType = 0;
            } else if (itemMVList.contains(view)) {
                playIndex = itemMVList.indexOf(view);
                fileType = 1;
            }
            Intent intent = new Intent(this, PlayVideoActivity.class);
            intent.putExtra("playIndex", playIndex);
            intent.putExtra("fileType", fileType);
            intent.putParcelableArrayListExtra("playList", playHistoryList);
            this.startActivity(intent);
        }
    }
}
