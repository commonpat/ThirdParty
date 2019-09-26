package com.utvgo.huya.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanMVDetail;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.net.IVolleyRequestSuccess;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ImageTool;
import com.utvgo.huya.utils.StrTool;


import java.util.ArrayList;
import java.util.List;


public class TopicOtherActivity extends BuyActivity {
    public final static String INTENT_ID = "id";
    private ImageView backFocusImage, backImage, moreImage, moreFocusImage, ivBg;
    private List<FrameLayout> frameLayouts = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_other);
        init();
        asyncHttpRequest.getTopic(this, getIntent().getStringExtra(INTENT_ID), new IVolleyRequestSuccess() {
            @Override
            public void onSucceeded(String method, String key, Object object) throws Exception {
                BeanTopic beanTopic = (BeanTopic) object;
                if (beanTopic != null && TextUtils.equals(beanTopic.getCode(), "1")) {
                    ImageTool.loadImageWithUrl(TopicOtherActivity.this, DiffHostConfig.generateImageUrl(beanTopic.getData().getImgUrl()), ivBg);
                    stat("专题-" + beanTopic.getData().getName());
                    setData(beanTopic);
                } else {
                    HiFiDialogTools.getInstance().showtips(TopicOtherActivity.this, "获取数据失败，请稍后重试", null);
                }
            }
        }, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.video).setFocusable(false);
    }

    private void init() {
        setHahaPlayer(findViewById(R.id.video));
        ivBg = (ImageView) findViewById(R.id.iv_bg);
        frameLayouts.add((FrameLayout) findViewById(R.id.f1));
        frameLayouts.add((FrameLayout) findViewById(R.id.f2));
        frameLayouts.add((FrameLayout) findViewById(R.id.f3));
        frameLayouts.add((FrameLayout) findViewById(R.id.f4));
        frameLayouts.add((FrameLayout) findViewById(R.id.f5));

        backFocusImage = (ImageView) findViewById(R.id.back_focus);
        backImage = (ImageView) findViewById(R.id.back);
        moreFocusImage = (ImageView) findViewById(R.id.more_focus);
        moreImage = (ImageView) findViewById(R.id.more);
        ImageTool.loadByGlide(this, R.mipmap.morebtnfocus, moreImage);
        backFocusImage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    backImage.setVisibility(View.VISIBLE);
                } else {
                    backImage.setVisibility(View.GONE);
                }
            }
        });
        moreFocusImage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    moreImage.setVisibility(View.VISIBLE);
                } else {
                    moreImage.setVisibility(View.GONE);
                }
            }
        });
        backFocusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        moreFocusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.vif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHrefList(href);
            }
        });
    }

    private void setData(final BeanTopic data) {
        for (int i = 0; i < 5; i++) {
            TextView textView = (TextView) frameLayouts.get(i).getChildAt(2);
            textView.setText(data.getData().getUtvgoSubjectRecordList().get(i).getName());
            ImageView imageView = (ImageView) frameLayouts.get(i).getChildAt(0);
            final int d = i;
            imageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        frameLayouts.get(d).getChildAt(1).setVisibility(View.VISIBLE);
                    } else {
                        frameLayouts.get(d).getChildAt(1).setVisibility(View.GONE);
                    }
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    href = data.getData().getUtvgoSubjectRecordList().get(d).getHref();
                    getMVData(data.getData().getUtvgoSubjectRecordList().get(d).getHref().split("mvMid")[1].replace("=", ""));
                }
            });
            if (i == 0) {
                imageView.requestFocus();
                href = data.getData().getUtvgoSubjectRecordList().get(d).getHref();
                getMVData(data.getData().getUtvgoSubjectRecordList().get(d).getHref().split("mvMid")[1].replace("=", ""));
            }
        }
    }

    private String href;

    private void getMVData(String mid) {
        Log.e("df", mid);
        asyncHttpRequest.getMVDetail(this, mid, new IVolleyRequestSuccess() {
            @Override
            public void onSucceeded(String method, String key, Object object) throws Exception {
                BeanMVDetail mvDetail = (BeanMVDetail) object;
                getHahaPlayerUrl(mvDetail.getMv().getVodId());
            }
        }, null);
    }

    private void getHrefList(String href) {
        //视频播放
        ArrayList<BeanUserPlayList.DataBean> playHistoryList = new ArrayList<>();
//        getHrefList(index, selectBean, playHistoryList);
        BeanUserPlayList.DataBean playBean = new BeanUserPlayList.DataBean();
        playBean.setBigPic("");
        playBean.setSmallPic("");
        playBean.setContentMid(StrTool.getValueByName(href, "mvMid"));
        String name = "";
        playBean.setContentName(name);
        playHistoryList.add(playBean);
        Intent intent = new Intent(this, PlayVideoActivity.class);
        intent.putExtra("playIndex", 0);
        intent.putExtra("fileType", 1);
        intent.putParcelableArrayListExtra("playList", playHistoryList);
        this.startActivity(intent);
    }
}
