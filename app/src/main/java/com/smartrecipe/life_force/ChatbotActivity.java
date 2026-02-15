package com.smartrecipe.life_force;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ChatbotActivity extends AppCompatActivity {

    // Your Chatbase info
    private static final String CHATBOT_ID = "VKIpbrqIDnVBPGMrG9cta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        WebView webView = findViewById(R.id.chatbotWebView);

        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        // Correct Chatbase iframe URL
        String url = "https://www.chatbase.co/chatbot/" + CHATBOT_ID;
        webView.loadUrl(url);
    }
}
