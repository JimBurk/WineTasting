package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jimburk on 11/10/17.
 */

public class Rating implements Parcelable {
    private long mId;
    private int mFlight;
    private int mOrder;
    private float mColor;
    private float mAroma;
    private float mBody;
    private float mTaste;
    private float mFinish;
    private float mTotal;
    private int mRank;
    private String mNotes;

    public Rating(long id, int flight, int order, float color, float aroma, float body, float taste, float finish, float total, int rank, String notes) {
        mId = id;
        mFlight = flight;
        mOrder = order;
        mColor = color;
        mAroma = aroma;
        mBody = body;
        mTaste = taste;
        mFinish = finish;
        mTotal = total;
        mRank = rank;
        mNotes = notes;
    }

    public Rating(int flight, int order) {
        this(-1, flight, order, -1, -1, -1, -1, -1, -1, -1, "");
    }

    public long getId() {
        return mId;
    }

    public int getFlight() {
        return mFlight;
    }

    public int getOrder() {
        return mOrder;
    }

    public float getColor() {
        return mColor;
    }

    public float getAroma() {
        return mAroma;
    }

    public float getBody() {
        return mBody;
    }

    public float getTaste() {
        return mTaste;
    }

    public float getFinish() {
        return mFinish;
    }

    public int getRank() {
        return mRank;
    }

    public float getTotal() {
        return mTotal;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setFlight(int flight) {
        mFlight = flight;
    }

    public void setOrder(int order) {
        mOrder = order;
    }

    public void setColor(float color) {
        mColor = color;
    }

    public void setAroma(float aroma) {
        mAroma = aroma;
    }

    public void setBody(float body) {
        mBody = body;
    }

    public void setTaste(float taste) {
        mTaste = taste;
    }

    public void setRank(int rank) {mRank = rank; }

    public void setFinish(float finish) {
        mFinish = finish;
    }

    public void setTotal(float total) {
        mTotal = total;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public void calcTotal() {
        if ((getColor() >= 0) && (getAroma() >= 0) && (getBody() >= 0) && (getTaste() >= 0) && (getFinish() >= 0))
            setTotal(getColor() + getAroma() + getBody() + getTaste() + getFinish());
    }

    /**
     * Return 0 if the its a stand parcel, else if rsendign files
     * need to run file descriptors
     *
     * @return 0
     */
    @Override
    public int describeContents() {return 0;}

    /**
     * Writes all the member variables of th class to the parcel.
     * we specify data types
     *
     * @param parcel The package with details about the game.
     * @param i any custom flags (with files)
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mId);
        parcel.writeInt(mFlight);
        parcel.writeInt(mOrder);
        parcel.writeFloat(mColor);
        parcel.writeFloat(mAroma);
        parcel.writeFloat(mBody);
        parcel.writeFloat(mTaste);
        parcel.writeFloat(mFinish);
        parcel.writeFloat(mTotal);
        parcel.writeInt(mRank);
        parcel.writeString(mNotes);
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>(){
        /**
         * This method is used with Intents to crate new Wine objects.
         *
         * @param parcel The package with all the information for the Wine
         * @return The new Wine object
         */
        @Override
        public Rating createFromParcel(Parcel parcel) {
            return null;
        }

        /**
         * This method is used with JSON to create an array of Wine Objects.
         * @param size Of the JSON array (How many wines).
         * @return New array of Wines.
         */
        @Override
        public Rating[] newArray(int size) { return new Rating[size];
        }
    } ;
}
