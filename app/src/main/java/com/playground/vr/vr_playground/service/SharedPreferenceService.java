package com.playground.vr.vr_playground.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.playground.vr.vr_playground.inject.ApplicationContext;

/**
 * Created by petnagy on 2017. 09. 03..
 */

public class SharedPreferenceService implements PreferenceService {

    private static final String SHARED_PREFERENCE = "SharedPreference";

    private static final String FIRST_LAUNCH = "firstLaunch";

    private SharedPreferences sharedPreferences;

    public SharedPreferenceService(@ApplicationContext Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }

    @Override
    public void setFirstLaunch() {
        SharedPreferences.Editor editor = getSharedPrefEditor(sharedPreferences);
        editor.putBoolean(FIRST_LAUNCH, false);
        editor.commit();
    }

    private SharedPreferences.Editor getSharedPrefEditor(SharedPreferences sharedPref) {
        return sharedPref.edit();
    }
}
