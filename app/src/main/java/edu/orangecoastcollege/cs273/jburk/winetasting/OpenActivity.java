package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/***
 * This is the Opening screen for the App. The user has the choices of adding a new tasting, reviewing
 * a previous tasting, reviewing the wines, locating some wine points of interest on a Google map or
 * exiting the app.
 */

public class OpenActivity extends AppCompatActivity {

    public static final String TAG = OpenActivity.class.getSimpleName();

    private List <OfferingRatingTasting> offeringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
    }

    public void newTasting(View view){
        Intent addTastingIntent = new Intent(this, AddTasting.class);
        startActivity(addTastingIntent);
        overridePendingTransition(R.anim.fade_in, 0);
    }

    public void reviewTastings(View view){
        Intent reviewTastingIntent = new Intent(this, ReviewTastingsActivity.class);
        startActivity(reviewTastingIntent);
        overridePendingTransition(R.anim.fade_in, 0);
    }

    public void reviewWines(View view){
        Intent reviewWinesIntent = new Intent(this, ReviewWinesActivity.class);
        startActivity(reviewWinesIntent);
        overridePendingTransition(R.anim.fade_in, 0);
    }

    public void locateWines(View view){
        Intent locateWinesIntent = new Intent(this, WineLocationListActivity.class);
        startActivity(locateWinesIntent);
        overridePendingTransition(R.anim.fade_in, 0);
    }

    public void quitApp(View view) {
        deleteDatabase(DBHelper.DATABASE_NAME);
        Toast.makeText(this, "Deleting Database", Toast.LENGTH_LONG).show();

        //this.finishAffinity();
    }
}
