package com.utvgo.handsome.interfaces;

import android.content.Context;

public interface CommonCallback {
    void onFinished(final Context context);
    void onSuccess(final Context context);
    void onFail(final Context context);
}
