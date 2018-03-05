package com.design.senior.what2eat.DatabaseComponents.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by KJ on 3/5/2018.
 */

@Entity
public class MealTuple {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "MealID", typeAffinity = ColumnInfo.INTEGER)
    private int mealID;

    @NonNull
    @ColumnInfo(name = "Name", typeAffinity = ColumnInfo.TEXT)
    private String name;

    @NonNull
    @ColumnInfo(name = "Picture", typeAffinity = ColumnInfo.BLOB)
    private byte[] picture;

    @ColumnInfo(name = "Ingredients", typeAffinity = ColumnInfo.TEXT)
    private String ingredients;

    @ColumnInfo(name = "Recipe", typeAffinity = ColumnInfo.TEXT)
    private String recipe;

    @NonNull
    @ColumnInfo(name = "CaloricAmount", typeAffinity = ColumnInfo.INTEGER)
    private int caloricAmount;

    @NonNull
    @ColumnInfo(name = "Allergies", typeAffinity = ColumnInfo.TEXT)
    private String allergies;

    @NonNull
    @ColumnInfo(name = "DietType", typeAffinity = ColumnInfo.TEXT)
    private String dietType;

    @NonNull
    @ColumnInfo(name = "MealTime", typeAffinity = ColumnInfo.TEXT)
    private String MealTime;

    @NonNull
    @ColumnInfo(name = "EntryType", typeAffinity = ColumnInfo.TEXT)
    private String EntryType;

    @NonNull
    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public int getCaloricAmount() {
        return caloricAmount;
    }

    public void setCaloricAmount(int caloricAmount) {
        this.caloricAmount = caloricAmount;
    }

    // TODO: implement enum return type
    public String getAllergies() {
        return allergies;
    }

    // TODO: implement enum input type
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    // TODO: implement enum return type
    public String getDietType() {
        return dietType;
    }

    // TODO: implement enum input type
    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    // TODO: implement enum return type
    public String getMealTime() {
        return MealTime;
    }

    // TODO: implement enum input type
    public void setMealTime( String mealTime) {
        MealTime = mealTime;
    }

    // TODO: implement enum return type
    public String getEntryType() {
        return EntryType;
    }

    // TODO: implement enum input type
    public void setEntryType(String entryType) {
        EntryType = entryType;
    }
}