package com.johnfrancis.samplearch.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.johnfrancis.samplearch.api.ApiManager;
import com.johnfrancis.samplearch.utils.BusProvider;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by john.francis on 15/05/16.
 */
public class BaseActivity extends AppCompatActivity {

  ApiManager apiManager;

  private Unbinder unbinder;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    apiManager = new ApiManager(this);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    unbinder = ButterKnife.bind(this);
    BusProvider.getInstance().register(this);
  }

  @Override protected void onDestroy() {
    unbinder.unbind();
    BusProvider.getInstance().unregister(this);
    super.onDestroy();
  }
}
