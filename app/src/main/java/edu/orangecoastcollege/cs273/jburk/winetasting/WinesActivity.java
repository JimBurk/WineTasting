package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static edu.orangecoastcollege.cs273.jburk.winetasting.AddPhotoActivity.getUriFromResource;


/***
 * This activity allows the user to enter the data about the wines they have just blind tasted. The
 * variables for each wine are the vintage, varietal, winery, vineyard (or designation) and price.
 * These will be saved in the database along with the associated tasting and rating.
 */

public class WinesActivity extends AppCompatActivity {

    private long mId = 0;
    private long groupId;

    private DBHelper db;
    private List<Wine> wineList = new ArrayList<>();
    private ArrayList<Wine> allWineList = new ArrayList<Wine>();

    public static final String TAG = WinesActivity.class.getSimpleName();

    private Button saveWine;
    private Button getSaveWine;

    private Wine wine1a;
    private Wine wine1b;
    private Wine wine2a;
    private Wine wine2b;
    private Wine wine3a;
    private Wine wine3b;
    private Wine wine4a;
    private Wine wine4b;
    private Wine wine5a;
    private Wine wine5b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wines);

        Intent intentFromOpen = getIntent();
        groupId = intentFromOpen.getLongExtra("group_id", 0);
        //deleteDatabase(DBHelper.DATABASE_NAME);

        saveWine = (Button) findViewById(R.id.winesContinue);
        //getSaveWine = (Button) findViewById(R.id.getSavedWines);

        allWineList.add(wine1a = new Wine());
        allWineList.add(wine1b = new Wine());
        allWineList.add(wine2a = new Wine());
        allWineList.add(wine2b = new Wine());
        allWineList.add(wine3a = new Wine());
        allWineList.add(wine3b = new Wine());
        allWineList.add(wine4a = new Wine());
        allWineList.add(wine4b = new Wine());
        allWineList.add(wine5a = new Wine());
        allWineList.add(wine5b = new Wine());

        DBHelper db = new DBHelper(this);
    }

    /***
     * This method will go to a new activity to allow the user to take some label shots, then assign
     * one to each wine.
     */

    public void addPhoto(View view) {
        saveWines(view);

        Intent picturesIntent = new Intent(this, AddPhotoActivity.class);
        ArrayList<Wine> wineArrayList = new ArrayList<Wine>();
        wineArrayList.addAll(allWineList);

        picturesIntent.putExtra("FirstWineID", 0);

        picturesIntent.putParcelableArrayListExtra("wineArrayList", wineArrayList);

        startActivity(picturesIntent);

        overridePendingTransition(R.anim.slide_from_left, 0);
    }

    /***
     * This is a dummy method to fill in ten wines during a demonstration. It will be deleted
     * for the released version.
     */
    public void winesEnter(View view) {
        EditText vintageET = findViewById(R.id.wines1AVintageET);
        EditText varietalET = findViewById(R.id.wines1AVarietalET);
        EditText wineryET = findViewById(R.id.wines1AWineryET);
        EditText vineyardET = findViewById(R.id.wines1AVineyardET);
        EditText priceET = findViewById(R.id.wines1APriceET);

        vintageET.setText("2014");
        varietalET.setText("Zinfandel");
        wineryET.setText("Bedrock");
        vineyardET.setText("Limerick Lane");
        priceET.setText("$38.00");

        vintageET = findViewById(R.id.wines1BVintageET);
        varietalET = findViewById(R.id.wines1BVarietalET);
        wineryET = findViewById(R.id.wines1BWineryET);
        vineyardET = findViewById(R.id.wines1BVineyardET);
        priceET = findViewById(R.id.wines1BPriceET);

        vintageET.setText("2013");
        varietalET.setText("Zinfandel");
        wineryET.setText("Ravenswood");
        vineyardET.setText("Bedrock VY");
        priceET.setText("$36.00");

        vintageET = findViewById(R.id.wines2AVintageET);
        varietalET = findViewById(R.id.wines2AVarietalET);
        wineryET = findViewById(R.id.wines2AWineryET);
        vineyardET = findViewById(R.id.wines2AVineyardET);
        priceET = findViewById(R.id.wines2APriceET);

        vintageET.setText("2003");
        varietalET.setText("Cabernet Sauvignon");
        wineryET.setText("Outpost");
        vineyardET.setText("Howell Mountain");
        priceET.setText("$64.95");

        vintageET = findViewById(R.id.wines2BVintageET);
        varietalET = findViewById(R.id.wines2BVarietalET);
        wineryET = findViewById(R.id.wines2BWineryET);
        vineyardET = findViewById(R.id.wines2BVineyardET);
        priceET = findViewById(R.id.wines2BPriceET);

        vintageET.setText("2002");
        varietalET.setText("Cabernet Sauvignon");
        wineryET.setText("Pride Mountain");
        vineyardET.setText("Napa");
        priceET.setText("$61.95");

        vintageET = findViewById(R.id.wines3AVintageET);
        varietalET = findViewById(R.id.wines3AVarietalET);
        wineryET = findViewById(R.id.wines3AWineryET);
        vineyardET = findViewById(R.id.wines3AVineyardET);
        priceET = findViewById(R.id.wines3APriceET);

        vintageET.setText("2004");
        varietalET.setText("Cabernet Franc");
        wineryET.setText("Paradigm");
        vineyardET.setText("Napa");
        priceET.setText("$53.00");

        vintageET = findViewById(R.id.wines3BVintageET);
        varietalET = findViewById(R.id.wines3BVarietalET);
        wineryET = findViewById(R.id.wines3BWineryET);
        vineyardET = findViewById(R.id.wines3BVineyardET);
        priceET = findViewById(R.id.wines3BPriceET);

        vintageET.setText("2011");
        varietalET.setText("Cabernet Franc");
        wineryET.setText("Rockpile");
        vineyardET.setText("Buck Pasture");
        priceET.setText("$40.95");

        vintageET = findViewById(R.id.wines4AVintageET);
        varietalET = findViewById(R.id.wines4AVarietalET);
        wineryET = findViewById(R.id.wines4AWineryET);
        vineyardET = findViewById(R.id.wines4AVineyardET);
        priceET = findViewById(R.id.wines4APriceET);

        vintageET.setText("2013");
        varietalET.setText("Zinfandel");
        wineryET.setText("Ridge");
        vineyardET.setText("Hooker Creek");
        priceET.setText("$32.00");

        vintageET = findViewById(R.id.wines4BVintageET);
        varietalET = findViewById(R.id.wines4BVarietalET);
        wineryET = findViewById(R.id.wines4BWineryET);
        vineyardET = findViewById(R.id.wines4BVineyardET);
        priceET = findViewById(R.id.wines4BPriceET);

        vintageET.setText("2002");
        varietalET.setText("Cabernet Sauvignon");
        wineryET.setText("ZD");
        vineyardET.setText("Napa Reserve");
        priceET.setText("$115.00");

        vintageET = findViewById(R.id.wines5AVintageET);
        varietalET = findViewById(R.id.wines5AVarietalET);
        wineryET = findViewById(R.id.wines5AWineryET);
        vineyardET = findViewById(R.id.wines5AVineyardET);
        priceET = findViewById(R.id.wines5APriceET);

        vintageET.setText("2005");
        varietalET.setText("Cabernet Sauvignon");
        wineryET.setText("Stag's Leap");
        vineyardET.setText("Artemis");
        priceET.setText("$35.00");

        vintageET = findViewById(R.id.wines5BVintageET);
        varietalET = findViewById(R.id.wines5BVarietalET);
        wineryET = findViewById(R.id.wines5BWineryET);
        vineyardET = findViewById(R.id.wines5BVineyardET);
        priceET = findViewById(R.id.wines5BPriceET);

        vintageET.setText("1998");
        varietalET.setText("Cabernet Sauvignnon");
        wineryET.setText("Vine Cliff");
        vineyardET.setText("Oakville");
        priceET.setText("$74.95");
    }

    /***
     * When the user is finished with this activity, the results are put in the database and control
     * passed back to the opening screen.
     */
    public Wine updateWine(Long tasteGroup, EditText vintage, EditText varietal, EditText winery, EditText vineyard, EditText price, Uri imageView){
        Wine newWine = new Wine();

        String vintageString = vintage.getText().toString();
        String varietalString = varietal.getText().toString();
        String wineryString = winery.getText().toString();
        String vineyardString = vineyard.getText().toString();
        String priceString = price.getText().toString();
        priceString = priceString.replace("$", "");

        if (!vintageString.isEmpty() && !varietalString.isEmpty() && !wineryString.isEmpty() && !vineyardString.isEmpty() && !priceString.isEmpty()) {
            int vintageRating = Integer.parseInt(vintageString);
            double priceRating = Double.parseDouble(priceString);

            newWine.setmTasteGroup(tasteGroup);
            newWine.setmVintage(vintageRating);
            newWine.setmVarietal(varietalString);
            newWine.setmVineyard(vineyardString);
            newWine.setmWinery(wineryString);
            newWine.setmPrice(priceRating);
            newWine.setmImageUri(imageView);
        }

        return newWine;
    }

    public void winesContinue(View view) {
        saveWines(view);
        DBHelper db = new DBHelper(this);

        for (Wine w: allWineList)
            db.addWine(w);

        wineList.clear();
        wineList = db.getAllWines();

        Log.i(TAG, "Showing all wines:");
        for (Wine w: wineList)
            Log.i(TAG, w.toString());

        /**
        db.deleteWine(wineList.get(3));
        wineList.clear();
        wineList = db.getAllWines();
        Log.i(TAG, "After deleting wine 4:");
        for (Wine w: wineList)
            Log.i(TAG, w.toString());

        wine1a.setmWinery("Test Upgrade");
        db.updateWine(wine1a);
        wineList.clear();
        wineList = db.getAllWines();
        Log.i(TAG, "After updating wines:");
        for (Wine w: wineList)
            Log.i(TAG, w.toString());

        Log.i(TAG, "Deleting all wines:");
        db.deleteAllWine();
        wineList.clear();
        wineList = db.getAllWines();
        for (Wine w: wineList)
            Log.i(TAG, w.toString());
        */

        Intent continueIntent = new Intent(this, OpenActivity.class);
        startActivity(continueIntent);
        overridePendingTransition(R.anim.fade_in, 0);
    }

    public void saveWines(View view){

        Long tasteGroup = groupId;
        Uri imageView = getUriFromResource(this, R.drawable.wine_bottle);
        EditText vintageET = findViewById(R.id.wines1AVintageET);
        EditText varietalET = findViewById(R.id.wines1AVarietalET);
        EditText wineryET = findViewById(R.id.wines1AWineryET);
        EditText vineyardET = findViewById(R.id.wines1AVineyardET);
        EditText priceET = findViewById(R.id.wines1APriceET);

        wine1a = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(0, wine1a);

        vintageET = findViewById(R.id.wines1BVintageET);
        varietalET = findViewById(R.id.wines1BVarietalET);
        wineryET = findViewById(R.id.wines1BWineryET);
        vineyardET = findViewById(R.id.wines1BVineyardET);
        priceET = findViewById(R.id.wines1BPriceET);

        wine1b = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(1, wine1b);

        vintageET = findViewById(R.id.wines2AVintageET);
        varietalET = findViewById(R.id.wines2AVarietalET);
        wineryET = findViewById(R.id.wines2AWineryET);
        vineyardET = findViewById(R.id.wines2AVineyardET);
        priceET = findViewById(R.id.wines2APriceET);

        wine2a = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(2, wine2a);

        vintageET = findViewById(R.id.wines2BVintageET);
        varietalET = findViewById(R.id.wines2BVarietalET);
        wineryET = findViewById(R.id.wines2BWineryET);
        vineyardET = findViewById(R.id.wines2BVineyardET);
        priceET = findViewById(R.id.wines2BPriceET);

        wine2b = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(3, wine2b);

        vintageET = findViewById(R.id.wines3AVintageET);
        varietalET = findViewById(R.id.wines3AVarietalET);
        wineryET = findViewById(R.id.wines3AWineryET);
        vineyardET = findViewById(R.id.wines3AVineyardET);
        priceET = findViewById(R.id.wines3APriceET);

        wine3a = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(4, wine3a);

        vintageET = findViewById(R.id.wines3BVintageET);
        varietalET = findViewById(R.id.wines3BVarietalET);
        wineryET = findViewById(R.id.wines3BWineryET);
        vineyardET = findViewById(R.id.wines3BVineyardET);
        priceET = findViewById(R.id.wines3BPriceET);

        wine3b = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(5, wine3b);

        vintageET = findViewById(R.id.wines4AVintageET);
        varietalET = findViewById(R.id.wines4AVarietalET);
        wineryET = findViewById(R.id.wines4AWineryET);
        vineyardET = findViewById(R.id.wines4AVineyardET);
        priceET = findViewById(R.id.wines4APriceET);

        wine4a = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(6, wine4a);

        vintageET = findViewById(R.id.wines4BVintageET);
        varietalET = findViewById(R.id.wines4BVarietalET);
        wineryET = findViewById(R.id.wines4BWineryET);
        vineyardET = findViewById(R.id.wines4BVineyardET);
        priceET = findViewById(R.id.wines4BPriceET);

        wine4b = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(7, wine4b);

        vintageET = findViewById(R.id.wines5AVintageET);
        varietalET = findViewById(R.id.wines5AVarietalET);
        wineryET = findViewById(R.id.wines5AWineryET);
        vineyardET = findViewById(R.id.wines5AVineyardET);
        priceET = findViewById(R.id.wines5APriceET);

        wine5a = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(8, wine5a);

        vintageET = findViewById(R.id.wines5BVintageET);
        varietalET = findViewById(R.id.wines5BVarietalET);
        wineryET = findViewById(R.id.wines5BWineryET);
        vineyardET = findViewById(R.id.wines5BVineyardET);
        priceET = findViewById(R.id.wines5BPriceET);

        wine5b = updateWine(tasteGroup, vintageET, varietalET, wineryET, vineyardET, priceET, imageView);
        allWineList.set(9, wine5b);
    }
}
