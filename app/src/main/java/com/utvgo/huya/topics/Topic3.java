package com.utvgo.huya.topics;

import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.utvgo.huya.R;
import com.utvgo.huya.activity.TopicActivity;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.ImageTool;
import com.utvgo.huya.utils.XLog;


import java.util.ArrayList;
import java.util.List;


public class Topic3 {
    private TopicActivity activity;
    private LayoutInflater inflater;
    private List<View> views = new ArrayList<>();

    public Topic3(TopicActivity activity) {
        activity.getTopicDefault().setVisibility(View.GONE);
        activity.getLayoutTopic3().setVisibility(View.VISIBLE);
        this.activity = activity;
        activity.needBringFront = false;
        inflater = LayoutInflater.from(activity);
        setIdsAndListener();
    }

    private void setIdsAndListener() {
        int currentId = -1;
        for (int parent = 0; parent < activity.getLayoutTopic3().getChildCount(); parent++) {
            LinearLayout viewParent = (LinearLayout) activity.getLayoutTopic3().getChildAt(parent);
            for (int child = 0; child < viewParent.getChildCount(); child++) {
                View view = viewParent.getChildAt(child);
                currentId++;
                view.setId(currentId);
                view.setOnClickListener(activity);
                view.setOnFocusChangeListener(activity);
            }
        }
    }

    public void addContent(BeanTopic topic) {
        if (topic == null)
            return;
        if (topic.getData() == null)
            return;
        List<BeanTopic.DataBean.UtvgoSubjectRecordListBean> utvgoSubjectRecordList = topic.getData().getUtvgoSubjectRecordList();
        if (utvgoSubjectRecordList == null)
            return;
        for (int i = 0; i < 6; i++) {
            View v = activity.findViewById(i);
            if (i < utvgoSubjectRecordList.size()) {
                ImageView img = v.findViewById(R.id.img);
                TextView name = v.findViewById(R.id.name);
                ImageTool.loadImageWithUrl(activity, DiffHostConfig.generateImageUrl(utvgoSubjectRecordList.get(i).getImgUrl()), img);
                name.setText(utvgoSubjectRecordList.get(i).getName());
                try {
                    name.setTextColor(Color.parseColor(topic.getData().getNameColor()));
                    name.setBackgroundColor(Color.parseColor(topic.getData().getNameBgColor()));
                } catch (Exception e) {
                    XLog.e("Topic3", "颜色有误");
                }
            } else {
                v.setVisibility(View.GONE);
            }
        }
        activity.focusOn1stView(activity.findViewById(0), R.drawable.mains_f_2, (int) activity.getResources().getDimension(R.dimen.dp20), (int) activity.getResources().getDimension(R.dimen.dp20));
    }


    public void onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.ACTION_DOWN || keyCode == KeyEvent.ACTION_UP) {
            if (activity.getCurrentFocus() == activity.getLayoutTopic3().getChildAt(1)) {
                //翻页
            }
        }
    }

}
