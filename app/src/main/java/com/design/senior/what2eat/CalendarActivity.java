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

       /* mealGenerator = new MealGenerator();

        setContentView(R.layout.calendar_layout);

        scrollView = (ScrollView) findViewById(R.id.calendar_scrollview);

        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        generateButton = (Button) findViewById(R.id.GenerateButton);

        generationOptionsButton = (Button) findViewById(R.id.optionsButton);

        currentDayDecorator = new CurrentDayDecorator(Color.RED);
        materialCalendarView.addDecorator(currentDayDecorator);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
          //          String dateString = date.getMonth() + 1 + "/" + date.getDay() + "/" + date.getYear(); // note: months start at 0
             //       String message = "I am a recipe list";

             //       Intent intent = new Intent(CalendarActivity.this, MealViewerActivity.class);
              //      intent.putExtra("date", dateString);
              //      intent.putExtra("message", message);
               //

                // TODO: make logic for creating list page of generated recipes

                Toast.makeText(getApplicationContext(), "clicked a day", Toast.LENGTH_SHORT).show();
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarDay startDay = CalendarDay.today();

                List<Date> datesToGenerate = createDatesForGeneration(startDay);

                occupiedDayDecorator = new OccupiedDayDecorator(Color.GREEN, datesToGenerate);

                materialCalendarView.addDecorator(occupiedDayDecorator);
            }
        });

        generationOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(generationOptionsLayout.getVisibility() == View.VISIBLE) {
                    generationOptionsLayout.setVisibility(View.GONE);
                    Log.d("What2Eat", ""+generationOptionsLayout.getBottom());
                } else {
                    generationOptionsLayout.setVisibility(View.VISIBLE);
                    Log.d("What2Eat", ""+generationOptionsLayout.getBottom());
                    scrollView.scrollTo(0, generationOptionsLayout.getBottom());
                }
            }
        });
    }

    private ArrayList<Date> createDatesForGeneration(CalendarDay startDay) {

        Date currentDate = startDay.getDate();

        ArrayList<Date> dates = new ArrayList<Date>();
        dates.add(currentDate);

        Calendar calendar = Calendar.getInstance();

        for(int i = 0; i < 6; i++) {
            // increment date
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, 1);
            currentDate = calendar.getTime();

            // add it to dates list
            dates.add(currentDate);
        }

        return dates;
    }*/
    }
}