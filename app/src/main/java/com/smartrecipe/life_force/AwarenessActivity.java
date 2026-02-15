package com.smartrecipe.life_force;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ScrollView;

public class AwarenessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);

        TextView tvAwareness = findViewById(R.id.tvAwareness);

        String awarenessContent = "💉 Blood Donation Awareness 💉\n\n" +

                "1️⃣ Benefits of Blood Donation:\n" +
                "- Saves lives of patients in need.\n" +
                "- Reduces risk of heart disease.\n" +
                "- Helps in production of new blood cells.\n\n" +

                "2️⃣ Myths vs Facts:\n" +
                "- Myth: Blood donation is harmful → Fact: It's safe for healthy adults.\n" +
                "- Myth: You will feel weak after donation → Fact: Most donors feel fine.\n" +
                "- Myth: Only rare blood groups are useful → Fact: All blood groups save lives.\n\n" +

                "3️⃣ Eligibility Criteria:\n" +
                "- Age: 18–65 years\n" +
                "- Weight: ≥ 50 kg\n" +
                "- Good general health\n\n" +

                "4️⃣ Tips for Donors:\n" +
                "- Eat a healthy meal before donating.\n" +
                "- Drink plenty of water.\n" +
                "- Avoid alcohol for 24 hours before donation.\n\n" +

                "5️⃣ Donation Frequency:\n" +
                "- Whole blood: Every 3 months\n" +
                "- Platelets: Every 2 weeks\n" +
                "- Plasma: Every 2–4 weeks\n\n" +

                "6️⃣ Emergency Alerts & Blood Drives:\n" +
                "- Check the app for nearby blood donation camps.\n" +
                "- Register to get notifications when your blood type is needed.\n\n" +

                "🙏 Your small contribution can save many lives. Be a hero today!";

        tvAwareness.setText(awarenessContent);
    }
}
