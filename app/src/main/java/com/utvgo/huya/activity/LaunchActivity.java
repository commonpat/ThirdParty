package com.utvgo.huya.activity;

import android.os.Bundle;

import com.utvgo.huya.R;

public class LaunchActivity extends NewHomeActivity {
    private boolean isBindBoxService = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       checkIntentData();



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
