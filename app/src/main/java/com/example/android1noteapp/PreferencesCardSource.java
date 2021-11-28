package com.example.android1noteapp;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreferencesCardSource implements CardSource{
    private List<Notes> notesList;
    private final SharedPreferences sharedPreferences;


    public PreferencesCardSource(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        fetch();
    }

    @Override
    public Notes getNote(int position) {
        return notesList.get(position);
    }

    @Override
    public int size() {
        return notesList.size();
    }

    @Override
    public void deleteNote(int position) {
        notesList.remove(position);
        update();
    }

    @Override
    public void updateNote(int position, Notes note) {
        notesList.set(position, note);
        update();
    }

    @Override
    public void addNote(Notes note) {
        notesList.add(note);
        update();
    }

    @Override
    public void clearNote() {
        notesList.clear();
        update();
    }

    private void fetch() {
        String json = sharedPreferences.getString("CARD_DATA", null);
        if (json == null) {
            notesList = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<Notes>>() {}.getType();
            notesList = new GsonBuilder().create().fromJson(json, type);
        }

        sharedPreferences.edit()
                .putString("CARD_DATA", json)
                .apply();
    }

    private void update() {

        String json = new GsonBuilder().create().toJson(notesList);
        sharedPreferences.edit()
                .putString("CARD_DATA", json)
                .apply();
    }
}
