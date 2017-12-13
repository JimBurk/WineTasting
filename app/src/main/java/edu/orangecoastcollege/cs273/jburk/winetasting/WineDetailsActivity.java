package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class WineDetailsActivity extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat(".#");

    private DBHelper db;
    private List<Wine> allWinesList = new ArrayList<>();

    private TextView ratingTextView ;
    private TextView tastingTextView;
    private TextView wineTextView;
    private ImageView wineImageView;

    private Tasting tasting;
    private Rating rating;
    private Wine selectedWine;

    List<OfferingWineRating> allWineRatingList;

    public static final String TAG = WineDetailsActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_details);

        Log.i(TAG, "Wine Details Opening.");

        selectedWine = new Wine();
        selectedWine = getIntent().getExtras().getParcelable("SelectedWine");

        db = new DBHelper(this);

        ratingTextView = (TextView) findViewById(R.id.ratingsTextView);
        tastingTextView = (TextView) findViewById(R.id.tastingsTextView);
        wineTextView = (TextView) findViewById(R.id.wineDetailsTextView);
        wineImageView = (ImageView) findViewById(R.id.wineBottleImageView);


        allWineRatingList = new ArrayList<>();

        allWinesList = db.getAllWines();

        allWineRatingList = db.getAllOfferingWineRatings();


        rating = new Rating();
        rating = db.getRating(selectedWine.getmId());

        OfferingWineRating offering = new OfferingWineRating(selectedWine, rating);
        db.addRatingWineRating(offering);

        tasting = new Tasting();
        tasting = db.getTasting(rating.getTasteGroup());

        OfferingRatingTasting rateTasteOffering = new OfferingRatingTasting(tasting, rating);
        db.addRatingTastingOffering(rateTasteOffering);

        Log.i(TAG, "ORT Name: " + rateTasteOffering.getmTasting().getName());
        Log.i(TAG, "ORT Date: " + rateTasteOffering.getmTasting().getDate());
        Log.i(TAG, "ORT Location: " + rateTasteOffering.getmTasting().getLocation());


        String ratingText =
                "Color:     " + df.format(offering.getmRating().getColor()) + "\n" +
                "Aroma:   "   + df.format(offering.getmRating().getColor()) + "\n" +
                "Body:      " + df.format(offering.getmRating().getColor()) + "\n" +
                "Taste:     " + df.format(offering.getmRating().getColor()) + "\n" +
                "Finish:    " + df.format(offering.getmRating().getColor()) + "\n" +
                "-----------------------------" + "\n" +
                "Total:     " + df.format(getRankTotal(offering)) + "\n\n" +
                "Notes:     " + offering.getmNotes();

        String tastingText =
                rateTasteOffering.getmTasting().getName() + "\n" +
                rateTasteOffering.getmTasting().getDate() + "\n" +
                rateTasteOffering.getmTasting().getLocation();

        String wineText =
                selectedWine.getmVintage() + "\n" +
                selectedWine.getmWinery() + "\n" +
                selectedWine.getmVineyard() + "\n" +
                selectedWine.getmVarietal();


        wineImageView.setImageURI(selectedWine.getmImageUri());

        ratingTextView.setText(ratingText);
        tastingTextView.setText(tastingText);
        wineTextView.setText(wineText);

        DBHelper db = new DBHelper(this);

        List<OfferingRatingTasting> rateTaste;
        rateTaste = db.getAllRatingTastingOfferings();

        Log.i(TAG, "Showing all Offering Rating Tasting:");
        Log.i(TAG, "Showing all Offering Rating Tasting:");
        Log.i(TAG, "Showing all Offering Rating Tasting:");
        for (OfferingRatingTasting ort: rateTaste)
            Log.i(TAG, ort.toString());
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
    }

    public float getRankTotal(OfferingWineRating offering){

        float total = 0.0f;

        total += offering.getmRating().getColor();
        total += offering.getmRating().getAroma();
        total += offering.getmRating().getBody();
        total += offering.getmRating().getTaste();
        total += offering.getmRating().getFinish();

        return total;
    }
}