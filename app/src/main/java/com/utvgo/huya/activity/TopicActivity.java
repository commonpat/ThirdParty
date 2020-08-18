package com.utvgo.huya.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.BuildConfig;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.constant.ConstantEnum;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.template.Topic1;
import com.utvgo.huya.template.Topic2;
import com.utvgo.huya.template.Topic3;
import com.utvgo.huya.template.Topic5;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public List<OpItem> subjectRecordListBeen;

    protected String topicId;

    public static void show(final Context context, final String topicId, final String style)
    {
        Intent intent = new Intent(context, TopicActivity.class);
        intent.putExtra("topicId", topicId);
        intent.putExtra("type", style);
        context.startActivity(intent);
    }

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

        this.topicId = getIntent().getStringExtra("topicId");
        if(TextUtils.isEmpty(this.topicId))
        {
            this.topicId = "159";
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (topic3 != null) {
            topic3.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void initView(BeanTopic beanTopic) {
        int allWidth = 0;
        for (int i = 0; i < subjectRecordListBeen.size(); i++) {
            View itemTopic = View.inflate(this, R.layout.item_topic, null);
            itemTopic.setOnFocusChangeListener(this);
//            itemTopic.setOnFocusChangeListener(changePage);
            itemTopic.setOnClickListener(this);
            itemTopic.setId(i);
            //2020/8/4 修复滚动子视图多的时候光标错乱的问题，指定聚焦视图
            itemTopic.setNextFocusRightId(i+1);
            itemTopic.setNextFocusLeftId(i-1);

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

            OpItem bean = subjectRecordListBeen.get(i);
            tvName.setText(bean.getName());
            loadImage(ivIcon, DiffConfig.generateImageUrl(bean.getImgUrl()));
        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(allWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        flTopicContent.setLayoutParams(params);
        focusOn1stView(findViewById(0), R.mipmap.border_focus_style_default,
                (int) getResources().getDimension(R.dimen.dp50),
                (int) getResources().getDimension(R.dimen.dp58));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){

            case R.id.back_focus:
                finish();
                break;
            case R.id.more_focus:
                int moreFocusIndex=6;
                final int defaultSelectedLabelId = -1;
                OpItem selectBeanMore = subjectRecordListBeen.get(moreFocusIndex);

                try{
//                    if(BuildConfig.DEBUG){
//                    int channelId=35;
//                    int packageId=29;
//                    MediaListActivity.show(this, channelId,null,packageId ,defaultSelectedLabelId);
//                }
                if(selectBeanMore.getHref()!= null||!"".equals(selectBeanMore.getHref())) {
                    Uri uri = Uri.parse(selectBeanMore.getHref());
                    String  channelId = uri.getQueryParameter("channelId");
                    String  packageId = uri.getQueryParameter("pkId");
                    if(channelId==null||"".equals(channelId)){ channelId="35";}
                    if(packageId==null||"".equals(packageId)){packageId="29";}
                    MediaListActivity.show(TopicActivity.this, Integer.valueOf(channelId), null, Integer.valueOf(packageId), defaultSelectedLabelId);
                }}catch (Exception e){
                    e.printStackTrace();
                }
                break;

            default:
                int playIndex = view.getId();
                OpItem selectBean = subjectRecordListBeen.get(playIndex);

                if(!actionOnOp(selectBean))
                {
                    playVideoFullScreen(playIndex);
                }
                break;
        }
    }


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
                    borderView.setBorderBitmapResId(R.mipmap.border_focus_style_default, -10, 0);
                }
            }

        }
        super.onFocusChange(v, hasFocus);
    }

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

    public void playVideoFullScreen(int playIndex) {
        ArrayList<ProgramInfoBase> list = new ArrayList<>();
        int channelId = 0;
        int playPosition = 0;
        for (int i = 0; i < subjectRecordListBeen.size(); i++) {

            OpItem bean = subjectRecordListBeen.get(i);
            ConstantEnum.OpType opType = ConstantEnum.OpType.fromTypeString(bean.getHrefType());
            if(opType == ConstantEnum.OpType.program)
            {
                ProgramInfoBase programInfoBase = new ProgramInfoBase();
                programInfoBase.setName(bean.getName());

                String href = bean.getHref();
                try{
                    Uri uri = Uri.parse(href);
                    String pkIdString = uri.getQueryParameter("pkId");
                    int pkId = StringUtils.intValueOfString(pkIdString);
                    if(pkId > 0)
                    {
                        programInfoBase.setPkId(pkId);
                        String channelIdString = uri.getQueryParameter("channelId");
                        channelId = StringUtils.intValueOfString(channelIdString);
                        programInfoBase.setChannelId(channelId);
                        list.add(programInfoBase);
                    }

                    if(i == playIndex)
                    {
                        playPosition = list.size() - 1;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(!list.isEmpty())
        {
            PlayVideoActivity.play(this, list, playPosition, false);
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

    void showTopicData(final BeanTopic bean)
    {
        subjectRecordListBeen = bean.getUtvgoSubjectRecordList();
        type = StringUtils.intValueOfString(bean.getShowType());
        loadImage(ivBg, DiffConfig.generateImageUrl(bean.getImgUrl()));
        showTopic(type, bean);
        stat("专题-" + bean.getName());
    }

    private void showTopic(int type, BeanTopic beanTopic) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //themId=151&styleID=5
    public void getData() {
        final Context context = this;
        NetworkService.defaultService().fetchTopicData(this, topicId, new JsonCallback<BaseResponse<BeanTopic>>() {
            @Override
            public void onSuccess(Response<BaseResponse<BeanTopic>> response) {
                BaseResponse<BeanTopic> bean = response.body();
                if(bean != null && bean.isOk())
                {
                    showTopicData(bean.getData());
                }
                else
                {
                    HiFiDialogTools.getInstance().showtips(context, "获取数据失败，请稍后重试", null);
                }
            }
        });
    }
}
