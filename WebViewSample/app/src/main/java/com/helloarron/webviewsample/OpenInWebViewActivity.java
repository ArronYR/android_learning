package com.helloarron.webviewsample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.ClientCertRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by arron on 16/5/13.
 */
public class OpenInWebViewActivity extends AppCompatActivity {

    String url = "http://blog.helloarron.com";
    private WebView webView;

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
            webView.setWebViewClient(new WebViewClient(){
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
                 * WebViewClient帮助Webview处理一些页面控制和请求通知
                 * 其中有很多方法，如：
                 *     onReceivedClientCertRequest()
                 *     onPageStarted()
                 *     onPageFinished()
                 *     ......
                 */
            });
        }
    }
}
