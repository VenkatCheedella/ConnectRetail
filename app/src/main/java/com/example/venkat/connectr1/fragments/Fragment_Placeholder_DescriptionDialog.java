package com.example.venkat.connectr1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.dialogfragments.DialogFragment_DescriptionDialog;

/**
 * Created by rambabu on 7/12/2015.
 */
public class Fragment_Placeholder_DescriptionDialog extends Fragment {
    public static String Description_ARGS;






    public static Fragment_Placeholder_DescriptionDialog newInstance(String description){
        Fragment_Placeholder_DescriptionDialog fragment = new Fragment_Placeholder_DescriptionDialog();
        Bundle args = new Bundle();
        args.putSerializable(Description_ARGS, description);
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_description_dialog, container, false);
        String desc = (String) getArguments().getSerializable(DialogFragment_DescriptionDialog.Description_ARGS);
        TextView textView = (TextView) rootView.findViewById(R.id.Description_Activity_Demo);
        textView.setText(desc);

        return rootView;

    }


}
