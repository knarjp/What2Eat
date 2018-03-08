package com.design.senior.what2eat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.design.senior.what2eat.DatabaseComponents.AppDatabase;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.ListViewAdapters.MealEditorListAdaptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJ on 2/10/2018.
 */

public class CustomMealListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;

    private AppDatabase db;

    private List<Meal> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_meal_list_layout);

        db = AppDatabase.getAppDatabase(getApplicationContext());

        Thread getDatabaseThread = new Thread(new Runnable() {
            public void run() {
               dataList = db.mealDao().getAllMeals();
            }
        });

        getDatabaseThread.start();
        try {
            getDatabaseThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(dataList != null && dataList.isEmpty()) {
            Toast.makeText(getApplicationContext(), "wow", Toast.LENGTH_SHORT).show();
        }

        recyclerView = (RecyclerView) findViewById(R.id.CustomMealList);

        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        MealEditorListAdaptor adapter = new MealEditorListAdaptor(dataList, this);
        recyclerView.setAdapter(adapter);
    }
}