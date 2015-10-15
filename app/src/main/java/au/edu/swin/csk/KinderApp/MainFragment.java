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
    String fullName;
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

        groupID=getArguments().getInt("id");
        fullName = getArguments().getString("fullName");
        Log.d(TAG, "Name received in bundle " + fullName);
        activity = getArguments().getString("activity");
        //loCode = getArguments().getString("loCode");

        mainGrid = (GridView)view.findViewById(R.id.main_grid);
        //testDB=new TestDB(k);

        if (fullName == null && activity == null) {
            mainGrid.setAdapter(new MainAdapter(getActivity(), k, groupID));
        } else if (fullName !=null && activity == null){
            mainGrid.setAdapter(new MainAdapter(getActivity(), k, fullName));
            fullName=null;
        } else if (fullName == null && activity !=null){
            mainGrid.setAdapter(new MainAdapter(getActivity(), k, groupID, activity));
            activity = null;

        }else {fullName = null;
                activity = null;}

        Log.d(TAG, String.valueOf(groupID));
        mainGrid.setOnItemClickListener(this);
        return view;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*Toast.makeText(getActivity(),
                "Card Clicked: " + position + " item", Toast.LENGTH_SHORT).show();*/
        //sending evidence code to form fragment
        Toast.makeText(getActivity(),
                "Card Clicked: " + position + " Card EvidId = " + MainAdapter.getEvidenceIDSelected(position) , Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).showFormFragment();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


/*
*
* @Author Fahad Alhamed 747234x
* This class handles querying the db to get Evidence cards based on group selected.
* Querying the db to get Evidence cards based on Child selected
* Querying the db to get Evidence cards based on Activity selected
* Querying the db to get Evidence cards based on LOCode selected
* */
class MainAdapter extends BaseAdapter
{
    private static final String TAG = "Fahad/ MainAdapter" ;
    public static ArrayList<Card> list;
    Context context;
    KinderDBCon k;
    int groupID;
    String firstName;
    String lastName;
    String fullName;
    String activity;

    /*
    * This is the constructor for mainAdapter it receives the following and query the db to filter evidence cards displayed in main screen
    * @Param Context from main activity
    * @Param object KinderDBCon created for db
    * @Param integer Group selected for filtering
    * @Param String to hold full child name for filtering
    * @Param String to hold activity selected for filtering
    * */
    MainAdapter(Context c, KinderDBCon k , int groupID)
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
            //Log.d(TAG, "evidance Code =" + tempCard.getID());
        }
    }

    MainAdapter(Context c, KinderDBCon k ,String fullName){
        this.context = c;
        this.k = k;
        list = new ArrayList<Card>();
        int img = R.drawable.cooking1;
        this.fullName = fullName;

        this.firstName = fullName.substring(0, fullName.indexOf(","));
        this.lastName = fullName.substring(fullName.indexOf(",") + 1, fullName.length());

        ArrayList<String> evidenceByChild = k.getEvidenceByChild(firstName, lastName);
        Log.d(TAG, "This is evid id for Child selected" + evidenceByChild.toString());

        for (int i = 0; i < evidenceByChild.size(); i++) {
            ArrayList<String> s = k.getEvidenceByID(evidenceByChild.get(i));
            for (int j=0 ;j < s.size(); j++ ) {
            Card tempCard = new Card(img, s.get(j));
            list.add(tempCard);
            }
        }
    }

    MainAdapter(Context c, KinderDBCon k ,int groupID, String activity){
        this.context = c;
        this.k = k;
        list = new ArrayList<Card>();
        int img = R.drawable.cooking1;
        this.groupID = groupID;
        this.activity = activity;

        ArrayList<String> evidenceByActivity = k.getEvidenceByActivity(groupID, activity);
        for (int i = 0; i < evidenceByActivity.size(); i++) {
            ArrayList<String> s = k.getEvidenceByID(evidenceByActivity.get(i));
            for (int j=0 ;j < s.size(); j++ ) {
                Card tempCard = new Card(img, s.get(j));
                list.add(tempCard);
            }
        }
    }

    public static String getEvidenceIDSelected(int position){
        String result;
        result = list.get(position).getID();
        return result;
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

    /*
    * @Author Fahad Alhamed 747234x
    * This class used to hold each cell component on the main screen
    * it holds Image & Date & Activity components
    *
    * */
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

    /*
    * This override method used to Assign Evidence cards to rows in the grid layout and
    * return the view of each card
    * @Param int i
    * @Param View the row
    * @Return returns the row requested
    * */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder;
        if (row == null)
        {
            //inflate row and assign to holder
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item_main, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        //get each object from the Card Class and set parameters
        Card temp = list.get(i);
        holder.cardImage.setImageResource(temp.imageId);
        holder.cardDate.setText(temp.date);
        holder.cardActivity.setText(temp.activityName);
        return row;
    }
}

/*
* This class holds each cell in the main screen
* @Author Fahad Alhamed 747234x
*
* */

class Card {


    String date;
    String activityName;
    int imageId;
    String evidID;
    private static final String TAG= "Fahad/ Card";


    /*
    * Constructor
    * @Param String image name
    * @param String info contains activity name and date
    * */
    Card(int imageId, String info)
    {
        if (info.length() !=0 ) {
            this.evidID = info.substring(0, info.indexOf("|"));
            this.date = info.substring(info.indexOf("|") + 1, info.indexOf(","));
            this.activityName = info.substring(info.indexOf(",") + 1, info.length());
        }

        this.imageId = imageId;

        Log.d(TAG, evidID + date + activityName);
    }

    /*
    * A method to get the evidence ID
    * @Return String Id
    * */
    public String getID(){

        return evidID;
    }
}

