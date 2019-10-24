package com.utvgo.huya.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BaseResponse;
import com.utvgo.huya.beans.ProgramInfoBase;
import com.utvgo.huya.beans.UserFavoriteData;
import com.utvgo.huya.beans.UserPlayHistoryData;
import com.utvgo.huya.net.NetworkService;
import com.utvgo.huya.utils.HiFiDialogTools;
import com.utvgo.huya.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserPlayHistoryActivity extends BaseActivity {

    @BindView(R.id.fl_songList)
    FrameLayout flSongList;
    @BindView(R.id.tv_page)
    TextView tvPage;
    @BindView(R.id.tv_singer_name)
    TextView tvSingerName;

    private int orderType = 1;
    private int pageNo = 1;
    private int pageSize = 10;
    private int pageTotal = 1;
    private List<UserPlayHistoryData.Item> dataList = new ArrayList<>();
    private List<FrameLayout> itemList = new ArrayList<>();
    private List<TextView> nameTVList = new ArrayList<>();
    private List<TextView> indexTVList = new ArrayList<>();
    private List<TextView> singNameList = new ArrayList<>();

    UserFavoriteData data = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_play_history);
        ButterKnife.bind(this);

        traversalView(this);
        createBorderView(this);
        initView();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        });
    }

    private void initView() {
        for (int i = 0; i < flSongList.getChildCount(); i++) {
            FrameLayout layout = (FrameLayout) flSongList.getChildAt(i);
            itemList.add(layout);
            indexTVList.add((TextView) layout.getChildAt(1));
            nameTVList.add((TextView) ((LinearLayout) layout.getChildAt(2)).getChildAt(0));

            singNameList.add((TextView) layout.getChildAt(3));
            layout.setVisibility(View.GONE);
            layout.setOnClickListener(this);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) v;
            if (hasFocus) {
                ((TextView) frameLayout.getChildAt(1)).setTextColor(ContextCompat.getColor(this, R.color.yellow));
                ((TextView) ((LinearLayout) frameLayout.getChildAt(2)).getChildAt(0)).setTextColor(ContextCompat.getColor(this, R.color.yellow));
            } else {
                ((ImageView) frameLayout.getChildAt(0)).setImageResource(0);
                ((TextView) frameLayout.getChildAt(1)).setTextColor(ContextCompat.getColor(this, R.color.white));
                ((TextView) ((LinearLayout) frameLayout.getChildAt(2)).getChildAt(0)).setTextColor(ContextCompat.getColor(this, R.color.white));
            }
        }
        super.onFocusChange(v, hasFocus);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean ret = false;
        if (focusView != null && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((focusView.getId() == R.id.fl_song2 || focusView.getId() == R.id.fl_song4 || focusView.getId() == R.id.fl_song6 || focusView.getId() == R.id.fl_song8
                    || focusView.getId() == R.id.fl_song10) && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {//下一页
                showOther(flSongList, 2000);
                ret =  true;
            } else if ((focusView.getId() == R.id.fl_song1 || focusView.getId() == R.id.fl_song3 || focusView.getId() == R.id.fl_song5 || focusView.getId() == R.id.fl_song7
                    || focusView.getId() == R.id.fl_song9) && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {//上一页
                showOther(flSongList, -2000);
                ret = true;
            }
        }
        if(!ret)
        {
            ret = super.onKeyDown(keyCode, event);
        }
        return ret;
    }

    @Override
    public void showOther(ViewGroup viewGroup, int value) {
        if (value > 0) {
            //下一页
            pageNo++;
            if (pageNo > pageTotal) {
                pageNo = pageTotal;
                return;
            }
        } else if (value < 0) {
            //上一页
            pageNo--;
            if (pageNo < 1) {
                pageNo = 1;
                return;
            }
        }
        loadData();
        super.showOther(viewGroup, value);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (itemList.contains(v)) {
            ArrayList<ProgramInfoBase> list = new ArrayList<>();
            int index = itemList.indexOf(v);
            for (int i = 0; i < dataList.size(); i++) {
                UserPlayHistoryData.Item item = dataList.get(index);
                list.add(item.toProgram());
            }
            PlayVideoActivity.play(this, list, index, false);
        }
    }

    //
    void updateData(final UserPlayHistoryData data, final int totalPage)
    {
        if(data == null || data.getHistorys() == null)
        {
            return;
        }

        List<UserPlayHistoryData.Item> list = data.getHistorys();
        this.dataList.clear();
        this.dataList.addAll(list);

        for (int i = 0; i < list.size() && i < itemList.size(); i++) {
            itemList.get(i).setVisibility(View.VISIBLE);
            UserPlayHistoryData.Item dataBean = list.get(i);
            nameTVList.get(i).setText(dataBean.getProgramName());
            indexTVList.get(i).setText((pageNo - 1) * pageSize + i + 1 + "");
        }

        for (int n = this.dataList.size(); n < itemList.size(); n++) {
            itemList.get(n).setVisibility(View.INVISIBLE);
        }

        this.pageTotal = totalPage;
        updatePageView();

        showViewByHandler(itemList.get(0));
    }

    void updatePageView()
    {
        tvPage.setText("< " + Math.min(pageNo, pageTotal) + "/" + pageTotal + " >");
        if(this.pageTotal <= 0)
        {
            ToastUtil.show(this, "您没有任何播放记录");
        }
    }

    //network
    public void loadData() {
        final Context context = this;
        NetworkService.defaultService().fetchUserPlayHistoryData(this, pageNo, pageSize, new JsonCallback<BaseResponse<UserPlayHistoryData>>() {
            @Override
            public void onSuccess(Response<BaseResponse<UserPlayHistoryData>> response) {
                BaseResponse<UserPlayHistoryData> data =  response.body();
                if (data != null && data.isOk()) {
                    updateData(data.getData(), data.getTotalPage());
                    updatePageView();
                } else {
                    HiFiDialogTools.getInstance().showtips(context, "获取数据失败，请稍后重试", null);
                }
            }
        });
    }
}
