package com.design.senior.what2eat;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

// Material Calendar View - Copyright (c) 2017 Prolific Interactive - see CREDITS.md for licensing credits
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DayDecorators.CurrentDayDecorator;
import com.design.senior.what2eat.DayDecorators.OccupiedDayDecorator;
import com.design.senior.what2eat.Fragments.CalendarOptionsFragment;
import com.design.senior.what2eat.Fragments.CalendarViewerFragment;
import com.design.senior.what2eat.Fragments.MealViewerFragment;
import com.design.senior.what2eat.MealGenerators.MealGenerator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by KJ on 2/3/2018.
 */

public class CalendarActivity extends AppCompatActivity {

  /*  private MaterialCalendarView materialCalendarView;
    private CurrentDayDecorator currentDayDecorator;
    private OccupiedDayDecorator occupiedDayDecorator;

    private ScrollView scrollView;

    private Button generateButton;

    private Button generationOptionsButton;
    private RelativeLayout generationOptionsLayout;*/

    private MealGenerator mealGenerator;

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
}