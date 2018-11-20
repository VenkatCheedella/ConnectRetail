package com.example.venkat.connectr1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.venkat.connectr1.dialogfragments.DialogFragment_DescriptionDialog;
import com.example.venkat.connectr1.fragments.Fragment_Placeholder_DescriptionDialog;

/**
 * Created by rambabu on 7/12/2015.
 */
public class Activity_DescriptionDialog extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_dialog);

        Intent intent = getIntent();
        String description = (String) intent.getSerializableExtra(DialogFragment_DescriptionDialog.Description_ARGS);

        getFragmentManager().beginTransaction()
                .replace(R.id.container1, Fragment_Placeholder_DescriptionDialog.newInstance(description))
                .commit();

    }
}
