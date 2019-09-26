package com.utvgo.huya.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.utvgo.huya.GlideApp;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanArryPage;
import com.utvgo.huya.beans.BeanExitPage;
import com.utvgo.huya.utils.ActivityUtility;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.FocusView;
import com.utvgo.huya.utils.ImageTool;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by oo on 2018/3/27.
 */

public class ExitActivity extends BaseActivity {
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
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.focusView)
    FocusView focusView;

    BeanExitPage beanExitPage;
    List<BeanExitPage.Data> endPushContentBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        ButterKnife.bind(this);
        btnOk.requestFocus();
        asyncHttpRequest.getExitPage(this,null,this,this);
//        DisplayMetrics metric = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metric);
//        WindowManager.LayoutParams p = getWindow().getAttributes();
//        p.width = metric.widthPixels;
//        p.height = metric.heightPixels;
//        getWindow().setAttributes(p);
//        getWindow().setBackgroundDrawableResource(R.color.transparent);


//        ImageTool.loadImgByGlideToImageView(this, DiffHostConfig.generateImageUrl(HuyaApplication.beanActivity.getData().getSmallImg()),
//                R.drawable.place_holder_song, btnOk);

  //      stat("退出挽留页");
    }

    @OnClick({R.id.iv_img0,R.id.iv_img1,R.id.iv_img2,R.id.iv_img3, R.id.btn_ok, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                setResult(RESULT_OK);
                finish();
                onBackPressed();
                break;
            case R.id.btn_cancel:
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
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
        if (TextUtils.equals(selectBean.getHrefType(), "0")) { //超链接
            ActivityUtility.goWebActivityActivity(this, selectBean.getHref());
        } else
        if (TextUtils.equals(selectBean.getHrefType(), "3")) { //mv专辑
            Intent intent = new Intent(this, MVAlbumActivity.class);
            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("pkId"));
            startActivity(intent);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "1")) { //音频专辑
//            Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);
//            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("zjid"));
//            startActivity(intent);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "8")) { //活动
//            //Intent intent = new Intent(IndexActivity.this, ActivityActivity.class);
//            //startActivity(intent);
//            //todo
//        } else if (selectBean.getHref().contains("collect.html")) { //收藏
//            Intent intent = new Intent(this, CollectCenterActivity.class);
//            intent.putExtra("type", 1);
//            startActivity(intent);
//        } else if (selectBean.getHref().contains("htyplay.html")) { //历史
//            Intent intent = new Intent(this, CollectCenterActivity.class);
//            intent.putExtra("type", 0);
//            startActivity(intent);
        }
        else if (TextUtils.equals(selectBean.getHrefType(), "4")) { //专题
            Intent intent = new Intent(this, TopicActivity.class);
            intent.putExtra("topicId", Uri.parse(selectBean.getHref()).getQueryParameter("themId"));
            intent.putExtra("type", Uri.parse(selectBean.getHref()).getQueryParameter("styleID"));
            startActivity(intent);
//        } else if (selectBean.getHref().contains("recordList_pd.html")) { //榜单
//            ActivityUtility.goSongRankActivity(this, Uri.parse(selectBean.getHref()).getQueryParameter("mid"),
//                    Uri.parse(selectBean.getHref()).getQueryParameter("id"), selectBean.getName());
//        } else if (TextUtils.equals(selectBean.getHrefType(), "12")) {//专题收录
//            ActivityUtility.goActivity(this, TopicCollectionActivity.class);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "13")) {//直播
//            if (!TextUtils.isEmpty(selectBean.getHref())) {
//                String[] strs = selectBean.getHref().split("=");
//                if (strs.length > 1) {
//                    turnLiveActivity(Integer.parseInt(strs[1]));
//                }
//            }
//        } else {
//            //视频播放
//            ArrayList<BeanUserPlayList.DataBean> playHistoryList = new ArrayList<>();
//            getHrefList(index, selectBean, playHistoryList);
//            Intent intent = new Intent(this, PlayVideoActivity.class);
//            if (index == 1) {
//                intent.putExtra("playIndex", vodIdPlayIndex);
//            } else {
//                intent.putExtra("playIndex", 0);
//            }
//            intent.putExtra("fileType", 1);
//            intent.putParcelableArrayListExtra("playList", playHistoryList);
//            this.startActivity(intent);
//        }
        }
    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        super.onSucceeded(method, key, object);
        switch (method){
            case "page.utvgo":
                if (object instanceof BeanExitPage)
                    beanExitPage= (BeanExitPage) object;
                    endPushContentBean=beanExitPage.getData();
                    ImageView[] imgViews= new ImageView[]{ivImg0,ivImg1,ivImg2,ivImg3};
                    for (int i=0;i<4;i++){
                     GlideApp.with(this).load(beanExitPage.getImageProfix()+beanExitPage.getData().get(i).getImgUrl()).into(imgViews[i]);

                    }
                    btnOk.requestFocus();
                    focusView.setFocusView(btnOk,R.drawable.focus_border);
                    exitRoot.setBackground(imgViews[0].getBackground());
                    focusView.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
                break;
        }
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        try {
            focusView.setFocusView(newFocus, R.drawable.focus_border);
            switch (newFocus.getId()) {
                case R.id.iv_img0:
                    GlideApp.with(this).load(beanExitPage.getImageProfix() + endPushContentBean.get(0).getBgImgUrl()).into(exitRoot);
                    break;
                case R.id.iv_img1:
                    GlideApp.with(this).load(beanExitPage.getImageProfix() + endPushContentBean.get(1).getBgImgUrl()).into(exitRoot);
                    break;
                case R.id.iv_img2:
                    GlideApp.with(this).load(beanExitPage.getImageProfix() + endPushContentBean.get(2).getBgImgUrl()).into(exitRoot);
                    break;
                case R.id.iv_img3:
                    GlideApp.with(this).load(beanExitPage.getImageProfix() + endPushContentBean.get(3).getBgImgUrl()).into(exitRoot);
                    break;
                default:
                    exitRoot.setBackgroundResource(R.drawable.bg1);
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
