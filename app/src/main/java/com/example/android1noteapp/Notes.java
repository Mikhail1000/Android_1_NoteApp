package com.example.android1noteapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Notes implements Parcelable {
    private String nameNote;
    private String descriptionNote;
    Calendar dateCreate;

    public Notes(String nameNote, String descriptionNote, Calendar dateCreate) {
        this.nameNote = nameNote;
        this.descriptionNote = descriptionNote;
        this.dateCreate = dateCreate;
    }

    public Notes(String nameNote, String descriptionNote) {
        this.nameNote = nameNote;
        this.descriptionNote = descriptionNote;
        this.dateCreate = new GregorianCalendar();
    }

    protected Notes(Parcel in) {
        nameNote = in.readString();
        descriptionNote = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getNameNote() {
        return nameNote;
    }

    public void setNameNote(String nameNote) {
        this.nameNote = nameNote;
    }

    public String getDescriptionNote() {
        return descriptionNote;
    }

    public void setDescriptionNote(String descriptionNote) {
        this.descriptionNote = descriptionNote;
    }

    public Calendar getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Calendar dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameNote);
        dest.writeString(descriptionNote);
    }
}
