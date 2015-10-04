package au.edu.swin.csk.KinderApp;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements
        AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener
{

    //Basically all the mainGrid functionality has been shifted to this fragment.

    GridView mainGrid;
    KinderDBCon k;
    TestDB testDB;
    private static final String TAG= "Somesh/ Main Fragment";
    int groupID;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        k = new KinderDBCon(getActivity());
        k.open(); //open database

        //The following line is used to retrieve the groupID that was stored in a bundle and associated with the mainFragment.
        groupID=getArguments().getInt("id");
        mainGrid = (GridView)view.findViewById(R.id.main_grid);
        testDB=new TestDB(k);
        mainGrid.setAdapter(new MainAdapter(getActivity(), k, groupID));
        Log.d(TAG, String.valueOf(groupID));
        mainGrid.setOnItemClickListener(this);
        return view;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            Toast.makeText(getActivity(),
                    "Item Clicked: " + position, Toast.LENGTH_SHORT).show();


            (( MainActivity)getActivity()).showFormFragment();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

class MainAdapter extends BaseAdapter
{
    private static final String TAG = "dd" ;
    ArrayList<Card> list;
    Context context;
    KinderDBCon k;
    int groupID;

    MainAdapter(Context c, KinderDBCon k , int groupID)
    {
        this.context = c;
        this.k = k;
        this.groupID = groupID;

        list = new ArrayList<Card>();
        //Resources res = context.getResources();
        int[] activityImages = {R.drawable.cooking1, R.drawable.cooking2, R.drawable.cooking3,
                R.drawable.cooking4, R.drawable.cooking5, R.drawable.cooking6, R.drawable.cooking7,
                R.drawable.cooking8, R.drawable.cooking9, R.drawable.cooking10,
                R.drawable.cooking11, R.drawable.cooking12, R.drawable.cooking12,
                R.drawable.cooking12, R.drawable.cooking12, R.drawable.cooking12,
                R.drawable.cooking12, R.drawable.cooking12};

        ArrayList<String> evidenceDateActivity;
        evidenceDateActivity = k.getEvidenceInfo(groupID);

//        String[] tempChildNames = res.getStringArray(R.array.childArray);
//        String[] tempActivityNames = res.getStringArray(R.array.activityArray);

        for (int i=0; i<evidenceDateActivity.size() ; i++)
        {
            Card tempCard = new Card(activityImages[i], evidenceDateActivity.get(i));
            list.add(tempCard);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder
    {
        ImageView cardImage;
        TextView cardDate;
        TextView cardActivity;

        ViewHolder(View v)
        {
            cardImage = (ImageView) v.findViewById(R.id.imageView);
            cardActivity = (TextView) v.findViewById(R.id.cardActivity);
            cardDate = (TextView) v.findViewById(R.id.cardDate);

        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder;
        if (row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item_main, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        Card temp = list.get(i);
        holder.cardImage.setImageResource(temp.imageId);
        holder.cardDate.setText(temp.date);
        holder.cardActivity.setText(temp.activityName);
        return row;
    }

}


class Card {


    String date;
    String activityName;
    int imageId;

    Card(int imageId, String info)
    {
        if (info.length() !=0 ) {
            this.date = info.substring(0, info.indexOf(","));
            this.activityName = info.substring(info.indexOf(",") + 1, info.length());
        }
        this.imageId = imageId;
        //this.date = date;
        //this.activityName = activityName;
    }
}

