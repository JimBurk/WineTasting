package edu.orangecoastcollege.cs273.jburk.winetasting;
/**
 * Created by pjsda on 12/5/2017.
 */

public class OfferingRatingTasting {
    private Tasting mTasting;
    private Rating mRating;

    //tasting
    private String mName;
    private String mDate;
    private String mLocation;

    //Rating
    private float mColor;
    private float mAroma;
    private float mBody;
    private float mTaste;
    private float mFinish;
    private String mNotes;

    public OfferingRatingTasting(Tasting mTasting, Rating mRating, String mName, String mDate,
                                 String mLocation, float mColor, float mAroma, float mBody,
                                 float mTaste, float mFinish, String mNotes)
    {
        this.mTasting = mTasting;
        this.mRating = mRating;
        this.mName = mName;
        this.mDate = mDate;
        this.mLocation = mLocation;
        this.mColor = mColor;
        this.mAroma = mAroma;
        this.mBody = mBody;
        this.mTaste = mTaste;
        this.mFinish = mFinish;
        this.mNotes = mNotes;
    }

    public OfferingRatingTasting(Tasting mTasting, Rating mRating){
        this.mTasting = mTasting;
        this.mRating = mRating;
        this.mName = mTasting.getName();
        this.mDate = mTasting.getDate();
        this.mLocation = mTasting.getLocation();
        this.mColor = mRating.getColor();
        this.mAroma = mRating.getAroma();
        this.mBody = mRating.getBody();
        this.mTaste = mRating.getTaste();
        this.mFinish = mRating.getFinish();
        this.mNotes = mRating.getNotes();
    }

    public OfferingRatingTasting(){
        mTasting = null; mRating = null; mName=""; mDate = ""; mLocation = ""; mColor = 0.0f;
        mAroma = 0.0f; mBody = 0.0f; mTaste = 0.0f; mFinish = 0.0f; mNotes = "";
    }

    public Tasting getmTasting() {
        return mTasting;
    }

    public void setmTasting(Tasting mTasting) {
        this.mTasting = mTasting;
    }

    public Rating getmRating() {
        return mRating;
    }

    public void setmRating(Rating mRating) {
        this.mRating = mRating;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
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

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof OfferingRatingTasting)) return false;

        OfferingRatingTasting mthat = (OfferingRatingTasting) mo;

        if (Float.compare(mthat.mColor, mColor) != 0) return false;
        if (Float.compare(mthat.mAroma, mAroma) != 0) return false;
        if (Float.compare(mthat.mBody, mBody) != 0) return false;
        if (Float.compare(mthat.mTaste, mTaste) != 0) return false;
        if (Float.compare(mthat.mFinish, mFinish) != 0) return false;
        if (mTasting != null ? !mTasting.equals(mthat.mTasting) : mthat.mTasting != null)
            return false;
        if (mRating != null ? !mRating.equals(mthat.mRating) : mthat.mRating != null) return false;
        if (mName != null ? !mName.equals(mthat.mName) : mthat.mName != null) return false;
        if (mDate != null ? !mDate.equals(mthat.mDate) : mthat.mDate != null) return false;
        if (mLocation != null ? !mLocation.equals(mthat.mLocation) : mthat.mLocation != null)
            return false;
        return mNotes != null ? mNotes.equals(mthat.mNotes) : mthat.mNotes == null;
    }

    @Override
    public int hashCode() {
        int mresult = mTasting != null ? mTasting.hashCode() : 0;
        mresult = 31 * mresult + (mRating != null ? mRating.hashCode() : 0);
        mresult = 31 * mresult + (mName != null ? mName.hashCode() : 0);
        mresult = 31 * mresult + (mDate != null ? mDate.hashCode() : 0);
        mresult = 31 * mresult + (mLocation != null ? mLocation.hashCode() : 0);
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
        return "OfferingRatingTasting{" +
                "mTasting=" + mTasting +
                ", mRating=" + mRating +
                ", mName='" + mName + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mLocation='" + mLocation + '\'' +
                ", mColor=" + mColor +
                ", mAroma=" + mAroma +
                ", mBody=" + mBody +
                ", mTaste=" + mTaste +
                ", mFinish=" + mFinish +
                ", mNotes='" + mNotes + '\'' +
                '}';
    }
}
