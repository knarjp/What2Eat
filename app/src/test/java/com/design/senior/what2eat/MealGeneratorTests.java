package com.design.senior.what2eat;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;
import com.design.senior.what2eat.MealGenerators.MealGenerator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJ on 3/24/2018.
 */

public class MealGeneratorTests {

    @Test
    public void testMealGeneratorSetters() throws Exception {
        MealGenerator generator = new MealGenerator();

        Assert.assertEquals(0, generator.getCalorieTarget(), 0);

        generator.setCalorieTarget(2200);

        Assert.assertEquals(2200, generator.getCalorieTarget(), 0);

        generator.setCalorieTarget(-3200);

        Assert.assertEquals(2200, generator.getCalorieTarget(), 0);
    }
}
