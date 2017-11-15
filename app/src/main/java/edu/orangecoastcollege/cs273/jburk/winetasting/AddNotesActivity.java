package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddNotesActivity extends AppCompatActivity {

    private DBHelper db;

    private EditText mVarietalEditText;
    private EditText mVintageEditText;
    private EditText mWineryEditText;
    private EditText mVineyardEditText;
    private EditText mPriceEditText;

    private Wine mWine = new Wine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        db = new DBHelper(this);

        mVarietalEditText = (EditText) findViewById(R.id.varietalEditText);
        mVintageEditText = (EditText) findViewById(R.id.vintageEditText);
        mWineryEditText = (EditText) findViewById(R.id.wineryEditText);
        mVineyardEditText = (EditText) findViewById(R.id.vineYardEditView);
        mPriceEditText = (EditText) findViewById(R.id.priceEditView);
    }

    public void collectWineData(View view){
        if (mVarietalEditText.getText().toString().length() == 0)
            mWine.setmVarietal("");
        else
            mWine.setmVarietal(String.valueOf(mVarietalEditText.getText().toString()));
        if (mVintageEditText.getText().toString().length() == 0)
            mWine.setmVintage(0);
        else
            mWine.setmVintage(Integer.parseInt(mVintageEditText.getText().toString()));
        if (mWineryEditText.getText().toString().length() == 0)
            mWine.setmWinery("");
        else
            mWine.setmWinery(String.valueOf(mWineryEditText.getText().toString()));
        if (mPriceEditText.getText().toString().length() == 0)
            mWine.setmPrice(0.0);
        else
            mWine.setmPrice(Double.parseDouble(mPriceEditText.getText().toString()));
    }

    public void addWineToNotes(View view){
        collectWineData(view);

        db.addWine(mWine);

        // create next activity
        Intent launchWineIntent = new Intent(this, RatingActivity.class);

        launchWineIntent.putExtra("Varietal", mWine.getmVarietal());
        launchWineIntent.putExtra("Vintage", mWine.getmVintage());
        launchWineIntent.putExtra("Winery", mWine.getmWinery());
        launchWineIntent.putExtra("Vineyard", mWine.getmVineyard());
        launchWineIntent.putExtra("Price", mWine.getmPrice());

        mVarietalEditText.setText("");
        mVintageEditText.setText("");
        mWineryEditText.setText("");
        mVineyardEditText.setText("");
        mPriceEditText.setText("");

        startActivity(launchWineIntent);
    }
}
