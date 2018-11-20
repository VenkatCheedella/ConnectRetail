package com.example.venkat.connectr1.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.venkat.connectr1.adapters.Adapter_Cart;
import com.example.venkat.connectr1.cloud.Data_CartObject;
import com.parse.FindCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by venkat on 8/6/2015.
 */
public class Activity_Cart extends AppCompatActivity {
    private LayoutInflater inflater;
    private ParseQueryAdapter<Data_CartObject> cartAdapter;
    private ListView cartListview;
    @InjectView(R.id.confirm_btn) Button order_cfm_btn;
    Toolbar mToolbar;

    public static final String CART_GROUP_NAME = "SERVER_CART";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mToolbar = (Toolbar)findViewById(R.id.app_tool_bar);
        setSupportActionBar(mToolbar);

        // Set up the Parse query to use in the adapter
        ParseQueryAdapter.QueryFactory<Data_CartObject> cart_factory = new ParseQueryAdapter.QueryFactory<Data_CartObject>(){

            @Override
            public ParseQuery<Data_CartObject> create() {
                ParseQuery<Data_CartObject> query = Data_CartObject.getQuery();
                query.fromLocalDatastore();
                query.whereEqualTo("isDraft", true);
                return query;
            }
        };

        // SetUp Adapter
        inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cartAdapter = new Adapter_Cart(this, cart_factory);
        cartListview = (ListView)findViewById(R.id.cart_listview);
        order_cfm_btn = (Button)findViewById(R.id.confirm_btn);

        order_cfm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncCartToParse();
            }
        });

//        ParseQuery<Data_CartObject> query = Data_CartObject.getQuery();
//        query.fromLocalDatastore();
//        int count=0;
//        try {
//          count =  query.count();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        //Toast.makeText(this, "Count of objects"+ String.valueOf(count) , Toast.LENGTH_LONG).show();
        cartListview.setAdapter(cartAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
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
    private void syncCartToParse(){

        // We could use saveEventually here, but we want to have some UI
        // around whether or not the draft cart has been saved to Parse
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if((ni != null) && (ni.isConnected())){
            if(!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
                // If we have a network connection and a current logged in user,
                // sync the cart to the cloud

                // In this app, local changes should overwrite content on the
                // server.

                ParseQuery<Data_CartObject> query = Data_CartObject.getQuery();
                query.fromPin();
                query.whereEqualTo("isDraft", true);
                query.findInBackground(new FindCallback<Data_CartObject>() {
                    @Override
                    public void done(List<Data_CartObject> list, ParseException e) {
                            if(e == null){
                                for(final Data_CartObject cart_obj : list){
                                    cart_obj.setDraft(false);
                                    cart_obj.setOwner(ParseUser.getCurrentUser());
                                    cart_obj.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if(e == null){
                                                // Let adapter know to update view
                                                if(!isFinishing()){
                                                    cartAdapter.clear();
                                                    cartAdapter.notifyDataSetChanged();
                                                }
                                            }
                                            else
                                            {
                                                cart_obj.setDraft(true);
                                            }
                                        }
                                    });
                                }
                            }else{
                                Log.i("CartActivity", "syncTodosToParse: Error finding pinned" + e.getMessage());
                            }
                    }
                });
            }else{
                // If we have a network connection but no logged in user, direct
                // the person to log in or sign up.
                Intent i = new Intent(this, Activity_Login.class);
                startActivity(i);
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.cart_container, new Fragment_LoginPage())
//                        .commit();
            }
        }else{
            // If there is no connection, let the user know the sync didn't
            // happen
            Toast.makeText(
                    getApplicationContext(),
                    "Your device appears to be offline. Some todos may not have been synced to Parse.",
                    Toast.LENGTH_LONG).show();
        }

    }
}
