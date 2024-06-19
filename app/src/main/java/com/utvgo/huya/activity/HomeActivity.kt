package com.utvgo.huya.activity

import com.utvgo.huya.HuyaApplication.Companion.hadBuy
import butterknife.BindView
import com.utvgo.huya.R
import com.utvgo.handsome.views.CustomVideoView
import android.view.SurfaceView
import android.os.Bundle
import butterknife.ButterKnife
import com.utvgo.handsome.diff.DiffConfig
import android.widget.Toast
import com.utvgo.huya.net.NetworkService
import com.utvgo.handsome.interfaces.JsonCallback
import com.utvgo.huya.utils.HiFiDialogTools
import com.utvgo.huya.constant.ConstantEnumHuya
import com.utvgo.huya.utils.ToastUtil
import android.text.TextUtils
import android.content.Intent
import com.alibaba.fastjson.JSON
import android.content.Context
import android.net.Uri
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.lzy.okgo.model.Response
import com.utvgo.handsome.config.AppConfig
import com.utvgo.huya.utils.NetUtils
import com.utvgo.handsome.utils.XLog
import com.utvgo.huya.BuildConfig
import com.utvgo.huya.beans.*
import com.utvgo.huya.utils.StringUtils
import java.lang.Exception
import java.util.*

class HomeActivity : BuyActivity() {
    @JvmField
    @BindView(R.id.btn_tab_0)
    var mainTabButton1: Button? = null

    @JvmField
    @BindView(R.id.btn_fl_1)
    var btn1: Button? = null

    @JvmField
    @BindView(R.id.vv_small)
    var videoView: CustomVideoView? = null

    @JvmField
    @BindView(R.id.sv_video)
    var svVideo: SurfaceView? = null

    @JvmField
    @BindView(R.id.iv_home_logo)
    var ivHomeLogo: ImageView? = null

    @JvmField
    @BindView(R.id.home_bg)
    var homeBg: ImageView? = null
    val flContentIdArray = intArrayOf(
        R.id.bits_1, R.id.bits_2, R.id.bits_3, R.id.bits_4, R.id.bits_5,
        R.id.bits_6, R.id.bits_7, R.id.bits_8, R.id.bits_9, R.id.bits_10
    )
    val flContentButtonIdArray = intArrayOf(
        R.id.btn_fl_1, R.id.btn_fl_2, R.id.btn_fl_3, R.id.btn_fl_4, R.id.btn_fl_5,
        R.id.btn_fl_6, R.id.btn_fl_7, R.id.btn_fl_8, R.id.btn_fl_9, R.id.btn_fl_10
    )
    var flContentImageViewArray = arrayOfNulls<ImageView>(flContentIdArray.size)

    //data
    var pageOpData: MutableList<OpItem> = ArrayList()
    var videoData: OpItem? = null
    var selectBean: OpItem? = null
    var beanExitPage: BeanExitPage? = null
    var endPushContentBean: List<BeanExitPage.Data>? = null
    var needEnterRecommend = true
    var currentPlayingIndex = 0
    var assetUrlArray: Array<String>? = null
    val itemOnClickListener = View.OnClickListener { view ->
        var index = -1
        for (i in flContentImageViewArray.indices) {
            if (view.id == flContentImageViewArray[i]!!.id) {
                index = i
                break
            }
        }
        if (index >= 0 && index < pageOpData.size) {
            val bean = pageOpData[index]
            actionOnOp(bean)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        initView()
        DiffConfig.CurrentPurchase.auth(this) {
            if (BuildConfig.DEBUG) {
                Toast.makeText(
                    this@HomeActivity,
                    "HomePage auth finished: " + if (DiffConfig.CurrentPurchase.isPurchased) "is Purchased" else "not Purchased",
                    Toast.LENGTH_LONG
                ).show()
            }
            initData
        }
        runOnUiThread {
            loadTypesData()
            loadData()
            loadUpgrade()
        }
    }

    private fun loadUpgrade() {
        NetworkService.defaultService()
            .getVersionInfo(this, "", object : JsonCallback<BeanUpgrade?>() {
                override fun onSuccess(response: Response<BeanUpgrade?>?) {
                    val beanUpgrade = BeanUpgrade()
                    if (beanUpgrade != null && "1" == beanUpgrade.code) {
                        if (beanUpgrade.data != null) {
                            val currentVersionCode = beanUpgrade.data.currentVersionCode
                            val currentVersionName = beanUpgrade.data.currentVersionName
                            val minVersionCode = beanUpgrade.data.minVersionCode
                            val minVersionName = beanUpgrade.data.minVersionName
                            var versionCode: Int
                            var versionName: String
                            //高于最低版本
                            if (BuildConfig.VERSION_CODE >= minVersionCode) {
                                if (BuildConfig.VERSION_CODE < currentVersionCode) {
                                    HiFiDialogTools.getInstance().showUpgradeTips(
                                        this@HomeActivity,
                                        beanUpgrade
                                    ) { dialog, `object` ->
                                        if (`object` as Int == 0) {
                                            jumpAppStore()
                                        } else if (`object` == 1) {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            } else {
                                HiFiDialogTools.getInstance().showMinUpgradeTips(
                                    this@HomeActivity,
                                    beanUpgrade
                                ) { dialog, `object` ->
                                    if (`object` as Int == 0) {
                                        jumpAppStore()
                                    }
                                }
                            }
                        }
                    }
                }
            })
    }

    private fun initView() {
        var i = 0
        for (id in flContentIdArray) {
            flContentImageViewArray[i] = findViewById(flContentIdArray[i])
            flContentImageViewArray[i]?.setOnClickListener(itemOnClickListener)
            i++
        }
        videoView = findViewById<View>(R.id.vv_small) as CustomVideoView
        //mainTabButton1!!.requestFocus()
    }

    private val currentButton: View? = null
    override fun onClick(v: View) {
        val context: Context = this
        val viewId = v.id
        when (viewId) {
            R.id.btn_main_order -> {
                stat("首页进入订购页", ConstantEnumHuya.ORDER)
                if (DiffConfig.CurrentPurchase.isPurchased) {
                    ToastUtil.show(
                        context,
                        "您已经是 " + resources.getString(R.string.app_name) + " 尊贵会员"
                    )
                }
                DiffConfig.CurrentPurchase.auth(this) { message ->
                    if (DiffConfig.CurrentPurchase.isPurchased) {
                        ToastUtil.show(
                            context,
                            "您已经是 " + resources.getString(R.string.app_name) + " 尊贵会员"
                        )
                    } else {
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtil.show(context, message)
                        }
                        DiffConfig.CurrentPurchase.pay(context) { }
                    }
                }
            }
            R.id.btn_main_user_favor -> {
                startActivity(Intent(this, UserFavoriteActivity::class.java))
            }
            R.id.btn_main_introduction -> {
                if (BuildConfig.DEBUG) {
                    QWebViewActivity.navigateUrl(
                        this,
                        "http://192.168.5.16:8080/app/huya/activity20200813.html"
                    )
                }
                if (DiffConfig.UseWebIntroduction) {
                    QWebViewActivity.navigateUrl(this, DiffConfig.IntroduceUrl)
                } else {
                    startActivity(Intent(this, IntroduceActivity::class.java))
                }
            }
            R.id.btn_main_user_center -> {
                startActivity(Intent(this, UserCenterActivity::class.java))
            }
            R.id.btn_fl_video -> {
                gotoMediaPlayer()
            }
            R.id.btn_tab_0 -> {

                // CategoryListActivity.show(context, 156);
                val channelId =
                    Uri.parse(typesBean.data.navigationBar[0].href).getQueryParameter("channelId")
                //            MediaListActivity.show(this, StringUtils.intValueOfString(channelId),
//                    opItem.getName(), StringUtils.intValueOfString(AppConfig.PackageId), 0);
                ProgramListActivity.show(
                    this, StringUtils.intValueOfString(channelId),
                    typesBean.data.navigationBar[0].columnName, 29, 0
                )
            }
            R.id.btn_tab_1 -> {
                val channelId =
                    Uri.parse(typesBean.data.navigationBar[1].href).getQueryParameter("channelId")
                //            MediaListActivity.show(this, StringUtils.intValueOfString(channelId),
//                    opItem.getName(), StringUtils.intValueOfString(AppConfig.PackageId), 0);
                ProgramListActivity.show(
                    this, StringUtils.intValueOfString(channelId),
                    typesBean.data.navigationBar[1].columnName, 29, 0
                )
            }
            R.id.btn_tab_2 -> {
                val channelId =
                    Uri.parse(typesBean.data.navigationBar[2].href).getQueryParameter("channelId")
                //            MediaListActivity.show(this, StringUtils.intValueOfString(channelId),
//                    opItem.getName(), StringUtils.intValueOfString(AppConfig.PackageId), 0);
                ProgramListActivity.show(
                    this, StringUtils.intValueOfString(channelId),
                    typesBean.data.navigationBar[2].columnName, 29, 0
                )
            }
            R.id.btn_tab_3 -> {
                CategoryListActivity.show(context, 156, JSON.toJSONString(typesBean))
            }
            R.id.btn_tab_4 -> {
                CategoryListActivity.show(context, 157, JSON.toJSONString(typesBean))
            }
            R.id.btn_tab_5 -> {
                CategoryListActivity.show(context, 158, JSON.toJSONString(typesBean))
            }
            else -> {
                var index = Arrays.binarySearch(flContentButtonIdArray, viewId)
                if (viewId == R.id.btn_fl_10) {
                    index = 9
                }
                if (index >= 0 && index < pageOpData.size) {
                    val bean = pageOpData[index]
                    actionOnOp(bean)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            turnHome()
        }
    }

    fun turnHome() {
        finish()
        Process.killProcess(Process.myPid())

//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
    }

    override fun onBackPressed() {
        //endPushContentBean != null
        Log.d(TAG, "onBackPressed: " + hadBuy())
        if (!hadBuy()) {
            showQuitRecommend()
        } else {
            alertForQuit()
        }
    }

    override fun onPause() {
        if (videoView != null) {
            videoView.pause()
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        playingTitle = "弹出订购-订购菜单"
        stat("首页", "")
        if (!DiffConfig.validateDeviceKeyNO(this)) {
            return
        }
        playVideo()
    }

    override fun onDestroy() {
        videoView.stopPlayback()
        super.onDestroy()
    }

    private fun alertForQuit() {
        HiFiDialogTools.getInstance().showLeftRightTip(
            this@HomeActivity, "温馨提示", "确认退出" +
                    resources.getString(R.string.app_name), "确认", "取消"
        ) { dialog, `object` ->
            if (`object` is Int) {
                if (`object` == 0) {
                    turnHome()
                }
            }
        }
    }

    private fun showQuitRecommend() {
        val intent = Intent(this@HomeActivity, ExitActivity::class.java)
        startActivityForResult(intent, ExitActivity.TAGRecommendExit)
    }

    private fun showPageData(imagePrefix: String, list: List<OpItem?>?) {
        if (list == null || list.isEmpty()) {
            return
        }
        pageOpData.clear()
        pageOpData.addAll(list as Collection<OpItem>)
        var hasVideo = false
        var i = 0
        while (i < list.size && i < flContentImageViewArray.size) {
            if (i < flContentImageViewArray.size) {
                val bean = list[i]
                var isVideo = "1".equals(bean?.isVideo, ignoreCase = true)
                if (i == 3) {
                    isVideo = true
                }
                if (isVideo) {
                    videoData = bean
                    hasVideo = true
                    var array = videoData!!.videoUrl.split(",".toRegex()).toTypedArray()
                    if (array == null || array.size <= 1) {
                        array = videoData!!.videoUrl.split("\\|".toRegex()).toTypedArray()
                    }
                    assetUrlArray = array
                }
                if (!isVideo) {
                    val imageView = flContentImageViewArray[i]
                    Glide.with(this).load(DiffConfig.generateImageUrl(bean?.imgUrl))
                        .into(imageView!!)
                    //loadImage(imageView, bean.getImgUrl());
                }
            }
            i++
        }
        if (hasVideo) {
            hahaPlayer = videoView
            playVideo()
        }
    }

    override fun onDuration(l: Long) {
        super.onDuration(l)
    }

    override fun hahaPlayEnd(v: Float) {
        super.hahaPlayEnd(v)
        playVideo()
    }

    private fun playVideo() {
        if (assetUrlArray != null) {
            val index = Random().nextInt(assetUrlArray!!.size)
            val array = assetUrlArray
            var playIndex = index
            if (index < 0 || index >= array!!.size) {
                playIndex = 0
            }
            if (playIndex >= 0 && playIndex < array!!.size) {
                currentPlayingIndex = playIndex
                getHahaPlayerUrl(array[playIndex])
            }
        }
    }

    private fun gotoMediaPlayer() {
        if (videoData != null) {
            var array: Array<String?> = videoData!!.href.split(",".toRegex()).toTypedArray()
            if (array == null || array.size <= 1) {
                array = videoData!!.videoUrl.split("\\|".toRegex()).toTypedArray()
            }
            val index = currentPlayingIndex
            if (index >= 0 && index < array.size) {
                val url = array[index]
                val uri = Uri.parse(url)
                val programId = uri.getQueryParameter("pkId")
                val channelId = uri.getQueryParameter("channelId")
                val list = ArrayList<ProgramInfoBase>()
                val programInfoBase = ProgramInfoBase()
                programInfoBase.name = ""
                programInfoBase.channelId = StringUtils.intValueOfString(channelId)
                programInfoBase.pkId = StringUtils.intValueOfString(programId)
                programInfoBase.multiSetType = "0"
                list.add(programInfoBase)
                PlayVideoActivity.play(this, list, 0, false)
            }
        }
    }

    /*
     **** network service
     */
    private fun loadData() {
        NetworkService.defaultService().fetchHomePageData(
            this,
            AppConfig.typeId,
            object : JsonCallback<BaseResponse<List<OpItem?>?>?>() {
                override fun onSuccess(response: Response<BaseResponse<List<OpItem?>?>?>?) {
                    val bean = response?.body()
                    if (bean != null && bean.isOk) {
                        showPageData(bean.imageProfix, bean.data)
                    } else {
                        HiFiDialogTools.getInstance().showLeftRightTip(
                            this@HomeActivity, "温馨提示", "请求数据失败，请检查网络",
                            "重试", "取消"
                        ) { dialog, `object` ->
                            if (`object` is Int) {
                                if (`object` == 0) {
                                    loadData()
                                }
                            }
                        }
                    }
                }
            })
    }

    private fun loadTypesData() {
        NetworkService.defaultService()
            .fetchHomePageNavData(this, 31.toString() + "", object : JsonCallback<TypesBean?>() {
                override fun onSuccess(response: Response<TypesBean?>?) {
                    val bean = response?.body()
                    if (bean!!.isOk) {
                        typesBean = bean
                    }
                }

                override fun onFinish() {
                    super.onFinish()
                }
            })
    }

    override fun onStop() {
        super.onStop()
    }

    fun checkIntentData() {
        val programId = intent.getStringExtra("pkId")
        val multisetType = intent.getStringExtra("multisetType")
        val channelId = intent.getStringExtra("channelId")
        val albumId = intent.getStringExtra("pkId")
        val topicId = intent.getStringExtra("themId")
        val styleId = intent.getStringExtra("styleId")
        Log.d(
            TAG,
            "checkIntentData:" + "programId" + programId + "multisetType" + multisetType + "channelId" + channelId + "albumId" + albumId + "topicId" + topicId + "styleId" + styleId
        )
        if (programId != null && multisetType != null && channelId != null) {
            needEnterRecommend = false
            NetworkService.defaultService().fetchProgramContent(this,
                StringUtils.intValueOfString(programId),
                multisetType,
                StringUtils.intValueOfString(channelId),
                "",
                object : JsonCallback<BaseResponse<ProgramContent?>?>() {
                    override fun onSuccess(response: Response<BaseResponse<ProgramContent?>?>?) {
                        val data = response?.body()
                        if (data!!.isOk) {
                            val list = ArrayList<ProgramInfoBase?>()
                            list.add(data.data)
                            PlayVideoActivity.play(baseContext, list, 0, false)
                        }
                    }
                })
        }
        if (albumId != null) {
            needEnterRecommend = false
            stat("外流好推荐进入合集", ConstantEnumHuya.INDEX)
            MediaAlbumActivity.show(this, StringUtils.intValueOfString(albumId))
        }
        if (topicId != null) {
            needEnterRecommend = false
            stat("外流好推荐进入专题", ConstantEnumHuya.INDEX)
            TopicActivity.show(this, topicId, styleId)
        }
    }

    // Glide.with(HomeActivity.this).load(bgImageUrl).into(homeBg);
    //                        if (HuyaApplication.hadBuy()) {
//                            //会员不弹活动
//                            return;
//                        }
    private val initData: Unit
        private get() {
            val path =
                (DiffConfig.activityHost + "/activity/androidActivityController/getPushContent.utvgo?regionCode="
                        + DiffConfig.getRegionCode(this))
            try {
                NetUtils.getData(
                    this,
                    path,
                    null,
                    BeanInitData::class.java
                ) { requestTag, `object` ->
                    if (`object` != null) {
                        XLog.d("logom$`object`")
                        val beanInitData = `object` as BeanInitData
                        if (beanInitData != null) {
                            val bgImageUrl =
                                DiffConfig.generateImageUrl(beanInitData.homePageResource.imagUrl)
                            val logoImageUrl =
                                DiffConfig.generateImageUrl(beanInitData.homePageResource.logoUrl)
                            if ("" != bgImageUrl && "" != logoImageUrl) {
                                ivHomeLogo?.let {
                                    Glide.with(this@HomeActivity).load(logoImageUrl).into(
                                        it
                                    )
                                }
                                // Glide.with(HomeActivity.this).load(bgImageUrl).into(homeBg);
                            }
                            //                        if (HuyaApplication.hadBuy()) {
//                            //会员不弹活动
//                            return;
//                        }
                            if (beanInitData.startPushContent != null && needEnterRecommend) {
                                if (!TextUtils.isEmpty(beanInitData.startPushContent.href) && beanInitData.startPushContent.href != null) {
                                    QWebViewActivity.navigateUrl(
                                        this@HomeActivity,
                                        beanInitData.startPushContent.href,
                                        null,
                                        null
                                    )
                                } else {
                                    val intent =
                                        Intent(this@HomeActivity, ActivityActivity::class.java)
                                    intent.putExtra("bgImageUrl", bgImageUrl)
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
}