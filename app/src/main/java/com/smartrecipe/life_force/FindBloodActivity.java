package com.smartrecipe.life_force;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Color;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FindBloodActivity extends AppCompatActivity {

    EditText etBloodGroupSearch, etLocationSearch;
    Button btnSearchBlood, btnBack;
    ListView lvDonors;

    DatabaseReference donorRef;
    ArrayList<String> donorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_blood);

        etBloodGroupSearch = findViewById(R.id.etBloodGroupSearch);
        etLocationSearch = findViewById(R.id.etLocationSearch);
        btnSearchBlood = findViewById(R.id.btnSearchBlood);
        btnBack = findViewById(R.id.btnBack);
        lvDonors = findViewById(R.id.lvDonors);

        donorRef = FirebaseDatabase.getInstance().getReference("Donors");
        donorList = new ArrayList<>();

        btnSearchBlood.setOnClickListener(v -> searchDonors());
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(FindBloodActivity.this, MainActivity.class));
            finish();
        });
    }

    private void searchDonors() {
        String bloodInput = etBloodGroupSearch.getText().toString().trim().toLowerCase();
        String locationInput = etLocationSearch.getText().toString().trim().toLowerCase();

        donorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                donorList.clear();

                for (DataSnapshot donorSnap : snapshot.getChildren()) {
                    DonorModel donor = donorSnap.getValue(DonorModel.class);
                    if (donor != null) {
                        String donorBlood = donor.getBloodGroup().toLowerCase();
                        String donorLocation = donor.getLocation().toLowerCase();

                        boolean bloodMatch = bloodInput.isEmpty() || donorBlood.contains(bloodInput);
                        boolean locationMatch = locationInput.isEmpty() || donorLocation.contains(locationInput);

                        if (bloodMatch && locationMatch) {
                            donorList.add(donor.getName() + " | " + donor.getBloodGroup() + " | " + donor.getLocation() +
                                    " | " + donor.getEmail() + " | " + donor.getPhone());
                        }
                    }
                }

                if (donorList.isEmpty()) {
                    Toast.makeText(FindBloodActivity.this, "No donors found", Toast.LENGTH_SHORT).show();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        FindBloodActivity.this,
                        android.R.layout.simple_list_item_1,
                        donorList
                ) {
                    @Override
                    public View getView(int position, View convertView, android.view.ViewGroup parent) {
                        android.widget.TextView tv = (android.widget.TextView) super.getView(position, convertView, parent);
                        tv.setTextColor(Color.parseColor("#8E0000")); // Dark red color
                        return tv;
                    }
                };

                lvDonors.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(FindBloodActivity.this, "Error fetching donors", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
