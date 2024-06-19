package com.utvgo.huya.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class LaunchActivity extends BuyActivity {
    private boolean isBindBoxService = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        intent.setClass(this,NewHomeActivity.class);
        startActivity(intent);
        finish();


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
