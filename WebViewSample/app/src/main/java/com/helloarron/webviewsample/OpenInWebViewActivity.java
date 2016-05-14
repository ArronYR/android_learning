package com.helloarron.webviewsample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

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
            webView.loadUrl("file:///android_asset/example.html");
            // webView.loadUrl(url);
        }
    }
}
