package com.utvgo.huya.template;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.utvgo.huya.R;
import com.utvgo.huya.activity.MVAlbumActivity;
import com.utvgo.huya.activity.PlayVideoActivity;

import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.beans.BeanWLAblumData;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.ImageTool;

import java.util.ArrayList;
import java.util.List;


public class MVAlbumTemplate1 {
    private final ImageView bg_mv_ablum;
    private final MVAlbumActivity mvAlbumActivity;
    private final FrameLayout list;
    private ArrayList<BeanUserPlayList.DataBean> playList = new ArrayList<>();
    private View.OnClickListener mvAlbumTemplateOnclickListener = new View.OnClickListener() {
        @SuppressLint("ResourceType")
        @Override
        public void onClick(View v) {
            if (v.getTag() != null && v.getTag() instanceof BeanWLAblumData.DataBean.VideoBean) {
                Intent intent = new Intent(mvAlbumActivity, PlayVideoActivity.class);
                intent.putExtra("playIndex", v.getId());
                intent.putExtra("fileType", 1);
                intent.putParcelableArrayListExtra("playList", playList);
                mvAlbumActivity.startActivity(intent);
            }
        }
    };

    public MVAlbumTemplate1(MVAlbumActivity mvAlbumActivity) {
        this.mvAlbumActivity = mvAlbumActivity;
        bg_mv_ablum = mvAlbumActivity.getBg();
        bg_mv_ablum.setVisibility(View.VISIBLE);
        View template1 = ((ViewGroup) bg_mv_ablum.getParent()).getChildAt(2);
        template1.setVisibility(View.VISIBLE);
        list = template1.findViewById(R.id.fl_topic_content);
        mvAlbumActivity.borderView.setBorderBitmapResId(R.drawable.mains_f_2_p9, 0);
    }

    public void addContent(BeanWLAblumData beanWLAblumData) {
        String[] pics = beanWLAblumData.getData().getImageBig().split("\\|");
        if (pics.length > 0 && !TextUtils.isEmpty(pics[0])) {
            ImageTool.loadImageWithUrl(bg_mv_ablum.getContext(), DiffHostConfig.generateImageUrl(pics[0]), bg_mv_ablum);
        }
        List<BeanWLAblumData.DataBean.VideoBean> albumData = beanWLAblumData.getData().getVideos();
        for (int i = 0; i < albumData.size(); i++) {
            BeanUserPlayList.DataBean data = new BeanUserPlayList.DataBean();
            data.setContentMid(String.valueOf(albumData.get(i).getVideoId()));
            data.setContentName(albumData.get(i).getName());
            playList.add(data);

            final View itemTopic = View.inflate(mvAlbumActivity, R.layout.item_topic, null);
            itemTopic.setOnFocusChangeListener(mvAlbumActivity);
            itemTopic.setId(i);
            itemTopic.setOnClickListener(mvAlbumTemplateOnclickListener);
            itemTopic.setNextFocusUpId(R.id.iv_video_focus);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) mvAlbumActivity.getResources().getDimension(R.dimen.dp250),
                    (int) mvAlbumActivity.getResources().getDimension(R.dimen.dp175));
            int leftMargin = (int) mvAlbumActivity.getResources().getDimension(R.dimen.dp55) + i * (int) mvAlbumActivity.getResources().getDimension(R.dimen.dp250);
            params.topMargin = (int) mvAlbumActivity.getResources().getDimension(R.dimen.dp430);
            params.leftMargin = leftMargin;
            params.rightMargin = (int) mvAlbumActivity.getResources().getDimension(R.dimen.dp60);
            list.addView(itemTopic, params);
            if (i == 0) {
                list.post(new Runnable() {
                    @Override
                    public void run() {
                        itemTopic.requestFocus();
                    }
                });
            }
            ImageView ivIcon = itemTopic.findViewById(R.id.iv_icon);
            TextView tvName = itemTopic.findViewById(R.id.tv_name);
            ImageTool.loadImageWithUrl(mvAlbumActivity, DiffHostConfig.generateImageUrl(albumData.get(i).getImageMid()), ivIcon);
            tvName.setText(albumData.get(i).getName());
            itemTopic.setTag(albumData.get(i));
        }
    }
}
