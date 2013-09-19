package com.libanez.listuscities.util;

import com.libanez.listuscities.model.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by libanez on 18/09/2013.
 */
public class Util {

    public static ArrayList<City> loadFile(InputStream inputStream) throws IOException {
        ArrayList<City> arrayList = new ArrayList<City>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        for (String line; (line = br.readLine()) != null; ) {
            arrayList.add(new City(line));
        }
        br.close();

        return arrayList;
    }

    public static ArrayList<City> loadFile(InputStream inputStream, int limit) throws IOException {
        ArrayList<City> arrayList = new ArrayList<City>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        for (String line; (line = br.readLine()) != null; ) {
            arrayList.add(new City(line));
            if(arrayList.size() >= limit){
                return arrayList;
            }
        }
        br.close();

        return arrayList;
    }
}
