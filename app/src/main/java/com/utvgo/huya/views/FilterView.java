package com.utvgo.huya.views;

import android.app.Activity;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.utvgo.huya.R;

import java.util.ArrayList;

/**
 * Created by herson on 2017/6/12.
 */

public class FilterView extends FrameLayout implements View.OnClickListener {

    private Activity context;
    private FrameLayout bottomView;

    private int leftMargin = (int) getResources().getDimension(R.dimen.dp50); //左边距
    private int topFirstMargin = (int) getResources().getDimension(R.dimen.dp35); //初始化的上边距
    public static final int itemHeight = 120; //每一项的高度
    private int filterItemHeight = topFirstMargin; //每一个分类的高度
    private int itemContentLeftMargin = 0; //
    private int contentWidth = 0;  //内容宽度  为屏幕宽度减去两个左边距初始值
    private ArrayList<ArrayList<TextView>> tvArrList = new ArrayList<>();

    public FilterView(Activity context) {
        super(context);
        this.context = context;

        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        contentWidth = metric.widthPixels - leftMargin * 2;     // 屏幕宽度（像素）

        bottomView = new FrameLayout(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.dp300));
        params.gravity = Gravity.BOTTOM;
        bottomView.setBackgroundColor(getResources().getColor(R.color.filter_bg));
        addView(bottomView, params);
        setBackgroundColor(getResources().getColor(R.color.thirdty_black));

        LayoutParams tipParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tipParams.gravity = Gravity.RIGHT;
        tipParams.topMargin = 10;
        tipParams.rightMargin = 20;
        TextView tipTV = new TextView(context);
        tipTV.setFocusable(false);
        tipTV.setTextSize(getResources().getDimension(R.dimen.sp12));
        tipTV.setText("按返回键可关闭面板");
        tipTV.setTextColor(getResources().getColor(R.color.sslife_title_color));
        bottomView.addView(tipTV, tipParams);
    }

    public void addFilterItemView(ArrayList<String[]> strList) {
        if (strList == null || strList.size() == 0) {
            return;
        }
        for (int i = 0; i < strList.size(); i++) {
            ArrayList<TextView> tvList = new ArrayList<>();
            String[] str = strList.get(i);
            for (int j = 0; j < str.length; j++) {
                if (j == 0) {
                    bottomView.addView(getLeftTitleTV(str[0]));
                } else {
                    TextView contentTV = getContentTitleTV(str[j], i, j);
                    tvList.add(contentTV);
                    bottomView.addView(contentTV);
                }
            }
            tvArrList.add(tvList);
            filterItemHeight = itemHeight + filterItemHeight;
        }
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, topFirstMargin + filterItemHeight);
        params.gravity = Gravity.BOTTOM;
        bottomView.setLayoutParams(params);
    }

    private TextView getLeftTitleTV(String title) {
        TextView textView = new TextView(context);
        textView.setFocusable(false);
        textView.setTextSize(getResources().getDimension(R.dimen.sp12));
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.filter_title));
        TextPaint textPaint = textView.getPaint();
        int textPaintWidth = (int) textPaint.measureText(title);
        LayoutParams params = new LayoutParams(textPaintWidth, itemHeight);
        params.leftMargin = (int) getResources().getDimension(R.dimen.dp50);
        params.topMargin = filterItemHeight;
        leftMargin = params.leftMargin + textPaintWidth + 20;
        itemContentLeftMargin = params.leftMargin + textPaintWidth + 20;
        textView.setLayoutParams(params);
        return textView;
    }

    private TextView getContentTitleTV(String title, int row, int column) {
        TextView textView = new TextView(context);
        textView.setFocusable(true);
        textView.setTextSize(getResources().getDimension(R.dimen.sp12));
        textView.setText(title);
        TextPaint textPaint = textView.getPaint();
        int textPaintWidth = (int) textPaint.measureText(title) + 70;
        textView.setTextColor(getResources().getColor(R.color.white));
        LayoutParams params = new LayoutParams(textPaintWidth, itemHeight);
        params.leftMargin = leftMargin;
        params.topMargin = filterItemHeight;
        leftMargin = params.leftMargin + textPaintWidth;
        if (leftMargin > contentWidth) {
            leftMargin = itemContentLeftMargin;
            filterItemHeight = itemHeight + filterItemHeight;
        }

        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(params);
        textView.setOnClickListener(this);
        textView.setTag(getResources().getString(R.string.tagForFocus));
        textView.setTag(R.id.filterItemTag, row + ":" + column);
        textView.setId(row * 100 + column);
        textView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    view.setBackgroundResource(R.drawable.focus);
                } else {
                    view.setBackgroundResource(0);
                }
            }
        });
        return textView;
    }

    @Override
    public void onClick(View view) {
        String[] tagStr = view.getTag(R.id.filterItemTag).toString().split(":");
        int row = Integer.parseInt(tagStr[0]);
        int column = Integer.parseInt(tagStr[1]);
        for (int i = 0; i < tvArrList.get(row).size(); i++) {
            TextView tv = tvArrList.get(row).get(i);
            tv.setTextColor(getResources().getColor(R.color.white));
        }
        ((TextView) view).setTextColor(getResources().getColor(R.color.green));
        Log.d("row  column", row + "  ----  " + column);
    }

}
