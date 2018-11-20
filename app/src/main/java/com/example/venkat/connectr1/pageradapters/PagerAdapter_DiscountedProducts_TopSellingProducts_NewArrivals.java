package com.example.venkat.connectr1.pageradapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.venkat.connectr1.fragments.Fragment_Discounted_TopSelling_NewArrivals_RecyclerView;

import java.util.Locale;

/**
 * Created by rambabu on 6/29/2015.
 */
public class PagerAdapter_DiscountedProducts_TopSellingProducts_NewArrivals extends FragmentPagerAdapter {
//    MovieData movieData = new MovieData();
    int count;
    String[] materialTabs;
    public PagerAdapter_DiscountedProducts_TopSellingProducts_NewArrivals(FragmentManager fm, int size) {
        super(fm);
        count = size;
       // materialTabs = get().getStringArray(R.id.materialtabs);


    }

    @Override
    public Fragment getItem(int position) {

        return Fragment_Discounted_TopSelling_NewArrivals_RecyclerView.newInstance(position);

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Locale l = Locale.getDefault();
        String name = "";

        if(position == 0)
        {
            name = "New Arrivals";
        }
        if(position == 1)
        {
            name = "Discounted Products";
        }
        if(position == 2)
        {
            name = "Top Sellers";
        }
        return name.toUpperCase(l);
    }
}
