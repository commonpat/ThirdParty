package com.utvgo.handsome.diff

import android.content.Context


interface ITVBox {
    interface FetchUrlByVODAssetIdCallBack {
        fun onReceivedUrl(vodId: String?, url: String?)
    }

    fun getCA(context: Context?): String?
    fun getRegionCode(context: Context?): String?
    fun getLoginToken(context: Context?): String?
    fun getDeviceModel(context: Context?): String?
    fun getCookies(context: Context?): String?
    fun initDeviceInfo(context: Context?)
    fun getUserId(context: Context?): String?
    fun exit(context: Context?)
    fun fetchUrlByVODAssetId(
        context: Context?,
        vodId: String?,
        callback: FetchUrlByVODAssetIdCallBack?
    )
}