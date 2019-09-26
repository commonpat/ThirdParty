package com.utvgo.huya.template;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


public class MVAlbumTemplate2 {
    private final ImageView bg_mv_ablum;
    private final MVAlbumActivity mvAlbumActivity;
    private final LinearLayout list;
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

    public MVAlbumTemplate2(MVAlbumActivity mvAlbumActivity) {
        this.mvAlbumActivity = mvAlbumActivity;
        mvAlbumActivity.needBringFront = false;
        bg_mv_ablum = mvAlbumActivity.getBg();
        bg_mv_ablum.setVisibility(View.VISIBLE);
        View template2 = ((ViewGroup) bg_mv_ablum.getParent()).getChildAt(3);
        template2.setVisibility(View.VISIBLE);
        list = template2.findViewById(R.id.music_list1);
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

            final View view = mvAlbumActivity.getLayoutInflater().inflate(R.layout.topic1_item, null);
            TextView song = view.findViewById(R.id.song);
            TextView singer = view.findViewById(R.id.singer);
            song.setText(albumData.get(i).getName().trim());
            singer.setText(albumData.get(i).getAliasName().trim());
            view.setId(i);
            view.setOnClickListener(mvAlbumTemplateOnclickListener);
            view.setOnFocusChangeListener(mvAlbumActivity);
            list.addView(view, -1, -2);
            view.setTag(albumData.get(i));
            if (i == 0) {
                list.post(new Runnable() {
                    @Override
                    public void run() {
                        view.requestFocus();
                    }
                });
            }
        }
    }
}
