package com.example.venkat.connectr1.pageradapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.venkat.connectr1.fragments.Fragment_TopSellingProducts;
import com.example.venkat.connectr1.jsonlocal.TopSellingProductsDataJsonLocal;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by rambabu on 6/28/2015.
 */
public class PagerAdapter_TopSellingProducts extends FragmentPagerAdapter {
    TopSellingProductsDataJsonLocal movieData;
    int count;
    public PagerAdapter_TopSellingProducts(FragmentManager fm, int size, TopSellingProductsDataJsonLocal moviedata) {

        super(fm);
        movieData = moviedata;
        count = size;

    }

    @Override
    public Fragment getItem(int position) {

        return Fragment_TopSellingProducts.newInstance((HashMap<String, ?>) movieData.getItem(position));
//        return recyclerviewFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Locale l = Locale.getDefault();
        HashMap<String,?> movie = (HashMap<String , ?>)movieData.getItem(position);
        String name = (String) movie.get("name");
        return name.toUpperCase(l);
    }


}
