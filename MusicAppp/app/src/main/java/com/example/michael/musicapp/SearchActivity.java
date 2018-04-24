package com.example.michael.musicapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageButton searchButton;
    private Spinner searchGenre;
    View Heading;
    DatabaseReference databaseEvents;

    ListView v;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //link to database /Events
        databaseEvents = FirebaseDatabase.getInstance().getReference("Events");
        //Results list
        v = (ListView) findViewById(R.id.listViewEvents);

        //links for navigation buttons
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



        searchButton = (ImageButton) findViewById(R.id.search_button2);
        //when search button is clicked, this code runs
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                databaseEvents.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        searchBox = (EditText) findViewById(R.id.search_box);
                        searchGenre = (Spinner) findViewById(R.id.search_by_genre);
                        int results_found = 0; //used to count result found
                        float list_height = 20;//used to make list bigger as more result found
                        TextView results = (TextView) findViewById(R.id.result);
                        List<Event> eventList= new ArrayList<>();;
                        //initially result heading set as "no results found"
                        results.setText("No Results Found");
                        //loop to obtian all required data per event
                        for(DataSnapshot eventSnapshot: dataSnapshot.getChildren()){
                            Event event = eventSnapshot.getValue(Event.class);
                            String name = eventSnapshot.child("eventName").getValue(String.class); //name stores event name from database
                            String genre = eventSnapshot.child("eventGenre").getValue(String.class);// this store event genre from database
                            String searchString = searchBox.getText().toString();//this stores a search inputed by the user
                            String genresearchString = searchGenre.getSelectedItem().toString();// this store the genre the user searched for
                            //makes both lower case to compare easier
                            name = name.toLowerCase();
                            searchString = searchString.toLowerCase();
                            //if searched by event name and genre, or only by genre
                            if (name.contains(searchString) && genre.contentEquals(genresearchString)){
                                eventList.add(event);
                                results_found = results_found + 1;
                                list_height = list_height + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 110, getResources().getDisplayMetrics());
                            }
                            //if searched by only event name
                            else if (name.contains(searchString) && genresearchString.contentEquals("Select Genre")){
                                eventList.add(event);
                                results_found = results_found + 1;
                                list_height = list_height + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 110, getResources().getDisplayMetrics());
                            }

                            //if result found, this displays amount of results found
                            if (results_found >= 1) results.setText("RESULTS FOUND: " + results_found);

                            //makes amount of result found visible
                            results.setVisibility(View.VISIBLE);
                        }
                        EventList adapter = new EventList(SearchActivity.this, eventList);
                        //sets list height
                        v.getLayoutParams().height = (int)list_height;
                        v.requestLayout();
                        //inputs events found into the list
                        v.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }



}
