package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This is the model for the rating class. A Rating has an id, and variables for rating Color, Aroma,
 * Body, Taste and Finish. The user can also write some notes on each wine.
 *
 * Created by jimburk on 11/10/17.
 */

public class Rating implements Parcelable {
    private long mId;
    private long mTasteGroup;
    private float mColor;
    private float mAroma;
    private float mBody;
    private float mTaste;
    private float mFinish;
    private String mNotes;

    /***
     * These are the constructors for the class. The first has all the data, while the second has none.
     * @param id
     * @param tasteGroup
     * @param color
     * @param aroma
     * @param body
     * @param taste
     * @param finish
     * @param notes
     */
    public Rating(long id, long tasteGroup, float color, float aroma, float body, float taste, float finish, String notes) {
        mId = id;
        mTasteGroup = tasteGroup;
        mColor = color;
        mAroma = aroma;
        mBody = body;
        mTaste = taste;
        mFinish = finish;
        mNotes = notes;
    }

    /**public Rating() {mTasteGroup = 0L;  mColor = 0.0F;  mAroma = 0.0F; mBody = 0.0F; mTaste = 0.0F; mFinish = 0.0F; mNotes ="";}*/

    public Rating() {this(-1, -1, -1, -1, -1, -1, -1, "");}

    private Rating(Parcel parcel){
        mId = parcel.readLong();
        mTasteGroup = parcel.readLong();
        mColor = parcel.readFloat();
        mAroma = parcel.readFloat();
        mBody = parcel.readFloat();
        mTaste = parcel.readFloat();
        mFinish = parcel.readFloat();
        mNotes = parcel.readString();
    }

    public long getId() {
        return mId;
    }

    public long getTasteGroup() {return mTasteGroup;}

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

    public String getNotes() {
        return mNotes;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setTasteGroup(long tasteGroup) {mTasteGroup = tasteGroup;}

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

    public void setFinish(float finish) {
        mFinish = finish;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public float calcTotal() {
        if ((getColor() >= 0) && (getAroma() >= 0) && (getBody() >= 0) && (getTaste() >= 0) && (getFinish() >= 0)) {
            return (getColor() + getAroma() + getBody() + getTaste() + getFinish());
        }
        else {
            return 0.0f;
        }
    }

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof Rating)) return false;

        Rating mmRating = (Rating) mo;

        if (mId != mmRating.mId) return false;
        if (mTasteGroup != mmRating.mTasteGroup) return false;
        if (Float.compare(mmRating.mColor, mColor) != 0) return false;
        if (Float.compare(mmRating.mAroma, mAroma) != 0) return false;
        if (Float.compare(mmRating.mBody, mBody) != 0) return false;
        if (Float.compare(mmRating.mTaste, mTaste) != 0) return false;
        if (Float.compare(mmRating.mFinish, mFinish) != 0) return false;
        return mNotes != null ? mNotes.equals(mmRating.mNotes) : mmRating.mNotes == null;
    }

    @Override
    public int hashCode() {
        int mresult = (int) (mId ^ (mId >>> 32));
        mresult = 31 * mresult + (int) (mTasteGroup ^ (mTasteGroup >>> 32));
        mresult = 31 * mresult + (mColor != +0.0f ? Float.floatToIntBits(mColor) : 0);
        mresult = 31 * mresult + (mAroma != +0.0f ? Float.floatToIntBits(mAroma) : 0);
        mresult = 31 * mresult + (mBody != +0.0f ? Float.floatToIntBits(mBody) : 0);
        mresult = 31 * mresult + (mTaste != +0.0f ? Float.floatToIntBits(mTaste) : 0);
        mresult = 31 * mresult + (mFinish != +0.0f ? Float.floatToIntBits(mFinish) : 0);
        mresult = 31 * mresult + (mNotes != null ? mNotes.hashCode() : 0);
        return mresult;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "mId=" + mId +
                ", mTasteGroup=" + mTasteGroup +
                ", mColor=" + mColor +
                ", mAroma=" + mAroma +
                ", mBody=" + mBody +
                ", mTaste=" + mTaste +
                ", mFinish=" + mFinish +
                ", mNotes='" + mNotes + '\'' +
                '}';
    }

    /**
     * Return 0 if the its a stand parcel, else if resending files
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
        parcel.writeLong(mTasteGroup);
        parcel.writeFloat(mColor);
        parcel.writeFloat(mAroma);
        parcel.writeFloat(mBody);
        parcel.writeFloat(mTaste);
        parcel.writeFloat(mFinish);
        parcel.writeString(mNotes);
    }

    public static final Parcelable.Creator<Rating> CREATOR = new Parcelable.Creator<Rating>(){
        /**
         * This method is used with Intents to crate new Wine objects.
         *
         * @param parcel The package with all the information for the Wine
         * @return The new Wine object
         */
        @Override
        public Rating createFromParcel(Parcel parcel) {
            return new Rating(parcel);
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
