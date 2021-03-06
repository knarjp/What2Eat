package com.design.senior.what2eat.DatabaseComponents.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by KJ on 3/5/2018.
 */

@Entity(tableName = "Entry")
public class Entry {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID", typeAffinity = ColumnInfo.INTEGER)
    private int ID;

    @NonNull
    @ColumnInfo(name = "Date", typeAffinity = ColumnInfo.TEXT) // STORED IN FORMAT MM/DD/YYYY
    private String date;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate()  {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getDateAsDate() {
        Date date = new Date();

        try {
            date = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US).parse(this.date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    public void setDate(Date date) {
        this.date = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US).format(date);
    }
}