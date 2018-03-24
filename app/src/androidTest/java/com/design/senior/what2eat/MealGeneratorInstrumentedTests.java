package com.design.senior.what2eat;

import android.support.test.runner.AndroidJUnit4;

import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;
import com.design.senior.what2eat.MealGenerators.MealGenerator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
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
}
