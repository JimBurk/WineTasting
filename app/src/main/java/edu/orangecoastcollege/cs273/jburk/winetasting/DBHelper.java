package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


 /**
 * Created by pjsda on 11/10/2017.
 */

public class  DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    // Database contains multiple tables.
    static final String DATABASE_NAME = "WineTastingRating";
    private static final int DATABASE_VERSION = 8;

    // Wine Database
    private static final String WINE_TABLE = "Wines";
    private static final String WINE_KEY_FIELD_ID = "_id";
    private static final String WINE_FIELD_TASTE_GROUP = "taste_group";
    private static final String WINE_FIELD_VARIETAL = "varietal";
    private static final String WINE_FIELD_VINTAGE = "vintage";
    private static final String WINE_FIELD_WINERY = "winery";
    private static final String WINE_FIELD_VINEYARD = "vineyard";
    private static final String WINE_FIELD_PRICE = "price";
    private static final String WINE_FIELD_IMAGE_NAME = "image";

    // Rating Database
    private static final String RATING_TABLE = "Ratings";
    private static final String RATING_KEY_FIELD_ID = "_id";
    private static final String RATING_FIELD_TASTE_GROUP = "taste_group";
    private static final String RATING_FIELD_COLOR = "color";
    private static final String RATING_FIELD_AROMA = "aroma";
    private static final String RATING_FIELD_BODY = "body";
    private static final String RATING_FIELD_TASTE = "taste";
    private static final String RATING_FIELD_FINISH = "finish";
    private static final String RATING_FIELD_NOTES = "notes";

    // Tasting Database
    private static final String TASTING_TABLE = "Tastings";
    private static final String TASTING_KEY_FIELD_ID = "_id";
    private static final String TASTING_FIELD_NAME = "name";
    private static final String TASTING_FIELD_DATE = "date";
    private static final String TASTING_FIELD_LOCATION = "location";
    //private static final String TASTING_FIELD_WINES = "wines";

    // Grape Database
    private static final String GRAPE_TABLE = "Grapes";
    private static final String GRAPE_KEY_FIELD_ID = "_id";
    private static final String GRAPE_FIELD_NAME = "name";
    private static final String GRAPE_FIELD_SYNONYM_1 = "synonym1";
    private static final String GRAPE_FIELD_SYNONYM_2 = "synonym2";

    // Alternate Grape Name Database
    private static final String ALTERNATE_TABLE = "Alternate_names";
    private static final String ALTERNATE_KEY_FIELD_ID = "_id";
    private static final String ALTERNATE_FIELD_ALTERNATE_NAME = "alternate_name";
    private static final String ALTERNATE_FIELD_NAME = "name";

    // Rating-Tasting
    private static final String OFFERING_RATING_TASTING_TABLE = "Offering_rating_tasting";
    private static final String OFFERING_RATING_TASTING_TASTING_ID = "Offering_rating_tasting_tasting_id";
    private static final String OFFERING_RATING_TASTING_RATING_ID = "offering_rating_tasting_group_id";
    private static final String OFFERING_FIELD_RATING_TASTING_NAME = "offering_rating_tasting_name";
    private static final String OFFERING_FIELD_RATING_TASTING_DATE = "offering_rating_tasting_date";
    private static final String OFFERING_FIELD_RATING_TASTING_LOCATION = "offering_rating_tasting_location";
    private static final String OFFERING_FIELD_RATING_TASTING_COLOR = "offering_rating_tasting_color";
    private static final String OFFERING_FIELD_RATING_TASTING_AROMA = "offering_rating_tasting_aroma";
    private static final String OFFERING_FIELD_RATING_TASTING_BODY = "offering_rating_tasting_body";
    private static final String OFFERING_FIELD_RATING_TASTING_TASTE = "offering_rating_tasting_taste";
    private static final String OFFERING_FIELD_RATING_TASTING_FINISH = "offering_rating_tasting_finish";
    private static final String OFFERING_FIELD_RATING_TASTING_NOTES = "offering_rating_tasting_notes";

    // Wine-Rating
    private static final String OFFERING_WINE_RATING_TABLE = "Offering_wine_rating";
    private static final String OFFERING_WINE_RATING_WINE_ID = "offering_wine_rating_wine_id";              // 1
    private static final String OFFERING_WINE_RATING_RATING_ID = "offering_wine_rating_rating_id";          // 2
    private static final String OFFERING_FIELD_WINE_RATING_COLOR = "offering_wine_rating_color";            // 3
    private static final String OFFERING_FIELD_WINE_RATING_AROMA = "offering_wine_rating_aroma";            // 4
    private static final String OFFERING_FIELD_WINE_RATING_BODY = "offering_wine_rating_body";              // 5
    private static final String OFFERING_FIELD_WINE_RATING_TASTE = "offering_wine_rating_taste";            // 6
    private static final String OFFERING_FIELD_WINE_RATING_FINISH = "offering_wine_rating_finish";          // 7
    private static final String OFFERING_FIELD_WINE_RATING_NOTES = "offering_wine_rating_notes";            // 8
    private static final String OFFERING_FIELD_WINE_RATING_VARIETAL = "offering_wine_rating_varietal";      // 9
    private static final String OFFERING_FIELD_WINE_RATING_VINTAGE = "offering_wine_rating_vintage";        // 10
    private static final String OFFERING_FIELD_WINE_RATING_WINERY = "offering_wine_rating_winery";          // 11
    private static final String OFFERING_FIELD_WINE_RATING_VINEYARD = "offering_wine_rating_vineyard";      // 12
    private static final String OFFERING_FIELD_WINE_RATING_PRICE = "offering_wine_rating_price";            // 13
    private static final String OFFERING_FIELD_WINE_RATING_IMAGE_NAME = "offering_wine_rating_image_name";  // 14

    // Wine-Tasting
    private static final String OFFERING_WINE_TASTING_TABLE = "Offering_wine_tasting";
    private static final String OFFERING_WINE_TASTING_WINE_GROUP_ID = "Offering_wine_tasting_wine_group_id";
    private static final String OFFERING_WINE_TASTING_TASTING_ID = "Offering_wine_tasting_tasting_id";
    private static final String OFFERING_FIELD_WINE_TASTING_VARIETAL = "offering_wine_tasting_varietal";
    private static final String OFFERING_FIELD_WINE_TASTING_VINTAGE = "offering_wine_tasting_vintage";
    private static final String OFFERING_FIELD_WINE_TASTING_WINERY = "offering_wine_tasting_winery";
    private static final String OFFERING_FIELD_WINE_TASTING_VINEYARD = "offering_wine_tasting_vineyard";
    private static final String OFFERING_FIELD_WINE_TASTING_PRICE = "offering_wine_tasting_price";
    private static final String OFFERING_FIELD_WINE_TASTING_IMAGE_NAME = "offering_wine_tasting_image_name";
    private static final String OFFERING_FIELD_WINE_TASTING_NAME = "offering_wine_tasting_name";
    private static final String OFFERING_FIELD_WINE_TASTING_DATE = "offering_wine_tasting_date";
    private static final String OFFERING_FIELD_WINE_TASTING_LOCATION = "offering_wine_tasting_location";

    public DBHelper(Context context) {super (context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String newDB = "CREATE TABLE " + WINE_TABLE + "("
                + WINE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + WINE_FIELD_TASTE_GROUP + " INTEGER, "
                + WINE_FIELD_VARIETAL + " TEXT, "
                + WINE_FIELD_VINTAGE + " INTEGER, "
                + WINE_FIELD_WINERY + " TEXT, "
                + WINE_FIELD_VINEYARD + " TEXT, "
                + WINE_FIELD_PRICE + " REAL, "
                + WINE_FIELD_IMAGE_NAME + " TEXT" + ")";
        sqLiteDatabase.execSQL(newDB);

        newDB = "CREATE TABLE " + RATING_TABLE + "("
                + RATING_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + RATING_FIELD_TASTE_GROUP + " INTEGER, "
                + RATING_FIELD_COLOR + " REAL, "
                + RATING_FIELD_AROMA + " REAL, "
                + RATING_FIELD_BODY + " REAL, "
                + RATING_FIELD_TASTE + " REAL, "
                + RATING_FIELD_FINISH + " REAL, "
                + RATING_FIELD_NOTES + " TEXT" + ")";
        sqLiteDatabase.execSQL(newDB);

        newDB = "CREATE TABLE " + TASTING_TABLE + "("
                + TASTING_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + TASTING_FIELD_NAME + " TEXT, "
                + TASTING_FIELD_DATE + " TEXT, "
                + TASTING_FIELD_LOCATION + " TEXT" + ")";
        sqLiteDatabase.execSQL(newDB);

        newDB = "CREATE TABLE " + GRAPE_TABLE + "("
                + GRAPE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + GRAPE_FIELD_NAME + " TEXT, "
                + GRAPE_FIELD_SYNONYM_1 + " TEXT, "
                + GRAPE_FIELD_SYNONYM_2 + " TEXT" + ")";
        sqLiteDatabase.execSQL(newDB);

        newDB = "CREATE TABLE " + ALTERNATE_TABLE + "("
                + ALTERNATE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + ALTERNATE_FIELD_ALTERNATE_NAME + " TEXT, "
                + ALTERNATE_FIELD_NAME + " TEXT" + ")";
        sqLiteDatabase.execSQL(newDB);

        newDB = "CREATE TABLE " + OFFERING_RATING_TASTING_TABLE + "("
                + OFFERING_RATING_TASTING_TASTING_ID + " INTEGER, "
                + OFFERING_RATING_TASTING_RATING_ID + " INTEGER, "
                + OFFERING_FIELD_RATING_TASTING_NAME + " TEXT, "
                + OFFERING_FIELD_RATING_TASTING_DATE + " TEXT, "
                + OFFERING_FIELD_RATING_TASTING_LOCATION + " TEXT, "
                + OFFERING_FIELD_RATING_TASTING_COLOR + " REAL, "
                + OFFERING_FIELD_RATING_TASTING_AROMA + " REAL, "
                + OFFERING_FIELD_RATING_TASTING_BODY + " REAL, "
                + OFFERING_FIELD_RATING_TASTING_TASTE + " REAL, "
                + OFFERING_FIELD_RATING_TASTING_FINISH + " REAL,"
                + OFFERING_FIELD_RATING_TASTING_NOTES + " TEXT, "
                + "FOREIGN KEY(" + OFFERING_RATING_TASTING_RATING_ID + ") REFERENCES "
                + RATING_TABLE + "(" + RATING_FIELD_TASTE_GROUP + "), "
                + "FOREIGN KEY(" + OFFERING_RATING_TASTING_TASTING_ID + ") REFERENCES "
                + TASTING_TABLE + "(" + TASTING_KEY_FIELD_ID  + "))";
        sqLiteDatabase.execSQL(newDB);

        newDB = "CREATE TABLE " + OFFERING_WINE_RATING_TABLE + "("
                + OFFERING_WINE_RATING_WINE_ID + " INTEGER, "       // 1
                + OFFERING_WINE_RATING_RATING_ID + " INTEGER, "     // 2
                + OFFERING_FIELD_WINE_RATING_COLOR + " REAL, "      // 3
                + OFFERING_FIELD_WINE_RATING_AROMA + " REAL, "      // 4
                + OFFERING_FIELD_WINE_RATING_BODY + " REAL, "       // 5
                + OFFERING_FIELD_WINE_RATING_TASTE + " REAL, "      // 6
                + OFFERING_FIELD_WINE_RATING_FINISH + " REAL, "     // 7
                + OFFERING_FIELD_WINE_RATING_NOTES + " TEXT, "      // 8
                + OFFERING_FIELD_WINE_RATING_VARIETAL + " TEXT, "   // 9
                + OFFERING_FIELD_WINE_RATING_VINTAGE + " INTEGER, " // 10
                + OFFERING_FIELD_WINE_RATING_WINERY + " TEXT, "     // 11
                + OFFERING_FIELD_WINE_RATING_VINEYARD + " TEXT, "   // 12
                + OFFERING_FIELD_WINE_RATING_PRICE + " REAL, "      // 13
                + OFFERING_FIELD_WINE_RATING_IMAGE_NAME + " TEXT, "  // 14
                + "FOREIGN KEY(" + OFFERING_WINE_RATING_RATING_ID + ") REFERENCES "
                + RATING_TABLE + "(" + RATING_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + OFFERING_WINE_RATING_WINE_ID + ") REFERENCES "
                + WINE_TABLE + "(" + WINE_KEY_FIELD_ID  + "))";
        sqLiteDatabase.execSQL(newDB);

        newDB = "CREATE TABLE " + OFFERING_WINE_TASTING_TABLE + "("
                + OFFERING_WINE_TASTING_WINE_GROUP_ID + " INTEGER, "
                + OFFERING_WINE_TASTING_TASTING_ID + " INTEGER, "
                + OFFERING_FIELD_WINE_TASTING_VARIETAL + " TEXT, "
                + OFFERING_FIELD_WINE_TASTING_VINTAGE + " INTEGER, "
                + OFFERING_FIELD_WINE_TASTING_WINERY + " TEXT, "
                + OFFERING_FIELD_WINE_TASTING_VINEYARD + " TEXT, "
                + OFFERING_FIELD_WINE_TASTING_PRICE + " REAL, "
                + OFFERING_FIELD_WINE_TASTING_IMAGE_NAME + " TEXT, "
                + OFFERING_FIELD_WINE_TASTING_NAME + " TEXT, "
                + OFFERING_FIELD_WINE_TASTING_DATE + " INTEGER, "
                + OFFERING_FIELD_WINE_TASTING_LOCATION + "TEXT, "
                + "FOREIGN KEY(" + OFFERING_WINE_TASTING_WINE_GROUP_ID + ") REFERENCES "
                + WINE_TABLE + "(" + WINE_FIELD_TASTE_GROUP + "), "
                + "FOREIGN KEY(" + OFFERING_WINE_TASTING_TASTING_ID + ") REFERENCES "
                + TASTING_TABLE + "(" + TASTING_KEY_FIELD_ID + "))";
        sqLiteDatabase.execSQL(newDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int i,
                          int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WINE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TASTING_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RATING_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GRAPE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ALTERNATE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OFFERING_RATING_TASTING_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OFFERING_WINE_RATING_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OFFERING_WINE_TASTING_TABLE);

        onCreate(sqLiteDatabase);
    }

    /*
    *********************************
    *   WINE DATABASE OPERATIONS    *
    *********************************
    */

    public void addWine(Wine wine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WINE_FIELD_TASTE_GROUP, wine.getmTasteGroup());
        values.put(WINE_FIELD_VARIETAL, wine.getmVarietal());
        values.put(WINE_FIELD_VINTAGE, wine.getmVintage());
        values.put(WINE_FIELD_WINERY, wine.getmWinery());
        values.put(WINE_FIELD_VINEYARD, wine.getmVineyard());
        values.put(WINE_FIELD_PRICE, wine.getmPrice());
        values.put(WINE_FIELD_IMAGE_NAME, wine.getmImageUri().toString());

        long id = db.insert(WINE_TABLE, null, values);

        wine.setmId(id);

        db.close();
    }

    public ArrayList<Wine> getAllWines() {
        ArrayList<Wine> wineList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                WINE_TABLE,
                new String[]{WINE_KEY_FIELD_ID,
                        WINE_FIELD_TASTE_GROUP,
                        WINE_FIELD_VARIETAL,
                        WINE_FIELD_VINTAGE,
                        WINE_FIELD_WINERY,
                        WINE_FIELD_VINEYARD,
                        WINE_FIELD_PRICE,
                        WINE_FIELD_IMAGE_NAME},
                null,
                null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Wine wine =
                        new Wine(cursor.getLong(0),
                                cursor.getLong(1),
                                cursor.getString(2),
                                cursor.getInt(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getDouble(6),
                                Uri.parse(cursor.getString(7)));
                wineList.add(wine);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return wineList;
    }

    public void deleteWine(Wine wine) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(WINE_TABLE, WINE_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(wine.getmId())});
        db.close();
    }

    public void deleteAllWine() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WINE_TABLE, null, null);
        db.close();
    }

    public void updateWine(Wine wine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WINE_FIELD_TASTE_GROUP, wine.getmTasteGroup());
        values.put(WINE_FIELD_VARIETAL, wine.getmVarietal());
        values.put(WINE_FIELD_VINTAGE, wine.getmVintage());
        values.put(WINE_FIELD_WINERY, wine.getmWinery());
        values.put(WINE_FIELD_VINEYARD, wine.getmVineyard());
        values.put(WINE_FIELD_PRICE, wine.getmPrice());
        values.put(WINE_FIELD_IMAGE_NAME, wine.getmImageUri().toString());

        db.update(WINE_TABLE, values, WINE_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(wine.getmId())});
        db.close();
    }

    public Wine getWine(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                WINE_TABLE,
                new String[]{WINE_KEY_FIELD_ID,
                        WINE_FIELD_TASTE_GROUP,
                        WINE_FIELD_VARIETAL,
                        WINE_FIELD_VINTAGE,
                        WINE_FIELD_WINERY,
                        WINE_FIELD_VINEYARD,
                        WINE_FIELD_PRICE,
                        WINE_FIELD_IMAGE_NAME},
                WINE_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Wine wine = new Wine(
                cursor.getLong(0),
                cursor.getLong(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getDouble(6),
                Uri.parse(cursor.getString(7)));

        cursor.close();
        db.close();
        return wine;
    }

     /*
    *********************************
    *   RATING DATABASE OPERATIONS    *
    *********************************
    */

    public void addRating(Rating rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RATING_FIELD_COLOR, rating.getColor());
        values.put(RATING_FIELD_TASTE_GROUP, rating.getTasteGroup());
        values.put(RATING_FIELD_AROMA, rating.getAroma());
        values.put(RATING_FIELD_BODY, rating.getBody());
        values.put(RATING_FIELD_TASTE, rating.getTaste());
        values.put(RATING_FIELD_FINISH, rating.getFinish());
        values.put(RATING_FIELD_NOTES, rating.getNotes());

        long id = db.insert(RATING_TABLE, null, values);

        rating.setId(id);

        db.close();
    }

    public ArrayList<Rating> getAllRatings(){
        ArrayList<Rating> ratingList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                RATING_TABLE,
                new String[]{RATING_KEY_FIELD_ID,
                        RATING_FIELD_TASTE_GROUP,
                        RATING_FIELD_COLOR,
                        RATING_FIELD_AROMA,
                        RATING_FIELD_BODY,
                        RATING_FIELD_TASTE,
                        RATING_FIELD_FINISH,
                        RATING_FIELD_NOTES},
                null,
                null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Rating rating =
                        new Rating(cursor.getLong(0), // ID
                                cursor.getLong(1), // ID
                                cursor.getFloat(2), // color
                                cursor.getFloat(3), // aroma
                                cursor.getFloat(4), // body
                                cursor.getFloat(5), // taste
                                cursor.getFloat(6), // finish
                                cursor.getString(7)); // notes
                ratingList.add(rating);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return ratingList;
    }

    public void deleteRating(Rating rating){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(RATING_TABLE, RATING_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(rating.getId())});
        db.close();
    }

    public void deleteAllRating(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RATING_TABLE, null, null);
        db.close();
    }

    public void updateRating(Rating rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RATING_FIELD_TASTE_GROUP, rating.getTasteGroup());
        values.put(RATING_FIELD_COLOR, rating.getColor());
        values.put(RATING_FIELD_AROMA, rating.getAroma());
        values.put(RATING_FIELD_BODY, rating.getBody());
        values.put(RATING_FIELD_TASTE, rating.getTaste());
        values.put(RATING_FIELD_FINISH, rating.getFinish());
        values.put(RATING_FIELD_NOTES, rating.getNotes());

        db.update(RATING_TABLE, values, RATING_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(rating.getId())});
        db.close();
    }

    public Rating getRating(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                RATING_TABLE,
                new String[]{RATING_KEY_FIELD_ID,
                        RATING_FIELD_TASTE_GROUP,
                        RATING_FIELD_COLOR,
                        RATING_FIELD_AROMA,
                        RATING_FIELD_BODY,
                        RATING_FIELD_TASTE,
                        RATING_FIELD_FINISH,
                        RATING_FIELD_NOTES},
                RATING_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Rating rating = new Rating(
                cursor.getLong(0), // ID
                cursor.getLong(1), // TasteGroup ID
                cursor.getFloat(2), // color
                cursor.getFloat(3), // aroma
                cursor.getFloat(4), // body
                cursor.getFloat(5), // taste
                cursor.getFloat(6), // finish
                cursor.getString(7)); // notes

        cursor.close();
        db.close();
        return rating;
    }


    /*
    *********************************
    *   TASTING DATABASE OPERATIONS *
    *********************************
    */

    public void addTasting(Tasting tasting){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TASTING_FIELD_NAME, tasting.getName());
        values.put(TASTING_FIELD_DATE, tasting.getDate());
        values.put(TASTING_FIELD_LOCATION, tasting.getLocation());
        //values.put(TASTING_FIELD_WINES, tasting.getmWine());

        long id = db.insert(TASTING_TABLE, null, values);

        tasting.setId(id);

        db.close();
    }

    public ArrayList<Tasting> getAllTastings(){
        ArrayList<Tasting> tastingList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TASTING_TABLE,
                new String[]{TASTING_KEY_FIELD_ID,
                        TASTING_FIELD_NAME,
                        TASTING_FIELD_DATE,
                        TASTING_FIELD_LOCATION},
                null,
                null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Tasting tasting =
                        new Tasting(
                                cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3));
                tastingList.add(tasting);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return tastingList;
    }

    public void deleteTasting(Tasting tasting){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TASTING_TABLE, TASTING_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(tasting.getId())});
        db.close();
    }

    public void deleteAllTasting(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASTING_TABLE, null, null);
        db.close();
    }

    public void updateTasting(Tasting tasting){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TASTING_FIELD_NAME, tasting.getName());
        values.put(TASTING_FIELD_DATE, tasting.getDate());
        values.put(TASTING_FIELD_LOCATION, tasting.getLocation());

        db.update(TASTING_TABLE, values, TASTING_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(tasting.getId())});
        db.close();
    }

    public Tasting getTasting(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TASTING_TABLE,
                new String[]{TASTING_KEY_FIELD_ID,
                        TASTING_FIELD_NAME,
                        TASTING_FIELD_DATE,
                        TASTING_FIELD_LOCATION},
                TASTING_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Tasting tasting = new Tasting(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        cursor.close();
        db.close();
        return  tasting;
    }

    /*
    *********************************
    *   GRAPE DATABASE OPERATIONS *
    *********************************
    */

    public void addGrape(Grape grape) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WINE_FIELD_VARIETAL, grape.getmName());
        values.put(WINE_FIELD_VINTAGE, grape.getmSynonym1());
        values.put(WINE_FIELD_WINERY, grape.getmSynonym2());

        long id = db.insert(GRAPE_TABLE, null, values);
        grape.setmId(id);
        db.close();
    }

    public ArrayList<Grape> getAllGrapes() {
        ArrayList<Grape> grapeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                GRAPE_TABLE,
                new String[]{GRAPE_KEY_FIELD_ID,
                        GRAPE_FIELD_NAME,
                        GRAPE_FIELD_SYNONYM_1,
                        GRAPE_FIELD_SYNONYM_2},
                null,
                null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Grape grape =
                        new Grape(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3));
                grapeList.add(grape);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return grapeList;
    }

    public Grape getGrape(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                GRAPE_TABLE,
                new String[]{GRAPE_KEY_FIELD_ID,
                        GRAPE_FIELD_NAME,
                        GRAPE_FIELD_SYNONYM_1,
                        GRAPE_FIELD_SYNONYM_2},
                GRAPE_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Grape grape = new Grape(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        cursor.close();
        db.close();
        return grape;
    }


    /*
    **********************************************
    *   ALTERNATE GRAPE NAME DATABASE OPERATIONS *
    **********************************************
    */

    public void addAlternateGrapeName(AlternateGrapeNames altGrapeName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ALTERNATE_FIELD_ALTERNATE_NAME, altGrapeName.getmAltName());
        values.put(ALTERNATE_FIELD_NAME, altGrapeName.getmName());

        long id = db.insert(ALTERNATE_TABLE, null, values);
        altGrapeName.setmId(id  );
        db.close();
    }

    public ArrayList<AlternateGrapeNames> getAllAltNames() {
        ArrayList<AlternateGrapeNames> altNameList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                ALTERNATE_TABLE,
                new String[]{ALTERNATE_KEY_FIELD_ID,
                        ALTERNATE_FIELD_ALTERNATE_NAME,
                        ALTERNATE_FIELD_NAME},
                null,
                null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                AlternateGrapeNames altNames =
                        new AlternateGrapeNames(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2));
                getAllAltNames().add(altNames);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return altNameList;
    }

    public AlternateGrapeNames getAltName(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                ALTERNATE_TABLE,
                new String[]{ALTERNATE_KEY_FIELD_ID,
                        ALTERNATE_FIELD_ALTERNATE_NAME,
                        ALTERNATE_FIELD_NAME},
                ALTERNATE_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        AlternateGrapeNames altNames = new AlternateGrapeNames(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2));

        cursor.close();
        db.close();
        return altNames;
    }


    /*
    *****************************************************
    *   OFFERING RATING AND TASTING DATABASE OPERATIONS *
    *****************************************************
    */
    public void addRatingTastingOffering(OfferingRatingTasting offering){   // 10
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(OFFERING_RATING_TASTING_RATING_ID, offering.getmRating().getId());             // 0
        values.put(OFFERING_RATING_TASTING_TASTING_ID, offering.getmTasting().getId());           // 1
        values.put(OFFERING_FIELD_RATING_TASTING_NAME, offering.getmTasting().getName());         // 2
        values.put(OFFERING_FIELD_RATING_TASTING_DATE, offering.getmTasting().getName());         // 3
        values.put(OFFERING_FIELD_RATING_TASTING_LOCATION, offering.getmTasting().getLocation()); // 4
        values.put(OFFERING_FIELD_RATING_TASTING_COLOR, offering.getmRating().getColor());        // 5
        values.put(OFFERING_FIELD_RATING_TASTING_AROMA, offering.getmRating().getAroma());        // 6
        values.put(OFFERING_FIELD_RATING_TASTING_BODY, offering.getmRating().getBody());          // 7
        values.put(OFFERING_FIELD_RATING_TASTING_TASTE, offering.getmRating().getTaste());        // 8
        values.put(OFFERING_FIELD_RATING_TASTING_FINISH, offering.getmRating().getFinish());      // 9
        values.put(OFFERING_FIELD_RATING_TASTING_NOTES , offering.getmRating().getNotes());       // 10

        db.insert(OFFERING_RATING_TASTING_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }


     public ArrayList<OfferingRatingTasting> getAllRatingTastingOfferings() {
         ArrayList<OfferingRatingTasting> offeringsList = new ArrayList<>();
         SQLiteDatabase database = this.getReadableDatabase();
         //Cursor cursor = database.rawQuery(queryList, null);
         Cursor cursor = database.query(
                 OFFERING_RATING_TASTING_TABLE,
                 new String[]{OFFERING_RATING_TASTING_TASTING_ID, // 0 Taste id
                         OFFERING_RATING_TASTING_RATING_ID,       // 1 Rate id
                         OFFERING_FIELD_RATING_TASTING_NAME,      // 2 Taste name
                         OFFERING_FIELD_RATING_TASTING_DATE,      // 3 taste date
                         OFFERING_FIELD_RATING_TASTING_LOCATION,  // 4 taste location
                         OFFERING_FIELD_RATING_TASTING_COLOR,     // 5 rating color
                         OFFERING_FIELD_RATING_TASTING_AROMA,     // 6 rating aroma
                         OFFERING_FIELD_RATING_TASTING_BODY,      // 7 rating body
                         OFFERING_FIELD_RATING_TASTING_TASTE,     // 8 rating taste
                         OFFERING_FIELD_RATING_TASTING_FINISH,    // 9 rating finish
                         OFFERING_FIELD_RATING_TASTING_NOTES},    // 10 rating notes
                 null,
                 null,
                 null, null, null, null);

         //COLLECT EACH ROW IN THE TABLE
         if (cursor.moveToFirst()) {
             do {
                 Tasting tasting = getTasting(cursor.getLong(0));
                 Rating rating = getRating(cursor.getLong(1));
                 OfferingRatingTasting offering =
                         new OfferingRatingTasting(
                                 tasting,
                                 rating,
                                 cursor.getString(2),
                                 cursor.getString(3),
                                 cursor.getString(4),
                                 cursor.getFloat(5),
                                 cursor.getFloat(6),
                                 cursor.getFloat(7),
                                 cursor.getFloat(8),
                                 cursor.getFloat(9),
                                 cursor.getString(10));
                 offeringsList.add(offering);
             } while (cursor.moveToNext());
         }
         cursor.close();
         database.close();
         return offeringsList;
     }

     public void deleteRatingTastingOffering(OfferingRatingTasting offering) {
         SQLiteDatabase db = this.getWritableDatabase();

         // DELETE THE TABLE ROW
         db.delete(OFFERING_RATING_TASTING_TABLE, TASTING_KEY_FIELD_ID + " = ?",
                 new String[]{String.valueOf(offering.getmTasting().getId())});
         db.close();
     }

     public void deleteAllRatingTastingOfferings() {
         SQLiteDatabase db = this.getWritableDatabase();
         db.delete(OFFERING_RATING_TASTING_TABLE, null, null);
         db.close();
     }


     /*
    ***************************************************
    *   OFFERING WINE AND RATING DATABASE OPERATIONS  *
    ***************************************************
    */

    public void addRatingWineRating(OfferingWineRating offering) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(OFFERING_WINE_RATING_WINE_ID, offering.getmWine().getmId());                             // 1
        values.put(OFFERING_WINE_RATING_RATING_ID , offering.getmRating().getId());                         // 2
        values.put(OFFERING_FIELD_WINE_RATING_COLOR, offering.getmRating().getColor());                     // 3
        values.put(OFFERING_FIELD_WINE_RATING_AROMA, offering.getmRating().getAroma());                     // 4
        values.put(OFFERING_FIELD_WINE_RATING_BODY , offering.getmRating().getBody());                      // 5
        values.put(OFFERING_FIELD_WINE_RATING_TASTE, offering.getmRating().getTaste());                     // 6
        values.put(OFFERING_FIELD_WINE_RATING_FINISH, offering.getmRating().getFinish());                   // 7
        values.put(OFFERING_FIELD_WINE_RATING_NOTES, offering.getmRating().getNotes());                     // 8
        values.put(OFFERING_FIELD_WINE_RATING_VARIETAL, offering.getmWine().getmVarietal());                // 9
        values.put(OFFERING_FIELD_WINE_RATING_VINTAGE, offering.getmWine().getmVintage());                  // 10
        values.put(OFFERING_FIELD_WINE_RATING_WINERY, offering.getmWine().getmWinery());                    // 11
        values.put(OFFERING_FIELD_WINE_RATING_VINEYARD, offering.getmWine().getmVineyard());                // 12
        values.put(OFFERING_FIELD_WINE_RATING_PRICE, offering.getmWine().getmPrice());                      // 13
        values.put(OFFERING_FIELD_WINE_RATING_IMAGE_NAME, offering.getmWine().getmImageUri().toString());   // 14


        db.insert(OFFERING_WINE_RATING_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

     public ArrayList<OfferingWineRating> getAllOfferingWineRatings() {
         ArrayList<OfferingWineRating> offeringsList = new ArrayList<>();
         SQLiteDatabase database = this.getReadableDatabase();
         //Cursor cursor = database.rawQuery(queryList, null);
         Cursor cursor = database.query(
                 OFFERING_WINE_RATING_TABLE,
                 new String[]{
                         OFFERING_WINE_RATING_WINE_ID,           // 1
                         OFFERING_WINE_RATING_RATING_ID,         // 2
                         OFFERING_FIELD_WINE_RATING_COLOR,       // 3
                         OFFERING_FIELD_WINE_RATING_AROMA,       // 4
                         OFFERING_FIELD_WINE_RATING_BODY,        // 5
                         OFFERING_FIELD_WINE_RATING_TASTE,       // 6
                         OFFERING_FIELD_WINE_RATING_FINISH,      // 7
                         OFFERING_FIELD_WINE_RATING_NOTES,       // 8
                         OFFERING_FIELD_WINE_RATING_VARIETAL,    // 9
                         OFFERING_FIELD_WINE_RATING_VINTAGE,     // 10
                         OFFERING_FIELD_WINE_RATING_WINERY,      // 11
                         OFFERING_FIELD_WINE_RATING_VINEYARD,    // 12
                         OFFERING_FIELD_WINE_RATING_PRICE,       // 13
                         OFFERING_FIELD_WINE_RATING_IMAGE_NAME}, // 14
                 null,
                 null,
                 null, null, null, null);

         //COLLECT EACH ROW IN THE TABLE
         if (cursor.moveToFirst()) {
             do {
                 Wine wine = getWine(cursor.getLong(0));
                 Rating rating = getRating(cursor.getLong(1));
                 OfferingWineRating offering =
                         new OfferingWineRating(
                                 wine,                                      // 1
                                 rating,                                    // 2
                                 cursor.getFloat(2),              // 3
                                 cursor.getFloat(3),              // 4
                                 cursor.getFloat(4),              // 5
                                 cursor.getFloat(5),              // 6
                                 cursor.getFloat(6),              // 7
                                 cursor.getString(7),             // 8
                                 cursor.getString(8),             // 9
                                 cursor.getInt(9),                // 10
                                 cursor.getString(10),            // 11
                                 cursor.getString(11),            // 12
                                 cursor.getDouble(12),            // 13
                                 Uri.parse(cursor.getString(13)));// 14

                 offeringsList.add(offering);
             } while (cursor.moveToNext());
         }
         cursor.close();
         database.close();
         return offeringsList;
     }


     public void deleteWineRatingOffering(OfferingWineRating offering) {
         SQLiteDatabase db = this.getWritableDatabase();

         // DELETE THE TABLE ROW
         db.delete(OFFERING_WINE_RATING_TABLE, RATING_KEY_FIELD_ID + " = ?",
                 new String[]{String.valueOf(offering.getmRating().getId())});
         db.close();
     }

     public void deleteAllWineRatingofferings() {
         SQLiteDatabase db = this.getWritableDatabase();
         db.delete(OFFERING_WINE_RATING_TABLE, null, null);
         db.close();
     }


    /*
    ***************************************************
    *   OFFERING WINE AND TASTING DATABASE OPERATIONS *
    ***************************************************
    */
     public void addRatingWineTasting(OfferingWineTasting offering) {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();

         values.put(OFFERING_WINE_TASTING_WINE_GROUP_ID, offering.getTasting().getId());                   // 1
         values.put(OFFERING_WINE_TASTING_TASTING_ID, offering.getWine().getmId());                        // 2
         values.put(OFFERING_FIELD_WINE_TASTING_VARIETAL, offering.getWine().getmVarietal());              // 3
         values.put(OFFERING_FIELD_WINE_TASTING_VINTAGE, offering.getWine().getmVintage());                // 4
         values.put(OFFERING_FIELD_WINE_TASTING_WINERY, offering.getWine().getmWinery());                  // 5
         values.put(OFFERING_FIELD_WINE_TASTING_VINEYARD, offering.getWine().getmVineyard());              // 6
         values.put(OFFERING_FIELD_WINE_TASTING_PRICE, offering.getWine().getmPrice());                    // 7
         values.put(OFFERING_FIELD_WINE_TASTING_IMAGE_NAME, offering.getWine().getmImageUri().toString()); // 8
         values.put(OFFERING_FIELD_WINE_TASTING_NAME, offering.getTasting().getName());                    // 9
         values.put(OFFERING_FIELD_WINE_TASTING_DATE, offering.getTasting().getDate());                    // 10
         values.put(OFFERING_FIELD_WINE_TASTING_LOCATION, offering.getTasting().getLocation());            // 11

         db.insert(OFFERING_RATING_TASTING_TABLE, null, values);

         // CLOSE THE DATABASE CONNECTION
         db.close();
     }

     public ArrayList<OfferingWineTasting> getAllOfferingWineTasting() {
         ArrayList<OfferingWineTasting> offeringsList = new ArrayList<>();
         SQLiteDatabase database = this.getReadableDatabase();
         //Cursor cursor = database.rawQuery(queryList, null);
         Cursor cursor = database.query(
                 OFFERING_WINE_TASTING_TABLE,
                 new String[]{
                         OFFERING_WINE_TASTING_WINE_GROUP_ID,       // 1
                         OFFERING_WINE_TASTING_TASTING_ID,          // 2
                         OFFERING_FIELD_WINE_TASTING_VARIETAL,      // 3
                         OFFERING_FIELD_WINE_TASTING_VINTAGE ,      // 4
                         OFFERING_FIELD_WINE_TASTING_WINERY,        // 5
                         OFFERING_FIELD_WINE_TASTING_VINEYARD,      // 6
                         OFFERING_FIELD_WINE_TASTING_PRICE,         // 7
                         OFFERING_FIELD_WINE_TASTING_IMAGE_NAME,    // 8
                         OFFERING_FIELD_WINE_TASTING_NAME,          // 9
                         OFFERING_FIELD_WINE_TASTING_DATE,          // 10
                         OFFERING_FIELD_WINE_TASTING_LOCATION},     // 11
                 null,
                 null,
                 null, null, null, null);

         //COLLECT EACH ROW IN THE TABLE
         if (cursor.moveToFirst()) {
             do {
                 Wine wine = getWine(cursor.getLong(0));          // 1 Wine id
                 Tasting tasting = getTasting(cursor.getLong(1)); // 2 Tasting id
                 OfferingWineTasting offering =
                         new OfferingWineTasting(
                                 wine,
                                 tasting,
                                 cursor.getString(2),             // 3
                                 cursor.getInt(3),                // 4
                                 cursor.getString(4),             // 5
                                 cursor.getString(5),             // 6
                                 cursor.getDouble(6),             // 7
                                 Uri.parse(cursor.getString(7)),  // 8
                                 cursor.getString(8),             // 9
                                 cursor.getString(9),             // 10
                                 cursor.getString(10));           // 11

                 offeringsList.add(offering);
             } while (cursor.moveToNext());
         }
         cursor.close();
         database.close();
         return offeringsList;
     }


     public void deleteWineTastingOffering(OfferingWineTasting offering) {
         SQLiteDatabase db = this.getWritableDatabase();

         // DELETE THE TABLE ROW
         db.delete(OFFERING_WINE_TASTING_TABLE, OFFERING_WINE_TASTING_TASTING_ID + " = ?",
                 new String[]{String.valueOf(offering.getTasting().getId())});
         db.close();
     }

     public void deleteAllWineTastingOfferings() {
         SQLiteDatabase db = this.getWritableDatabase();
         db.delete(OFFERING_WINE_TASTING_TABLE, null, null);
         db.close();
     }


    /****************************************************************************************
    *****************************************************************************************
    * CSV OPERATIONS                                                                        *
    * ***************************************************************************************
    ****************************************************************************************/
    /*
    *********************************
    *   GRAPES - IMPORT FROM CSV    *
    *********************************
    */
    public boolean importGrapesFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 4) {
                    Log.d("Wine Tasting Notes", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                String synonym1 = fields[2].trim();
                String synonym2 = fields[3].trim();
                addGrape(new Grape(id, name, synonym1, synonym2));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
    ************************************************
    *   ALTERNATE GRAPE NAMES - IMPORT FROM CSV    *
    ************************************************
    */

    public boolean importAlternateGrapeNamesFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 3) {
                    Log.d("Wine Tasting Notes", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                String altName = fields[1].trim();
                String name = fields[2].trim();
                addAlternateGrapeName(new AlternateGrapeNames(id, altName, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}