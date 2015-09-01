package au.edu.swin.csk.prototype;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList;
    private ArrayList<String> navArray;
    //private ArrayList<String> groupArray;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    ImageButton runCommand;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runCommand = (ImageButton)findViewById(R.id.runCommand);
        runCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Selected Button", Toast.LENGTH_SHORT).show();
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer );
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //spinner

        spinner = (Spinner) findViewById(R.id.spinnerGroup);
        ArrayAdapter spinAdapter =  ArrayAdapter.createFromResource(this, R.array.groups, android.R.layout.simple_spinner_item);
        //spinner.setOnItemSelectedListener(this);
        //spinner.setAdapter(spinAdapter);



        navList = (ListView) findViewById(R.id.nav_list);
        navArray = new ArrayList<String>();
        navArray.add("Koala");
        navArray.add("Filter");
        navArray.add("Edit");
        navArray.add("Options");
        //navArray.add("4");
        //navArray.add("5");
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, navArray );
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        manager = getSupportFragmentManager();

        loadSelection(0);

    }

    private void loadSelection(int i){
        navList.setItemChecked(i, true);

        switch (i) {
            case 0:
                //home
                Home home = new Home();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder,home);
                transaction.commit();
                break;
            case 1:
                Frag1 frag1 = new Frag1();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder,frag1);
                transaction.commit();
                break;
            case 2:
                Frag2 frag2 = new Frag2();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder,frag2);
                transaction.commit();
                break;
            case 3:
                Frag3 frag3 = new Frag3();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder,frag3);
                transaction.commit();
                break;
            case 4:
                Frag4 frag4 = new Frag4();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder,frag4);
                transaction.commit();
                break;
            case 5:
                Frag5 frag5 = new Frag5();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_holder,frag5);
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
            if (drawerLayout.isDrawerOpen(navList)) {
                drawerLayout.closeDrawer(navList);
            }else {
                drawerLayout.openDrawer(navList);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        loadSelection(position);

        drawerLayout.closeDrawer(navList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

/*
        TextView group = (TextView) view;
        Toast.makeText(this, "you selected"+ group.getText(), Toast.LENGTH_SHORT).show();
*/

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
