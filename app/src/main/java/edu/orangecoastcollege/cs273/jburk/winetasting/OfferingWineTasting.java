package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.net.Uri;

/**
 * Created by pjsda on 12/2/2017.
 */

public class OfferingWineTasting {
    private Wine mWine;          // 1
    private Tasting mTasting;    // 2

    // Wine
    private String mVarietal;    // 3
    private int mVintage;        // 4
    private String mWinery;      // 5
    private String mVineyard;    // 6
    private double mPrice;       // 7
    private Uri mImageUri;       // 8

    // Tasting
    private String mName;        // 9
    private String mDate;        // 10
    private String mLocation;    // 11

    public OfferingWineTasting(Wine wine, Tasting tasting, String varietal, int vintage,
                               String winery, String vineyard, double price, Uri imageUri,
                               String name, String date, String location)
    {
        mWine = wine;
        mTasting = tasting;
        mVarietal = varietal;
        mVintage = vintage;
        mWinery = winery;
        mVineyard = vineyard;
        mPrice = price;
        mImageUri = imageUri;
        mName = name;
        mDate = date;
        mLocation = location;
    }

    public Wine getWine() {
        return mWine;
    }

    public void setWine(Wine wine) {
        mWine = wine;
    }

    public Tasting getTasting() {
        return mTasting;
    }

    public void setTasting(Tasting tasting) {
        mTasting = tasting;
    }

    public String getVarietal() {
        return mVarietal;
    }

    public void setVarietal(String varietal) {
        mVarietal = varietal;
    }

    public int getVintage() {
        return mVintage;
    }

    public void setVintage(int vintage) {
        mVintage = vintage;
    }

    public String getWinery() {
        return mWinery;
    }

    public void setWinery(String winery) {
        mWinery = winery;
    }

    public String getVineyard() {
        return mVineyard;
    }

    public void setVineyard(String vineyard) {
        mVineyard = vineyard;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public Uri getImageUri() {
        return mImageUri;
    }

    public void setImageUri(Uri imageUri) {
        mImageUri = imageUri;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfferingWineTasting)) return false;

        OfferingWineTasting that = (OfferingWineTasting) o;

        if (getVintage() != that.getVintage()) return false;
        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (getWine() != null ? !getWine().equals(that.getWine()) : that.getWine() != null)
            return false;
        if (getTasting() != null ? !getTasting().equals(that.getTasting()) : that.getTasting() != null)
            return false;
        if (getVarietal() != null ? !getVarietal().equals(that.getVarietal()) : that.getVarietal() != null)
            return false;
        if (getWinery() != null ? !getWinery().equals(that.getWinery()) : that.getWinery() != null)
            return false;
        if (getVineyard() != null ? !getVineyard().equals(that.getVineyard()) : that.getVineyard() != null)
            return false;
        if (getImageUri() != null ? !getImageUri().equals(that.getImageUri()) : that.getImageUri() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null)
            return false;
        return getLocation() != null ? getLocation().equals(that.getLocation()) : that.getLocation() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getWine() != null ? getWine().hashCode() : 0;
        result = 31 * result + (getTasting() != null ? getTasting().hashCode() : 0);
        result = 31 * result + (getVarietal() != null ? getVarietal().hashCode() : 0);
        result = 31 * result + getVintage();
        result = 31 * result + (getWinery() != null ? getWinery().hashCode() : 0);
        result = 31 * result + (getVineyard() != null ? getVineyard().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getImageUri() != null ? getImageUri().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OfferingWineTasting{" +
                "mWine=" + mWine +
                ", mTasting=" + mTasting +
                ", mVarietal='" + mVarietal + '\'' +
                ", mVintage=" + mVintage +
                ", mWinery='" + mWinery + '\'' +
                ", mVineyard='" + mVineyard + '\'' +
                ", mPrice=" + mPrice +
                ", mImageUri=" + mImageUri +
                ", mName='" + mName + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mLocation='" + mLocation + '\'' +
                '}';
    }
}