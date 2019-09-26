package com.utvgo.huya.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.utvgo.huya.R;
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
                showBuy("");
            }
    }
}
