package com.uas.uas.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uas.uas.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
