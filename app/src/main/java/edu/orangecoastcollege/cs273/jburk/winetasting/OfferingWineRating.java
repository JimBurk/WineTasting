package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.net.Uri;

/**
 * Created by pjsda on 12/2/2017.
 */

public class OfferingWineRating {
    private Wine mWine;
    private Rating mRating;

    // Rating
    private float mColor;
    private float mAroma;
    private float mBody;
    private float mTaste;
    private float mFinish;
    private String mNotes;

    // Wine
    private String mVarietal;
    private int mVintage;
    private String mWinery;
    private String mVineyard;
    private double mPrice;
private Uri mImageUri;

    public OfferingWineRating(Wine mWine, Rating mRating, float mColor, float mAroma, float mBody,
                              float mTaste, float mFinish, String mNotes, String mVarietal, int mVintage,
                              String mWinery, String mVineyard, double mPrice, Uri mImageUri)
    {
        this.mWine = mWine;             // 1
        this.mRating = mRating;         // 2
        this.mColor = mColor;           // 3
        this.mAroma = mAroma;           // 4
        this.mBody = mBody;             // 5
        this.mTaste = mTaste;           // 6
        this.mFinish = mFinish;         // 7
        this.mNotes = mNotes;           // 8
        this.mVarietal = mVarietal;     // 9
        this.mVintage = mVintage;       // 10
        this.mWinery = mWinery;         // 11
        this.mVineyard = mVineyard;     // 12
        this.mPrice = mPrice;           // 13
        this.mImageUri = mImageUri;     // 14
    }

    public OfferingWineRating(Wine mWine, Rating mRating) {
        this.mWine = mWine;                     // 1
        this.mRating = mRating;                 // 2
        this.mColor = mRating.getColor();       // 3
        this.mAroma = mRating.getAroma();       // 4
        this.mBody = mRating.getBody();         // 5
        this.mTaste = mRating.getTaste();       // 6
        this.mFinish = mRating.getFinish();     // 7
        this.mNotes = mRating.getNotes();       // 8
        this.mVarietal = mWine.getmVarietal();  // 9
        this.mVintage = mWine.getmVintage();    // 10
        this.mWinery = mWine.getmWinery();      // 11
        this.mVineyard = mWine.getmVineyard();  // 12
        this.mPrice = mWine.getmPrice();        // 13
        this.mImageUri = mWine.getmImageUri();  // 14
    }

    public Wine getmWine() {
        return mWine;
    }

    public void setmWine(Wine mWine) {
        this.mWine = mWine;
    }

    public Rating getmRating() {
        return mRating;
    }

    public void setmRating(Rating mRating) {
        this.mRating = mRating;
    }

    public float getmColor() {
        return mColor;
    }

    public void setmColor(float mColor) {
        this.mColor = mColor;
    }

    public float getmAroma() {
        return mAroma;
    }

    public void setmAroma(float mAroma) {
        this.mAroma = mAroma;
    }

    public float getmBody() {
        return mBody;
    }

    public void setmBody(float mBody) {
        this.mBody = mBody;
    }

    public float getmTaste() {
        return mTaste;
    }

    public void setmTaste(float mTaste) {
        this.mTaste = mTaste;
    }

    public float getmFinish() {
        return mFinish;
    }

    public void setmFinish(float mFinish) {
        this.mFinish = mFinish;
    }

    public String getmNotes() {
        return mNotes;
    }

    public void setmNotes(String mNotes) {
        this.mNotes = mNotes;
    }

    public String getmVarietal() {
        return mVarietal;
    }

    public void setmVarietal(String mVarietal) {
        this.mVarietal = mVarietal;
    }

    public int getmVintage() {
        return mVintage;
    }

    public void setmVintage(int mVintage) {
        this.mVintage = mVintage;
    }

    public String getmWinery() {
        return mWinery;
    }

    public void setmWinery(String mWinery) {
        this.mWinery = mWinery;
    }

    public String getmVineyard() {
        return mVineyard;
    }

    public void setmVineyard(String mVineyard) {
        this.mVineyard = mVineyard;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public Uri getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof OfferingWineRating)) return false;

        OfferingWineRating mthat = (OfferingWineRating) mo;

        if (Float.compare(mthat.getmColor(), getmColor()) != 0) return false;
        if (Float.compare(mthat.getmAroma(), getmAroma()) != 0) return false;
        if (Float.compare(mthat.getmBody(), getmBody()) != 0) return false;
        if (Float.compare(mthat.getmTaste(), getmTaste()) != 0) return false;
        if (Float.compare(mthat.getmFinish(), getmFinish()) != 0) return false;
        if (getmVintage() != mthat.getmVintage()) return false;
        if (Double.compare(mthat.getmPrice(), getmPrice()) != 0) return false;
        if (getmWine() != null ? !getmWine().equals(mthat.getmWine()) : mthat.getmWine() != null)
            return false;
        if (getmRating() != null ? !getmRating().equals(mthat.getmRating()) : mthat.getmRating() != null)
            return false;
        if (getmNotes() != null ? !getmNotes().equals(mthat.getmNotes()) : mthat.getmNotes() != null)
            return false;
        if (getmVarietal() != null ? !getmVarietal().equals(mthat.getmVarietal()) : mthat.getmVarietal() != null)
            return false;
        if (getmWinery() != null ? !getmWinery().equals(mthat.getmWinery()) : mthat.getmWinery() != null)
            return false;
        if (getmVineyard() != null ? !getmVineyard().equals(mthat.getmVineyard()) : mthat.getmVineyard() != null)
            return false;
        return getmImageUri() != null ? getmImageUri().equals(mthat.getmImageUri()) : mthat.getmImageUri() == null;
    }

    @Override
    public int hashCode() {
        int mresult;
        long mtemp;
        mresult = getmWine() != null ? getmWine().hashCode() : 0;
        mresult = 31 * mresult + (getmRating() != null ? getmRating().hashCode() : 0);
        mresult = 31 * mresult + (getmColor() != +0.0f ? Float.floatToIntBits(getmColor()) : 0);
        mresult = 31 * mresult + (getmAroma() != +0.0f ? Float.floatToIntBits(getmAroma()) : 0);
        mresult = 31 * mresult + (getmBody() != +0.0f ? Float.floatToIntBits(getmBody()) : 0);
        mresult = 31 * mresult + (getmTaste() != +0.0f ? Float.floatToIntBits(getmTaste()) : 0);
        mresult = 31 * mresult + (getmFinish() != +0.0f ? Float.floatToIntBits(getmFinish()) : 0);
        mresult = 31 * mresult + (getmNotes() != null ? getmNotes().hashCode() : 0);
        mresult = 31 * mresult + (getmVarietal() != null ? getmVarietal().hashCode() : 0);
        mresult = 31 * mresult + getmVintage();
        mresult = 31 * mresult + (getmWinery() != null ? getmWinery().hashCode() : 0);
        mresult = 31 * mresult + (getmVineyard() != null ? getmVineyard().hashCode() : 0);
        mtemp = Double.doubleToLongBits(getmPrice());
        mresult = 31 * mresult + (int) (mtemp ^ (mtemp >>> 32));
        mresult = 31 * mresult + (getmImageUri() != null ? getmImageUri().hashCode() : 0);
        return mresult;
    }

    @Override
    public String toString() {
        return "OfferingWineRating{" +
                "mWine=" + mWine +
                ", mRating=" + mRating +
                ", mColor=" + mColor +
                ", mAroma=" + mAroma +
                ", mBody=" + mBody +
                ", mTaste=" + mTaste +
                ", mFinish=" + mFinish +
                ", mNotes='" + mNotes + '\'' +
                ", mVarietal='" + mVarietal + '\'' +
                ", mVintage=" + mVintage +
                ", mWinery='" + mWinery + '\'' +
                ", mVineyard='" + mVineyard + '\'' +
                ", mPrice=" + mPrice +
                ", mImageUri=" + mImageUri +
                '}';
    }
}

/**
 public OfferingWineRating(Wine wine, Rating rating) {
 this.mWine = wine;
 this.mRating = rating;

 // Rating
 this.mColor = mRating.getColor();
 this.mAroma = mRating.getAroma();
 this.mBody = mRating.getBody();
 this.mTaste = mRating.getTaste();
 this.mFinish = mRating.getFinish();
 this.mNotes = mRating.getNotes();

 // Wine
 this.mVarietal = mWine.getmVarietal();
 this.mVintage = mWine.getmVintage();
 this.mWinery = mWine.getmWinery();
 this.mVineyard = mWine.getmVineyard();
 this.mPrice = mWine.getmPrice();
 this.mImageUri = mWine.getmImageUri();
 }*/