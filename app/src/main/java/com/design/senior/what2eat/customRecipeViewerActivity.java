package com.design.senior.what2eat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by KJ on 2/10/2018.
 */

public class customRecipeViewerActivity extends AppCompatActivity {

    private TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customrecipeviewer_layout);
        messageText = (TextView) findViewById(R.id.messageViewCalendarRecipeList);

        messageText.setText("Custom Recipe List");
    }
}
