package com.example.andriginting.kamus_ku.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Andri Ginting on 12/24/2017.
 */

public class FirstPreference {
    SharedPreferences preferences;
    Context context;
    public static final String KEY_RUN= "first_key";

    public FirstPreference(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setFirstInstalled(Boolean run){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_RUN,run);
        editor.commit();
    }

    public Boolean getFirstRun(){
        return preferences.getBoolean(KEY_RUN,true);
    }
}
