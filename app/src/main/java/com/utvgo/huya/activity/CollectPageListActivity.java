package com.utvgo.huya.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanCollectPageList;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.utils.HiFiDialogTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fute on 17/3/21.
 */

public class CollectPageListActivity extends BaseActivity {

    @BindView(R.id.fl_songList)
    FrameLayout flSongList;
    @BindView(R.id.tv_page)
    TextView tvPage;
    @BindView(R.id.tv_singer_name)
    TextView tvSingerName;

    private int orderType = 1;
    private int pageNo = 1;
    private int pageSize = 10;
    private int pageTotal = 1;
    private List<BeanCollectPageList.Data.CollectList> collectPageList;
    private List<FrameLayout> itemList = new ArrayList<>();
    private List<TextView> nameTVList = new ArrayList<>();
    private List<TextView> indexTVList = new ArrayList<>();
    private List<TextView> freeTagList = new ArrayList<>();
    private List<ImageView> mvTagList = new ArrayList<>();
    private List<TextView> singNameList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_song_list);
        ButterKnife.bind(this);

        traversalView(this);
        createBorderView(this);
        getSongData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //stat(singerBean == null ? "收藏-歌曲" : ("歌手歌曲列表-" + singerBean.getSingerName()));
    }

    private void initView() {
        for (int i = 0; i < flSongList.getChildCount(); i++) {
            FrameLayout layout = (FrameLayout) flSongList.getChildAt(i);
            itemList.add(layout);
            indexTVList.add((TextView) layout.getChildAt(1));
            nameTVList.add((TextView) ((LinearLayout) layout.getChildAt(2)).getChildAt(0));
            mvTagList.add((ImageView) ((LinearLayout) layout.getChildAt(2)).getChildAt(1));
            freeTagList.add((TextView) ((LinearLayout) layout.getChildAt(2)).getChildAt(2));
            singNameList.add((TextView) layout.getChildAt(3));
            layout.setVisibility(View.GONE);
            layout.setOnClickListener(this);
        }
            tvSingerName.setText("我的收藏");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.e(TAG, "onFocusChange: " + v + ":" + v.getId());
        if (hasFocus) {
            if (v instanceof FrameLayout) {
                scale = 1.0f;
                borderView.setBorderBitmapResId(R.drawable.video_list_focus, (int) getResources().getDimension(R.dimen.dp10));
            } else {
                borderView.setBorderBitmapResId(0);
            }
        }
        if (v instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) v;
            if (hasFocus) {
                ((TextView) frameLayout.getChildAt(1)).setTextColor(ContextCompat.getColor(this, R.color.transparent));
                ((TextView) ((LinearLayout) frameLayout.getChildAt(2)).getChildAt(0)).setTextColor(ContextCompat.getColor(this, R.color.yellow));
            } else {
                ((ImageView) frameLayout.getChildAt(0)).setImageResource(0);
                ((TextView) frameLayout.getChildAt(1)).setTextColor(ContextCompat.getColor(this, R.color.white));
                ((TextView) ((LinearLayout) frameLayout.getChildAt(2)).getChildAt(0)).setTextColor(ContextCompat.getColor(this, R.color.white));
            }
        }
        super.onFocusChange(v, hasFocus);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (focusView != null && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((focusView.getId() == R.id.fl_song2 || focusView.getId() == R.id.fl_song4 || focusView.getId() == R.id.fl_song6 || focusView.getId() == R.id.fl_song8
                    || focusView.getId() == R.id.fl_song10) && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {//下一页
                showOther(flSongList, 2000);
                return true;
            } else if ((focusView.getId() == R.id.fl_song1 || focusView.getId() == R.id.fl_song3 || focusView.getId() == R.id.fl_song5 || focusView.getId() == R.id.fl_song7
                    || focusView.getId() == R.id.fl_song9) && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {//上一页
                showOther(flSongList, -2000);
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
        getSongData();
        super.showOther(viewGroup, value);
    }

    private void getSongData() {
        asyncHttpRequest.getCollectPageList(this,null,this,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (itemList.contains(v)) {
            ArrayList<BeanUserPlayList.DataBean> playHistoryList = new ArrayList<>();
            int index=itemList.indexOf(v);
            BeanCollectPageList.Data.CollectList playVideoBean = collectPageList.get(index);
            if ("4".equals(playVideoBean.getMultiSetType())||"3".equals(playVideoBean.getMultiSetType())){
                Intent intent = new Intent(this, MVAlbumActivity.class);
                intent.putExtra("albumMid", playVideoBean.getProgramId()+"");
                startActivity(intent);
            }else {
            for (int i = 0; i < collectPageList.size(); i++) {
                    BeanCollectPageList.Data.CollectList mvBean = collectPageList.get(i);
                    BeanUserPlayList.DataBean playBean = new BeanUserPlayList.DataBean();
                    playBean.setContentMid(String.valueOf(mvBean.getVideoId()));//videoId
                    playBean.setContentType(mvBean.getMultiSetType());//单集多集
                    playBean.setContentId(mvBean.getProgramId());//pkgId
                    playBean.setMediaType(mvBean.getChannelId());//channelId
                    playBean.setContentName(mvBean.getVideoName());
                    playBean.setIsFree(-1);
                    playBean.setSingerMids("0");
                    playBean.setSingerNames(mvBean.getProgramName());
                    playHistoryList.add(playBean);
            }
            Intent intent = new Intent(this, PlayVideoActivity.class);
            intent.putExtra("playIndex", itemList.indexOf(v));
            intent.putExtra("fileType", 1);
            intent.putExtra("playlistName", "我的收藏");
            intent.putParcelableArrayListExtra("playList", playHistoryList);
            this.startActivity(intent);
        }}
    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        super.onSucceeded(method, key, object);
        if (TextUtils.equals(method, "collectPageList.utvgo")) {
            BeanCollectPageList beanCollectPageList = (BeanCollectPageList) object;
            if (beanCollectPageList != null && TextUtils.equals(beanCollectPageList.getCode(), "1")) {
                collectPageList = beanCollectPageList.getData().getCollectList();
                for (int i = 0; i < collectPageList.size() && i < itemList.size(); i++) {
                    itemList.get(i).setVisibility(View.VISIBLE);
                    BeanCollectPageList.Data.CollectList dataBean = collectPageList.get(i);

                    nameTVList.get(i).setText(dataBean.getProgramName());
                    indexTVList.get(i).setText((pageNo - 1) * pageSize + i + 1 + "");
                    if (i == 0) {
                        showViewByHandler(itemList.get(0));
                    }

                    if (TextUtils.isEmpty(String.valueOf(dataBean.getVideoId()))) {
                        mvTagList.get(i).setVisibility(View.GONE);
                    } else {
                        mvTagList.get(i).setVisibility(View.VISIBLE);
                    }
                    freeTagList.get(i).setVisibility(View.GONE);

                }
                for (int n = collectPageList.size(); n < itemList.size(); n++) {
                    itemList.get(n).setVisibility(View.GONE);
                }
                pageTotal = beanCollectPageList.getTotalPage();
                tvPage.setText(beanCollectPageList.getCurrentPage() + "/" + beanCollectPageList.getTotalPage());
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取数据失败，请稍后重试", null);
            }
        }
    }
}
