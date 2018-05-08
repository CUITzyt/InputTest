package com.skyworth.inputtest.web;

import com.skyworth.inputtest.R;
import com.skyworth.inputtest.utils.MyLog;
import com.skyworth.inputtest.utils.SkyToast;
import com.skyworth.inputtest.utils.Utils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class WebActivity extends Activity {

    private SdtWebView mWebView;
    private Context mContext;
    private LinearLayout mLinearLayout;
    private String url;
    private WebView mWebView_Own;
    private WebSettings mWebSettings;
    private Button btn1, btn2;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        mContext = this;
        mProgressBar = (ProgressBar) findViewById(R.id.mProgress);

        mWebView = new SdtWebView(mContext, mProgressBar);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 0, 0, 50);
        mLinearLayout = (LinearLayout) findViewById(R.id.web_layout);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

        // mWebView_Own = (WebView) findViewById(R.id.web_view);
        mWebView.setLayoutParams(params);
        mLinearLayout.addView(mWebView);

        btn1.setOnClickListener(mBtnClickListener);
        btn2.setOnClickListener(mBtnClickListener);

        retriveUrl();
        loadWeb(url);
        // loadWebOwn();
    }

    private View.OnClickListener mBtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
            case R.id.button:
                mWebView.callNoParam();
                break;
            case R.id.button2:
                mWebView.callWithParam();
                break;

            default:
                break;
            }
        }
    };

    @SuppressLint({ "SetJavaScriptEnabled", "SdCardPath" })
    private void loadWebOwn() {
        // 设置支持JavaScript等
        mWebSettings = mWebView_Own.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setBuiltInZoomControls(true);
        mWebSettings.setLightTouchEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebView_Own.setHapticFeedbackEnabled(false);
        // mWebView.setInitialScale(0); // 改变这个值可以设定初始大小

        // 重要,用于与页面交互!
        // mWebView.addJavascriptInterface(new Object() {
        // @SuppressWarnings("unused")
        // public void oneClick(final String locX, final String locY)
        // {//此处的参数可传入作为js参数
        // mHandler.post(new Runnable() {
        // public void run() {
        // mWebView.loadUrl("javascript:shows(" + locX + "," + locY + ")");
        // }
        // });
        // }
        // }, "demo");//此名称在页面中被调用,方法如下:
        // <body onClick="window.demo.clickOnAndroid(event.pageX,event.pageY)">

        final String mimeType = "text/html";
        final String encoding = "utf-8";
        final String html = "index.html";// TODO 从本地读取HTML文件
        String pfHtml = "file:///sdcard/pfbar/index.html";

        // mWebView_Own.loadDataWithBaseURL("file:///sdcard/", html, mimeType,
        // encoding, "");
        // mWebView_Own.loadUrl("file:///sdcard/index.html");
        // mWebView_Own.loadUrl("file:///android_asset/index.html");
        mWebView_Own.loadUrl(pfHtml);
    }

    private void retriveUrl() {
        // url = "http://www.baidu.com/";
        url = "http://zhangqisheng.cn/tkms/detail.html?listId=56&deviceId=01230100002881614&cityLcode=430100";
        
        // url = Utils.getAdWebFile(mContext);
        // url = "file:///android_asset/twobuttons.html";
        // url = "file:///sdcard/pfbar/index.html";
        // url = "file:///sdcard/html_video/index.html";
        // url =
        // "http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f22794e792.f20.mp4";//
        // need auth

    }

    private void loadWeb(String mUrl) {
        MyLog.Out(" load url in webactivity ");
        mWebView.loadUrl(mUrl);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    @JavascriptInterface
    public void startFunction() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // SkyToast.makeText(MainActivity.this,"show",3000).show();
                SkyToast.showToast(mContext, " SHOW ", 800, 200);
            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String text) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                new AlertDialog.Builder(mContext).setMessage(text).show();
            }
        });

    }
}
