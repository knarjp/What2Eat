package com.design.senior.what2eat.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;
import com.design.senior.what2eat.DatabaseComponents.Enums.MealTime;
import com.design.senior.what2eat.ListViewAdapters.GeneratedMealListAdapter;
import com.design.senior.what2eat.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by KJ on 3/26/2018.
 */

public class GeneratedMealListFragment extends Fragment {
    private RecyclerView breakfastsRecyclerView;
    private RecyclerView lunchesRecyclerView;
    private RecyclerView dinnersRecyclerView;

    private TextView emptyBreakfasts;
    private TextView emptyLunches;
    private TextView emptyDinners;

    private TextView currentDate;

    private LinearLayoutManager breakfastLinearLayoutManager;
    private LinearLayoutManager lunchLinearLayoutManager;
    private LinearLayoutManager dinnerLinearLayoutManager;

    private static final String BREAKFASTS_ARG = "breakfasts";
    private static final String LUNCHES_ARG = "lunches";
    private static final String DINNERS_ARG = "dinners";
    private static final String JOINS_ARG = "joins";
    private static final String DATE_ARG = "date";

    public GeneratedMealListFragment() {
        // Required empty public constructor
    }

    public static GeneratedMealListFragment newInstance(List<Meal> meals, List<MealEntryJoin> entryJoins, Date date) {
        // make an empty fragment and return it
        GeneratedMealListFragment fragment = new GeneratedMealListFragment();

        ArrayList<Meal> breakfasts = new ArrayList<>();
        ArrayList<Meal> lunches = new ArrayList<>();
        ArrayList<Meal> dinners = new ArrayList<>();

        for(Meal meal : meals) {
            if(meal.getMealTimeEnum().equals(MealTime.BREAKFAST)) {
                breakfasts.add(meal);
            } else if(meal.getMealTimeEnum().equals(MealTime.LUNCH)) {
                lunches.add(meal);
            } else if(meal.getMealTimeEnum().equals(MealTime.DINNER)) {
                dinners.add(meal);
            } else {
                throw new RuntimeException("invalid time of day for meal in GeneratedMealListFragment.java");
            }
        }

        Bundle args = new Bundle();

        args.putParcelableArrayList(BREAKFASTS_ARG, breakfasts);
        args.putParcelableArrayList(LUNCHES_ARG, lunches);
        args.putParcelableArrayList(DINNERS_ARG, dinners);

        args.putParcelableArrayList(JOINS_ARG, (ArrayList<MealEntryJoin>) entryJoins);

        args.putString(DATE_ARG, DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(date));

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generated_meals, container, false);

        breakfastsRecyclerView = (RecyclerView) view.findViewById(R.id.GeneratedBreakfasts);
        lunchesRecyclerView = (RecyclerView) view.findViewById(R.id.GeneratedLunches);
        dinnersRecyclerView = (RecyclerView) view.findViewById(R.id.GeneratedDinners);

        emptyBreakfasts = (TextView) view.findViewById(R.id.empty_breakfasts);
        emptyLunches = (TextView) view.findViewById(R.id.empty_lunches);
        emptyDinners = (TextView) view.findViewById(R.id.empty_dinners);

        currentDate = (TextView) view.findViewById(R.id.currentDate);

        if(getArguments() != null) {
            currentDate.setText(getArguments().getString(DATE_ARG));

            ArrayList<Meal> breakfasts = getArguments().getParcelableArrayList(BREAKFASTS_ARG);

            ArrayList<MealEntryJoin> joins = getArguments().getParcelableArrayList(JOINS_ARG);

            if(breakfasts == null || breakfasts.isEmpty()) {
                breakfastsRecyclerView.setVisibility(View.GONE);
                emptyBreakfasts.setVisibility(View.VISIBLE);
            } else {
                breakfastsRecyclerView.setVisibility(View.VISIBLE);
                emptyBreakfasts.setVisibility(View.GONE);

                breakfastsRecyclerView.setHasFixedSize(true);
                breakfastLinearLayoutManager = new LinearLayoutManager(getContext());
                breakfastsRecyclerView.setLayoutManager(breakfastLinearLayoutManager);
                GeneratedMealListAdapter breakfastAdapter = new GeneratedMealListAdapter(getActivity(), breakfasts, joins, getContext());
                breakfastsRecyclerView.setAdapter(breakfastAdapter);
            }

            ArrayList<Meal> lunches = getArguments().getParcelableArrayList(LUNCHES_ARG);

            if(lunches == null || lunches.isEmpty()) {
                lunchesRecyclerView.setVisibility(View.GONE);
                emptyLunches.setVisibility(View.VISIBLE);
            } else {
                lunchesRecyclerView.setVisibility(View.VISIBLE);
                emptyLunches.setVisibility(View.GONE);

                lunchesRecyclerView.setHasFixedSize(true);
                lunchLinearLayoutManager = new LinearLayoutManager(getContext());
                lunchesRecyclerView.setLayoutManager(lunchLinearLayoutManager);
                GeneratedMealListAdapter lunchAdapter = new GeneratedMealListAdapter(getActivity(), lunches, joins, getContext());
                lunchesRecyclerView.setAdapter(lunchAdapter);
            }

            ArrayList<Meal> dinners = getArguments().getParcelableArrayList(DINNERS_ARG);

            if(dinners == null || dinners.isEmpty()) {
                dinnersRecyclerView.setVisibility(View.GONE);
                emptyDinners.setVisibility(View.VISIBLE);
            } else {
                dinnersRecyclerView.setVisibility(View.VISIBLE);
                emptyDinners.setVisibility(View.GONE);

                dinnersRecyclerView.setHasFixedSize(true);
                dinnerLinearLayoutManager = new LinearLayoutManager(getContext());
                dinnersRecyclerView.setLayoutManager(dinnerLinearLayoutManager);
                GeneratedMealListAdapter dinnerAdapter = new GeneratedMealListAdapter(getActivity(), dinners, joins, getContext());
                dinnersRecyclerView.setAdapter(dinnerAdapter);
            }
        }
        return view;
    }
}