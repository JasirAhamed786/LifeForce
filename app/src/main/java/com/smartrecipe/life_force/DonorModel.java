package com.smartrecipe.life_force;

public class DonorModel {
    String name, bloodGroup, location, email, phone;

    public DonorModel() {
        // Empty constructor needed for Firebase
    }

    public DonorModel(String name, String bloodGroup, String location, String email, String phone) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.location = location;
        this.email = email;
        this.phone = phone;
    }

    // Getters
    public String getName() { return name; }
    public String getBloodGroup() { return bloodGroup; }
    public String getLocation() { return location; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
