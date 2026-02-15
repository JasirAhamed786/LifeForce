package com.smartrecipe.life_force;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GeneralCheckupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_checkup);

        // Optional: Set a title dynamically
        TextView tvTitle = findViewById(R.id.tvGeneralCheckupTitle);
        tvTitle.setText("General Checkup Information");
    }
}
