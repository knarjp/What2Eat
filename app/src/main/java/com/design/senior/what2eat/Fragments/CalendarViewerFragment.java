package com.design.senior.what2eat.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Material Calendar View - Copyright (c) 2017 Prolific Interactive - see CREDITS.md for licensing credits
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import com.design.senior.what2eat.DayDecorators.CurrentDayDecorator;
import com.design.senior.what2eat.DayDecorators.OccupiedDayDecorator;
import com.design.senior.what2eat.R;

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

    private EditText calorieTargetTextbox;

    private CalendarViewToParentActivityCommunicator communicator;

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
        calorieTargetTextbox = (EditText) view.findViewById(R.id.CaloricAmountTextbox);

        currentDayDecorator = new CurrentDayDecorator(Color.RED);
        materialCalendarView.addDecorator(currentDayDecorator);

        List<Date> dates = communicator.getMarkedDatesForCalendar();
        setOccupiedDayDecorator(dates);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Date day = date.getDate();

                List<Meal> meals = communicator.getMealsForDay(day);
                List<MealEntryJoin> mealEntryJoins = communicator.getGeneratedEntriesForDay(day);

                if(mealEntryJoins == null || mealEntryJoins.isEmpty()) {
                    Toast.makeText(getContext(), "No meals generated for this day!", Toast.LENGTH_SHORT).show();
                } else {
                    communicator.changeToMealListFragment(meals, mealEntryJoins);
                }
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(calorieTargetTextbox.getText().toString())) {
                    Toast.makeText(getContext(), "Please enter a valid caloric intake!", Toast.LENGTH_LONG).show();
                } else {
                    communicator.generateMeals(Integer.parseInt(calorieTargetTextbox.getText().toString()));

                    List<Date> dates = communicator.getMarkedDatesForCalendar();

                    setOccupiedDayDecorator(dates);
                }
            }
        });

        generationOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.changeToOptionsFragment(); // send event to the host activity
            }
        });

        return view;
    }

    public void setOccupiedDayDecorator(List<Date> datesToMark) {
        occupiedDayDecorator = new OccupiedDayDecorator(Color.GREEN, datesToMark);

        materialCalendarView.addDecorator(occupiedDayDecorator);
    }

    public interface CalendarViewToParentActivityCommunicator {
        void changeToOptionsFragment();
        void changeToMealListFragment(List<Meal> meals, List<MealEntryJoin> mealEntryJoins);
        void generateMeals(int calorieTarget);
        List<Date> getMarkedDatesForCalendar();
        List<Meal> getMealsForDay(Date day);
        List<MealEntryJoin> getGeneratedEntriesForDay(Date day);
    }

    @Override
    public void onAttach(Context context) { // required for android API versions on or after 23
        super.onAttach(context);

        Activity activity;

        if(context instanceof Activity) { // TODO: oh god this is gross figure out how to get around this
            activity = (Activity) context;

            try {
                communicator = (CalendarViewToParentActivityCommunicator) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + "must implement CalendarViewToParentActivityCommunicator");
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) { // required for android API versions before 23
        super.onAttach(activity);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                communicator = (CalendarViewToParentActivityCommunicator) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + "must implement CalendarViewToParentActivityCommunicator");
            }
        }
    }
}
