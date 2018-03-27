package com.design.senior.what2eat.DatabaseComponents.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by KJ on 3/6/2018.
 */

@Entity(tableName = "MealEntryJoin",
        foreignKeys =  {
                @ForeignKey(entity = Meal.class,
                            parentColumns =  "MealID",
                            childColumns =  "meal",
                            onDelete = CASCADE),
                @ForeignKey(entity = Entry.class,
                            parentColumns = "ID",
                            childColumns = "entry",
                            onDelete = CASCADE)
        },
        indices = {@Index(value = {"entry"}), @Index(value ={"meal"})
        })
public class MealEntryJoin {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "joinID", typeAffinity = ColumnInfo.INTEGER)
    private int joinID;
    @ColumnInfo(name = "meal", typeAffinity = ColumnInfo.INTEGER)
    private int meal;
    @ColumnInfo(name = "entry", typeAffinity = ColumnInfo.INTEGER)
    private int entry;

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

    public int getJoinID() { return joinID; }

    public void setJoinID(int joinID) {
        this.joinID = joinID;
    }
}
