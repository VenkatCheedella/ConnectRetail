package com.example.venkat.connectr1.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.adapters.Adapter_DiscountedProductsRecyclerView;
import com.example.venkat.connectr1.adapters.Adapter_NewArrivalProductsRecyclerView;
import com.example.venkat.connectr1.adapters.Adapter_TopSellingProductsRecyclerView;
import com.example.venkat.connectr1.jsonlocal.DiscountProductsDataJsonLocal;
import com.example.venkat.connectr1.jsonlocal.NewArrivalProductsDataJsonLocal;
import com.example.venkat.connectr1.jsonlocal.TopSellingProductsDataJsonLocal;

import java.util.HashMap;


/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment_Discounted_TopSelling_NewArrivals_RecyclerView extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mlinearLayoutManager;



    private Adapter_DiscountedProductsRecyclerView mdiscountedProductsRecyclerViewAdapter;
    private Adapter_TopSellingProductsRecyclerView mtopSellingProductsRecyclerViewAdapter;
    private Adapter_NewArrivalProductsRecyclerView mnewArrivalProductsRecyclerViewAdapter;
    private DiscountProductsDataJsonLocal discountProductsDataJsonLocal;
    private TopSellingProductsDataJsonLocal topSellingProductsDataJsonLocal;
    private NewArrivalProductsDataJsonLocal newArrivalProductsDataJsonLocal;
    private OnListItemSelectedListener mListener;

    public Fragment_Discounted_TopSelling_NewArrivals_RecyclerView() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        discountProductsDataJsonLocal = new DiscountProductsDataJsonLocal();
        discountProductsDataJsonLocal.loadLocalMovieDataJson(getActivity());
        topSellingProductsDataJsonLocal = new TopSellingProductsDataJsonLocal();
        topSellingProductsDataJsonLocal.loadLocalMovieDataJson(getActivity());
        newArrivalProductsDataJsonLocal = new NewArrivalProductsDataJsonLocal();
        newArrivalProductsDataJsonLocal.loadLocalMovieDataJson(getActivity());
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       int option = getArguments().getInt("someInt");

        View rootView;

//        final View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
//        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
//
//        mRecyclerView.setHasFixedSize(true);
//        mlinearLayoutManager = new LinearLayoutManager(getActivity());
//
//        mRecyclerView.setLayoutManager(mlinearLayoutManager);
//
//
//        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(),movieData.getMoviesList());
//
//        mRecyclerView.setAdapter(mRecyclerViewAdapter);
//
//        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                HashMap<String,?> movie = (HashMap<String ,?>)movieData.getItem(position);
//                mListener.onListItemSelected(position,movie);
//            }
//
//            @Override
//            public void onItemLongClick(View v, int position) {
//                movieData.addItem(position, (HashMap) ((HashMap) movieData.getItem(position)).clone());
//                mRecyclerViewAdapter.notifyItemInserted(position);
//
//            }
//
//
//        });

        if(option == 0)
        {
            rootView = inflater.inflate(R.layout.fragment_new_arrival_products_grid, container, false);
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardGrid_new_arrival_products);

            mRecyclerView.setHasFixedSize(true);
            mlinearLayoutManager = new GridLayoutManager(getActivity(),2);

            mRecyclerView.setLayoutManager(mlinearLayoutManager);


            mnewArrivalProductsRecyclerViewAdapter = new Adapter_NewArrivalProductsRecyclerView(getActivity(),newArrivalProductsDataJsonLocal.getNewArrivalProductsList());

            mRecyclerView.setAdapter(mnewArrivalProductsRecyclerViewAdapter);

            mnewArrivalProductsRecyclerViewAdapter.setOnItemClickListener(new Adapter_NewArrivalProductsRecyclerView.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    HashMap<String,?> movie = (HashMap<String ,?>)newArrivalProductsDataJsonLocal.getItem(position);
                    mListener.onListItemSelected(position, movie);
                }

            });
        }

        else if(option==1) {
            rootView = inflater.inflate(R.layout.fragment_discounted_products, container, false);
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList_discounted_products);
            mRecyclerView.setHasFixedSize(true);
            mlinearLayoutManager = new LinearLayoutManager(getActivity());

            mRecyclerView.setLayoutManager(mlinearLayoutManager);


            mdiscountedProductsRecyclerViewAdapter = new Adapter_DiscountedProductsRecyclerView(getActivity(),discountProductsDataJsonLocal.getDiscountedProductsList());

            mRecyclerView.setAdapter(mdiscountedProductsRecyclerViewAdapter);

            mdiscountedProductsRecyclerViewAdapter.setOnItemClickListener(new Adapter_DiscountedProductsRecyclerView.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    HashMap<String,?> movie = (HashMap<String ,?>)discountProductsDataJsonLocal.getItem(position);
                    mListener.onListItemSelected(position,movie);
                }
            });
        }
        else {
            rootView = inflater.inflate(R.layout.fragment_top_selling_products_grid, container, false);
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardGrid_top_selling_products);

            mRecyclerView.setHasFixedSize(true);
            mlinearLayoutManager = new GridLayoutManager(getActivity(),2);

            mRecyclerView.setLayoutManager(mlinearLayoutManager);


            mtopSellingProductsRecyclerViewAdapter = new Adapter_TopSellingProductsRecyclerView(getActivity(),topSellingProductsDataJsonLocal.getTopSellingProductsList());

            mRecyclerView.setAdapter(mtopSellingProductsRecyclerViewAdapter);

            mtopSellingProductsRecyclerViewAdapter.setOnItemClickListener(new Adapter_TopSellingProductsRecyclerView.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    HashMap<String,?> movie = (HashMap<String ,?>)topSellingProductsDataJsonLocal.getItem(position);
                    mListener.onListItemSelected(position, movie);
                }

            });
        }

        return rootView;
    }
    public interface OnListItemSelectedListener
    {
        public void onListItemSelected(int position, HashMap<String, ?> movie);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try{
            mListener =(OnListItemSelectedListener) activity;

        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }
    public static Fragment_Discounted_TopSelling_NewArrivals_RecyclerView newInstance(int someInt){
        Fragment_Discounted_TopSelling_NewArrivals_RecyclerView fragment = new Fragment_Discounted_TopSelling_NewArrivals_RecyclerView();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        fragment.setArguments(args);
        return fragment;

    }

//        public interface OnFragmentInteractionListener {
//        public void onFragmentInteraction(int sint);
//    }

}
