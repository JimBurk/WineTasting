package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class WinesActivity extends AppCompatActivity {

    private long mId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wines);
    }

    public void addPhoto(View view) {
        Intent picturesIntent = new Intent(this, AddPhotoActivity.class);
        picturesIntent.putExtra("FirstWineID", 0);
        startActivity(picturesIntent);
        overridePendingTransition(R.anim.fade_in, 0);
    }

    public void winesEnter(View view) {
        EditText vintageET = findViewById(R.id.wines1AVintageET);
        EditText varietalET = findViewById(R.id.wines1AVarietalET);
        EditText wineryET = findViewById(R.id.wines1AWineryET);
        EditText vineyardET = findViewById(R.id.wines1AVineyardET);
        EditText priceET = findViewById(R.id.wines1APriceET);

        vintageET.setText("2003");
        varietalET.setText("Cabernet Sauvignon");
        wineryET.setText("Pride Mountain");
        vineyardET.setText("Napa");
        priceET.setText("$64.00");

        vintageET = findViewById(R.id.wines1BVintageET);
        varietalET = findViewById(R.id.wines1BVarietalET);
        wineryET = findViewById(R.id.wines1BWineryET);
        vineyardET = findViewById(R.id.wines1BVineyardET);
        priceET = findViewById(R.id.wines1BPriceET);

        vintageET.setText("2015");
        varietalET.setText("Zinfandel");
        wineryET.setText("Mauritson");
        vineyardET.setText("Pritchet Peak");
        priceET.setText("$42.95");

        vintageET = findViewById(R.id.wines2AVintageET);
        varietalET = findViewById(R.id.wines2AVarietalET);
        wineryET = findViewById(R.id.wines2AWineryET);
        vineyardET = findViewById(R.id.wines2AVineyardET);
        priceET = findViewById(R.id.wines2APriceET);

        vintageET.setText("2015");
        varietalET.setText("Zinfandel");
        wineryET.setText("Seghesio Family Vineyards");
        vineyardET.setText("Sonoma County");
        priceET.setText("$24.95");

        vintageET = findViewById(R.id.wines2BVintageET);
        varietalET = findViewById(R.id.wines2BVarietalET);
        wineryET = findViewById(R.id.wines2BWineryET);
        vineyardET = findViewById(R.id.wines2BVineyardET);
        priceET = findViewById(R.id.wines2BPriceET);

        vintageET.setText("2013");
        varietalET.setText("Pinot Noir");
        wineryET.setText("Wild Horse");
        vineyardET.setText("Central Coast");
        priceET.setText("$23.95");

        vintageET = findViewById(R.id.wines3AVintageET);
        varietalET = findViewById(R.id.wines3AVarietalET);
        wineryET = findViewById(R.id.wines3AWineryET);
        vineyardET = findViewById(R.id.wines3AVineyardET);
        priceET = findViewById(R.id.wines3APriceET);

        vintageET.setText("2010");
        varietalET.setText("Petite Syrah");
        wineryET.setText("Carlisle");
        vineyardET.setText("Palisades VY");
        priceET.setText("$37.95");

        vintageET = findViewById(R.id.wines3BVintageET);
        varietalET = findViewById(R.id.wines3BVarietalET);
        wineryET = findViewById(R.id.wines3BWineryET);
        vineyardET = findViewById(R.id.wines3BVineyardET);
        priceET = findViewById(R.id.wines3BPriceET);

        vintageET.setText("2008");
        varietalET.setText("Merlot");
        wineryET.setText("Pahlmeyer");
        vineyardET.setText("Napa Valley");
        priceET.setText("$64.95");

        vintageET = findViewById(R.id.wines4AVintageET);
        varietalET = findViewById(R.id.wines4AVarietalET);
        wineryET = findViewById(R.id.wines4AWineryET);
        vineyardET = findViewById(R.id.wines4AVineyardET);
        priceET = findViewById(R.id.wines4APriceET);

        vintageET.setText("2012");
        varietalET.setText("Pinot Noir");
        wineryET.setText("Arista");
        vineyardET.setText("Harper's Rest");
        priceET.setText("$34.00");

        vintageET = findViewById(R.id.wines4BVintageET);
        varietalET = findViewById(R.id.wines4BVarietalET);
        wineryET = findViewById(R.id.wines4BWineryET);
        vineyardET = findViewById(R.id.wines4BVineyardET);
        priceET = findViewById(R.id.wines4BPriceET);

        vintageET.setText("2016");
        varietalET.setText("Red Blend");
        wineryET.setText("Bedrock");
        vineyardET.setText("Evangelho Heritage");
        priceET.setText("$42.95");

        vintageET = findViewById(R.id.wines5AVintageET);
        varietalET = findViewById(R.id.wines5AVarietalET);
        wineryET = findViewById(R.id.wines5AWineryET);
        vineyardET = findViewById(R.id.wines5AVineyardET);
        priceET = findViewById(R.id.wines5APriceET);

        vintageET.setText("2013");
        varietalET.setText("Syrah");
        wineryET.setText("Rockpile");
        vineyardET.setText("Madrone Springs");
        priceET.setText("$39.00");

        vintageET = findViewById(R.id.wines5BVintageET);
        varietalET = findViewById(R.id.wines5BVarietalET);
        wineryET = findViewById(R.id.wines5BWineryET);
        vineyardET = findViewById(R.id.wines5BVineyardET);
        priceET = findViewById(R.id.wines5BPriceET);

        vintageET.setText("2013");
        varietalET.setText("Zinfandel");
        wineryET.setText("Ravenswood");
        vineyardET.setText("Big River VY");
        priceET.setText("$35.95");
    }

    public void winesContinue(View view) {
        Intent continueIntent = new Intent(this, OpenActivity.class);
        startActivity(continueIntent);
        overridePendingTransition(R.anim.fade_in, 0);
    }
}
