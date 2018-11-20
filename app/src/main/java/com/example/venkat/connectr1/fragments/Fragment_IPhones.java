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
import com.example.venkat.connectr1.adapters.Adapter_ElectronicsRecyclerView;
import com.example.venkat.connectr1.json.ElectronicsInfoFromJSON;
import com.example.venkat.connectr1.json.IphonesInfoFromJSON;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by venkat on 7/27/2015.
 */
public class Fragment_IPhones extends Fragment {

    ElectronicsInfoFromJSON elecData;
    Adapter_ElectronicsRecyclerView electronics_adapter;
    private OnListItemSelectedListener eleItemSelectListner;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        elecData = new ElectronicsInfoFromJSON();
        // here equivalent step to load data from localjson is missing.
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_electronics, container, false);
        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.electronics_cardlist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        electronics_adapter = new Adapter_ElectronicsRecyclerView(elecData.getElectronicsList(), getActivity());
        MyJSONDownloadAsync elecJSONDownloadAsync = new MyJSONDownloadAsync(electronics_adapter);
        String url = IphonesInfoFromJSON.IPHONESJSON_API;
        elecJSONDownloadAsync.execute(new String[]{url});
        recyclerView.setAdapter(electronics_adapter);

        electronics_adapter.setOnItemClickListner(new Adapter_ElectronicsRecyclerView.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                HashMap<String, ?> elec_Item = (HashMap<String, ?>) electronics_adapter.getItem(position);
                eleItemSelectListner.OnListElectronicItemSelected(position, elec_Item);
            }
        });
        return rootview;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            eleItemSelectListner = (OnListItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnFragment interaction listner");
        }
    }

    public interface OnListItemSelectedListener {
        void OnListElectronicItemSelected(int position, HashMap<String, ?> movie);
    }

    public static Fragment_IPhones newInstance() {
        return new Fragment_IPhones();
    }

    private class MyJSONDownloadAsync extends AsyncTask<String, Void, ElectronicsInfoFromJSON> {

        private final WeakReference<Adapter_ElectronicsRecyclerView> elec_Adapter;

        public MyJSONDownloadAsync(Adapter_ElectronicsRecyclerView elec_Adapter) {
            this.elec_Adapter = new WeakReference<Adapter_ElectronicsRecyclerView>(elec_Adapter);
        }


        @Override
        protected ElectronicsInfoFromJSON doInBackground(String... urls) {
            ElectronicsInfoFromJSON elecDataJSON = new ElectronicsInfoFromJSON();
            for (String url : urls) {
                elecDataJSON.dowloadElectronicsinfoJSON(url);

            }
            return elecDataJSON;
        }

        @Override
        protected void onPostExecute(ElectronicsInfoFromJSON electronicsInfoFromJSON) {
            elecData.electronicsList.clear();
            for (int i = 0; i < electronicsInfoFromJSON.getSize(); i++) {
                elecData.electronicsList.add(electronicsInfoFromJSON.electronicsList.get(i));
            }
            final Adapter_ElectronicsRecyclerView curr_Electronics_Adap = elec_Adapter.get();
            if (curr_Electronics_Adap != null) {
                curr_Electronics_Adap.notifyDataSetChanged();
            }

        }
    }
}

