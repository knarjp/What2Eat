package com.design.senior.what2eat;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.design.senior.what2eat.DatabaseComponents.Entities.Entry;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;
import com.design.senior.what2eat.DatabaseComponents.Enums.MealTime;
import com.design.senior.what2eat.MealGenerators.MealGenerator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KJ on 3/24/2018.
 */

@RunWith(AndroidJUnit4.class)
public class MealGeneratorInstrumentedTests {

    @Test
    public void testMealGeneratorSetters() throws Exception {
        MealGenerator generator = new MealGenerator();

        Assert.assertEquals(0, generator.getCalorieTarget(), 0);

        generator.setCalorieTarget(2200);

        Assert.assertEquals(2200, generator.getCalorieTarget(), 0);

        generator.setCalorieTarget(-3200);

        Assert.assertEquals(2200, generator.getCalorieTarget(), 0);

        Assert.assertTrue(generator.getAllowedDiets().contains(DietType.NONE));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.PALEO));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.PESCETARIAN));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.VEGAN));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.VEGETARIAN));

        List<DietType> diets = new ArrayList<>();

        diets.add(DietType.PALEO);
        diets.add(DietType.PESCETARIAN);

        generator.setAllowedDiets(diets);

        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.NONE));
        Assert.assertTrue(generator.getAllowedDiets().contains(DietType.PALEO));
        Assert.assertTrue(generator.getAllowedDiets().contains(DietType.PESCETARIAN));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.VEGAN));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.VEGETARIAN));

        diets.clear();

        diets.add(DietType.VEGAN);
        diets.add(DietType.VEGETARIAN);

        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.NONE));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.PALEO));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.PESCETARIAN));
        Assert.assertTrue(generator.getAllowedDiets().contains(DietType.VEGAN));
        Assert.assertTrue(generator.getAllowedDiets().contains(DietType.VEGETARIAN));

        List<AllergyType> allergyTypes = new ArrayList<>();
        allergyTypes.add(AllergyType.EGGS);
        allergyTypes.add(AllergyType.FISH);
        allergyTypes.add(AllergyType.PEANUTS);

        Assert.assertTrue(generator.getDisallowedAllergies().contains(AllergyType.NONE));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.EGGS));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.FISH));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.MILK));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.PEANUTS));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.SHELLFISH));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.SOY));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.TREENUTS));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.WHEAT));

        generator.setDisallowedAllergies(allergyTypes);

        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.NONE));
        Assert.assertTrue(generator.getDisallowedAllergies().contains(AllergyType.EGGS));
        Assert.assertTrue(generator.getDisallowedAllergies().contains(AllergyType.FISH));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.MILK));
        Assert.assertTrue(generator.getDisallowedAllergies().contains(AllergyType.PEANUTS));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.SHELLFISH));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.SOY));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.TREENUTS));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.WHEAT));

        diets.clear();
        allergyTypes.clear();

        generator.setDisallowedAllergies(allergyTypes);
        generator.setAllowedDiets(diets);

        Assert.assertTrue(generator.getAllowedDiets().contains(DietType.NONE));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.PALEO));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.PESCETARIAN));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.VEGAN));
        Assert.assertFalse(generator.getAllowedDiets().contains(DietType.VEGETARIAN));

        Assert.assertTrue(generator.getDisallowedAllergies().contains(AllergyType.NONE));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.EGGS));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.FISH));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.MILK));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.PEANUTS));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.SHELLFISH));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.SOY));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.TREENUTS));
        Assert.assertFalse(generator.getDisallowedAllergies().contains(AllergyType.WHEAT));
    }

    @Test
    public void testMealGeneratorGeneration() throws Exception {
        MealGenerator generator = new MealGenerator();
        generator.setCalorieTarget(2150);

        Meal breakfast1 = new Meal();
        breakfast1.setMealID(0);
        breakfast1.setCaloricAmount(500);
        breakfast1.setMealTimeEnum(MealTime.BREAKFAST);

        Meal breakfast2 = new Meal();
        breakfast2.setMealID(1);
        breakfast2.setCaloricAmount(250);
        breakfast2.setMealTimeEnum(MealTime.BREAKFAST);

        Meal breakfast3 = new Meal();
        breakfast3.setMealID(2);
        breakfast3.setCaloricAmount(800);
        breakfast3.setMealTimeEnum(MealTime.BREAKFAST);

        Meal breakfast4 = new Meal();
        breakfast4.setMealID(3);
        breakfast4.setCaloricAmount(425);
        breakfast4.setMealTimeEnum(MealTime.BREAKFAST);

        Meal breakfast5 = new Meal();
        breakfast5.setMealID(4);
        breakfast5.setCaloricAmount(100);
        breakfast5.setMealTimeEnum(MealTime.BREAKFAST);

        Meal breakfast6 = new Meal();
        breakfast6.setMealID(5);
        breakfast6.setCaloricAmount(335);
        breakfast6.setMealTimeEnum(MealTime.BREAKFAST);

        Meal lunch1 = new Meal();
        lunch1.setMealID(6);
        lunch1.setCaloricAmount(125);
        lunch1.setMealTimeEnum(MealTime.LUNCH);

        Meal lunch2 = new Meal();
        lunch2.setMealID(7);
        lunch2.setCaloricAmount(250);
        lunch2.setMealTimeEnum(MealTime.LUNCH);

        Meal lunch3 = new Meal();
        lunch3.setMealID(8);
        lunch3.setCaloricAmount(650);
        lunch3.setMealTimeEnum(MealTime.LUNCH);

        Meal lunch4 = new Meal();
        lunch4.setMealID(9);
        lunch4.setCaloricAmount(725);
        lunch4.setMealTimeEnum(MealTime.LUNCH);

        Meal lunch5 = new Meal();
        lunch5.setMealID(10);
        lunch5.setCaloricAmount(480);
        lunch5.setMealTimeEnum(MealTime.LUNCH);

        Meal lunch6 = new Meal();
        lunch6.setMealID(11);
        lunch6.setCaloricAmount(100);
        lunch6.setMealTimeEnum(MealTime.LUNCH);

        Meal dinner1 = new Meal();
        dinner1.setMealID(12);
        dinner1.setCaloricAmount(1100);
        dinner1.setMealTimeEnum(MealTime.DINNER);

        Meal dinner2 = new Meal();
        dinner2.setMealID(13);
        dinner2.setCaloricAmount(440);
        dinner2.setMealTimeEnum(MealTime.DINNER);

        Meal dinner3 = new Meal();
        dinner3.setMealID(14);
        dinner3.setCaloricAmount(670);
        dinner3.setMealTimeEnum(MealTime.DINNER);

        Meal dinner4 = new Meal();
        dinner4.setMealID(15);
        dinner4.setCaloricAmount(850);
        dinner4.setMealTimeEnum(MealTime.DINNER);

        Meal dinner5 = new Meal();
        dinner5.setMealID(16);
        dinner5.setCaloricAmount(425);
        dinner5.setMealTimeEnum(MealTime.DINNER);

        Meal dinner6 = new Meal();
        dinner6.setMealID(17);
        dinner6.setCaloricAmount(375);
        dinner6.setMealTimeEnum(MealTime.DINNER);

        List<Meal> mealList = new ArrayList<>();
        mealList.add(breakfast1);
        mealList.add(breakfast2);
        mealList.add(breakfast3);
        mealList.add(breakfast4);
        mealList.add(breakfast5);
        mealList.add(breakfast6);
        mealList.add(lunch1);
        mealList.add(lunch2);
        mealList.add(lunch3);
        mealList.add(lunch4);
        mealList.add(lunch5);
        mealList.add(lunch6);
        mealList.add(dinner1);
        mealList.add(dinner2);
        mealList.add(dinner3);
        mealList.add(dinner4);
        mealList.add(dinner5);
        mealList.add(dinner6);

        Entry date1 = new Entry();
        date1.setID(0);

        Entry date2 = new Entry();
        date2.setID(1);

        List<Entry> entryList = new ArrayList<>();
        entryList.add(date1);
        entryList.add(date2);

        List<MealEntryJoin> generatedMeals;

        generatedMeals = generator.generateMeals(mealList, entryList);

        Assert.assertFalse(generatedMeals.isEmpty());

        List<Meal> date1Meals = new ArrayList<>();
        List<Meal> date2Meals = new ArrayList<>();

        for(MealEntryJoin tuple : generatedMeals) {
            for (Meal meal : mealList) {
                if (tuple.getMeal() == meal.getMealID()) {
                    if(tuple.getEntry() == date1.getID()) {
                        date1Meals.add(meal);
                    } else if (tuple.getEntry() == date2.getID()) {
                        date2Meals.add(meal);
                    }
                }
            }
        }

        int date1Calories = 0;
        int date2Calories = 0;

        for(Meal meal : date1Meals) {
            date1Calories += meal.getCaloricAmount();
        }

        for(Meal meal : date2Meals) {
            date2Calories += meal.getCaloricAmount();
        }

        Assert.assertEquals(2150, date1Calories, 150); // assert true if calories between 2000 and 2300
        Assert.assertEquals(2150, date2Calories, 150); // assert true if calories between 2000 and 2300
    }
}
