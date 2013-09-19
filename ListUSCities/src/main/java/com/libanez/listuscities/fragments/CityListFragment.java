package com.libanez.listuscities.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.libanez.listuscities.R;
import com.libanez.listuscities.listeners.CityListener;
import com.libanez.listuscities.model.City;
import com.libanez.listuscities.util.Util;

import java.util.ArrayList;

public class CityListFragment extends ListFragment {
    static private ArrayList<City> cities = new ArrayList<City>();
    static private final String CITY_SELECTED = "city_selected";
    private CityListener listener = null;

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        fillCityNames();

        setListAdapter(new CityAdapter());

        if (state != null) {
            int position = state.getInt(CITY_SELECTED, -1);

            if (position > -1) {
                getListView().setItemChecked(position, true);
            }
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position,
                                long id) {
        l.setItemChecked(position, true);

        if (listener != null) {
            listener.onCitySelected(cities.get(position));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        state.putInt(CITY_SELECTED,
                getListView().getCheckedItemPosition());
    }

    public void setCityListener(CityListener listener) {
        this.listener = listener;
    }

    public void enablePersistentSelection() {
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private class CityAdapter extends ArrayAdapter<City> {
        CityAdapter() {
            super(getActivity(), R.layout.row, R.id.name, cities);
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            CityWrapper wrapper = null;

            if (convertView == null) {
                convertView = LayoutInflater
                        .from(getActivity())
                        .inflate(R.layout.row, null);
                wrapper = new CityWrapper(convertView);
                convertView.setTag(wrapper);
            } else {
                wrapper = (CityWrapper) convertView.getTag();
            }

            wrapper.populateFrom(getItem(position));

            return (convertView);
        }
    }

    private static class CityWrapper {
        private TextView name = null;

        public CityWrapper(View row) {
            name = (TextView) row.findViewById(R.id.name);
        }

        public TextView getName() {
            return (name);
        }

        public void populateFrom(City nation) {
            getName().setText(nation.name);
        }
    }

    private void fillCityNames() {
        GetCityNamesTask getNames = new GetCityNamesTask();
        getNames.execute();
    }

    private class GetCityNamesTask extends AsyncTask<Void, Void, ArrayList<City>> {

        @Override
        protected ArrayList<City> doInBackground(Void... voids) {

            try {
                cities = Util.loadFile(getResources().openRawResource(R.raw.uscities),100);
                setListAdapter(new CityAdapter());
                ((CityAdapter)getListView().getAdapter()).notifyDataSetChanged();

                cities = Util.loadFile(getResources().openRawResource(R.raw.uscities));
                return cities;
            } catch (Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "Couldn't fetch cities, try again later.",
                        Toast.LENGTH_SHORT).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<City> items) {
            setListAdapter(new CityAdapter());
            ((CityAdapter)getListView().getAdapter()).notifyDataSetChanged();
        }
    }
}
