package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * The database - Stores Wines: id, varietal, vintage, winery, vineyard, and price; Flight: id,
 * flightA, flightB; [Top:], [Rating:];
 *
 * Created by pjsda on 11/10/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    // Database contains multiple tables.
    static final String DATABASE_NAME = "WineTastingRating";
    private static final int DATABASE_VERSION = 1;

    // Wine Database
    private static final String WINE_TABLE = "Wines";
    private static final String WINE_KEY_FIELD_ID = "id";
    private static final String FIELD_VARIETAL = "varietal";
    private static final String FIELD_VINTAGE = "vintage";
    private static final String FIELD_WINERY = "winery";
    private static final String FIELD_VINEYARD = "vineyard";
    private static final String FIELD_PRICE = "price";

    // Rating Database
    private static final String FLIGHT_TABLE = "Flights";
    private static final String FLIGHT_KEY_FIELD_ID = "id";
    private static final String FIELD_FLIGHT_A = "flightA";
    private static final String FIELD_FLIGHT_B = "flightB";

    public DBHelper(Context context) {super (context, DATABASE_NAME, null, DATABASE_VERSION);}


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String newDB = "CREATE TABLE " + WINE_TABLE + "("
                + WINE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_VARIETAL + " TEXT, "
                + FIELD_VINTAGE + " INTEGER, "
                + FIELD_WINERY + " TEXT, "
                + FIELD_VINEYARD + " TEXT, "
                + FIELD_PRICE + " REAL" + ")";
        sqLiteDatabase.execSQL(newDB);

        newDB = "CREATE TABLE " + FLIGHT_TABLE + "("
                + FLIGHT_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_FLIGHT_A + " BLOB, "
                + FIELD_FLIGHT_B + " BLOB" + ")";
        sqLiteDatabase.execSQL(newDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int i,
                          int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WINE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FLIGHT_TABLE);
        onCreate(sqLiteDatabase);
    }

    /*
    *********************************
    *   WINE DATABASE OPERATIONS    *
    *********************************
    */
    public void addWine(Wine wine){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_VARIETAL, wine.getmVarietal());
        values.put(FIELD_VINTAGE, wine.getmVintage());
        values.put(FIELD_WINERY, wine.getmWinery());
        values.put(FIELD_VINEYARD, wine.getmVineyard());
        values.put(FIELD_PRICE, wine.getmPrice());

        long id = db.insert(WINE_TABLE, null, values);

        wine.setmId(id);

        db.close();
    }

    public ArrayList<Wine> getAllWines(){
        ArrayList<Wine> wineList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                WINE_TABLE,
                new String[]{WINE_KEY_FIELD_ID, FIELD_VARIETAL, FIELD_VINTAGE, FIELD_WINERY,
                        FIELD_VINEYARD, FIELD_PRICE},
                null,
                null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Wine wine =
                        new Wine(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getDouble(5));
                wineList.add(wine);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return wineList;
    }

    public void deleteWine(Wine wine){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(WINE_TABLE, WINE_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(wine.getmId())});
        db.close();
    }

    public void deleteAllWine(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WINE_TABLE, null, null);
        db.close();
    }

    public void updateWine(Wine wine){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_VARIETAL, wine.getmVarietal());
        values.put(FIELD_VINTAGE, wine.getmVintage());
        values.put(FIELD_WINERY, wine.getmWinery());
        values.put(FIELD_VINEYARD, wine.getmVineyard());
        values.put(FIELD_PRICE, wine.getmPrice());

        db.update(WINE_TABLE, values, WINE_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(wine.getmId())});
        db.close();
    }

    public Wine getWine(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                WINE_TABLE,
                new String[]{WINE_KEY_FIELD_ID, FIELD_VARIETAL, FIELD_VINTAGE, FIELD_WINERY,
                        FIELD_VINEYARD, FIELD_PRICE},
                WINE_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Wine wine = new Wine(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getDouble(5));

        cursor.close();
        db.close();
        return wine;
    }


    /*
    *********************************
    *   FLIGHT DATABASE OPERATIONS    *
    *********************************
    */

    /**
    public void addFlight(Flight flight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_FLIGHT_A, String.valueOf(flight.getmFlightA().getmId()));
        values.put(FIELD_FLIGHT_B, flight.getmFlightB().getmId());

        long id = db.insert(FLIGHT_TABLE, null, values);

        flight.setmId(id);

        db.close();
    }

    public ArrayList<Flight> getAllFlights() {
        ArrayList<Flight> flightsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                FLIGHT_TABLE,
                new String[]{FLIGHT_KEY_FIELD_ID, FIELD_FLIGHT_A, FIELD_FLIGHT_B},
                    null,
                    null,
                    null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Flight flight =
                        new Flight(cursor.getLong(0),
                                cursor.getString(getWine(1).getmId())),
                                cursor.getLong(getWine(2)));
                flightsList.add(flight);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return flightsList;
    }

    public void deleteAllFlights(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FLIGHT_TABLE, null, null);
        db.close();
    }

    public void updateFlight(Flight flight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_FLIGHT_A, flight.getmFlightA());
        values.put(FIELD_FLIGHT_B, flight.getmFlightB());

        db.update(FLIGHT_TABLE, values, FLIGHT_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(flight.getmId())});
        db.close();
    }

    public Flight getFlight(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                FLIGHT_TABLE,
                new String[]{FLIGHT_KEY_FIELD_ID, FIELD_FLIGHT_A, FIELD_FLIGHT_B},
                FLIGHT_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Flight flight = new Flight(
                cursor.getLong(0),
                cursor.getLong(1),
                cursor.getString(2));
        db.close();
        cursor.close();
        )
    }
    **/

}
