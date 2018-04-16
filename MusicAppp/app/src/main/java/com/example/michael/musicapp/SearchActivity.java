package com.example.michael.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanif on 10/04/2018.
 */



public class SearchActivity extends AppCompatActivity {

    private LinearLayout btnHome, btnSettings, btnChat, btnStar;
    private EditText searchBox;
    private Button searchButton;

    DatabaseReference databaseEvents;

    ListView v;
    List<Event> eventList;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        databaseEvents = FirebaseDatabase.getInstance().getReference("Events");
        v = (ListView) findViewById(R.id.listViewEvents);
        eventList = new ArrayList<>();


        btnSettings = (LinearLayout) findViewById(R.id.setting_button);
        //If this button is clicked then it activates the sign up class
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, SettingsActivity.class));

            }
        });
        btnStar = (LinearLayout) findViewById(R.id.star_button);
        //If this button is clicked then it activates the sign up class
        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, StarredActivity.class));

            }
        });
        btnHome = (LinearLayout) findViewById(R.id.home_button);
        //If this button is clicked then it activates the sign up class
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));

            }
        });
        btnChat= (LinearLayout) findViewById(R.id.chat_button);
        //If this button is clicked then it activates the sign up class
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, ChatActivity.class));
            }
        });

        searchBox = (EditText) findViewById(R.id.search_box);

        searchButton = (Button) findViewById(R.id.search_button2);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                databaseEvents.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot eventSnapshot: dataSnapshot.getChildren()){
                            Event event = eventSnapshot.getValue(Event.class);

                            eventList.add(event);

                        }

                        EventList adapter = new EventList(SearchActivity.this, eventList);
                        v.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();



    }


}
