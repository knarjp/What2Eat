package com.design.senior.what2eat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

// Material Calendar View - Copyright (c) 2017 Prolific Interactive - see CREDITS.md for licensing credits
import com.design.senior.what2eat.DayDecorators.CurrentDayDecorator;
import com.design.senior.what2eat.DayDecorators.OccupiedDayDecorator;
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

    private MaterialCalendarView materialCalendarView;
    private CurrentDayDecorator currentDayDecorator;
    private OccupiedDayDecorator occupiedDayDecorator;

    private Button generateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        generateButton = (Button) findViewById(R.id.GenerateButton);

        currentDayDecorator = new CurrentDayDecorator(Color.RED);
        materialCalendarView.addDecorator(currentDayDecorator);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                    String dateString = date.getMonth() + 1 + "/" + date.getDay() + "/" + date.getYear(); // note: months start at 0
                    String message = "I am a recipe list";

                    Intent intent = new Intent(CalendarActivity.this, MealViewerActivity.class);
                    intent.putExtra("date", dateString);
                    intent.putExtra("message", message);
                    startActivity(intent);
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
    }
}
