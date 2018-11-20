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
import com.example.venkat.connectr1.adapters.Adapter_ConfectionariesRecyclerView;
import com.example.venkat.connectr1.json.ChocolatesInfoFromJSON;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by venkat on 7/27/2015.
 */
public class Fragment_Chocolates extends Fragment {
    ChocolatesInfoFromJSON chocolateData;
    Adapter_ConfectionariesRecyclerView confectionaries_adapter;
    private OnListItemSelectedListener confectionaryItemSelectListner;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        chocolateData = new ChocolatesInfoFromJSON();
        // here equivalent step to load data from localjson is missing.
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_confectionaries, container, false);
        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.confectionaries_cardlist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        confectionaries_adapter = new Adapter_ConfectionariesRecyclerView(chocolateData.getChocolatesList(), getActivity());
        MyJSONDownloadAsync confectionayJSONDownloadAsync = new MyJSONDownloadAsync(confectionaries_adapter);
        String url = ChocolatesInfoFromJSON.CHOCOLATESJSON_API;
        confectionayJSONDownloadAsync.execute(new String[]{url});
        recyclerView.setAdapter(confectionaries_adapter);

        confectionaries_adapter.setOnItemClickListner(new Adapter_ConfectionariesRecyclerView.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                HashMap<String, ?> chocolate_Item = (HashMap<String, ?>) confectionaries_adapter.getItem(position);
                confectionaryItemSelectListner.OnListConfectionaryItemSelected(position, chocolate_Item);
            }
        });
        return rootview;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            confectionaryItemSelectListner = (OnListItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnFragment interaction listner");
        }
    }

    public interface OnListItemSelectedListener {
        void OnListConfectionaryItemSelected(int position, HashMap<String, ?> movie);
    }

    public static Fragment_Chocolates newInstance() {
        return new Fragment_Chocolates();
    }

    private class MyJSONDownloadAsync extends AsyncTask<String, Void, ChocolatesInfoFromJSON> {

        private final WeakReference<Adapter_ConfectionariesRecyclerView> confectionary_Adapter;

        public MyJSONDownloadAsync(Adapter_ConfectionariesRecyclerView confectionaries_adapter) {
            this.confectionary_Adapter = new WeakReference<Adapter_ConfectionariesRecyclerView>(confectionaries_adapter);
        }


        @Override
        protected ChocolatesInfoFromJSON doInBackground(String... urls) {
            ChocolatesInfoFromJSON chocolateDataJSON = new ChocolatesInfoFromJSON();
            for (String url : urls) {
                chocolateDataJSON.dowloadChocolatesinfoJSON(url);

            }
            return chocolateDataJSON;
        }

        @Override
        protected void onPostExecute(ChocolatesInfoFromJSON chocolateInfoFromJSON) {
            chocolateData.chocolatesList.clear();
            for (int i = 0; i < chocolateInfoFromJSON.getSize(); i++) {
                chocolateData.chocolatesList.add(chocolateInfoFromJSON.chocolatesList.get(i));
            }
            final Adapter_ConfectionariesRecyclerView curr_Confectionaries_Adap = confectionary_Adapter.get();
            if (curr_Confectionaries_Adap != null) {
                curr_Confectionaries_Adap.notifyDataSetChanged();
            }

        }
    }
}

