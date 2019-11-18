package com.utvgo.huya.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.GZTVPurchase;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityActivity extends BaseActivity {
    @BindView(R.id.btn_purchase)
    Button  btnOrder;
    @BindView(R.id.activity_show)
    ImageView activityShow;
    private String bgImageUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_actvity);
        ButterKnife.bind(this);
        bgImageUrl=getIntent().getStringExtra("bgImageUrl");
        Glide.with(ActivityActivity.this).load(bgImageUrl).into(activityShow);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showViewByHandler(btnOrder);
            }
        });
    }

    public void onClick(View view){
            if (view == btnOrder){
                DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
                    @Override
                    public void onFinished(String message) {

                        if (!TextUtils.isEmpty(message)) {
                            ToastUtil.show(getBaseContext(), message);
                        }
                          /*  DiffConfig.CurrentPurchase.pay(ActivityActivity.this, new CommonCallback() {
                                @Override
                                public void onFinished(Context context) {

                                }
                            });*/
                        if (!HuyaApplication.hadBuy()) {
                            if (DiffConfig.CurrentPurchase instanceof GZTVPurchase) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        GZTVPurchase purchase = (GZTVPurchase) DiffConfig.CurrentPurchase;
                                        purchase.foo(ActivityActivity.this, new GZTVPurchase.TryBestCallback() {
                                            @Override
                                            public void d(String s) {
                                                final String msg = s;
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void success(String s) {
                                                ToastUtil.show(ActivityActivity.this, "你已经是虎牙TV的尊贵会员！");

                                                ActivityActivity.this.finish();


                                            }

                                            @Override
                                            public void fail(String s) {
                                                ToastUtil.show(ActivityActivity.this, "订购失败！");
                                            }
                                        });
                                    }
                                }).start();
                            }
                        }
                    }
                });
            }
    }
}
