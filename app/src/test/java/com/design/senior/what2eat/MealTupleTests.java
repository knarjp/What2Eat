package com.design.senior.what2eat;

import com.design.senior.what2eat.DatabaseComponents.Entities.MealTuple;
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

public class MealTupleTests {
    @Test
    public void testAllergySetter() throws Exception {
        MealTuple meal = new MealTuple();

        List<AllergyType> allergyTypes = new ArrayList<>();

        allergyTypes.add(AllergyType.NONE);

        meal.setAllergies(allergyTypes);

        Assert.assertTrue(meal.getAllergies().contains(AllergyType.NONE));

        Assert.assertFalse(meal.getAllergies().contains(AllergyType.MILK));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.EGGS));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.FISH));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.SHELLFISH));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.TREENUTS));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.PEANUTS));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.WHEAT));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.SOY));

        allergyTypes.clear();

        allergyTypes.add(AllergyType.MILK);
        allergyTypes.add(AllergyType.EGGS);
        allergyTypes.add(AllergyType.FISH);
        allergyTypes.add(AllergyType.SHELLFISH);
        allergyTypes.add(AllergyType.TREENUTS);
        allergyTypes.add(AllergyType.PEANUTS);
        allergyTypes.add(AllergyType.WHEAT);
        allergyTypes.add(AllergyType.SOY);

        meal.setAllergies(allergyTypes);

        Assert.assertTrue(meal.getAllergies().contains(AllergyType.MILK));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.EGGS));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.FISH));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.SHELLFISH));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.TREENUTS));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.PEANUTS));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.WHEAT));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.SOY));

        Assert.assertFalse(meal.getAllergies().contains(AllergyType.NONE));

        allergyTypes.clear();

        allergyTypes.add(AllergyType.MILK);
        allergyTypes.add(AllergyType.EGGS);
        allergyTypes.add(AllergyType.SHELLFISH);
        allergyTypes.add(AllergyType.WHEAT);
        allergyTypes.add(AllergyType.SOY);

        meal.setAllergies(allergyTypes);

        Assert.assertTrue(meal.getAllergies().contains(AllergyType.MILK));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.EGGS));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.SHELLFISH));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.WHEAT));
        Assert.assertTrue(meal.getAllergies().contains(AllergyType.SOY));

        Assert.assertFalse(meal.getAllergies().contains(AllergyType.NONE));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.FISH));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.TREENUTS));
        Assert.assertFalse(meal.getAllergies().contains(AllergyType.PEANUTS));

    }

    @Test
    public void testDietTypeSetter() throws Exception {
        MealTuple meal = new MealTuple();

        meal.setDietType(DietType.NONE);

        Assert.assertEquals(meal.getDietType(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietType(), DietType.VEGAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.PALEO);

        meal.setDietType(DietType.VEGAN);

        Assert.assertEquals(meal.getDietType(), DietType.VEGAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietType(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.PALEO);

        meal.setDietType(DietType.VEGETARIAN);

        Assert.assertEquals(meal.getDietType(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietType(), DietType.VEGAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.PALEO);

        meal.setDietType(DietType.PESCETARIAN);

        Assert.assertEquals(meal.getDietType(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietType(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.VEGAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.PALEO);

        meal.setDietType(DietType.PALEO);

        Assert.assertEquals(meal.getDietType(), DietType.PALEO);
        Assert.assertNotEquals(meal.getDietType(), DietType.NONE);
        Assert.assertNotEquals(meal.getDietType(), DietType.VEGETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.PESCETARIAN);
        Assert.assertNotEquals(meal.getDietType(), DietType.VEGAN);
    }

    @Test
    public void testEntryTypeSetter() throws Exception {
        MealTuple meal = new MealTuple();

        meal.setEntryType(EntryType.CUSTOM);

        Assert.assertEquals(meal.getEntryType(), EntryType.CUSTOM);
        Assert.assertNotEquals(meal.getEntryType(), EntryType.DEFAULT);

        meal.setEntryType(EntryType.DEFAULT);

        Assert.assertEquals(meal.getEntryType(), EntryType.DEFAULT);
        Assert.assertNotEquals(meal.getEntryType(), EntryType.CUSTOM);
    }

    @Test
    public void tesMealTimeSetter() throws Exception {
        MealTuple meal = new MealTuple();

        meal.setMealTime(MealTime.BREAKFAST);

        Assert.assertEquals(meal.getMealTime(), MealTime.BREAKFAST);
        Assert.assertNotEquals(meal.getMealTime(), MealTime.LUNCH);
        Assert.assertNotEquals(meal.getMealTime(), MealTime.DINNER);

        meal.setMealTime(MealTime.LUNCH);

        Assert.assertEquals(meal.getMealTime(), MealTime.LUNCH);
        Assert.assertNotEquals(meal.getMealTime(), MealTime.BREAKFAST);
        Assert.assertNotEquals(meal.getMealTime(), MealTime.DINNER);

        meal.setMealTime(MealTime.DINNER);

        Assert.assertEquals(meal.getMealTime(), MealTime.DINNER);
        Assert.assertNotEquals(meal.getMealTime(), MealTime.LUNCH);
        Assert.assertNotEquals(meal.getMealTime(), MealTime.BREAKFAST);
    }
}
