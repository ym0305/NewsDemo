package com.example.yamin.jsontest.aty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yamin.jsontest.R;


public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private String url;
    private Button backButton;
    private TextView title;
    private ImageButton rightButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        backButton = (Button)findViewById(R.id.left_button);
        backButton.setBackgroundResource(R.drawable.ic_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = (TextView)findViewById(R.id.title);
        title.setText("正文");

        rightButton = (ImageButton)findViewById(R.id.right_button);
        rightButton.setVisibility(View.GONE);

        webView = (WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);


    }
}
