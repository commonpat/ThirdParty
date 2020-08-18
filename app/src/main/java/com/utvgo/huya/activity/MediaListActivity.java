package com.utvgo.huya.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.config.AppConfig;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.XLog;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.LabelInfo;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.beans.ProgramListData;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ImageTool;
import com.utvgo.huya.utils.StringUtils;
import com.utvgo.huya.utils.Tools;
import com.utvgo.huya.views.SmoothHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MediaListActivity extends BasePageActivity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.fl_type)
    FrameLayout flType;

    @BindView(R.id.tv_page)
    TextView tvPage;

    @BindView(R.id.shsv_type)
    SmoothHorizontalScrollView shsvType;

    @BindView(R.id.fl_filter)
    FrameLayout flFilter;
    @BindView(R.id.fra_had_left)
    ImageView fraHadLeft;
    @BindView(R.id.fra_had_right)
    ImageView fraHadRight;

    String channelName = "主机游戏";
    private int channelId = 48;
    private int packageId = StringUtils.intValueOfString(AppConfig.PackageId);

    private final int pageSize = 8;

    private ImageView[] ivList = new ImageView[pageSize];
    private LinearLayout[] itemList = new LinearLayout[pageSize];
    private ArrayList<CheckBox> cbList = new ArrayList<>();
    private TextView[] tvList = new TextView[pageSize];
    private TextView[] freeList = new TextView[pageSize];

    ArrayList<LabelInfo> labelInfoList = new ArrayList<>();
    ArrayList<ProgramInfoBase> programList = new ArrayList<>();

    private boolean isFirstCome = true;
    private boolean isNextPage = true;

    int defaultSelectedLabelIndex = 0;
    int defaultSelectedLabelId = 0;

    public static void show(final Context context,
                            final int channelId,
                            final String channelName,
                            final int packageId,
                            final int defaultSelectedLabelId) {
        Intent intent = new Intent(context, MediaListActivity.class);
        intent.putExtra("channelId", channelId);
        intent.putExtra("channelName", channelName);
        intent.putExtra("packageId", packageId);
        intent.putExtra("defaultSelectedLabelId", defaultSelectedLabelId);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mv);
        ButterKnife.bind(this);

        createBorderView(this);
        scale = 1.0f;

        for (int i = 0; i < flContent.getChildCount(); i++) {
            View view = flContent.getChildAt(i);
            if (view instanceof LinearLayout) {
                itemList[i] = (LinearLayout) view;
                ivList[i] = (ImageView) ((LinearLayout) view).getChildAt(0);
                tvList[i] = (TextView) ((LinearLayout) view).getChildAt(1);
            }
        }
        ImageView fra_had_right = findViewById(R.id.fra_had_right);
        ImageView fra_had_left = findViewById(R.id.fra_had_left);

        flType.setVisibility(View.VISIBLE);
        flFilter.setVisibility(View.VISIBLE);

        final Intent intent = getIntent();
        this.channelId = intent.getIntExtra("channelId", this.channelId);
        this.channelName = intent.getStringExtra("channelName");
        this.packageId = intent.getIntExtra("packageId", this.packageId);

        this.defaultSelectedLabelId = intent.getIntExtra("defaultSelectedLabelId", this.defaultSelectedLabelId);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fetchLabels();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        stat("节目列表-" + this.channelName);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.item1:
            case R.id.item2:
            case R.id.item3:
            case R.id.item4:
            case R.id.item5:
            case R.id.item6:
            case R.id.item7:
            case R.id.item8:
                //runText((ViewGroup) v, hasFocus);
                break;
        }
        super.onFocusChange(v, hasFocus);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean ret = false;
        if (focusView != null)
        {
            int viewId = focusView.getId();
            int code = event.getKeyCode();
            if ((viewId == R.id.item4 || viewId == R.id.item8) && code == KeyEvent.KEYCODE_DPAD_RIGHT) {//下一页
                loadPage(1);
                ret = true;
            } else if ((viewId == R.id.item1 || viewId == R.id.item5) && code == KeyEvent.KEYCODE_DPAD_LEFT) {//上一页
                loadPage(-1);
                ret = true;
            }
        }
        if(!ret)
        {
            ret = super.onKeyDown(keyCode, event);
        }
        return ret;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseImageViewResource();

        ivList = null;
        itemList = null;
        cbList.clear();
        cbList = null;
        tvList = null;
        freeList = null;

        handler.removeMessages(MSG_TURN_PAGE);
        handler = null;

        System.gc();
    }

    @OnClick({R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7, R.id.item8})
    public void onClick(View view) {
        int itemIndex = getItemIndex(view);
        PlayVideoActivity.play(this, this.programList, itemIndex, false);
    }

    View.OnClickListener labelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int newIndex= cbList.indexOf(view);
           // layoutWithLabels();
            pageNo=1;
            updateLabelsStatus(newIndex);
            XLog.d("fetchProgramListWithLabel 1");
            fetchProgramListWithLabel(newIndex);
        }
    };

    private void layoutWithLabels() {
        int leftPreMargin = 0;
        for (int i = 0; i < this.labelInfoList.size(); i++) {
            LabelInfo labelInfo = this.labelInfoList.get(i);
            String name = labelInfo.getName();
            CheckBox cb = (CheckBox) LayoutInflater.from(this).inflate(R.layout.item_top_title, null);
            cb.setText(name);
            cb.setId(10000+i);
            cb.setTag(i);
            cb.setOnClickListener(labelOnClickListener);
            TextPaint textPaint = cb.getPaint();
            int textPaintWidth = (int) textPaint.measureText(name);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) (textPaintWidth + getResources().getDimension(R.dimen.dp30)),
                    (int) getResources().getDimension(R.dimen.dp60));
            if (i == 0) {
                params.leftMargin = (int) getResources().getDimension(R.dimen.dp20);
            } else {
//                params.leftMargin = (int) getResources().getDimension(R.dimen.dp30) * this.labelInfoList.get(i - 1).getName().length() + leftPreMargin +
//                        (int) getResources().getDimension(R.dimen.dp30);


                int numberCount = 0;
                int chineseCount = 0;
                int englishCount = 0;
                for (int n = 0; n < this.labelInfoList.get(i - 1).getName().length(); n++) {
                    char ch = this.labelInfoList.get(i - 1).getName().charAt(n);
                    if (isChinese(ch)) {
                        chineseCount++;
                    } else {
                        numberCount++;
                    }

                    params.leftMargin = (int) getResources().getDimension(R.dimen.dp30) * chineseCount + (int) getResources().getDimension(R.dimen.dp15) * numberCount + leftPreMargin +
                            (int) getResources().getDimension(R.dimen.dp30);

                }
            }
            params.topMargin = (int) getResources().getDimension(R.dimen.dp20);
            leftPreMargin = params.leftMargin;
            cb.setLayoutParams(params);
            cbList.add(cb);
            flType.addView(cb);
        }
    }
    private boolean isChinese(char ch){
        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
        if(unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                ||unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B||unicodeBlock == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
        ){
            return true;
        }
        return false;
    }

    private void updateProgramListItem(final int totalPage, final int currentPage)
    {
        List<ProgramInfoBase> list = this.programList;

        for (int i = 0; i < list.size() && i < itemList.length; i++) {
            itemList[i].setVisibility(View.VISIBLE);
            runText(itemList[i], false);
            ProgramInfoBase dataBean = list.get(i);
            tvList[i].setText(dataBean.getName());
            ImageTool.loadImageWithUrl(this, DiffConfig.imageHost+dataBean.getImageSmall(), ivList[i]);
        }

        for (int n = list.size(); n < itemList.length; n++) {
            itemList[n].setVisibility(View.GONE);
        }
        pageTotal = totalPage;
        fraHadLeft.setVisibility((currentPage < 2) ? View.INVISIBLE : View.VISIBLE);
        fraHadRight.setVisibility((currentPage - totalPage == 0) ? View.INVISIBLE : View.VISIBLE);
        tvPage.setText("< " + Math.min(currentPage, totalPage) + "/" + totalPage + " >");

        if (totalPage <= 0) {
            showViewTip("该分类下暂无数据");
        } else {
            hideViewTip();
        }
    }

    private void loadPage(int pageNext) {
        if (pageNext > 0) {//下一页
            pageNo++;
            if (pageNo > pageTotal) {
                pageNo = 1;
            }
        } else if (pageNext < 0) {//上一页
            pageNo--;
            if (pageNo < 1) {
                pageNo = pageTotal;
            }
        }
        XLog.d("fetchProgramListWithLabel 2");
        fetchProgramListWithLabel(this.defaultSelectedLabelIndex);
    }

    private void runText(ViewGroup rootLayout, boolean isRunning) {
        if (rootLayout.getChildCount() > 1) {
            if (rootLayout.getChildAt(1) instanceof TextView) {
                TextView textView = (TextView) rootLayout.getChildAt(1);
                textView.setSingleLine();
                textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                textView.setMarqueeRepeatLimit(1);
                if (isRunning) {
                    textView.setSelected(true);
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setSelected(false);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void setItemUpFocusId(int upFocusId) {
        for (int i = 0; i < 4 && i < itemList.length; i++) {
            itemList[i].setNextFocusUpId(upFocusId);
            Log.d(TAG, "setItemUpFocusId: "+upFocusId);
        }
    }

    private void releaseImageViewResource() {
        for (int i = 0; i < ivList.length; i++) {
            ImageView imageView = ivList[i];
            if (imageView == null) return;
            Drawable drawable = imageView.getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
            }
        }
    }

    private int getItemIndex(View view) {
        for (int i = 0; i < itemList.length; i++) {
            if (itemList[i] == view) {
                return i;
            }
        }
        return -1;
    }

    void initLabels(final List<LabelInfo> list) {
        if (list != null && !list.isEmpty()) {
            this.labelInfoList.clear();
            this.labelInfoList.addAll(list);
            layoutWithLabels();
        }
        pageNo = 1;
    }

    void updateLabelsStatus(final int labelIndex)
    {
        int index = labelIndex;
        for (int i = 0; i < cbList.size(); i++) {
            cbList.get(i).setChecked(false);
        }
        if(index < 0 || labelIndex >= cbList.size())
        {
            index = 0;
        }

        if(index != defaultSelectedLabelIndex)
        {
            CheckBox oldView = cbList.get(defaultSelectedLabelIndex);
            oldView.setTextColor(getResources().getColor(R.color.white));

            CheckBox view = cbList.get(index);
            view.setTextColor(getResources().getColor(R.color.yellow));

            defaultSelectedLabelIndex = index;
        }else {
            CheckBox  v= cbList.get(index);
            v.setTextColor(getResources().getColor(R.color.yellow));
        }
    }

    void initProgramList(final List<ProgramInfoBase> list, final int totalPage, final int currentPage)
    {
        if (list != null && !list.isEmpty()) {
            this.programList.clear();
            this.programList.addAll(list);
            updateProgramListItem(totalPage, currentPage);
        }

        setItemUpFocusId(cbList.get(defaultSelectedLabelIndex).getId());
    }

    @Override
    void getData() {
        XLog.d("fetchProgramListWithLabel getData");
        fetchProgramListWithLabel(this.defaultSelectedLabelIndex);
    }

    //network
    void fetchLabels() {
        final Activity activity = this;
        NetworkService.defaultService().fetchLabels(this, channelId, this.packageId, new JsonCallback<BaseResponse<List<LabelInfo>>>() {
            @Override
            public void onSuccess(Response<BaseResponse<List<LabelInfo>>> response) {
                BaseResponse<List<LabelInfo>> data = response.body();
                if(data.getData()==null||data.getData().size()==0){
                    HiFiDialogTools.getInstance().showtips(MediaListActivity.this, "没有配置数据", null);
                    return;
                }
                if (data.isOk()) {
                        initLabels(data.getData());//标题栏
                    int index = -1;
                    for(int i = 0; i < data.getData().size(); i++)
                    {
                        LabelInfo labelInfo = data.getData().get(i);
                        if(labelInfo.getLabelId() == defaultSelectedLabelId)
                        {
                            index = i;
                            break;
                        }
                    }
                    if(index < 0)
                    {
                        index = 0;
                    }
                    defaultSelectedLabelIndex = index;
                    updateLabelsStatus(index);

                    if (labelInfoList.size() > 0) {
                        traversalView(activity);

                        if (defaultSelectedLabelIndex >= 0) {
                            showViewByHandler(cbList.get(defaultSelectedLabelIndex));
                        }
                    }
                    fetchProgramListWithLabel(defaultSelectedLabelIndex);
                }
            }
        });
    }

    void fetchProgramListWithLabel(int labelIndex)
    {
        if(labelIndex >= 0 && labelIndex < this.labelInfoList.size())
        {
            LabelInfo labelInfo = labelInfoList.get(labelIndex);
            if(labelInfo.getLabelId() > 0)
            {
                defaultSelectedLabelIndex = labelIndex;
                NetworkService.defaultService().fetchProgramListWithLabel(this, this.channelId, labelInfo.getLabelId(),
                        this.pageNo, this.pageSize, new JsonCallback<BaseResponse<ProgramListData>>() {
                            @Override
                            public void onSuccess(Response<BaseResponse<ProgramListData>> response) {
                                BaseResponse<ProgramListData> data = response.body();
                                if(data.isOk())
                                {
                                    initProgramList(data.getData().getPrograms(), data.getTotalPage(), data.getCurrentPage());
                                }
                            }
                        });
            }
        }

    }
}

