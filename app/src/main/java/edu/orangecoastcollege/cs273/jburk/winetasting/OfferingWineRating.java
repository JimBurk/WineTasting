package edu.orangecoastcollege.cs273.jburk.winetasting;

/**
 * Created by pjsda on 12/2/2017.
 */

public class OfferingWineRating {
    private long mTasteGroup;
    private Wine mWine;
    private Rating mRating;

    public OfferingWineRating(long mTasteGroup, Wine mWine, Rating mRating) {
        this.mTasteGroup = mTasteGroup;
        this.mWine = mWine;
        this.mRating = mRating;
    }

    public long getmTasteGroup() {
        return mTasteGroup;
    }

    public void setmTasteGroup(long mTasteGroup) {
        this.mTasteGroup = mTasteGroup;
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

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof OfferingWineRating)) return false;

        OfferingWineRating mthat = (OfferingWineRating) mo;

        if (getmTasteGroup() != mthat.getmTasteGroup()) return false;
        if (getmWine() != null ? !getmWine().equals(mthat.getmWine()) : mthat.getmWine() != null)
            return false;
        return getmRating() != null ? getmRating().equals(mthat.getmRating()) : mthat.getmRating() == null;
    }

    @Override
    public int hashCode() {
        int mresult = (int) (getmTasteGroup() ^ (getmTasteGroup() >>> 32));
        mresult = 31 * mresult + (getmWine() != null ? getmWine().hashCode() : 0);
        mresult = 31 * mresult + (getmRating() != null ? getmRating().hashCode() : 0);
        return mresult;
    }

    @Override
    public String toString() {
        return "OfferingWineRating{" +
                "mTasteGroup=" + mTasteGroup +
                ", mWine=" + mWine +
                ", mRating=" + mRating +
                '}';
    }
}
