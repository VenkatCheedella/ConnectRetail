package com.example.venkat.connectr1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.venkat.connectr1.adapters.Adapter_NavigationDrawerList;
import com.example.venkat.connectr1.cloud.Data_CartObject;
import com.example.venkat.connectr1.fragments.Fragment_Books;
import com.example.venkat.connectr1.fragments.Fragment_Candy;
import com.example.venkat.connectr1.fragments.Fragment_Chocolates;
import com.example.venkat.connectr1.fragments.Fragment_Default;
import com.example.venkat.connectr1.fragments.Fragment_Detail_Electronics;
import com.example.venkat.connectr1.fragments.Fragment_Electronics;
import com.example.venkat.connectr1.fragments.Fragment_IPhones;
import com.example.venkat.connectr1.fragments.Fragment_IpadTablets;
import com.example.venkat.connectr1.fragments.Fragment_MilkProducts;
import com.example.venkat.connectr1.fragments.Fragment_Movies;
import com.example.venkat.connectr1.fragments.Fragment_Music;
import com.example.venkat.connectr1.fragments.Fragment_TopSellingProducts_ViewPager;
import com.example.venkat.connectr1.fragments.Fragment_hompage_viewflipper;
import com.example.venkat.connectr1.jsonlocal.Data_NavgationDrawer;
import com.example.venkat.connectr1.utilities.ImageHelper;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseCrashReporting;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.HashMap;
//import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity
        implements Fragment_Electronics.OnListItemSelectedListener, Fragment_IpadTablets.OnListItemSelectedListener
        , Fragment_IPhones.OnListItemSelectedListener, Fragment_Candy.OnListItemSelectedListener
        , Fragment_Chocolates.OnListItemSelectedListener, Fragment_Books.OnListItemSelectedListener
        , Fragment_Music.OnListItemSelectedListener, Fragment_Movies.OnListItemSelectedListener
        , Fragment_MilkProducts.OnListItemSelectedListener {

    Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    RecyclerView myNavList;
    Adapter_NavigationDrawerList navAdapter;
    RelativeLayout nav_DrawSelection;
    public ImageView user_prof_pic;
    private Boolean trigger = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trigger = getIntent().getBooleanExtra("Trigger", true);
//
//        // Initialize Crash Reporting.
//        if(trigger == true) {
//            ParseCrashReporting.enable(this);
//            // Enable Local Datastore.
//            Parse.enableLocalDatastore(this);
//
//            //Register the class- Data_CartObject
//            ParseObject.registerSubclass(Data_CartObject.class);
//
//            // Enable the local datastore
//            Parse.enableLocalDatastore(getApplicationContext());
//
//            // Add your initialization code here
//            Parse.initialize(this, "Ffwykq9dpASYCdQf46kSABr6HaNc9FkowWxbcpa5", "AhIe9wFMdII2jWFNivcCSArdLg2KRgj57Rzj7LTa");
//            ParseInstallation.getCurrentInstallation().saveInBackground();
//
//            ParseUser.enableAutomaticUser();
//            ParseACL defaultACL = new ParseACL();
//            // Optionally enable public read access.
//            // defaultACL.setPublicReadAccess(true);
//            ParseACL.setDefaultACL(defaultACL, true);
//        }






        nav_DrawSelection = (RelativeLayout) findViewById(R.id.navigation_drawer);
        mToolbar = (Toolbar) findViewById(R.id.app_tool_bar);
        setSupportActionBar(mToolbar);

        myNavList = (RecyclerView) findViewById(R.id.navdraw_recycler);
        myNavList.setLayoutManager(new LinearLayoutManager(this));

        Data_NavgationDrawer navdata = new Data_NavgationDrawer();
        navdata.loadNavData();
        navAdapter = new Adapter_NavigationDrawerList(navdata.getNavItems(), this);

        navAdapter.setOnItemClickListner(new Adapter_NavigationDrawerList.OnItemClickListner() {
            @Override
            public void OnItemClick(View view, int position) {
                selectedView(position);
            }
        });
        myNavList.setAdapter(navAdapter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.layout_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.Open_Drawer, R.string.Close_Drawer);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainactivity_container, new Fragment_hompage_viewflipper())
                .commit();

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private void selectedView(int position) {
        switch (position) {
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, new Fragment_hompage_viewflipper())
                        .commit();
                break;
            case 4:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, Fragment_Electronics.newInstance())
                        .commit();
                break;
            case 5:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, Fragment_IpadTablets.newInstance())
                        .commit();
                break;
            case 6:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, Fragment_IPhones.newInstance())
                        .commit();
                break;
            case 9:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, Fragment_Candy.newInstance())
                        .commit();
                break;
            case 10:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, Fragment_Chocolates.newInstance())
                        .commit();
                break;
            case 13:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, Fragment_MilkProducts.newInstance())
                        .commit();
                break;
            case 16:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, Fragment_Books.newInstance())
                        .commit();
                break;
            case 17:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, Fragment_Music.newInstance())
                        .commit();
                break;
            case 18:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainactivity_container, Fragment_Movies.newInstance())
                        .commit();
                break;
            default:
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.mainactivity_container, new Fragment_hompage_viewflipper())
//                        .commit();
                break;
        }

        mDrawerLayout.closeDrawer(nav_DrawSelection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == R.id.cart) {
            Intent i = new Intent(this, Activity_Cart.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.login) {
            if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
                Intent login_intent = new Intent(this, Activity_Login.class);
                startActivity(login_intent);
                return true;
            }
            else
            {
                Toast.makeText(this, "Already LoggedIn", Toast.LENGTH_SHORT).show();
                return true;
            }

        }

        if(id == R.id.orderhistory)
        {
            Intent i = new Intent(this, Activity_OrderHistory.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.concept) {
            Intent i = new Intent(this,Activity_Concept.class);
            startActivity(i);
            return true;
        }

        if(id == R.id.references){
            Intent i = new Intent(this,Activity_References.class);
            startActivity(i);
            return true;
        }



        if(id == R.id.aboutus){
            Intent i = new Intent(this, Activity_AboutUs.class);
            startActivity(i);
            return true;
        }



        if((id == R.id.logout)){
            // Set up a progress dialog
            if(!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setMessage(getString(R.string.progress_logout));
                dialog.show();
                ParseUser.logOut();
                dialog.dismiss();
                Toast.makeText(this, "log out successful", Toast.LENGTH_LONG).show();
                callingMainActivity();
            }
            else
                Toast.makeText(this, "Current User: Anonymous", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    void callingMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("Trigger", false);
        startActivity(intent);
    }

    @Override
    public void OnListElectronicItemSelected(int position, HashMap<String, ?> electronic_object) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainactivity_container, Fragment_Detail_Electronics.newInstance(electronic_object))
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void OnListConfectionaryItemSelected(int position, HashMap<String, ?> confectionary_object) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainactivity_container, Fragment_Detail_Electronics.newInstance(confectionary_object))
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void OnListDairyItemSelected(int position, HashMap<String, ?> dairy_object) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainactivity_container, Fragment_Detail_Electronics.newInstance(dairy_object))
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void OnListMediaItemSelected(int position, HashMap<String, ?> media_object) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainactivity_container, Fragment_Detail_Electronics.newInstance(media_object))
                .addToBackStack(null)
                .commit();
    }

}
