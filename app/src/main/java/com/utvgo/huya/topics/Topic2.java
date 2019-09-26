package com.utvgo.huya.topics;

import android.graphics.Color;
import android.util.Log;
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

import java.util.List;


public class Topic2 {
    private TopicActivity activity;
    private LayoutInflater inflater;

    public Topic2(TopicActivity activity) {
        activity.getTopicDefault().setVisibility(View.GONE);
        activity.getLayoutTopic2().setVisibility(View.VISIBLE);
        activity.needBringFront = false;
        inflater = LayoutInflater.from(activity);
        this.activity = activity;
    }

    public void addContent(BeanTopic topic) {
        if (topic == null)
            return;
        if (topic.getData() == null)
            return;
        List<BeanTopic.DataBean.UtvgoSubjectRecordListBean> utvgoSubjectRecordList = topic.getData().getUtvgoSubjectRecordList();
        if (utvgoSubjectRecordList == null)
            return;
        int[] margins = {(int) activity.getResources().getDimension(R.dimen.dp50), (int) activity.getResources().getDimension(R.dimen.dp70), 0, (int) activity.getResources().getDimension(R.dimen.dp120)};
        for (int i = 0; i < utvgoSubjectRecordList.size(); i++) {
            View view = inflater.inflate(R.layout.topic2_item, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
            params.topMargin = margins[i % 4];

            if (i != 0)
                params.leftMargin = (int) activity.getResources().getDimension(R.dimen.dp15);
            Log.e("LGH", "addContent: " + params.topMargin);

            ImageView img = view.findViewById(R.id.img);
            ImageTool.loadImageWithUrl(activity, DiffHostConfig.generateImageUrl(utvgoSubjectRecordList.get(i).getImgUrl()), img);

            TextView tv = view.findViewById(R.id.name);
            tv.setText(utvgoSubjectRecordList.get(i).getName());
            try {
                tv.setBackgroundColor(Color.parseColor(topic.getData().getNameColor()));
                tv.setTextColor(Color.parseColor(topic.getData().getNameBgColor()));
            } catch (Exception e) {
                XLog.e("LGH", "颜色有误");
            }

            if (i % 2 == 1) {
                img.bringToFront();
            }

            view.setId(i);
            view.setOnClickListener(activity);
            view.setOnFocusChangeListener(activity);
            activity.getMusicList2().addView(view, params);
        }
        activity.focusOn1stView(activity.getMusicList2().getChildAt(0), R.drawable.mains_f_3, (int) activity.getResources().getDimension(R.dimen.dp25), (int) activity.getResources().getDimension(R.dimen.dp50));

    }

}
