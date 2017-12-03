package edu.orangecoastcollege.cs273.jburk.winetasting;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

//TODO: (1) Implement the OnMapReadCallback interface for Google Maps
//TODO: First, you'll need to compile GoogleMaps in build.gradle
//TODO: and add permissions and your Google Maps API key in the AndroidManifest.xml
public class WineLocationListActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LocationDBHelper db;
    private List<Location> allLocationsList;
    private ListView locationsListView;
    private LocationListAdapter locationListAdapter;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_location_list);

        deleteDatabase(LocationDBHelper.DATABASE_NAME);
        db = new LocationDBHelper(this);
        db.importLocationsFromCSV("locations.csv");

        allLocationsList = db.getAllwineLocations();
        locationsListView = (ListView) findViewById(R.id.locationsListView);
        locationListAdapter = new LocationListAdapter(this, R.layout.activity_wine_location, allLocationsList);
        locationsListView.setAdapter(locationListAdapter);

        //TODO: (2) Load the support map fragment asynchronously
        // Instruct android to load Google Map into our fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.wineMapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // When list launches, asynchronous call to Map fragment
        // this method called after the Map is loaded from Google Play Services
        // At this popint the map is ready

        // Store the reference to the Google Map in our member variable
        mMap = googleMap;
        // Custom marker (Big Blue one - myMarker.png
        LatLng myPosition = new LatLng(33.712274, -117.943139);
        // Add a custom marker at myPosition
        mMap.addMarker(new MarkerOptions()
                .position(myPosition)
                .title("My Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker)));
        //. center the cameera over my position, vice 0/0
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(myPosition)
                .zoom(15.0f)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

        // Plot each location from the list using standard marker
        for (Location location : allLocationsList) {
            LatLng wineLocation = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(wineLocation).title(location.getName()));
        }
    }

    // (3) Implement the onMapReady method, which will add a special "marker" for our current location,
    // which is 33.671028, -117.911305  (MBCC 139)
    // Then add normal markers for all the wine locations from the allLocationsList.
    // Set the zoom level of the map to 15.0f

    public void viewLocationDetails(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            Location selectedLocation = (Location) selectedLayout.getTag();
            Intent detailsIntent = new Intent(this, WineLocationDetailActivity.class);

            detailsIntent.putExtra("SelectedLocation", selectedLocation);
            startActivity(detailsIntent);
        }
    }

    // TODO: (4) Create a viewLocationsDetails(View v) method to create a new Intent to the
    // TODO: wineDetailsActivity class, sending it the selectedLocation the user picked from the locationsListView
}