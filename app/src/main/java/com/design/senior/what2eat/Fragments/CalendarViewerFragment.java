package com.design.senior.what2eat.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

// Material Calendar View - Copyright (c) 2017 Prolific Interactive - see CREDITS.md for licensing credits
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DayDecorators.CurrentDayDecorator;
import com.design.senior.what2eat.DayDecorators.OccupiedDayDecorator;
import com.design.senior.what2eat.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by KJ on 3/24/2018.
 */

public class CalendarViewerFragment extends Fragment {

    private MaterialCalendarView materialCalendarView;
    private CurrentDayDecorator currentDayDecorator;
    private OccupiedDayDecorator occupiedDayDecorator;

    private Button generateButton;
    private Button generationOptionsButton;

    public CalendarViewerFragment() {
        // Required empty public constructor
    }

    public static CalendarViewerFragment newInstance() {
        // make an empty fragment and return it
        CalendarViewerFragment fragment = new CalendarViewerFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar_view, container, false);

        // assign view fields to xml IDs
        materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.MaterialCalendarView);
        generateButton = (Button) view.findViewById(R.id.GenerateButton);
        generationOptionsButton = (Button) view.findViewById(R.id.OptionsButton);

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

                Toast.makeText(getContext(), "clicked a day", Toast.LENGTH_SHORT).show();
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
                // TODO: switch to options fragment on click
            }
        });

        return view;
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
