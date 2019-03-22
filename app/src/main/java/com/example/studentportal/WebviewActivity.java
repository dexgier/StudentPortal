package com.example.studentportal;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView webView = findViewById(R.id.webView);
        Portal portal = getIntent().getParcelableExtra(MainActivity.WEBPAGE_TEXT);
        webView.loadUrl(portal.getUrl());
        webView.setWebViewClient(new WebViewController());
    }

    public class WebViewController extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
