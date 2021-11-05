package com.example.android1noteapp;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Notes {
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
}
