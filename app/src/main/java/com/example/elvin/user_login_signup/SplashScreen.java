/**
 * @author Elvin Martinez on  Sept, 2018
 */

package com.example.elvin.user_login_signup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SplashScreen extends AppCompatActivity {
    /*Makes view from file called splash_screen.xml onCreate*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(SplashScreen.this,MainActivity.class); //makes a new intent/activity that leads from this class to another called MainActivity
                startActivity(i); //starts the new activity specified by i
                finish(); //stops and destroys the current display
            }
        }, 2000);  /*
                               This class displays the splash screen design specified by splash_screen.xml for a total of 2 seconds before executing the next function
                               which is cto create a new intent using the code of the class MainActivity.class and stopping and destroying the current splash screen
                                */
    }

}
