package com.design.senior.what2eat.MealGenerators;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KJ on 3/24/2018.
 */

public class MealGenerator {

    private int calorieTarget;
    private List<AllergyType> disallowedAllergies;
    private List<DietType> allowedDiets;

    public MealGenerator() {
        calorieTarget = 0;

        disallowedAllergies = new ArrayList<>();
        disallowedAllergies.add(AllergyType.NONE);

        allowedDiets = new ArrayList<>();
        allowedDiets.add(DietType.NONE);
    }

    public List<MealEntryJoin> generateMeals(List<Meal> availableMeals, Date startDate) {
        List<MealEntryJoin> generatedMealsList = new ArrayList<>();

        List<Meal> allowedMeals = new ArrayList<>();

       /* for(Meal meal : availableMeals) {

            allowedMeals.add(meal);

            if(!allowedDiets.contains(meal.getDietTypeEnum())) {
                allowedMeals.remove(meal);
            }

            for(AllergyType allergy : meal.getAllergiesEnum()) {
                if(disallowedAllergies.contains(allergy)) {

                }
            }
        }*/


        return generatedMealsList;
    }

    public int getCalorieTarget() {
        return calorieTarget;
    }

    public void setCalorieTarget(int calorieTarget) {
        if (calorieTarget > 0) {
            this.calorieTarget = calorieTarget;
        }
    }

    public List<AllergyType> getDisallowedAllergies() {
        return disallowedAllergies;
    }

    public void setDisallowedAllergies(List<AllergyType> disallowedAllergies) {
        this.disallowedAllergies = disallowedAllergies;

        if(this.disallowedAllergies.isEmpty()) {
            this.disallowedAllergies.add(AllergyType.NONE);
        }
    }

    public List<DietType> getAllowedDiets() {
        return allowedDiets;
    }

    public void setAllowedDiets(List<DietType> allowedDiets) {
        this.allowedDiets = allowedDiets;
        if(this.allowedDiets.isEmpty()) {
            this.allowedDiets.add(DietType.NONE);
        }
    }
}