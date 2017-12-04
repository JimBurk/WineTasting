package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URI;

/**
 * The <code>Wine</code> class maintains information about wine, including its id number, varietal,
 * vintage, winery, vineyard, and price.
 *
 * Created by pjsda on 11/10/2017.
 */

public class Wine implements Parcelable{

    private Long mId;
    private String mVarietal;
    private int mVintage;
    private String mWinery;
    private String mVineyard;
    private double mPrice;
    private Uri mImageUri;

    /**
     * Default Constructor, Creates a new <code>Wine</code> from its id, varietal, vintage, winery,
     * vineyard, and price.
     */
    public Wine() {mVarietal =  ""; mVintage = 0; mWinery = ""; mVineyard = ""; mPrice = 0.0;}

    /**
     * Creates a new <code>Wine</code> from its id, varietal, vintage, winery, vineyard, and price.
     * @param mVarietal The wine varietal.
     * @param mVintage The wine vintage.
     * @param mWinery The winery that created the wine.
     * @param mVineyard The wine vineyard.
     * @param mPrice The wine price.
     */
    public Wine(String mVarietal, int mVintage, String mWinery, String mVineyard, double mPrice, Uri imageUri) {
        this.mVarietal = mVarietal;
        this.mVintage = mVintage;
        this.mWinery = mWinery;
        this.mVineyard = mVineyard;
        this.mPrice = mPrice;
        this.mImageUri = imageUri;
    }

    /**
     * Creates a new <code>Wine</code> from its id, varietal, vintage, winery, vineyard, price and Uri.
     * @param mId The wine id.
     * @param mVarietal The wine varietal.
     * @param mVintage The wine vintage.
     * @param mWinery The winery that created the wine.
     * @param mVineyard The wine vineyard.
     * @param mPrice The wine price.
     */
    public Wine(Long mId, String mVarietal, int mVintage, String mWinery, String mVineyard, double mPrice, Uri imageUri) {
        this.mId = mId;
        this.mVarietal = mVarietal;
        this.mVintage = mVintage;
        this.mWinery = mWinery;
        this.mVineyard = mVineyard;
        this.mPrice = mPrice;
        this.mImageUri = imageUri;
    }

    /**
     * Gets the unique id of the <code>Wine</code>.
     * @return The unique id.
     */
    public Long getmId() {
        return mId;
    }

    /**
     * Sets the unique id of the <code>Wine</code>
     * @param mId The unique id.
     */
    public void setmId(Long mId) {
        this.mId = mId;
    }

    /**
     * Gets the varietal of the <code>Wine</code>.
     * @return The varietal.
     */
    public String getmVarietal() {
        return mVarietal;
    }

    /**
     * Sets the varietal of the <code>Wine</code>.
     * @param mVarietal The varietal.
     */
    public void setmVarietal(String mVarietal) {
        this.mVarietal = mVarietal;
    }

    /**
     * Gets the vintage of the <code>Wine</code>.
     * @return The vintage.
     */
    public int getmVintage() {
        return mVintage;
    }

    /**
     * Sets the vintage of the <code>Wine</code>.
     * @param mVintage The vintage.
     */
    public void setmVintage(int mVintage) {
        this.mVintage = mVintage;
    }

    /**
     * Gets the winery of the <code>Wine</code>.
     * @return The winery.
     */
    public String getmWinery() {
        return mWinery;
    }

    /**
     * Sets the winery of the <code>Wine</code>.
     * @param mWinery The winery.
     */
    public void setmWinery(String mWinery) {
        this.mWinery = mWinery;
    }

    /**
     * Gets the vineyard of the <code>Wine</code>.
     * @return The vineyard of the wine.
     */
    public String getmVineyard() {
        return mVineyard;
    }

    /**
     * Sets vineyard of the <code>Wine</code>.
     * @param mVineyard The vineyard.
     */
    public void setmVineyard(String mVineyard) {
        this.mVineyard = mVineyard;
    }

    /**
     * Gets the price of the <code>Wine</code>.
     * @return The price.
     */
    public double getmPrice() {
        return mPrice;
    }

    /**
     * Sets the price of the <code>Wine</code>.
     * @param mPrice The price.
     */
    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wine)) return false;

        Wine winem = (Wine) o;

        if (getmVintage() != winem.getmVintage()) return false;
        if (Double.compare(winem.getmPrice(), getmPrice()) != 0) return false;
        if (getmId() != null ? !getmId().equals(winem.getmId()) : winem.getmId() != null)
            return false;
        if (getmVarietal() != null ? !getmVarietal().equals(winem.getmVarietal()) : winem.getmVarietal() != null)
            return false;
        if (getmWinery() != null ? !getmWinery().equals(winem.getmWinery()) : winem.getmWinery() != null)
            return false;
        return getmVineyard() != null ? getmVineyard().equals(winem.getmVineyard()) : winem.getmVineyard() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getmId() != null ? getmId().hashCode() : 0;
        result = 31 * result + (getmVarietal() != null ? getmVarietal().hashCode() : 0);
        result = 31 * result + getmVintage();
        result = 31 * result + (getmWinery() != null ? getmWinery().hashCode() : 0);
        result = 31 * result + (getmVineyard() != null ? getmVineyard().hashCode() : 0);
        temp = Double.doubleToLongBits(getmPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Wine{" +
                "mId=" + mId +
                ", mVarietal='" + mVarietal + '\'' +
                ", mVintage=" + mVintage +
                ", mWinery='" + mWinery + '\'' +
                ", mVineyard='" + mVineyard + '\'' +
                ", mPrice=" + mPrice +
                '}';
    }

    /**
     * Return 0 if the its a stand parcel, else if resendign files
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
     * @param parcel The package with details about the wine.
     * @param i any custom flags (with files)
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mId);
        parcel.writeString(mVarietal);
        parcel.writeInt(mVintage);
        parcel.writeString(mWinery);
        parcel.writeString(mVineyard);
        parcel.writeDouble(mPrice);
    }

    public static final Creator<Wine> CREATOR = new Creator<Wine>(){
        /**
         * This method is used with Intents to crate new Wine objects.
         *
         * @param parcel The package with all the information for the Wine
         * @return The new Wine object
         */
        @Override
        public Wine createFromParcel(Parcel parcel) {
            return null;
        }

        /**
         * This method is used with JSON to create an array of Wine Objects.
         * @param size Of the JSON array (How many wines).
         * @return New array of Wines.
         */
        @Override
        public Wine[] newArray(int size) { return new Wine[size];
        }
    } ;
}
