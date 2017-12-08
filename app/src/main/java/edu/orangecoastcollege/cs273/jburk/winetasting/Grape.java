package edu.orangecoastcollege.cs273.jburk.winetasting;

/**
 * Created by pjsda on 12/2/2017.
 *
 */

public class Grape {
    private long mId;
    private String mName;
    private String mSynonym1;
    private String mSynonym2;

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSynonym1() {
        return mSynonym1;
    }

    public void setmSynonym1(String mSynonym1) {
        this.mSynonym1 = mSynonym1;
    }

    public String getmSynonym2() {
        return mSynonym2;
    }

    public void setmSynonym2(String mSynonym2) {
        this.mSynonym2 = mSynonym2;
    }

    public Grape(long mId, String mName, String mSynonym1, String mSynonym2) {
        this.mId = mId;
        this.mName = mName;
        this.mSynonym1 = mSynonym1;
        this.mSynonym2 = mSynonym2;
    }

    public Grape(String mName, String mSynonym1, String mSynonym2) {
        this.mName = mName;
        this.mSynonym1 = mSynonym1;
        this.mSynonym2 = mSynonym2;
    }

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof Grape)) return false;

        Grape mmGrape = (Grape) mo;

        if (getmId() != mmGrape.getmId()) return false;
        if (getmName() != null ? !getmName().equals(mmGrape.getmName()) : mmGrape.getmName() != null)
            return false;
        if (getmSynonym1() != null ? !getmSynonym1().equals(mmGrape.getmSynonym1()) : mmGrape.getmSynonym1() != null)
            return false;
        return getmSynonym2() != null ? getmSynonym2().equals(mmGrape.getmSynonym2()) : mmGrape.getmSynonym2() == null;
    }

    @Override
    public int hashCode() {
        int mresult = (int) (getmId() ^ (getmId() >>> 32));
        mresult = 31 * mresult + (getmName() != null ? getmName().hashCode() : 0);
        mresult = 31 * mresult + (getmSynonym1() != null ? getmSynonym1().hashCode() : 0);
        mresult = 31 * mresult + (getmSynonym2() != null ? getmSynonym2().hashCode() : 0);
        return mresult;
    }

    @Override
    public String toString() {
        return "Grape{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSynonym1='" + mSynonym1 + '\'' +
                ", mSynonym2='" + mSynonym2 + '\'' +
                '}';
    }
}
