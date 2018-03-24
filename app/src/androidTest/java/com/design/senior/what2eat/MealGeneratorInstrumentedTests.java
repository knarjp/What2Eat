package com.design.senior.what2eat;

import android.support.test.runner.AndroidJUnit4;

import com.design.senior.what2eat.MealGenerators.MealGenerator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    }
}
