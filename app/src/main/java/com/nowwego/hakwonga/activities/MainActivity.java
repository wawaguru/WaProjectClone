package com.nowwego.hakwonga.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nowwego.hakwonga.R;

/**
 * Main screen
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Creates a starting intent to start this activity
     * @param context Parent context
     * @return Starting intent
     */
    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setElevation(0);
    }
}
