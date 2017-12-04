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
    private long[] mWines;

    public Tasting(Long id, String name, String date, String location, long[] wines) {
        mId = id;
        mName = name;
        mDate = date;
        mLocation = location;
        mWines = wines;
    }

    public Tasting(Long id) {
        mId = id;
    }

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

    public long[] getWines() {
        return mWines;
    }

    public void setWines(long[] wines) {
        mWines = wines;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mId);
        parcel.writeString(mName);
        parcel.writeString(mDate);
        parcel.writeString(mLocation);
        // parcel.writeLong(mWines);
    }
}
