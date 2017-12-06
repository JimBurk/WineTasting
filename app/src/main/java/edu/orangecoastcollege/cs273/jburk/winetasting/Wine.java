package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * The <code>Wine</code> class maintains information about wine, including its id number, varietal,
 * vintage, winery, vineyard, and price.
 *
 * Created by pjsda on 11/10/2017.
 */

public class Wine implements Parcelable{

    private long mId;
    private long mTasteGroup;
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
    public Wine() {mTasteGroup = 0L; mVarietal =  ""; mVintage = 0; mWinery = ""; mVineyard = ""; mPrice = 0.0; mImageUri = null;}

    /**
     * Creates a new <code>Wine</code> from its id, varietal, vintage, winery, vineyard, and price.
     * @param mVarietal The wine varietal.
     * @param mVintage The wine vintage.
     * @param mWinery The winery that created the wine.
     * @param mVineyard The wine vineyard.
     * @param mPrice The wine price.
     * @param mImageUri The Uri of the picture.
     */
    public Wine(long mTasteGroup, String mVarietal, int mVintage, String mWinery, String mVineyard, double mPrice, Uri mImageUri) {
        this.mTasteGroup = mTasteGroup;
        this.mVarietal = mVarietal;
        this.mVintage = mVintage;
        this.mWinery = mWinery;
        this.mVineyard = mVineyard;
        this.mPrice = mPrice;
        this.mImageUri = mImageUri;
    }

    /**
     * Creates a new <code>Wine</code> from its id, varietal, vintage, winery, vineyard, price and Uri.
     * @param mId The wine id.
     * @param mTasteGroup the tasting id.
     * @param mVarietal The wine varietal.
     * @param mVintage The wine vintage.
     * @param mWinery The winery that created the wine.
     * @param mVineyard The wine vineyard.
     * @param mPrice The wine price.
     */
    public Wine(long mId, long mTasteGroup, String mVarietal, int mVintage, String mWinery, String mVineyard, double mPrice, Uri imageUri) {
        this.mId = mId;
        this.mTasteGroup = mTasteGroup;
        this.mVarietal = mVarietal;
        this.mVintage = mVintage;
        this.mWinery = mWinery;
        this.mVineyard = mVineyard;
        this.mPrice = mPrice;
        this.mImageUri = imageUri;
    }

    public Wine(Parcel parcel){
        mId = parcel.readLong();
        mTasteGroup = parcel.readLong();
        mVarietal = parcel.readString();
        mVintage = parcel.readInt();
        mWinery = parcel.readString();
        mVineyard = parcel.readString();
        mPrice = parcel.readDouble();

        String strUri = parcel.readString();
        mImageUri = Uri.parse(strUri);
    }

    public long getmTasteGroup() {
        return mTasteGroup;
    }

    public void setmTasteGroup(long mTasteGroup) {
        this.mTasteGroup = mTasteGroup;
    }

    /**
     * Gets the unique id of the <code>Wine</code>.
     * @return The unique id.
     */
    public long getmId() {
        return mId;
    }

    /**
     * Sets the unique id of the <code>Wine</code>
     * @param mId The unique id.
     */
    public void setmId(long mId) {
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

    public Uri getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }

    /**
     * Sets the price of the <code>Wine</code>.
     * @param mPrice The price.
     */
    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof Wine)) return false;

        Wine mmWine = (Wine) mo;

        if (getmId() != mmWine.getmId()) return false;
        if (getmTasteGroup() != mmWine.getmTasteGroup()) return false;
        if (getmVintage() != mmWine.getmVintage()) return false;
        if (Double.compare(mmWine.getmPrice(), getmPrice()) != 0) return false;
        if (getmVarietal() != null ? !getmVarietal().equals(mmWine.getmVarietal()) : mmWine.getmVarietal() != null)
            return false;
        if (getmWinery() != null ? !getmWinery().equals(mmWine.getmWinery()) : mmWine.getmWinery() != null)
            return false;
        if (getmVineyard() != null ? !getmVineyard().equals(mmWine.getmVineyard()) : mmWine.getmVineyard() != null)
            return false;
        return getmImageUri() != null ? getmImageUri().equals(mmWine.getmImageUri()) : mmWine.getmImageUri() == null;
    }

    @Override
    public int hashCode() {
        int mresult;
        long mtemp;
        mresult = (int) (getmId() ^ (getmId() >>> 32));
        mresult = 31 * mresult + (int) (getmTasteGroup() ^ (getmTasteGroup() >>> 32));
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
        return "Wine{" +
                "mId=" + mId +
                ", mTasteGroup=" + mTasteGroup +
                ", mVarietal='" + mVarietal + '\'' +
                ", mVintage=" + mVintage +
                ", mWinery='" + mWinery + '\'' +
                ", mVineyard='" + mVineyard + '\'' +
                ", mPrice=" + mPrice +
                ", mImageUri=" + mImageUri +
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
        parcel.writeLong(mTasteGroup);
        parcel.writeString(mVarietal);
        parcel.writeInt(mVintage);
        parcel.writeString(mWinery);
        parcel.writeString(mVineyard);
        parcel.writeDouble(mPrice);
        parcel.writeString(mImageUri.toString());
    }

    public static final Parcelable.Creator<Wine> CREATOR = new Parcelable.Creator<Wine>(){
        /**
         * This method is used with Intents to crate new Wine objects.
         *
         * @param parcel The package with all the information for the Wine
         * @return The new Wine object
         */
        @Override
        public Wine createFromParcel(Parcel parcel) {return new Wine(parcel);}

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
