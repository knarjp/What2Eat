package com.design.senior.what2eat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnViewCalendar;
    private Button viewCustomRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewCalendar = (Button) findViewById(R.id.btnViewCalendar);
        viewCustomRecipeButton = (Button) findViewById(R.id.viewCustomRecipeButton);

        btnViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarGeneratorActivity.class);
                startActivity(intent);
            }
        });

        viewCustomRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, customRecipeViewerActivity.class);
                startActivity(intent);
            }
        });
    }
}
