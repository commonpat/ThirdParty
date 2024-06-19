package com.utvgo.handsome.diff

import android.content.Context
import com.utvgo.handsome.diff.ITVBox
import com.utvgo.handsome.diff.ITVBox.FetchUrlByVODAssetIdCallBack

class GDTVBox : ITVBox {
    override fun getCA(context: Context?): String? {
        return "8002004093892878"
    }

    override fun getRegionCode(context: Context?): String? {
        return "12000"
    }

    override fun getLoginToken(context: Context?): String? {
        TODO("Not yet implemented")
    }

    override fun getDeviceModel(context: Context?): String? {
        TODO("Not yet implemented")
    }

    override fun getCookies(context: Context?): String? {
        TODO("Not yet implemented")
    }

    override fun initDeviceInfo(context: Context?) {}
    override fun getUserId(context: Context?): String? {
        TODO("Not yet implemented")
    }

    override fun exit(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun fetchUrlByVODAssetId(
        context: Context?,
        vodId: String?,
        callback: FetchUrlByVODAssetIdCallBack?
    ) {
    }
}