package com.utvgo.huya.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.utils.URLBuilder;
import com.utvgo.huya.R;
import com.utvgo.huya.net.QJSInterface;

public class QWebViewActivity extends BaseActivity {

    long timeStamp = 0;

    public enum RequestType {
        get, post
    }

    enum ActionType {
        url,
        order,
        album,
        player,
        rank
    }

    private WebView webView;

    private static final String IntentUrl = "IntentUrl";
    private static final String IntentRequestType = "IntentRequestType";
    private static final String IntentRequestParams = "IntentRequestParams";

    public static final String  QuitScheme = "UTVGOQuit";

    View loadView;

    String currentUrlString;

    public static void navigateUrl(final Context context,
                                   final String url,
                                   final RequestType requestType) {
        RequestType val = RequestType.get;
        if (requestType != null) {
            val = requestType;
        }
        Intent intent = new Intent();
        intent.setClass(context, QWebViewActivity.class);
        intent.putExtra(IntentUrl, url);
        intent.putExtra(IntentRequestType, val.ordinal());
        context.startActivity(intent);
    }

    public static void navigateUrl(final Context context, final String url)
    {
        navigateUrl(context, url, RequestType.get);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        init();
        showBar();

        final Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra(IntentUrl);
            if (TextUtils.isEmpty(url)) {

            } else {
                RequestType requestType = RequestType.get;
                int requestTypeVal = intent.getIntExtra(IntentRequestType, RequestType.get.ordinal());
                try {
                    requestType = RequestType.values()[requestTypeVal];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (requestType == RequestType.post) {

                    String params = intent.getStringExtra(IntentRequestParams);
                    if (params == null) {
                        params = "";
                    }
                    webView.postUrl(url, params.getBytes());
                } else {
                    webView.loadUrl(
                            new URLBuilder()
                                    .appendPath(url)
                                    .appendParam("ca", DiffConfig.getCA(this))
                                    .appendParam("isPurchase",  "true")
                                    .toString());
                }
            }
        }
    }

    public void showBar() {
        if (loadView != null && loadView.getVisibility() == View.GONE) {
            findViewById(R.id.qqmusic_bar).setVisibility(View.VISIBLE);
        }
    }

    public void hideBar() {
        if (loadView != null && loadView.getVisibility() == View.VISIBLE) {
            findViewById(R.id.qqmusic_bar).setVisibility(View.GONE);
        }
    }

    private void init() {
        webView = findViewById(R.id.webv);
        loadView = findViewById(R.id.qqmusic_bar);
        WebSettings webseting = webView.getSettings();
        webseting.setJavaScriptEnabled(true);
        webseting.setDomStorageEnabled(true);
        webseting.setAppCacheMaxSize(1024 * 1024 * 8);//设置缓冲大小，我设的是8M
        String appCacheDir = this.getApplicationContext().getCacheDir().getPath();
        webseting.setAppCachePath(appCacheDir);
        webseting.setAllowFileAccess(true);
        webseting.setAppCacheEnabled(true);
        webseting.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(spaceNeeded * 2);
            }
        });
        webView.addJavascriptInterface(new QJSInterface(this), "app");

        final Context context = this;
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                boolean ret = false;

                if (url.contains("orderResult")) {
                    String resultCode = Uri.parse(url).getQueryParameter("resultCode");
                    if (resultCode.equals("70003") ) {
                        ret = true;
                    }
                }else if (url.toLowerCase().contains(QuitScheme.toLowerCase())){
                    finish();
                    ret = true;
                }
                return ret;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideBar();
            }
        });
    }

    @Override
    protected void onDestroy() {
        //DiffConfig.CurrentProxy.shutDown();
        super.onDestroy();
        webView.clearCache(true);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            /*
            if(timeStamp > 0)
            {
                if(System.currentTimeMillis() < (timeStamp + 3000))
                {
                    finish();
                    return true;
                }
            }

            timeStamp = System.currentTimeMillis();
            webView.loadUrl("javascript:doBackKey()");
            */
            this.finish();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
