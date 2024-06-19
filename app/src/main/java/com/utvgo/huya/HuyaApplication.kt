package com.utvgo.huya

import android.app.Application
import com.utvgo.handsome.diff.DiffConfig
import com.utvgo.handsome.utils.TopWayBroacastUtils
import com.utvgo.huya.beans.BeanActivity

class HuyaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DiffConfig.initEnv(true, this)
        TopWayBroacastUtils.getInstance().appStart(this)
        // IntentFilter intentFilter = new IntentFilter("intent.PAY_STATE");
        //  registerReceiver(orderBroadcastReceiver,intentFilter);
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        @JvmField
        var beanActivity: BeanActivity? = null
        @JvmStatic
        fun hadBuy(): Boolean {
            return DiffConfig.CurrentPurchase.isPurchased
        }
    }
}