package com.design.senior.what2eat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

/**
 * Created by KJ on 2/3/2018.
 */

public class CalendarGeneratorActivity extends AppCompatActivity {

    private CalendarView mCalendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendargenerator_layout);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year; // note: months start at 0
                String message = "I am a recipe list";

                Intent intent = new Intent(CalendarGeneratorActivity.this, RecipeViewerActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("message", message);
                startActivity(intent);
            }
        });
    }
}
