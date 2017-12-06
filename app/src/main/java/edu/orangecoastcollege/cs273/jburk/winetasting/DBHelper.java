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
 * The database - Stores Wines: id, varietal, vintage, winery, vineyard, and price; Flight: id,
 * flightA, flightB; [Top:], [Rating:];
 *
 * Created by pjsda on 11/10/2017.
 */
public class  DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    // Database contains multiple tables.
    static final String DATABASE_NAME = "WineTastingRating";
    private static final int DATABASE_VERSION = 3;

    // Wine Database
    private static final String WINE_TABLE = "Wines";
    private static final String WINE_KEY_FIELD_ID = "id";
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

    // Wine-Rating
    private static final String OFFERING_WINE_RATING_TABLE = "Offering_wine_rating_names";
    private static final String OFFERING_FIELD_WINE_NAME = "offering_wine";
    private static final String OFFERING_FIELD_TASTING_NAME ="offering_tasting";
    private static final String OFFERING_FIELD_TASTING_ID = "tasting_id";
    private static final String OFFERING_WINE_GROUP_ID = "wine_group_id";

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

/**
        newDB = "CREATE TABLE " + OFFERING_WINE_RATING_TABLE + "("
                + OFFERING_WINE_GROUP_ID = " INTEGER" + ")";
                + OFFERING_FIELD_WINE_NAME = " TEXT, "
                + OFFERING_FIELD_TASTING_NAME = " TEXT, "
                + OFFERING_FIELD_TASTING_ID = " TEXT, "
        sqLiteDatabase.execSQL(newDB);*/

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

    public Wine getWine(int id) {
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

    public Rating getRating(int id){
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
    *****************************************************************************************
    * CSV OPERATIONS                                                                        *
    * ***************************************************************************************
    */

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