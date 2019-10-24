package com.utvgo.handsome.diff;

import android.content.Context;

public class GDTVBox implements ITVBox {
    @Override
    public String getCA(Context context) {
        return "8002004093892878";
    }

    @Override
    public String getRegionCode(Context context) {
        return "12000";
    }

    @Override
    public void fetchUrlByVODAssetId(final Context context, String vodId, FetchUrlByVODAssetIdCallBack callback) {

    }
}
