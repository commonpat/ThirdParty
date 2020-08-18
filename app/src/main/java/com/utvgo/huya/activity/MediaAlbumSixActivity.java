package com.utvgo.huya.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.HiFiDialogTools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wzb
 * @description:
 * @date : 2019/12/25 14:51
 */
public class MediaAlbumSixActivity extends BuyActivity{
    @BindView(R.id.activity_RootView)
    FrameLayout rootView;
    @BindView(R.id.video5)
    VideoView videoView;
    @BindView(R.id.vif)
    ImageView vif;
    @BindView(R.id.btn_more)
    ImageView btnMore;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    protected String topicId;
    private String showType = "";
    private boolean isExperience;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_five);
        ButterKnife.bind(this);
        createBorderView(this);
        traversal(rootView);
        this.topicId = getIntent().getStringExtra("topicId");
        loadData();
    }

    private void loadData() {
        NetworkService.defaultService().fetchTopicData(this, this.topicId, new JsonCallback<BaseResponse<BeanTopic>>() {
            @Override
            public void onSuccess(Response<BaseResponse<BeanTopic>> response) {
                BaseResponse<BeanTopic> beanTopic = response.body();
                if(beanTopic.isOk()&&beanTopic!=null){
                    showBeanTopic(beanTopic.getData());
                }else {
                    HiFiDialogTools.getInstance().showtips(MediaAlbumSixActivity.this,"获取数据有误，请稍后再试！",null);
                }
            }
        });
    }

    private void showBeanTopic(BeanTopic data) {
        for(int i=0;i<data.getUtvgoSubjectRecordList().size();i++) {
            View itemView = View.inflate(this, R.layout.album_5_item, null);
            itemView.setId(i);
            itemView.setOnFocusChangeListener(this);
            itemView.setOnClickListener(this);
            FrameLayout content = rootView.findViewById(R.id.album_5_list);
            content.addView(itemView);
        }
    }
}
