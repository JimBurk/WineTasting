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

public class WinesListAdapter extends ArrayAdapter<Wine> {

    private Context mContext;
    private List<Wine> mWinesList = new ArrayList<>();
    private int mResId;

    private LinearLayout wineLineLay;

    public WinesListAdapter(Context c, int rId, List<Wine> wines){
        super(c, rId, wines);
        mContext = c;
        mResId = rId;
        mWinesList = wines;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){

        final Wine selectedWine = mWinesList.get(pos);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResId, null);

        wineLineLay = (LinearLayout) view.findViewById(R.id.wineListLinearLayout);

        TextView varietalTextView = (TextView) view.findViewById(R.id.varietalListTextView);
        TextView vintageTextView = (TextView) view.findViewById(R.id.vintageListTextView);
        TextView wineryTextView = (TextView) view.findViewById(R.id.wineryListTextView);

        wineLineLay.setTag(selectedWine);

        varietalTextView.setText(selectedWine.getmVarietal());
        vintageTextView.setText(Integer.toString(selectedWine.getmVintage()));
        wineryTextView.setText(selectedWine.getmWinery());

        return view;
    }
}
