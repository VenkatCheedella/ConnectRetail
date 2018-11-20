package com.example.venkat.connectr1.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.adapters.Adapter_DairyRecyclerView;
import com.example.venkat.connectr1.json.MilkProductsInfoFromJSON;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by venkat on 7/27/2015.
 */
public class Fragment_MilkProducts extends Fragment {
    MilkProductsInfoFromJSON milkProductsData;
    Adapter_DairyRecyclerView dairy_adapter;
    private OnListItemSelectedListener dairyItemSelectListner;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        milkProductsData = new MilkProductsInfoFromJSON();
        // here equivalent step to load data from localjson is missing.
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_dairy, container, false);
        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.dairy_cardlist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        dairy_adapter = new Adapter_DairyRecyclerView(milkProductsData.getMilkProductsList(), getActivity());
        MyJSONDownloadAsync dairyJSONDownloadAsync = new MyJSONDownloadAsync(dairy_adapter);
        String url = MilkProductsInfoFromJSON.MILKPRODUCTSJSON_API;
        dairyJSONDownloadAsync.execute(new String[]{url});
        recyclerView.setAdapter(dairy_adapter);

        dairy_adapter.setOnItemClickListner(new Adapter_DairyRecyclerView.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                HashMap<String, ?> dairy_Item = (HashMap<String, ?>) dairy_adapter.getItem(position);
                dairyItemSelectListner.OnListDairyItemSelected(position, dairy_Item);
            }
        });
        return rootview;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            dairyItemSelectListner = (OnListItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnFragment interaction listner");
        }
    }

    public interface OnListItemSelectedListener {
        void OnListDairyItemSelected(int position, HashMap<String, ?> movie);
    }

    public static Fragment_MilkProducts newInstance() {
        return new Fragment_MilkProducts();
    }

    private class MyJSONDownloadAsync extends AsyncTask<String, Void, MilkProductsInfoFromJSON> {

        private final WeakReference<Adapter_DairyRecyclerView> dairy_Adapter;

        public MyJSONDownloadAsync(Adapter_DairyRecyclerView dairy_adapter) {
            this.dairy_Adapter = new WeakReference<Adapter_DairyRecyclerView>(dairy_adapter);
        }


        @Override
        protected MilkProductsInfoFromJSON doInBackground(String... urls) {
            MilkProductsInfoFromJSON milkProductDataJSON = new MilkProductsInfoFromJSON();
            for (String url : urls) {
                milkProductDataJSON.dowloadMilkProductsinfoJSON(url);

            }
            return milkProductDataJSON;
        }

        @Override
        protected void onPostExecute(MilkProductsInfoFromJSON milkProductsInfoFromJSON) {
            milkProductsData.milkProductsList.clear();
            for (int i = 0; i < milkProductsInfoFromJSON.getSize(); i++) {
                milkProductsData.milkProductsList.add(milkProductsInfoFromJSON.milkProductsList.get(i));
            }
            final Adapter_DairyRecyclerView curr_Dairy_Adap = dairy_Adapter.get();
            if (curr_Dairy_Adap != null) {
                curr_Dairy_Adap.notifyDataSetChanged();
            }

        }
    }
}

