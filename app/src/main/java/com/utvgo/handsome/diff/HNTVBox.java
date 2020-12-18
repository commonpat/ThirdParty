package com.utvgo.handsome.diff;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.utvgo.handsome.bean.HNTVAuthData;
import com.utvgo.handsome.interfaces.JsonCallback;
import com.utvgo.handsome.utils.NetworkUtils;
import com.utvgo.handsome.utils.SystemPropertiesProxy;
import com.utvgo.handsome.utils.XLog;

import rx.Observer;

public class HNTVBox implements ITVBox {
    @Override
    public String getCA(Context context) {
        String clientId = "";
        try {
            clientId = SystemPropertiesProxy.get(context, "persist.sys.ca.card_id");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return clientId;
    }

    @Override
    public String getRegionCode(Context context) {
        return "101";
    }

    @Override
    public void initDeviceInfo(Context context) {

    }

    @Override
    public void fetchUrlByVODAssetId(final Context context, final String vodId, final FetchUrlByVODAssetIdCallBack callback) {
        final String ca = DiffConfig.getCA(context);
        String url = DiffConfig.baseHost+"/cq-order-web/hunan/hnUserController/authorization3A.utvgo";
        NetworkUtils.get(context, url, new JsonCallback<HNTVAuthData>() {
            @Override
            public void onSuccess(Response<HNTVAuthData> response) {
                try{
                    HNTVAuthData authData = response.body();
                    String playUrl = generatePlayUrl(ca, vodId,  authData.getAAAResult());
                    callback.onReceivedUrl(vodId, playUrl);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取播放串
     */
    public static String generatePlayUrl(String keyNo, String vodId, HNTVAuthData.AAAResultBean bean) {
        String url = "http://lvs.hunancatv.com:8060/vod/" + vodId + ".m3u8?sid=" + bean.getSid() +
                "&userid=&deviceid=" + keyNo +
                "&nonce=" + bean.getNonce() +
                "&acl=" + bean.getAcl();
        XLog.e("HNTV generatePlayUrl:", url);
        return url;
    }
}
