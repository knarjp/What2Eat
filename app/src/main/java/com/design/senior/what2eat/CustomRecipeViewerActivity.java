package com.design.senior.what2eat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.design.senior.what2eat.DatabaseComponents.AppDatabase;
import com.design.senior.what2eat.DatabaseComponents.Entities.temp_data;
import com.design.senior.what2eat.ListViewAdapters.RecipeEditorAdaptor;

import java.util.List;

/**
 * Created by KJ on 2/10/2018.
 */

public class CustomRecipeViewerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;

    private AppDatabase db;

    private List<temp_data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customrecipeviewer_layout);

        db = AppDatabase.getAppDataBase(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.customRecipeViewerList);

        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        Thread getDatabaseThread = new Thread(new Runnable() {
            public void run() {
               dataList = db.tempDataDao().getAllData();
            }
        });

        getDatabaseThread.start();
        try {
            getDatabaseThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RecipeEditorAdaptor adapter = new RecipeEditorAdaptor(dataList, this);
        recyclerView.setAdapter(adapter);
    }
}