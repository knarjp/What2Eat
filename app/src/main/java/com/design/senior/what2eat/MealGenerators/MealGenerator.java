package com.design.senior.what2eat.MealGenerators;

import android.util.Log;

import com.design.senior.what2eat.DatabaseComponents.Entities.Entry;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<MealEntryJoin> generateMeals(List<Meal> mealsToUse, List<Entry> datesToUse) {
        List<MealEntryJoin> generatedMealsList = new ArrayList<>();

        List<Meal> allowedBreakfasts = new ArrayList<>();
        List<Meal> allowedLunches = new ArrayList<>();
        List<Meal> allowedDinners = new ArrayList<>();

        for(Meal mealCandidate : mealsToUse) {
            switch(mealCandidate.getMealTimeEnum()) {
                case BREAKFAST:
                    allowedBreakfasts.add(mealCandidate);
                    break;
                case LUNCH:
                    allowedLunches.add(mealCandidate);
                    break;
                case DINNER:
                    allowedDinners.add(mealCandidate);
                    break;
                default:
                    throw new RuntimeException();
            }

            if(!disallowedAllergies.contains(AllergyType.NONE)) {
                for (AllergyType badAllergy : disallowedAllergies) {
                    if (mealCandidate.getAllergiesEnum().contains(badAllergy)) {
                        switch(mealCandidate.getMealTimeEnum()) {
                            case BREAKFAST:
                                allowedBreakfasts.remove(mealCandidate);
                                break;
                            case LUNCH:
                                allowedLunches.remove(mealCandidate);
                                break;
                            case DINNER:
                                allowedDinners.remove(mealCandidate);
                                break;
                            default:
                                throw new RuntimeException();
                        }
                        break;
                    }
                }
            }

            if(!allowedDiets.contains(DietType.NONE) && !allowedDiets.contains(mealCandidate.getDietTypeEnum())) {
                switch(mealCandidate.getMealTimeEnum()) {
                    case BREAKFAST:
                        allowedBreakfasts.remove(mealCandidate);
                        break;
                    case LUNCH:
                        allowedLunches.remove(mealCandidate);
                        break;
                    case DINNER:
                        allowedDinners.remove(mealCandidate);
                        break;
                    default:
                        throw new RuntimeException();
                }
                break;
            }
        }

        for(Entry day : datesToUse) {
            int dayID = day.getID();
            int caloriesLeft = calorieTarget;

            Random rand = new Random();

            boolean hasNoBreakfast = true;
            boolean hasNoLunch = true;
            boolean hasNoDinner = true;

            while(hasNoBreakfast) {
                Meal meal = allowedBreakfasts.get(rand.nextInt(allowedBreakfasts.size()));

                if(meal.getCaloricAmount() < caloriesLeft/3 + 150) {
                    caloriesLeft -= meal.getCaloricAmount();

                    generatedMealsList.add(new MealEntryJoin(meal.getMealID(), dayID));

                    hasNoBreakfast = false;
                }
            }

            while(hasNoLunch) {
                Meal meal = allowedLunches.get(rand.nextInt(allowedLunches.size()));

                if(meal.getCaloricAmount() < caloriesLeft/3 + 150) {
                    caloriesLeft -= meal.getCaloricAmount();

                    generatedMealsList.add(new MealEntryJoin(meal.getMealID(), dayID));

                    hasNoLunch = false;
                }
            }

            while(hasNoDinner) {
                Meal meal = allowedDinners.get(rand.nextInt(allowedDinners.size()));

                if(meal.getCaloricAmount() < caloriesLeft/3 + 150) {
                    caloriesLeft -= meal.getCaloricAmount();

                    generatedMealsList.add(new MealEntryJoin(meal.getMealID(), dayID));

                    hasNoDinner = false;
                }
            }

            while(!(caloriesLeft < 150) && !(caloriesLeft < -150)) {
                Random randomTime = new Random();
                switch(randomTime.nextInt(4)) {
                    case 1:
                        hasNoBreakfast = true;

                        while(hasNoBreakfast) {
                            Meal meal = allowedBreakfasts.get(rand.nextInt(allowedBreakfasts.size()));

                            if(meal.getCaloricAmount() < caloriesLeft + 150) {
                                caloriesLeft -= meal.getCaloricAmount();

                                generatedMealsList.add(new MealEntryJoin(meal.getMealID(), dayID));

                                hasNoBreakfast = false;
                            }
                        }
                        break;
                    case 2:
                        hasNoLunch = true;

                        while(hasNoLunch) {
                            Meal meal = allowedLunches.get(rand.nextInt(allowedLunches.size()));

                            if(meal.getCaloricAmount() < caloriesLeft + 150) {
                                caloriesLeft -= meal.getCaloricAmount();

                                generatedMealsList.add(new MealEntryJoin(meal.getMealID(), dayID));

                                hasNoLunch = false;
                            }
                        }
                        break;
                    case 3:
                        hasNoDinner = true;

                        while(hasNoDinner) {
                            Meal meal = allowedDinners.get(rand.nextInt(allowedDinners.size()));

                            if(meal.getCaloricAmount() < caloriesLeft + 150) {
                                caloriesLeft -= meal.getCaloricAmount();

                                generatedMealsList.add(new MealEntryJoin(meal.getMealID(), dayID));

                                hasNoDinner = false;
                            }
                        }
                        break;
                }
            }
        }

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