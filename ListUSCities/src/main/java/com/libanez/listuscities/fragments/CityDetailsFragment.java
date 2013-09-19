package com.libanez.listuscities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.libanez.listuscities.R;


public class CityDetailsFragment extends Fragment {

    static private final String CITY_NAME = "city_name";

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        if (state != null) {
            String name = state.getString(CITY_NAME, "");

            if (!name.isEmpty()) {
                setText(name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.details_fragment, container, false));
    }

    public void setText(String name) {
        ((TextView) (getView().findViewById(R.id.cityName))).setText(name);
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        state.putString(CITY_NAME, ((TextView) getView().findViewById(R.id.cityName)).getText().toString());
    }
}
