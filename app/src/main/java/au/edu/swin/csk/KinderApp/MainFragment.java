package au.edu.swin.csk.KinderApp;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


/**
 * @author Fahad Alhamed 747234x
 * @author Somesh Bahuguna
 */
public class MainFragment extends Fragment implements
        AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener
{

    //Basically all the mainGrid functionality has been shifted to this fragment.
    private FloatingActionButton homeButton;
    private AddFloatingActionButton newButton;

    GridView mainGrid;
    KinderDBCon k;
    TestDB testDB;
    private static final String TAG= "Somesh/ Main Fragment";
    int groupID;
    String fullName;
    String activity;
    String loutcomeCode;
    int completionStatus;
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
        homeButton=(FloatingActionButton)view.findViewById(R.id.fab_home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("id", groupID);
                ((MainActivity) getActivity()).showMainFragment(bundle);
            }
        });
        newButton=(AddFloatingActionButton)view.findViewById(R.id.fab_new);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showFormFragment(2, "");

            }
        });
        groupID=getArguments().getInt("id");
        fullName = getArguments().getString("fullName");
        Log.d(TAG, "Name received in bundle " + fullName);
        activity = getArguments().getString("activity");
        loutcomeCode = getArguments().getString("loutcomeCode");
        Log.d(TAG,"hereee-- " +loutcomeCode);
        completionStatus = getArguments().getInt("completionStatus");
        Log.d(TAG,"completionStatus----- " +completionStatus);

        mainGrid = (GridView)view.findViewById(R.id.main_grid);
        //testDB=new TestDB(k);

        if (fullName == null && activity == null && loutcomeCode == null && completionStatus == 0) {
            mainGrid.setAdapter(new MainAdapter(getActivity(), k, groupID, true));
        } else if (fullName !=null && activity == null && loutcomeCode == null && completionStatus == 0){
            mainGrid.setAdapter(new MainAdapter(getActivity(), k, fullName));
            fullName=null;
        } else if (fullName == null && activity !=null && loutcomeCode == null && completionStatus == 0){
            mainGrid.setAdapter(new MainAdapter(getActivity(), k, groupID, activity));
            activity = null;

        } else if (fullName == null && activity == null && loutcomeCode != null && completionStatus == 0) {
            mainGrid.setAdapter(new MainAdapter(getActivity(), k, loutcomeCode, groupID));
            loutcomeCode = null;

        } else if (fullName == null && activity == null && loutcomeCode == null && completionStatus == 1) {
            completionStatus = 0;
            mainGrid.setAdapter(new MainAdapter(getActivity(), k, groupID, false));
            Log.d(TAG, "Status " + String.valueOf(completionStatus));

        }else {
                fullName = null;
                activity = null;
                loutcomeCode = null;
                completionStatus=0;}

        Log.d(TAG, String.valueOf(groupID));
        mainGrid.setOnItemClickListener(this);
        return view;

    }

    public void showHomeButton()
    {

    }
    public void hideHomeButton()
    {

    }
    public void showAddButton()
    {

    }
    public void hideAddButton()
    {

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*Toast.makeText(getActivity(),
                "Card Clicked: " + position + " item", Toast.LENGTH_SHORT).show();*/
        //sending evidence code to form fragment
        Toast.makeText(getActivity(),
                "Card Clicked: " + position + " Card EvidId = " + MainAdapter.getEvidenceIDSelected(position) , Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).showFormFragment(1, MainAdapter.getEvidenceIDSelected(position));
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
class MainAdapter extends BaseAdapter //change to arrayadapter
{
    String dir = "/storage/emulated/0/Pictures/KinderThumbnails/";
    private static final String TAG = "Fahad/ MainAdapter" ;
    public static ArrayList<Card> list;
    Context context;
    KinderDBCon k;
    int groupID;
    String firstName;
    String lastName;
    String fullName;
    String activity;
    String loutcomeCode;
    Boolean status = true;

    /*
    * @author Fahad Alhamed 747234x
    * This is the constructor for mainAdapter it receives the following and query the db to filter evidence cards displayed in main screen
    * @Param Context from main activity
    * @Param object KinderDBCon created for db
    * @Param integer Group selected for filtering
    * @Param String to hold full child name for filtering
    * @Param String to hold activity selected for filtering
    * */
    MainAdapter(Context c, KinderDBCon k , int groupID, Boolean status)
    {
        this.context = c;
        this.k = k;
        this.groupID = groupID;

        //int img = R.drawable.cooking1;

        if (status) {
            list = new ArrayList<Card>();
            ArrayList<String> evidenceDateActivity;
            evidenceDateActivity = k.getEvidenceInfo(groupID);
            for (int i=0; i<evidenceDateActivity.size() ; i++)
            {
                Card tempCard = new Card(evidenceDateActivity.get(i));
                list.add(tempCard);
                //Log.d(TAG, "evidance Code =" + tempCard.getID());
            }

        } else {
            list = new ArrayList<Card>();
            ArrayList<String> incomplete = k.getIncompleteEvidence(groupID);
            Log.d(TAG, " incomplet eviID : "+ incomplete.toString());
            for (int i=0 ; i < incomplete.size(); i++) {

                ArrayList<String> s = k.getEvidenceByID(incomplete.get(i));
                for (int j=0 ;j < s.size(); j++ ) {
                    Card tempCard = new Card(s.get(j));
                    list.add(tempCard);
                    Log.d(TAG, " this is id 5 ===  " + tempCard.getID());
                }
            }

        }

    }

    MainAdapter(Context c, KinderDBCon k ,String fullName){
        this.context = c;
        this.k = k;
        list = new ArrayList<Card>();
        //int img = R.drawable.cooking1;
        this.fullName = fullName;

        this.firstName = fullName.substring(0, fullName.indexOf(" "));
        this.lastName = fullName.substring(fullName.indexOf(" ") + 1, fullName.length());

        ArrayList<String> evidenceByChild = k.getEvidenceByChild(firstName, lastName);
        Log.d(TAG, "This is evid id for Child selected" + evidenceByChild.toString());

        for (int i = 0; i < evidenceByChild.size(); i++) {
            ArrayList<String> s = k.getEvidenceByID(evidenceByChild.get(i));
            for (int j=0 ;j < s.size(); j++ ) {
            Card tempCard = new Card(s.get(j));
            list.add(tempCard);
            }
        }
    }

    MainAdapter(Context c, KinderDBCon k ,int groupID, String activity){
        this.context = c;
        this.k = k;
        list = new ArrayList<Card>();
        //int img = R.drawable.cooking1;
        this.groupID = groupID;
        this.activity = activity;

        ArrayList<String> evidenceByActivity = k.getEvidenceByActivity(groupID, activity);
        for (int i = 0; i < evidenceByActivity.size(); i++) {
            ArrayList<String> s = k.getEvidenceByID(evidenceByActivity.get(i));
            for (int j=0 ;j < s.size(); j++ ) {
                Card tempCard = new Card(s.get(j));
                list.add(tempCard);
            }
        }
    }

    public MainAdapter(Context c, KinderDBCon k, String loutcomeCode, int groupID) {
        this.context = c;
        this.k = k;
        list = new ArrayList<Card>();
        //int img = R.drawable.cooking1;
        this.groupID = groupID;
        this.loutcomeCode = loutcomeCode;

        ArrayList<String> evidenceByLoCode = k.getEvidenceByLoutcomeCode(loutcomeCode);
        for (int i = 0; i < evidenceByLoCode.size(); i++) {
            ArrayList<String> s = k.getEvidenceByID(evidenceByLoCode.get(i));
            for (int j=0 ;j < s.size(); j++ ) {
                Card tempCard = new Card(s.get(j));
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
        TextView incomplete;

        ViewHolder(View v)
        {
            cardImage = (ImageView) v.findViewById(R.id.imageView);
            cardActivity = (TextView) v.findViewById(R.id.cardActivity);
            cardDate = (TextView) v.findViewById(R.id.cardDate);
            incomplete = (TextView) v.findViewById(R.id.incomplete);

        }
    }

    /*
    * This override method used to Assign Evidence cards to rows in the grid layout and
    * return the view of each card
    * @Param int i
    * @Param View the view
    * @Return returns the view requested
    * */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Card temp = list.get(i);
        ViewHolder holder =null;
        if (view == null)
        {
            //inflate view and assign to holder
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.single_item_main, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        //get each object from the Card Class and set parameters
        holder.cardDate.setText(temp.date);
        holder.cardActivity.setText(temp.activityName);
        if (!temp.completionStatus.equals("")){
            holder.incomplete.setText(temp.completionStatus);
            holder.incomplete.setTextColor(Color.RED);

        }

        File imgFile = new File("/storage/emulated/0/Pictures/KinderThumbnails/" + temp.imageFileName);

        if (!temp.imageFileName.equals("") && imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.cardImage.setImageBitmap(myBitmap);

        } else {
            holder.cardImage.setImageResource(R.drawable.img_not_found);

            Log.d(TAG, "File Not exists : " + temp.imageFileName);
        }
        return view;
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
    String imageFileName;
    String evidID;
    String completionStatus;
    private static final String TAG= "Fahad/ Card";

    /*
    * Constructor
    * @Param String image name
    * @param String info contains activity name and date
    * */
    Card(String data)
    {
        if (data.length() !=0 ) {
            this.evidID = data.substring(0, data.indexOf("|"));
            this.date = data.substring(data.indexOf("|") + 1, data.indexOf(","));
            this.activityName = data.substring(data.indexOf(",") + 1, data.indexOf(":"));
            this.imageFileName = data.substring(data.indexOf(":") + 1, data.indexOf("$"));
            this.completionStatus = data.substring(data.indexOf("$") + 1, data.length());
        }


        if (completionStatus.equals("false")){
            this.completionStatus = "Incomplete";
        } else {
            this.completionStatus="";
        }
        Log.d(TAG, evidID +":"+ date + activityName + imageFileName + completionStatus);

    }

    /*
    * A method to get the evidence ID
    * @Return String Id
    * */
    public String getID(){

        return evidID;
    }

}

