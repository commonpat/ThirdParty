package com.utvgo.huya.topics;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.utvgo.huya.R;
import com.utvgo.huya.activity.TopicActivity;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.ImageTool;


import java.util.ArrayList;
import java.util.List;



public class Topic5 {
    private TopicActivity activity;
    private LayoutInflater inflater;
    private List<ImageView> imageViews = new ArrayList<>();
    private List<ImageView> imageFocusViews = new ArrayList<>();
    private ImageView backFocusImage, backImage, moreImage, moreFocusImage;

    public Topic5(TopicActivity activity) {
        this.activity = activity;
        activity.getTopicDefault().setVisibility(View.GONE);
        activity.getLayoutTopic5().setVisibility(View.VISIBLE);
        activity.needBringFront = false;
        inflater = LayoutInflater.from(activity);
        init();
    }

    private void init() {
        backFocusImage = (ImageView) activity.findViewById(R.id.back_focus);
        backImage = (ImageView) activity.findViewById(R.id.back);
        moreFocusImage = (ImageView) activity.findViewById(R.id.more_focus);
        moreImage = (ImageView) activity.findViewById(R.id.more);
        imageViews.add((ImageView) activity.findViewById(R.id.i1));
        imageViews.add((ImageView) activity.findViewById(R.id.i2));
        imageViews.add((ImageView) activity.findViewById(R.id.i3));
        imageViews.add((ImageView) activity.findViewById(R.id.i4));
        imageViews.add((ImageView) activity.findViewById(R.id.i5));
        imageViews.add((ImageView) activity.findViewById(R.id.i6));
        int currentId = -1;
        for (int i = 0; i < imageViews.size(); i++) {
            currentId++;
            imageViews.get(i).setId(currentId);
        }
        imageFocusViews.add((ImageView) activity.findViewById(R.id.if1));
        imageFocusViews.add((ImageView) activity.findViewById(R.id.if2));
        imageFocusViews.add((ImageView) activity.findViewById(R.id.if3));
        imageFocusViews.add((ImageView) activity.findViewById(R.id.if4));
        imageFocusViews.add((ImageView) activity.findViewById(R.id.if5));
        imageFocusViews.add((ImageView) activity.findViewById(R.id.if6));
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
                activity.finish();
            }
        });
        moreFocusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    public void addContent(BeanTopic topic) {
        if (topic == null)
            return;
        if (topic.getData() == null)
            return;
        List<BeanTopic.DataBean.UtvgoSubjectRecordListBean> utvgoSubjectRecordList = topic.getData().getUtvgoSubjectRecordList();
        if (utvgoSubjectRecordList == null)
            return;
        for (int i = 0; i < utvgoSubjectRecordList.size(); i++) {
            ImageTool.loadImageWithUrl(activity, DiffHostConfig.generateImageUrl(utvgoSubjectRecordList.get(i).getImgUrl()), imageViews.get(i));
            imageViews.get(i).setOnClickListener(activity);
            final int d = i;
            imageViews.get(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        imageFocusViews.get(d).setVisibility(View.VISIBLE);
                    } else {
                        imageFocusViews.get(d).setVisibility(View.GONE);
                    }
                }
            });
            if (i == 0) {
                imageViews.get(i).requestFocus();
            }
        }
        ImageTool.loadImageWithUrl(activity, DiffHostConfig.generateImageUrl(topic.getData().getImgUrl1()), moreFocusImage);
        ImageTool.loadImageWithUrl(activity, DiffHostConfig.generateImageUrl(topic.getData().getImgUrl2()), moreImage);
        ImageTool.loadImageWithUrl(activity, DiffHostConfig.generateImageUrl(topic.getData().getImgUrl3()), backFocusImage);
        ImageTool.loadImageWithUrl(activity, DiffHostConfig.generateImageUrl(topic.getData().getImgUrl4()), backImage);
    }
}
