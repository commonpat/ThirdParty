package com.utvgo.huya.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanStatistics;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.beans.ProgramContent;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.beans.TPageData;
import com.utvgo.huya.beans.VideoInfo;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListActivity extends BaseActivity {

    @BindView(R.id.tv_count)
    TextView tvCount;

    private String showType = "";
    private boolean isExperience;

    private int freeTime = -1;
    private long nowTime = 0;
    private int playIndex = 0;

    public List<OpItem> subjectRecordListBeen = new ArrayList<>();

    private int pageIndex = 0;
    final int pageSize = 4;

    private final List<RelativeLayout> itemViewArray = new ArrayList<>();

    private int pkId = 156, currentPage = 0, totalPage = 0;
    BeanTopic data = null;

    public static void show(final Context context, final int pkId)
    {
        Intent intent = new Intent(context, CategoryListActivity.class);
        intent.putExtra("pkId", pkId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        ButterKnife.bind(this);

        this.pkId = getIntent().getIntExtra("pkId", pkId);

        final int array[] = {R.id.fl_item_0, R.id.fl_item_1, R.id.fl_item_2, R.id.fl_item_3, R.id.fl_item_4, R.id.fl_item_5,
                R.id.fl_item_6, R.id.fl_item_7};
        for(int i = 0; i < array.length; i++)
        {
            this.itemViewArray.add((RelativeLayout)findViewById(array[i]));
        }



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(pkId == 156)
                {
                    findViewById(R.id.btn_tab_1).requestFocus();
                }
                else if(pkId == 157)
                {
                    findViewById(R.id.btn_tab_2).requestFocus();
                }
                else if(pkId == 158)
                {
                    findViewById(R.id.btn_tab_3).requestFocus();
                }
                loadData();
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean flag = false;
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            {
                View view = getCurrentFocus();
                if(view != null)
                {
                    int focusViewId = view.getId();
                    if(focusViewId == R.id.btn_fl_3 || focusViewId == R.id.btn_fl_7)
                    {
                        if(((this.currentPage + 1)*this.pageSize) < this.subjectRecordListBeen.size())
                        {
                            flag = true;
                            this.currentPage++;
                            updateItemCount();
                            updateItemContent();
                            findViewById(R.id.btn_fl_0).requestFocus();
                        }
                    }
                }
                break;
            }

            case KeyEvent.KEYCODE_DPAD_LEFT:
            {
                View view = getCurrentFocus();
                if(view != null)
                {
                    int focusViewId = view.getId();
                    if(focusViewId == R.id.btn_fl_0 || focusViewId == R.id.btn_fl_4)
                    {
                        if(this.currentPage > 0)
                        {
                            flag = true;
                            this.currentPage--;
                            updateItemCount();
                            updateItemContent();
                            findViewById(R.id.btn_fl_0).requestFocus();
                        }
                    }
                }
                break;
            }
        }
        if(!flag)
        {
            flag = super.onKeyDown(keyCode, event);
        }
        return flag;
    }


    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        switch (viewId)
        {
            case R.id.btn_tab_1:
            {
                this.pkId = 156;
                loadData();
                break;
            }

            case R.id.btn_tab_2:
            {
                this.pkId = 157;
                loadData();
                break;
            }

            case R.id.btn_tab_3:
            {
                this.pkId = 158;
                loadData();
                break;
            }

            case R.id.btn_fl_0:
            case R.id.btn_fl_1:
            case R.id.btn_fl_2:
            case R.id.btn_fl_3:
            case R.id.btn_fl_4:
            case R.id.btn_fl_5:
            case R.id.btn_fl_6:
            case R.id.btn_fl_7:
            {
                final int array[] = {R.id.btn_fl_0, R.id.btn_fl_1, R.id.btn_fl_2, R.id.btn_fl_3, R.id.btn_fl_4, R.id.btn_fl_5, R.id.btn_fl_6, R.id.btn_fl_7 };
                int index = Arrays.binarySearch(array, viewId);
                if(index >= 0)
                {
                    int playIndex = this.currentPage*pageSize + index;
                    acitonAt(playIndex);
                }
            }
        }
    }

    public void acitonAt(final int index) {
        OpItem opItem = this.subjectRecordListBeen.get(index);
        Uri uri = Uri.parse(opItem.getHref());
        String channelId = uri.getQueryParameter("channelId");
        MediaListActivity.show(this, StringUtils.intValueOfString(channelId),
                opItem.getName(), StringUtils.intValueOfString(AppConfig.PackageId), 0);
    }

    void updateItemCount()
    {
        String text = String.format("< %d/%d >", Math.min(this.currentPage + 1, this.totalPage), this.totalPage);
        tvCount.setText(text);

        //leftImageView.setImageResource((this.currentPage > 0)?R.mipmap.icon_previous:R.mipmap.icon_previous_disavle);
        //rightImageView.setImageResource(((this.currentPage + 1) >= (this.totalPage))?R.mipmap.icon_next_disable:R.mipmap.icon_next);
    }

    void updateItemContent()
    {
        for(int j = 0; j < itemViewArray.size(); j++)
        {
            RelativeLayout itemLayout = this.itemViewArray.get(j);
            int contentIndex = this.currentPage*pageSize + j;
            if(contentIndex < this.subjectRecordListBeen.size())
            {
                itemLayout.setVisibility(View.VISIBLE);
                OpItem bean = this.subjectRecordListBeen.get(contentIndex);
                for(int k = 0; k < itemLayout.getChildCount(); k++)
                {
                    View view = itemLayout.getChildAt(k);
                    if(view instanceof ImageView)
                    {
                        ImageView imageView = (ImageView)view;
                        String posterUrl = DiffConfig.generateImageUrl(bean.getImgUrl());
                        loadImage(imageView, posterUrl);
                    }
                }
            }
            else
            {
                itemLayout.setVisibility(View.INVISIBLE);
            }
        }
        findViewById(R.id.btn_fl_0).requestFocus();
    }


    /*
    *** Network
     */
    void loadData()
    {
        NetworkService.defaultService().fetchTopicData(this, "" + this.pkId, new JsonCallback<BaseResponse<BeanTopic>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<BeanTopic>> response) {
                        BaseResponse <BeanTopic> data = response.body();
                        if(data.isOk())
                        {
                            layout(data.getData());
                            stat("专辑播放-" + data.getData().getName());
                        }
                    }
                });
    }

    void layout(final BeanTopic bean)
    {
        if(bean == null)
        {
            return;
        }

        this.data = bean;
        subjectRecordListBeen.clear();
        subjectRecordListBeen.addAll(this.data.getUtvgoSubjectRecordList());

        this.currentPage = 0;
        this.totalPage = this.data.getUtvgoSubjectRecordList().size()/pageSize;
        if(this.data.getUtvgoSubjectRecordList().size()%pageSize > 0)
        {
            this.totalPage++;
        }

        updateItemCount();
        updateItemContent();
    }
}
