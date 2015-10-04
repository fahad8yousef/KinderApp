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
import java.util.List;


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
    String childName;
    String activity;
    String loCode;
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
        groupID = getArguments().getInt("id");
        childName = getArguments().getString("childName");
        activity = getArguments().getString("activity");
        loCode = getArguments().getString("loCode");

        mainGrid = (GridView)view.findViewById(R.id.main_grid);
//        testDB=new TestDB(k);
        //testing
        childName="Chris,T";
        mainGrid.setAdapter(new MainAdapter(getActivity(), k, groupID, childName, activity, loCode));


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
    String firstName;
    String lastName;

    MainAdapter(Context c, KinderDBCon k , int groupID, String fullName, String activity, String loCode)
    {
        this.context = c;
        this.k = k;
        this.groupID = groupID;

        list = new ArrayList<Card>();

        int img = R.drawable.cooking1;
        ArrayList<String> evidenceDateActivity;
        evidenceDateActivity = k.getEvidenceInfo(groupID);

        for (int i=0; i<evidenceDateActivity.size() ; i++)
        {
            Card tempCard = new Card(img, evidenceDateActivity.get(i));
            list.add(tempCard);
        }

        //attempt to get Evidence cards by child name
/*        if (fullName.length() !=0 ) {
            this.firstName = fullName.substring(0, fullName.indexOf(","));
            this.lastName = fullName.substring(fullName.indexOf(",") + 1, fullName.length());

            ArrayList<String> evidenceByChild;
            evidenceByChild = k.getEvidenceByChild(firstName, lastName);
            //k.getEvidenceByID(evidanceByChild.get(3));
            for (int i = 0; i < evidenceByChild.size(); i++) {
                //String[] name = k.getEvidenceByID(evidanceByChild.get(i));
                ArrayList<String> a = new ArrayList<>();
                a.add(evidenceByChild.get(i));
                for (int x = 0; i < a.size(); x++) {
                    ArrayList<String> result;
                    result = k.getEvidenceByID(a.get(i));
                    Card tempCard = new Card(img, result.get(i));

                    list.add(tempCard);
                }

            }
        }*/
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

