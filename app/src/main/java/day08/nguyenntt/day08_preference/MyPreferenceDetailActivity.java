package day08.nguyenntt.day08_preference;

import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyPreferenceDetailActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
//        setContentView(R.xml.preferences);
        SharedPreferences shared = getSharedPreferences("day08.nguyenntt.day08_preference_preferences",MODE_PRIVATE);
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            initData(getPreferenceScreen(), shared);
        }
    }

    private void initData(Preference p, SharedPreferences shared) {
        if (p instanceof PreferenceCategory) {
            PreferenceCategory category = (PreferenceCategory) p;
            for (int i = 0; i<category.getPreferenceCount(); i++) {
                initData(category.getPreference(i), shared);
            }
            //update preference
            updatePreference(p, shared);
        }
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        updatePreference(findPreference(s), sharedPreferences);
    }

    private void updatePreference(Preference p, SharedPreferences shared) {
        if (p instanceof PreferenceCategory) {
            EditTextPreference editTextPreference = (EditTextPreference) p;
            p.setSummary(editTextPreference.getText());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
}
