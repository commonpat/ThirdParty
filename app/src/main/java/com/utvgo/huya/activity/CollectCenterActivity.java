package com.utvgo.huya.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.utvgo.huya.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.utvgo.huya.activity.PlayRecordActivity;

/**
 * Created by oo on 2018/4/18.
 */

public class CollectCenterActivity extends BaseActivity {

    @BindView(R.id.iv_mv)
    FrameLayout ivMv;
    @BindView(R.id.iv_vocal)
    FrameLayout ivVocal;
    @BindView(R.id.iv_song)
    FrameLayout ivSong;
    @BindView(R.id.iv_album)
    FrameLayout ivAlbum;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private int type = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_center);
        ButterKnife.bind(this);

        traversalView(this);
        createBorderView(this);
        borderView.setBorderBitmapResId(R.drawable.singer_list_f, (int) getResources().getDimension(R.dimen.dp38),
                (int) getResources().getDimension(R.dimen.dp75));
        scale = 1.0f;
        showViewByHandler(ivMv);

        type = getIntent().getIntExtra("type", 1);
        if (type == 1) {
            tvTitle.setText("我的收藏");
        } else {
            tvTitle.setText("最近播放");
        }
       // asyncHttpRequest.getUserInfo();
       // asyncHttpRequest.getArryPage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        stat(type == 1 ? "我的收藏" : "最近播放");
    }

    @OnClick({R.id.iv_mv, R.id.iv_vocal, R.id.iv_song, R.id.iv_album})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mv:
                if (type == 1) {
                    Intent intent = new Intent(this, MVListActivity.class);
                    intent.putExtra("mvType", "5");
                    intent.putExtra("isConcert", "0");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, PlayRecordActivity.class);
                    intent.putExtra("contentType", "1");
                    intent.putExtra("isConcert", "0");
                    startActivity(intent);
                }
                break;
            case R.id.iv_vocal:
                if (type == 1) {
                    Intent intent = new Intent(this, MVListActivity.class);
                    intent.putExtra("mvType", "5");
                    intent.putExtra("isConcert", "1");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, PlayRecordActivity.class);
                    intent.putExtra("contentType", "1");
                    intent.putExtra("isConcert", "1");
                    startActivity(intent);
                }
                break;
//            case R.id.iv_song:
//                if (type == 1) {
//                    Intent intent = new Intent(this, SongListActivity.class);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(this, PlayRecordActivity.class);
//                    intent.putExtra("contentType", "0");
//                    intent.putExtra("isConcert", "0");
//                    startActivity(intent);
//                }
//                break;
//            case R.id.iv_album:
//                Intent intent = new Intent(this, AlbumListActivity.class);
//                if (type == 1) {
//                    intent.putExtra("collect", "1");
//                } else {
//                    intent.putExtra("playList", "1");
//                }
//                startActivity(intent);
//                break;
        }
    }
}
