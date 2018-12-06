package iot.nvipash_harvardapi.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Map;

import iot.nvipash_harvardapi.entities.Record;

public class SharedPreferencesRepository {
    private static final String FAVOURITES = "favourites";

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesRepository( Context context) {
        sharedPreferences = context.getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
    }

    public void add(Record record){
        sharedPreferences.edit().putString(record.getTitle(), new Gson().toJson(record)).apply();
    }

    public void remove(Record record) {
        sharedPreferences.edit().remove(record.getTitle()).apply();
    }

    public boolean contains (String name) {
        return sharedPreferences.contains(name);
    }

    public Map<String, ?> getAll(){
        return sharedPreferences.getAll();
    }

}