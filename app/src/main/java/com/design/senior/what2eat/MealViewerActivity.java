package com.design.senior.what2eat;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.Fragments.MealViewerFragment;

/**
 * Created by KJ on 2/3/2018.
 */

public class MealViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealviewer_layout);

        Intent incomingIntent = getIntent();
        Meal meal = (Meal) incomingIntent.getParcelableExtra("meal");

        MealViewerFragment mealViewerFragment = MealViewerFragment.newInstance(meal);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, mealViewerFragment);
        transaction.commit();
    }
}
