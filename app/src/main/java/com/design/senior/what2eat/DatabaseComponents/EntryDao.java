package com.design.senior.what2eat.DatabaseComponents;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.design.senior.what2eat.DatabaseComponents.Entities.Entry;

import java.util.List;

/**
 * Created by KJ on 3/7/2018.
 */

@Dao
public interface EntryDao {

    @Query("SELECT * FROM Entry")
    List<Entry> getAllEntries();

    @Query("SELECT * from Entry WHERE Date = :date")
    Entry getEntryWithDate(String date);

    @Insert
    void inserEntryTuples(Entry... entries);

    @Delete
    int deleteEntryTuple(Entry entry);
}
