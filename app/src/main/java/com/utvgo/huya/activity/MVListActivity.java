package com.utvgo.huya.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanMvListByGenreId;
import com.utvgo.huya.beans.BeanTypeGenre2;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.utils.Appconfig;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ImageTool;
import com.utvgo.huya.views.SmoothHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MVListActivity extends PageActivity {

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

    private boolean isSingle;

    private String mvType = "1";//1为mv   2为超清  3为演唱会   4为儿歌  5收藏
    private String category = "1";//1为mv   2为超清  3为演唱会   4为儿歌
    private String channelId = "";
    private String genreId = ""; //顶部的类别的id
    private String isConcert = "0"; //是否是演唱会
    //    private int pageNo = 1;
    private int pageSize = 8;
    //    private int pageTotal = 1;
    private CheckBox selectCheckBox;
    private ImageView[] ivList = new ImageView[pageSize];
    private LinearLayout[] itemList = new LinearLayout[pageSize];
    private ArrayList<CheckBox> cbList = new ArrayList<>();
    private TextView[] tvList = new TextView[pageSize];
    private TextView[] freeList = new TextView[pageSize];

    private List<BeanTypeGenre2.dataBean> genreListBeen2 = null;
    private List<BeanMvListByGenreId.dataBean.programBean> mvListByGenreIdData = null;
    private ArrayList<BeanUserPlayList.DataBean> playHistoryList = null;
    private boolean isFirstCome = true;
    private boolean isNextPage = true;
    //private BeanSearchCondition beanSearchCondition = null;

    String intentDefaultSelectedLabelId = "";

    public static void show(final Context context, final boolean isSingle,
                            final String channelId,
                            final String category,
                            final String mvType,
                            final String defaultSelectedLabelId)
    {
        Intent intent = new Intent(context, MVListActivity.class);
        intent.putExtra("mvType", mvType);
        intent.putExtra("isSingle", false);
        intent.putExtra("category", category);
        intent.putExtra("channelId", channelId);
        intent.putExtra("labelId", defaultSelectedLabelId);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mv);
        ButterKnife.bind(this);
        isSingle = getIntent().getBooleanExtra("isSingle", false);
        createBorderView(this);
        scale = 1.0f;
        initView();
        if (getIntent().getStringExtra("channelId") != null) {
            channelId = getIntent().getStringExtra("channelId");
        }
        if (getIntent().getStringExtra("category") != null) {
            category = getIntent().getStringExtra("category");
        }
        mvType = getIntent().getStringExtra("mvType");
        intentDefaultSelectedLabelId = getIntent().getStringExtra("labelId");


         flType.setVisibility(View.VISIBLE);

         flFilter.setVisibility(View.VISIBLE);

         asyncHttpRequest.getGenreIdLabel(this, Appconfig.getKeyNo(this), channelId, "0", this, this);

    }



    @Override
    protected void onResume() {
        super.onResume();

        String statName = "";
        if (TextUtils.equals(mvType, "1")) {
            statName = "MV";
        } else if (TextUtils.equals(mvType, "3")) {
            statName = "演唱会";
        } else if (TextUtils.equals(mvType, "2")) {
            statName = "超清";
        } else if (TextUtils.equals(mvType, "4")) {
            statName = "儿歌";
        } else if (TextUtils.equals(mvType, "5")) { //收藏
            statName = "我的收藏-演唱会";
        } else if (TextUtils.equals(mvType, "6")) { //收藏
            statName = "我的收藏-精选专辑";
        } else if (TextUtils.equals(mvType, "7")) {
            statName = "已购音乐-MV";
        } else if (TextUtils.equals(mvType, "8")) {
            statName = "已购音乐-演唱会";
        } else if (TextUtils.equals(mvType, "10")) {
            statName = "KTV";
        }
        if (!TextUtils.isEmpty(statName)) {
            stat(statName);
        }
    }

    private void initView() {
        for (int i = 0; i < flContent.getChildCount(); i++) {
            View view = flContent.getChildAt(i);
//            view.setVisibility(View.GONE);

            if (view instanceof LinearLayout) {
                itemList[i] = (LinearLayout) view;
                if (isSingle) {
                    view.setOnFocusChangeListener(this);
                }
                ivList[i] = (ImageView) ((LinearLayout) view).getChildAt(0);
                tvList[i] = (TextView) ((LinearLayout) view).getChildAt(1);
                //freeList[i] = (TextView) ((LinearLayout) view).getChildAt(2);
                //freeList[i].setVisibility(View.GONE);
            }
        }
//        tvPage.setText("1/1");
//        showViewByHandler(itemList[0]);
    }

    private void initTopType2() {
        int leftPreMatgin = 0;
        for (int i = 0; i < genreListBeen2.size(); i++) {
            String name = genreListBeen2.get(i).getName();
            CheckBox cb = (CheckBox) LayoutInflater.from(this).inflate(R.layout.item_top_title, null);
            cb.setText(name);
            cb.setId(10000 + i);
            TextPaint textPaint = cb.getPaint();
            int textPaintWidth = (int) textPaint.measureText(name);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) (textPaintWidth + getResources().getDimension(R.dimen.dp30)),
                    (int) getResources().getDimension(R.dimen.dp60));
            if (i == 0) {
                params.leftMargin = (int) getResources().getDimension(R.dimen.dp20);
                cb.setOnClickListener(this);
            } else {
                params.leftMargin = (int) getResources().getDimension(R.dimen.dp30) * genreListBeen2.get(i - 1).getName().length() + leftPreMatgin +
                        (int) getResources().getDimension(R.dimen.dp30);
            }
            params.topMargin = (int) getResources().getDimension(R.dimen.dp20);
            leftPreMatgin = params.leftMargin;
            cb.setLayoutParams(params);
            cbList.add(cb);
            flType.addView(cb);
            cb.setOnFocusChangeListener(this);
        }

        if (genreListBeen2.size() > 0) {
            traversalView(this);

            int defaultSelectedIndex = 0;
            if(!TextUtils.isEmpty(intentDefaultSelectedLabelId))
            {
                for(int i = 0; i < genreListBeen2.size(); i++)
                {
                    if(TextUtils.equals(genreListBeen2.get(i).getLabelId() + "", intentDefaultSelectedLabelId))
                    {
                        defaultSelectedIndex = i;
                        break;
                    }
                }
            }

            showViewByHandler(cbList.get(defaultSelectedIndex));
        }
    }

    @Override
    public void getMvData() {
         asyncHttpRequest.getMvListByLabelId(this, Appconfig.getKeyNo(this), genreId, channelId, category, "", pageNo, pageSize, this, this);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (focusView != null && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((focusView.getId() == R.id.item4 || focusView.getId() == R.id.item8)
                    && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {//下一页
                getOtherData(1);
                if (itemList[0] != null) {
                    showViewByHandler(itemList[0]);
                }
                return true;
            } else if ((focusView.getId() == R.id.item1 || focusView.getId() == R.id.item4)
                    && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {//上一页
                getOtherData(-1);
                if (itemList[0] != null) {
                    showViewByHandler(itemList[0]);
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private void getOtherData(int pageNext) {
        if (pageNext > 0) {//下一页
            pageNo++;
            isNextPage = true;
            if (pageNo > pageTotal) {
                pageNo = pageTotal;
                return;
            }
        } else if (pageNext < 0) {//上一页
            pageNo--;
            isNextPage = false;
            if (pageNo < 1) {
                pageNo = 1;
                return;
            }
        }
        getMvData();
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
        if (hasFocus) {
            if (v instanceof CheckBox) {
                borderView.setBorderBitmapResId(0);
                scale = 1.0f;
                for (int i = 0; i < cbList.size(); i++) {
                    cbList.get(i).setChecked(false);
                }
                if (selectCheckBox != v) {
                    pageNo = 1;
                    if (cbList.contains(v)) {
                        int indexCB = cbList.indexOf(v);
                        if (genreListBeen2 != null) {
                            BeanTypeGenre2.dataBean bean = genreListBeen2.get(indexCB);
                            if (bean.getLabelId() == -1) {
                                return;
                            }
                            genreId = bean.getLabelId() + "";
                            getMvData();
                        } else {
//                            BeanTypeGenre.GenreListBean bean = genreListBeen.get(indexCB);
//                            if (bean.getLabelId() == -1) {
//                                return;
//                            }
//                            genreId = bean.getLabelId() + "";
                            getMvData();
                        }
                    }
                }
                selectCheckBox = (CheckBox) v;
            } else {
                scale = 1.0f;
                if (selectCheckBox != null) {
                    for (int i = 0; i < cbList.size(); i++) {
                        cbList.get(i).setChecked(false);
                    }
                    selectCheckBox.setChecked(true);
                    setItemUpFocusId(selectCheckBox.getId());
                }
                borderView.setBorderBitmapResId(R.drawable.singer_list_f, (int) getResources().getDimension(R.dimen.dp70));
            }
        }
        super.onFocusChange(v, hasFocus);
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
        }
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

        mvListByGenreIdData = null;
        playHistoryList = null;
        handler.removeMessages(MSG_TURN_PAGE);
        handler = null;

        System.gc();

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

    @OnClick({R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6,R.id.item7,R.id.item8})
    public void onClick(View view) {
        super.onClick(view);
        int itemIndex = getItemIndex(view);

            if (!TextUtils.equals(mvType, "5") && !TextUtils.equals(mvType, "6") &&
                    !TextUtils.equals(mvType, "7") && !TextUtils.equals(mvType, "8")) {
                playHistoryList = new ArrayList<>();
                for (int i = 0; i < mvListByGenreIdData.size(); i++) {
                    BeanMvListByGenreId.dataBean.programBean mvBean = mvListByGenreIdData.get(i);
                    BeanUserPlayList.DataBean playBean = new BeanUserPlayList.DataBean();
                    playBean.setSingerMids("0");
                    playBean.setBigPic(mvBean.getImageBig());
                    playBean.setSmallPic(mvBean.getImageSmall());
                    playBean.setContentMid(String.valueOf(mvBean.getSupplierId()));
                    playBean.setContentType(mvBean.getMultiSetType());//单集多集
                    playBean.setContentId(mvBean.getPkId());//pkgId
                    playBean.setMediaType(mvBean.getChannelId());//channelId
                    playBean.setContentName(mvBean.getName());
                    playBean.setSingerNames(mvBean.getSupplierName());
                    playBean.setIsFree(Integer.valueOf(mvBean.getIsFree()));
                    playHistoryList.add(playBean);
                }
            }
            Intent intent = new Intent(this, PlayVideoActivity.class);
            intent.putExtra("isSingle", getIntent().getBooleanExtra("isSingle", false));
            intent.putExtra("playIndex", itemIndex);
            intent.putExtra("fileType", 1);
            intent.putParcelableArrayListExtra("playList", playHistoryList);
            if (isSingle) {
                intent.putExtra("buySingleName", tvList[itemIndex].getText());
            }
            startActivity(intent);

    }

    private int getItemIndex(View view) {
        for (int i = 0; i < itemList.length; i++) {
            if (itemList[i] == view) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        super.onSucceeded(method, key, object);
        if (TextUtils.equals(method, "vipLabelList.utvgo")) {
            BeanTypeGenre2 typeGenre = (BeanTypeGenre2) object;
            if (typeGenre != null && TextUtils.equals(typeGenre.getCode(), "1")) {
                genreListBeen2 = typeGenre.getData();
                initTopType2();
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取数据失败，请稍后重试", null);
            }
        }
        else if (TextUtils.equals(method, "programList.utvgo")) {
            BeanMvListByGenreId mvListByGenreId = (BeanMvListByGenreId) object;
            if (mvListByGenreId != null && TextUtils.equals(mvListByGenreId.getCode(), "1")) {
                mvListByGenreIdData = mvListByGenreId.getData().getPrograms();
                for (int i = 0; i < mvListByGenreIdData.size() && i < itemList.length; i++) {
                    itemList[i].setVisibility(View.VISIBLE);
                    runText(itemList[i], false);
                    BeanMvListByGenreId.dataBean.programBean dataBean = mvListByGenreIdData.get(i);
                    tvList[i].setText(dataBean.getName());
                    if (dataBean.getIsFree() == String.valueOf(1)) {
                        ///freeList[i].setVisibility(View.VISIBLE);
                    } else {
                        //freeList[i].setVisibility(View.GONE);
                    }
                    ImageTool.loadImageWithUrl(this, DiffHostConfig.generateImageUrl(dataBean.getImageSmall()), ivList[i]);
                    if (isFirstCome) {
                        isFirstCome = false;
                        showViewByHandler(itemList[0]);
                    }
                }
                for (int n = mvListByGenreIdData.size(); n < itemList.length; n++) {
                    itemList[n].setVisibility(View.GONE);
                }
                pageTotal = mvListByGenreId.getTotalPage();
                tvPage.setText("< " + mvListByGenreId.getCurrentPage() + "/" + mvListByGenreId.getTotalPage() + " >");
//                showViewByHandler(itemList[0]);
                if (isNextPage) {
                    showOther(flContent, 1000);
                } else {
                    showOther(flContent, -1000);
                }
                if (mvListByGenreIdData.size() == 0) {
                    showViewTip("该分类下暂无数据");
                } else {
                    hideViewTip();
                }
            } else {
                HiFiDialogTools.getInstance().showtips(this, "获取数据失败，请稍后重试", null);
            }
        }
    }
}

