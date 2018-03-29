package com.example.android.news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Webviewnews extends AppCompatActivity {
    ProgressBar progressBar;
    WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        Intent intent = getIntent();

        String url = intent.getStringExtra("url");

         myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new MyBrowser());
        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress > 20) {
                    newProgress = newProgress * 3;
                    progressBar.setProgress(newProgress);
                } else if (newProgress <= 20) {
                    newProgress = newProgress * 2;
                    progressBar.setProgress(newProgress);
                }
                if (newProgress >= 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        myWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        myWebView.loadUrl(url);
    }

    class MyBrowser extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }

}
