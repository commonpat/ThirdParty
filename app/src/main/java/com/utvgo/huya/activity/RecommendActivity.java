package com.utvgo.huya.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.huya.R;
import com.utvgo.huya.utils.ImageTool;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by fute on 2018/5/30.
 */

public class RecommendActivity extends BaseActivity {

    @BindView(R.id.iv_bg)
    ImageView ivBg;

    private String recommendType = "enter";
    private String contentMid = "31";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        ButterKnife.bind(this);

        recommendType = getIntent().getStringExtra("recommendType");
        String bgUrl = getIntent().getStringExtra("bgUrl");
        contentMid = getIntent().getStringExtra("contentMid");

        loadImage(ivBg, DiffConfig.generateImageUrl(bgUrl));
    }

    @OnClick({R.id.iv_bg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bg:
                if (TextUtils.equals("exit", recommendType)) {
                    Intent intent2 = new Intent(this, TopicActivity.class);
                    intent2.putExtra("topicId", contentMid);
                    startActivity(intent2);
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Intent intent = new Intent(this, TopicActivity.class);
                    intent.putExtra("topicId", contentMid);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (TextUtils.equals("exit", recommendType)) {
            setResult(RESULT_CANCELED);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        stat(TextUtils.equals("exit", recommendType) ? "退出推荐" : "启动推荐","");
    }
}
