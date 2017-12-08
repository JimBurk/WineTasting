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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_details);

        Wine selectedWine = getIntent().getExtras().getParcelable("SelectedWine");

        db = new DBHelper(this);

        List<OfferingRatingTasting> allRatingsTastingList = new ArrayList<>();
        List<OfferingRatingTasting> filteredRatingsTastingList = new ArrayList<>();

        allWinesList = db.getAllWines();
        allRatingsTastingList = db.getAllRatingTastingOfferings();

        ratingTextView = (TextView) findViewById(R.id.ratingsTextView);
        tastingTextView = (TextView) findViewById(R.id.tastingsTextView);
        wineTextView = (TextView) findViewById(R.id.wineDetailsTextView);

        for (Wine wine : allWinesList)
            if (wine.getmTasteGroup() == selectedWine.getmTasteGroup())
                filteredWinesList.add(wine);


        OfferingRatingTasting offering = new OfferingRatingTasting();

        for (OfferingRatingTasting offer : allRatingsTastingList)
            if (offer.getmRating().getId() == selectedWine.getmId()) {
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
            }

        ratingTextView.setText(
                "Color: " + df.format(valueOf(offering.getmRating().getColor())) + "\n" +
                "Aroma: " + df.format(valueOf(offering.getmRating().getColor())) + "\n" +
                "Body: " + df.format(valueOf(offering.getmRating().getColor())) + "\n" +
                "Taste: " + df.format(valueOf(offering.getmRating().getColor())) + "\n" +
                "Finish: " + df.format(valueOf(offering.getmRating().getColor())) + "\n" +
                "----------------" + "\n" +
                //"Total: " + df.format(getRankTotal()) + "\n" +
                "Notes: " + offering.getmNotes()
        );

        tastingTextView.setText(
                "Name: " + offering.getmTasting().getName() + "\n" +
                "Date: " + offering.getmTasting().getDate() + "\n" +
                "Location: " + offering.getmTasting().getLocation()
        );

        wineTextView.setText(
                "Varietal: " + selectedWine.getmVarietal() + "\n" +
                "Vintage: " + valueOf(selectedWine.getmVintage()) + "\n" +
                "Winery: " + selectedWine.getmWinery() + "\n" +
                "Vineyard: " + selectedWine.getmVineyard() + "\n" +
                "Price: " + df.format(valueOf(selectedWine.getmPrice()))
        );



        Log.i("Wine Details Activity", " -> " + selectedWine.getmId());

    }
/**
    public float getRankTotal(){

        float total = 0.0f;

        total += offering.getmRating().getColor();
        total += offering.getmRating().getAroma();
        total += offering.getmRating().getBody();
        total += offering.getmRating().getTaste();
        total += offering.getmRating().getFinish();

        return total;
    }
 */
}
