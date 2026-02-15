package com.smartrecipe.life_force;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // ✅ Default location: Coimbatore
    private final LatLng COIMBATORE = new LatLng(11.0168, 76.9558);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        // ✅ Always show Coimbatore by default
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(COIMBATORE, 12));

        // ✅ Add fixed hospital & blood camp markers
        addFixedMarkers();
    }

    private void addFixedMarkers() {
        // Major Hospitals in Coimbatore
        Marker cmch = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(11.0175, 76.9580))
                .title("Coimbatore Medical College Hospital (CMCH)"));
        if (cmch != null) cmch.showInfoWindow();

        Marker psg = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(11.0186, 76.9556))
                .title("PSG Hospitals"));
        if (psg != null) psg.showInfoWindow();

        Marker ganga = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(11.0146, 76.9772))
                .title("Ganga Hospital"));
        if (ganga != null) ganga.showInfoWindow();

        Marker kg = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(11.0284, 76.9387))
                .title("KG Hospital"));
        if (kg != null) kg.showInfoWindow();

        Marker kmch = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(11.0123, 76.9408))
                .title("Kovai Medical Center & Hospital (KMCH)"));
        if (kmch != null) kmch.showInfoWindow();

        // Example Blood Donation Camps / Red Cross
        Marker redCross = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(11.0135, 76.9492))
                .title("Indian Red Cross Society - Blood Bank"));
        if (redCross != null) redCross.showInfoWindow();

        Marker lions = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(11.0232, 76.9419))
                .title("Lions Blood Bank, Coimbatore"));
        if (lions != null) lions.showInfoWindow();

        Marker rotary = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(11.0168, 76.9558))
                .title("Rotary Blood Bank, Coimbatore"));
        if (rotary != null) rotary.showInfoWindow();
    }
}
