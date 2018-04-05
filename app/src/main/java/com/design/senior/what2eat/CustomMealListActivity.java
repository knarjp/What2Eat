package com.design.senior.what2eat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.design.senior.what2eat.DatabaseComponents.AppDatabase;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;
import com.design.senior.what2eat.DatabaseComponents.Enums.EntryType;
import com.design.senior.what2eat.DatabaseComponents.Enums.MealTime;
import com.design.senior.what2eat.FragmentPagerAdapters.SectionsPageAdapter;
import com.design.senior.what2eat.Fragments.CustomMealListFragment;
import com.design.senior.what2eat.ListViewAdapters.MealEditorListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJ on 2/10/2018.
 */

public class CustomMealListActivity extends AppCompatActivity
                                    implements MealEditorListAdapter.EditorFragmentRefresher {

    private AppDatabase db;

    private List<Meal> mealList;
    private List<Integer> breakfastIDList;
    private List<Integer> lunchIDList;
    private List<Integer> dinnerIDList;

    private SectionsPageAdapter sectionsPageAdapter;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private FloatingActionButton floatingActionButton;

    private MealTime currentMealTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_meal_list_layout);

        db = AppDatabase.getAppDatabase(getApplicationContext());

        mealList = new ArrayList<>();
        breakfastIDList = new ArrayList<>();
        lunchIDList = new ArrayList<>();
        dinnerIDList = new ArrayList<>();

        currentMealTime = MealTime.BREAKFAST;

        populateLists();

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch(position) {
                    case 0:
                        currentMealTime = MealTime.BREAKFAST;
                        break;
                    case 1:
                        currentMealTime = MealTime.LUNCH;
                        break;
                    case 2:
                        currentMealTime = MealTime.DINNER;
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch(position) {
                    case 0:
                        currentMealTime = MealTime.BREAKFAST;
                        break;
                    case 1:
                        currentMealTime = MealTime.LUNCH;
                        break;
                    case 2:
                        currentMealTime = MealTime.DINNER;
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // no behavior
            }
        });

        floatingActionButton = (FloatingActionButton) findViewById(R.id.newMealButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();

                Meal newMeal = new Meal();

                ArrayList<AllergyType> allergyTypes = new ArrayList<>();
                allergyTypes.add(AllergyType.NONE);

                newMeal.setAllergiesEnum(allergyTypes);
                newMeal.setDietTypeEnum(DietType.NONE);

                newMeal.setMealTimeEnum(currentMealTime);
                newMeal.setEntryTypeEnum(EntryType.CUSTOM);

                Intent intent = new Intent(context, MealEditorActivity.class);
                intent.putExtra("meal", newMeal);
                intent.putExtra("isNewMeal", true);
                context.startActivity(intent);
            }
        });
    }

    private void populateLists() {

        breakfastIDList.clear();
        lunchIDList.clear();
        dinnerIDList.clear();
        mealList.clear();

        Thread getDatabaseThread = new Thread(new Runnable() {
            public void run() {
                mealList = db.mealDao().getAllCustomMeals();
            }
        });

        getDatabaseThread.start();
        try {
            getDatabaseThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Meal meal : mealList) {
            switch(meal.getMealTimeEnum()) {
                case BREAKFAST:
                    breakfastIDList.add(meal.getMealID());
                    break;
                case LUNCH:
                    lunchIDList.add(meal.getMealID());
                    break;
                case DINNER:
                    dinnerIDList.add(meal.getMealID());
                    break;
                default:
                    throw new RuntimeException("Invalid meal time/meal time not found!");
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPager.setAdapter(null); // clear viewPager

        int startingTab = 0;

        switch(currentMealTime) {
            case BREAKFAST:
                startingTab = 0;
                break;
            case LUNCH:
                startingTab = 1;
                break;
            case DINNER:
                startingTab = 2;
                break;
        }

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        adapter.addFragment(CustomMealListFragment.newInstance(breakfastIDList, "Breakfasts"), "Breakfasts");
        adapter.addFragment(CustomMealListFragment.newInstance(lunchIDList, "Lunches"), "Lunches");
        adapter.addFragment(CustomMealListFragment.newInstance(dinnerIDList, "Dinners"), "Dinners");

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(startingTab);
    }

    public void deleteMealFromDatabase(final Meal meal) {
        Thread deleteDatabaseThread = new Thread(new Runnable() {
            public void run() {
                db.mealDao().deleteMealTuple(meal);
            }
        });

        deleteDatabaseThread.start();
        try {
            deleteDatabaseThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        populateLists();
        setupViewPager(viewPager);
    }
}