package com.design.senior.what2eat.DatabaseComponents.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by KJ on 3/5/2018.
 */

/*@Entity (foreignKeys = @ForeignKey(entity = MealTuple.class,
                                    parentColumns = "MealID",
                                    childColumns = "MealID",
                                    onDelete = CASCADE))*/
public class EntryTuple {
    /*@PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID", typeAffinity = ColumnInfo.INTEGER)
    private int ID;

    @NonNull
    @ColumnInfo(name = "MealID", typeAffinity = ColumnInfo.INTEGER)
    private int mealID;

    @NonNull
    @ColumnInfo(name = "Date", typeAffinity = ColumnInfo.TEXT) // STORED IN FORMAT dd/MM/yyyy
    private String date;

    static final String datePattern = "dd/MM/yyyy";

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public Date getDate() throws Exception {
        return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US).parse(date);
    }

    public void setDate(Date date) {
        this.date = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US).format(date);
    }*/
}