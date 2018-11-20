package com.example.venkat.connectr1.jsonlocal;

import com.example.venkat.connectr1.activities.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by venkat on 7/10/2015.
 */
public class Data_NavgationDrawer {
    private List<Map<String,?>> nav_draw_items;

    public Data_NavgationDrawer() {
        this.nav_draw_items = new ArrayList<Map<String,?>>();
    }

    public List<Map<String,?>> getNavItems(){
        return nav_draw_items;
    }

    public void loadNavData(){
//        HashMap<String, ?> item1 = (HashMap<String,?>)createList("Task2", R.mipmap.taskimg, 1);
//        HashMap<String, ?> item2 = (HashMap<String,?>)createList("Laptops", R.mipmap.list, 1);
//        HashMap<String, ?> item3 = (HashMap<String,?>)createList("Ipad & Tablets", R.mipmap.grid, 1);
//
//        HashMap<String, ?> item4 = (HashMap<String,?>)createList("", R.mipmap.blue_div, 2);
//        HashMap<String, ?> item5 = (HashMap<String,?>)createList("View1", 0, 3);
//        HashMap<String, ?> item6 = (HashMap<String,?>) createList("View2", 0, 3);
//        HashMap<String, ?> item7 = (HashMap<String,?>)createList("About me", 0, 3);


        HashMap<String, ?> item0 = (HashMap<String,?>)createList("",R.mipmap.nav_banner, 0);
        HashMap<String, ?> item1 = (HashMap<String,?>)createList("Home", R.mipmap.ic_home_black_24dp, 1);
        HashMap<String, ?> item2 = (HashMap<String,?>)createList("", R.mipmap.blue_div, 2);
        HashMap<String, ?> item3 = (HashMap<String,?>)createList("Electronics", 0, 3);
        HashMap<String, ?> item4 = (HashMap<String,?>)createList("Laptops And Computers", R.mipmap.ic_laptop_black_24dp, 1);
        HashMap<String, ?> item5 = (HashMap<String,?>)createList("Ipad & Tablets", R.mipmap.ic_tablet_mac_black_24dp, 1);
        HashMap<String, ?> item6 = (HashMap<String,?>)createList("Apple Iphones", R.mipmap.ic_phone_iphone_black_24dp, 1);
        HashMap<String, ?> item7 = (HashMap<String,?>)createList("", R.mipmap.blue_div, 2);
        HashMap<String, ?> item8 = (HashMap<String,?>)createList("Confectionaries", 0, 3);
        HashMap<String, ?> item9 = (HashMap<String,?>)createList("Candy", R.mipmap.ic_grain_black_24dp, 1);
        HashMap<String, ?> item10 = (HashMap<String,?>)createList("Chocolates",R.mipmap.ic_crop_7_5_black_24dp, 1);
        HashMap<String, ?> item11 = (HashMap<String,?>)createList("", R.mipmap.blue_div, 2);
        HashMap<String, ?> item12 = (HashMap<String,?>)createList("Dairy", 0, 3);
        HashMap<String, ?> item13 = (HashMap<String,?>)createList("Milk Products", R.mipmap.ic_local_cafe_black_24dp, 1);
        HashMap<String, ?> item14 = (HashMap<String,?>)createList("", R.mipmap.blue_div, 2);
        HashMap<String, ?> item15 = (HashMap<String,?>)createList("Media", 0, 3);
        HashMap<String, ?> item16 = (HashMap<String,?>)createList("Books", R.mipmap.ic_book_black_24dp, 1);
        HashMap<String, ?> item17 = (HashMap<String,?>)createList("Music", R.mipmap.ic_music_note_black_24dp, 1);
        HashMap<String, ?> item18 = (HashMap<String,?>)createList("Movies", R.mipmap.ic_movie_creation_black_24dp, 1);


        nav_draw_items.add(item0);
        nav_draw_items.add(item1);
        nav_draw_items.add(item2);
        nav_draw_items.add(item3);
        nav_draw_items.add(item4);
        nav_draw_items.add(item5);
        nav_draw_items.add(item6);
        nav_draw_items.add(item7);
        nav_draw_items.add(item8);
        nav_draw_items.add(item9);
        nav_draw_items.add(item10);
        nav_draw_items.add(item11);
        nav_draw_items.add(item12);
        nav_draw_items.add(item13);
        nav_draw_items.add(item14);
        nav_draw_items.add(item15);
        nav_draw_items.add(item16);
        nav_draw_items.add(item17);
        nav_draw_items.add(item18);





    }

    private static HashMap createList(String _itemname, int _itemImage, int _type){
        HashMap  nav_item = new HashMap<>();
        nav_item.put("itemname", _itemname);
        nav_item.put("itemimage", _itemImage);
        nav_item.put("type", _type);
        return nav_item;
    }
}
