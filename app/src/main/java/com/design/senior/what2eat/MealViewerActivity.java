package com.design.senior.what2eat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by KJ on 2/3/2018.
 */

public class MealViewerActivity extends AppCompatActivity {

    private TextView dateText;
    private TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealviewer_layout);
        dateText = (TextView) findViewById(R.id.DateViewCalendarMealList);
        messageText = (TextView) findViewById(R.id.MessageViewCalendarMealList);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        String message = incomingIntent.getStringExtra("message");

        dateText.setText(date);
        messageText.setText(message);
    }
}
