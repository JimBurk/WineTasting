package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class ReviewTastingsActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Tasting> tasteList;
    private TastingListAdapter tasteListAdapter;
    private ListView tasteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_tastings);

        //this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);


        tasteList = db.getAllTastings();
        tasteListAdapter = new TastingListAdapter(this, R.layout.tasting_list_item, tasteList);
        tasteListView = (ListView) findViewById(R.id.tasteListView);
        tasteListView.setAdapter(tasteListAdapter);
    }

    public void viewWineList(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            Tasting selectedTasting= (Tasting) selectedLayout.getTag();
            Log.i("Tasting Events", selectedTasting.toString());
            Intent detailsIntent = new Intent(this, ReviewWinesFromTastingActivity.class);

            detailsIntent.putExtra("SelectedTasting", selectedTasting);
            startActivity(detailsIntent);
        }
    }
}
