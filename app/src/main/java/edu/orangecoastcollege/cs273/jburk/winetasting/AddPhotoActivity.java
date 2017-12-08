package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/***
 * This activity is used to attach a photo of each wine's label to the database. A text box to the
 * right of the activity shows the information on the wine to be added, along with an "Add Photo"
 * button. The user will be transferred to the picture gallery on his device, where he will select
 * a photo to be used. Upon selection, the label is shown on the left side of the screen, along with
 * the information on that wine. When the last wine is selected, the image is shown for 6 seconds,
 * while the button and information for the next wine is suppressed.
 */

public class AddPhotoActivity extends AppCompatActivity {

    private ArrayList<Wine> wineArrayList;
    private TextView numTextView;
    private TextView wineInfoTV;
    private TextView addWineInfoTV;
    private ImageView wineImageView;
    private Button addPhotoButton;
    private Uri imageUri;
    private int imageNum = 0;
    private List<String> permsList = new ArrayList<>();

    private DBHelper db;
    private Wine wine;

    private int hasCameraPerm;
    private int readStoragePerm;
    private int writeStoragePerm;

    private Handler handler;

    // constants for permissions
    private static final int GRANTED = PackageManager.PERMISSION_GRANTED;
    private static final int DENIED = PackageManager.PERMISSION_DENIED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        Intent photoIntent = getIntent();

        wineArrayList  = getIntent().getParcelableArrayListExtra("wineArrayList");

        wineImageView = (ImageView) findViewById(R.id.wineBottleIV);
        wineInfoTV = (TextView) findViewById(R.id.wineInfoTV);
        addWineInfoTV = (TextView) findViewById(R.id.addWineInfoTV);

        addPhotoButton = (Button) findViewById(R.id.addPhotoButton);

        wineInfoTV.setVisibility(View.INVISIBLE);

        wineImageView.setImageURI(getUriFromResource(this, R.drawable.wine_bottle));

        List<String> permsList = new ArrayList<>();

        hasCameraPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (hasCameraPerm == DENIED)
            permsList.add(Manifest.permission.CAMERA);

        readStoragePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readStoragePerm == DENIED)
            permsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        writeStoragePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writeStoragePerm == DENIED)
            permsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permsList.size() > 0) {
            // Convert perms list into array
            String[] permsArray = new String[permsList.size()];
            permsList.toArray(permsArray);

            // Ask user for them
            ActivityCompat.requestPermissions(this, permsArray, 1337);
        }

        wine = wineArrayList.get(imageNum);

        String addWineInfo = "  " + Integer.toString(wine.getmVintage());
        addWineInfo += "\n  " + wine.getmWinery();
        addWineInfo += "\n  " + wine.getmVarietal();
        addWineInfo += "\n  " + wine.getmVineyard() + "  ";

        addWineInfoTV.setText(addWineInfo);

        db = new DBHelper(this);
    }

    /***
     * If the permissions are granted, the image gallery is opened.
     * @param v
     */

    public void selectWineImage(View v) {
        // Lets make sure we have all permissions, then start Image Gallery
        if (hasCameraPerm == GRANTED && readStoragePerm == GRANTED && writeStoragePerm == GRANTED) {

            // Open the Image Gallery
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        wineInfoTV.setVisibility(View.VISIBLE);

        wine = wineArrayList.get(imageNum);

        String wineInfo = "  " + Integer.toString(wine.getmVintage());
        wineInfo += "\n  " + wine.getmWinery();
        wineInfo += "\n  " + wine.getmVarietal();
        wineInfo += "\n  " + wine.getmVineyard() + "  ";

        wineInfoTV.setText(wineInfo);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)  {
            // data is from GalleryIntent
            imageUri = data.getData();

            wineImageView.setImageURI(imageUri);

            wine.setmImageUri(imageUri);
            db.updateWine(wine);
        }
        imageNum++;

        if (imageNum > 9) {
            handler = new Handler();
            addWineInfoTV.setVisibility(View.INVISIBLE);
            addPhotoButton.setVisibility(View.INVISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    endIt();
                }
            }, 6000);
        }
        else {
            wine = wineArrayList.get(imageNum);

            String addWineInfo = "  " + Integer.toString(wine.getmVintage());
            addWineInfo += "\n  " + wine.getmWinery();
            addWineInfo += "\n  " + wine.getmVarietal();
            addWineInfo += "\n  " + wine.getmVineyard() + "  ";

            addWineInfoTV.setText(addWineInfo);
        }
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
        // Build a string in the form:
        // android.resources://"package"/drawable/none;
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + res.getResourcePackageName(resId) + "/"
                +res.getResourceTypeName(resId) + "/"
                +res.getResourceEntryName(resId);

        // Parse the String
        return Uri.parse(uri);
    }

    public void endIt() {
        this.finish();
    }
}
