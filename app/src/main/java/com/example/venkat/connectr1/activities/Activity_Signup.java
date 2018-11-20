package com.example.venkat.connectr1.activities;

/**
 * Created by rambabu on 8/9/2015.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venkat.connectr1.utilities.ImageHelper;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Activity_Signup extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private static int RESULT_LOAD_IMG = 1;
    private String profileImgString;
    @InjectView(R.id.input_name)
    EditText _nameText;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.input_password_confirm)
    EditText _confirmPasswordText;
    @InjectView(R.id.input_address1)
    EditText _address1;
    @InjectView(R.id.input_address2)
    EditText _address2;
    @InjectView(R.id.input_city)
    EditText _city;
    @InjectView(R.id.input_zip)
    EditText _zip;

    @InjectView(R.id.input_upload)
    TextView _upload;
    @InjectView(R.id.upload_button)
    ImageView upload_button;
    @InjectView(R.id.input_display_image)
    ImageView _display;

    @InjectView(R.id.btn_signup)
    Button _signupButton;
    @InjectView(R.id.link_login)
    TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMG);
            }
        });

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Activity_Signup.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString().trim();
        String password = _passwordText.getText().toString().trim();
        String address1 = _address1.getText().toString();
        String address2 = _address2.getText().toString();
        String city = _city.getText().toString();
        String zip = _zip.getText().toString();


        // TODO: Implement your own signup logic here.

        ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(password);
        user.put("name", name);
        user.put("address1", address1);
        user.put("address2",address2 );
        user.put("city", city);
        user.put("zip", zip);
        if(profileImgString!= null)
        user.put("profileImageString", profileImgString);

        // Call the Parse signup method
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
               // dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    onSignupFailed();
                } else {
                    // Start an intent for the dispatch activity
                    onSignupSuccess();

                }
            }
        });


//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
    }


    public void onSignupSuccess() {
//        _signupButton.setEnabled(true);
//        setResult(RESULT_OK, null);
//
//        finish();

            Intent intent = new Intent(this,Activity_Login.class);
            startActivity(intent);
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String confirmPassword = _confirmPasswordText.getText().toString();
        String address1 = _address1.getText().toString();
        String address2 = _address2.getText().toString();
        String city = _city.getText().toString();
        String zip = _zip.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (password.equals(confirmPassword)==false) {
            _confirmPasswordText.setError("Passwords Doesnt match");
            valid = false;
        } else {
            _confirmPasswordText.setError(null);
        }

        if (address1.isEmpty()) {
            _address1.setError("Please enter a valid address");
            valid = false;
        } else {
            _address1.setError(null);
        }

        if (city.isEmpty() || city.length() < 4 || city.length() > 30) {
            _city.setError("between 4 and 30 characters");
            valid = false;
        } else {
            _city.setError(null);
        }

        if (zip.isEmpty() || zip.length() < 5 || zip.length() > 6) {
            _zip.setError("Zipcode should be 5 alphanumeric characters");
            valid = false;
        } else {
            _zip.setError(null);
        }

        return valid;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMG && resultCode == this.RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = this.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            bitmap = bitmap.createScaledBitmap(bitmap, 100, 100, true);

            _display.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitmap,10));

            //Convert BitMap to string to save in database

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
            byte [] byte_arr = stream.toByteArray();
            profileImgString = Base64.encodeToString(byte_arr, Base64.DEFAULT);


        }

    }
}
