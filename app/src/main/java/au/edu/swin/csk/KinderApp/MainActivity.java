package au.edu.swin.csk.KinderApp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/*
*
* Main Class
* @Author Fahad Alhamed
* @Author Somesh
*
* */
public class MainActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener,
        DialogInterface.OnClickListener,
        View.OnClickListener,
        DrawerLayout.DrawerListener {

    private static final  String TAG="App/ MainActivity";
    /*
    * Drawer component
    */
    private ActionBarDrawerToggle actionBarDrawerToggle;
    /*
    * Drawer layout
    */
    private DrawerLayout drawerLayout;
    /*
    * Navigation lists
    */
    private ListView navList1;
    private ListView navList2;
    private ListView navList3;
    /*
    * Handling fragments transitioning */
    private FragmentManager manager;
    private int groupID = 1;
    private FragmentTransaction transaction;
    ImageButton runCommand;
    ImageButton cancelButton;
    Spinner spinner;
    LinearLayout linear;
    AlertDialog ad;
    KinderDBCon k;
    TestDB testDB;
    ActionBar actionBar;

    /*
    * This method is call when the app is running to create all components
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Drawer layout
        linear = (LinearLayout) findViewById(R.id.drawer_linear);
        //add button to create new EvidCards
        runCommand = (ImageButton) findViewById(R.id.runCommand);
        runCommand.setOnClickListener(this);
        runCommand.bringToFront();

        //add button to create new EvidCards
        cancelButton = (ImageButton) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
        cancelButton.setVisibility(View.INVISIBLE);
        cancelButton.bringToFront();

        //constructing drawer
        createDrawer();
        //managing fragments
        manager = getFragmentManager();
        //create database object
        k=new KinderDBCon(this);
        k.open();
    }

    /*This function creates navigation drawer and
    * adds all components to the drawer and bring to front button and main layout when slide closed */
    private void createDrawer(){
        //construct drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer );

        linear.bringToFront();
        //sets listener for drawer
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //construct spinner
        spinner = (Spinner) findViewById(R.id.spinner_group);
        ArrayAdapter spinAdapter =  ArrayAdapter.createFromResource(this, R.array.groups, R.layout.spinner_item);
        spinAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(spinAdapter);

        //1 Construct Filter list
        navList1 = (ListView) findViewById(R.id.nav_list1);
        ArrayList<String> navArray1 = new ArrayList<String>();
        navArray1.add("Child");
        navArray1.add("Activity Type");
        navArray1.add("Learning Outcome");

        navList1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> listAdapter1 = new ArrayAdapter<String>(this, R.layout.row_layout_drawer, navArray1 );
        navList1.setAdapter(listAdapter1);
        navList1.setOnItemClickListener(this);

        //2 Construct Edit List
        navList2 = (ListView) findViewById(R.id.nav_list2);
        ArrayList<String> navArray2 = new ArrayList<String>();
        navArray2.add("Child");
        navArray2.add("Activity");
        navArray2.add("Learning Outcome");

        navList2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> listAdapter2 = new ArrayAdapter<String>(this, R.layout.row_layout_drawer, navArray2 );
        navList2.setAdapter(listAdapter2);
        navList2.setOnItemClickListener(this);

        //3 Construct Options list
        navList3 = (ListView) findViewById(R.id.nav_list3);
        ArrayList<String> navArray3 = new ArrayList<String>();
        navArray3.add("Summary");
        navArray3.add("Sort Order");
        navArray3.add("Complete");

        navList3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> listAdapter3 = new ArrayAdapter<String>(this, R.layout.row_layout_drawer, navArray3 );
        navList3.setAdapter(listAdapter3);
        navList3.setOnItemClickListener(this);
        //End of drawer items

        //display drawer Icon in action bar
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /*
     * Deals with different orientations by syncing status of drawer button
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuInflater mif = getMenuInflater(); //search icon
        mif.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //handles the action bar components
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id==R.id.back_button)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("id", groupID);
            showMainFragment(bundle);
        }
        else if (id == R.id.exit_app) {
            finish();
            return true;
        }else if (id == R.id.action_import){
            //imports data and populate DB tables
            //Log.d(TAG, "Group"+ String.valueOf(groupID));
            testDB= new TestDB(k);
            //If users clicks on import, we call the showMainFragment function and pass the current group ID, 1, through a bundle.
            Bundle bundle= new Bundle();
            bundle.putInt("id", groupID);
            showMainFragment(bundle);
        //handles drawer button when clicked
        }else if (id == android.R.id.home){
            if (drawerLayout.isDrawerOpen(linear)) {
                //drawerLayout.bringToFront();
                runCommand.setVisibility(View.INVISIBLE);
                runCommand.setClickable(false);
                drawerLayout.closeDrawer(linear);
                //linear.bringToFront();
                runCommand.bringToFront();
                runCommand.setVisibility(View.VISIBLE);
                runCommand.setClickable(true);
                //add bring to front for main screen layout and button
                //testing ------------
                //mainGrid.bringToFront();
                //---------------

            }else {
                drawerLayout.openDrawer(linear);
                linear.bringToFront();            }
            }
            /*if (drawerLayout.isDrawerOpen(linear)) {
                //drawerLayout.bringToFront();
                //linear.bringToFront();
                drawerLayout.closeDrawer(linear);
                //linear.bringToFront();
                //cancelButton.setVisibility(View.VISIBLE);
                cancelButton.bringToFront();
            }else {
                drawerLayout.openDrawer(linear);
                drawerLayout.bringToFront();
                linear.bringToFront();
                //cancelButton.setVisibility(View.INVISIBLE);
            }
        }*/
        return super.onOptionsItemSelected(item);
    }
    /*
    * Listens to items clicked within drawer*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        View a = (View) view.getParent();
        if (a.getId() == R.id.nav_list1 || a.getId() == R.id.nav_list2 || a.getId() == R.id.nav_list3 ) {
            showDialogAlert(a, position); //calling showDialogAlert
            drawerLayout.closeDrawer(linear);
        }
    }

    /*
    * This method handles the drop down for groups
    * */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (view != null) {
            TextView groupName = (TextView) view;
            drawerLayout.closeDrawer(linear);
            groupID = position + 1;
            Bundle bundle = new Bundle();
            bundle.putInt("id", groupID);
            //Instead of calling the mainAdapter directly, I'm calling the mainFragment from where we'll call the mainAdapter
            showMainFragment(bundle);
            actionBar.setTitle(groupName.getText()); //set title to group selected
        } else {actionBar.setTitle("KinderApp");}
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    /*
    * Array to hold children list based on group selected
    */
    ArrayAdapter<String> alertAdapterChild;
    /*
    * Array to hold activities list
    */
    ArrayAdapter<String> alertAdapterActivity;
    /*
    * Array to hold Learning Outcome Codes list
    */
    ArrayAdapter<Double> alertAdapterLoCode;
    public void showDialogAlert(View view, int position){
        //create alertdialog to show a list
        drawerLayout.closeDrawer(linear);

        if (view.getId() == R.id.nav_list1) {
            //Log.d(TAG, String.valueOf(R.id.view));
            //Log.d(TAG, String.valueOf(position));
            if (position == 0) {
                alertAdapterChild = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, k.getChildNames(groupID));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("select from list");
                //sets adapter and listener for items in the alert list
                builder.setAdapter(alertAdapterChild, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String fullName = alertAdapterChild.getItem(which);
                        Log.d(TAG, "This child is selected : " + fullName);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", groupID);
                        bundle.putString("fullName", fullName);
                        showMainFragment(bundle);
                        cancelButton.setVisibility(View.VISIBLE);
                        cancelButton.bringToFront();
                    }
                });
                ad = builder.create();
                ad.show();

            } else if (position == 1) {
                alertAdapterActivity = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, k.getActivityNames());
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("select from list");
                //sets adapter and listener for items in the alert list
                builder.setAdapter(alertAdapterActivity, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String activity = alertAdapterActivity.getItem(which);
                        Log.d(TAG, "This child is selected : " + activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", groupID);
                        bundle.putString("activity", activity);
                        showMainFragment(bundle);
                        cancelButton.setVisibility(View.VISIBLE);
                        cancelButton.bringToFront();
                    }
                });
                ad = builder.create();
                ad.show();
            } else if (position == 2) {
                alertAdapterLoCode = new ArrayAdapter<Double>(this, android.R.layout.select_dialog_item, k.getLOCode());
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("select from list");
                //sets adapter and listener for items in the alert list
                builder.setAdapter(alertAdapterLoCode, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Log.d(TAG, "This child is selected : " + String.valueOf(which));
                        alertAdapterLoCode.getItem(which);
                        cancelButton.setVisibility(View.VISIBLE);
                    }
                });
                ad = builder.create();
                ad.show();
            } else ad.dismiss();
        } else {
            Toast.makeText(this, " Not implemented yet! " , Toast.LENGTH_SHORT).show();
        }
    }

    /*
    *   The following method calls the mainFragment
    *   passes a bundle containing info
    */
    public void showMainFragment(Bundle bundle)
    {
        MainFragment mainFragment= new MainFragment();
        mainFragment.setArguments(bundle);
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_holder, mainFragment);
        transaction.commit();
    }

    /*
    *   The following function calls the pictureFragment
    */
    public void showFormFragment()
    {
        FormFragment formFragment = new FormFragment();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_holder, formFragment);
        runCommand.setVisibility(View.INVISIBLE);
        runCommand.setClickable(false);
        transaction.commit();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {}

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        //drawerLayout.bringToFront();
        drawerView.bringToFront();
        drawerLayout.bringChildToFront(drawerView);
        drawerLayout.requestLayout();
        //cancelButton.setVisibility(View.INVISIBLE);

        /*super.onDrawerSlide(drawerView, slideOffset);
        mDrawerLayout.bringChildToFront(drawerView);
        mDrawerLayout.requestLayout();*/

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        drawerView.bringToFront();
        drawerLayout.bringChildToFront(drawerView);
        drawerLayout.requestLayout();
//        runCommand.setVisibility(View.INVISIBLE);
//        runCommand.setClickable(false);
        //cancelButton.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        //linear.bringToFront();
        //cancelButton.setVisibility(View.VISIBLE);
        cancelButton.bringToFront();
        runCommand.setVisibility(View.VISIBLE);
        runCommand.setClickable(true);

    }

    @Override
    public void onDrawerStateChanged(int newState) {
     /*   if (newState == DrawerLayout.STATE_DRAGGING) {
                // starts opening
            drawerLayout.bringChildToFront(linear);
            linear.bringToFront();
            drawerLayout.requestLayout();
        } else {
                // closing drawer
            cancelButton.bringToFront();
        }*/
    }

    /*
    * This method is called when back button is clicked
    * */
    @Override
    public void onBackPressed() {
        // If the drawer is open, then back button should just close the drawer
       if (drawerLayout != null && drawerLayout.isDrawerOpen(linear)) {
            drawerLayout.closeDrawer(linear);
            return;
        }
        // Get current active fragment
        Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_holder);

        // Current fragment is Main fragment, close the app
        if (currentFrag instanceof MainFragment){
            finish();}
        // Current fragment is not the main fragment, show the main fragment
       else if (currentFrag instanceof FormFragment) {
            runCommand.setVisibility(View.VISIBLE);
            runCommand.setClickable(true);

            Bundle bundle= new Bundle();
            bundle.putInt("id", groupID);
            showMainFragment(bundle);
        }
        else{
            super.onBackPressed();
            k.close();
            //moveTaskToBack(true);
        }
    }

    @Override
    public void onClick(View v) {

    if (v.getId() == R.id.runCommand) {
        showFormFragment();
    } else if (v.getId() == R.id.cancelButton){
        Bundle bundle = new Bundle();
        bundle.putInt("id", groupID);
        showMainFragment(bundle);
        cancelButton.setVisibility(View.INVISIBLE);
        }
    }
}
