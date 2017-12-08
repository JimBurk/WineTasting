package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.location.Location;
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
    private WineLocation mSelectedWineLocation;
    private Location mMyLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_location_detail);

        TextView locationDetailsNameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView locationDetailsAddressTextView = (TextView) findViewById(R.id.addressTextView);
        TextView locationDetailsPhoneTextView = (TextView) findViewById(R.id.phoneTextView);
        TextView locationDetailsPositionTextView = (TextView) findViewById(R.id.positionTextView);

        mSelectedWineLocation = getIntent().getExtras().getParcelable("SelectedLocation");

        locationDetailsNameTextView.setText(mSelectedWineLocation.getName());
        locationDetailsAddressTextView.setText(mSelectedWineLocation.getAddress());
        locationDetailsPhoneTextView.setText(mSelectedWineLocation.getPhone());
        locationDetailsPositionTextView.setText(mSelectedWineLocation.getFormattedLatLng());

        mSelectedWineLocation = getIntent().getExtras().getParcelable("SelectedLocation");
        mMyLocation = getIntent().getExtras().getParcelable("MyLocation");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng coordinate = new LatLng(mSelectedWineLocation.getLatitude(), mSelectedWineLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(coordinate).title(mSelectedWineLocation.getName()));

        LatLng myCoordinate = new LatLng(mMyLocation.getLatitude(), mMyLocation.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(myCoordinate)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker)));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(coordinate).zoom(15.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }
}