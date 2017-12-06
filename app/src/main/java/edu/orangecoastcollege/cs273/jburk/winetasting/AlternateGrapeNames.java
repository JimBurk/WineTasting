package edu.orangecoastcollege.cs273.jburk.winetasting;

/**
 * Created by pjsda on 12/2/2017.
 */

public class AlternateGrapeNames {
    private long mId;
    private String mAltName;
    private String mName;

    public AlternateGrapeNames(long mId, String mAltName, String mName) {
        this.mId = mId;
        this.mAltName = mAltName;
        this.mName = mName;
    }

    public AlternateGrapeNames(String mAltName, String mName) {
        this.mAltName = mAltName;
        this.mName = mName;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmAltName() {
        return mAltName;
    }

    public void setmAltName(String mAltName) {
        this.mAltName = mAltName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mNmae) {
        this.mName = mNmae;
    }

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof AlternateGrapeNames)) return false;

        AlternateGrapeNames mthat = (AlternateGrapeNames) mo;

        if (getmId() != mthat.getmId()) return false;
        if (getmAltName() != null ? !getmAltName().equals(mthat.getmAltName()) : mthat.getmAltName() != null)
            return false;
        return getmName() != null ? getmName().equals(mthat.getmName()) : mthat.getmName() == null;
    }

    @Override
    public int hashCode() {
        int mresult = (int) (getmId() ^ (getmId() >>> 32));
        mresult = 31 * mresult + (getmAltName() != null ? getmAltName().hashCode() : 0);
        mresult = 31 * mresult + (getmName() != null ? getmName().hashCode() : 0);
        return mresult;
    }

    @Override
    public String toString() {
        return "AlternateGrapeNames{" +
                "mId=" + mId +
                ", mAltName='" + mAltName + '\'' +
                ", mNmae='" + mName + '\'' +
                '}';
    }
}
