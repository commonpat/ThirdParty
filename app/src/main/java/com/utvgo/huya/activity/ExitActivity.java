package com.utvgo.huya.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanExitPage;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.views.FocusView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by oo on 2018/3/27.
 */

public class ExitActivity extends BaseActivity {
    public static final int TAGRecommendExit = 1008;

    @BindView(R.id.exitRoot)
    ImageView   exitRoot;
    @BindView(R.id.iv_img0)
    ImageView ivImg0;
    @BindView(R.id.iv_img1)
    ImageView ivImg1;
    @BindView(R.id.iv_img2)
    ImageView ivImg2;
    @BindView(R.id.iv_img3)
    ImageView ivImg3;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_exit)
    Button btn_exit;
    @BindView(R.id.focusView)
    FocusView focusView;

    BeanExitPage beanExitPage;
    List<BeanExitPage.Data> endPushContentBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        ButterKnife.bind(this);
//        asyncHttpRequest.getExitPage(this,null,this,this);
//        DisplayMetrics metric = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metric);
//        WindowManager.LayoutParams p = getWindow().getAttributes();
//        p.width = metric.widthPixels;
//        p.height = metric.heightPixels;
//        getWindow().setAttributes(p);
//        getWindow().setBackgroundDrawableResource(R.color.transparent);


//        ImageTool.loadImgByGlideToImageView(this, DiffHostConfig.generateImageUrl(HuyaApplication.beanActivity.getData().getSmallImg()),
//                R.drawable.place_holder_song, btnOk);
        NetworkService.defaultService().fetchExitPage(this, null, new JsonCallback<BeanExitPage>() {
            @Override
            public void onSuccess(Response<BeanExitPage> response) {
                 beanExitPage = response.body();
                ImageView[] imgViews= new ImageView[]{ivImg0,ivImg1,ivImg2,ivImg3};
                for (int i=0; i<imgViews.length; i++) {
                    Glide.with(ExitActivity.this).load( beanExitPage.getImageProfix()+beanExitPage.getData().get(i).getBgImgUrl()).into(imgViews[i]);
                }
                focusView.getViewTreeObserver().addOnGlobalFocusChangeListener(ExitActivity.this);
                ivImg0.requestFocus();
                Glide.with(ExitActivity.this).load(beanExitPage.getImageProfix() + beanExitPage.getData().get(0).getBgImgUrl()).into(exitRoot);
                focusView.setFocusView(ivImg0,R.mipmap.border_focus_style_default);
            }
        });
       stat("退出挽留页");
    }

    @OnClick({R.id.iv_img0,R.id.iv_img1,R.id.iv_img2,R.id.iv_img3, R.id.btn_back, R.id.btn_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_exit:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.iv_img0:
               clickRecItem(0);
               break;
            case R.id.iv_img1:
                clickRecItem(1);
                break;
            case R.id.iv_img2:
                clickRecItem(2);
                break;
            case R.id.iv_img3:
                clickRecItem(3);
                break;

        }
    }
    public void clickRecItem(int index) {
        //Log.d(TAG, "clickRecItem: " +beanArryPage.getData().get(index).get(0));
        BeanExitPage.Data selectBean = beanExitPage.getData().get(index);
        String href=selectBean.getHref();
        if(href.indexOf("albumPlayer.html") >= 0)
        {
            Intent intent = new Intent(this, MediaAlbumActivity.class);
            intent.putExtra("albumMid", Uri.parse(href).getQueryParameter("pkId"));
            startActivity(intent);
        }
        else if(href.indexOf("listPage.html") >= 0)
        {
            String channelId=Uri.parse(selectBean.getHref()).getQueryParameter("channelId");
            String channelName;
            int packageId=29;
            final int defaultSelectedLabelId = -1;
            MediaListActivity.show(this, Integer.valueOf(channelId),null,packageId ,defaultSelectedLabelId);
        }

    }


    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        try {
            focusView.setVisibility(View.VISIBLE);
            focusView.setFocusView(newFocus, R.mipmap.border_focus_style_default);
            switch (newFocus.getId()) {
                case R.id.iv_img0:
                    Glide.with(this).load(beanExitPage.getImageProfix() + beanExitPage.getData().get(0).getBgImgUrl()).into(exitRoot);
                    break;
                case R.id.iv_img1:
                    Glide.with(this).load(beanExitPage.getImageProfix() +  beanExitPage.getData().get(1).getBgImgUrl()).into(exitRoot);
                    break;
                case R.id.iv_img2:
                    Glide.with(this).load(beanExitPage.getImageProfix() +  beanExitPage.getData().get(2).getBgImgUrl()).into(exitRoot);
                    break;
                case R.id.iv_img3:
                    Glide.with(this).load(beanExitPage.getImageProfix() +  beanExitPage.getData().get(3).getBgImgUrl()).into(exitRoot);
                    break;
                default:
                    focusView.setVisibility(View.INVISIBLE);
                    exitRoot.setBackgroundResource(R.mipmap.bg);
                    break;
            }
        } catch (Exception e) {
            Log.d(TAG, "onGlobalFocusChanged: " + e.toString());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
