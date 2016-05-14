package com.helloarron.webviewsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOpenInBrowser, btnOpenInWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenInBrowser = (Button) findViewById(R.id.open_in_browser_btn);
        btnOpenInWebView = (Button) findViewById(R.id.open_in_webview_btn);

        btnOpenInBrowser.setOnClickListener(this);
        btnOpenInWebView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_in_browser_btn:
                startActivity(new Intent(this, OpenInBrowserActivity.class));
                break;
            case R.id.open_in_webview_btn:
                startActivity(new Intent(this, OpenInWebViewActivity.class));
                break;
            default:
                break;
        }
    }
}
