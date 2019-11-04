package com.utvgo.huya.template;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.huya.R;
import com.utvgo.huya.activity.TopicActivity;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.OpItem;
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
        moreFocusImage.setOnClickListener(activity);
    }

    public void addContent(BeanTopic topic) {
        if (topic == null)
            return;

        List<OpItem> utvgoSubjectRecordList = topic.getUtvgoSubjectRecordList();
        if (utvgoSubjectRecordList == null)
            return;
        for (int i = 0; i < imageViews.size(); i++) {
            ImageTool.loadImageWithUrl(activity, DiffConfig.generateImageUrl(utvgoSubjectRecordList.get(i).getImgUrl()), imageViews.get(i));
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
        ImageTool.loadImageWithUrl(activity, DiffConfig.generateImageUrl(utvgoSubjectRecordList.get(6).getImgUrl()), moreFocusImage);
        ImageTool.loadImageWithUrl(activity, DiffConfig.generateImageUrl(utvgoSubjectRecordList.get(7).getImgUrl()), moreImage);
        ImageTool.loadImageWithUrl(activity, DiffConfig.generateImageUrl(utvgoSubjectRecordList.get(8).getImgUrl()), backFocusImage);
        ImageTool.loadImageWithUrl(activity, DiffConfig.generateImageUrl(utvgoSubjectRecordList.get(9).getImgUrl()), backImage);
    }
}
