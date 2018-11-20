package com.example.venkat.connectr1.dialogfragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.venkat.connectr1.activities.R;

/**
 * Created by rambabu on 7/12/2015.
 */
public class DialogFragment_DescriptionDialog extends DialogFragment {
    public static String Description_ARGS;




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_datepicker, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(v);
          return alertDialogBuilder.create();
    }

    public static DialogFragment_DescriptionDialog newInstance(String Description){
        DialogFragment_DescriptionDialog fragment = new DialogFragment_DescriptionDialog();
        Bundle args = new Bundle();
        args.putSerializable(Description_ARGS, Description);
        fragment.setArguments(args);
        return fragment;

    }


}
