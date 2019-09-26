package com.utvgo.huya.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.utvgo.huya.HuyaApplication;
import com.utvgo.huya.activity.BaseActivity;


public abstract class BaseFragment extends Fragment {
    public  int typeId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeId=getArguments().getInt("typedId");
    }
    public BaseActivity getBaseActivity(){
        return ((BaseActivity) getActivity());
    }
    public HuyaApplication getApplication(){
        return ((HuyaApplication) getActivity().getApplication());
    }
    public String getKeyNo(){
        return getApplication().keyNo;
    }

}
