package com.utvgo.huya.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.XLog;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.beans.ProgramContent;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.constant.ConstantEnum;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.DensityUtil;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.StringUtils;
import com.utvgo.huya.views.FocusBorderView;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends RooterActivity implements View.OnFocusChangeListener,
        AdapterView.OnItemSelectedListener,
        AdapterView.OnItemClickListener,
        View.OnClickListener,
        ViewTreeObserver.OnTouchModeChangeListener,   // 用于监听 Touch 和非 Touch 模式的转换
        ViewTreeObserver.OnGlobalLayoutListener,     // 用于监听布局之类的变化，比如某个空间消失了
        ViewTreeObserver.OnPreDrawListener,        // 用于在屏幕上画 View 之前，要做什么额外的工作
        ViewTreeObserver.OnGlobalFocusChangeListener // 用于监听焦点的变化
{
    public String TAG = "huya";
    public float scale = 1.0f;
    public View focusView = null;
    Handler handler = new Handler();

    public HiFiDialogTools hiFiDialogTools = new HiFiDialogTools();
    private FrameLayout topLayout;
    private View viewTip;
    public FocusBorderView borderView;
    public boolean hadCallBuyView = false;
    public boolean needBringFront = true;
    private boolean needTransition = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DensityUtil.init(this, 1280);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        try {
            unregisterReceiver(mHomeKeyEventReceiver);
        } catch (Exception e) {
            //TODO
        }
        // LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
        borderView = null;
        hiFiDialogTools = null;
        focusView = null;
        topLayout = null;
        viewTip = null;
        super.onDestroy();
    }

    //对 专题 聚焦框进行另一种处理 （与showViewByHandler并行项目聚焦框）
    public void focusOn1stView(final View view, final int borderId, final int borderViewAndX, final int borderViewAndY) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (view == null) {
                    return;
                }
                if (borderView != null) {
                    borderView.setBorderBitmapResId(borderId, borderViewAndX, borderViewAndY);
                }
                view.clearFocus();
                view.requestFocus();
            }
        });
    }

    //延时设置view获得焦点
    public void showViewByHandler(final View view) {
        view.clearFocus();//不清除让系统认为同个view再次聚焦，不会调用onFocusChange的
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (borderView != null) {
                    borderView.setVisibility(View.VISIBLE);
                }
                view.requestFocus();
            }
        }, 500);
    }

    /**
     * @param activity
     */
    @Override
    public void createBorderView(Activity activity) {
        borderView = new FocusBorderView(this);
        borderView.setBorderBitmapResId(R.mipmap.border_focus_style_default);
        topLayout = (FrameLayout) getRootView(activity);
        borderView.setBorderViewHide();

        if (topLayout != null) {
            topLayout.addView(borderView);

        }
    }

    public View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0).findViewById(R.id.activity_RootView);
    }

    /**
     * 遍历所有view,实现focus监听
     *
     * @param activity
     */
    public void traversalView(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) getRootView(activity);
        traversal(viewGroup);

    }

    public void traversal(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            doView(view);
            if (view instanceof ViewGroup)
                traversal((ViewGroup) view);
        }
    }

    /**
     * 处理view
     *
     * @param view
     */
    private void doView(View view) {
        if (view == null)
            return;
        if (view.isFocusable()) {
            view.setOnFocusChangeListener(this);
        }


//        Object tagO = view.getTag();
//        if (tagO == null)
//            return;
//        String tag = tagO.toString();
//        if (TextUtils.isEmpty(tag))
//            return;
//        if (TextUtils.equals(tag, this.getResources().getString(R.string.tagForFocus))) {
//            view.setFocusable(true);
//            view.setOnFocusChangeListener(this);
//        }else {
//            view.setFocusable(false);
//        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (borderView == null) {
            return;
        }
        if (hasFocus) {
            if (needBringFront) {
                v.bringToFront();
                v.getParent().bringChildToFront(v);
            }
            v.requestLayout();
            v.invalidate();
            borderView.setHideView(false);//这一句非常重要
            borderView.setFocusView(v, scale);
            focusView = v;
        } else {
            borderView.setUnFocusView(v);
        }
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {

    }

    @Override
    public void onGlobalLayout() {

    }

    @Override
    public boolean onPreDraw() {
        return false;
    }

    @Override
    public void onTouchModeChanged(boolean isInTouchMode) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void startActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//设置切换没有动画，用来实现活动之间的无缝切换
        super.startActivity(intent);
    }

    /**
     * 重点，在这里设置按下返回键，或者返回button的时候无动画
     */
    @Override
    public void finish() {
        if (!needTransition) {
            overridePendingTransition(0, 0);//设置返回没有动画
        }
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }

    public void changeFragment(Fragment fragment) {

    }

    public void showOther(ViewGroup viewGroup, int value) {
//        SpringChain springChain = SpringChain.create(40, 6, 50, 7);
//        int childCount = viewGroup.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            final View view = viewGroup.getChildAt(i);
//            springChain.addSpring(new SimpleSpringListener() {
//                @Override
//                public void onSpringUpdate(Spring spring) {
//                    view.setTranslationX((float) spring.getCurrentValue());
//                }
//            });
//        }
//        List<Spring> springs = springChain.getAllSprings();
//        for (int i = 0; i < springs.size(); i++) {
//            springs.get(i).setCurrentValue(value);
//        }
//        springChain.setControlSpringIndex(2).getControlSpring().setEndValue(0);
    }

    public void showViewTip(String tipStr) {
        if (viewTip == null) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            viewTip = View.inflate(this, R.layout.layout_empty, null);
            topLayout.addView(viewTip, params);
        }
        TextView tipTV = (TextView) viewTip.findViewById(R.id.tv_tip);
        viewTip.setVisibility(View.VISIBLE);
        tipTV.setText(tipStr);
    }

    public void hideViewTip() {
        if (viewTip != null) {
            viewTip.setVisibility(View.GONE);
        }
    }

    /**
     * 监听是否点击了home键将客户端推到后台
     */
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
        String SYSTEM_REASON = "reason";
        String SYSTEM_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    HuyaApplication.beanActivity = null;
                    //表示按了home键,程序到了后台
                    finish();
//                    Process.killProcess(Process.myPid());
                }
            }
        }
    };


    //
    public void netBack(int requestTag, Object object) {

    }

    public void buyDialogDimiss() {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d("BaseActivity", "====BaseActivity has been recycled!");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            keyCode = KeyEvent.KEYCODE_DPAD_CENTER;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {

    }

    protected void stat(final String name, final String title) {
        NetworkService.defaultService().statisticsVisit(this, "app-" + name, title, "");
    }

    public void stat(final String name) {
        stat(name, "");
    }

    public void loadImage(final ImageView imageView, final String imageUrl) {
        Glide.with(this).load(DiffConfig.generateImageUrl(imageUrl)).into(imageView);
    }

    public boolean actionOnOp(final OpItem opItem)
    {
        boolean ret = false;
        if(opItem != null)
        {
            ret = true;
            final Context context = this;
            ConstantEnum.OpType opType = ConstantEnum.OpType.fromTypeString(opItem.getHrefType());
            String href = opItem.getHref();
            Uri uri = Uri.parse(href);
            switch (opType)
            {
                case web:
                {
                    QWebViewActivity.navigateUrl(this, href);
                    break;
                }
                case program:
                {
                    String programId = uri.getQueryParameter("pkId");
                    String multisetType = uri.getQueryParameter("multisetType");
                    String channelId = uri.getQueryParameter("channelId");
                    NetworkService.defaultService().fetchProgramContent(this, StringUtils.intValueOfString(programId),
                            multisetType, StringUtils.intValueOfString(channelId), new JsonCallback<BaseResponse<ProgramContent>>() {
                                @Override
                                public void onSuccess(Response<BaseResponse<ProgramContent>> response) {
                                    BaseResponse<ProgramContent> data = response.body();
                                    if(data.isOk())
                                    {
                                        ArrayList<ProgramInfoBase> list = new ArrayList<>();
                                        list.add(data.getData());
                                        PlayVideoActivity.play(context, list, 0, false);
                                    }
                                }
                            });
                    break;
                }

                case album:{
                    String albumId = uri.getQueryParameter("pkId");
                    MediaAlbumActivity.show(this, StringUtils.intValueOfString(albumId));
                    break;
                }

                case topic:{
                    String topicId = uri.getQueryParameter("themId");
                    String styleId = uri.getQueryParameter("styleId");
                    TopicActivity.show(this, topicId, styleId);
                    break;
                }

                case topicPage:
                case topicCollection:
                case albumList:
                case more:
                case back:
                case activity:
                        default:
                {
                    ret = false;
                    break;
                }
            }
        }
        return ret;
    }
}
