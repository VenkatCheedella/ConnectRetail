package com.example.venkat.connectr1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.jsonlocal.TopSellingProductsDataJsonLocal;
import com.example.venkat.connectr1.pageradapters.PagerAdapter_TopSellingProducts;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_TopSellingProducts_ViewPager extends Fragment {

    PagerAdapter_TopSellingProducts myPagerAdapter;
    ViewPager mViewPager;
    TopSellingProductsDataJsonLocal topSellingProductsData;


//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity__view_pager);
//        topSellingProductsData = new TopSellingProductsDataJsonLocal();
//        topSellingProductsData.loadLocalMovieDataJson(getApplicationContext());
//
//        myPagerAdapter = new PagerAdapter_TopSellingProducts(getSupportFragmentManager(), topSellingProductsData.getSize(),topSellingProductsData);
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(myPagerAdapter);
//        mViewPager.setCurrentItem(0);
//
//            mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
//                private static final float MIN_SCALE = 0.85f;
//                private static final float MIN_ALPHA = 0.5f;
//                @Override
//                public void transformPage(View page, float position) {
//
//                    int pageWidth = page.getWidth();
//                    int pageHeight = page.getHeight();
//
//                    if (position < -1) { // [-Infinity,-1)
//                        // This page is way off-screen to the left.
//                        page.setAlpha(0);
//
//                    } else if (position <= 1) { // [-1,1]
//                        // Modify the default slide transition to shrink the page as well
//                        float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//                        float vertMargin = pageHeight * (1 - scaleFactor) / 2;
//                        float horzMargin = pageWidth * (1 - scaleFactor) / 2;
//                        if (position < 0) {
//                            page.setTranslationX(horzMargin - vertMargin / 2);
//                        } else {
//                            page.setTranslationX(-horzMargin + vertMargin / 2);
//                        }
//
//                        // Scale the page down (between MIN_SCALE and 1)
//                        page.setScaleX(scaleFactor);
//                        page.setScaleY(scaleFactor);
//
//                        // Fade the page relative to its size.
//                        page.setAlpha(MIN_ALPHA +
//                                (scaleFactor - MIN_SCALE) /
//                                        (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//
//                    } else { // (1,+Infinity]
//                        // This page is way off-screen to the right.
//                        page.setAlpha(0);
//                    }
//                }
//            });
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.activity__view_pager, container, false);
        mViewPager = (ViewPager) mView.findViewById(R.id.pager);
        topSellingProductsData = new TopSellingProductsDataJsonLocal();
        topSellingProductsData.loadLocalMovieDataJson(getActivity());

        myPagerAdapter = new PagerAdapter_TopSellingProducts(getChildFragmentManager(), topSellingProductsData.getSize(),topSellingProductsData);
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setCurrentItem(0);
//        pageSwitcher(3);
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
//            private static final float MIN_SCALE = 0.85f;
//            private static final float MIN_ALPHA = 0.5f;
            private static final float MIN_SCALE_DEPTH = 0.75f;
            private static final float MIN_SCALE_ZOOM = 0.85f;
            private static final float MIN_ALPHA_ZOOM = 0.5f;
            private static final float SCALE_FACTOR_SLIDE = 0.85f;
            private static final float MIN_ALPHA_SLIDE = 0.35f;

            @Override
            public void transformPage(View page, float position) {

//                int pageWidth = page.getWidth();
//                int pageHeight = page.getHeight();

//                if (position < -1) { // [-Infinity,-1)
//                    // This page is way off-screen to the left.
//                    page.setAlpha(0);
//
//                } else if (position <= 1) { // [-1,1]
//                    // Modify the default slide transition to shrink the page as well
//                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
//                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
//                    if (position < 0) {
//                        page.setTranslationX(horzMargin - vertMargin / 2);
//                    } else {
//                        page.setTranslationX(-horzMargin + vertMargin / 2);
//                    }
//
//                    // Scale the page down (between MIN_SCALE and 1)
//                    page.setScaleX(scaleFactor);
//                    page.setScaleY(scaleFactor);
//
//                    // Fade the page relative to its size.
//                    page.setAlpha(MIN_ALPHA +
//                            (scaleFactor - MIN_SCALE) /
//                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//
//                } else { // (1,+Infinity]
//                    // This page is way off-screen to the right.
//                    page.setAlpha(0);
//                }

                final float alpha;
                final float scale;
                final float translationX;



                if (position < 0 && position > -1) {
                    // this is the page to the left
                    scale = Math.abs(Math.abs(position) - 1) * (1.0f - SCALE_FACTOR_SLIDE) + SCALE_FACTOR_SLIDE;
                    alpha = Math.max(MIN_ALPHA_SLIDE, 1 - Math.abs(position));
                    int pageWidth = page.getWidth();
                    float translateValue = position * -pageWidth;
                    if (translateValue > -pageWidth) {
                        translationX = translateValue;
                    } else {
                        translationX = 0;
                    }
                } else {
                    alpha = 1;
                    scale = 1;
                    translationX = 0;
                }

                page.setAlpha(alpha);
                page.setTranslationX(translationX);
                page.setScaleX(scale);
                page.setScaleY(scale);

            }
        });

        return mView;
    }




//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//       // getMenuInflater().inflate(R.menu.menu_activity__view_pager, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public void onFragmentInteraction(int sint){
//        //you can leave it empty
//
//    }

//    @Override
//    public void onListItemSelected(int position, HashMap<String, ?> movie) {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container,DetailView.newInstance(movie))
//                .addToBackStack(null)
//                .commit();




//    }

    public static Fragment_TopSellingProducts_ViewPager newInstance() {
        return new Fragment_TopSellingProducts_ViewPager();
    }

}
