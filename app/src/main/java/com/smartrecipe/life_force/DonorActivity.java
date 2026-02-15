package com.smartrecipe.life_force;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorActivity extends AppCompatActivity {

    EditText etName, etBloodGroup, etLocation, etEmail, etPhone;
    Button btnRegister, btnBack;
    DatabaseReference donorRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        // Bind UI elements
        etName = findViewById(R.id.etName);
        etBloodGroup = findViewById(R.id.etBloodGroup);
        etLocation = findViewById(R.id.etLocation);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);

        donorRef = FirebaseDatabase.getInstance().getReference("Donors");

        // Register donor
        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String blood = etBloodGroup.getText().toString();
            String location = etLocation.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();

            if (name.isEmpty() || blood.isEmpty() || location.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(DonorActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                String donorId = donorRef.push().getKey();
                DonorModel donor = new DonorModel(name, blood, location, email, phone);
                donorRef.child(donorId).setValue(donor);
                Toast.makeText(DonorActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
            }
        });

        // Back button to MainActivity
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(DonorActivity.this, MainActivity.class));
            finish();
        });
    }
}
