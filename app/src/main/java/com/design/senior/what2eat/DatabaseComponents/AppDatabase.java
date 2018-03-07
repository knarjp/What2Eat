package com.design.senior.what2eat.DatabaseComponents;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

// RoomAsset - Copyright (c) 2018 Ibrahim Eid - see CREDITS.md for licensing credits
import com.huma.room_for_asset.RoomAsset;

import com.design.senior.what2eat.DatabaseComponents.Entities.Entry;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;

/**
 * Created by KJ on 2/10/2018.
 */

@Database(entities = {Meal.class, Entry.class, MealEntryJoin.class},
        version = 2,
        exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract MealDao getMealDao();
    public abstract EntryDao getEntryDao();
    public abstract MealEntryJoinDao getMealEntryJoinDao();

    public static AppDatabase getAppDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = RoomAsset.databaseBuilder(context, AppDatabase.class, "meal_entries.db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}