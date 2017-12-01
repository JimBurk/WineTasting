package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;
import java.util.EmptyStackException;

public class AddTasting extends AppCompatActivity {

    private EditText tastingNameET;
    private EditText tastingDateET;
    private EditText tastingLocationET;

    private String mTastingName;
    private String mTastingDate;
    private String mTastingLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasting);
    }

    public void newRating(View view) {
        tastingNameET = (EditText) findViewById(R.id.tasting_name);
        tastingDateET = (EditText) findViewById(R.id.tasting_name);
        tastingLocationET = (EditText) findViewById(R.id.tasting_name);


        mTastingName = tastingNameET.getText().toString();
        mTastingDate = tastingDateET.getText().toString();
        mTastingLocation = tastingDateET.getText().toString();

        Intent newRatingIntent = new Intent(this, RatingActivity.class);
        newRatingIntent.putExtra("TastingName", mTastingName);
        newRatingIntent.putExtra("TastingDate", mTastingDate);
        newRatingIntent.putExtra("TastingLocation", mTastingLocation);
        startActivity(newRatingIntent);
        overridePendingTransition(R.anim.fade_in, 0);
    }
}
