package com.techeasylife.resultbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class Ssc extends AppCompatActivity {
    WebView Ssc_web;
    private Object NetworkInfo;
    LinearLayout layNonet;
    ProgressBar progressBar;
    LinearLayout layRoot;
    String USER_AGENT_ = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssc);


        layRoot = findViewById(R.id.layRoot);
        layNonet = findViewById(R.id.layNonet);


        webView = new WebView(Ssc.this);
        webView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        webView.setFitsSystemWindows(false); // your preferences
        webView.setVerticalScrollBarEnabled(false); // your preferences
        webView.setPadding(15,15,15,15); // your preferences
        layRoot.addView(webView);

        // Enabling some setting so that browser can work properly
        webView.getSettings().setUserAgentString(USER_AGENT_);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(new HelloWebViewClient());
        webView.setWebChromeClient(new ChromeClient());
        webView.getSettings().setDomStorageEnabled(true);


        Ssc_web = findViewById(R.id.Ssc_web);
        Ssc_web.getSettings().setJavaScriptEnabled(true);
        Ssc_web.loadUrl("https://eboardresults.com/v2/home");

        if(!isNetworkAvailable(Ssc.this)){
            webView.setVisibility(View.GONE);
            layNonet.setVisibility(View.VISIBLE);
        }else{
            webView.setVisibility(View.VISIBLE);
            layNonet.setVisibility(View.GONE);
        }




    }


    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }





    }