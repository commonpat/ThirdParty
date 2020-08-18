package com.utvgo.huya.utils;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewUtil {
    public static void runText(View rootLayout, boolean isRunning) {
        if (rootLayout instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) rootLayout).getChildCount(); i++) {
                runText(((ViewGroup) rootLayout).getChildAt(i), isRunning);
            }
        } else {
            //View
            if (rootLayout instanceof TextView) {
                TextView textView = (TextView) rootLayout;
//                textView.setSingleLine();
//                textView.setMarqueeRepeatLimit(1);
                if (isRunning) {
                    textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    textView.setFocusable(true);
                    textView.setFocusableInTouchMode(true);
                    textView.setSelected(true);
                } else {
                    textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    textView.setSelected(false);
                    textView.setFocusable(false);
                    textView.setFocusableInTouchMode(false);
                }
            }
        }
    }

    public static void runTextForever(View rootLayout) {
        if (rootLayout instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) rootLayout).getChildCount(); i++) {
                runTextForever(((ViewGroup) rootLayout).getChildAt(i));
            }
        } else {
            //View
            if (rootLayout instanceof TextView) {
                TextView textView = (TextView) rootLayout;
                textView.setSingleLine();
                textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                textView.setMarqueeRepeatLimit(-1);
//                    textView.setFocusable(true);
//                    textView.setFocusableInTouchMode(true);
                textView.setSelected(true);
            }
        }
    }

    /**
     * 获取View的位置
     *
     * @param view 获取的控件
     * @return 位置
     */
    public static Rect findLocationWithView(View view, ViewGroup rootLayout) {
        ViewGroup root = rootLayout;
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        root.offsetDescendantRectToMyCoords(view, rect);
        return rect;
    }


}
