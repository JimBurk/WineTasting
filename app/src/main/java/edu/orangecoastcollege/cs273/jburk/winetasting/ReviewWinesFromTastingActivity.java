package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ReviewWinesFromTastingActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Wine> allWinesList = new ArrayList<>();
    private List<Wine> filteredWinesList = new ArrayList<>();
    private WinesListAdapter winesListAdapter;
    private ListView winesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_wines_from_tasting);


        db = new DBHelper(this);
        allWinesList = db.getAllWines();


        Tasting selectedTasting = getIntent().getExtras().getParcelable("SelectedTasting");


        for (Wine wine : allWinesList)
            if ((wine.getmTasteGroup()) == (selectedTasting.getId()))
                filteredWinesList.add(wine);


        winesListAdapter = new WinesListAdapter(this, R.layout.wines_list_item, filteredWinesList);
        winesListView = (ListView) findViewById(R.id.winesListView);
        winesListView.setAdapter(winesListAdapter);
    }

    public void viewWineDetails(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            Wine selectedWine = (Wine) selectedLayout.getTag();
            Log.i("Wine Tasting Details", selectedWine.toString());
            Intent detailsIntent = new Intent(this, WineDetailsActivity.class);

            detailsIntent.putExtra("SelectedWine", selectedWine);
            startActivity(detailsIntent);
        }
    }
}