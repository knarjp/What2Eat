package com.design.senior.what2eat.DatabaseComponents.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;
import com.design.senior.what2eat.DatabaseComponents.Enums.EntryType;
import com.design.senior.what2eat.DatabaseComponents.Enums.MealTime;

import java.util.ArrayList;
import java.util.List;

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
    private String mealTime;

    @NonNull
    @ColumnInfo(name = "EntryType", typeAffinity = ColumnInfo.TEXT)
    private String entryType;

    public MealTuple() {
        this.allergies = AllergyType.NONE.name() + ",";
        this.dietType = DietType.NONE.name() + ",";
        this.mealTime = MealTime.BREAKFAST.name();
        this.entryType = EntryType.DEFAULT.name();
    }

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

    public List<AllergyType> getAllergies() {
        List<AllergyType> allergiesList = new ArrayList<>();

        if(allergies.contains(AllergyType.NONE.name())) {
            allergiesList.add(AllergyType.NONE);
        }

        if(allergies.contains(AllergyType.MILK.name())) {
            allergiesList.add(AllergyType.MILK);
        }

        if(allergies.contains(AllergyType.EGGS.name())) {
            allergiesList.add(AllergyType.EGGS);
        }

        if(allergies.contains(AllergyType.FISH.name())) { // need to account for FISH and SHELLFISH having the same substring "FISH"
            if(allergies.startsWith(AllergyType.FISH.name())) { // FISH will either be at start of list
                allergiesList.add(AllergyType.FISH);
            } else if (allergies.contains("," + AllergyType.FISH.name())) { // or not, and have a comma before it
                allergiesList.add(AllergyType.FISH);
            }
        }

        if(allergies.contains(AllergyType.SHELLFISH.name())) {
            allergiesList.add(AllergyType.SHELLFISH);
        }

        if(allergies.contains(AllergyType.TREENUTS.name())) {
            allergiesList.add(AllergyType.TREENUTS);
        }

        if(allergies.contains(AllergyType.PEANUTS.name())) {
            allergiesList.add(AllergyType.PEANUTS);
        }

        if(allergies.contains(AllergyType.WHEAT.name())) {
            allergiesList.add(AllergyType.WHEAT);
        }

        if(allergies.contains(AllergyType.SOY.name())) {
            allergiesList.add(AllergyType.SOY);
        }

        return allergiesList;
    }


    public void setAllergies(List<AllergyType> allergiesList) {
        this.allergies = "";

        if(allergiesList.isEmpty()) {
            this.allergies = "NONE,";
        } else {
            for (AllergyType allergy : allergiesList) {
                this.allergies += allergy.name() + ',';
            }
        }
    }

    public DietType getDietType() {
        if(dietType.contains(DietType.VEGAN.name())) {
            return DietType.VEGAN;
        } else if(dietType.contains(DietType.VEGETARIAN.name())) {
            return DietType.VEGETARIAN;
        } else if(dietType.contains(DietType.PESCETARIAN.name())) {
            return DietType.PESCETARIAN;
        } else if(dietType.contains(DietType.PALEO.name())) {
            return DietType.PALEO;
        } else if(dietType.contains(DietType.NONE.name())) {
            return DietType.NONE;
        } else {
            throw new RuntimeException();
        }
    }

    public void setDietType(DietType dietType) {
        this.dietType = dietType.name();
    }

    public MealTime getMealTime() {
        if(mealTime.contains(MealTime.BREAKFAST.name())) {
            return MealTime.BREAKFAST;
        } else if(mealTime.contains(MealTime.LUNCH.name())) {
            return MealTime.LUNCH;
        } else if(mealTime.contains(MealTime.DINNER.name())) {
            return MealTime.DINNER;
        } else {
            throw new RuntimeException();
        }
    }

    public void setMealTime(MealTime mealTime) {
        this.mealTime = mealTime.name();
    }

    public EntryType getEntryType() {
        if(entryType.contains(EntryType.DEFAULT.name())) {
            return EntryType.DEFAULT;
        } else if(entryType.contains(EntryType.CUSTOM.name())) {
            return EntryType.CUSTOM;
        } else {
            throw new RuntimeException();
        }
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType.name();
    }
}