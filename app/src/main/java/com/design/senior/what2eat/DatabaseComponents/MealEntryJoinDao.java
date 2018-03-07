package com.design.senior.what2eat.DatabaseComponents;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.design.senior.what2eat.DatabaseComponents.Entities.Entry;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;

import java.util.List;

/**
 * Created by KJ on 3/7/2018.
 */

@Dao
public interface MealEntryJoinDao {

    @Insert
    void insertMealEntry(MealEntryJoin mealEntryJoin);

    @Query("SELECT MealID, Name, Picture, Ingredients, Recipe, CaloricAmount, Allergies, DietType, MealTime, EntryType FROM Meal " +
            "INNER JOIN MealEntryJoin ON Meal.MealID=MealEntryJoin.meal " +
            "WHERE MealEntryJoin.entry=:entryID")
    List<Meal> getMealsFromEntry(int entryID);

    @Query("SELECT ID, Date FROM Entry " +
            "INNER JOIN MealEntryJoin ON Entry.ID=MealEntryJoin.entry " +
            "WHERE MealEntryJoin.meal=:mealID")
    List<Entry> getEntriesFromMeal(int mealID);
}
