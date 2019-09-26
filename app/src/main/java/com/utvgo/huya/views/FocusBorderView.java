package com.utvgo.huya.views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * Created by YS on 2016/3/1.
 * 类说明：高亮边框View
 */
public class FocusBorderView extends View {

    private static final String TAG = "FocusBorderView";
    private int resId;
    private Drawable borderDrawable;
    private Drawable shadowDrawable;
    private Context mContext;
    private View focusView;
    private float scale;

    public static int DURATION_TIME = 300;
    private int mNewWidth;
    private int mNewHeight;
    private AnimatorSet mCurrentAnimatorSet;
    private int mOldWidth;
    private int mOldHeight;

    private int addX = 0;
    private int addY = 0;

    /**
     * 此设置仅仅生效一次，每次动画结束会被置为false
     *
     * @param hideView
     */
    public void setHideView(boolean hideView) {
        isHideView = hideView;
    }

    private boolean isHideView = false;


    public FocusBorderView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FocusBorderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public FocusBorderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void setBorderBitmapResId(int resId) {
        this.resId = resId;
        this.addX = 0;
        this.addY = 0;
        try {
            if (resId != 0) {
                this.borderDrawable = mContext.getResources().getDrawable(resId); // 移动的边框.
                invalidate();
            } else {
                this.borderDrawable = null;
            }
        } catch (Exception e) {
            this.borderDrawable = null;
            e.printStackTrace();
        }
    }

    public void setBorderBitmapResId(int resId, int addXAndY) {
        this.resId = resId;
        this.addX = addXAndY;
        this.addY = addXAndY;
        try {
            this.borderDrawable = mContext.getResources().getDrawable(resId); // 移动的边框.
            invalidate();
        } catch (Exception e) {
            this.borderDrawable = null;
            e.printStackTrace();
        }
    }

    public void setBorderBitmapResId(int resId, int addX, int addY) {
        this.resId = resId;
        this.addX = addX;
        this.addY = addY;
        try {
            this.borderDrawable = ContextCompat.getDrawable(mContext, resId); // 移动的边框.
            invalidate();
        } catch (Exception e) {
            this.borderDrawable = null;
            e.printStackTrace();
        }
    }

    public void setShadowBitmapResId(int resId) {
        this.resId = resId;
        this.addX = 0;
        this.addY = 0;
        try {
            this.shadowDrawable = mContext.getResources().getDrawable(resId); // 阴影
            invalidate();
        } catch (Exception e) {
            this.shadowDrawable = null;
            e.printStackTrace();
        }
    }


    private void init() {
        setFocusable(false);
        setFocusableInTouchMode(false);
        setEnabled(false);
        setBorderSize();
    }


    private void setBorderSize() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(1, 1);
        setLayoutParams(layoutParams);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.save();
//        onDrawShadow(canvas);//画阴影
//        canvas.restore();

        canvas.save();
        ondrawBorder(canvas);//画边框
        canvas.restore();
    }

    private void ondrawBorder(Canvas canvas) {
        if (borderDrawable != null) {
            int width = getWidth();
            int height = getHeight();
            // 边框的绘制.
//            borderDrawable.getPadding(padding);
            borderDrawable.setBounds(mPaddingRect.left, mPaddingRect.top, width -
                    (mPaddingRect.right), height - (mPaddingRect.bottom));

            borderDrawable.draw(canvas);
        }
    }

    private Rect mPaddingRect = new Rect(0, 0, 0, 0);
    private Rect mShadowPaddingRect = new Rect(0, 0, 0, 0);

    private void onDrawShadow(Canvas canvas) {
        if (shadowDrawable != null) {
//            XLog.d(TAG, "画阴影----------");
            int width = getWidth();
            int height = getHeight();
            Rect padding = new Rect();
            shadowDrawable.getPadding(padding);
            shadowDrawable.setBounds(-padding.left + (mShadowPaddingRect.left), -padding.top + (mShadowPaddingRect.top), width + padding.right -
                    (mShadowPaddingRect.right), height + padding.bottom - (mShadowPaddingRect.bottom));
            shadowDrawable.draw(canvas);
        }
    }

    /**
     * 放大view，并且实现边框移动和放大跟view相同的倍数和位置
     *
     * @param toView
     * @param scale
     */
    public void setFocusView(View toView, float scale) {
        setFocusView(toView, scale, true);
    }

    /**
     * 放大view，并且实现边框移动和放大跟view相同的倍数和位置
     *
     * @param toView
     * @param scale
     */
    public void setFocusView(View toView, float scale, boolean needScaleBorder) {
        if (toView == null)
            return;
        this.scale = scale;
        this.focusView = toView;
        focusView.animate().scaleX(scale).scaleY(scale).setDuration(DURATION_TIME).start();
        try {
            if (needScaleBorder) {
                runTranslateAnimation(toView);
            } else {
                runTranslateWithOutScaleAnimation(toView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置无焦点子控件还原.
     */
    public void setUnFocusView(View view) {
        if (view != null) {
            view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(DURATION_TIME).start();
        }
    }

    private void runTranslateWithOutScaleAnimation(View toView) throws Exception {
        Rect fromRect = findLocationWithView(this);
        Rect toRect = findLocationWithView(toView);
        float x = toRect.left - fromRect.left;
        float y = toRect.top - fromRect.top;

        if (focusView != null) {
            mNewWidth = 100;
            mNewHeight = 100;
            x = x + (focusView.getMeasuredWidth()) - 10;
            y = y + (focusView.getMeasuredHeight() - mNewHeight) / 2;
        }
        // 取消之前的动画.
        if (mCurrentAnimatorSet != null)
            mCurrentAnimatorSet.cancel();

        mOldWidth = this.getMeasuredWidth();
        mOldHeight = this.getMeasuredHeight();

        ObjectAnimator transAnimatorX = ObjectAnimator.ofFloat(this, "translationX", x);
        ObjectAnimator transAnimatorY = ObjectAnimator.ofFloat(this, "translationY", y);
        // BUG，因为缩放会造成图片失真(拉伸).
        // hailong.qiu 2016.02.26 修复 :)
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofInt(new ScaleView(this), "width", mOldWidth, mNewWidth);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofInt(new ScaleView(this), "height", mOldHeight, mNewHeight);
        //
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(transAnimatorX, transAnimatorY, scaleXAnimator, scaleYAnimator);//
        mAnimatorSet.setInterpolator(new DecelerateInterpolator(1));
        mAnimatorSet.setDuration(DURATION_TIME);
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setVisibility(isHideView ? View.GONE : View.VISIBLE);
                isHideView = false;//结束了需要置为false
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });
        mAnimatorSet.start();
        mCurrentAnimatorSet = mAnimatorSet;
    }

    private void runTranslateAnimation(View toView) throws Exception {
        Rect fromRect = findLocationWithView(this);
        Rect toRect = findLocationWithView(toView);
        float x = toRect.left - fromRect.left;
        float y = toRect.top - fromRect.top;

        flyBorder(x, y);
    }

    private void flyBorder(float x, float y) {
        if (focusView != null) {
            mNewWidth = (int) ((float) focusView.getMeasuredWidth() * scale) + addX;
            mNewHeight = (int) ((float) focusView.getMeasuredHeight() * scale) + addY;
            x = x + (focusView.getMeasuredWidth() - mNewWidth) / 2;
            y = y + (focusView.getMeasuredHeight() - mNewHeight) / 2;
        }
        // 取消之前的动画.
        if (mCurrentAnimatorSet != null)
            mCurrentAnimatorSet.cancel();

        mOldWidth = this.getMeasuredWidth();
        mOldHeight = this.getMeasuredHeight();

        ObjectAnimator transAnimatorX = ObjectAnimator.ofFloat(this, "translationX", x);
        ObjectAnimator transAnimatorY = ObjectAnimator.ofFloat(this, "translationY", y);
        // BUG，因为缩放会造成图片失真(拉伸).
        // hailong.qiu 2016.02.26 修复 :)
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofInt(new ScaleView(this), "width", mOldWidth, mNewWidth);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofInt(new ScaleView(this), "height", mOldHeight, mNewHeight);
        //
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(transAnimatorX, transAnimatorY, scaleXAnimator, scaleYAnimator);//
        mAnimatorSet.setInterpolator(new DecelerateInterpolator(1));
        mAnimatorSet.setDuration(DURATION_TIME);
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                XLog.d("isHideView = " + isHideView);
                setVisibility(isHideView ? View.GONE : View.VISIBLE);
                isHideView = false;//结束了需要置为false
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });
        mAnimatorSet.start();
        mCurrentAnimatorSet = mAnimatorSet;
    }

    public Rect findLocationWithView(View view) {
        ViewGroup root = (ViewGroup) this.getParent();
        Rect rect = new Rect();
        root.offsetDescendantRectToMyCoords(view, rect);
        return rect;
    }


    public void setBorderViewHide() {
        setVisibility(INVISIBLE);
    }

    public void setBorderVisible() {
        setVisibility(VISIBLE);
    }

}
