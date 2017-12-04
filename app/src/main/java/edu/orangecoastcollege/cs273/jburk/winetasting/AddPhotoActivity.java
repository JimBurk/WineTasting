package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddPhotoActivity extends AppCompatActivity {

    private TextView numTextView;
    private ImageView wineImageView;
    private Uri imageUri;
    private int imageNum = 0;
    private long mId = 0l;

    // constants for permissions
    private static final int GRANTED = PackageManager.PERMISSION_GRANTED;
    private static final int DENIED = PackageManager.PERMISSION_DENIED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        Intent photoIntent = getIntent();
        String mIdString = photoIntent.getStringExtra("FirstWineID");
        // mId = Long.parseLong(mIdString);

        numTextView = (TextView) findViewById(R.id.numWinesTV);

        wineImageView = (ImageView) findViewById(R.id.wineBottleIV);

        wineImageView.setImageURI(getUriFromResource(this, R.drawable.wine_bottle));
    }

    public void selectWineImage(View v) {
        List<String> permsList = new ArrayList<>();

        // Check each permission individually
        int hasCameraPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (hasCameraPerm == DENIED)
            permsList.add(Manifest.permission.CAMERA);

        int readStoragePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readStoragePerm == DENIED)
            permsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        int writeStoragePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writeStoragePerm == DENIED)
            permsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permsList.size() > 0) {
            // Convert perms list into array
            String [] permsArray = new String[permsList.size()];
            permsList.toArray(permsArray);

            // Ask user for them
            ActivityCompat.requestPermissions(this, permsArray, 1337);
        }

        // Lets make sure we have all permissions, then start Image Gallery
        if (hasCameraPerm == GRANTED && readStoragePerm == GRANTED && writeStoragePerm == GRANTED) {
            // Open the Image Gallery
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mId = mId + imageNum;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)  {
            // data is from GalleryIntent
            imageUri = data.getData();
            wineImageView.setImageURI(imageUri);
        }
        imageNum++;
        if (imageNum > 9) {
            this.finish();
        }
        else {
            numTextView.setText("numImages = " + imageNum);
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
        // android.resources://"package"/dwawable/none;
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + res.getResourcePackageName(resId) + "/"
                +res.getResourceTypeName(resId) + "/"
                +res.getResourceEntryName(resId);

        // Parse the String
        return Uri.parse(uri);
    }
}
