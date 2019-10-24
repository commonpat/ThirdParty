package com.utvgo.huya.template;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.utvgo.huya.R;
import com.utvgo.huya.activity.TopicActivity;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.OpItem;

import java.util.List;

public class Topic1 {
    private TopicActivity activity;
    private LayoutInflater inflater;

    public Topic1(TopicActivity activity) {
        activity.getTopicDefault().setVisibility(View.GONE);
        activity.getLayoutTopic1().setVisibility(View.VISIBLE);
        this.activity = activity;
        changeDisplayAreaSize();

        activity.needBringFront = false;
        inflater = LayoutInflater.from(activity);
    }

    private void changeDisplayAreaSize() {
        WindowManager wm = activity.getWindowManager();
        ViewGroup.LayoutParams layoutParams = activity.getDisplayArea().getLayoutParams();
        layoutParams.height = wm.getDefaultDisplay().getHeight() * 585 / 720;
        layoutParams.width = wm.getDefaultDisplay().getWidth() * 360 / 1280;
        activity.getDisplayArea().setLayoutParams(layoutParams);
    }

    public void addContent(BeanTopic topic) {
        if (topic == null)
            return;

        List<OpItem> utvgoSubjectRecordList = topic.getUtvgoSubjectRecordList();
        if (utvgoSubjectRecordList == null)
            return;
        for (int i = 0; i < utvgoSubjectRecordList.size(); i++) {
            View view = inflater.inflate(R.layout.topic1_item, null);
            TextView song = view.findViewById(R.id.song);
            TextView singer = view.findViewById(R.id.singer);
            song.setText(utvgoSubjectRecordList.get(i).getName().trim());
            singer.setText(utvgoSubjectRecordList.get(i).getDes().trim());
            view.setId(i);
            view.setOnClickListener(activity);
            view.setOnFocusChangeListener(activity);
            activity.getMusicList1().addView(view, -1, -2);
            activity.focusOn1stView(activity.getMusicList1().getChildAt(0), R.mipmap.border_focus_style_default, (int) activity.getResources().getDimension(R.dimen.dp20),
                    (int) activity.getResources().getDimension(R.dimen.dp6));
        }
    }

}
