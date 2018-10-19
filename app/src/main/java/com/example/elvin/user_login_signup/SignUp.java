/**
 * @author Elvin Martinez on  Sept, 2018
 */

package com.example.elvin.user_login_signup;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SignUp extends AppCompatActivity {

    private SharedPreferences myPref;
    private SharedPreferences.Editor myEditor;


    //Declaration of variables
    EditText field1, field2,field3, field4, field5, field6;
    Button registerBTN;
    TextView returnPrevious;

    /*Makes view from file called splash_screen.xml onCreate*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //Variable given the id of object they will manipulate in the xml file
        field1=findViewById(R.id.First_Name_Field);
        field2=findViewById(R.id.Last_name_Field);
        field3=findViewById(R.id.email_sign_up);
        field4=findViewById(R.id.Date_of_birth);
        field5=findViewById(R.id.password);
        field6=findViewById(R.id.password2);
        registerBTN=findViewById(R.id.btn_create);
        returnPrevious=findViewById(R.id.register_id);

        //Making my own sharedPreference and calling it myDatabase and setting it to private
        myPref=getSharedPreferences("myDatabase",Context.MODE_PRIVATE);
        myEditor=myPref.edit(); //setting editor so I can save info to shared preferences


        /*When the variable returnPrevious is clicked in the UI, it will stop and destroy the current intent/activity and return to the previous activity
        * that was not destroyed*/
        returnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /*
        * Validates all fields when the button is pressed for registration
        * if all fields are correctly inputted, it will create the account*/
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(field1.length()<3) //checks that the field is at least 3 characters long, otherwise returns error message
                    field1.setError("Needs to be at least 3 characters long");
                else if(field2.length()<3)//checks that the field is at least 3 characters long, otherwise returns error message
                    field2.setError("Needs to be at least 3 characters long");
                else if(!isValidEmail(field3.getText())) //Uses the defined function to determine if the inputted information is correct, otherwise returns an error message
                    field3.setError("Needs to be an email address"+field3.getText());
                else if(field4.length()<3)//checks that the field is at least 3 characters long, otherwise returns error message
                    field4.setError("Needs to be date of birth");
                else if(field5.length()<6)//checks that the field is at least 6 characters long, otherwise returns error message
                    field5.setError("Needs to be at least 6 characters long");
                else if(!field6.getText().toString().equals(field5.getText().toString()))//Checks that this field is equal to field 5, otherwise returns an error message
                    field6.setError("Password needs to match");
                else
                {
                    myEditor.putString(getString(R.string.userFName),field1.getText().toString()); //finds the key in the xml string file and sets the value
                    myEditor.apply();//applies to sharedPreferences
                    myEditor.putString(getString(R.string.userLName),field2.getText().toString());//finds the key in the xml string file and sets the value
                    myEditor.apply();//applies to sharedPreferences
                    myEditor.putString(getString(R.string.userEmail),field3.getText().toString());//finds the key in the xml string file and sets the value
                    myEditor.apply();//applies to sharedPreferences
                    myEditor.putString(getString(R.string.userDate),field4.getText().toString());//finds the key in the xml string file and sets the value
                    myEditor.apply();//applies to sharedPreferences
                    myEditor.putString(getString(R.string.userPassword),field5.getText().toString());//finds the key in the xml string file and sets the value
                    myEditor.apply();//applies to sharedPreferences

                    //String name =myPref.getString(getString(R.string.userFName),"default");

                    registerBTN.setText(getString(R.string.complete));//replaces text of the registerBTN
                    registerBTN.setBackgroundColor(Color.GREEN);//replaces color of the registerBTN
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1500);/*
                               This function will execute after 1.5 seconds and it will thenstop and destroy the current intent/activity and return to the previous activity
        * that was not destroyed
                                */

                }

            }
        });


    }

    /**
     *
     * @param target the string email
     * @return boolean true if the string is in email format, otherwise false
     */
    public static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
