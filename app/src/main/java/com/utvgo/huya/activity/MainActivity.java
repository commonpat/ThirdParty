package com.utvgo.huya.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.MainFragmentAdapter;
import com.utvgo.huya.R;
import com.utvgo.huya.UTVGOSubscriber;
import com.utvgo.huya.beans.BeanExitPage;
import com.utvgo.huya.beans.TypesBean;
import com.utvgo.huya.databinding.ActivityMainBinding;
import com.utvgo.huya.fragments.Fragment1;
import com.utvgo.huya.fragments.Fragment2;
import com.utvgo.huya.interfaces.CommonCallback;
import com.utvgo.huya.interfaces.IPurchase;
import com.utvgo.huya.listeners.MainPageChangeListener;
import com.utvgo.huya.listeners.MyDialogEnterListener;
import com.utvgo.huya.net.Purchase;
import com.utvgo.huya.utils.ActivityUtility;
import com.utvgo.huya.utils.Appconfig;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static com.utvgo.huya.constant.ConstantEnum.TAGRecommendExit;


public class MainActivity extends BuyActivity implements RadioGroup.OnCheckedChangeListener {
    ActivityMainBinding binding;
    private List<Fragment> list = new ArrayList();
    private MainFragmentAdapter adapter;
    TypesBean typesBean = new TypesBean();
    Purchase purchase = new Purchase();
    final Activity activity = this;
    BeanExitPage beanExitPage;
    List<BeanExitPage.Data> endPushContentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        server.getTypes(Appconfig.getKeyNo(this), new UTVGOSubscriber<TypesBean>() {
            public void onNext(TypesBean typesBean) {
                if (typesBean.getCode().equals("1")) {
                    loadTypes(typesBean);
                    showViewByHandler(findViewById(R.id.bits_1));
                }
            }
        });
        asyncHttpRequest.getExitPage(this, null, this, this);
//       String json="[{'typeName':'全部','typeId':48,'templateId':17},{'typeName':'推荐','typeId':40,'templateId':16},{'typeName':'王者荣耀','typeId':41,'templateId':17},{'typeName':'英雄联盟','typeId':42,'templateId':17},{'typeName':'绝地求生','typeId':43,'templateId':17}]" ;
//        List<TypesBean.DataBean>  list =com.alibaba.fastjson.JSONObject.parseArray(json,TypesBean.DataBean.class);
//        typesBean.setCode("1");
//        typesBean.setMessage("success");
//        typesBean.setData(list);
//       loadTypes(typesBean);

    }


    private void loadTypes(TypesBean typesBean) {
        for (int i = 0; i < typesBean.getData().getNavigationBar().size(); i++) {
            if (binding.navs.getChildCount() > i) {
                binding.navs.getChildAt(i).setId(i);
                binding.navs.getChildAt(i).setVisibility(View.VISIBLE);
                //    ((RadioButton) binding.navs.getChildAt(i)).setText(typesBean.getData().get(i).getTypeName());
            }
            Fragment fragment;
            if (i < 1) {
                fragment = new Fragment1();
            } else {
                fragment = new Fragment2();
            }
            Bundle args = new Bundle();
            String id = Uri.parse(typesBean.getData().getNavigationBar().get(i).getHref()).getQueryParameter("typeId");
            if (id == null || "".equals(id)) {
                id = "71";
            }
            int id2 = Integer.valueOf(id);
            args.putInt("typedId", id2);
            fragment.setArguments(args);
            list.add(fragment);
        }
        initView();
    }

    private void initView() {

        adapter = new MainFragmentAdapter(getSupportFragmentManager(), list);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setCurrentItem(0);
        //noinspection deprecation
        binding.viewPager.setOnPageChangeListener(new MainPageChangeListener() {
                                                      @Override
                                                      public void onPageSelected(int id) {
                                                          if (id > 3) {

                                                          } else {
                                                              currentButton = binding.navs.findViewById(id);
                                                              ((RadioButton) binding.navs.findViewById(id)).setChecked(true);
                                                          }
                                                      }
                                                  }
        );
        binding.focusView.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
//        initNavsId();
        binding.navs.setOnCheckedChangeListener(this);
        // binding.btnOrder.setOnFocusChangeListener((View.OnFocusChangeListener) this);
        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                purchase.auth(activity, new IPurchase.AuthCallback() {
                    @Override
                    public void onFinished(String message) {
                        if (HuyaApplication.hadBuy()) {
                            ToastUtil.show(activity, "你已订购");
                        } else {
                            if (!TextUtils.isEmpty(message)) {
                                ToastUtil.show(activity, message);
                            }
                            purchase.pay(activity, new CommonCallback() {
                                @Override
                                public void onFinished(Context context) {

                                }
                            });
                        }
                    }
                });
            }
        });
        binding.btnCollect.setOnClickListener(this);
        binding.btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtility.goActivity(activity, UserCenterActivity.class);
            }
        });
        binding.btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtility.goActivity(activity, IntroduceActivity.class);
            }
        });
        //  binding.focusView.setOnClickListener(this);
        handler.post(new Runnable() {
            @Override
            public void run() {
                ((RadioButton) binding.navs.getChildAt(0)).setChecked(true);
                currentButton = binding.navs.getChildAt(0);
            }
        });
    }

    public void getRootView() {

    }

    private void initNavsId() {
        for (int index = 0; index < binding.navs.getChildCount(); index++) {
            binding.navs.getChildAt(index).setId(index);
        }
    }

    private View currentButton;


    @Override//下个方法是onfocusChange
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        if (newFocus.getId() == R.id.btn_main_introduction  || newFocus.getId() == R.id.btn_main_order
                || newFocus.getId() == R.id.btn_main_user_center || newFocus.getId() == R.id.btn_main_user_favor ||
                newFocus.getId() == R.id.main_tab_1  || newFocus.getId() == R.id.main_tab_2
                || newFocus.getId() == R.id.main_tab_4 || newFocus.getId() == R.id.main_tab_3) {
            binding.focusView.setVisibility(View.INVISIBLE);
        } else{
            binding.focusView.setVisibility(View.VISIBLE);
            if (binding.navs.equals(newFocus.getParent())) {
                if (oldFocus != null && binding.navs.equals(oldFocus.getParent()) && currentButton != null && binding.navs.equals(newFocus.getParent())) {
                    //       currentButton.requestFocus();
//                ((RadioButton) newFocus).setChecked(true);
//                // binding.navs.getChildAt(binding.navs.getCheckedRadioButtonId()).requestFocus();
//            } else {
                    ((RadioButton) newFocus).setChecked(true);
                    currentButton = newFocus;
                }
                binding.focusView.setFocusView(newFocus, R.mipmap.border_focus_style_default);
                // binding.focusView.setVisibility(View.INVISIBLE);

            } else {
                if (oldFocus == null || (oldFocus != null && binding.navs.equals(oldFocus.getParent())) || (oldFocus != null && oldFocus.getId() == R.id.bits_1)) {
                    binding.focusView.setFocusView(newFocus, R.mipmap.border_focus_style_default);
                } else {
                    binding.focusView.fly(newFocus, oldFocus, R.mipmap.border_focus_style_default);
                }
                //    binding.focusView.setVisibility(View.VISIBLE);
            }
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (binding.viewPager.getAdapter().getCount() > i) {
            binding.viewPager.setCurrentItem(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAGRecommendExit) {

            turnHome();
        }
    }

    public void turnHome() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onClick(View v) {
        final Context context = this;
        switch (v.getId()) {
            case R.id.btn_main_order:

                purchase.auth(this, new IPurchase.AuthCallback() {
                    @Override
                    public void onFinished(String message) {
                        if (HuyaApplication.hadBuy()) {
                            ToastUtil.show(context, "您已经是 " + getResources().getString(R.string.app_name) + " 尊贵会员");
                        } else {
                            if (!TextUtils.isEmpty(message)) {
                                ToastUtil.show(context, message);
                            }
                            purchase.pay(context, new CommonCallback() {
                                @Override
                                public void onFinished(Context context) {

                                }
                            });
                        }
                    }
                });
                break;
            case R.id.btn_main_user_favor: {
                break;
            }
            case R.id.btn_main_introduction: {
                startActivity(new Intent(this, IntroduceActivity.class));
                break;
            }
            case R.id.btn_main_user_center:
            {
                startActivity(new Intent(this, UserCenterActivity.class));
                break;
            }
        }
    }

    @Override
    public void onSucceeded(String method, String key, Object object) throws Exception {
        switch (method) {
            case "page.utvgo":
                if (object instanceof BeanExitPage)
                    beanExitPage = (BeanExitPage) object;
                endPushContentBean = beanExitPage.getData();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (endPushContentBean != null && !HuyaApplication.hadBuy()) {
            Intent intent = new Intent(MainActivity.this, ExitActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra("bgUrl", endPushContentBean.get(0).getBgImgUrl());
            intent.putExtra("contentMid", endPushContentBean.get(0).getTypeId());
            //intent.putExtra("beanExitPage",  beanExitPage);
            intent.putExtra("recommendType", "exit");
            startActivity(intent);
            //startActivityForResult(intent, ConstantEnum.TAGRecommendExit);

        } else {
            endDefault();
        }
    }

    private void endDefault() {
        HiFiDialogTools.getInstance().showLeftRightTip(MainActivity.this, "温馨提示", "确认退出" +
                getResources().getString(R.string.app_name), "确认", "取消", new MyDialogEnterListener() {
            @Override
            public void onClickEnter(Dialog dialog, Object object) {
                if (object instanceof Integer) {
                    if (((Integer) object) == 0) {
                        turnHome();
                    }
                }
            }
        });
    }

}