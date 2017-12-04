package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class WineLocationDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_location_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        mapFragment.getMapAsync(this);

        TextView locationDetailsNameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView locationDetailsAddressTextView = (TextView) findViewById(R.id.addressTextView);
        TextView locationDetailsPhoneTextView = (TextView) findViewById(R.id.phoneTextView);
        TextView locationDetailsPositionTextView = (TextView) findViewById(R.id.positionTextView);

        Location selectedLocation = getIntent().getExtras().getParcelable("SelectedLocation");

        locationDetailsNameTextView.setText(selectedLocation.getName());
        locationDetailsAddressTextView.setText(selectedLocation.getAddress());
        locationDetailsPhoneTextView.setText(selectedLocation.getPhone());
        locationDetailsPositionTextView.setText(selectedLocation.getLatitude() + ", " + selectedLocation.getLongitude());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // When list launches, asynchronous call to Map fragment
        // this method called after the Map is loaded from Google Play Services
        // At this popint the map is ready
        Location selectedLocation = getIntent().getExtras().getParcelable("SelectedLocation");

        // Store the reference to the Google Map in our member variable
        mMap = googleMap;
        // Custom marker (Big Blue one - myMarker.png
        LatLng myPosition = new LatLng(33.7122274, -117.943139);
        // Add a custom marker at myPosition
        mMap.addMarker(new MarkerOptions()
                .position(myPosition)
                .title("My Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker)));

        // Plot this location using standard marker

        LatLng caffeineLocation = new LatLng(selectedLocation.getLatitude(), selectedLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(caffeineLocation).title(selectedLocation.getName()));

        // Center the cameera over new position
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(caffeineLocation)
                .zoom(15.0f)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

    }
}