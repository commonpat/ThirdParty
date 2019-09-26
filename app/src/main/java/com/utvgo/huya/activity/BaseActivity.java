package com.utvgo.huya.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.beans.SelectData;
import com.utvgo.huya.net.AsyncHttpRequest;
import com.utvgo.huya.net.IVolleyRequestSuccess;
import com.utvgo.huya.net.IVolleyRequestfail;
import com.utvgo.huya.net.UTVGOServer;
import com.utvgo.huya.utils.DensityUtil;
import com.utvgo.huya.utils.DiffHostConfig;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.StrTool;
import com.utvgo.huya.utils.XLog;
import com.utvgo.huya.views.FocusBorderView;

import java.util.ArrayList;

import static com.utvgo.huya.Constants.BASE_URL;
import static com.utvgo.huya.Constants.DESIGN_WIDTH;

public  class BaseActivity extends RooterActivity implements View.OnFocusChangeListener
        , View.OnClickListener
        , AdapterView.OnItemSelectedListener
        , AdapterView.OnItemClickListener
        , ViewTreeObserver.OnTouchModeChangeListener,   // 用于监听 Touch 和非 Touch 模式的转换
        ViewTreeObserver.OnGlobalLayoutListener,     // 用于监听布局之类的变化，比如某个空间消失了
        ViewTreeObserver.OnPreDrawListener,        // 用于在屏幕上画 View 之前，要做什么额外的工作
        ViewTreeObserver.OnGlobalFocusChangeListener, // 用于监听焦点的变化
        IVolleyRequestSuccess,
        IVolleyRequestfail{
    public String TAG ="huya";
    public float scale = 1.0f;
    public View focusView = null;
    Handler handler = new Handler();
    public UTVGOServer server;
    public AsyncHttpRequest asyncHttpRequest = new AsyncHttpRequest();
    public HiFiDialogTools hiFiDialogTools = new HiFiDialogTools();
    private FrameLayout topLayout;
    private View viewTip;
    public FocusBorderView borderView;
    public boolean hadCallBuyView = false;
    public boolean needBringFront = true;
    private boolean needTransition = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        server = new UTVGOServer(BASE_URL);
        DensityUtil.init(this,DESIGN_WIDTH);
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onDestroy() {
        try {
            unregisterReceiver(mHomeKeyEventReceiver);
        }catch (Exception e){
            //TODO
        }
        // LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
        borderView = null;
        asyncHttpRequest = null;
        hiFiDialogTools = null;
        focusView = null;
        topLayout = null;
        viewTip = null;
        super.onDestroy();
//        setContentView(R.shape_bg_player_controller.view_null);
    }

    //对 专题 聚焦框进行另一种处理 （与showViewByHandler并行项目聚焦框）
    public void focusOn1stView(final View view, final int borderId, final int borderViewAndX, final int borderViewAndY) {
        new Handler().post(new Runnable() {
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
        XLog.d("BaseActivity == onActivityCreated ----> Create  FocusBorderView!");

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
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_order: {
//                Intent intent = new Intent(this, SearchListActivity.class);
//                intent.putExtra("SearchType", 2);
//                startActivity(intent);
//            }
//            break;
//            case R.id.btn_search_mv: {
//                Intent intent = new Intent(this, SearchListActivity.class);
//                intent.putExtra("SearchType", 1);
//                startActivity(intent);
//            }
//            break;
//            case R.id.btn_search_song: {
//                Intent intent = new Intent(this, SearchListActivity.class);
//                intent.putExtra("SearchType", 0);
//                startActivity(intent);
//            }
//            break;
 //       }
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


    @Override
    public void onFailed(String method, String key, int errorTipId) throws Exception {

    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {

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

    protected void stat(final String name, final String title) {
        asyncHttpRequest.statisticsVisit(this, "app-" + name, title, "");
    }

    public void stat(final String name) {
        stat(name, "");
    }


    protected void actionOnOp(final SelectData.SData selectBean, final DiffHostConfig.SubPortalType subPortalType) {
        Uri uri = Uri.parse(selectBean.getHref());
        if (selectBean.getHref().contains("column.html")) {
            String labelId = uri.getQueryParameter("labelId");
            String category = uri.getQueryParameter("category");
            String mvType = subPortalType.toId();
            String groupId = uri.getQueryParameter("groupId");
         //   MVListActivity.show(this, false, groupId, category, mvType, labelId);
            //ActivityUtility.goMVActivity(this, "10", Uri.parse(selectBean.getHref()).getQueryParameter("category"), Uri.parse(selectBean.getHref()).getQueryParameter("groupId"));
        } else if (TextUtils.equals(selectBean.getHrefType(), "0")) { //超链接
          //  ActivityUtility.goWebActivityActivity(this, selectBean.getHref());
        } else if (TextUtils.equals(selectBean.getHrefType(), "2")) { //mv专辑
            Intent intent = new Intent(this, MVAlbumActivity.class);
            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("themId"));
            startActivity(intent);
        } else if (TextUtils.equals(selectBean.getHrefType(), "1")) { //音频专辑
//          1  Intent intent = new Intent(this, AlbumDetailActivity.class);
//            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("zjid"));
//            startActivity(intent);
        }
        //else if (TextUtils.equals(selectBean.getHrefType(), "8")) { //活动
            //Intent intent = new Intent(this, ActivityActivity.class);
            //startActivity(intent);
            //todo
//        } else if (selectBean.getHref().contains("collect.html")) { //收藏
//            Intent intent = new Intent(this, CollectCenterActivity.class);
//            intent.putExtra("type", 1);
//            startActivity(intent);
//        } else if (selectBean.getHref().contains("htyplay.html")) { //历史
//            Intent intent = new Intent(this, CollectCenterActivity.class);
//            intent.putExtra("type", 0);
//            startActivity(intent);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "7")) { //专题
//            Intent intent = new Intent(this, TopicActivity.class);
//            intent.putExtra("topicId", Uri.parse(selectBean.getHref()).getQueryParameter("themId"));
//            startActivity(intent);
//        } else if (selectBean.getHref().contains("recordList_pd.html")) { //榜单
//            ActivityUtility.goSongRankActivity(this, Uri.parse(selectBean.getHref()).getQueryParameter("mid"),
//                    Uri.parse(selectBean.getHref()).getQueryParameter("id"), selectBean.getName());
//        } else if (TextUtils.equals(selectBean.getHrefType(), "12")) {//专题收录
//            ActivityUtility.goActivity(this, TopicCollectionActivity.class);
//        }
           else {
            //视频播放
            ArrayList<BeanUserPlayList.DataBean> playHistoryList = new ArrayList<>();
            if (!TextUtils.isEmpty(selectBean.getHref()) && !TextUtils.isEmpty(StrTool.getValueByName(selectBean.getHref(), "mvMid"))) {
                BeanUserPlayList.DataBean playBean = new BeanUserPlayList.DataBean();
                playBean.setBigPic(selectBean.getImgUrl());
                playBean.setSmallPic(selectBean.getImgUrl());
                playBean.setContentMid(StrTool.getValueByName(selectBean.getHref(), "mvMid"));
                playBean.setContentName(selectBean.getName());
                playHistoryList.add(playBean);
            }
            Intent intent = new Intent(this, PlayVideoActivity.class);
            intent.putExtra("playIndex", 0);
            intent.putExtra("fileType", 1);
            intent.putParcelableArrayListExtra("playList", playHistoryList);
            startActivity(intent);
        }
    }
}
