package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/***
 * This is the screen for adding a new tasting. There are edit texts for three string entries, a name,
 * a date and a location. This data is passed to the next screen for inclusion in the data base.
 */

public class AddTasting extends AppCompatActivity {

    private DBHelper db;

    public static final String TAG = AddTasting.class.getSimpleName();

    private EditText mTastingName;
    private EditText mTastingDate;
    private EditText mTastingLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasting);

        //deleteDatabase(DBHelper.DATABASE_NAME);

        mTastingName = (EditText) findViewById(R.id.tasting_name);
        mTastingDate = (EditText) findViewById(R.id.tasting_date);
        mTastingLocation = (EditText) findViewById(R.id.tasting_location);
    }

    public void testLog(){
        db = new DBHelper(this);

        List<Tasting> tastingList = new ArrayList<>();
        tastingList = db.getAllTastings();

        Log.i(TAG, "Showing all tastings");
        for (Tasting t: tastingList)
            Log.i(TAG, t.toString());

        db.deleteAllTasting();
        tastingList.clear();
        tastingList = db.getAllTastings();
        Log.i(TAG, "After deleting the list.");
        for (Tasting t: tastingList)
            Log.i(TAG, t.toString());
    }

    /***
     * This method obtains data from the ETs and puts it into an intent for passage to the rating activity.
     */

    public void newRating(View view) {
        String name = mTastingName.getText().toString();
        String date = mTastingDate.getText().toString();
        String location = mTastingLocation.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(date) || TextUtils.isEmpty(location))
            Toast.makeText(this, "Please completely fill out the 'name', 'date', and 'location.'",
                    Toast.LENGTH_SHORT).show();
        else{
            Tasting newTasting = new Tasting(name, date, location);
            db = new DBHelper(this);

            db.addTasting(newTasting);

            mTastingName.setText("");
            mTastingDate.setText("");
            mTastingLocation.setText("");

        testLog();

        Intent newRatingIntent = new Intent(this, RatingActivity.class);
        startActivity(newRatingIntent);
        overridePendingTransition(R.anim.fade_in, 0);
        }
    }
}
