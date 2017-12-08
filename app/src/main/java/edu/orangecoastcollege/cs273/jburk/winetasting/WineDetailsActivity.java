package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;


public class WineDetailsActivity extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat(".#");

    private DBHelper db;
    private List<Wine> allWinesList = new ArrayList<>();
    private List<Wine> filteredWinesList = new ArrayList<>();
    //private List<OfferingRatingTasting> allRatingsTastingList = new ArrayList<>();
    //private List<OfferingRatingTasting> filteredRatingsTastingList = new ArrayList<>();

    private TextView ratingTextView ;
    private TextView tastingTextView;
    private TextView wineTextView;

    List<OfferingWineRating> allWineRatingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_details);

        Wine selectedWine = getIntent().getExtras().getParcelable("SelectedWine");

        db = new DBHelper(this);

        ratingTextView = (TextView) findViewById(R.id.ratingsTextView);
        tastingTextView = (TextView) findViewById(R.id.tastingsTextView);
        wineTextView = (TextView) findViewById(R.id.wineDetailsTextView);

        allWineRatingList = new ArrayList<>();
        //List<OfferingWineRating> filteredWineRatingList = new ArrayList<>();

        allWinesList = db.getAllWines();
        allWineRatingList = db.getAllOfferingWineRatings();


        Rating rating = db.getRating(selectedWine.getmId());

        OfferingWineRating offering = new OfferingWineRating(selectedWine, rating);

        db.addRatingWineRating(offering);

        Tasting tasting = new Tasting(rating.getTasteGroup());

        OfferingRatingTasting rateTasteOffering = new OfferingRatingTasting(tasting, rating);


        ratingTextView.setText(
                "Color: " + String.valueOf(offering.getmRating().getColor()) + "\n" +
                "Aroma: " + String.valueOf(offering.getmRating().getColor()) + "\n" +
                "Body: " + String.valueOf(offering.getmRating().getColor()) + "\n" +
                "Taste: " + String.valueOf(offering.getmRating().getColor()) + "\n" +
                "Finish: " + String.valueOf(offering.getmRating().getColor()) + "\n" +
                "----------------" + "\n" +
                "Total: " + String.valueOf(getRankTotal(offering)) + "\n" +
                "Notes: " + offering.getmNotes()
        );

        tastingTextView.setText(
                "Name: " + rateTasteOffering.getmTasting().getName() + "\n" +
                "Date: " + rateTasteOffering.getmTasting().getDate() + "\n" +
                "Location: " + rateTasteOffering.getmTasting().getLocation()
        );


        wineTextView.setText(
                "Varietal: " + selectedWine.getmVarietal() + "\n" +
                "Vintage: " + String.valueOf(selectedWine.getmVintage()) + "\n" +
                "Winery: " + selectedWine.getmWinery() + "\n" +
                "Vineyard: " + selectedWine.getmVineyard() + "\n" +
                "Price: " + String.valueOf(selectedWine.getmPrice())
        );



        Log.i("Wine Details Activity", " -> " + selectedWine.getmId());

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

/**
 for (OfferingWineRating offer : allWineRatingList)
 if (offer.getmRating().getId() == selectedWine.getmId()) {}
 */

/**
 offering.setmTasting(offer.getmTasting());
 offering.setmRating(offer.getmRating());
 offering.getmTasting().setName(offer.getmTasting().getName());
 offering.getmTasting().setDate(offer.getmTasting().getDate());
 offering.getmTasting().setLocation(offer.getmTasting().getLocation());
 offering.getmRating().setColor(offer.getmRating().getColor());
 offering.getmRating().setAroma(offer.getmRating().getAroma());
 offering.getmRating().setBody(offer.getmRating().getBody());
 offering.getmRating().setTaste(offer.getmRating().getTaste());
 offering.getmRating().setFinish(offer.getmRating().getFinish());
 offering.getmRating().setNotes(offer.getmRating().getNotes());
 */
