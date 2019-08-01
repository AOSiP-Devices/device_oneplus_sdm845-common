/*
* Copyright (C) 2019 Android Open Source Illusion Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package com.aosip.device.DeviceSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.content.Intent;
import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.TwoStatePreference;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.util.Log;

import com.aosip.device.DeviceSettings.DeviceSettings;
import com.android.internal.util.aosip.FileUtils;
import com.aosip.device.DeviceSettings.Constants;

public class KcalSettings extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    private RedPreference mRed;
    private GreenPreference mGreen;
    private BluePreference mBlue;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.kcal);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

        mRed = (RedPreference) findPreference(DeviceSettings.KEY_RED);
        if (mRed != null) {
            mRed.setEnabled(RedPreference.isSupported());
        }

        mGreen = (GreenPreference) findPreference(DeviceSettings.KEY_GREEN);
        if (mGreen != null) {
            mGreen.setEnabled(GreenPreference.isSupported());
        }

        mBlue = (BluePreference) findPreference(DeviceSettings.KEY_BLUE);
        if (mBlue != null) {
            mBlue.setEnabled(BluePreference.isSupported());
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Constants.setPreferenceInt(getContext(), preference.getKey(), Integer.parseInt((String) newValue));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
