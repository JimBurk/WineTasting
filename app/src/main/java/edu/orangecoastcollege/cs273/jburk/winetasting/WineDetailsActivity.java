package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import static java.lang.String.valueOf;


public class WineDetailsActivity extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat(".#");

    private DBHelper db;
    private List<Wine> allWinesList = new ArrayList<>();

    private TextView ratingTextView ;
    private TextView tastingTextView;
    private TextView wineTextView;
    private ImageView wineImageView;

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
        wineImageView = (ImageView) findViewById(R.id.wineBottleImageView);


        allWineRatingList = new ArrayList<>();

        allWinesList = db.getAllWines();

        allWineRatingList = db.getAllOfferingWineRatings();
        Rating rating = db.getRating(selectedWine.getmId());

        OfferingWineRating offering = new OfferingWineRating(selectedWine, rating);
        db.addRatingWineRating(offering);

        Tasting tasting = new Tasting(rating.getTasteGroup());

        OfferingRatingTasting rateTasteOffering = new OfferingRatingTasting(tasting, rating);

        String ratingText = "Color: " + df.format(offering.getmRating().getColor()) + "\n" +
                "Aroma: " + df.format(offering.getmRating().getColor()) + "\n" +
                "Body: " + df.format(offering.getmRating().getColor()) + "\n" +
                "Taste: " + df.format(offering.getmRating().getColor()) + "\n" +
                "Finish: " + df.format(offering.getmRating().getColor()) + "\n" +
                "----------------" + "\n" +
                "Total: " + df.format(getRankTotal(offering)) + "\n\n" +
                "Notes: " + offering.getmNotes();

        String tastingText = "Name: " + rateTasteOffering.getmTasting().getName() + "\n" +
                "Date: " + rateTasteOffering.getmTasting().getDate() + "\n" +
                "Location: " + rateTasteOffering.getmTasting().getLocation();

        String wineText = "Name: " + rateTasteOffering.getmTasting().getName() + "\n" +
                "Date: " + rateTasteOffering.getmTasting().getDate() + "\n" +
                "Location: " + rateTasteOffering.getmTasting().getLocation();


        wineImageView.setImageURI(getUriFromResource(this, R.drawable.vines5));

        ratingTextView.setText(ratingText);
        tastingTextView.setText(tastingText);
        wineTextView.setText(wineText);
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

    /***
     * This method obtains the URI of the image so it can be saved.
     *
     * @param context
     * @param resId
     * @return
     */
    public static Uri getUriFromResource(Context context, int resId) {
        Resources res = context.getResources();
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + res.getResourcePackageName(resId) + "/"
                +res.getResourceTypeName(resId) + "/"
                +res.getResourceEntryName(resId);

        // Parse the String
        return Uri.parse(uri);
    }

}