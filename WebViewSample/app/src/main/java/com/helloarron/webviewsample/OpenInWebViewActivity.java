package com.helloarron.webviewsample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by arron on 16/5/13.
 */
public class OpenInWebViewActivity extends AppCompatActivity {

    String url = "http://blog.helloarron.com";
    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_in_webview);

        webView = (WebView) findViewById(R.id.wv_webView);
        if (webView != null) {
            // WebView加载本地资源
            // webView.loadUrl("file:///android_asset/example.html");
            // WebView加载网络资源
            webView.loadUrl(url);

            // 覆盖WebView默认通过系统浏览器或第三方浏览器打开链接
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    /**
                     * 返回值为true时在WebView中打开
                     * 返回值为false时在系统浏览器或第三方浏览器中打开
                     */
                    webView.loadUrl(url);
                    return true;
                }
                /**
                 * WebViewClient帮助WebView处理一些页面控制和请求通知
                 * 其中有很多方法，如：
                 *     onReceivedClientCertRequest()
                 *     onPageStarted()
                 *     onPageFinished()
                 *     ......
                 */
            });

            // 启用JavaScript
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            // WebView加载网页优先使用缓存加载
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

            // 检测网页加载进度
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    // newProgress为0-100之间的整数
                    if (newProgress == 100) {
                        // 网页加载完毕，关闭ProgressDialog
                        closeProgressDialog();
                    } else {
                        // 网页正在加载，打开ProgressDialog
                        openProgressDialog(newProgress);
                    }
                }
            });
        }
    }

    /**
     * 打开ProgressDialog
     *
     * @param newProgress
     */
    private void openProgressDialog(int newProgress) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(OpenInWebViewActivity.this);
            progressDialog.setTitle("正在加载...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(newProgress);
            progressDialog.show();
        } else {
            progressDialog.setProgress(newProgress);
        }
    }

    /**
     * 关闭ProgressDialog
     */
    private void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * 改写物理按键 - 返回的逻辑
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            // 返回上一个页面
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
