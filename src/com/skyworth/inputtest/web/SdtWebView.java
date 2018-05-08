package com.skyworth.inputtest.web;

import java.io.File;

import com.skyworth.inputtest.utils.MyLog;
import com.skyworth.inputtest.utils.NetStatusUtil;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.webkit.WebSettings.LayoutAlgorithm;

public class SdtWebView extends WebView {

    private Context mContext;
    private ProgressBar mProgressBar;

    public SdtWebView(Context context, ProgressBar progressBar) {
        super(context);
        // TODO Auto-generated constructor stub
        mContext = context;
        mProgressBar = progressBar;
        MyLog.Out(" SdtWebview init in one param!! ");
        init();
    }

    public SdtWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        MyLog.Out(" SdtWebview init in two param!! ");
        init();
    }

    public SdtWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        MyLog.Out(" SdtWebview init in three param!! ");
        mContext = context;
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {

        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLightTouchEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("GBK");
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        // if (!NetUtil.checkNet(MainActivity.this)) {
        // mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // webSettings.setHapticFeedbackEnabled(false);
        setScrollBarStyle(0);
        setWebViewClient(webViewClient);

        // add js interface
        addJavascriptInterface(mContext, "android");
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init_1() {
        WebSettings webSettings = getSettings();
        // mWebSettings.setSupportZoom(true);
        // mWebSettings.setLoadWithOverviewMode(true);
        // mWebSettings.setUseWideViewPort(true);
        // mWebSettings.setDefaultTextEncodingName("utf-8");
        // mWebSettings.setLoadsImagesAutomatically(true);
        //
        // // 调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
        // mWebSettings.setJavaScriptEnabled(true);
        // mWebSettings.setSupportMultipleWindows(true);
        // // 缓存数据
        //// saveData(mWebSettings);
        // newWin(mWebSettings);
        // setWebChromeClient(webChromeClient);
        // setWebViewClient(webViewClient);

        // WebSettings webSettings = webView.getSettings();
        // java script
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);

        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.supportMultipleWindows();
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        webSettings.setAllowFileAccess(true);
        webSettings.setNeedInitialFocus(true);

        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // webSettings.setLoadWithOverviewMode(true);
        // webSettings.setLoadsImagesAutomatically(true);

        // webView.setHorizontalScrollBarEnabled(false);//水平不显示
        // webView.setVerticalScrollBarEnabled(false); //垂直不显示
        //
        //// webView.setWebViewClient(new MyWebViewClient());
        //// webView.setWebChromeClient(new MyWebChromeClient());
        // webView.setBackgroundColor(0);
        // webView.addJavascriptInterface(this, JS_FUNTION_DEFINE);

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init_2() {
        WebSettings mWebSettings = getSettings();
        mWebSettings.setSupportZoom(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setLoadsImagesAutomatically(true);

        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportMultipleWindows(true);
        // saveData(mWebSettings);
        newWin(mWebSettings);
        setWebChromeClient(webChromeClient);
        setWebViewClient(webViewClient);
        mWebSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

        mWebSettings.supportMultipleWindows();
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setNeedInitialFocus(true);
        mWebSettings.setBuiltInZoomControls(true);

    }

    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            MyLog.Out(" Url is  " + url);
            MyLog.Out(" in SdtWebView Url is  " + url);
            // Log.d("Url:", url);
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            MyLog.Out(" onPageStarted  ===1");
        };

        public void onPageFinished(WebView view, String url) {
            MyLog.Out(" onPageFinished  ===2");
            view.loadUrl("javascript:try{autoplay();}catch(e){}");//
        };

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
        
        

    };

    // private void saveData(WebSettings mWebSettings) {
    //
    // if (NetStatusUtil.isConnected(mContext)) {
    // mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//
    // 根据cache-control决定是否从网络上取数据。
    // } else {
    // mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//
    // 没网，则从本地获取，即离线加载
    // }
    // File cacheDir = mContext.getCacheDir();
    // if (cacheDir != null) {
    // String appCachePath = cacheDir.getAbsolutePath();
    // mWebSettings.setDomStorageEnabled(true);
    // mWebSettings.setDatabaseEnabled(true);
    // mWebSettings.setAppCacheEnabled(true);
    // mWebSettings.setAppCachePath(appCachePath);
    // }
    // }

    private void newWin(WebSettings mWebSettings) {
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(final String origin,
                final GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);// 注意个函数，第二个参数就是是否同意定位权限，第三个是是否希望内核记住
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(view);
            resultMsg.sendToTarget();
            return true;
        }

        public void onShowCustomView(android.view.View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        };

        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                if (View.GONE == mProgressBar.getVisibility()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        };
    };

    public void callNoParam() {
        loadUrl("javascript:javacalljs()");
    }

    public void callWithParam() {
        loadUrl("javascript:javacalljswith(" + "'http://blog.csdn.net/Leejizhou'" + ")");
    }
}
