package com.utvgo.handsome.diff;

import android.content.Context;

public interface ITVBox {

    interface FetchUrlByVODAssetIdCallBack
    {
        void onReceivedUrl(final String vodId, final String url);
    }

    String getCA(final Context context);
    String getRegionCode(final Context context);
    void initDeviceInfo(final Context context);

    void fetchUrlByVODAssetId(final Context context, final String vodId, final FetchUrlByVODAssetIdCallBack callback);
}
