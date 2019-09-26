package com.utvgo.huya.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.topics.Topic1;
import com.utvgo.huya.topics.Topic2;
import com.utvgo.huya.topics.Topic3;
import com.utvgo.huya.topics.Topic5;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ImageTool;
import com.utvgo.huya.utils.StrTool;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by oo on 2017/10/23.
 */

public class TopicActivity extends BuyActivity {

    @BindView(R.id.topic_default)
    FrameLayout topicDefault;
    @BindView(R.id.topic_1)
    FrameLayout layoutTopic1;
    @BindView(R.id.topic_2)
    FrameLayout layoutTopic2;
    @BindView(R.id.topic_3)
    LinearLayout layoutTopic3;
    @BindView(R.id.topic_5)
    FrameLayout layoutTopic5;
    @BindView(R.id.page)
    TextView page;
    private Topic2 topic2;
    private Topic3 topic3;
    private int type = 0;
    private Topic5 topic5;

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (v.getParent().equals(flTopicContent)
                    || v.getParent().equals(musicList1)
                    || v.getParent().equals(musicList2)
                    || (v.getParent().getParent() != null && v.getParent().getParent().equals(layoutTopic3))
                    && subjectRecordListBeen != null && subjectRecordListBeen.size() > 0) {
                page.setText(v.getId() + 1 + "/" + subjectRecordListBeen.size());
                if (v.getParent().equals(flTopicContent)) {
                    borderView.setBorderBitmapResId(R.drawable.mains_f_2_p9, (int) getResources().getDimension(R.dimen.dp5), (int) getResources().getDimension(R.dimen.dp13));
                }
            }

        }
        super.onFocusChange(v, hasFocus);
    }

    ;

    public FrameLayout getTopicDefault() {
        return topicDefault;
    }

    public FrameLayout getLayoutTopic1() {
        return layoutTopic1;
    }

    public FrameLayout getLayoutTopic2() {
        return layoutTopic2;
    }

    public LinearLayout getLayoutTopic3() {
        return layoutTopic3;
    }

    public FrameLayout getLayoutTopic5() {
        return layoutTopic5;
    }

    //topic_default
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.sv_video)
    SurfaceView svVideo;
    @BindView(R.id.video)
    VideoView video;
    @BindView(R.id.iv_video_focus)
    ImageView ivVideoFocus;
    @BindView(R.id.fl_topic_content)
    FrameLayout flTopicContent;
    @BindView(R.id.activity_RootView)
    FrameLayout activityRootView;
    @BindView(R.id.iv_bg1)
    ImageView ivBg1;
    @BindView(R.id.iv_bg2)
    ImageView ivBg2;
    @BindView(R.id.iv_bg3)
    ImageView ivBg3;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;

    //topic_1
    private Topic1 topic1;
    @BindView(R.id.music_list1)
    LinearLayout musicList1;
    @BindView(R.id.display_area)
    ScrollView displayArea;

    public ScrollView getDisplayArea() {
        return displayArea;
    }

    //topic_2
    @BindView(R.id.music_list2)
    LinearLayout musicList2;

    public LinearLayout getMusicList1() {
        return musicList1;
    }

    public LinearLayout getMusicList2() {
        return musicList2;
    }

    private boolean isRecommendExit = false;
    public int topMargin = 0;
    public List<BeanTopic.DataBean.UtvgoSubjectRecordListBean> subjectRecordListBeen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ButterKnife.bind(this);
        createBorderView(this);
        if (type == 0) {
            topMargin = (int) getResources().getDimension(R.dimen.dp430);
        }

        isRecommendExit = getIntent().getBooleanExtra("isRecommendExit", false);
        if (getIntent().getStringExtra("type") != null && getIntent().getStringExtra("type").equals("6")) {
            Intent intent = new Intent(this, TopicOtherActivity.class);
            intent.putExtra(TopicOtherActivity.INTENT_ID, getIntent().getStringExtra("topicId") + "");
            startActivity(intent);
            finish();
        } else {
            getData();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (topic3 != null) {
            topic3.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showTopic(int type, BeanTopic beanTopic) {
        try{
            switch (type) {
                case 0:
                    borderView.setBorderBitmapResId(0, (int) getResources().getDimension(R.dimen.dp38),
                            (int) getResources().getDimension(R.dimen.dp50));
                    initView(beanTopic);
                    page.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    topic1 = new Topic1(this);
                    topic1.addContent(beanTopic);
                    FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) page.getLayoutParams();
                    layoutParams1.leftMargin = (int) getResources().getDimension(R.dimen.dp460);
                    layoutParams1.bottomMargin = (int) getResources().getDimension(R.dimen.dp40);
                    page.setLayoutParams(layoutParams1);
                    break;
                case 2:
                    topic2 = new Topic2(this);
                    topic2.addContent(beanTopic);
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) page.getLayoutParams();
                    layoutParams2.bottomMargin = (int) getResources().getDimension(R.dimen.dp20);
                    layoutParams2.leftMargin = (int) getResources().getDimension(R.dimen.dp100);
                    page.setLayoutParams(layoutParams2);
                    break;
                case 3:
                    topic3 = new Topic3(this);
                    topic3.addContent(beanTopic);
                    FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) page.getLayoutParams();
                    layoutParams3.bottomMargin = (int) getResources().getDimension(R.dimen.dp10);
                    layoutParams3.leftMargin = 0;
                    layoutParams3.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
                    page.setLayoutParams(layoutParams3);
                    break;
                case 5:
                    topic5 = new Topic5(this);
                    topic5.addContent(beanTopic);
                    page.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
//themId=151&styleID=5
    public void getData() {
        String topicId = getIntent().getStringExtra("topicId");
        asyncHttpRequest.getTopic(this, topicId, this, this);
    }

    public void initView( BeanTopic beanTopic) {
        int allWidth = 0;
        for (int i = 0; i < subjectRecordListBeen.size(); i++) {
            View itemTopic = View.inflate(this, R.layout.item_topic, null);
            itemTopic.setOnFocusChangeListener(this);
//            itemTopic.setOnFocusChangeListener(changePage);
            itemTopic.setOnClickListener(this);
            itemTopic.setId(i);
            itemTopic.setNextFocusUpId(R.id.iv_video_focus);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp250),
                    (int) getResources().getDimension(R.dimen.dp175));

            int leftMargin = (int) getResources().getDimension(R.dimen.dp55) + i * (int) getResources().getDimension(R.dimen.dp250);

            params.topMargin = topMargin;
            params.leftMargin = leftMargin;
            params.rightMargin = (int) getResources().getDimension(R.dimen.dp60);

            flTopicContent.addView(itemTopic, params);
            allWidth = leftMargin + params.width + (int) getResources().getDimension(R.dimen.dp200);

            ImageView ivIcon = (ImageView) itemTopic.findViewById(R.id.iv_icon);
            TextView tvName = (TextView) itemTopic.findViewById(R.id.tv_name);

            BeanTopic.DataBean.UtvgoSubjectRecordListBean bean = subjectRecordListBeen.get(i);
            Log.d(TAG, "initView: url"+DiffHostConfig.generateImageUrl(bean.getImgUrl()));
            ImageTool.loadImageWithUrl(this,DiffHostConfig.generateImageUrl(bean.getImgUrl()), ivIcon);
            tvName.setText(bean.getName());
//            if (i == 0) {
//                showViewByHandler(itemTopic);
//            }
        }


        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(allWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        flTopicContent.setLayoutParams(params);
        focusOn1stView(findViewById(0), R.drawable.singer_list_f, (int) getResources().getDimension(R.dimen.dp50),
                (int) getResources().getDimension(R.dimen.dp58));
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        int playIndex = view.getId();
        BeanTopic.DataBean.UtvgoSubjectRecordListBean selectBean = subjectRecordListBeen.get(playIndex);
        if (selectBean.getHref().contains("qqmusic_zt.html")) {
            Intent intent = new Intent(this, TopicActivity.class);
            intent.putExtra("topicId", Uri.parse(selectBean.getHref()).getQueryParameter("themId"));
            startActivity(intent);
        } else if (selectBean.getHref().contains("play_album.html")) {
//            Intent intent = new Intent(this, AlbumDetailActivity.class);
//            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("zjid"));
//            startActivity(intent);
        } else if (selectBean.getHref().contains("albumPlayer.html")) {
            Intent intent = new Intent(this, MVAlbumActivity.class);
            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("pkId"));
            startActivity(intent);
        } else if (selectBean.getHref().contains("recordList_gs.html")) {
//            Intent intent = new Intent(this, SongListActivity.class);
//            BeanAreaTypeSingerList.DataBean bean = new BeanAreaTypeSingerList.DataBean();
//            bean.setSingerMid(Uri.parse(selectBean.getHref()).getQueryParameter("singerMid"));
//            bean.setSingerName(selectBean.getName());
//            intent.putExtra("BeanAreaTypeSinger", bean);
//            startActivity(intent);
        } else {
            playVideoFullScreen(playIndex);
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
        startActivity(intent);
        if (isRecommendExit) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        super.onSucceeded(method, key, object);
        if (TextUtils.equals(method, "select.utvgo")) {
//            Gson gson = new Gson();
//            BeanTopic beanTopic = gson.fromJson("{\"message\":\"success\",\"data\":{\"createTime\":null,\"des\":\"暑期档这些影视原声OST不能错过\",\"nameBgColor\":\"\",\"updateTime\":null,\"remark\":\"\",\"status\":\"0\",\"editeBy\":null,\"imgNum\":0,\"createBy\":null,\"imgUrl\":\"2018\\/07\\/25\\/20180725161638713.jpg\",\"id\":42,\"imgUrl1\":\"\",\"imgUrl3\":\"\",\"name\":\"暑期档这些影视原声OST不能错过\",\"imgUrl2\":\"\",\"imgUrl4\":\"\",\"utvgoSubjectRecordList\":[{\"imgUrl\":\"2018\\/07\\/25\\/20180725161808588.jpg\",\"id\":520,\"createTime\":null,\"des\":\"《扶摇》电视剧命运主题曲\",\"updateTime\":null,\"remark\":\"\",\"status\":\"0\",\"priority\":6,\"subjectId\":42,\"name\":\"《扶摇》电视剧命运主题曲\",\"editeBy\":null,\"createBy\":null,\"href\":\"play_video.html?mvsMid=53,1,29,12&mvMid=v002659hihz\"},{\"imgUrl\":\"2018\\/07\\/25\\/20180725162149398.jpg\",\"id\":524,\"createTime\":null,\"des\":\"《为了你我愿意热爱整个世界》电视剧主题曲\",\"updateTime\":null,\"remark\":\"\",\"status\":\"0\",\"priority\":4,\"subjectId\":42,\"name\":\"《为了你我愿意热爱整个世界》电视剧主题曲\",\"editeBy\":null,\"createBy\":null,\"href\":\"play_video.html?mvsMid=53,1,29,12&mvMid=g0026vtixiu\"},{\"imgUrl\":\"2018\\/07\\/25\\/20180725163326900.jpg\",\"id\":526,\"createTime\":null,\"des\":\"《芸汐传》电视剧推广曲\",\"updateTime\":null,\"remark\":\"\",\"status\":\"0\",\"priority\":4,\"subjectId\":42,\"name\":\"《芸汐传》电视剧推广曲\",\"editeBy\":null,\"createBy\":null,\"href\":\"play_video.html?mvsMid=53,1,29,12&mvMid=z0026cjg5vs\"},{\"imgUrl\":\"2018\\/07\\/25\\/20180725162110747.jpg\",\"id\":523,\"createTime\":null,\"des\":\"《网球少年》电视剧开赛推广曲\",\"updateTime\":null,\"remark\":\"\",\"status\":\"0\",\"priority\":3,\"subjectId\":42,\"name\":\"《网球少年》电视剧开赛推广曲\",\"editeBy\":null,\"createBy\":null,\"href\":\"play_video.html?mvsMid=53,1,29,12&mvMid=l0026jvhi1g\"},{\"imgUrl\":\"2018\\/07\\/25\\/20180725161914884.jpg\",\"id\":522,\"createTime\":null,\"des\":\"《来到你的世界》网络剧主题曲\",\"updateTime\":null,\"remark\":\"\",\"status\":\"0\",\"priority\":2,\"subjectId\":42,\"name\":\"《来到你的世界》网络剧主题曲\",\"editeBy\":null,\"createBy\":null,\"href\":\"play_video.html?mvsMid=53,1,29,12&mvMid=r0026i2qjp1\"},{\"imgUrl\":\"2018\\/07\\/25\\/20180725161839429.jpg\",\"id\":521,\"createTime\":null,\"des\":\"《快把我哥带走》网剧主题曲\",\"updateTime\":null,\"remark\":\"\",\"status\":\"0\",\"priority\":1,\"subjectId\":42,\"name\":\"《快把我哥带走》网剧主题曲\",\"editeBy\":null,\"createBy\":null,\"href\":\"play_video.html?mvsMid=53,1,29,12&mvMid=m0026xz6a8g\"}],\"nameColor\":\"\"},\"imageProfix\":\"http:\\/\\/10.69.45.49:81\\/cms\\/uploadFile\\/image\\/\",\"code\":\"1\"}", BeanTopic.class);
            BeanTopic beanTopic = (BeanTopic) object;
            if (beanTopic != null && TextUtils.equals(beanTopic.getCode(), "1")) {
                subjectRecordListBeen = beanTopic.getData().getUtvgoSubjectRecordList();
                try {
                    type = Integer.parseInt(beanTopic.getData().getShowType());
                } catch (Exception e) {
                    type = 0;
                }
                ImageTool.loadImageWithUrl(this, DiffHostConfig.generateImageUrl(beanTopic.getData().getImgUrl()), ivBg);
//                if(ApkUtil.isDebug(TopicActivity.this)){
//                    // TODO: 2019/3/8 模板类型测试
////                    type=3;
//                }
                showTopic(type, beanTopic);
                stat("专题-" + beanTopic.getData().getName());
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取数据失败，请稍后重试", null);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (isRecommendExit) {
            setResult(RESULT_CANCELED);
        } else {
            setResult(RESULT_OK);
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseImageViewResouce();
    }

    private void releaseImageViewResouce() {
        for (int i = 0; i < flTopicContent.getChildCount(); i++) {
            ImageView imageView = null;
            View view = flTopicContent.getChildAt(i);
            if (view instanceof ImageView) {
                imageView = (ImageView) view;
            } else {
                imageView = (ImageView) view.findViewById(R.id.iv_icon);
            }

            if (imageView == null) return;
            Drawable drawable = imageView.getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
            }
        }
        System.gc();
    }

}
