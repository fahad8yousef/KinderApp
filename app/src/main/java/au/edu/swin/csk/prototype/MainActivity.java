package au.edu.swin.csk.prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

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
    ImageButton runCommand;
    Spinner spinner;
    LinearLayout linear;
    AlertDialog ad;
    GridView mainGrid;
    KinderDBCon k;
    TestDB testDB;
    //Context c;

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
                // (( MainActivity)getActivity()).switchToFragmentOne();
                Intent intent = new Intent(MainActivity.this, Picture.class);
                startActivity(intent);
            }
        });
        runCommand.bringToFront();
        //constructing drawer
        createDrawer();
        //managing fragments
        manager = getSupportFragmentManager();

        //#############################################
        //create database object
        //#############################################
        k = new KinderDBCon(this);
        k.open(); //open database
        //insert into database
        testDB = new TestDB(k); //to initiate testing //comment after inserting data to avoid errors
        //k.close();
        //#############################################

        //Main screen layout
        mainGrid = (GridView)findViewById(R.id.main_grid);
        mainGrid.setAdapter(new MainAdapter(this, k, groupID));
        mainGrid.setOnItemClickListener(this);

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
        ActionBar actionBar = getSupportActionBar();
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
        }else if (id == R.id.exit_app){
            finish();
            return true;
            //needs fixing bugs
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
        if (a.getId() == R.id.nav_list1 || a.getId() == R.id.nav_list2 || a.getId() == R.id.nav_list3 ){
            showDialogAlert(a, position); //calling showDialogAlert
            drawerLayout.closeDrawer(linear);

        } else {

                Toast.makeText(getApplicationContext(),
                    "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Picture.class);
            startActivity(intent);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView groupName = (TextView) view;
        drawerLayout.closeDrawer(linear);
        groupID = position + 1;
        mainGrid.setAdapter(new MainAdapter(this , k, groupID));
        //Toast.makeText(this, "you selected group: "+ groupName.getText() , Toast.LENGTH_SHORT).show();
        //drawerLayout.clearFocus();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void showDialogAlert(View view, int position){
        //create alertdialog to show a list
        //Toast.makeText(this, " you selected:  "+ navList1.getPositionForView(view) , Toast.LENGTH_SHORT).show();

        if (view.getId() == R.id.nav_list1) {

            if (position == 0) {
                ArrayAdapter<String> alertAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, k.getChildNames(groupID));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("select from list");
                builder.setAdapter(alertAdapter, this);
                ad = builder.create();
                ad.show();

            } else if (position == 1) {
                ArrayAdapter<String> alertAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, k.getActivityNames());
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("select from list");
                builder.setAdapter(alertAdapter, this);
                ad = builder.create();
                ad.show();
            } else if (position == 2) {
                ArrayAdapter<Double> alertAdapter = new ArrayAdapter<Double>(this, android.R.layout.select_dialog_item, k.getLOCode());
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("select from list");
                builder.setAdapter(alertAdapter, this);
                ad = builder.create();
                ad.show();
            } else ad.dismiss();
        } else {
            Toast.makeText(this, " Not implemented yet! " , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {}

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
}
