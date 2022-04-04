package com.aryan.stumps11.Wallet.webViewPage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.aryan.stumps11.R;

public class AddMoneyWebViewActivity extends AppCompatActivity {

    private WebView webViewAddMoney;
    String url;
    private ImageView imgBackBtn;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money_web_view);
        imgBackBtn=findViewById(R.id.addMoneyBackBtn);
        webViewAddMoney=findViewById(R.id.add_money_web_view);
        webViewAddMoney.getSettings().setJavaScriptEnabled(true);
        webViewAddMoney.getSettings().setLoadWithOverviewMode(true);
        webViewAddMoney.getSettings().setUseWideViewPort(true);
        startWebView();

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void startWebView() {
        url=getIntent().getExtras().getString("payment");


        webViewAddMoney.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            @Override
            public void onLoadResource(WebView view, String url) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //progDailog.dismiss();


            }

        });

//       Load url in webView
        webViewAddMoney.loadUrl(url);

    }

}