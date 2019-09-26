package com.utvgo.huya.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.utvgo.huya.GlideApp;
import com.utvgo.huya.R;
import com.utvgo.huya.UTVGOSubscriber;
import com.utvgo.huya.activity.MVAlbumActivity;
import com.utvgo.huya.activity.MVListActivity;
import com.utvgo.huya.activity.TopicActivity;
import com.utvgo.huya.beans.BeanTopic;
import com.utvgo.huya.beans.BeanUserPlayList;
import com.utvgo.huya.beans.PageBean;
import com.utvgo.huya.databinding.Page2Binding;
import com.utvgo.huya.net.AsyncHttpRequest;
import com.utvgo.huya.net.IVolleyRequestSuccess;
import com.utvgo.huya.net.IVolleyRequestfail;
import com.utvgo.huya.utils.ActivityUtility;
import com.utvgo.huya.utils.StrTool;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Fragment2 extends BaseFragment{
    private String TAG="Fragment2";
    private Page2Binding binding;
    private ImageView[] imgs;
    //PageBean pageBean=new PageBean();
    AsyncHttpRequest asyncHttpRequest=new AsyncHttpRequest();
    private List<BeanTopic.DataBean.UtvgoSubjectRecordListBean> utvgoSubjectRecordListBeans;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " );
        binding = DataBindingUtil.inflate(inflater, R.layout.page_2, container, false);
        imgs= new ImageView[]{binding.bits1,binding.bits2,binding.bits3,binding.bits4,binding.bits5,binding.bits6,binding.bits7,binding.bits8};
        for (int i=0;i<imgs.length;i++){
            imgs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.bits_1:
                            clickRecItem(0);
                            break;
                        case R.id.bits_2:
                            clickRecItem(1);
                            break;
                        case R.id.bits_3:
                            clickRecItem(2);
                            break;
                        case R.id.bits_4:
                            clickRecItem(3);
                            break;
                        case R.id.bits_5:
                            clickRecItem(4);
                            break;
                        case R.id.bits_6:
                            clickRecItem(5);
                            break;
                        case R.id.bits_7:
                            clickRecItem(6);
                            break;
                        case R.id.bits_8:
                            clickRecItem(7);
                            break;
                        case R.id.bits_9:
                            clickRecItem(8);
                            break;
                        case R.id.bits_10:
                            clickRecItem(9);
                            break;

                    }
                }
            });
        }
        asyncHttpRequest.getTopic(getContext(), String.valueOf(typeId), new IVolleyRequestSuccess() {
            @Override
            public void onSucceeded(String method, String key, Object object) throws Exception {
                BeanTopic beanTopic = (BeanTopic) object;
                utvgoSubjectRecordListBeans=beanTopic.getData().getUtvgoSubjectRecordList();
                for(int i=0;i<beanTopic.getData().getUtvgoSubjectRecordList().size() &&i<imgs.length;i++){
                    BeanTopic.DataBean.UtvgoSubjectRecordListBean dataBean = beanTopic.getData().getUtvgoSubjectRecordList().get(i);
//                    if(i==0){
//                        if(dataBean.getIsVideo().equals("0")){
//                        }else {
//                            ((View) binding.bits1.getParent()).setVisibility(View.VISIBLE);
//                        }
//                    }else {
                       // if (dataBean.getIsVideo().equals("0")) {
                     Log.e(TAG, "onNext: "+beanTopic.getImageProfix()+dataBean.getImgUrl() );
                     GlideApp.with(getContext()).load(beanTopic.getImageProfix()+dataBean.getImgUrl()).into(imgs[i]);


                }
            }
        }, new IVolleyRequestfail() {
            @Override
            public void onFailed(String method, String key, int errorTipId) throws Exception {
                Log.d(TAG, "onFailed: "+errorTipId);
            }
        });


//        getBaseActivity().server.getPage(typeId, getKeyNo(),new UTVGOSubscriber<PageBean>(){
//            @Override
//            public void onNext(PageBean pageBean) {
//                for(int i=0;i<pageBean.getData().size()&&i<imgs.length;i++){
//                    PageBean.DataBean dataBean = pageBean.getData().get(i);
//                    if(i==0){
//                        if(dataBean.getIsVideo().equals("0")){
//                        }else {
//                            ((View) binding.bits1.getParent()).setVisibility(View.VISIBLE);
//                        }
//                    }else {
//                        if (dataBean.getIsVideo().equals("0")) {
//                            Log.e(TAG, "onNext: "+pageBean.getImageProfix()+dataBean.getImgUrl() );
//                            GlideApp.with(getContext()).load(pageBean.getImageProfix()+dataBean.getImgUrl()).into(imgs[i]);
//                        }
//                    }
//                }
//            }
//        });
//        for(int i=0;i<pageBean.getData().size()&&i<imgs.length;i++){
//            PageBean.DataBean dataBean = pageBean.getData().get(i);
//            if(i==0){
//                if(dataBean.getIsVideo().equals("0")){
//                }else {
//                    ((View) binding.bits1.getParent()).setVisibility(View.VISIBLE);
//                }
//            }else {
//                if (dataBean.getIsVideo().equals("0")) {
//                    Log.e(TAG, "onNext: "+pageBean.getImageProfix()+dataBean.getImgUrl() );
//                    Glide.with(getContext()).load(pageBean.getImageProfix()+dataBean.getImgUrl()).into(imgs[i]);
//                }
//            }
//        }
        return binding.getRoot();
    }
    private void clickRecItem(int index) {
        BeanTopic.DataBean.UtvgoSubjectRecordListBean selectBean = utvgoSubjectRecordListBeans.get(index);
        String href = selectBean.getHref();
        if(href.indexOf("albumPlayer.html") >= 0)
        {
            Intent intent = new Intent(getActivity(), MVAlbumActivity.class);
            intent.putExtra("albumMid", Uri.parse(href).getQueryParameter("pkId"));
            startActivity(intent);
        }
        else if(href.indexOf("listPage.html") >= 0)
        {
            String channelId=Uri.parse(selectBean.getHref()).getQueryParameter("channelId");
            String labelId = "";
            String category = "";
            String mvType = "";
            String groupId = "";
            MVListActivity.show(getContext(), false, channelId, category, mvType, labelId);
        }

//        selectBean.setHref("http://172.16.146.41/qqmusic_zt.html?themId=139&styleID=6");
//        selectBean.setHrefType("7");
//        if (TextUtils.equals(selectBean.get, "0")) { //超链接
//            ActivityUtility.goWebActivityActivity(getActivity(), selectBean.getHref());
//        } else
//        if (TextUtils.equals(selectBean.gethrefType(), "3")) { //mv专辑
//            Intent intent = new Intent(getActivity(), MVAlbumActivity.class);
//            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("pkId"));
//            startActivity(intent);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "1")) { //音频专辑
//            Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);
//            intent.putExtra("albumMid", Uri.parse(selectBean.getHref()).getQueryParameter("zjid"));
//            startActivity(intent);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "8")) { //活动
//            //Intent intent = new Intent(IndexActivity.this, ActivityActivity.class);
//            //startActivity(intent);
//            //todo
//        } else if (selectBean.getHref().contains("collect.html")) { //收藏
//            Intent intent = new Intent(this, CollectCenterActivity.class);
//            intent.putExtra("type", 1);
//            startActivity(intent);
//        } else if (selectBean.getHref().contains("htyplay.html")) { //历史
//            Intent intent = new Intent(this, CollectCenterActivity.class);
//            intent.putExtra("type", 0);
//            startActivity(intent);
       // }
        //else if (TextUtils.equals(selectBean.gethrefType(), "7")) { //专题
//            Intent intent = new Intent(getContext(), TopicActivity.class);
//            intent.putExtra("topicId", Uri.parse(selectBean.getHref()).getQueryParameter("themId"));
//            intent.putExtra("type", Uri.parse(selectBean.getHref()).getQueryParameter("styleID"));
//            startActivity(intent);
//        } else if (selectBean.getHref().contains("recordList_pd.html")) { //榜单
//            ActivityUtility.goSongRankActivity(this, Uri.parse(selectBean.getHref()).getQueryParameter("mid"),
//                    Uri.parse(selectBean.getHref()).getQueryParameter("id"), selectBean.getName());
//        } else if (TextUtils.equals(selectBean.getHrefType(), "12")) {//专题收录
//            ActivityUtility.goActivity(this, TopicCollectionActivity.class);
//        } else if (TextUtils.equals(selectBean.getHrefType(), "13")) {//直播
//            if (!TextUtils.isEmpty(selectBean.getHref())) {
//                String[] strs = selectBean.getHref().split("=");
//                if (strs.length > 1) {
//                    turnLiveActivity(Integer.parseInt(strs[1]));
//                }
//            }
//        } else {
//            //视频播放
//            ArrayList<BeanUserPlayList.DataBean> playHistoryList = new ArrayList<>();
//            getHrefList(index, selectBean, playHistoryList);
//            Intent intent = new Intent(this, PlayVideoActivity.class);
//            if (index == 1) {
//                intent.putExtra("playIndex", vodIdPlayIndex);
//            } else {
//                intent.putExtra("playIndex", 0);
//            }
//            intent.putExtra("fileType", 1);
//            intent.putParcelableArrayListExtra("playList", playHistoryList);
//            this.startActivity(intent);
//        }
        }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: " );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: " );
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG, "onHiddenChanged: "+hidden );
    }
}
