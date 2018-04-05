package com.design.senior.what2eat.DatabaseComponents;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;

import java.util.List;

/**
 * Created by KJ on 3/7/2018.
 */

@Dao
public interface MealDao {

    @Query("SELECT * FROM Meal")
    List<Meal> getAllMeals();

    @Query("SELECT * FROM Meal WHERE EntryType = 'CUSTOM'")
    List<Meal> getAllCustomMeals();

    @Query("SELECT mealID FROM Meal")
    List<Integer> getAllMealKeys();

    @Insert
    void insertMealTuples(Meal... meals);

    @Update
    void updateMealTuples(Meal... meals);

    @Delete
    void deleteMealTuple(Meal meal);

    @Query("DELETE FROM Meal WHERE EntryType = 'CUSTOM'")
    void deleteAllCustomMeals();
}
