package com.smartrecipe.life_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnBecomeDonor, btnFindCamp, btnFindBlood, btnLoginTop, btnGeneralCheckup;
    TextView tvAwareness;
    FirebaseAuth mAuth;
    WebView webViewChatbot;

    // Chatbase info
    private static final String CHATBOT_ID = "VKIpbrqIDnVBPGMrG9cta";
    private static final String CHATBASE_URL = "https://www.chatbase.co/chatbot-iframe/" + CHATBOT_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind UI elements
        btnBecomeDonor = findViewById(R.id.btnBecomeDonor);
        btnFindCamp = findViewById(R.id.btnFindCamp);
        btnFindBlood = findViewById(R.id.btnFindBlood);
        btnLoginTop = findViewById(R.id.btnLoginTop);
        btnGeneralCheckup = findViewById(R.id.btnGeneralCheckup);
        tvAwareness = findViewById(R.id.tvAwareness);
        webViewChatbot = findViewById(R.id.chatbotWebView);

        mAuth = FirebaseAuth.getInstance();

        setAwarenessContent();
        updateTopRightButton();
        setupWebView();

        // Button click listeners
        btnBecomeDonor.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DonorActivity.class)));
        btnFindCamp.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MapsActivity.class)));
        btnFindBlood.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FindBloodActivity.class)));
        btnGeneralCheckup.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GeneralCheckupActivity.class)));
    }

    private void setupWebView() {
        webViewChatbot.getSettings().setJavaScriptEnabled(true);
        webViewChatbot.getSettings().setDomStorageEnabled(true);
        webViewChatbot.setWebViewClient(new WebViewClient());
        webViewChatbot.loadUrl(CHATBASE_URL);
    }

    private void updateTopRightButton() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String email = currentUser.getEmail();
            String username = (email != null && email.contains("@")) ? email.split("@")[0] : email;
            btnLoginTop.setText(username);
            btnLoginTop.setOnClickListener(this::showUserMenu);
        } else {
            btnLoginTop.setText("Login");
            btnLoginTop.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
        }
    }

    private void showUserMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenu().add("Logout");

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getTitle().equals("Logout")) {
                mAuth.signOut();
                updateTopRightButton();
            }
            return true;
        });

        popupMenu.show();
    }

    private void setAwarenessContent() {
        String awarenessFacts =
                "🔴 What is it?\n" +
                        "Blood collected straight from the donor into a blood bag and mixed with an anticoagulant is called whole blood. " +
                        "This collected whole blood is then centrifuged and red cell, platelets, and plasma are separated.\n\n" +
                        "🔴 Who can donate?\n" +
                        "You need to be 18-65 years old, weigh 45kg or more, and be fit and healthy.\n\n" +
                        "🔴 Used For?\n" +
                        "Correction of severe anemia, blood loss in childbirth, surgery, or trauma.\n\n" +
                        "🔴 Lasts For?\n" +
                        "Red cells can be stored for 42 days at 2-6°C.\n\n" +
                        "🔴 How long does it take to donate?\n" +
                        "15-30 minutes including pre-donation check-up.\n\n" +
                        "🔴 How often can I donate?\n" +
                        "Male donors: every 90 days. Female donors: every 120 days.";

        tvAwareness.setText(awarenessFacts);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTopRightButton();
    }
}
