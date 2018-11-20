package com.example.venkat.connectr1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.venkat.connectr1.adapters.Adapter_NavigationDrawerList;
import com.example.venkat.connectr1.fragments.Fragment_Books;
import com.example.venkat.connectr1.fragments.Fragment_Candy;
import com.example.venkat.connectr1.fragments.Fragment_Chocolates;
import com.example.venkat.connectr1.fragments.Fragment_Default;
import com.example.venkat.connectr1.fragments.Fragment_Detail_Electronics;
import com.example.venkat.connectr1.fragments.Fragment_Discounted_TopSelling_NewArrivals_RecyclerView;
import com.example.venkat.connectr1.fragments.Fragment_Electronics;
import com.example.venkat.connectr1.fragments.Fragment_IPhones;
import com.example.venkat.connectr1.fragments.Fragment_IpadTablets;
import com.example.venkat.connectr1.fragments.Fragment_MilkProducts;
import com.example.venkat.connectr1.fragments.Fragment_Movies;
import com.example.venkat.connectr1.fragments.Fragment_Music;
import com.example.venkat.connectr1.jsonlocal.Data_NavgationDrawer;
import com.example.venkat.connectr1.pageradapters.PagerAdapter_DiscountedProducts_TopSellingProducts_NewArrivals;
import com.example.venkat.connectr1.utilities.SlidingTabLayout;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import java.util.HashMap;

/**
 * Created by rambabu on 6/29/2015.
 */


public class Activity_DiscountedProducts_TopSellingProducts_NewArrivals_ViewPager extends AppCompatActivity implements Fragment_Discounted_TopSelling_NewArrivals_RecyclerView.OnListItemSelectedListener {

    PagerAdapter_DiscountedProducts_TopSellingProducts_NewArrivals myPagerAdapter;
    ViewPager mViewPager;
    int currentItem;
    private SlidingTabLayout materialTabs;
    Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    RecyclerView myNavList;
    Adapter_NavigationDrawerList navAdapter;
    RelativeLayout nav_DrawSelection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__view_pager);


        myPagerAdapter = new PagerAdapter_DiscountedProducts_TopSellingProducts_NewArrivals(getSupportFragmentManager(), 3);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        materialTabs = (SlidingTabLayout) findViewById(R.id.material_tabs);

        //nav_DrawSelection = (RelativeLayout)findViewById(R.id.navigation_drawer);
        mToolbar = (Toolbar) findViewById(R.id.app_tool_bar);
        setSupportActionBar(mToolbar);

//        myNavList = (RecyclerView)findViewById(R.id.navdraw_recycler);
//        myNavList.setLayoutManager(new LinearLayoutManager(this));
//
//        Data_NavgationDrawer navdata = new Data_NavgationDrawer();
//        navdata.loadNavData();
//        navAdapter = new Adapter_NavigationDrawerList(navdata.getNavItems(), this);
//
//        navAdapter.setOnItemClickListner(new Adapter_NavigationDrawerList.OnItemClickListner() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                selectedView(position);
//            }
//        });
//        myNavList.setAdapter(navAdapter);
//        mDrawerLayout = (DrawerLayout)findViewById(R.id.layout_drawer);
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.Open_Drawer, R.string.Close_Drawer);
//        mDrawerToggle.syncState();


        mViewPager.setAdapter(myPagerAdapter);
        currentItem = mViewPager.getCurrentItem();
        int page_to_open = getIntent().getIntExtra("openPage", 5);
        if (page_to_open == 5)
            mViewPager.setCurrentItem(0);
        else if (page_to_open == 10)
            mViewPager.setCurrentItem(1);
        else
            mViewPager.setCurrentItem(2);

        materialTabs.setViewPager(mViewPager);


        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {

            @Override
            public void transformPage(View page, float position) {
                final float normalized_position = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalized_position / 2 + 0.5f);
                page.setScaleY(normalized_position / 2 + 0.5f);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_activities, menu);
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

        if (id == R.id.home) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }



        if (id == R.id.cart) {
            Intent i = new Intent(this, Activity_Cart.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.login) {
            Intent login_intent = new Intent(this, Activity_Login.class);
            startActivity(login_intent);
            return true;
        }

        if (id == R.id.orderhistory) {
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

        if (id == R.id.concept) {
            Intent i = new Intent(this, Activity_Concept.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.references) {
            Intent i = new Intent(this, Activity_References.class);
            startActivity(i);
            return true;
        }


        if (id == R.id.aboutus) {
            Intent i = new Intent(this, Activity_AboutUs.class);
            startActivity(i);
            return true;
        }


        if ((id == R.id.logout)) {
            // Set up a progress dialog
            if (!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setMessage(getString(R.string.progress_logout));
                dialog.show();
                ParseUser.logOut();
                dialog.dismiss();
                Toast.makeText(this, "log out successful", Toast.LENGTH_LONG).show();
                callingMainActivity();
            } else
                Toast.makeText(this, "Current User: Anonymous", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void callingMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Trigger", false);
        startActivity(intent);
    }


//    @Override
//    public void onFragmentInteraction(int sint){
//        //you can leave it empty
//
//    }


    @Override
    public void onListItemSelected(int position, HashMap<String, ?> movie) {
        if (currentItem == 0) {
            String ARG_MOVIE_INDEX = "";
            Intent intent = new Intent(this, Activity_Detail_DiscountedProducts_TopSellingProducts_NewArrivals_ViewPager.class);
            int index = mViewPager.getCurrentItem();
            intent.putExtra("index", index);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }


}
