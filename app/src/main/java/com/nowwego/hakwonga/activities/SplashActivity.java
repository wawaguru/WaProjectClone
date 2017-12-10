package com.nowwego.hakwonga.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Splash screen
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO: Later remove sleeping because there will be already some time consuming background processes here
        // Wait a little bit on the splash screen
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // There is nothing to do here.
        }

        Intent i = LoginActivity.getStartIntent(getApplicationContext());
        startActivity(i);
        finish();
    }
}
