package com.example.android1noteapp;

public interface CardSource {
    Notes getNote(int position);

    int size();

    void deleteNote(int position);

    void updateNote(int position, Notes note);

    void addNote(Notes note);

    void clearNote();


}
