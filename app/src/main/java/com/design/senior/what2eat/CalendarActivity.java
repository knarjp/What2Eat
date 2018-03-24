package com.design.senior.what2eat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

// Material Calendar View - Copyright (c) 2017 Prolific Interactive - see CREDITS.md for licensing credits
import com.design.senior.what2eat.DatabaseComponents.AppDatabase;
import com.design.senior.what2eat.Fragments.CalendarOptionsFragment;
import com.design.senior.what2eat.Fragments.CalendarViewerFragment;
import com.design.senior.what2eat.MealGenerators.MealGenerator;

/**
 * Created by KJ on 2/3/2018.
 */

public class CalendarActivity extends AppCompatActivity
        implements CalendarViewerFragment.CalendarViewToParentActivityCommunicator {

    private MealGenerator mealGenerator;
    private AppDatabase appDatabase;

    private CalendarViewerFragment calendarViewFragment;
    private CalendarOptionsFragment optionsViewFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        calendarViewFragment = CalendarViewerFragment.newInstance();
        optionsViewFragment = CalendarOptionsFragment.newInstance();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.calendar_frame_layout, calendarViewFragment);
        transaction.commit();
    }

    public void changeToOptionsFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.calendar_frame_layout, optionsViewFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}