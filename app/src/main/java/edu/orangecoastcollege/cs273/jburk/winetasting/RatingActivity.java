package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/***
 *  The Rating Activity is the page used to enter values to each of the 5 attributes for each wine.
 *  These are Color, Aroma, Body, Taste and Finish. A rating is from 0 to 5 for each attribute. Tapping
 *  any of the attribute headers will present the user with a description of what the various rating
 *  represent. When any wine has been rated, tapping the total button will sum the 5 values. After
 *  all 10 wines have been totaled, the "Rank" button is set visible. The wines will be ranked for
 *  the top 3 places. The user may then proceed to the next page, the wine entry page.
 *
 *  Written by:  Jim Burk
 */

public class RatingActivity extends AppCompatActivity {

    boolean [] totaled = { false, false, false, false, false, false, false, false, false, false };
    float total [] = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    Button totalButton;
    Button rankButton;
    Button continueButton;

    String totalString;

    private Rating rating1a;
    private Rating rating1b;
    private Rating rating2a;
    private Rating rating2b;
    private Rating rating3a;
    private Rating rating3b;
    private Rating rating4a;
    private Rating rating4b;
    private Rating rating5a;
    private Rating rating5b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        rankButton = (Button) findViewById(R.id.rankDisplay);
        rankButton.setVisibility(View.INVISIBLE);
        continueButton = (Button) findViewById(R.id.ratingContinue);
        continueButton.setVisibility(View.INVISIBLE);

        rating1a = new Rating();
        rating1b = new Rating();
        rating2a = new Rating();
        rating2b = new Rating();
        rating3a = new Rating();
        rating3b = new Rating();
        rating4a = new Rating();
        rating4b = new Rating();
        rating5a = new Rating();
        rating5b = new Rating();
    }

    /***
     * When the "Total" button is pressed for each wine, from 1A to 5B, the 5 attribute ratings are
     * determined. A check is made that all ratings have been entered, and that the values are from
     * 0 to 5. If either is not met an alert is displayed. There is a total function for each of the
     * 10 wines that are identical except for the line number.
     *
     * @param view
     */

    public void total1A(View view) {
        EditText colorET = findViewById(R.id.rating1AColorTV);
        EditText aromaET = findViewById(R.id.rating1AAromaTV);
        EditText bodyET =  findViewById(R.id.rating1ABodyTV);
        EditText tasteET = findViewById(R.id.rating1ATasteTV);
        EditText finishET = findViewById(R.id.rating1AFinishTV);

        rating1a = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[0] = rating1a.getColor() + rating1a.getAroma() + rating1a.getBody() + rating1a.getTaste() + rating1a.getFinish();

        if (total[0] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating1ATotalTV);
            totalString = Float.toString(total[0]);
            totalButton.setText(totalString);
            totaled[0] = true;
            checkTotaled();
        }
    }

    public void total1B(View view) {
        EditText colorET = findViewById(R.id.rating1BColorTV);
        EditText aromaET = findViewById(R.id.rating1BAromaTV);
        EditText bodyET =  findViewById(R.id.rating1BBodyTV);
        EditText tasteET = findViewById(R.id.rating1BTasteTV);
        EditText finishET = findViewById(R.id.rating1BFinishTV);

        rating1b = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[1] = rating1b.getColor() + rating1b.getAroma() + rating1b.getBody() + rating1b.getTaste() + rating1b.getFinish();

        if (total[1] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating1BTotalTV);
            totalString = Float.toString(total[1]);
            totalButton.setText(totalString);
            totaled[1] = true;
            checkTotaled();
        }
    }

    public void total2A(View view) {
        EditText colorET = findViewById(R.id.rating2AColorTV);
        EditText aromaET = findViewById(R.id.rating2AAromaTV);
        EditText bodyET =  findViewById(R.id.rating2ABodyTV);
        EditText tasteET = findViewById(R.id.rating2ATasteTV);
        EditText finishET = findViewById(R.id.rating2AFinishTV);

        rating2a = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[2] = rating2a.getColor() + rating2a.getAroma() + rating2a.getBody() + rating2a.getTaste() + rating2a.getFinish();

        if (total[2] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating2ATotalTV);
            totalString = Float.toString(total[2]);
            totalButton.setText(totalString);
            totaled[2] = true;
            checkTotaled();
        }
    }

    public void total2B(View view) {
        EditText colorET = findViewById(R.id.rating2BColorTV);
        EditText aromaET = findViewById(R.id.rating2BAromaTV);
        EditText bodyET =  findViewById(R.id.rating2BBodyTV);
        EditText tasteET = findViewById(R.id.rating2BTasteTV);
        EditText finishET = findViewById(R.id.rating2BFinishTV);

        rating2b = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[3] = rating2b.getColor() + rating2b.getAroma() + rating2b.getBody() + rating2b.getTaste() + rating2b.getFinish();

        if (total[3] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating2BTotalTV);
            totalString = Float.toString(total[3]);
            totalButton.setText(totalString);
            totaled[3] = true;
            checkTotaled();
        }
    }

    public void total3A(View view) {
        EditText colorET = findViewById(R.id.rating3AColorTV);
        EditText aromaET = findViewById(R.id.rating3AAromaTV);
        EditText bodyET = findViewById(R.id.rating3ABodyTV);
        EditText tasteET = findViewById(R.id.rating3ATasteTV);
        EditText finishET = findViewById(R.id.rating3AFinishTV);

        rating3a = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[4] = rating3a.getColor() + rating3a.getAroma() + rating3a.getBody() + rating3a.getTaste() + rating3a.getFinish();

        if (total[4] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating3ATotalTV);
            totalString = Float.toString(total[4]);
            totalButton.setText(totalString);
            totaled[4] = true;
            checkTotaled();
        }
    }

    public void total3B(View view) {
        EditText colorET = findViewById(R.id.rating3BColorTV);
        EditText aromaET = findViewById(R.id.rating3BAromaTV);
        EditText bodyET = findViewById(R.id.rating3BBodyTV);
        EditText tasteET = findViewById(R.id.rating3BTasteTV);
        EditText finishET = findViewById(R.id.rating3BFinishTV);

        rating3b = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[5] = rating3b.getColor() + rating3b.getAroma() + rating3b.getBody() + rating3b.getTaste() + rating3b.getFinish();

        if (total[5] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating3BTotalTV);
            totalString = Float.toString(total[5]);
            totalButton.setText(totalString);
            totaled[5] = true;
            checkTotaled();
        }
    }

    public void total4A(View view) {
        EditText colorET = findViewById(R.id.rating4AColorTV);
        EditText aromaET = findViewById(R.id.rating4AAromaTV);
        EditText bodyET = findViewById(R.id.rating4ABodyTV);
        EditText tasteET = findViewById(R.id.rating4ATasteTV);
        EditText finishET = findViewById(R.id.rating4AFinishTV);

        rating4a = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[6] = rating4a.getColor() + rating4a.getAroma() + rating4a.getBody() + rating4a.getTaste() + rating4a.getFinish();

        if (total[6] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating4ATotalTV);
            totalString = Float.toString(total[6]);
            totalButton.setText(totalString);
            totaled[6] = true;
            checkTotaled();
        }
    }

    public void total4B(View view) {
        EditText colorET = findViewById(R.id.rating4BColorTV);
        EditText aromaET = findViewById(R.id.rating4BAromaTV);
        EditText bodyET = findViewById(R.id.rating4BBodyTV);
        EditText tasteET = findViewById(R.id.rating4BTasteTV);
        EditText finishET = findViewById(R.id.rating4BFinishTV);

        rating4b = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[7] = rating4b.getColor() + rating4b.getAroma() + rating4b.getBody() + rating4b.getTaste() + rating4b.getFinish();

        if (total[7] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating4BTotalTV);
            totalString = Float.toString(total[7]);
            totalButton.setText(totalString);
            totaled[7] = true;
            checkTotaled();
        }
    }

    public void total5A(View view) {
        EditText colorET = findViewById(R.id.rating5AColorTV);
        EditText aromaET = findViewById(R.id.rating5AAromaTV);
        EditText bodyET = findViewById(R.id.rating5ABodyTV);
        EditText tasteET = findViewById(R.id.rating5ATasteTV);
        EditText finishET = findViewById(R.id.rating5AFinishTV);

        rating5a = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[8] = rating5a.getColor() + rating5a.getAroma() + rating5a.getBody() + rating5a.getTaste() + rating5a.getFinish();

        if (total[8] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating5ATotalTV);
            totalString = Float.toString(total[8]);
            totalButton.setText(totalString);
            totaled[8] = true;
            checkTotaled();
        }
    }

    public void total5B(View view) {
        EditText colorET = findViewById(R.id.rating5BColorTV);
        EditText aromaET = findViewById(R.id.rating5BAromaTV);
        EditText bodyET = findViewById(R.id.rating5BBodyTV);
        EditText tasteET = findViewById(R.id.rating5BTasteTV);
        EditText finishET = findViewById(R.id.rating5BFinishTV);

        rating5b = getTotal(colorET, aromaET, bodyET, tasteET, finishET);

        total[9] = rating5b.getColor() + rating5b.getAroma() + rating5b.getBody() + rating5b.getTaste() + rating5b.getFinish();

        if (total[9] > 0.0) {
            totalButton = (Button) findViewById(R.id.rating5BTotalTV);
            totalString = Float.toString(total[9]);
            totalButton.setText(totalString);
            totaled[9] = true;
            checkTotaled();
        }
    }

    /***
     * Thias method is called by each of the 10 "totalxx" methods. Each Edit text value is converted
     * to a float, and the value is checked for the correct range. This method puts up the alerts when
     * the inputs are incorrect. If the value is correct the checkTotal() method is called to see if
     * all 10 wines have beeen totaled.
     *
     * @param colorET
     * @param aromaET
     * @param bodyET
     * @param tasteET
     * @param finishET
     *
     * @return the sum of the 5 attributes
     */

    public Rating getTotal(EditText colorET, EditText aromaET, EditText bodyET, EditText tasteET, EditText finishET) {
        float total = 0.0f;

        Rating newRating = new Rating();

        String colorString = colorET.getText().toString();
        String aromaString = aromaET.getText().toString();
        String bodyString = bodyET.getText().toString();
        String tasteString = tasteET.getText().toString();
        String finishString = finishET.getText().toString();

        if (!colorString.isEmpty() && !aromaString.isEmpty() && !bodyString.isEmpty() && !tasteString.isEmpty() && !finishString.isEmpty()) {

            float colorRating = Float.parseFloat(colorString);
            float aromaRating = Float.parseFloat(aromaString);
            float bodyRating = Float.parseFloat(bodyString);
            float tasteRating = Float.parseFloat(tasteString);
            float finishRating = Float.parseFloat(finishString);

            newRating.setColor(colorRating);
            newRating.setAroma(aromaRating);
            newRating.setBody(bodyRating);
            newRating.setTaste(tasteRating);
            newRating.setFinish(finishRating);

            if ((colorRating >= 0 && colorRating <= 5.0) && (aromaRating >= 0.0 && aromaRating <= 5.0)
                    && (bodyRating >= 0.0 && bodyRating <= 5.0) && (tasteRating >= 0.0 && tasteRating <= 5.0)
                    && (finishRating >= 0.0 && finishRating <= 5.0)) {

                total = colorRating + aromaRating + bodyRating + tasteRating + finishRating;

                checkTotaled();
            } else {
                setDialog5();
            }
        }
        else {
            setDialogEmpty();
        }
        return newRating;
    }

    /***
     * This is a dummy method to fill in a bunch of ratings during a demonstration. It will be deleted
     * for the released version.
     *
     * @param v
     */

    public void fillRatings(View v) {
        EditText colorET = findViewById(R.id.rating1AColorTV);
        EditText aromaET = findViewById(R.id.rating1AAromaTV);
        EditText bodyET = findViewById(R.id.rating1ABodyTV);
        EditText tasteET = findViewById(R.id.rating1ATasteTV);
        EditText finishET = findViewById(R.id.rating1AFinishTV);
        Button totalET = findViewById(R.id.rating1ATotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("2");
        totalET.setText("");

        colorET = findViewById(R.id.rating1BColorTV);
        aromaET = findViewById(R.id.rating1BAromaTV);
        bodyET = findViewById(R.id.rating1BBodyTV);
        tasteET = findViewById(R.id.rating1BTasteTV);
        finishET = findViewById(R.id.rating1BFinishTV);
        totalET = findViewById(R.id.rating1BTotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("2");
        totalET.setText("");

        colorET = findViewById(R.id.rating2AColorTV);
        aromaET = findViewById(R.id.rating2AAromaTV);
        bodyET = findViewById(R.id.rating2ABodyTV);
        tasteET = findViewById(R.id.rating2ATasteTV);
        finishET = findViewById(R.id.rating2AFinishTV);
        totalET = findViewById(R.id.rating2ATotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("2");
        totalET.setText("");

        colorET = findViewById(R.id.rating2BColorTV);
        aromaET = findViewById(R.id.rating2BAromaTV);
        bodyET = findViewById(R.id.rating2BBodyTV);
        tasteET = findViewById(R.id.rating2BTasteTV);
        finishET = findViewById(R.id.rating2BFinishTV);
        totalET = findViewById(R.id.rating2BTotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("2");
        totalET.setText("");

        colorET = findViewById(R.id.rating3AColorTV);
        aromaET = findViewById(R.id.rating3AAromaTV);
        bodyET = findViewById(R.id.rating3ABodyTV);
        tasteET = findViewById(R.id.rating3ATasteTV);
        finishET = findViewById(R.id.rating3AFinishTV);
        totalET = findViewById(R.id.rating3ATotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("2");
        totalET.setText("");

        colorET = findViewById(R.id.rating3BColorTV);
        aromaET = findViewById(R.id.rating3BAromaTV);
        bodyET = findViewById(R.id.rating3BBodyTV);
        tasteET = findViewById(R.id.rating3BTasteTV);
        finishET = findViewById(R.id.rating3BFinishTV);
        totalET = findViewById(R.id.rating3BTotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("2");
        totalET.setText("");

        colorET = findViewById(R.id.rating4AColorTV);
        aromaET = findViewById(R.id.rating4AAromaTV);
        bodyET = findViewById(R.id.rating4ABodyTV);
        tasteET = findViewById(R.id.rating4ATasteTV);
        finishET = findViewById(R.id.rating4AFinishTV);
        totalET = findViewById(R.id.rating4ATotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("2");
        totalET.setText("");

        colorET = findViewById(R.id.rating4BColorTV);
        aromaET = findViewById(R.id.rating4BAromaTV);
        bodyET = findViewById(R.id.rating4BBodyTV);
        tasteET = findViewById(R.id.rating4BTasteTV);
        finishET = findViewById(R.id.rating4BFinishTV);
        totalET = findViewById(R.id.rating4BTotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("3");
        totalET.setText("");

        colorET = findViewById(R.id.rating5AColorTV);
        aromaET = findViewById(R.id.rating5AAromaTV);
        bodyET = findViewById(R.id.rating5ABodyTV);
        tasteET = findViewById(R.id.rating5ATasteTV);
        finishET = findViewById(R.id.rating5AFinishTV);
        totalET = findViewById(R.id.rating5ATotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("4");
        totalET.setText("");

        colorET = findViewById(R.id.rating5BColorTV);
        aromaET = findViewById(R.id.rating5BAromaTV);
        bodyET = findViewById(R.id.rating5BBodyTV);
        tasteET = findViewById(R.id.rating5BTasteTV);
        finishET = findViewById(R.id.rating5BFinishTV);
        totalET = findViewById(R.id.rating5BTotalTV);

        colorET.setText("3");
        aromaET.setText("3");
        bodyET.setText("3");
        tasteET.setText("3");
        finishET.setText("5");
        totalET.setText("");
    }

    /***
     * This method is called for each total to see if all 10 wines have been totaled. If so the "Rank"
     * button is made visible.
     */

    private void checkTotaled() {
        boolean allDone = true;
        for (int i = 0; i < 10; i++)
            if (!totaled[i])
                allDone = false;

        if (allDone) rankButton.setVisibility(View.VISIBLE);
    }

    /***
     * This method will rank the totals obtained for each wine. Only the top 3 places are determined.
     * There may be ties for 3rd.
     */

    public void rankCalculate(View view) {
        float order[] = {-1, -1, -1};
        for (int i = 0; i < 10; i++) {
            if (total[i] >= order[0]) {
                order[2] = order[1];
                order[1] = order[0];
                order[0] = total[i];
            }
            else if (total [i] >= order[1]) {
                order[2] = order[1];
                order[1] = total[i];
            }
            else if (total[i] >= order[2])
                order[2] = total[i];
        }

        TextView rankView1A = findViewById(R.id.rating1ARankTV);
        TextView rankView1B = findViewById(R.id.rating1BRankTV);
        TextView rankView2A = findViewById(R.id.rating2ARankTV);
        TextView rankView2B = findViewById(R.id.rating2BRankTV);
        TextView rankView3A = findViewById(R.id.rating3ARankTV);
        TextView rankView3B = findViewById(R.id.rating3BRankTV);
        TextView rankView4A = findViewById(R.id.rating4ARankTV);
        TextView rankView4B = findViewById(R.id.rating4BRankTV);
        TextView rankView5A = findViewById(R.id.rating5ARankTV);
        TextView rankView5B = findViewById(R.id.rating5BRankTV);

        rankView1A.setText("");
        rankView1B.setText("");
        rankView2A.setText("");
        rankView2B.setText("");
        rankView3A.setText("");
        rankView3B.setText("");
        rankView4A.setText("");
        rankView4B.setText("");
        rankView5A.setText("");
        rankView5B.setText("");

        Button totalButton = (Button) findViewById(R.id.rating1ATotalTV);
        String totalString = totalButton.getText().toString();
        float totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView1A.setText("1");
        else if (totalRating == order[1]) rankView1A.setText("2");
        else if (totalRating == order[2]) rankView1A.setText("3");

        totalButton = (Button) findViewById(R.id.rating1BTotalTV);
        totalString = totalButton.getText().toString();
        totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView1B.setText("1");
        else if (totalRating == order[1]) rankView1B.setText("2");
        else if (totalRating == order[2]) rankView1B.setText("3");

        totalButton = (Button) findViewById(R.id.rating2ATotalTV);
        totalString = totalButton.getText().toString();
        totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView2A.setText("1");
        else if (totalRating == order[1]) rankView2A.setText("2");
        else if (totalRating == order[2]) rankView2A.setText("3");

        totalButton = (Button) findViewById(R.id.rating2BTotalTV);
        totalString = totalButton.getText().toString();
        totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView2B.setText("1");
        else if (totalRating == order[1]) rankView2B.setText("2");
        else if (totalRating == order[2]) rankView2B.setText("3");

        totalButton = (Button) findViewById(R.id.rating3ATotalTV);
        totalString = totalButton.getText().toString();
        totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView3A.setText("1");
        else if (totalRating == order[1]) rankView3A.setText("2");
        else if (totalRating == order[2]) rankView3A.setText("3");

        totalButton = (Button) findViewById(R.id.rating3BTotalTV);
        totalString = totalButton.getText().toString();
        totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView3B.setText("1");
        else if (totalRating == order[1]) rankView3B.setText("2");
        else if (totalRating == order[2]) rankView3B.setText("3");

        totalButton = (Button) findViewById(R.id.rating4ATotalTV);
        totalString = totalButton.getText().toString();
        totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView4A.setText("1");
        else if (totalRating == order[1]) rankView4A.setText("2");
        else if (totalRating == order[2]) rankView4A.setText("3");

        totalButton = (Button) findViewById(R.id.rating4BTotalTV);
        totalString = totalButton.getText().toString();
        totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView4B.setText("1");
        else if (totalRating == order[1]) rankView4B.setText("2");
        else if (totalRating == order[2]) rankView4B.setText("3");

        totalButton = (Button) findViewById(R.id.rating5ATotalTV);
        totalString = totalButton.getText().toString();
        totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView5A.setText("1");
        else if (totalRating == order[1]) rankView5A.setText("2");
        else if (totalRating == order[2]) rankView5A.setText("3");

        totalButton = (Button) findViewById(R.id.rating5BTotalTV);
        totalString = totalButton.getText().toString();
        totalRating = Float.parseFloat(totalString);
        if (totalRating == order[0]) rankView5B.setText("1");
        else if (totalRating == order[1]) rankView5B.setText("2");
        else if (totalRating == order[2]) rankView5B.setText("3");

        continueButton.setVisibility(View.VISIBLE);
    }

    /***
     *This method continues to the next activity
     */

    public void ratingContinue(View view) {
        Intent winesIntent = new Intent(this, WinesActivity.class);
        startActivity(winesIntent);
        overridePendingTransition(R.anim.fade_in, 0);
    }

    /***
     * The following are all alert dialogs for the total errors, and the rating criteria.
     */

    private void setDialog5() {
        AlertDialog alertDialog = new AlertDialog.Builder(RatingActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("A Rating must be from 0 to 5");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void setDialogEmpty() {
        AlertDialog alertDialog = new AlertDialog.Builder(RatingActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("All Ratings Must be entered BEFORE Totaling");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void colorAlert(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(RatingActivity.this).create();
        alertDialog.setTitle("Color");
        alertDialog.setMessage("0 is Cloudy, off color, sediment\t\t5 is Clear, no off colors, leggy");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void aromaAlert(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(RatingActivity.this).create();
        alertDialog.setTitle("Aroma");
        alertDialog.setMessage("0 is Little or no aroma, vinegary\t\t5 is Complex, Fruity, Flowery");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void bodyAlert(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(RatingActivity.this).create();
        alertDialog.setTitle("Body");
        alertDialog.setMessage("0 is Little texture in mouth\t\t5 is Texture and Weight feel in mouth");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void tasteAlert(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(RatingActivity.this).create();
        alertDialog.setTitle("Taste");
        alertDialog.setMessage("0 is Little or no flavors\t\t5 is Several flavors detected");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void finishAlert(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(RatingActivity.this).create();
        alertDialog.setTitle("Finish");
        alertDialog.setMessage("0 is Taste ends abruptly, no taste\t\t5 is Flavoir lingers in Mouth");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
