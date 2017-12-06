package edu.orangecoastcollege.cs273.jburk.winetasting;

/**
 * Created by pjsda on 12/5/2017.
 */

public class OfferingRatingTasting {
    private long mGroupId;
    private Tasting mTasting;
    private Rating mRating;

    public OfferingRatingTasting(long mGroupId, Tasting mTasting, Rating mRating) {
        this.mGroupId = mGroupId;
        this.mTasting = mTasting;
        this.mRating = mRating;
    //mTasting.get
    }

    public long getmGroupId() {
        return mGroupId;
    }

    public void setmGroupId(long mGroupId) {
        this.mGroupId = mGroupId;
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

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof OfferingRatingTasting)) return false;

        OfferingRatingTasting mthat = (OfferingRatingTasting) mo;

        if (mGroupId != mthat.mGroupId) return false;
        if (mTasting != null ? !mTasting.equals(mthat.mTasting) : mthat.mTasting != null)
            return false;
        return mRating != null ? mRating.equals(mthat.mRating) : mthat.mRating == null;
    }

    @Override
    public int hashCode() {
        int mresult = (int) (mGroupId ^ (mGroupId >>> 32));
        mresult = 31 * mresult + (mTasting != null ? mTasting.hashCode() : 0);
        mresult = 31 * mresult + (mRating != null ? mRating.hashCode() : 0);
        return mresult;
    }

    @Override
    public String toString() {
        return "OfferingRatingTasting{" +
                "mGroupId=" + mGroupId +
                ", mTasting=" + mTasting +
                ", mRating=" + mRating +
                '}';
    }
}
