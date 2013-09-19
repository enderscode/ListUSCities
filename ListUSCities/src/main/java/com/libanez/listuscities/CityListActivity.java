package com.libanez.listuscities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.libanez.listuscities.fragments.CityDetailsFragment;
import com.libanez.listuscities.fragments.CityListFragment;
import com.libanez.listuscities.listeners.CityListener;
import com.libanez.listuscities.model.City;

public class CityListActivity extends FragmentActivity implements CityListener {
  private boolean detailsInline=false;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    CityListFragment cityListFragment
      =(CityListFragment)getSupportFragmentManager()
                            .findFragmentById(R.id.cities);
    
    cityListFragment.setCityListener(this);
    
    Fragment f=getSupportFragmentManager().findFragmentById(R.id.details);
    
    detailsInline=(f!=null);
    
    if (detailsInline) {
      cityListFragment.enablePersistentSelection();
    }
    else if (f!=null) {
      f.getView().setVisibility(View.GONE);
    }
  }
  
  @Override
  public void onCitySelected(City c) {
    String name=c.name;
                            
    if (detailsInline) {
      ((CityDetailsFragment)getSupportFragmentManager()
                            .findFragmentById(R.id.details))
                            .setText(name);
    }
    else {
      Intent i=new Intent(this, CityDetailsActivity.class);
      
      i.putExtra(CityDetailsActivity.NAME, name);
      startActivity(i);
    }
  }
}
