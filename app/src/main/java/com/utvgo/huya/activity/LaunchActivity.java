package com.utvgo.huya.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.utils.ApkUtil;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ApkUtil.isDebug(this)) {
            ((HuyaApplication) getApplication()).keyNo="3072535006";
        }
        Intent intent = new Intent(this,MainActivity. class);
        startActivity(intent);
        finish();
    }
}
