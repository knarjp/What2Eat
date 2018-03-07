package com.design.senior.what2eat.DatabaseComponents.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by KJ on 3/6/2018.
 */
// TODO: add index for entry to make queries faster
@Entity(tableName = "MealEntryJoin",
        primaryKeys =  {"meal", "entry"},
        foreignKeys =  {
                @ForeignKey(entity = Meal.class,
                            parentColumns =  "MealID",
                            childColumns =  "meal",
                            onDelete = CASCADE),
                @ForeignKey(entity = Entry.class,
                            parentColumns = "ID",
                            childColumns = "entry",
                            onDelete = CASCADE)
        })
public class MealEntryJoin {

    @ColumnInfo(name = "meal", typeAffinity = ColumnInfo.INTEGER)
    private final int meal;
    @ColumnInfo(name = "entry", typeAffinity = ColumnInfo.INTEGER)
    private final int entry;

    public MealEntryJoin(final int meal, final int entry) {
        this.meal = meal;
        this.entry = entry;
    }

    public int getMeal() {
        return meal;
    }

    public int getEntry() {
        return entry;
    }
}
