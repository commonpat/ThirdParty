package com.utvgo.huya.views;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.utvgo.huya.utils.DensityUtil;

@SuppressLint("AppCompatCustomView")
public class FocusView extends ImageView {

    private Activity activity;


    public FocusView(Context context) {
        super(context);
        init(context);
    }

    public FocusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FocusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.activity = (Activity) context;
        setScaleType(ScaleType.FIT_XY);
    }

    public void setFocusView(View v, int resId){
       try {
           setFocusView(v,resId,0,0,0);
       }catch (Exception e){
           Log.d("focus", "setFocusView: "+e);       }
    }
    public void setFocusView(View v, int resId, int left, int top, int padding) {
        FrameLayout.MarginLayoutParams layoutParams = (FrameLayout.MarginLayoutParams) getLayoutParams();
        setImageResource(resId);
        Rect location = findLocationWithView(v, (ViewGroup) getParent());
        layoutParams.leftMargin = location.left - (int) DensityUtil.pt2px(activity, padding) + (int) DensityUtil.pt2px(activity, left);
        layoutParams.topMargin = location.top - (int) DensityUtil.pt2px(activity, padding) + (int) DensityUtil.pt2px(activity, top);
        layoutParams.width = location.width() + (int) DensityUtil.pt2px(activity, 2 * padding);
        layoutParams.height = location.height() + (int) DensityUtil.pt2px(activity, 2 * padding);
        setLayoutParams(layoutParams);
    }
    /**
     * 获取View的位置
     *
     * @param view 获取的控件
     * @return 位置
     */
    private Rect findLocationWithView(View view, ViewGroup rootLayout) {
        ViewGroup root = rootLayout;
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        root.offsetDescendantRectToMyCoords(view, rect);
        return rect;
    }
    public void fly(View nv, View ov, int resId) {
        setImageResource(resId);
        final FrameLayout.MarginLayoutParams layoutParams = (FrameLayout.MarginLayoutParams) getLayoutParams();
        AnimatorSet set = new AnimatorSet();
        final Rect location_n = findLocationWithView(nv, (ViewGroup) getParent());
        final Rect location_o = findLocationWithView(ov, (ViewGroup) getParent());
        //x
        ValueAnimator xValue = ValueAnimator.ofInt(location_o.left, location_n.left);
        xValue.setDuration(300L);
        xValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.leftMargin= (int) animation.getAnimatedValue();
                setLayoutParams(layoutParams);
            }
        });
        //y
        ValueAnimator yValue = ValueAnimator.ofInt(location_o.top, location_n.top);
        yValue.setDuration(300L);
        yValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.topMargin= (int) animation.getAnimatedValue();
                setLayoutParams(layoutParams);
            }
        });
        //width
        ValueAnimator wValue= ValueAnimator.ofInt(location_o.width(),location_n.width());
        wValue.setDuration(300L);
        wValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.width= (int) valueAnimator.getAnimatedValue();
                setLayoutParams(layoutParams);
            }
        });
        //height
        ValueAnimator hValue= ValueAnimator.ofInt(location_o.height(),location_n.height());
        hValue.setDuration(300L);
        hValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height= (int) valueAnimator.getAnimatedValue();
                setLayoutParams(layoutParams);
            }
        });
        set.play(xValue).with(yValue).with(wValue).with(hValue);
        set.start();
    }

}
