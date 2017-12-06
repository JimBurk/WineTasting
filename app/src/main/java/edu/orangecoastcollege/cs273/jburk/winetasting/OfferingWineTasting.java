package edu.orangecoastcollege.cs273.jburk.winetasting;

/**
 * Created by pjsda on 12/2/2017.
 */

public class OfferingWineTasting {
    private long mTastingGroupId;
    private Wine mWine;
    private Tasting mTasting;

    public OfferingWineTasting(long mTastingGroupId, Wine mWine, Tasting mTasting) {
        this.mTastingGroupId = mTastingGroupId;
        this.mWine = mWine;
        this.mTasting = mTasting;
    }

    public long getmTastingGroupId() {
        return mTastingGroupId;
    }

    public void setmTastingGroupId(long mTastingGroupId) {
        this.mTastingGroupId = mTastingGroupId;
    }

    public Wine getmWine() {
        return mWine;
    }

    public void setmWine(Wine mWine) {
        this.mWine = mWine;
    }

    public Tasting getmTasting() {
        return mTasting;
    }

    public void setmTasting(Tasting mTasting) {
        this.mTasting = mTasting;
    }

    @Override
    public boolean equals(Object mo) {

        if (this == mo) return true;
        if (!(mo instanceof OfferingWineTasting)) return false;

        OfferingWineTasting mthat = (OfferingWineTasting) mo;

        if (getmTastingGroupId() != mthat.getmTastingGroupId()) return false;
        if (getmWine() != null ? !getmWine().equals(mthat.getmWine()) : mthat.getmWine() != null)
            return false;
        return getmTasting() != null ? getmTasting().equals(mthat.getmTasting()) : mthat.getmTasting() == null;
    }

    @Override
    public int hashCode() {
        int mresult = (int) (getmTastingGroupId() ^ (getmTastingGroupId() >>> 32));
        mresult = 31 * mresult + (getmWine() != null ? getmWine().hashCode() : 0);
        mresult = 31 * mresult + (getmTasting() != null ? getmTasting().hashCode() : 0);
        return mresult;
    }

    @Override
    public String toString() {
        return "OfferingWineTasting{" +
                "mTastingGroupId=" + mTastingGroupId +
                ", mWine=" + mWine +
                ", mTasting=" + mTasting +
                '}';
    }
}