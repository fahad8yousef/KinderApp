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

public class MainActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener,
        DialogInterface.OnClickListener,
        DrawerLayout.DrawerListener {

    private static final  String TAG="App/ MainActivity";
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList1;
    private ListView navList2;
    private ListView navList3;
    private FragmentManager manager;
    private int groupID = 1;
    private FragmentTransaction transaction;
    ImageButton runCommand;
    Spinner spinner;
    LinearLayout linear;
    AlertDialog ad;
    KinderDBCon k;
    TestDB testDB;
    ActionBar actionBar;

    /*
    * need listening to drawer on slide */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Drawer layout
        linear = (LinearLayout) findViewById(R.id.drawer_linear);
        //add button to create new EvidCards
        runCommand = (ImageButton) findViewById(R.id.runCommand);
        runCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFormFragment();

            }
        });
        runCommand.bringToFront();
        //constructing drawer
        createDrawer();
        //managing fragments
        manager = getFragmentManager();

        //#############################################
        //create database object
        //#############################################
        k=new KinderDBCon(this);
        k.open();
        //insert into database
        //testDB = new TestDB(k); //to initiate testing //comment after inserting data to avoid errors
        //k.close();
        //#############################################


    }

    /*This function creates navigation drawer and
    * adds all components to the drawer and bring to front button and main layout when slide closed */
    private void createDrawer(){
        //construct drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer );

        linear.bringToFront();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }else if (id == R.id.exit_app) {
            finish();
            return true;
        }else if (id == R.id.action_import){
            Log.d(TAG, String.valueOf(groupID));
            testDB= new TestDB(k);
            //If users clicks on import, we call the showMainFragment function and pass the current group ID, 1, through a bundle.
            Bundle bundle= new Bundle();
            bundle.putInt("id", groupID);
            showMainFragment(bundle);

        }else if (id == android.R.id.home){

            if (drawerLayout.isDrawerOpen(linear)) {
                drawerLayout.bringToFront();
                drawerLayout.closeDrawer(linear);
                linear.bringToFront();
                //add bring to front for main screen layout and button
                //testing ------------
                //mainGrid.bringToFront();
                //---------------

            }else {
                drawerLayout.openDrawer(linear);
                linear.bringToFront();            }
        }

        return super.onOptionsItemSelected(item);
    }
    /*
    * Listens to items clicked */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        View a = (View) view.getParent();
        if (a.getId() == R.id.nav_list1 || a.getId() == R.id.nav_list2 || a.getId() == R.id.nav_list3 ) {
            showDialogAlert(a, position); //calling showDialogAlert
            drawerLayout.closeDrawer(linear);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //if (view.getParent() == spinner){

            drawerLayout.closeDrawer(linear);
            groupID = position + 1;
            Bundle bundle = new Bundle();
            bundle.putInt("id", groupID);

            //Instead of calling the mainAdapter directly, I'm calling the mainFragment from where we'll call the mainAdapter
            showMainFragment(bundle);
            TextView groupName = (TextView) view;
            actionBar.setTitle(groupName.getText()); //set title to group selected
        //}
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}


    ArrayAdapter<String> alertAdapterChild;
    ArrayAdapter<String> alertAdapterActivity;
    ArrayAdapter<Double> alertAdapterLoCode;

    public void showDialogAlert(View view, int position){
        //create alertdialog to show a list
        //Toast.makeText(this, " you selected:  "+ navList1.getPositionForView(view) , Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(linear);

        if (view.getId() == R.id.nav_list1) {
            Log.d(TAG, String.valueOf(R.id.view));
            Log.d(TAG, String.valueOf(position));
            if (position == 0) {
                alertAdapterChild = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, k.getChildNames(groupID));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("select from list");
                builder.setAdapter(alertAdapterChild, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "This child is selected : " + String.valueOf(which));
                        String childName = alertAdapterChild.getItem(which);
                      /*  Bundle bundle = new Bundle();
                        bundle.putInt("id", groupID );
                        bundle.putString("childName", childName);
                        showMainFragment(bundle);*/
                    }
                });
                ad = builder.create();
                ad.show();

            } else if (position == 1) {
                alertAdapterActivity = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, k.getActivityNames());
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("select from list");
                builder.setAdapter(alertAdapterActivity, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "This child is selected : " + String.valueOf(which));
                        alertAdapterActivity.getItem(which);
                    }
                });
                ad = builder.create();
                ad.show();
            } else if (position == 2) {
                alertAdapterLoCode = new ArrayAdapter<Double>(this, android.R.layout.select_dialog_item, k.getLOCode());
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("select from list");
                builder.setAdapter(alertAdapterLoCode, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "This child is selected : " + String.valueOf(which));
                        alertAdapterLoCode.getItem(which);
                    }
                });
                ad = builder.create();
                ad.show();
            } else ad.dismiss();
        } else {
            Toast.makeText(this, " Not implemented yet! " , Toast.LENGTH_SHORT).show();
        }
    }


    //The following function calls the mainFragment
    public void showMainFragment(Bundle bundle)
    {
        MainFragment mainFragment= new MainFragment();
        mainFragment.setArguments(bundle);
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_holder, mainFragment);
        transaction.commit();
    }

    //The following function calls the pictureFragment
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
    public void onClick(DialogInterface dialog, int which) {

        if (which == 0){


        }else {

            Toast.makeText(this, " second  " , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        linear.bringToFront();
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        linear.bringToFront();
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        linear.bringToFront();
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
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
            bundle.putInt("id", 1);
            showMainFragment(bundle);
        }

        else{
            super.onBackPressed();
            //moveTaskToBack(true);
        }
    }
}
