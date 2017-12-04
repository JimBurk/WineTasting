package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jimburk on 11/30/17.
 */

public class Tasting implements Parcelable {
    private Long mId;
    private String mName;
    private String mDate;
    private String mLocation;

    public Tasting(Long id, String name, String date, String location) {
        mId = id;
        mName = name;
        mDate = date;
        mLocation = location;
    }

    public Tasting(String name, String date, String location) {
        mName = name;
        mDate = date;
        mLocation = location;
    }

    public Tasting(Long id) {
        mId = id;
    }

    protected Tasting(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mName = in.readString();
        mDate = in.readString();
        mLocation = in.readString();
    }

    public static final Creator<Tasting> CREATOR = new Creator<Tasting>() {
        @Override
        public Tasting createFromParcel(Parcel in) {
            return new Tasting(in);
        }

        @Override
        public Tasting[] newArray(int size) {
            return new Tasting[size];
        }
    };

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
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
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object mo) {
        if (this == mo) return true;
        if (!(mo instanceof Tasting)) return false;

        Tasting mmTasting = (Tasting) mo;

        if (mId != null ? !mId.equals(mmTasting.mId) : mmTasting.mId != null) return false;
        if (mName != null ? !mName.equals(mmTasting.mName) : mmTasting.mName != null) return false;
        if (mDate != null ? !mDate.equals(mmTasting.mDate) : mmTasting.mDate != null) return false;
        return mLocation != null ? mLocation.equals(mmTasting.mLocation) : mmTasting.mLocation == null;
    }

    @Override
    public int hashCode() {
        int mresult = mId != null ? mId.hashCode() : 0;
        mresult = 31 * mresult + (mName != null ? mName.hashCode() : 0);
        mresult = 31 * mresult + (mDate != null ? mDate.hashCode() : 0);
        mresult = 31 * mresult + (mLocation != null ? mLocation.hashCode() : 0);
        return mresult;
    }

    @Override
    public String toString() {
        return "Tasting{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mLocation='" + mLocation + '\'' +
                '}';
    }



    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mId);
        parcel.writeString(mName);
        parcel.writeString(mDate);
        parcel.writeString(mLocation);
    }
}
