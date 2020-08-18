package com.utvgo.huya.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonObject;
import com.lzy.okgo.model.Response;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.OpItem;
import com.utvgo.huya.beans.TypesBean;
import com.utvgo.huya.net.NetworkService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * @author wzb
 * @description:
 * @date : 2020/8/3 14:26
 */
public class AlbumListActivity extends BasePageActivity{
    @BindView(R.id.content_name)
    TextView content_name;
    private List<OpItem> contentData = new ArrayList<>();
    private int typeId = 81;
    private String name = "";
    private String string = "";
    private final int contentImgArr[] = {R.id.album_img_0,R.id.album_img_1,R.id.album_img_2,R.id.album_img_3,R.id.album_img_4,R.id.album_img_5,
            R.id.album_img_6,R.id.album_img_7,R.id.album_img_8};
    private final int contentBtnArr[] = {R.id.album_btn_0,R.id.album_btn_1,R.id.album_btn_2,R.id.album_btn_3,R.id.album_btn_4,R.id.album_btn_5,
            R.id.album_btn_6,R.id.album_btn_7,R.id.album_btn_8};

    public static void show(Context context, int typeId,String name ,String string) {
        Intent intent = new Intent(context,AlbumListActivity.class);
        intent.putExtra("typeId",typeId);
        intent.putExtra("name",name);
        intent.putExtra("string",string);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mv_new);
        ButterKnife.bind(this);
        this.typeId = getIntent().getIntExtra("typeId",81);
        this.string = getIntent().getStringExtra("string");
        this.name = getIntent().getStringExtra("name");
        TypesBean typesBean = JSON.parseObject(this.string,new TypeReference<TypesBean>(){});
        content_name.setText(this.name);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fetchContentData();
            }
        });
    }
    private void fetchContentData(){
        NetworkService.defaultService().fetchHomePageData(this, this.typeId, new JsonCallback<BaseResponse<List<OpItem>>>() {
            @Override
            public void onSuccess(Response<BaseResponse<List<OpItem>>> response) {
                BaseResponse<List<OpItem>> bean = response.body();
                contentData = bean.getData();
                if (bean.isOk()&&bean != null){
                    showContenData(bean.getData());
                }
            }
        });
    }

    private void showContenData(List<OpItem> bean) {
        for (int i = 0;i < bean.size()&&i < contentImgArr.length;i++){
            loadImage((ImageView) findViewById(contentImgArr[i]), DiffConfig.generateImageUrl(bean.get(i).getImgUrl()));
        }
    }

    @Override
    @OnFocusChange({R.id.album_btn_0,R.id.album_btn_1,R.id.album_btn_2,R.id.album_btn_3,R.id.album_btn_4,R.id.album_btn_5,
            R.id.album_btn_6,R.id.album_btn_7,R.id.album_btn_8})
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        if (hasFocus){
            ((View) v.getParent()).bringToFront();
            ViewCompat.animate((View) v.getParent()).scaleY(1.12f).scaleX(1.12f).start();
        }else {
            ViewCompat.animate((View) v.getParent()).scaleY(1.00f).scaleX(1.00f).start();
        }
    }

    @Override
    @OnClick({R.id.album_btn_0,R.id.album_btn_1,R.id.album_btn_2,R.id.album_btn_3,R.id.album_btn_4,R.id.album_btn_5,
            R.id.album_btn_6,R.id.album_btn_7,R.id.album_btn_8})
    public void onClick(View view) {
        super.onClick(view);
        int index = Arrays.binarySearch(contentBtnArr,view.getId());
        actionOnOp(contentData.get(index));
    }

    @Override
    void getData() {

    }
}
