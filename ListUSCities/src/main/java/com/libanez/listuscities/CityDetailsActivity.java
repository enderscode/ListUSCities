package com.libanez.listuscities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.libanez.listuscities.fragments.CityDetailsFragment;

public class CityDetailsActivity extends ActionBarActivity {
  public static final String NAME="name";
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details);

    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    
    CityDetailsFragment details
      =(CityDetailsFragment)getSupportFragmentManager()
                            .findFragmentById(R.id.details);
    
    details.setText(getIntent().getStringExtra(NAME));
  }
}
