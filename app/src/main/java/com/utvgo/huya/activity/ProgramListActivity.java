package com.utvgo.huya.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.LabelInfo;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.beans.ProgramListData;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.views.SmoothVorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.utvgo.huya.constant.ConstantEnumHuya.VIDEOLIST;
import static com.utvgo.huya.utils.ViewUtil.runText;

/**
 * @author wzb
 * @description:
 * @date : 2020/1/7 14:38
 */
public class ProgramListActivity extends BasePageActivity{
    @BindView(R.id.content_up)
    ImageView contentUp;
    @BindView(R.id.content_down)
    ImageView contentDown;
    @BindView(R.id.left_down)
    ImageView leftDown;
    @BindView(R.id.leftBar_labels)
    FrameLayout leftBarLabels;
    @BindView(R.id.program_content)
    FrameLayout programContent;
    @BindView(R.id.program_title)
    TextView programTitle;
    @BindView(R.id.smoothVoriztalScroll)
    SmoothVorizontalScrollView smoothVorizontalScrollView;
    @BindView(R.id.image_loading)
    ImageView imageLoading;

    private int packageId;
    private int channelId;
    private String channelName;
    private int defaultSelectedLabelId;
    private int defaultSelectedLabelIdIndex;
    private ArrayList<LabelInfo> labelInfoList=new ArrayList<>();
    private ArrayList<View> leftViewList=new ArrayList<>();
    private int pageSize = 1000;
    private List<ProgramInfoBase> programList =new ArrayList<>();

    public static void show(final Context context,
                            final int channelId,
                            final String channelName,
                            final int packageId,
                            final int defaultSelectedLabelId){
        Intent intent = new Intent(context,ProgramListActivity.class);
        intent.putExtra("channelId",channelId);
        intent.putExtra("channelName",channelName);
        intent.putExtra("packageId",packageId);
        intent.putExtra("defaultSelectedLabelId",defaultSelectedLabelId);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_program);
        ButterKnife.bind(this);
        createBorderView(this);

        this.channelId = getIntent().getIntExtra("channelId",34);
        this.packageId = getIntent().getIntExtra("packageId",34);
        this.channelName = getIntent().getStringExtra("channelName");
        this.defaultSelectedLabelId = getIntent().getIntExtra("defaultSelectedLabelId",0);
         fetchLabels();
        programTitle.setText(channelName);
        imageLoading.setVisibility(View.VISIBLE);
        stat(channelName+"节目列表",VIDEOLIST);
    }

    private void fetchLabels() {

        NetworkService.defaultService().fetchLabels(this, channelId, this.packageId, new JsonCallback<BaseResponse<List<LabelInfo>>>() {
            @Override
            public void onSuccess(Response<BaseResponse<List<LabelInfo>>> response) {
                BaseResponse<List<LabelInfo>> data = response.body();
                if(data.getData()==null||data.getData().size()==0){
                    HiFiDialogTools.getInstance().showtips(ProgramListActivity.this, "没有配置数据", null);
                    return;
                }
                if (data.isOk()) {
                    initLabels(data.getData());
                    updateLeftBar(data);
                   int index = -1;
                   for(int i =0;i<data.getData().size();i++) {
                       if(data.getData().get(i).getLabelId()==defaultSelectedLabelId) {
                           index = i;
                           break;
                       }
                       //updateContent();
                   }
                   if(index<0){
                       index=0;
                   }
                   defaultSelectedLabelIdIndex = index;
                   focusLabel(defaultSelectedLabelIdIndex);
                   fetchProgramListWithLabel(defaultSelectedLabelIdIndex);
                }
            }
        });
    }

    private void focusLabel(int defaultSelectedLabelIdIndex) {
        showViewByHandler(leftViewList.get(defaultSelectedLabelIdIndex));
        ((TextView)(((LinearLayout)(leftViewList.get(defaultSelectedLabelIdIndex))).getChildAt(0))).setTextColor(getResources().getColor(R.color.yellow));
    }

    private void initLabels(List<LabelInfo> data) {
        if(data!=null&&!data.isEmpty()) {
            labelInfoList.clear();
            labelInfoList.addAll(data);
        }
    }

    private void fetchContent(){

    }

    private void updateLeftBar(BaseResponse<List<LabelInfo>> data) {

        int topMargin =20;
        for (int i=0;i<data.getData().size();i++){
            View itemView = View.inflate(this,R.layout.program_left_item,null);
            itemView.setId(3000+i);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: left");
                    int checkId;
                    int labelId;
                    if(v instanceof LinearLayout){
                        if(((ViewGroup)v.getParent()).getId() == R.id.leftBar_labels) {
                            checkId = v.getId();
                            updateLabelStatus(checkId);
                            defaultSelectedLabelIdIndex = checkId - 3000;
                            unbindDrawables(programContent);
                            fetchProgramListWithLabel(defaultSelectedLabelIdIndex);
                        }

                    }
                }
            });
            leftViewList.add(itemView);
            itemView.setOnFocusChangeListener(this);
            TextView textView = itemView.findViewById(R.id.left_label);
            textView.setText(data.getData().get(i).getName());
            FrameLayout.LayoutParams params =new FrameLayout.LayoutParams((int)getResources().getDimension(R.dimen.dp165),(int)getResources().getDimension(R.dimen.dp45));
            topMargin = 20+(int)getResources().getDimension(R.dimen.dp40)*i+(int)getResources().getDimension(R.dimen.dp20)*i;
           // Log.d(TAG, "updateLeftBar: topMargin"+topMargin);

            params.topMargin=topMargin;
            leftBarLabels.addView(itemView,params);

        }

    }
    private  void fetchProgramListWithLabel(int id){
          if(id>=0&&id<labelInfoList.size()) {
              LabelInfo labelInfo = labelInfoList.get(id);
              imageLoading.setVisibility(View.VISIBLE);
              NetworkService.defaultService().fetchProgramListWithLabel(this, this.channelId, labelInfo.getLabelId(), pageNo, 30, new JsonCallback<BaseResponse<ProgramListData>>() {
                  @Override
                  public void onSuccess(Response<BaseResponse<ProgramListData>> response) {
                      BaseResponse<ProgramListData> programListDataBaseResponse=response.body();
                      programList = programListDataBaseResponse.getData().getPrograms();
                      updateContent(programList,pageNo);
                      imageLoading.setVisibility(View.INVISIBLE);
                  }
              });
          }
    }

    private void updateContent(final List<ProgramInfoBase> programLists,int pageNo) {
         int topMargin = 0;
         int leftMargin = 0;
         int allHeight = 0;
         int allWidth=0;
        for (int i=(pageNo-1)*30;i<programLists.size();i++){
            View itemView = View.inflate(this,R.layout.program_content_item,null);
            ((ViewGroup)itemView).getChildAt(0).setId(1000+i);
            ((ViewGroup)itemView).getChildAt(0).setNextFocusDownId(1000+i+3);
            ((ViewGroup)itemView).getChildAt(0).setNextFocusUpId(1000+i-3);
            itemView.setOnFocusChangeListener(this);
            ((ViewGroup)itemView).getChildAt(0).setOnFocusChangeListener(this);
            ((ViewGroup)itemView).getChildAt(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i=(v).getId();
                    programList.get(i-1000);
                    PlayVideoActivity.play(ProgramListActivity.this, (ArrayList<ProgramInfoBase>) programList,i-1000,false);
                }
            });
            ImageView imageView = itemView.findViewById(R.id.content_image);
            TextView textView = itemView.findViewById(R.id.content_name);
            loadImage(imageView,programList.get(i).getImageSmall());
            textView.setText(programList.get(i).getName());
            textView.setSingleLine();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int pos1 = i/3;
            int pos2 = i%3;

            topMargin = (int)getResources().getDimension(R.dimen.dp20)*pos1+(int)getResources().getDimension(R.dimen.dp180)*pos1;
            leftMargin = (int)getResources().getDimension(R.dimen.dp20)*pos2+(int)getResources().getDimension(R.dimen.dp250)*pos2;
            params.topMargin = topMargin;
            params.leftMargin = leftMargin;
            allHeight = topMargin+(int)getResources().getDimension(R.dimen.dp200);
            allWidth = leftMargin+(int)getResources().getDimension(R.dimen.dp250);
            programContent.addView(itemView,params);
            if(pos2==0){
                View g = ((ViewGroup) itemView).getChildAt(0);
                g.setNextFocusLeftId(leftBarLabels.getChildAt(defaultSelectedLabelIdIndex).getId());
                (((FrameLayout)itemView).getChildAt(0)).setNextFocusLeftId((leftViewList.get(defaultSelectedLabelIdIndex)).getId());
            }
        }
        findViewById(R.id.program_content).setFocusable(false);
        findViewById(R.id.smoothVoriztalScroll).setFocusable(false);
        traversalView(this);
    }


    @Override
    void getData() {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
       // Log.d(TAG, "onFocusChange: "+v.getId());
        try {
            if (v instanceof LinearLayout) {
                if (((ViewGroup) v.getParent()).getId() == R.id.leftItem) {
                    borderView.setBorderBitmapResId(0);
                } else {
                    borderView.setBorderBitmapResId(R.mipmap.border_focus_style_default);
                }
                runText((ViewGroup) v, hasFocus);

            } else {
                borderView.setBorderBitmapResId(R.mipmap.border_focus_style_default);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onFocusChange(v, hasFocus);
    }

   //@Override
    public void onClick(View view) {
        int checkId;
        int labelId;
        if(view instanceof LinearLayout){
            if(((ViewGroup)view.getParent()).getId() == R.id.leftBar_labels) {
                 checkId = view.getId();
                 updateLabelStatus(checkId);
                 defaultSelectedLabelIdIndex = checkId - 3000;
                 fetchProgramListWithLabel(defaultSelectedLabelIdIndex);
            }

        }


        super.onClick(view);
    }

    private void updateLabelStatus(int checkId) {
        TextView leftText = (TextView) ((ViewGroup)leftViewList.get(checkId-3000)).getChildAt(0);
        TextView oldLeftText = (TextView) ((ViewGroup)leftViewList.get(defaultSelectedLabelIdIndex)).getChildAt(0);
        leftText.setTextColor(getResources().getColor(R.color.yellow));
        oldLeftText.setTextColor(getResources().getColor(R.color.white));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {
            if(getCurrentFocus().getParent() == null){
                return false;
            }
            View view = (ViewGroup) getCurrentFocus().getParent();
            //Log.d(TAG, getCurrentFocus().getId()+"onKeyDown: "+((labelInfoList.size() - 1)+3000));
            if(view.getId() == R.id.leftBar_labels) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN && (getCurrentFocus().getId()) == ((labelInfoList.size() - 1)+3000)) {
                    return true;
                }
                if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP && (getCurrentFocus().getId()) == 3000) {
                    return true;
                }
            }
            }catch (Exception e){
                e.printStackTrace();
            }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        unbindDrawables(programContent);
        clearCache();
        super.onDestroy();

    }
    public void  unbindDrawables(View view){
        if(view.getBackground() != null){
            view.getBackground().setCallback(null);
        }
        if(view instanceof ViewGroup){
            for (int i = 0;i < ((ViewGroup)view).getChildCount(); i++){
                unbindDrawables(((ViewGroup)view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }
}
