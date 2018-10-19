/**
 * @author Elvin Martinez on  Sept, 2018
 */


package com.example.elvin.user_login_signup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class LoggedIn extends AppCompatActivity {
    /*Makes view from file called activity_logged.xml onCreate*/

    /*Variable declaration*/
    private SharedPreferences myPref;
    TextView myview1, myview2, myview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        myview1=findViewById(R.id.userinfo1);
        myview2=findViewById(R.id.userinfo2);
        myview3=findViewById(R.id.userinfo3);

        /*Access to my sharedPreference*/
        myPref=getSharedPreferences("myDatabase",Context.MODE_PRIVATE);

        /*Retreating all info stored on sharedPreference and setting textview to their values*/
        String name =myPref.getString(getString(R.string.userFName),"default");
        String last =myPref.getString(getString(R.string.userLName),"default");
        String fullname=name+" "+last;
        myview1.setText(fullname);

        String email =myPref.getString(getString(R.string.userEmail),"default");
        myview2.setText(email);

        String date =myPref.getString(getString(R.string.userDate),"default");
        myview3.setText(date);

    }
}
