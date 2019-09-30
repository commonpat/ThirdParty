package com.utvgo.huya.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.diff.DiffConfig;
import com.utvgo.huya.diff.IPurchase;
import com.utvgo.huya.interfaces.CommonCallback;
import com.utvgo.huya.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroduceActivity extends BuyActivity {
    @BindView(R.id.btn_purchase)
    Button  btnOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_introduce);
        ButterKnife.bind(this);
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
                        if (HuyaApplication.hadBuy()) {
                            ToastUtil.show(getBaseContext(), "您已经是 " + getResources().getString(R.string.app_name) + " 尊贵会员");
                        } else {
                            if (!TextUtils.isEmpty(message)) {
                                ToastUtil.show(getBaseContext(), message);
                            }
                            DiffConfig.CurrentPurchase.pay(getBaseContext(), new CommonCallback() {
                                @Override
                                public void onFinished(Context context) {

                                }
                            });
                        }
                    }
                });
            }
    }
}
