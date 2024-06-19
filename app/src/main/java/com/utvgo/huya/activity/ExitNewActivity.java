package com.utvgo.huya.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.views.CustomVideoView;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanExitPage;
import com.utvgo.huya.net.NetworkService;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wzb
 * @description:
 * @date : 2020/1/10 9:54
 */
public class ExitNewActivity extends PlayGuangDongActivity{
    private  BeanExitPage beanExitPage;
    @BindView(R.id.exit_video)
    CustomVideoView exitVideo;
    @BindView(R.id.exit_new_back)
    Button exitNewBack;
    private int vodPlayIndex=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit_new);
        ButterKnife.bind(this);
        setHahaPlayer(exitVideo);
        exitNewBack.requestFocus();
        try {
            loadExitDate();
            stat("退出页","");
        }catch (Exception e){
            hiFiDialogTools.showtips(this,"获取数据有误,请重试",null);
        }
    }

    private void loadExitDate() {
        NetworkService.defaultService().fetchExitNewPage(this, null, new JsonCallback<BeanExitPage>() {
            @Override
            public void onSuccess(Response<BeanExitPage> response) {
                 beanExitPage = response.body();
                 playData();

            }
        });
    }
    String[] vodUrl=null;
    private void playData(){
        vodUrl=beanExitPage.getData().get(0).getVideoUrl().split(",");
        if (vodUrl.length<=1){
            vodUrl=beanExitPage.getData().get(0).getVideoUrl().split("\\|");
        }
        playVideo();
    }
    private void playVideo(){
        vodPlayIndex = new Random().nextInt(vodUrl.length);
        hahaPlayUrl(vodUrl[vodPlayIndex]);
    }

    @Override
    public void hahaPlayEnd(float v) {

        super.hahaPlayEnd(v);
        playVideo();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: "+view.getWidth());
        if(view.getId()== R.id.exit_new_continue){
            onBackPressed();
        }else if (view.getId() == R.id.exit_new_back){
            setResult(RESULT_OK);
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        super.onClick(view);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_DPAD_UP){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
