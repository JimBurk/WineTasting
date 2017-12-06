package edu.orangecoastcollege.cs273.jburk.winetasting;

/**
 * Created by pjsda on 12/2/2017.
 */

public class AVA {
    private String mName;
    private String mState;
    private String mCounty;
    private String mLocated;
    private String mContains;

    public AVA(String mName, String mState, String mCounty, String mLocated, String mContains) {
        this.mName = mName;
        this.mState = mState;
        this.mCounty = mCounty;
        this.mLocated = mLocated;
        this.mContains = mContains;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmCounty() {
        return mCounty;
    }

    public void setmCounty(String mCounty) {
        this.mCounty = mCounty;
    }

    public String getmLocated() {
        return mLocated;
    }

    public void setmLocated(String mLocated) {
        this.mLocated = mLocated;
    }

    public String getmContains() {
        return mContains;
    }

    public void setmContains(String mContains) {
        this.mContains = mContains;
    }

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof AVA)) return false;

        AVA mmAVA = (AVA) mo;

        if (getmName() != null ? !getmName().equals(mmAVA.getmName()) : mmAVA.getmName() != null)
            return false;
        if (getmState() != null ? !getmState().equals(mmAVA.getmState()) : mmAVA.getmState() != null)
            return false;
        if (getmCounty() != null ? !getmCounty().equals(mmAVA.getmCounty()) : mmAVA.getmCounty() != null)
            return false;
        if (getmLocated() != null ? !getmLocated().equals(mmAVA.getmLocated()) : mmAVA.getmLocated() != null)
            return false;
        return getmContains() != null ? getmContains().equals(mmAVA.getmContains()) : mmAVA.getmContains() == null;
    }

    @Override
    public int hashCode() {
        int mresult = getmName() != null ? getmName().hashCode() : 0;
        mresult = 31 * mresult + (getmState() != null ? getmState().hashCode() : 0);
        mresult = 31 * mresult + (getmCounty() != null ? getmCounty().hashCode() : 0);
        mresult = 31 * mresult + (getmLocated() != null ? getmLocated().hashCode() : 0);
        mresult = 31 * mresult + (getmContains() != null ? getmContains().hashCode() : 0);
        return mresult;
    }

    @Override
    public String toString() {
        return "AVA{" +
                "mName='" + mName + '\'' +
                ", mState='" + mState + '\'' +
                ", mCounty='" + mCounty + '\'' +
                ", mLocated='" + mLocated + '\'' +
                ", mContains='" + mContains + '\'' +
                '}';
    }
}
