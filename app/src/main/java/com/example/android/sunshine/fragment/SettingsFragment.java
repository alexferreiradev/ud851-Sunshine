package com.example.android.sunshine.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;
import android.widget.Toast;

import com.example.android.sunshine.R;

/**
 * Created by alex on 01/05/18.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_forecast);

        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
            Preference preference = preferenceScreen.getPreference(i);
            if (!(preference instanceof CheckBoxPreference)) {
                String preferenceValue = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, preferenceValue);
            }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        Preference locationKey = findPreference(getString(R.string.pref_location_key));
        locationKey.setOnPreferenceChangeListener(this);
    }

    private void setPreferenceSummary(Preference preference, String preferenceValue) {
        if (preference instanceof EditTextPreference) {
            preference.setSummary(preferenceValue);
        } else if (preference instanceof ListPreference) {
            int indexOfValue = ((ListPreference) preference).findIndexOfValue(preferenceValue);
            preference.setSummary(((ListPreference) preference).getEntries()[indexOfValue]);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (!(preference instanceof CheckBoxPreference)) {
            setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference instanceof EditTextPreference) {
            if (isLocationValueInvalid(newValue)) {
                Toast.makeText(getContext(), "Insira um cep valido com 10 numeros", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    private boolean isLocationValueInvalid(Object newValue) {
        if (newValue instanceof String) {
            String locationString = (String) newValue;
            try {
                Integer locationNumber = Integer.valueOf(locationString);
                if (locationNumber > 99999999) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                return true;
            }
        }

        return false;
    }
}
