package com.example.venkat.connectr1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.venkat.connectr1.pageradapters.PagerAdapter_Detail_DiscountedProducts_TopSellingProducts_NewArrivals;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import java.util.HashMap;

//implements recyclerviewFragment.OnFragmentInteractionListener
public class Activity_Detail_DiscountedProducts_TopSellingProducts_NewArrivals_ViewPager extends AppCompatActivity {

    PagerAdapter_Detail_DiscountedProducts_TopSellingProducts_NewArrivals myPagerAdapter;
    ViewPager mViewPager;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__view_pager);

        mToolbar = (Toolbar) findViewById(R.id.app_tool_bar);
        setSupportActionBar(mToolbar);


        int pos = getIntent().getIntExtra("position", 0);
        int index = getIntent().getIntExtra("index", 0);
        myPagerAdapter = new PagerAdapter_Detail_DiscountedProducts_TopSellingProducts_NewArrivals(getSupportFragmentManager(), index, getApplicationContext());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(myPagerAdapter);

        mViewPager.setCurrentItem(pos);


        int animationStyle = getIntent().getIntExtra("index", 0);

        if (animationStyle == 0) {
            mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {

                @Override
                public void transformPage(View page, float position) {

                    final float normalized_position = Math.abs(Math.abs(position) - 1);
                    page.setScaleX(normalized_position / 2 + 0.5f);
                    page.setScaleY(normalized_position / 2 + 0.5f);
                }
            });
        }
        if (animationStyle == 1) {
            mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
                private static final float MIN_SCALE = 0.85f;
                private static final float MIN_ALPHA = 0.5f;

                @Override
                public void transformPage(View page, float position) {

                    int pageWidth = page.getWidth();
                    int pageHeight = page.getHeight();

                    if (position < -1) { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        page.setAlpha(0);

                    } else if (position <= 1) { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                        float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                        float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                        if (position < 0) {
                            page.setTranslationX(horzMargin - vertMargin / 2);
                        } else {
                            page.setTranslationX(-horzMargin + vertMargin / 2);
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        page.setScaleX(scaleFactor);
                        page.setScaleY(scaleFactor);

                        // Fade the page relative to its size.
                        page.setAlpha(MIN_ALPHA +
                                (scaleFactor - MIN_SCALE) /
                                        (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                    } else { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        page.setAlpha(0);
                    }
                }
            });

        }


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

        if (id == R.id.orderhistory) {
            Intent i = new Intent(this, Activity_OrderHistory.class);
            startActivity(i);
            return true;
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

//    @Override
//    public void onListItemSelected(int position, HashMap<String, ?> movie) {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.mainactivity_container,Fragment_Detail_Electronics.newInstance(movie))
//                .addToBackStack(null)
//                .commit();
//
//
//
//
//    }

}
