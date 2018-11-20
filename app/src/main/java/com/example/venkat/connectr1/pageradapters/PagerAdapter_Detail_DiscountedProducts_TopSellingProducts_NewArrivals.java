package com.example.venkat.connectr1.pageradapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.venkat.connectr1.fragments.Fragment_Detail_Electronics;
import com.example.venkat.connectr1.fragments.Fragment_Detail_NewProducts;
import com.example.venkat.connectr1.jsonlocal.DiscountProductsDataJsonLocal;
import com.example.venkat.connectr1.jsonlocal.NewArrivalProductsDataJsonLocal;
import com.example.venkat.connectr1.jsonlocal.TopSellingProductsDataJsonLocal;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by rambabu on 6/28/2015.
 */
public class PagerAdapter_Detail_DiscountedProducts_TopSellingProducts_NewArrivals extends FragmentPagerAdapter {
    DiscountProductsDataJsonLocal discountProductsDataJsonLocal = new DiscountProductsDataJsonLocal();
    TopSellingProductsDataJsonLocal topSellingProductsDataJsonLocal = new TopSellingProductsDataJsonLocal();
    NewArrivalProductsDataJsonLocal newArrivalProductsDataJsonLocal = new NewArrivalProductsDataJsonLocal();
    int count;
    int index;
    Context context;
    public PagerAdapter_Detail_DiscountedProducts_TopSellingProducts_NewArrivals(FragmentManager fm,int index1,Context context1) {
        super(fm);
        index = index1;
        context = context1;
    }

    @Override
    public Fragment getItem(int position) {
        if(index==0) {
            newArrivalProductsDataJsonLocal.loadLocalMovieDataJson(context);
            return Fragment_Detail_NewProducts.newInstance((HashMap<String, ?>) newArrivalProductsDataJsonLocal.getItem(position));
        }
        else if(index==1) {
            discountProductsDataJsonLocal.loadLocalMovieDataJson(context);
            return Fragment_Detail_Electronics.newInstance((HashMap<String, ?>) discountProductsDataJsonLocal.getItem(position));
        }
        else if(index == 2) {
            topSellingProductsDataJsonLocal.loadLocalMovieDataJson(context);
            return Fragment_Detail_Electronics.newInstance((HashMap<String, ?>) topSellingProductsDataJsonLocal.getItem(position));
        }
        else {
            discountProductsDataJsonLocal.loadLocalMovieDataJson(context);
            return Fragment_Detail_Electronics.newInstance((HashMap<String, ?>) discountProductsDataJsonLocal.getItem(position));
        }
    }

    @Override
    public int getCount() {
        if(index == 0){
            newArrivalProductsDataJsonLocal.loadLocalMovieDataJson(context);
           count = newArrivalProductsDataJsonLocal.getSize();
        return count;}
        else  if(index == 1){
            discountProductsDataJsonLocal.loadLocalMovieDataJson(context);
            count = discountProductsDataJsonLocal.getSize();
            return count;}
        else{
            topSellingProductsDataJsonLocal.loadLocalMovieDataJson(context);
            count = topSellingProductsDataJsonLocal.getSize();
            return count;}

    }

    @Override
    public CharSequence getPageTitle(int position) {

        Locale l = Locale.getDefault();
        HashMap<String,?> movie;
        if(index == 0) {
            newArrivalProductsDataJsonLocal.loadLocalMovieDataJson(context);
            movie = (HashMap<String, ?>) newArrivalProductsDataJsonLocal.getItem(position);
        }
        if(index == 1) {
            discountProductsDataJsonLocal.loadLocalMovieDataJson(context);
            movie = (HashMap<String, ?>) discountProductsDataJsonLocal.getItem(position);
        }
        else
        {
            topSellingProductsDataJsonLocal.loadLocalMovieDataJson(context);
            movie = (HashMap<String , ?>)topSellingProductsDataJsonLocal.getItem(position);
        }


        String name = (String) movie.get("name");
        return name.toUpperCase(l);
    }


}
