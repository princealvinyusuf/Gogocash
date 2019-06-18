package com.uas.uas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView logo;
    SharedPreferences preferences;
    private static final String PREF_NAME = "gogocash";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = findViewById(R.id.logo);


        final Intent intent = new Intent(this, LoginActivity.class);

        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);


        Thread timer = new Thread() {
            public void run() {

                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    startActivity(intent);
                    finish();
                }
            }
        };

        timer.start();

    }
}
