package edu.orangecoastcollege.cs273.jburk.winetasting;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent){

        final Wine selectedWine = mWinesList.get(pos);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResId, null);

        wineLineLay = (LinearLayout) view.findViewById(R.id.wineListLinearLayout);

        ImageView wineImageView = (ImageView) view.findViewById(R.id.wineBottleImageView);
        TextView varietalTextView = (TextView) view.findViewById(R.id.varietalListTextView);
        TextView vintageTextView = (TextView) view.findViewById(R.id.vintageListTextView);
        TextView wineryTextView = (TextView) view.findViewById(R.id.wineryListTextView);

        wineLineLay.setTag(selectedWine);


        wineImageView.setImageURI(selectedWine.getmImageUri());
        varietalTextView.setText(selectedWine.getmVarietal());
        vintageTextView.setText(Integer.toString(selectedWine.getmVintage()));
        wineryTextView.setText(selectedWine.getmWinery());

        return view;
    }
}
