package com.tj.day05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editUrl;
    private Button btnGo, btnBack;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUrl = findViewById(R.id.editUrl);
        btnGo = findViewById(R.id.btnGo);
        btnBack = findViewById(R.id.btnBack);
        webView = findViewById(R.id.webView);
        // 손가락으로 줌 기능 추가
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);

        // 해당 사이트가 자바스크립트등이 실행되도록 설정
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        //webView.loadUrl(editUrl.getText().toString());
        // webView 에 해당  url 접속
        webView.loadUrl("http://www.naver.com");

        btnGo.setOnClickListener(goListener);
        editUrl.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // keyCode가 엔터키에 해당하는 값이면 goListener의 onClick실행
                if(keyCode == KeyEvent.KEYCODE_ENTER) {
                    goListener.onClick(btnGo);
                }
                return false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });
    }

    private View.OnClickListener goListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            webView.loadUrl(editUrl.getText().toString().trim());
        }
    };
}
