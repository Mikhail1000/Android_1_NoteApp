package com.example.android1noteapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private static final String ARG_SETTINGS = "settings";

    ArrayList<Setting> settings;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(ArrayList<Setting> settings) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_SETTINGS, settings);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            settings = getArguments().getParcelableArrayList(ARG_SETTINGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            settings = getArguments().getParcelableArrayList(ARG_SETTINGS);

            LinearLayout layoutView = (LinearLayout) view;
            for (int i = 0; i < settings.size(); i++) {
                SwitchMaterial tv = new SwitchMaterial(getContext());
                tv.setText(settings. get(i).getNameSetting());
                tv.setTextSize(30);
                layoutView.addView(tv);
            }
        }
    }
}