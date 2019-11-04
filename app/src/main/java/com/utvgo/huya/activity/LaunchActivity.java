package com.utvgo.huya.activity;

import android.os.Bundle;

public class LaunchActivity extends HomeActivity {
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
