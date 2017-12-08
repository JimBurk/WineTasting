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

public class TastingListAdapter extends ArrayAdapter<Tasting> {

    private Context mContext;
    private List<Tasting> mTastingList = new ArrayList<>();
    private int mResId;

    private LinearLayout tastingLinLay;

    public TastingListAdapter(Context c, int rId, List<Tasting> tastings){
        super(c, rId, tastings);
        mContext = c;
        mResId = rId;
        mTastingList = tastings;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){

        final Tasting selectedTasting = mTastingList.get(pos);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResId, null);

        tastingLinLay = (LinearLayout) view.findViewById(R.id.tastingListLinearLayout);

        TextView nameListTextView = (TextView) view.findViewById(R.id.nameListTextView);
        TextView dateListTextView = (TextView) view.findViewById(R.id.dateListTextView);
        TextView locationListTextView = (TextView) view.findViewById(R.id.locationListTextView);

        tastingLinLay.setTag(selectedTasting);

        nameListTextView.setText(selectedTasting.getName());
        dateListTextView.setText(selectedTasting.getDate());
        locationListTextView.setText(selectedTasting.getLocation());

        return view;
    }
}
