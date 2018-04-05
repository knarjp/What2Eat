package com.design.senior.what2eat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.design.senior.what2eat.DatabaseComponents.AppDatabase;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.ListViewAdapters.MealEditorListAdapter;
import com.design.senior.what2eat.R;

import java.util.ArrayList;
import java.util.List;

public class CustomMealListFragment extends Fragment {

    private String name = "";
    private List<Integer> mealIDs;
    private List<Meal> meals;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TextView emptyMeals;

    private static final String MEALS_ARG = "meals";
    private static final String NAME_ARG = "name";

    private AppDatabase database;

    public CustomMealListFragment() {
        // Required empty public constructor
    }

    public static CustomMealListFragment newInstance(List<Integer> mealIDs, String name) {
        // make an empty fragment and return it
        CustomMealListFragment fragment = new CustomMealListFragment();

        Bundle args = new Bundle();

        args.putIntegerArrayList(MEALS_ARG, (ArrayList<Integer>) mealIDs);
        args.putString(NAME_ARG, name);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_meals_tab, container, false);

        emptyMeals = (TextView) view.findViewById(R.id.empty_custom_meals);

        recyclerView = (RecyclerView) view.findViewById(R.id.CreatedMeals);

        if(getArguments() != null) {
            name = getArguments().getString(NAME_ARG);
            mealIDs = getArguments().getIntegerArrayList(MEALS_ARG);

            database = AppDatabase.getAppDatabase(getContext());

            Thread getDatabaseThread = new Thread(new Runnable() {
                public void run() {
                    meals = database.mealDao().getAllCustomMeals();
                }
            });

            getDatabaseThread.start();
            try {
                getDatabaseThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(meals == null || meals.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                emptyMeals.setVisibility(View.VISIBLE);
            } else {
               recyclerView.setVisibility(View.VISIBLE);
               emptyMeals.setVisibility(View.GONE);

               ArrayList<Meal> mealsToAdd = new ArrayList<>();

               for(Meal meal : meals) {
                   if(mealIDs.contains(meal.getMealID())) {
                       mealsToAdd.add(meal);
                   }
               }

               recyclerView.setHasFixedSize(true);
               linearLayoutManager = new LinearLayoutManager(getContext());
               recyclerView.setLayoutManager(linearLayoutManager);
               MealEditorListAdapter listAdapter = new MealEditorListAdapter(getActivity(), mealsToAdd, getContext());
               recyclerView.setAdapter(listAdapter);
           }
        }
        return view;
    }
}