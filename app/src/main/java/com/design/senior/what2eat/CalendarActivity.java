package com.design.senior.what2eat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

// Material Calendar View - Copyright (c) 2017 Prolific Interactive - see CREDITS.md for licensing credits
import com.design.senior.what2eat.DatabaseComponents.AppDatabase;
import com.design.senior.what2eat.DatabaseComponents.Entities.Entry;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;
import com.design.senior.what2eat.Fragments.CalendarOptionsFragment;
import com.design.senior.what2eat.Fragments.CalendarViewerFragment;
import com.design.senior.what2eat.Fragments.GeneratedMealListFragment;
import com.design.senior.what2eat.ListViewAdapters.GeneratedMealListAdapter;
import com.design.senior.what2eat.MealGenerators.MealGenerator;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by KJ on 2/3/2018.
 */

public class CalendarActivity extends FragmentActivity
        implements CalendarViewerFragment.CalendarViewToParentActivityCommunicator,
         CalendarOptionsFragment.OptionsViewToParentActivityCommunicator,
         GeneratedMealListAdapter.FragmentRefresher {

    private MealGenerator mealGenerator;
    private AppDatabase appDatabase;

    private CalendarViewerFragment calendarViewFragment;
    private CalendarOptionsFragment optionsViewFragment;
    private GeneratedMealListFragment mealListFragment;

    private List<Meal> meals;
    private List<Entry> entries;
    private List<MealEntryJoin> joins;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        appDatabase = AppDatabase.getAppDatabase(getApplicationContext());

        mealGenerator = new MealGenerator();

        calendarViewFragment = CalendarViewerFragment.newInstance();
        optionsViewFragment = CalendarOptionsFragment.newInstance();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.calendar_frame_layout, calendarViewFragment);
        transaction.commit();
    }

    public void changeToOptionsFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.calendar_frame_layout, optionsViewFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToMealListFragment(List<Meal> meals, List<MealEntryJoin> mealEntryJoins, Date day) {
        mealListFragment = GeneratedMealListFragment.newInstance(meals, mealEntryJoins, day);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.calendar_frame_layout, mealListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void
    deleteGeneratedEntryFromDatabase(final MealEntryJoin mealEntryJoin) {
        Thread deleteMealEntryJoinThread = new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.mealEntryJoinDao().deleteEntryTuple(mealEntryJoin);
            }
        });

        deleteMealEntryJoinThread.start();

        try {
            deleteMealEntryJoinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void refreshView() {
        getSupportFragmentManager().beginTransaction()
                .detach(mealListFragment)
                .attach(mealListFragment)
                .commitAllowingStateLoss();
    }

    public List<Date> getMarkedDatesForCalendar() {
        List<Date> dates = new ArrayList<>();

        Thread getGeneratedMealsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                entries = appDatabase.mealEntryJoinDao().getAllGeneratedEntries();
            }
        });

        getGeneratedMealsThread.start();

        try {
            getGeneratedMealsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Entry entry : entries) {
            dates.add(entry.getDateAsDate());
        }

        return dates;
    }

    public List<Meal> getMealsForDay(final Date day) {
        meals = null;

        Thread getMealsFromDateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                entries = appDatabase.entryDao().getAllEntries();

                for(Entry entry : entries) {
                    if(entry.getDateAsDate().equals(day)) {
                        meals = appDatabase.mealEntryJoinDao().getMealsFromEntry(entry.getID());
                    }
                }
            }
        });

        getMealsFromDateThread.start();

        try {
            getMealsFromDateThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return meals;
    }

    public List<MealEntryJoin> getGeneratedEntriesForDay(final Date day) {
        joins = null;

        Thread getMealsFromDateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                entries = appDatabase.entryDao().getAllEntries();

                for(Entry entry : entries) {
                    if(entry.getDateAsDate().equals(day)) {
                        joins = appDatabase.mealEntryJoinDao().getMealEntryJoinsFromEntryID(entry.getID());
                    }
                }
            }
        });

        getMealsFromDateThread.start();

        try {
            getMealsFromDateThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return joins;
    }

    public void generateMeals(int calorieTarget) {
        mealGenerator.setCalorieTarget(calorieTarget);

        Thread getMealsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                meals = appDatabase.mealDao().getAllMeals();
            }
        });

        Thread getEntriesThread = new Thread(new Runnable() {
            @Override
            public void run() {
                entries = appDatabase.entryDao().getAllEntries();
            }
        });

        getMealsThread.start();
        getEntriesThread.start();

        try {
            getMealsThread.join();
            getEntriesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date currentDate = CalendarDay.today().getDate();
        List<Date> dates = new ArrayList<>();
        dates.add(currentDate);

        Calendar calendar = Calendar.getInstance();
        int numDaysToGenerateTo = 6;

        for(int i = 0; i < numDaysToGenerateTo; i++) {
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, 1);
            currentDate = calendar.getTime();

            dates.add(currentDate);
        }

        final List<Entry> newEntries = new ArrayList<>();

        for(final Date date : dates) { // delete any entries (and their corresponding entries in the join table) if dates overlap
            for(final Entry entry : entries) {
                if(date.equals(entry.getDateAsDate())) {
                    Thread deleteEntryThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int times = appDatabase.entryDao().deleteEntryTuple(entry);
                        }
                    });

                    deleteEntryThread.start();

                    try {
                        deleteEntryThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            Thread setEntriesThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Entry newEntry = new Entry();
                    newEntry.setDate(date);
                    newEntries.add(newEntry);

                    appDatabase.entryDao().inserEntryTuples(newEntry);
                }
            });

            setEntriesThread.start();

            try {
                setEntriesThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        final List<Entry> entriesToProcess = new ArrayList<>();

        Thread getNewEntriesThread = new Thread(new Runnable() {
            @Override
            public void run() {
                entries = appDatabase.entryDao().getAllEntries();
            }
        });

        getNewEntriesThread.start();

        try {
            getNewEntriesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<MealEntryJoin> generatedMeals = mealGenerator.generateMeals(meals, entries);

        for(final MealEntryJoin mealEntryJoin : generatedMeals) {
            Thread setGeneratedMealsThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    appDatabase.mealEntryJoinDao().insertMealEntry(mealEntryJoin);
                }
            });

            setGeneratedMealsThread.start();

            try {
                setGeneratedMealsThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Meals Generated!", Toast.LENGTH_LONG).show();
    }

    public void setDietType(List<DietType> diets) {
        mealGenerator.setAllowedDiets(diets);
    }

    public void addAllergy(AllergyType allergyType) {
        mealGenerator.addDisallowedAllergy(allergyType);
    }

    public void removeAllergy(AllergyType allergyType) {
        mealGenerator.removeDisallowedAllergy(allergyType);
    }
}