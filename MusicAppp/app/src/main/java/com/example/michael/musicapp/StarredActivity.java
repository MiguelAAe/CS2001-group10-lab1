package com.example.michael.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Hanif on 16/04/2018.
 */

public class StarredActivity extends AppCompatActivity {

    private LinearLayout btnSearch, btnChat, btnHome, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starred);

        btnSearch = (LinearLayout) findViewById(R.id.search_button);
        //If this button is clicked then it activates the sign up class
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StarredActivity.this, SearchActivity.class));

            }
        });
        btnChat = (LinearLayout) findViewById(R.id.chat_button);
        //If this button is clicked then it activates the sign up class
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StarredActivity.this, ChatActivity.class));

            }
        });
        btnHome = (LinearLayout) findViewById(R.id.home_button);
        //If this button is clicked then it activates the sign up class
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StarredActivity.this, MainActivity.class));

            }
        });
        btnSettings = (LinearLayout) findViewById(R.id.setting_button);
        //If this button is clicked then it activates the sign up class
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StarredActivity.this, SettingsActivity.class));

            }
        });
    }
}
