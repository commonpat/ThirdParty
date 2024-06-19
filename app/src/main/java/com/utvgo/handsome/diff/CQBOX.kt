package com.utvgo.handsome.diff

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.ipanel.join.cq.vodauth.IAuthService
import com.utvgo.huya.utils.Tools
import org.jetbrains.annotations.TestOnly
import java.lang.Exception

/**

@author wzb
@description:
@date : 2024/6/5 14:45
 */
class CQBOX : ITVBox {
    private var context: Context? = null
    override fun getCA(context: Context?): String {
        TODO("Not yet implemented")
    }

    override fun getRegionCode(context: Context?): String {
        TODO("Not yet implemented")
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

    override fun initDeviceInfo(context: Context?) {
        this.context = context
    }

    override fun getUserId(context: Context?): String? {
        TODO("Not yet implemented")
    }

    override fun exit(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun fetchUrlByVODAssetId(
        context: Context?,
        vodId: String?,
        callback: ITVBox.FetchUrlByVODAssetIdCallBack?
    ) {
        TODO("Not yet implemented")
    }

    //    @Override
    //    public void fetchUrlByVODAssetId(Context context, String flag, String vodId, FetchUrlByVODAssetIdCallBack callback) {
    //        if (platfromUtils.isP30()) {
    //            p30(context, flag, vodId, callback);
    //        } else if (platfromUtils.isP60()) {
    //            p60(context, flag, vodId, callback);
    //        } else if (platfromUtils.isIptv()) {
    //            iptv(context, flag, vodId, callback);
    //        }
    //    }
    private var mNav: IAuthService? = null
    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mNav = null
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mNav = IAuthService.Stub.asInterface(service)
            try {
                 mNav?.let{
                    // CA卡号
                    val card = mNav!!.caCardNumber
                    val authStatus = mNav!!.authStatus
                    // cookie
                    val cookieString = mNav!!.cookieString
                    // epgServerUrl
                    val epgServerUrl = mNav!!.epgServerUrl
                    DiffConfig.epg_url = epgServerUrl
                    // ip
                    val ip = mNav!!.ip
                    // mac
                    val mac = mNav!!.mac
                    // serviceGroupId
                    val serviceGroupId = mNav!!.serviceGroupId
                    Tools.setStringPreference(context, "card", card)
                    Tools.setIntegerPreference(context, "authStatus", authStatus)
                    Tools.setStringPreference(context, "cookieString", cookieString)
                    Tools.setStringPreference(context, "epgServerUrl", epgServerUrl)
                    Tools.setStringPreference(context, "ip", ip)
                    Tools.setStringPreference(context, "mac", mac)
                    Tools.setStringPreference(
                        context,
                        "serviceGroupId",
                        serviceGroupId.toString() + ""
                    )
                    Log.d(
                        "TAGonServiceConnected",
                        """
                        initDeviceInfo_p30: getCACardNumber:$card
                        AuthStatus:$authStatus
                        getCookieString:$cookieString
                        getEPGServerUrl:$epgServerUrl
                        ip:$ip
                        mac:$mac
                        getServiceGroupId:$serviceGroupId
                        """.trimIndent()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
