package com.design.senior.what2eat;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;
import com.design.senior.what2eat.DatabaseComponents.Enums.EntryType;
import com.design.senior.what2eat.DatabaseComponents.Enums.MealTime;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJ on 3/6/2018.
 */

public class MealTests {
    @Test
    public void testAllergySetter() throws Exception {
        Meal meal = new Meal();

        List<AllergyType> allergyTypes = new ArrayList<>();

        allergyTypes.add(AllergyType.NONE);

        meal.setAllergiesEnum(allergyTypes);

        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.NONE));

        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.MILK));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.EGGS));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.FISH));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.SHELLFISH));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.TREENUTS));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.PEANUTS));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.WHEAT));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.SOY));

        allergyTypes.clear();

        allergyTypes.add(AllergyType.MILK);
        allergyTypes.add(AllergyType.EGGS);
        allergyTypes.add(AllergyType.FISH);
        allergyTypes.add(AllergyType.SHELLFISH);
        allergyTypes.add(AllergyType.TREENUTS);
        allergyTypes.add(AllergyType.PEANUTS);
        allergyTypes.add(AllergyType.WHEAT);
        allergyTypes.add(AllergyType.SOY);

        meal.setAllergiesEnum(allergyTypes);

        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.MILK));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.EGGS));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.FISH));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.SHELLFISH));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.TREENUTS));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.PEANUTS));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.WHEAT));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.SOY));

        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.NONE));

        allergyTypes.clear();

        allergyTypes.add(AllergyType.MILK);
        allergyTypes.add(AllergyType.EGGS);
        allergyTypes.add(AllergyType.SHELLFISH);
        allergyTypes.add(AllergyType.WHEAT);
        allergyTypes.add(AllergyType.SOY);

        meal.setAllergiesEnum(allergyTypes);

        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.MILK));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.EGGS));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.SHELLFISH));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.WHEAT));
        Assert.assertTrue(meal.getAllergiesEnum().contains(AllergyType.SOY));

        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.NONE));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.FISH));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.TREENUTS));
        Assert.assertFalse(meal.getAllergiesEnum().contains(AllergyType.PEANUTS));

    }

    @Test
    public void testDietTypeSetter() throws Exception {
        Meal meal = new Meal();

        meal.setDietTypeEnum(DietType.NONE);

        Assert.assertEquals(meal.getDietTypeEnum(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.VEGAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.PALEO);

        meal.setDietTypeEnum(DietType.VEGAN);

        Assert.assertEquals(meal.getDietTypeEnum(), DietType.VEGAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.PALEO);

        meal.setDietTypeEnum(DietType.VEGETARIAN);

        Assert.assertEquals(meal.getDietTypeEnum(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.VEGAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.PALEO);

        meal.setDietTypeEnum(DietType.PESCETARIAN);

        Assert.assertEquals(meal.getDietTypeEnum(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.VEGAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.PALEO);

        meal.setDietTypeEnum(DietType.PALEO);

        Assert.assertEquals(meal.getDietTypeEnum(), DietType.PALEO);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietTypeEnum(), DietType.VEGAN);
    }

    @Test
    public void testEntryTypeSetter() throws Exception {
        Meal meal = new Meal();

        meal.setEntryTypeEnum(EntryType.CUSTOM);

        Assert.assertEquals(meal.getEntryTypeEnum(), EntryType.CUSTOM);
        Assert.assertNotEquals(meal.getEntryTypeEnum(), EntryType.DEFAULT);

        meal.setEntryTypeEnum(EntryType.DEFAULT);

        Assert.assertEquals(meal.getEntryTypeEnum(), EntryType.DEFAULT);
        Assert.assertNotEquals(meal.getEntryTypeEnum(), EntryType.CUSTOM);
    }

    @Test
    public void tesMealTimeSetter() throws Exception {
        Meal meal = new Meal();

        meal.setMealTimeEnum(MealTime.BREAKFAST);

        Assert.assertEquals(meal.getMealTimeEnum(), MealTime.BREAKFAST);
        Assert.assertNotEquals(meal.getMealTimeEnum(), MealTime.LUNCH);
        Assert.assertNotEquals(meal.getMealTimeEnum(), MealTime.DINNER);

        meal.setMealTimeEnum(MealTime.LUNCH);

        Assert.assertEquals(meal.getMealTimeEnum(), MealTime.LUNCH);
        Assert.assertNotEquals(meal.getMealTimeEnum(), MealTime.BREAKFAST);
        Assert.assertNotEquals(meal.getMealTimeEnum(), MealTime.DINNER);

        meal.setMealTimeEnum(MealTime.DINNER);

        Assert.assertEquals(meal.getMealTimeEnum(), MealTime.DINNER);
        Assert.assertNotEquals(meal.getMealTimeEnum(), MealTime.LUNCH);
        Assert.assertNotEquals(meal.getMealTimeEnum(), MealTime.BREAKFAST);
    }
}
