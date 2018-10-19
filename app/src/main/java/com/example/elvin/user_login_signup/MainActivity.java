/**
 * @author Elvin Martinez on  Sept, 2018
 */

package com.example.elvin.user_login_signup;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

/*Makes view from file called activity_main.xml onCreate*/
public class MainActivity extends AppCompatActivity {

    //Declaration of variables
    TextView signUpText;
    EditText field1,field2;
    Button btn1;
    private SharedPreferences myPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPref=getSharedPreferences("myDatabase",Context.MODE_PRIVATE);

        //Variable given the id of object they will manipulate in the xml file
        signUpText=(TextView) findViewById(R.id.sign_up_id);
        field1=findViewById(R.id.email);
        field2=findViewById(R.id.password0);
        btn1=findViewById(R.id.btn_login);

        /*Makes new activity/intent from the SignUp.class when the variable signUpText is clicked in the UI*/
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });

        final String email =myPref.getString(getString(R.string.userEmail),"default");

        final String password =myPref.getString(getString(R.string.userPassword),"default");

        /*Checks if the two fields email and password are valid
        * if it is not valid it will give an error message.
        * if valid it will make a new activity from LoggedIn.class and stop and destroy this activity*/
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isValidEmail(field1.getText()))
                    field1.setError("Invalid Email");
                else if(field2.length()<1)
                    field2.setError("Must enter password");
                else if(!field1.getText().toString().equals(email))
                {
                    field1.setError("Invalid Email or Password");
                }
                else if(!field2.getText().toString().equals(password))
                {
                    field2.setError("Invalid Email or Password");
                }
                else {
                    startActivity(new Intent(getApplicationContext(), LoggedIn.class));
                    finish();
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
