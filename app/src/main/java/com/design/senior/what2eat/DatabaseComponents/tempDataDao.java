package com.design.senior.what2eat.DatabaseComponents;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.design.senior.what2eat.DatabaseComponents.Entities.temp_data;

import java.util.List;

/**
 * Created by KJ on 2/10/2018.
 */

@Dao
public interface tempDataDao {

    @Query("SELECT * from temp_data")
    List<temp_data> getAllData();

    @Insert
    void insertTuples(temp_data... data);

    @Delete
    void delete(temp_data data);
}
