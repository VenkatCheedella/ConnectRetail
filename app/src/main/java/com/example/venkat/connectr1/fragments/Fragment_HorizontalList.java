package com.example.venkat.connectr1.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.venkat.connectr1.activities.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment_HorizontalList extends Fragment {
    private ImageView image;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;

    public Fragment_HorizontalList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_horizontal_list, container, false);
        image = (ImageView) rootView.findViewById(R.id.horizontalScrollImage1);
        image.setImageResource(R.drawable.image1);
        image1 = (ImageView) rootView.findViewById(R.id.horizontalScrollImage2);
        image1.setImageResource(R.drawable.image1);
        image2 = (ImageView) rootView.findViewById(R.id.horizontalScrollImage3);
        image2.setImageResource(R.drawable.image1);
        image3 = (ImageView) rootView.findViewById(R.id.horizontalScrollImage4);
        image3.setImageResource(R.drawable.image1);
        image4 = (ImageView) rootView.findViewById(R.id.horizontalScrollImage5);
        image4.setImageResource(R.drawable.image1);
        return rootView;


    }
}
