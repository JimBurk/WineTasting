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

public class WinePreviewListAdapter extends ArrayAdapter<Wine> {

    private Context mContext;
    private List<Wine> mWinesList = new ArrayList<>();
    private int mResId;

    public WinePreviewListAdapter(Context c, int rId, List<Wine> wines){
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

        LinearLayout wineListLinLay = view.findViewById(R.id.wineListLinearLayout);
        TextView vineyardListTextView = view.findViewById(R.id.vineyardListTextView);
        TextView varietalListTextView = view.findViewById(R.id.varietalListTextView);
        TextView vintageListTextView = view.findViewById(R.id.vintageListTextView);

        wineListLinLay.setTag(selectedWine);
        vineyardListTextView.setText(selectedWine.getmVineyard());
        varietalListTextView.setText(selectedWine.getmVarietal());
        vintageListTextView.setText(selectedWine.getmVineyard());

        return view;
    }
}
