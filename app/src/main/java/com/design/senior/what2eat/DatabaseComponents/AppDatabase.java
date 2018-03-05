package com.design.senior.what2eat.DatabaseComponents;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.design.senior.what2eat.DatabaseComponents.Entities.temp_data;

// RoomAsset - Copyright (c) 2018 Ibrahim Eid - see CREDITS.md for licensing credits
import com.huma.room_for_asset.RoomAsset;

/**
 * Created by KJ on 2/10/2018.
 */

@Database(entities = {temp_data.class},
        version = 2,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract tempDataDao tempDataDao();

    public static AppDatabase getAppDataBase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = RoomAsset.databaseBuilder(context, AppDatabase.class, "meal_entries.db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}