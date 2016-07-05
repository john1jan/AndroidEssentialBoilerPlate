package com.johnfrancis.samplearch.ui.activities;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
