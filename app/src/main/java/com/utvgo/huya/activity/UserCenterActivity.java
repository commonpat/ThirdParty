package com.utvgo.huya.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.XLog;
import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.BeanArryPage;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.IPurchase;
import com.utvgo.handsome.interfaces.CommonCallback;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.AppUtils;
import com.utvgo.huya.views.FocusView;
import com.utvgo.huya.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by fute on 17/3/24.
 */

public class UserCenterActivity extends BuyActivity {

    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.btn_playRecord)
    Button btnPlayRecord;
    @BindView(R.id.btn_order)
    Button btnOrder;

    @BindView(R.id.image_view_0)
    ImageView imageView0;
    @BindView(R.id.image_view_1)
    ImageView imageView1;
    @BindView(R.id.image_view_2)
    ImageView imageView2;

    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_keyNo)
    TextView tvKeyNo;
    @BindView(R.id.tv_area)
    TextView tvArea;

    private String code = "";
    FocusView focus;

    Map<Integer, OpItem> opData = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        needBringFront = false;
        setContentView(R.layout.activity_user_center);
        ButterKnife.bind(this);


        String areaName = "";//Appconfig.getRegionCode(this);

        tvUserName.setText("户 主 ：***");
        tvArea.setText("地 区 ：" + areaName);
        tvVersion.setText("版本号：" + AppUtils.getLocalVersionName(this));

        String keyNO = DiffConfig.getCA(this);
        tvKeyNo.setText("卡 号 ：" + keyNO);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnOrder.requestFocus();
                loadData();
            }
        });
    }

    @OnClick({R.id.btn_playRecord, R.id.btn_order, R.id.btn_image_view_0, R.id.btn_image_view_1, R.id.btn_image_view_2 /*,R.id.ll_userInfo,,R.id.my_song_list*/})
    public void onClick(View view) {
        String keyNO = DiffConfig.getCA(this);
        final Context context = this;

        int i = view.getId();
        if (i == R.id.btn_playRecord) {
            Intent intent = new Intent(this, UserPlayHistoryActivity.class);
            intent.putExtra("type", 0);
            startActivity(intent);

        } else if (i == R.id.btn_order) {
            DiffConfig.CurrentPurchase.auth(this, new IPurchase.AuthCallback() {
                @Override
                public void onFinished(String message) {
                    if (HuyaApplication.hadBuy()) {
                        ToastUtil.show(context, "您已经是 " + getResources().getString(R.string.app_name) + " 尊贵会员");
                    } else {
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtil.show(context, message);
                        }
                        DiffConfig.CurrentPurchase.pay(context, new CommonCallback() {
                            @Override
                            public void onFinished(Context context) {

                            }
                        });
                    }
                }
            });
        } else if (i == R.id.btn_image_view_0) {
            clickRecItem(i);
        } else if (i == R.id.btn_image_view_1) {
            clickRecItem(i);
        } else if (i == R.id.btn_image_view_2) {
            clickRecItem(i);
        }
    }

    public void clickRecItem(final int viewId) {
        OpItem opItem = opData.get(viewId);
        if(opItem != null)
        {
            actionOnOp(opItem);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_0:
                    code = code + "0";
                    break;
                case KeyEvent.KEYCODE_1:
                    code = code + "1";
                    break;
                case KeyEvent.KEYCODE_2:
                    code = code + "2";
                    break;
                case KeyEvent.KEYCODE_3:
                    code = code + "3";
                    break;
                case KeyEvent.KEYCODE_4:
                    code = code + "4";
                    break;
                case KeyEvent.KEYCODE_5:
                    code = code + "5";
                    break;
                case KeyEvent.KEYCODE_6:
                    code = code + "6";
                    break;
                case KeyEvent.KEYCODE_7:
                    code = code + "7";
                    break;
                case KeyEvent.KEYCODE_8:
                    code = code + "8";
                    break;
                case KeyEvent.KEYCODE_9:
                    code = code + "9";
                    break;
            }
            checkCode();
        }
        return super.dispatchKeyEvent(event);
    }

    private void checkCode() {
        if ("0123".equals(code)) {
            code = "";
            //Intent intent = new Intent(this, MemberSingleActivity.class);
            //startActivity(intent);
        }
    }

    void loadData()
    {
        NetworkService.defaultService().fetchUserCenterOpData(this, new JsonCallback<BaseResponse<List<List<OpItem>>>>() {
            @Override
            public void onSuccess(Response<BaseResponse<List<List<OpItem>>>> response) {
                BaseResponse<List<List<OpItem>>> data = response.body();
                if(data != null && data.isOk())
                {
                    List<List<OpItem>> array = data.getData();
                    if(array != null)
                    {
                        final ImageView[] targetImageViewArray  = {imageView0, imageView1, imageView2};
                        final int[] targetButtonArray  = {R.id.btn_image_view_0, R.id.btn_image_view_1, R.id.btn_image_view_2};
                        for(int i = 0; i < targetImageViewArray.length; i++)
                        {
                            ImageView imageView = targetImageViewArray[i];
                            int buttonId = targetButtonArray[i];

                            if(array.size() > i)
                            {
                                List<OpItem> itemDataArray = array.get(i);
                                if(itemDataArray.size() > 0)
                                {
                                    OpItem opItem = itemDataArray.get(0);
                                    opData.put(buttonId, opItem);
                                    loadImage(imageView, DiffConfig.generateImageUrl(opItem.getImgUrl()));
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
