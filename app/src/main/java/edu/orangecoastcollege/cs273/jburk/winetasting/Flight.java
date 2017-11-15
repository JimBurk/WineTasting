package edu.orangecoastcollege.cs273.jburk.winetasting;

/**
 * The <code>Flight</code> class maintains information about a wine tasting flight, including its
 * id number, wine A, and wine B.
 *
 * Created by pjsda on 11/10/2017.
 */

public class Flight {

    private Long mId;
    private Wine mFlightA;
    private Wine mFlightB;

    public Flight(){mFlightA=null; mFlightB=null;}

    /**
     * Creates a new <code>Flight</code> from its id, flightA, and flightB.
     * @param mFlightA The flight A.
     * @param mFlightB The flight B.
     */
    public Flight(Wine mFlightA, Wine mFlightB) {
        this.mFlightA = mFlightA;
        this.mFlightB = mFlightB;
    }

    /**
     * Creates a new <code>Flight</code> from its id, flightA, and flightB.
     * @param mId The unique id.
     * @param mFlightA The flight A.
     * @param mFlightB The flight B.
     */
    public Flight(Long mId, Wine mFlightA, Wine mFlightB) {
        this.mId = mId;
        this.mFlightA = mFlightA;
        this.mFlightB = mFlightB;
    }

    /**
     * Gets the unique id of the <code>Flight</code>.
     * @return The unique id.
     */
    public Long getmId() {
        return mId;
    }

    /**
     * Sets the unique id of the <code>Flight</code>.
     * @param mId The unique id.
     */
    public void setmId(Long mId) {
        this.mId = mId;
    }

    /**
     * Gets flightA of the <code>Flight</code>.
     * @return the Flight A.
     */
    public Wine getmFlightA() {
        return mFlightA;
    }

    /**
     * Sets the flightA of the <code>Flight</code>.
     * @param mFlightA The flight A.
     */
    public void setmFlightA(Wine mFlightA) {
        this.mFlightA = mFlightA;
    }

    /**
     * Gets the flightB of the <code>Flight</code>.
     * @return The flightB.
     */
    public Wine getmFlightB() {
        return mFlightB;
    }

    /**
     * Setes the flightB of the <code>Flight</code>.
     * @param mFlightB
     */
    public void setmFlightB(Wine mFlightB) {
        this.mFlightB = mFlightB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flightm = (Flight) o;

        if (mId != null ? !mId.equals(flightm.mId) : flightm.mId != null) return false;
        if (mFlightA != null ? !mFlightA.equals(flightm.mFlightA) : flightm.mFlightA != null)
            return false;
        return mFlightB != null ? mFlightB.equals(flightm.mFlightB) : flightm.mFlightB == null;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mFlightA != null ? mFlightA.hashCode() : 0);
        result = 31 * result + (mFlightB != null ? mFlightB.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "mId=" + mId +
                ", mFlightA=" + mFlightA +
                ", mFlightB=" + mFlightB +
                '}';
    }
}
