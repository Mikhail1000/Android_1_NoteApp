package com.example.android1noteapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Setting implements Parcelable {
    private String nameSetting;
    private String valueSetting;

    public Setting(String nameSetting, String valueSetting) {
        this.nameSetting = nameSetting;
        this.valueSetting = valueSetting;
    }

    protected Setting(Parcel in) {
        nameSetting = in.readString();
        valueSetting = in.readString();
    }

    public static final Creator<Setting> CREATOR = new Creator<Setting>() {
        @Override
        public Setting createFromParcel(Parcel in) {
            return new Setting(in);
        }

        @Override
        public Setting[] newArray(int size) {
            return new Setting[size];
        }
    };

    public String getNameSetting() {
        return nameSetting;
    }

    public void setNameSetting(String nameSetting) {
        this.nameSetting = nameSetting;
    }

    public String getValueSetting() {
        return valueSetting;
    }

    public void setValueSetting(String valueSetting) {
        this.valueSetting = valueSetting;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameSetting);
        dest.writeString(valueSetting);
    }
}
