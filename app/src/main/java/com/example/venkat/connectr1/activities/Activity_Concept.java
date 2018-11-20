package com.example.venkat.connectr1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.venkat.connectr1.activities.R;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class Activity_Concept extends AppCompatActivity {

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept);
        mToolbar = (Toolbar)findViewById(R.id.app_tool_bar);
        setSupportActionBar(mToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_activities, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == R.id.home) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.login) {
            if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
                Intent login_intent = new Intent(this, Activity_Login.class);
                startActivity(login_intent);
                return true;
            }
            else
            {
                Toast.makeText(this, "Already LoggedIn", Toast.LENGTH_SHORT).show();
                return true;
            }
        }

        if(id == R.id.orderhistory)
        {
            Intent i = new Intent(this, Activity_OrderHistory.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.concept) {
            Intent i = new Intent(this,Activity_Concept.class);
            startActivity(i);
            return true;
        }

        if(id == R.id.references){
            Intent i = new Intent(this,Activity_References.class);
            startActivity(i);
            return true;
        }



        if(id == R.id.aboutus){
            Intent i = new Intent(this, Activity_AboutUs.class);
            startActivity(i);
            return true;
        }



        if((id == R.id.logout)){
            // Set up a progress dialog
            if(!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setMessage(getString(R.string.progress_logout));
                dialog.show();
                ParseUser.logOut();
                dialog.dismiss();
                Toast.makeText(this, "log out successful", Toast.LENGTH_LONG).show();
                callingMainActivity();
            }
            else
                Toast.makeText(this, "Current User: Anonymous", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void callingMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("Trigger", false);
        startActivity(intent);
    }
}
