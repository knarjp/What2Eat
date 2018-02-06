package com.design.senior.what2eat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

// Material Calendar View - Copyright (c) 2017 Prolific Interactive - see LICENSE.md for licensing credits
import com.design.senior.what2eat.DayDecorators.CurrentDayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

/**
 * Created by KJ on 2/3/2018.
 */

public class CalendarGeneratorActivity extends AppCompatActivity {

    private MaterialCalendarView mCalendarView;
    private CurrentDayDecorator currentDayDecorator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendargenerator_layout);

        currentDayDecorator = new CurrentDayDecorator(Color.RED, CalendarDay.today());

        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        mCalendarView.addDecorator(currentDayDecorator);

        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                    String dateString = date.getMonth() + 1 + "/" + date.getDay() + "/" + date.getYear(); // note: months start at 0
                    String message = "I am a recipe list";

                    Intent intent = new Intent(CalendarGeneratorActivity.this, RecipeViewerActivity.class);
                    intent.putExtra("date", dateString);
                    intent.putExtra("message", message);
                    startActivity(intent);
            }
        });
    }
}
