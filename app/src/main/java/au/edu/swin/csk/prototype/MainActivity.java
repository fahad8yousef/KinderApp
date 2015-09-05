package au.edu.swin.csk.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList1;
    private ListView navList2;
    private ListView navList3;
    private FragmentManager manager;
    ImageButton runCommand;
    Spinner spinner;
    LinearLayout linear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear = (LinearLayout) findViewById(R.id.drawer_linear);

        runCommand = (ImageButton) findViewById(R.id.runCommand);

        runCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // (( MainActivity)getActivity()).switchToFragmentOne();
                Intent intent = new Intent(MainActivity.this, Picture.class);
                startActivity(intent);
            }
        });
        createDrawer();
        createAddbutton();
        manager = getSupportFragmentManager();
        loadSelection(0);
        
    }
    
    private void createDrawer(){

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer );
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //spinner

        spinner = (Spinner) findViewById(R.id.spinner_group);
        ArrayAdapter spinAdapter =  ArrayAdapter.createFromResource(this, R.array.groups, R.layout.spinner_item);
        spinAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(spinAdapter);

///////////1
        navList1 = (ListView) findViewById(R.id.nav_list1);
        ArrayList<String> navArray1 = new ArrayList<String>();
        navArray1.add("Child");
        navArray1.add("Activity Type");
        navArray1.add("Learning Outcome");

        navList1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> listAdapter1 = new ArrayAdapter<String>(this, R.layout.row_layout_drawer, navArray1 );
        navList1.setAdapter(listAdapter1);
        navList1.setOnItemClickListener(this);

//////////////2
        navList2 = (ListView) findViewById(R.id.nav_list2);
        ArrayList<String> navArray2 = new ArrayList<String>();
        navArray2.add("Child");
        navArray2.add("Activity");
        navArray2.add("Learning Outcome");

        navList2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> listAdapter2 = new ArrayAdapter<String>(this,  R.layout.row_layout_drawer, navArray2 );
        navList2.setAdapter(listAdapter2);
        navList2.setOnItemClickListener(this);

///////////////
        navList3 = (ListView) findViewById(R.id.nav_list3);
        ArrayList<String> navArray3 = new ArrayList<String>();
        navArray3.add("Summary");
        navArray3.add("Sort Order");
        navArray3.add("Complete");

        navList3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> listAdapter3 = new ArrayAdapter<String>(this,  R.layout.row_layout_drawer, navArray3 );
        navList3.setAdapter(listAdapter3);
        navList3.setOnItemClickListener(this);
///////////////

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void createAddbutton(){

        runCommand = (ImageButton)findViewById(R.id.runCommand);
        runCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Selected Button", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadSelection(int i){
        navList1.setItemChecked(i, true);

        FragmentTransaction transaction;
        switch (i) {
            case 0:
                //home
                Home home = new Home();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, home);
                transaction.commit();
                break;
            case 1:
                Frag1 frag1 = new Frag1();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, frag1);
                transaction.commit();
                break;
            case 2:
                Frag2 frag2 = new Frag2();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, frag2);
                transaction.commit();
                break;
            case 3:
                Frag3 frag3 = new Frag3();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, frag3);
                transaction.commit();
                break;
        }

        navList2.setItemChecked(i, true);

        switch (i) {
            case 0:
                //home
                Home home = new Home();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, home);
                transaction.commit();
                break;
            case 1:
                Frag1 frag1 = new Frag1();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, frag1);
                transaction.commit();
                break;
            case 2:
                Frag2 frag2 = new Frag2();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, frag2);
                transaction.commit();
                break;
            case 3:
                Frag3 frag3 = new Frag3();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, frag3);
                transaction.commit();
                break;
        }

        navList3.setItemChecked(i, true);

        switch (i) {
            case 0:
                //home
                Home home = new Home();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, home);
                transaction.commit();
                break;
            case 1:
                Frag1 frag1 = new Frag1();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, frag1);
                transaction.commit();
                break;
            case 2:
                Frag2 frag2 = new Frag2();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, frag2);
                transaction.commit();
                break;
            case 3:
                Frag3 frag3 = new Frag3();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder, frag3);
                transaction.commit();
                break;
        }

    }


    /*
     * Deals with different orientations
     *  */
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
        }else if (id == android.R.id.home){

            if (drawerLayout.isDrawerOpen(linear)) {
                drawerLayout.closeDrawer(linear);
            }else {
                drawerLayout.openDrawer(linear);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        loadSelection(position);
        drawerLayout.closeDrawer(linear);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TextView group = (TextView) view;
        //Toast.makeText(this, "you selected"+ group.getText(), Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(linear);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
