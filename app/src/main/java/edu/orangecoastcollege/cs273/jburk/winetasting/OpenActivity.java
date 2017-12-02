package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class OpenActivity extends AppCompatActivity {

    /*
    private DBHelper db;
    private List<Wine> winesList;
    private ListView winesListView;
    private WinePreviewListAdapter wineAdapter;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        /*db = new DBHelper(this);

        db.addWine(new Wine("Zinfandel", 2013, "Newport Beach Vineyards and Winery", "Carlisle", 20.3 ));
        db.addWine(new Wine("Merlot", 2016, "Laguna Canyon Winery", "Ridgeback", 40.7 ));
        db.addWine(new Wine("Pinot Noir", 2011, "Miramonte Winery", "Carlisle", 38.87 ));

        winesList = db.getAllWines();
        wineAdapter = new WinePreviewListAdapter(this, R.layout.wine_preview_list_item, winesList);
        winesListView = (ListView) findViewById(R.id.wineListView);
        winesListView.setAdapter(wineAdapter);
        */
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

    public void quitApp(View view) {
        this.finishAffinity();
    }
}
