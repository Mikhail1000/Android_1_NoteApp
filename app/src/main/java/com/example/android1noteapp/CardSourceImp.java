package com.example.android1noteapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class CardSourceImp implements CardSource, Parcelable {

    ArrayList<Notes> notes;

    public CardSourceImp(@NonNull Context context) {
        this.notes = new ArrayList<>(Arrays.asList(
                new Notes("Заголовок 1", "Описание заметки 1", new GregorianCalendar()),
                new Notes("Заголовок 2", "Описание заметки 2", new GregorianCalendar()),
                new Notes("Заголовок 3", "Описание заметки 3", new GregorianCalendar()),
                new Notes("Заголовок 4", "Описание заметки 4", new GregorianCalendar()),
                new Notes("Заголовок 5", "Описание заметки 5", new GregorianCalendar()),
                new Notes("Заголовок 6", "Описание заметки 6", new GregorianCalendar()),
                new Notes("Заголовок 7", "Описание заметки 7", new GregorianCalendar()),
                new Notes("Заголовок 8", "Описание заметки 8", new GregorianCalendar()),
                new Notes("Заголовок 9", "Описание заметки 9", new GregorianCalendar())
        ));
    }

    protected CardSourceImp(Parcel in) {
        notes = in.createTypedArrayList(Notes.CREATOR);
    }

    public static final Creator<CardSourceImp> CREATOR = new Creator<CardSourceImp>() {
        @Override
        public CardSourceImp createFromParcel(Parcel in) {
            return new CardSourceImp(in);
        }

        @Override
        public CardSourceImp[] newArray(int size) {
            return new CardSourceImp[size];
        }
    };

    @Override
    public Notes getNote(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public void deleteNote(int position) {
        notes.remove(position);
    }

    @Override
    public void updateNote(int position, Notes note) {
        notes.set(position, note);
    }

    @Override
    public void addNote(Notes note) {
        notes.add(note);
    }

    @Override
    public void clearNote() {
        notes.clear();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(notes);
    }
}
