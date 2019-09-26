package com.utvgo.huya.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.iflytek.xiri.Feedback;
import com.iflytek.xiri.scene.ISceneListener;
import com.iflytek.xiri.scene.Scene;
import com.utvgo.huya.utils.ToastUtil;

/**
 * @Author lgh
 * 专门做页面跳转工作
 */
public class PageActivity extends BaseActivity {

    public int pageTotal = 1;
    public int pageNo = 1;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_TURN_PAGE:
                    if (tmpPageIndex <= pageTotal && tmpPageIndex > 0) {
                        pageNo = (int) msg.obj;
//                        Toast.makeText(MVActivity.this,"跳转至"+pageNo+"页",Toast.LENGTH_SHORT).showInCenter();
                        ToastUtil.showInCenter(PageActivity.this, "已跳转至" + pageNo + "页");
                        getMvData();
                        getRankData();
                        getData();
                    } else {
                        ToastUtil.showInCenter(PageActivity.this, "不存在" + tmpPageIndex + "页");
                    }
                    tmpPageIndex = -1;
                    break;
            }
        }
    };
    public static final int MSG_TURN_PAGE = 0;
    private int tmpPageIndex = -1;
    private Feedback feedback;
    private Scene focusScene;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedback = new Feedback(this);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
            Log.e("LGH", "onKeyUp: " + (keyCode - 7));
            handler.removeMessages(MSG_TURN_PAGE);
            if (tmpPageIndex == -1) {
                tmpPageIndex = keyCode - 7;
            } else {
                tmpPageIndex = tmpPageIndex * 10 + keyCode - 7;
            }
            Message message = new Message();
            message.what = MSG_TURN_PAGE;
            message.obj = tmpPageIndex;
            handler.sendMessageDelayed(message, 800);
        }
        return super.onKeyUp(keyCode, event);
    }


    public void getMvData() {
        //让子类操作翻页
    }

    public void getRankData() {
        //让子类操作
    }

    @Override
    protected void onResume() {
        super.onResume();
        startXiri();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopXiri();
    }

    private void startXiri() {
        focusScene = new Scene(this);
        focusScene.init(new ISceneListener() {
            @Override
            public String onQuery() {
                return "{\"_scene\":\"com.utvgo.huya.page\",\"_commands\":{\"PAGE_UP\":[\"上一页\"],\"PAGE_DOWN\":[\"下一页\"]}}";
            }

            @Override
            public void onExecute(Intent intent) {
                feedback.begin(intent);
                if (intent.hasExtra("_scene")) {
                    String command = intent.getStringExtra("_command");
                    Toast.makeText(PageActivity.this, command, Toast.LENGTH_LONG).show();
                    Log.d("TAG", "command = " + command);
                    switch (command) {
                        case "PAGE_UP":
                            if (pageNo - 1 > 0) {
                                pageNo--;
                                getMvData();
                                getRankData();
                                getData();
                            }
                            feedback.feedback("上一页", Feedback.SILENCE);
                            break;
                        case "PAGE_DOWN":
                            if (pageNo + 1 < pageTotal) {
                                pageNo++;
                                getMvData();
                                getRankData();
                                getData();
                            }
                            feedback.feedback("下一页", Feedback.SILENCE);
                            break;
                    }

                }
            }
        });
    }

    private void stopXiri() {
        focusScene.release();
    }

    public void getData() {
    }
}
