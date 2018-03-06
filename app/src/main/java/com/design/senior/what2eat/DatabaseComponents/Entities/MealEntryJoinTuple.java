package com.design.senior.what2eat.DatabaseComponents.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by KJ on 3/6/2018.
 */

@Entity(tableName = "MealEntryJoin",
        primaryKeys =  {"meal", "entry"},
        foreignKeys =  {
                @ForeignKey(entity = MealTuple.class,
                            parentColumns =  "MealID",
                            childColumns =  "meal",
                            onDelete = CASCADE),
                @ForeignKey(entity = EntryTuple.class,
                            parentColumns = "ID",
                            childColumns = "entry",
                            onDelete = CASCADE)
        })
public class MealEntryJoinTuple {

    private final int meal;
    private final int entry;

    public MealEntryJoinTuple(final int meal, final int entry) {
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
