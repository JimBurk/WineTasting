package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
    }

    public void colorFragment1A() { Log.i("Rating Activity", "colorFragment1A") }
    public void aromaFragment1A() { Log.i("Rating Activity", "aromaFragment1A") }
    public void bodyFragment1A() { Log.i("Rating Activity", "bodyFragment1A") }
    public void tasteFragment1A() { Log.i("Rating Activity", "tasteFragment1A") }
    public void finishFragment1A() { Log.i("Rating Activity", "finishFragment1A") }
}
