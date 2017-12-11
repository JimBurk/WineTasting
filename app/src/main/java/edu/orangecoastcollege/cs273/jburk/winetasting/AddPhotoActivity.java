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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URLDecoder;
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
    private ArrayList<Wine> updateWineList;
    private TextView wineInfoTV;
    private TextView addWineInfoTV;
    private ImageView wineImageView;
    private Button addPhotoButton;
    private Uri imageUri;
    private int imageNum = 0;
    private List<String> permslist;
    private List<Wine> wineList;
    private String imgUriStr;


    private DBHelper db;
    private Wine wine;

    private int hasCameraPerm;
    private int readStoragePerm;
    private int writeStoragePerm;

    private Handler handler;

    public static final String TAG = AddPhotoActivity.class.getSimpleName();

    // constants for permissions
    private static final int GRANTED = PackageManager.PERMISSION_GRANTED;
    private static final int DENIED = PackageManager.PERMISSION_DENIED;

    protected static final String CONTENT_PHOTOS_URI_PREFIX = "content://com.google.android.apps.photos.contentprovider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        db = new DBHelper(this);


        wineArrayList = getIntent().getParcelableArrayListExtra("wineArrayList");
        wine = wineArrayList.get(imageNum);


        wineList = new ArrayList<>();
        updateWineList = new ArrayList<>();


        imgUriStr = null;


        wineInfoTV = (TextView) findViewById(R.id.wineInfoTV);
        addWineInfoTV = (TextView) findViewById(R.id.addWineInfoTV);
        addPhotoButton = (Button) findViewById(R.id.addPhotoButton);


        wineInfoTV.setVisibility(View.INVISIBLE);


        wineImageView = (ImageView) findViewById(R.id.wineBottleIV);
        imageUri = getUriFromResource(this, R.drawable.wine_bottle);
        wineImageView.setImageURI(imageUri);



        String addWineInfo = "  " + Integer.toString(wine.getmVintage());
        addWineInfo += "\n  " + wine.getmWinery();
        addWineInfo += "\n  " + wine.getmVarietal();
        addWineInfo += "\n  " + wine.getmVineyard() + "  ";

        addWineInfoTV.setText(addWineInfo);
    }

    /***
     * If the permissions are granted, the image gallery is opened.
     * @param v
     */

    public void selectWineImage(View v) {
        permslist = new ArrayList<>();

        readStoragePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readStoragePerm == DENIED)
            permslist.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        writeStoragePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writeStoragePerm == DENIED)
            permslist.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // Some of the permissions have not been granted
        if (permslist.size() > 0){
            // Convert the permsList into an array:
            String[] permsArray = new String[permslist.size()]; // ensure the sizes are the same
            permslist.toArray(permsArray);

            // Ask user for them:
            ActivityCompat.requestPermissions(this, permsArray, 1337);
        }
        // Let's make sure we have all the permissions, then start the Image Gallery:
        if (readStoragePerm == GRANTED && writeStoragePerm == GRANTED){
            // Let's open up the image gallery
            Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Star the activity for a result (picture)
            startActivityForResult(galleryIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        wineInfoTV.setVisibility(View.VISIBLE);
        db = new DBHelper(this);

        String wineInfo = "  " + Integer.toString(wine.getmVintage());
        wineInfo += "\n  " + wine.getmWinery();
        wineInfo += "\n  " + wine.getmVarietal();
        wineInfo += "\n  " + wine.getmVineyard() + "  ";

        wineInfoTV.setText(wineInfo);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)  {
            wine = wineArrayList.get(imageNum);

            imageUri = data.getData();


            getContentResolver().takePersistableUriPermission
                    (imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


            wineImageView.setImageURI(imageUri);
            wine.setmImageUri(imageUri);


            updateWineList.add(wine);


            db.updateWine(wine);
            wineList.clear();
            wineList = db.getAllWines();


            Log.i(TAG, "Showing all wines:");
            for (Wine w: wineList)
               Log.i(TAG, w.toString());


            Log.i(TAG, "Showing imageUri");
            Log.i(TAG, imageUri.toString());

            Log.i(TAG, "Showing imageUri");
            Log.i(TAG, imageUri.toString());

            /**
            Log.i(TAG, "Showing Permissions:");
            for (String s: permsList)
                Log.i(TAG, s);
             */

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

    public static Uri getUriFromResource(Context context, int resId){
        Resources res=context.getResources();
        // Build a String in the form:
        // android.resources://edu.orangecoastcollege.cs273.petprotector/drawable/none
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE+"://"
        +res.getResourcePackageName(resId)+"/"
        +res.getResourceTypeName(resId)+"/"
        +res.getResourceEntryName(resId);

        // Parse the String in order to  construct a URI
        return Uri.parse(uri);
    }

    public void endIt() {
       /**db = new DBHelper(this);
       for (Wine w: updateWineList)
           db.updateWine(w);

       wineList.clear();
       wineList = db.getAllWines();*/

       Log.i(TAG, "Showing all updated Wine List:");
       for (Wine w: updateWineList)
            Log.i(TAG, w.toString());

        Log.i(TAG, "Showing imageUri");
        Log.i(TAG, imageUri.toString());



        this.finish();
    }
}

/**
    // Let's open up the image gallery
    //Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    Intent galleryIntent = new Intent(Intent.ACTION_PICK);


    ContentValues values = new ContentValues(1);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");

                    Uri fileUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    //Uri uri = FileProvider.getUriForFile(this, getPackageName() + Configs.FILE_PROVIDER_NAME, values);

                    galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);


                    galleryIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    galleryIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                    // Star the activity for a result (picture)
                    startActivityForResult(galleryIntent, 1);
 */

/**
 // Let's make sure we have all the permissions, then start the Image Gallery:
 if (/*hasCameraPerm == GRANTED && /readStoragePerm == GRANTED && writeStoragePerm == GRANTED){
 // Let's open up the image gallery
 Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
 // Star the activity for a result (picture)
 if (galleryIntent.resolveActivity(getPackageManager()) != null) {
 Uri photoURI = null;
 try {
 //File photoFile = createImageFileWith();
 //path = photoFile.getAbsolutePath();
 photoURI = FileProvider.getUriForFile(AddPhotoActivity.this,
 getString(R.string.file_provider_authority),
 photoFile);

 } catch (IOException ex) {
 Log.e("TakePicture", ex.getMessage());
 }
 galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
 startActivityForResult(galleryIntent, 1);
 }
 */
