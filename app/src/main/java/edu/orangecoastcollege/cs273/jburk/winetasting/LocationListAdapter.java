package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to provide custom adapter for the <code>Location</code> list.
 */
public class LocationListAdapter extends ArrayAdapter<WineLocation> {

    private Context mContext;
    private List<WineLocation> mLocationsList = new ArrayList<>();
    private int mResourceId;

    /**
     * Creates a new <code>LocationsListAdapter</code> given a mContext, resource id and list of locations.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param locations The list of locations to display
     */
    public LocationListAdapter(Context c, int rId, List<WineLocation> locations) {
        super(c, rId, locations);
        mContext = c;
        mResourceId = rId;
        mLocationsList = locations;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Location selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final WineLocation selectedLocation = mLocationsList.get(pos);


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout offeringListLinearLayout =
                (LinearLayout) view.findViewById(R.id.locationListLinearLayout);

        TextView locationListNameTextView =
                (TextView) view.findViewById(R.id.locationListNameTextView);
        TextView locationListAddressTextView =
                (TextView) view.findViewById(R.id.locationListAddressTextView);
        TextView locationListPhoneTextView =
                (TextView) view.findViewById(R.id.locationListPhoneTextView);

        offeringListLinearLayout.setTag(selectedLocation);

        locationListNameTextView.setText(selectedLocation.getName());
        locationListAddressTextView.setText(selectedLocation.getFullAddress());
        locationListPhoneTextView.setText(selectedLocation.getPhone());

        return view;
    }
}