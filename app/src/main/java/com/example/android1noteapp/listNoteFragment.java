package com.example.android1noteapp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class listNoteFragment extends Fragment {

    public static final String CURRENT_NOTE = "current_note";
    private int currentNote = 0;
    ArrayList<Notes> notes = new ArrayList<>();
    ArrayList<Setting> settings = new ArrayList<>();

    private static final String ARG_INDEX = "index";
    private static final String ARG_NOTES = "notes";

    private String mParam1;
    private String mParam2;

    public listNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null){
            currentNote = savedInstanceState.getInt(CURRENT_NOTE, 0);
        }

        initList(view);
        initButtons(view);

        if (isLandscape()){
            showLandNote(currentNote);
        }
    }

    private void initButtons(View view) {
        settings.add(new Setting("Ночная тема", "0"));
        settings.add(new Setting("Русский язык", "1"));
        settings.add(new Setting("От старых к новым", "0"));


        Button buttonSettings = view.findViewById(R.id.button_settings);
        buttonSettings.findViewById(R.id.button_settings).setOnClickListener(v -> {
            showSettings();
        });
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_NOTE, currentNote);
        super.onSaveInstanceState(outState);
    }

    private void initList(View view) {
        notes.add(new Notes("Заголовок 1", "Описание заметки 1", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 2", "Описание заметки 2", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 3", "Описание заметки 3", new GregorianCalendar()));

        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < notes.size(); i++) {
            TextView tv = new TextView(getContext());
            tv.setText(notes. get(i).getNameNote());
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(v -> {
                currentNote = position;
                showNote(position);
            });
        }
    }

    private void showNote(int index) {
        if (isLandscape()){
            showLandNote(index);
        } else {
            showPortNote(index);
        }
    }

    private void showPortNote(int index) {
        NoteFragment noteFragment = NoteFragment.newInstance(index, notes);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .add(R.id.fragment_container, noteFragment)
                .commit();
    }

    private void showLandNote(int index) {
        NoteFragment detail = NoteFragment.newInstance(index, notes);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_note, detail)
                .commit();
    }

    private void showSettings() {
        showPortNote();
    }

    private void showPortNote() {
        SettingsFragment settingsFragment = SettingsFragment.newInstance(settings);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .add(R.id.fragment_container, settingsFragment)
                .commit();
    }


}