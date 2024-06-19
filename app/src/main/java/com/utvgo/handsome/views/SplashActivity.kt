package com.utvgo.handsome.views

import android.content.Intent
import android.os.Bundle
import com.utvgo.huya.activity.BaseActivity
import com.utvgo.huya.activity.NewHomeActivity

/**

@author wzb
@description:
@date : 2024/6/17 15:40
 */
class SplashActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(intent.setClass(this, NewHomeActivity::class.java))
        finish()
    }
}